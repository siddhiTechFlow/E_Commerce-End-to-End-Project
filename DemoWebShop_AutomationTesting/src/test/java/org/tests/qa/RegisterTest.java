package org.tests.qa;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import org.base.qa.BaseTest;
import org.pages.qa.HomePage;
import org.pages.qa.RegisterPage;
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
@Feature("Registration Module")

public class RegisterTest extends BaseTest{

	HomePage hm;
	RegisterPage rp;
	
	
	public RegisterTest() throws IOException {
		super();
	}
	
	@BeforeMethod
	public void setUp() {
		
		Log.info("Start Test for Registration");
		
		begin();
		
		hm = new HomePage(driver);
		rp = new RegisterPage(driver);
		
		DB_Utils.connectionDb();
	}
	
	
	@Test(priority = 1)
	@Story("As a User, I want to register with valid credentials so I can create an account")
	@Description("Verify user registration with valid data using PostgreSQL")
	@Severity(SeverityLevel.CRITICAL)
	public void ATC_001_Verify_user_registration_with_valid_data() throws SQLException {
		
		
		Log.info("Executing ATC_001 : Verify user registration with valid data");
		
		hm.clkRegister();
		
		ResultSet rs = DB_Utils.executeQuery("select * from registration_data where id = 1");
		
		if(rs.next()) {
			
			String fn = rs.getString("first_name");
			String ln = rs.getString("last_name");
			
			
			//for dynamic email do the following
			String mail = "siddhi" + UUID.randomUUID().toString().substring(0,8 )+"@gmail.com";
			
			
			//String mail = rs.getString("email");
			
			String pswd = rs.getString("password");
			
			rp.register(fn, ln, mail, pswd);
			
			String actualMsg = rp.successMsg();
			
			Log.info("Success Registration message :"+actualMsg);
			
			Assert.assertTrue( actualMsg.contains("Your registration completed"),"Succcess message for registration is not displayed");
		}
		else {
			Assert.fail("Data not found in PostgreSQL");
		}
		
	}

	
	@Test(priority = 2)
	@Story("As a User, I want to see validation message when mandatory fields are left empty")
	@Description("Verify form validation on registration mandatory fields are empty")
	@Severity(SeverityLevel.MINOR)
	public void ATC_015_Verify_user_registration_with_empty_fields() {
		
		Log.info("Executing ATC_015 : Verify user registration with empty fields");
		
		hm.clkRegister();
		rp.clkRegBtnWithoutData();
		
		Assert.assertTrue(rp.validationMsgIsDisplayed());
	}
	
	@AfterMethod
	public void tearDownT(ITestResult res) {
		
		Log.info("End the test");
		DB_Utils.closeDb();
		
		tearDown(res);
	
	}
}
