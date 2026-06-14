package org.tests.qa;

import java.util.HashMap;
import java.util.Map;

import org.base.qa.BaseTest;
import org.endpoints.qa.POST_Request;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.utils.qa.TokenManager;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import io.restassured.response.Response;

public class POST_RequestTest {

	@BeforeMethod
	public void setUp() {

		BaseTest.initializeURL(BaseTest.base_url);
	}

	// 2. User Login - returns JWT token
	@Test(priority = 1)
	public void login() {

		Map<String, Object> body = new HashMap<String, Object>();

		body.put("username", "emilys");
		body.put("password", "emilyspass");
		body.put("expiresInMins", 14400);

		Response res = POST_Request.userLogin(body);

		System.out.println(res.getStatusCode());
		System.out.println(res.asPrettyString());
		res.then().statusCode(200);

		String token = res.jsonPath().getString("accessToken");
		TokenManager.setToken(token);

		assertThat(token, notNullValue());
		assertThat(res.jsonPath().getString("username"), equalTo("emilys"));
		assertThat(res.jsonPath().getInt("id"), equalTo(1));

	}

	// 7. Add item to cart
	@Test(priority = 2)

	public void addItemtocart() {

		Map<String, Object> prod = new HashMap<String, Object>();

		prod.put("id", 1);
		prod.put("quantity", 2);

		Map<String, Object> body = new HashMap<String, Object>();

		body.put("userId", 5);
		body.put("products", new Object[] { prod });

		Response res = POST_Request.itemToCart(body);

		res.then().statusCode(201);

		assertThat(res.jsonPath().getInt("userId"), equalTo(5));
		assertThat(res.jsonPath().getList("products").size(), greaterThan(0));
		assertThat(res.jsonPath().getInt("products[0].quantity"), equalTo(2));
	}

	// 1. New user registration - Mock server postman
	@Test(priority = 3)
	public void newUserRegister() {

		BaseTest.initializeURL(BaseTest.mock_url);

		Map<String, Object> body = new HashMap<String, Object>();
		body.put("firstName", "Siddhi");
		body.put("lastName", "More");
		body.put("email", "siddhi.more@example.com");
		body.put("password", "Siddhi@123");
		body.put("phone", "9876543210");

		Response res = POST_Request.registerUser(body);
		System.out.println(res.asPrettyString());

		res.then().statusCode(201);

		assertThat(res.jsonPath().getInt("userId"), equalTo(201));
		assertThat(res.jsonPath().getString("email"), equalTo("siddhi.more@example.com"));
		assertThat(res.jsonPath().getString("message"), equalTo("User registered successfully"));
	}

	// 3. Send reset password email - mock server postman
	@Test
	public void resetPassEmail() {

		BaseTest.initializeURL(BaseTest.mock_url);

		Map<String, Object> body = new HashMap<String, Object>();

		body.put("email", "siddhi.more@example.com");

		Response res = POST_Request.resetPass(body);

		System.out.println(res.asPrettyString());

		res.then().statusCode(200);

		assertThat(res.jsonPath().getString("email"), equalTo("siddhi.more@example.com"));
		assertThat(res.jsonPath().getString("message"), equalTo("Password reset link sent successfully"));
	}

	// 11. Add to wishlist
	@Test
	public void addProdToWl() {

		BaseTest.initializeURL(BaseTest.mock_url);

		Map<String, Object> body = new HashMap<>();

		body.put("userId", 1);
		body.put("productId", 1);

		Response res = POST_Request.addtoWl(body);
		System.out.println(res.asPrettyString());

		res.then().statusCode(200);

		assertThat(res.jsonPath().getInt("wishlistId"), equalTo(1));
		assertThat(res.jsonPath().getInt("userId"), equalTo(1));
		assertThat(res.jsonPath().getInt("productId"), equalTo(1));
		assertThat(res.jsonPath().getString("message"), equalTo("Product added to wishlist successfully"));

	}

	// 13. Place an order Mock server postman
	@Test(priority = 6)
	public void placeAnOrder() {

		BaseTest.initializeURL(BaseTest.mock_url);

		Map<String, Object> body = new HashMap<String, Object>();

		body.put("userId", 1);
		body.put("cartId", 1);
		body.put("paymentMode", "UPI");
		body.put("deliveryAddressId", 101);
		body.put("orderReference", "ORD12345");

		Response res = POST_Request.placeOrder(body);

		System.out.println(res.asPrettyString());

		res.then().statusCode(201);

		assertThat(res.jsonPath().getInt("orderId"), equalTo(1001));
		assertThat(res.jsonPath().getInt("userId"), equalTo(1));
		assertThat(res.jsonPath().getString("orderStatus"), equalTo("Placed"));
		assertThat(res.jsonPath().getString("message"), equalTo("Order placed successfully"));
	}

	// 17. Validate a coupon code Mock server postman
	@Test(priority = 7)
	public void validCouponCode() {

		BaseTest.initializeURL(BaseTest.mock_url);

		Map<String, Object> body = new HashMap<String, Object>();

		body.put("couponCode", "SAVE20");

		Response res = POST_Request.validCoupon(body);
		System.out.println(res.asPrettyString());

		res.then().statusCode(200);

		assertThat(res.jsonPath().getString("couponCode"), equalTo("SAVE20"));
		assertThat(res.jsonPath().getBoolean("isValid"), equalTo(true));
		assertThat(res.jsonPath().getInt("discountPercentage"), equalTo(20));
	}

	// 20. Add delivery address mock
	@Test(priority = 7)
	public void deliveryAdd() {

		BaseTest.initializeURL(BaseTest.mock_url);

		Map<String, Object> body = new HashMap<String, Object>();

		body.put("addressLine1", "NNP Goregaon");
		body.put("city", "Mumbai");
		body.put("state", "Maharashtra");
		body.put("pincode", "400065");
		body.put("country", "India");
		body.put("label", "Home");

		Response res = POST_Request.addDeliveryAdd(body);
		System.out.println(res.asPrettyString());

		res.then().statusCode(201);

		assertThat(res.jsonPath().getInt("addressId"), equalTo(101));
		assertThat(res.jsonPath().getString("city"), equalTo("Mumbai"));
		assertThat(res.jsonPath().getString("message"), equalTo("Address added successfully"));
	}
}
