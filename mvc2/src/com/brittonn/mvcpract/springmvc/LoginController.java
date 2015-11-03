package com.brittonn.mvcpract.springmvc;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Random;

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

import com.brittonn.hibpract.dietlog.UserDetailsDao;
import com.brittonn.hibpract.dietlog.beans.UserDetails;
import com.brittonn.mvcpract.EmailSender;
import com.brittonn.mvcpract.security.MySecurityProvider;
import com.brittonn.mvcpract.security.PasswordHash;
import com.brittonn.mvcpract.security.UserNotAutenticatedException;

@Controller
@RequestMapping("/autheticate")
public class LoginController {

	@Autowired 
	private MySecurityProvider securityProvider;
	
	@Autowired
	private FoodItemController foodItemController;
	
	@Autowired 
	UserDetailsDao userDetailsDao;
	
	@Autowired
	EmailSender emailSender;
	
	@RequestMapping(value="/login",	method = RequestMethod.POST)
	public ModelAndView autheticate(@ModelAttribute("SpringWeb") LoginForm loginForm, ModelMap model, HttpServletResponse response) throws UserNotAutenticatedException {
		
		String [] roles = {"*"};
		String token = securityProvider.autheticate(loginForm.getUser(), loginForm.getPassword(), roles);
		response.reset();
		Cookie cookie = new Cookie(MySecurityProvider.TOKEN_HEADER, token);
		cookie.setPath("/");
		response.addCookie(cookie);
		cookie = new Cookie(MySecurityProvider.USER_HEADER, loginForm.getUser());
		cookie.setPath("/");
		response.addCookie(cookie);
		
		return foodItemController.getAllFoodItems(model);
	}	
		
	
	@RequestMapping(value="/forgotpassword", method=RequestMethod.GET)
	public ModelAndView getForgotPasswordForm() {
		return new ModelAndView("forgotPasswordForm");
	}
	
	@RequestMapping(value="/sendnewpassword", 	method = RequestMethod.POST) 
	public ModelAndView sendNewPassword(@ModelAttribute("SpringWeb") RequestNewPasswordForm requestNewPasswordForm , ModelMap modelMap ) {
		UserDetails userDetails = userDetailsDao.getUserDetails(requestNewPasswordForm.getUsername());
		ModelAndView modelAndView = null;
		if(userDetails == null) {
			modelAndView = new ModelAndView("forgotPasswordForm");
			modelAndView.addObject("error", "Unknown User");
		} else if (userDetails.getEmailaddr() == null || userDetails.getEmailaddr().equals(requestNewPasswordForm.getEmailaddr()) == false) {
			modelAndView = new ModelAndView("forgotPasswordForm");
			modelAndView.addObject("error", "Incorrect E-mail Address");
		}
		else {
			modelAndView = new ModelAndView("login");
			String password = generateNewPassword();
			try {
				String hashedPassword = PasswordHash.createHash(password);
				
				emailSender.sendEmail(userDetails.getEmailaddr(), userDetails.getUsername(), "Diet Logger Password Reset", "Your new diet logger password is " + password);
				userDetails.setPwhash(hashedPassword);
				
				userDetailsDao.updateUserDetails(userDetails);
				
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvalidKeySpecException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return modelAndView;
		
	}
	
	
    private String generateNewPassword() {
    	final String passwordChars = "abcdefghijklmnopqrstuwvABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!£$%^&?";
    	final int passwordLength = 10;
    	final Random rand = new Random();
    	final char [] newPasswordChars = new char[passwordLength];
    	for(int i=0; i< passwordLength; i++) {
    		newPasswordChars[i] = passwordChars.charAt(rand.nextInt(passwordChars.length()));
    	}
    	
    	return new String(newPasswordChars);
	}


	@ExceptionHandler(UserNotAutenticatedException.class)
    public ModelAndView handleException(UserNotAutenticatedException ex, HttpServletResponse response) {
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        ModelMap model = new ModelMap();
        model.addAttribute("error", "Login Failed");
        return new ModelAndView("login", model);
    }
}
