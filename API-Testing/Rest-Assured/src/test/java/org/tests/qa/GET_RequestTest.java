package org.tests.qa;

import org.base.qa.BaseTest;
import org.endpoints.qa.GET_Request;
import org.listeners.qa.TestListeners;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.utils.qa.TokenManager;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import io.restassured.response.Response;

@Listeners(TestListeners.class)
public class GET_RequestTest {

	String token = "";

	@BeforeMethod
	public void setUp() {

		BaseTest.initializeURL(BaseTest.base_url);
	}

	// 4. List all products with filters
	@Test(priority = 1)
	public void listAllProd() {

		Response res = GET_Request.allProd();

		res.then().statusCode(200);

		assertThat(res.jsonPath().getList("products").size(), greaterThan(0));
		assertThat(res.jsonPath().getInt("limit"), equalTo(10));
		assertThat(res.jsonPath().getInt("skip"), equalTo(0));
	}

	// 5. Get Get product by ID
	@Test(priority = 2)
	public void getProductById() {

		Response response = GET_Request.productByID(1);

		response.then().statusCode(200);

		assertThat(response.jsonPath().getInt("id"), equalTo(1));
		assertThat(response.jsonPath().getString("title"), notNullValue());
		assertThat(response.jsonPath().getFloat("price"), greaterThan(0.0f));
	}

	// 8. Get current user cart
	@Test(priority = 4)
	public void getCurrentUserCart() {

		Response response = GET_Request.currentUserCart(5);

		response.then().statusCode(200);

		assertThat(response.jsonPath().getInt("carts[0].userId"), equalTo(5));
		assertThat(response.jsonPath().getList("carts").size(), greaterThan(0));
	}

	// 18. Get current user profile
	@Test(priority = 5)
	public void getCurrentUserProfile() {

		String token = TokenManager.getToken();
		System.out.println("Token in GET Profile: " + token);

		Response response = GET_Request.currentUserProfile(token);

		response.then().statusCode(200);

		assertThat(response.jsonPath().getInt("id"), equalTo(1));
		assertThat(response.jsonPath().getString("email"), notNullValue());
		assertThat(response.jsonPath().getString("username"), equalTo("emilys"));
	}

	// 12. Get wishlist
	@Test(priority = 6)
	public void getWishlist() {

		BaseTest.initializeURL(BaseTest.mock_url);

		Response res = GET_Request.getWl();
		System.out.println(res.asPrettyString());

		res.then().statusCode(200);

		assertThat(res.jsonPath().getInt("wishlistId"), equalTo(101));
		assertThat(res.jsonPath().getInt("userId"), equalTo(1));
		assertThat(res.jsonPath().getList("wishlistItems").size(), equalTo(4));
		assertThat(res.jsonPath().getInt("totalItems"), equalTo(4));
	}

	// 14. Get all orders for user
	@Test(priority = 7)
	public void getAllOrders() {

		BaseTest.initializeURL(BaseTest.mock_url);

		Response res = GET_Request.getOrders();
		System.out.println(res.asPrettyString());

		res.then().statusCode(200);

		assertThat(res.jsonPath().getInt("userId"), equalTo(1));
		assertThat(res.jsonPath().getList("orders").size(), equalTo(4));
		assertThat(res.jsonPath().getInt("totalOrders"), equalTo(4));
		assertThat(res.jsonPath().getInt("orders[0].orderId"), equalTo(1001));
		assertThat(res.jsonPath().getString("orders[0].orderStatus"), equalTo("Delivered"));
	}

	// 15. Get order details by order ID - Mock API
	@Test(priority = 8)
	public void getOrderDetails() {

		BaseTest.initializeURL(BaseTest.mock_url);

		Response res = GET_Request.getOrderDet(1001);

		System.out.println(res.asPrettyString());

		res.then().statusCode(200);

		assertThat(res.jsonPath().getInt("orderId"), equalTo(1001));
		assertThat(res.jsonPath().getInt("userId"), equalTo(1));
		assertThat(res.jsonPath().getString("orderStatus"), equalTo("Delivered"));
		assertThat(res.jsonPath().getString("paymentMode"), equalTo("UPI"));
		assertThat(res.jsonPath().getList("items").size(), greaterThan(0));
	}
}
