package org.tests.qa;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.base.qa.BaseTest;
import org.pages.qa.HomePage;
import org.pages.qa.LoginPage;
import org.pages.qa.MyAccountPage;
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
@Feature("My Account Module")
public class MyAccountTest extends BaseTest {

	HomePage hm;
	LoginPage lp;
	MyAccountPage myaccpg;

	public MyAccountTest() throws IOException {
		super();
	}

	@BeforeMethod
	public void setUp() {

		Log.info("Test Started for My Account Module");

		begin();

		hm = new HomePage(driver);
		lp = new LoginPage(driver);
		myaccpg = new MyAccountPage(driver);
	}

	public void loginToApp() throws SQLException {

		DB_Utils.connectionDb();

		ResultSet rs = DB_Utils.executeQuery("select * from login_data where expected_result = 'Valid'");

		if (rs.next()) {

			hm.clkLogin();

			String mail = rs.getString("email");
			String pass = rs.getString("password");

			lp.login(mail, pass);

			Assert.assertTrue(hm.isLogoutLinkDisplayed(), "Login was not successful");

			Log.info("Login completed");
		} else {

			Assert.fail("No valid data was found in the login_data table");
		}

		DB_Utils.closeDb();
	}
	
	@Test
	@Story("As a logged-in User, I want to view my account details")
	@Description("Verify My Account page navigation and details")
	@Severity(SeverityLevel.NORMAL)
	
	public void ATC_020_Verify_my_acc_page_navigation() throws SQLException {
		
		Log.info("Executing ATC_020 - Verify My Account page navigation");
		
		loginToApp();
		
		myaccpg.openMyAcc();
		
		Assert.assertTrue(myaccpg.accDisplayed(), "My account page is not displayed");
		
		Assert.assertEquals(myaccpg.seeTitle().trim(), "My account - Customer info");
		
		Assert.assertTrue(myaccpg.isOrderlnkDisplayed(), "Orders link is not displayed");
		
		Log.info("Test ATC_020 passed successfully");
	
	}

	
	@AfterMethod
	public void tearDownT(ITestResult res) {
		
		Log.info("Test ended for My Account");
		
		tearDown(res);
	}
}
