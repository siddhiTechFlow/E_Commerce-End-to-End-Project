package org.tests.qa;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.base.qa.BaseTest;
import org.pages.qa.HomePage;
import org.pages.qa.LoginPage;
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

@Epic("Demo Web Shop Automation (Tricentis) ")
@Feature("Login Module")

public class LoginTest extends BaseTest {

	HomePage hm;
	LoginPage lg;

	public LoginTest() throws IOException {
		super();
	}

	@BeforeMethod
	public void setUp() {

		Log.info("Start Test for Login");

		begin();

		hm = new HomePage(driver);
		lg = new LoginPage(driver);

		DB_Utils.connectionDb();
	}

	@Test(priority = 1)
	@Story("As a User, I want to login with valid credentials so that I can access my account")
	@Description("Verify login with valid credentials and session creation ")
	@Severity(SeverityLevel.CRITICAL)
	public void ATC_002_Verify_login_with_valid_credentials() throws SQLException {

		Log.info("Executing ATC_002 : Login with valid credentials");

		hm.clkLogin();

		ResultSet rs = DB_Utils.executeQuery("select * from login_data where expected_result = 'Valid'");

		if (rs.next()) {

			String em = rs.getString("email");
			String pass = rs.getString("password");

			lg.login(em, pass);

			Assert.assertTrue(hm.isLogoutLinkDisplayed());
		} else {
			Assert.fail("No login data found in the PostgreSQL login_data table");
		}
	}

	@Test(priority = 2)
	@Story("As a User, when I login with invalid password I should see an error message")
	@Description("Verify login with invalid password shows error message")
	@Severity(SeverityLevel.CRITICAL)
	public void ATC_003_Verify_login_with_invalid_password() throws SQLException {

		Log.info("Executing ATC_003 : Verify login with invalid password");

		hm.clkLogin();

		ResultSet rs = DB_Utils.executeQuery("select * from login_data where expected_result='Invalid'");

		if (rs.next()) {

			String em = rs.getString("email");
			String pass = rs.getString("password");

			lg.login(em, pass);

			Assert.assertTrue(lg.validationIsDisplayed());
		} else {
			Assert.fail("No invalid data found in the PostgreSQL login_data table");
		}
	}

	@Test(priority = 3)
	@Story("As a logged- in User, I want to logout from the application")
	@Description("Verify logout functionality after successful login  ")
	@Severity(SeverityLevel.CRITICAL)

	public void ATC_016_Verify_logout_function() throws SQLException {

		Log.info("Executing ATC_016 : Verify the logout functionality");

		hm.clkLogin();

		ResultSet rs = DB_Utils.executeQuery("select * from login_data where expected_result='Valid'");

		if (rs.next()) {

			String em = rs.getString("email");
			String pass = rs.getString("password");

			lg.login(em, pass);
			
			Assert.assertTrue(hm.isLogoutLinkDisplayed());
			
			hm.clkLogout();
			
			Log.info("Application logout completed");
			
		}else {
			Assert.fail("No data found in the PostgreSQL login_data table");
		}

	}
	
	@AfterMethod
	public void tearDownT(ITestResult rslt) {
		
		Log.info("Login test ended");
		
		DB_Utils.closeDb();
		
		tearDown(rslt);
	}

}
