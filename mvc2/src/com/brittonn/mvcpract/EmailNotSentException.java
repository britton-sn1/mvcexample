package com.brittonn.mvcpract;

public class EmailNotSentException extends Exception {

	public EmailNotSentException(Exception e) {
		super(e);
	}

	private static final long serialVersionUID = 2586161459473125730L;

}
