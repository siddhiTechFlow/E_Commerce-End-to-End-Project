package org.tests.qa;

import org.base.qa.BaseTest;
import org.endpoints.qa.DELETE_Request;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;


import io.restassured.response.Response;

public class DELETE_RequestTest {

	  @BeforeMethod
	    public void setUp() {

	        BaseTest.initializeURL(BaseTest.base_url);
	    }
	
	//10. Remove item from cart
	  @Test(priority = 1)
	    public void deleteCart() {

	        Response res = DELETE_Request.deleteItemFromCart(1);

	        System.out.println(res.asPrettyString());

	        res.then().statusCode(200);

	        assertThat(res.jsonPath().getInt("id"), equalTo(1));
	        assertThat(res.jsonPath().getBoolean("isDeleted"), equalTo(true));
	    }
	  
	//16. Cancel an order - Mock server postman
	  @Test(priority = 2)
	  public void canceltheOrder() {

	      BaseTest.initializeURL(BaseTest.mock_url);

	      Response res = DELETE_Request.cancelOrder(1001);
	      System.out.println(res.asPrettyString());

	      res.then().statusCode(200);

	      assertThat(res.jsonPath().getInt("orderId"), equalTo(1001));
	      assertThat(res.jsonPath().getString("orderStatus"), equalTo("Cancelled"));
	      assertThat(res.jsonPath().getString("message"),equalTo("Order cancelled successfully"));
	  }
}
