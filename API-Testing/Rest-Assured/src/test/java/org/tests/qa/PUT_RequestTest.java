package org.tests.qa;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.base.qa.BaseTest;
import org.endpoints.qa.PUT_Request;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

import io.restassured.response.Response;

public class PUT_RequestTest {

    @BeforeMethod
    public void setUp() {

        BaseTest.initializeURL(BaseTest.base_url);
    }
    
  //9. Update cart item quantity
    @Test(priority = 1)
    public void updateCartQty() {

        int productId = 113;
        int updateQty = 18;

       
        Map<String, Object> product = new HashMap<>();
        product.put("id", productId);
        product.put("quantity", updateQty);

      
        Map<String, Object> body = new HashMap<>();
        body.put("merge", true);
        body.put("products", new Object[] { product });

        Response res = PUT_Request.updateCart(113, body);

        System.out.println(res.asPrettyString());

        res.then().statusCode(200);

        
        assertThat(res.jsonPath().getInt("id"), equalTo(113));

        
        assertThat(res.jsonPath().getList("products").size(), greaterThan(0));

     
        List<Map<String, Object>> products = res.jsonPath().getList("products");

        boolean productFound = false;

        for (Map<String, Object> item : products) {

            if (((Integer) item.get("id")).intValue() == productId) {

                assertThat((Integer) item.get("quantity"), equalTo(updateQty));
                productFound = true;
                break;
            }
        }

       
        assertThat(productFound, equalTo(true));
    }
    
  //19. Update user profile Mock server pm
    @Test(priority = 2)
    public void updateUserProfile() {

        BaseTest.initializeURL(BaseTest.base_url);

        Map<String, Object> body = new HashMap<String, Object>();

        body.put("firstName", "Siddhi");
        body.put("lastName", "More");
        body.put("email", "siddhim@gmail.com");
        body.put("age", 21);

        Response res = PUT_Request.updateUserPf(body);
        System.out.println(res.asPrettyString());

        res.then().statusCode(200);

        assertThat(res.jsonPath().getString("firstName"), equalTo("Siddhi"));
        assertThat(res.jsonPath().getString("lastName"), equalTo("More"));
    }
}