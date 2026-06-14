package org.base.qa;

import io.restassured.RestAssured;

public class BaseTest {

	public static final String base_url = "https://dummyjson.com";
	
	public static final String mock_url = "https://3e7e6d40-6440-4b18-97ac-e920da287a78.mock.pstmn.io";
	
	public static void initializeURL(String url) {
		
		RestAssured.baseURI = url;
	}
	
}
