package com.brittonn.mvcpract.security;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.brittonn.hibpract.dietlog.UserDetailsDao;
import com.brittonn.hibpract.dietlog.beans.UserDetails;
import com.brittonn.mvcpract.EmailSender;
import com.brittonn.mvcpract.springmvc.RequestNewPasswordForm;

@Component
public class MySecurityProviderImpl implements MySecurityProvider {

	@Autowired
	private UserDetailsDao userDetailsDao;
	
	@Autowired
	EmailSender emailSender;
	
	private Logger log = Logger.getLogger(MySecurityProviderImpl.class);
	
	private static final int ALWAYS_KEEP_SIZE = 1000;
	
	private long sessionTimeout = 5*60*1000;
	
	private class SecurityDetails {
		String userName;
		Set<String> roles;
		long expiryTime;
		
		SecurityDetails(String userName, String [] roles) {
			this.userName = userName;
			this.roles = new HashSet<String>(Arrays.asList(roles));
			expiryTime = System.currentTimeMillis() + getSessionTimeout();
		}
	}

	private Map<String, SecurityDetails> securityDetailsMap = new HashMap<>();
	
	private Random random = new Random(System.currentTimeMillis());

	@Override
	@Transactional
	public String autheticate(String user, String password, String[] requestedUserRoles) throws UserNotAutenticatedException {
		
		UserDetails userDetails = userDetailsDao.getUserDetails(user);
		if(userDetails == null) {
			throw new UserNotAutenticatedException();
		}
		
		try {
			if(PasswordHash.validatePassword(password, userDetails.getPwhash()) == false) {
				throw new UserNotAutenticatedException();
			}
		} catch (Exception e) {
			throw new UserNotAutenticatedException();
		}
		
		String token = Long.valueOf(random.nextLong()).toString();
		log.debug("User autheticated - token : " + token);
		
		securityDetailsMap.put(token, new SecurityDetails(user, requestedUserRoles));
		if(securityDetailsMap.size() > ALWAYS_KEEP_SIZE) {
			shrinkSecurityDetailsMap();
		}
		return token;
	}

	private void shrinkSecurityDetailsMap() {
		
		long now = System.currentTimeMillis();
		int currentMapSize = securityDetailsMap.size();
		log.debug("securityDetailsMap has " + currentMapSize);
		
		
		securityDetailsMap.keySet().stream()
				.sorted((t1,t2)->compareSecurityDetialsTimeout(t1,t2)).skip(ALWAYS_KEEP_SIZE/2) // keep the newest x entries
				.filter(x->securityDetailsMap.get(x).expiryTime < now) // keep those that have timeout
				.forEach(x->removeSecurityDetail(x)); 
		
		log.debug("securityDetailsMap has been reduced by " + (currentMapSize-securityDetailsMap.size()));
	}
	
	private int compareSecurityDetialsTimeout(String t1, String t2) {
		int x = securityDetailsMap.get(t1).expiryTime > securityDetailsMap.get(t2).expiryTime ? -1 : 
			securityDetailsMap.get(t1).expiryTime < securityDetailsMap.get(t2).expiryTime ? 1 : 0;
					
		return x;
	}

	private void removeSecurityDetail(String token) {
		log.debug("Removing token " + token);
		securityDetailsMap.remove(token);
	}

	@Override
	public void checkSecurityToken(String user, String token, String role) throws UserNotAutenticatedException, SessionTimedOutException  {
		
		SecurityDetails securityDetails = securityDetailsMap.get(token);
		
		if(securityDetails == null || 
				securityDetails.userName.equals(user) == false || 
				securityDetails.roles.contains(role) == false)  throw new UserNotAutenticatedException();
		
		if(securityDetails.expiryTime < System.currentTimeMillis()) throw new SessionTimedOutException();
		
		securityDetails.expiryTime = System.currentTimeMillis() + getSessionTimeout();
	}

	private long getSessionTimeout() {
		return sessionTimeout;
	}

	public void setSessionTimeout(long sessionTimeout) {
		this.sessionTimeout = sessionTimeout;
	}

	@Override
	@Transactional
	public void sendNewPassword(RequestNewPasswordForm requestNewPasswordForm) throws PasswordNotSentException {
		UserDetails userDetails = userDetailsDao.getUserDetails(requestNewPasswordForm.getUsername());
		if(userDetails == null) {
			throw new PasswordNotSentException("Unknown User");
		} else if (userDetails.getEmailaddr() == null || userDetails.getEmailaddr().equals(requestNewPasswordForm.getEmailaddr()) == false) {
			throw new PasswordNotSentException("Incorrect E-mail Address");
		}
		else {
			String password = generateNewPassword();
			try {
				String hashedPassword = PasswordHash.createHash(password);
				
				emailSender.sendEmail(userDetails.getEmailaddr(), userDetails.getUsername(), "Diet Logger Password Reset", "Your new diet logger password is " + password);
				userDetails.setPwhash(hashedPassword);
				
				userDetailsDao.updateUserDetails(userDetails);
				
			} catch (NoSuchAlgorithmException e) {
				throw new PasswordNotSentException(e.getLocalizedMessage());
			} catch (InvalidKeySpecException e) {
				throw new PasswordNotSentException(e.getLocalizedMessage());
			}
		}
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


}
