package com.brittonn.mvcpract.springmvc;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.brittonn.mvcpract.security.MySecurityProvider;
import com.brittonn.mvcpract.security.PasswordNotSentException;
import com.brittonn.mvcpract.security.UserNotAutenticatedException;

@Controller
@RequestMapping("/autheticate")
public class LoginController {

	@Autowired 
	private MySecurityProvider securityProvider;
	
	@RequestMapping(value="/login",	method = RequestMethod.POST)
	public String autheticate(@ModelAttribute("SpringWeb") LoginForm loginForm, ModelMap model, HttpServletResponse response) throws UserNotAutenticatedException {
		
		String [] roles = {"*"};
		String token = securityProvider.autheticate(loginForm.getUser(), loginForm.getPassword(), roles);

		response.reset();
		addCookie(response, new Cookie(MySecurityProvider.TOKEN_HEADER, token));
		addCookie(response, new Cookie(MySecurityProvider.USER_HEADER, loginForm.getUser()));
		
		return "redirect:/dlmvc/getAllFoodItems"; // using redirect rather than forward as the etAllFoodItems request needs the above cookies.
	}	
		
	
	private void addCookie(HttpServletResponse response, Cookie cookie) {
		cookie.setPath("/");
		response.addCookie(cookie);
	}


	@RequestMapping(value="/forgotpassword", method=RequestMethod.GET)
	public ModelAndView getForgotPasswordForm() {
		return new ModelAndView("forgotPasswordForm");
	}
	
	@RequestMapping(value="/sendnewpassword", 	method = RequestMethod.POST) 
	public ModelAndView sendNewPassword(@ModelAttribute("SpringWeb") RequestNewPasswordForm requestNewPasswordForm , ModelMap modelMap ) {
		ModelAndView modelAndView;
		try {
			securityProvider.sendNewPassword(requestNewPasswordForm);
			modelAndView = new ModelAndView("login");
		} catch (PasswordNotSentException e1) {
			modelAndView = new ModelAndView("forgotPasswordForm");
			modelAndView.addObject("error",e1.getMessage());
		}
		
		return modelAndView;
		
	}
	

	@ExceptionHandler(UserNotAutenticatedException.class)
    public ModelAndView handleException(UserNotAutenticatedException ex, HttpServletResponse response) {
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        ModelMap model = new ModelMap();
        model.addAttribute("error", "Login Failed");
        return new ModelAndView("login", model);
    }
}
