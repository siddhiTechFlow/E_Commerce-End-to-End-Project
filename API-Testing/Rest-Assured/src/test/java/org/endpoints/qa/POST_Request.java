package org.endpoints.qa;

import java.util.Map;
import static io.restassured.RestAssured.*;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class POST_Request {

	
	// 1. New user registration - Mock server postman
	 public static Response registerUser(Map<String, Object> body) {
		 return given()
	                .contentType(ContentType.JSON)
	                .body(body)
	                .when()
	                .post("/auth/register");
	    }
	
	//3. Send reset password email - mock server postman
	 public static Response resetPass(Map<String, Object>body) {
		 
		 return given().contentType(ContentType.JSON)
				 .body(body)
				 .when()
				 .post("/auth/forgot-password");
	 }
	
	
	// 2. User Login - returns JWT token
	public static Response userLogin(Map<String, Object>body) {
		
		return given().contentType(ContentType.JSON)
				.body(body).when().post("/auth/login");
	}
	
	// 7. Add item to cart
	public static Response itemToCart(Map<String, Object> body) {

        return given()
                .contentType(ContentType.JSON)
                .body(body)
                .when()
                .post("/carts/add");
    }
	
	//11. Add to wishlist Mock server postman
	
	public static Response addtoWl(Map<String, Object> body) {
		
		return given().contentType(ContentType.JSON)
				.body(body)
				.when()
				.post("/wishlist");
	}
	
	//13. Place an order Mock server postman
	public static Response placeOrder(Map<String, Object> body) {
		
		return given().contentType(ContentType.JSON)
				.body(body)
				.when()
				.post("/orders");
				
	}
	
	//17. Validate a coupon code mock 
	public static Response validCoupon(Map<String, Object> body) {
		
		return given().contentType(ContentType.JSON)
				.body(body)
				.when()
				.post("/coupons/validate");
	}
	
	//20. Add delivery address mock
	public static Response addDeliveryAdd(Map<String, Object> body) {
		
		return given().contentType(ContentType.JSON).header("x-mock-response-name", "20. Add delivery address (POSITIVE)")
				.body(body)
				.when()
				.post("/users/me/addresses");
	}
}
