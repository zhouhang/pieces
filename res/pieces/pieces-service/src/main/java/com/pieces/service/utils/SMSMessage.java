package com.pieces.service.utils;

public class SMSMessage {

	private static SendMessage instance;

	private SMSMessage (){}

	public static SendMessage getInstance() {
		if (instance == null) {
			instance = new YPSendMessage();
		}
		return instance;
	}

}
