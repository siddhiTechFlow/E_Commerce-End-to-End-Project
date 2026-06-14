package org.endpoints.qa;

import java.util.Map;

import static io.restassured.RestAssured.*;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class PUT_Request {

	//9. Update cart item quantity
	public static Response updateCart(int cartID, Map<String, Object>body) {
		
		return given().contentType(ContentType.JSON)
				.body(body)
				.when()
				.put("/carts/"+cartID);
	}
	
	//19. Update user profile Mock server pm
	public static Response updateUserPf(Map<String, Object> body) {

	    return given().contentType(ContentType.JSON)
	            .body(body)
	            .when()
	            .put("/users/1");
	}
	
}
