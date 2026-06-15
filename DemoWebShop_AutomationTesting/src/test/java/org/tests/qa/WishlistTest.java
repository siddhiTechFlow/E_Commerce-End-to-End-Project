package org.tests.qa;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.base.qa.BaseTest;
import org.pages.qa.ProductDetailsPage;
import org.pages.qa.SearchProductPage;
import org.pages.qa.WishlistPage;
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
@Feature("Wishlist Module")
public class WishlistTest extends BaseTest{

	SearchProductPage sp;
	ProductDetailsPage pd;
	WishlistPage wl;
	
	public WishlistTest() throws IOException {
		super();
	
	}
	
	@BeforeMethod
	public void setUp() {
		
		Log.info("Start test for Wishlist module");
		
		begin();
		
		sp = new SearchProductPage(driver);
		pd = new ProductDetailsPage(driver);
		wl = new WishlistPage(driver);
	}
	
	public String wishlistProdFromDB() throws SQLException {
		
		DB_Utils.connectionDb();
		
		ResultSet rs = DB_Utils.executeQuery("select product_name from product_data where product_name='Blue and green Sneaker'");
		
		String pn = " ";
		
		if(rs.next()) {
			
			pn = rs.getString("product_name");
		}else
		{
			Assert.fail("No wishlist product found in the product_data table");
		}
		
		DB_Utils.closeDb();
		
		return pn;
	}
	
	@Test
	@Story("As a User, I want to add product to wishlist so that I can purchase it later")
	@Description("Add product to wishlist and verify wishlist count")
	@Severity(SeverityLevel.NORMAL)
	
	public void ATC_009_Add_product_to_wishlist() throws SQLException {
		
		Log.info("Executing ATC_009 - Add product to wishlist");
		
		String pn = wishlistProdFromDB();
		
		sp.searchProd(pn);
		pd.openProd(pn);
		
		pd.addProdToWishlist();
		
		Assert.assertTrue(pd.successMsgDisplayed());
		
		String wlCount = wl.wishlistCount();
		
		Log.info("Wishlist count after adding product :" +wlCount);
		
		Assert.assertEquals(wlCount.trim(), "(1)");
		
		wl.clkWishlist();
		
		Assert.assertTrue(wl.checkIfTheProdIsThereInTheWl());
		
		Log.info("Test ATC_009 passed successfully for :"+pn);
		
	}

	
	@AfterMethod
	public void tearDownT(ITestResult res) {
		
		Log.info("Wishlist module test ended");
		
		tearDown(res);
	}
}

