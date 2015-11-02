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
import com.brittonn.mvcpract.security.UserNotAutenticatedException;

@Controller
@RequestMapping("/autheticate")
public class LoginController {

	@Autowired 
	private MySecurityProvider securityProvider;
	
	@Autowired
	private FoodItemController foodItemController;
	
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView autheticate(@ModelAttribute("SpringWeb") LoginForm loginForm, ModelMap model, HttpServletResponse response) throws UserNotAutenticatedException {
		
		String [] roles = {"*"};
		String token = securityProvider.autheticate(loginForm.getUser(), loginForm.getPassword(), roles);
		response.addCookie(new Cookie(MySecurityProvider.TOKEN_HEADER, token));
		response.addCookie(new Cookie(MySecurityProvider.USER_HEADER, loginForm.getUser()));
		
		return foodItemController.getAllFoodItems(model);
	}	
		
	
    @ExceptionHandler(UserNotAutenticatedException.class)
    public ModelAndView handleException(UserNotAutenticatedException ex, HttpServletResponse response) {
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        ModelMap model = new ModelMap();
        model.addAttribute("error", "Login Failed");
        return new ModelAndView("login", model);
    }
}
