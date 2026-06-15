package org.tests.qa;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.base.qa.BaseTest;
import org.pages.qa.CompareProductPage;
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
@Feature("Compare Product Page Module")
public class CompareProductTest extends BaseTest {

	SearchProductPage sp;
	ProductDetailsPage pd;
	CompareProductPage cp;

	public CompareProductTest() throws IOException {
		super();
	}

	@BeforeMethod
	public void setUp() {

		Log.info("Start test for Compare Product Page");

		begin();

		sp = new SearchProductPage(driver);
		pd = new ProductDetailsPage(driver);
		cp = new CompareProductPage(driver);

	}

	public ArrayList<String> compareProdFromDB() throws SQLException{
		
		ArrayList<String> prod = new ArrayList<>();
		
		DB_Utils.connectionDb();
		
		ResultSet rs = DB_Utils.executeQuery("select product_name from product_data where id in (6,7)");
		
		while(rs.next()) {
			prod.add(rs.getString("product_name"));
		}
		
		DB_Utils.closeDb();
		
		return prod;
 	}

	
	@Test
	@Story("As a User, I want to compare products so that I can view product detials")
	@Description("Add product to compare list and verify compare products page is displayed")
	@Severity(SeverityLevel.NORMAL)
	
	public void ATC_019_Verify_compare_product_function() throws SQLException {
		
		Log.info("Executing ATC_019 - Verify compare product functionality");
		
		ArrayList<String> prod = compareProdFromDB();
		
		String prod1 = prod.get(0);
		String prod2 = prod.get(1);
		
		Log.info("Product 1 :"+prod1);
		Log.info("Product 2 :"+prod2);
		
		
		sp.searchProd(prod1);
		pd.openProd(prod1);
		pd.addProdToCompareList();
		Log.info(prod1 + " was added to compare list");
		
		driver.navigate().back();
		driver.navigate().back();
		
		sp.searchProd(prod2);
		pd.openProd(prod2);
		pd.addProdToCompareList();
		Log.info(prod2 +" was added to compare list");
		
		
		driver.navigate().to(prop.getProperty("url")+"compareproducts");
		
		Assert.assertTrue(cp.isCompareProdsPageDisplayed(), "Compare product page is not displayed");
		
		Assert.assertEquals(cp.comparePgTitle().trim(), "Compare products");
		
		Log.info("Compare products verified successfully");
	}
	
	@AfterMethod
	public void tearDownT(ITestResult res) {
		
		Log.info("Compare Products test ended");
		
		tearDown(res);
	}
}
