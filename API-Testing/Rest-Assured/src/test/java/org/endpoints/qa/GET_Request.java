package org.endpoints.qa;
import static io.restassured.RestAssured.*;
import io.restassured.response.Response;

public class GET_Request {

	//4. List all products with filters
	public static Response allProd() {
		
		return given().queryParam("limit", "10")
				.queryParam("skip", "0")
				.when()
				.get("/products");
	}
	
	
	//5. Get product by ID
	public static Response productByID(int prodID) {
		
		return given().pathParam("id", prodID)
				.when()
				.get("/products/{id}");
	}
	
	
	
	//6. List all categories 
	public static Response listAllCat() {
		
		return given().
				when().get("products/category-list");
	}
	
	
	
	//8. Get current user cart 
	public static Response currentUserCart(int userID) {
		
		return given().pathParam("id", userID)
				.when()
				.get("/carts/user/{id}");
	}
	
	// 18. Get current user profile
	public static Response currentUserProfile(String token) {

	    return given()
	            .header("Authorization", "Bearer " + token)
	            .when()
	            .get("/auth/me");
	}
	
	//12. Get wishlist 
	public static Response getWl() {
		
		return given()
				.when()
				.get("/wishlist");
	}
	
	// 14. Get all orders for user
	public static Response getOrders() {

	    return given()
	            .when()
	            .get("/orders");
	}
	
	// 15. Get order details by order ID - Mock API
	public static Response getOrderDet(int orderId) {

	    return given()
	            .when()
	            .get("/orders/" + orderId);
	}
}