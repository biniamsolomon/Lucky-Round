package com.bini.Lucky_Round.utils;

import java.security.SecureRandom;

public final class Utilities {

	private Utilities() {

	}

	public static String resolvePhoneNumber(String serviceNumber) {

		if (serviceNumber.startsWith("+251")) {
			return serviceNumber.replace("+", "");
		}
		else if (serviceNumber.startsWith("07")) {
			return serviceNumber.replaceFirst("07", "2517");
		}
		return serviceNumber;
	}

	public static String randomStringGenerator(int length) {
		if (length < 1) {
			throw new IllegalArgumentException("Length must be at least 1");
		}

		// Pre-calculate character array for efficient lookup
		char[] allowedChars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();

		SecureRandom random = new SecureRandom();
		StringBuilder sb = new StringBuilder(length);

		// Generate characters directly using random.nextInt()
		for (int i = 0; i < length; i++) {
			int rndCharIndex = random.nextInt(allowedChars.length);
			sb.append(allowedChars[rndCharIndex]);
		}

		return sb.toString();
	}

}
