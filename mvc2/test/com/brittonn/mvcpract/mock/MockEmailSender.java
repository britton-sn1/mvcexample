package com.brittonn.mvcpract.mock;

import org.springframework.stereotype.Component;

import com.brittonn.mvcpract.EmailSender;

@Component
public class MockEmailSender implements EmailSender {

	@Override
	public boolean sendEmail(String toAddress, String toName, String subject, String message) {
		return true;
	}

}
