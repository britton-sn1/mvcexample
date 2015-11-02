package com.brittonn.mvcpract;

public interface EmailSender {

	boolean sendEmail(String toAddress, String toName, String subject, String message);
}
