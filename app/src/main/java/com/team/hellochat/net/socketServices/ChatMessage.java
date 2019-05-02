package com.team.hellochat.net.socketServices;

public class ChatMessage {
	public static String getSender(String message) {
		return message.substring(0, 11);
	}

	public static boolean getMessageType(String message) {
		return message.charAt(11) == '0';
	}

	public static String getRecipient(String message) {
		return message.substring(12, 23);
	}

	public static String getMessage(String message) {
		return message.substring(23);
	}
}
