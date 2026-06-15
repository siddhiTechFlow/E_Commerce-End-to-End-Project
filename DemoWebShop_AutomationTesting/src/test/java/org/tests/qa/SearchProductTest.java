package org.tests.qa;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.base.qa.BaseTest;
import org.pages.qa.SearchProductPage;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
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
@Feature("Search Product Module")
public class SearchProductTest extends BaseTest {

	SearchProductPage sp;

	public SearchProductTest() throws IOException {
		super();
	}

	@BeforeMethod
	public void setUp() {

		Log.info("Start Test for Search Product");

		begin();

		sp = new SearchProductPage(driver);
	}

	@DataProvider(name = "validProdSrch")
	public Object[][] validProdSrch() throws SQLException {

		ArrayList<Object[]> valdata = new ArrayList<Object[]>();

		DB_Utils.connectionDb();

		ResultSet rs = DB_Utils.executeQuery("select * from product_search where expected_value='Valid'");

		while (rs.next()) {

			valdata.add(new Object[] { rs.getString("product_name") });

		}
		
		DB_Utils.closeDb();
		return valdata.toArray(new Object[0][0]);
	}

	@DataProvider(name = "invalidProdSrch")
	public Object[][] invalidProdSrch() throws SQLException {

		ArrayList<Object[]> invdata = new ArrayList<Object[]>();

		DB_Utils.connectionDb();

		ResultSet rs = DB_Utils.executeQuery("select * from product_search where expected_value = 'Invalid'");

		while (rs.next()) {

			invdata.add(new Object[] { rs.getString("product_name") });
		}
		DB_Utils.closeDb();

		return invdata.toArray(new Object[0][0]);
	}

	@Test(priority = 1, dataProvider = "validProdSrch")
	@Story("As a User, I want to search a valid product so that I can view matching product")
	@Description("Search for a product by keyword and verify results")
	@Severity(SeverityLevel.CRITICAL)

	public void ATC_004_Verify_product_with_valid_keyword(String pn) {

		Log.info("Executing AATC_004 - Verify product search with valid keyword :" + pn);

		sp.searchProd(pn);

		Assert.assertTrue(sp.isProdDisplayed());

		Log.info("Valid product search verified successfully for :" + pn);

	}

	@Test(priority = 2, dataProvider = "invalidProdSrch")
	@Story("As a User, I should see an error message for invalid product search")
	@Description("Verify product search with invalid keyword shows error message")
	@Severity(SeverityLevel.NORMAL)

	public void ATC_017_Verify_product_search_with_invalid_keyword(String pn) {

		Log.info("Executing ATC_017 - Verify product search with invalid keyword entered :" + pn);

		sp.searchProd(pn);

		Assert.assertTrue(sp.invalidProd());

		String msg = sp.getInvalidProdMsg();

		Log.info("Invalid product search message :" + msg);
	}

	@AfterMethod
	public void tearDownT(ITestResult res) {

		Log.info("Search Product Test Ended");

		tearDown(res);
	}
}
