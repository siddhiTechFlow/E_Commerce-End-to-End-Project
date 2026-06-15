package org.tests.qa;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.base.qa.BaseTest;
import org.pages.qa.HomePage;
import org.pages.qa.LoginPage;
import org.pages.qa.MyAccountPage;
import org.pages.qa.OrderPage;
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
@Feature("Orders Page Module")
public class OrderTest extends BaseTest {

	HomePage hm;
	LoginPage lp;
	MyAccountPage ma;
	OrderPage op;

	public OrderTest() throws IOException {
		super();
	}

	@BeforeMethod
	public void setUp() {

		Log.info("Test started for Order module");

		begin();

		hm = new HomePage(driver);
		lp = new LoginPage(driver);
		ma = new MyAccountPage(driver);
		op = new OrderPage(driver);
	}

	public void loginToApp() throws SQLException {

		DB_Utils.connectionDb();

		ResultSet rs = DB_Utils.executeQuery("select * from login_data where expected_result='Valid'");

		if (rs.next()) {

			hm.clkLogin();

			String mail = rs.getString("email");
			String pass = rs.getString("password");

			lp.login(mail, pass);

			Assert.assertTrue(hm.isLogoutLinkDisplayed(), "Login was not successful");

			Log.info("Login completed succesfully");
		} else {
			Assert.fail("No valid login data found in login_data table");
		}

		DB_Utils.closeDb();
	}

	@Test(priority = 1)
	@Story("As a logged-in User, I want to view my orders")
	@Description("View order history and verify order details")
	@Severity(SeverityLevel.NORMAL)

	public void ATC_013_Verify_order_history() throws SQLException {

		Log.info("Executing ATC_013 - View order history and verify order details");

		loginToApp();

		ma.openMyAcc();

		ma.linkForOrdersSetion();

		Assert.assertTrue(op.orderHistoryDisplayed(), "Order history is not displayed");

		Assert.assertTrue(op.isAnyOrderPresent(), "No order is present");

		op.openOrderDetailsPage();
		Assert.assertTrue(op.orderDetailsPgDisplay(), "Order detail page is not displayed");

		Assert.assertTrue(op.orderNo(), "Order no is not displayed");

		Log.info("Test ATC_013 passed successfully");
	}

	@Test(priority = 2)
	@Story("As a User, I want to verify orders Page navigation")
	@Description("Verify order page navigation and order visibility")
	@Severity(SeverityLevel.NORMAL)

	public void ATC_014_Verify_order_page_navigation() throws SQLException {

		Log.info("Executing ATC_014 - Verify order page navigation and order visibility");

		loginToApp();

		ma.openMyAcc();
		ma.linkForOrdersSetion();

		Assert.assertTrue(op.orderHistoryDisplayed(), "Orders page is not displayed");

		Assert.assertTrue(op.isAnyOrderPresent(), "Oders list is empty");

		Log.info("Test ATC_014 passed successfully");

	}

	@AfterMethod
	public void tearDownT(ITestResult res) {

		Log.info("Order page test ended");

		tearDown(res);
	}
}
