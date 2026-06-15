package org.tests.qa;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.base.qa.BaseTest;
import org.pages.qa.CartPage;
import org.pages.qa.CheckoutPage;
import org.pages.qa.HomePage;
import org.pages.qa.LoginPage;
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
@Feature("Checkout Module")
public class CheckoutTest extends BaseTest {

	HomePage hm;
	LoginPage lp;
	SearchProductPage sp;
	ProductDetailsPage pd;
	CartPage cp;
	CheckoutPage chkoutpg;

	public CheckoutTest() throws IOException {
		super();
	}

	@BeforeMethod
	public void setUp() {

		Log.info("Test started for Checkout Module");

		begin();

		hm = new HomePage(driver);
		lp = new LoginPage(driver);
		sp = new SearchProductPage(driver);
		pd = new ProductDetailsPage(driver);
		cp = new CartPage(driver);
		chkoutpg = new CheckoutPage(driver);
	}

	public void loginToApp() throws SQLException {

		DB_Utils.connectionDb();

		ResultSet rs = DB_Utils.executeQuery("select * from login_data where id = 3");

		if (rs.next()) {

			hm.clkLogin();
			lp.login(rs.getString("email"), rs.getString("password"));
			Assert.assertTrue(hm.isLogoutLinkDisplayed());

		} else {

			Assert.fail("No valid login data was found in the login_data table");
		}
	}

	public String checkoutProdFromDB() throws SQLException {

		DB_Utils.connectionDb();

		ResultSet rs = DB_Utils
				.executeQuery("select product_name from product_data where product_name = 'Computing and Internet'");

		String pn = "";

		if (rs.next()) {

			pn = rs.getString("product_name");
		} else {

			Assert.fail("No checkout product found in the product_data table");
		}

		DB_Utils.closeDb();

		return pn;
	}

	public String[] checkoutAddFromDB() throws SQLException {

		DB_Utils.connectionDb();

		ResultSet rs = DB_Utils.executeQuery("select * from checkout_data limit 1");

		String[] data = new String[8];

		if (rs.next()) {

			data[0] = rs.getString("first_name");
			data[1] = rs.getString("last_name");
			data[2] = rs.getString("email");
			data[3] = rs.getString("country");
			data[4] = rs.getString("city");
			data[5] = rs.getString("address1");
			data[6] = rs.getString("zip_code");
			data[7] = rs.getString("phone");
		}else {
			
			Assert.fail("No checkout data was found in the checkout_data table");
		}
		
		DB_Utils.closeDb();
		
		return data;
	}
	
	public void addProdToCartForCheckout() throws SQLException {
		
	
		String pn = checkoutProdFromDB();
		
		Log.info("Checkout product fetched from the database :" +pn);
		
		sp.searchProd(pn);
		pd.openProd(pn);
		
		pd.addProdToCart();
		
		Assert.assertTrue(pd.successMsgDisplayed());
		
		hm.cart();
		
		Assert.assertTrue(cp.isProdThereInTheCart());
		
		cp.checkoutProcess();
		
	}
	
	@Test
	@Story("As a User, I want to complete checkout using COD payment")
	@Description("Complete full checkout flow (COD payment) ")
	@Severity(SeverityLevel.CRITICAL)
	
	public void ATC_010_Complete_full_checkout() throws SQLException {
		
		Log.info("Executing ATC_010 - Complete full checkout flow with COD payment");
		
		loginToApp();
		
		addProdToCartForCheckout();
		
		String[] chkoutData = checkoutAddFromDB();
		
		chkoutpg.fillBillingAdd(
				chkoutData[0],
				chkoutData[1],
				chkoutData[2],
				chkoutData[3],
				chkoutData[4],
				chkoutData[5],
				chkoutData[6],
				chkoutData[7]);
		
		chkoutpg.completeCODChkoutFlow();
		
		Assert.assertTrue(chkoutpg.successMsgAfterChkout(), "Order success message is not displayed");
		
		Log.info("Test ACT_010 passed successfully");
	}
	
	@AfterMethod
	public void tearDownT(ITestResult res) {
		
		Log.info("Checkout test ended");
		
		tearDown(res);
	}
}
