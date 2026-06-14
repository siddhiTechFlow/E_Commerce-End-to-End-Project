package org.utils.qa;

public class TokenManager {

	private static String token;

	public static void setToken(String accessToken) {

		token = accessToken;
	}

	public static String getToken() {
		
		return token;
	}
}
