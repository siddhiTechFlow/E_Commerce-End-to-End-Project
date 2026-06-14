package org.endpoints.qa;

import static io.restassured.RestAssured.given;


import io.restassured.response.Response;

public class DELETE_Request {

	
	//10. Remove item from cart
	public static Response deleteItemFromCart(int cartId) {

		return given().when()
				.delete("/carts/" + cartId);

	}
	
	//16. Cancel an order - Mock server postman
	public static Response cancelOrder(int orderId) {

	    return given()
	            .when()
	            .delete("/orders/" + orderId + "/cancel");
	}
}