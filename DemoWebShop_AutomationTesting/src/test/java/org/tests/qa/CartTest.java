package org.tests.qa;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.base.qa.BaseTest;
import org.pages.qa.CartPage;
import org.pages.qa.ProductDetailsPage;
import org.pages.qa.SearchProductPage;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.utilities.qa.DB_Utils;
import org.utilities.qa.Log;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;


@Epic("Demo Web Shop Automation (Tricentis)")
@Feature("Cart Module")
public class CartTest extends BaseTest{

	SearchProductPage sp;
	ProductDetailsPage pd; 
	CartPage cp;
	
	public CartTest() throws IOException {
		super();
	}
	
	@BeforeMethod
	public void setUp() {
		
		Log.info("Start Cart Module Test");
		
		begin();
		
		sp = new SearchProductPage(driver);
		pd = new ProductDetailsPage(driver);
		cp = new CartPage(driver);
	}
	
	public String getProdFromDB() throws SQLException {
		
		DB_Utils.connectionDb();
		
		ResultSet rs = DB_Utils.executeQuery("select product_name from product_data where product_name = 'Computing and Internet'");
		
		String pn = " ";
		
		if(rs.next()) {
			
			pn = rs.getString("product_name");
		}else {
			Assert.fail("No product found in the product_data table");
		}
		
		DB_Utils.closeDb();
		
		return pn;
	}
	
	public void addProdToCart() throws SQLException {
		
		String pm = getProdFromDB();
		
		Log.info("Product is fetched from the database :"+pm);
		
		sp.searchProd(pm);
		
		pd.openProd(pm);
		
		pd.addProdToCart();
		
		Assert.assertTrue(pd.successMsgDisplayed());
		
		Log.info("The product is added to cart successfully");
		
		driver.navigate().to(prop.getProperty("url")+"cart");   //the cart page 
		
	}
	
	
	@Test(priority = 1)
	@Story("As a User, I want to add product to the cart so that I can purchase that produc")
	@Description("Add a product to the cart and verify cart count ")
	@Severity(SeverityLevel.CRITICAL)
	
	public void ATC_006_Add_product_to_the_cart() throws SQLException {
		
		Log.info("Executing ATC_006 - Add a product to the Cart");
		
		addProdToCart();
		
		Assert.assertTrue(cp.isProdThereInTheCart());
		
		String cartcnt = cp.cartCount();
		
		Log.info("The cart count after adding the product :"+cartcnt);
		
		Assert.assertEquals(cartcnt.trim(), "(1)");
		
		Log.info("Test ATC_006 passed successfully");
	}
	
	
	@Test(priority = 2)
	@Story("As a User, I want to update cart quantity so that product totals get updated")
	@Description("Update cart item quantity and verify updated total")
	@Severity(SeverityLevel.CRITICAL)
	
	public void ATC_007_Update_cart_item_quantity() throws SQLException {
		
		Log.info("Executing ATC_007 - Update Cart Quantity and verify updated total");
		
		addProdToCart();
		
		String beforeTtl = cp.prodSubTotal();
		
		Log.info("Subtotal before updating the product quantity :" +beforeTtl);
		
		cp.updateProdQty("4");
		
		String afterTtl = cp.prodSubTotal();
		
		Log.info("Subtotal after updating the product quantity :" +afterTtl);
		
		Assert.assertTrue(cp.isProdThereInTheCart());
		
		Assert.assertNotEquals(beforeTtl, afterTtl);
		
		Log.info("Test STC_007 passed successfully");
	}
	
	@Test(priority = 3)
	@Story("As a User, I want to remove item from the cart so that cart becomes empty")
	@Description("Remove item from cart and verify cart is empty")
	@Severity(SeverityLevel.CRITICAL)
	
	public void ATC_008_Remove_item_from_cart() throws SQLException {
		
		Log.info("Executing ATC_008 - Remove item from the cart and verify cart is empty");
		
		addProdToCart();
		
		cp.removeProdFromCart();
		
		Assert.assertTrue(cp.ifCartIsEmpty());
		
		Log.info("Test ATC_008 passed successfullly");
		
	}

	
	@AfterMethod
	public void tearDownT(ITestResult res) {
		
		Log.info("Cart Test Ended");
		
		tearDown(res);
	}
}
