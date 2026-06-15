package org.tests.qa;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.base.qa.BaseTest;
import org.pages.qa.ProductDetailsPage;
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
@Feature("Product Details Module")
public class ProductDetailsTest extends BaseTest{

	SearchProductPage sp;
	ProductDetailsPage pd;
	
	
	public ProductDetailsTest() throws IOException {
		super();
	}

	@BeforeMethod
	public void setUp() {
		
		Log.info("Start Test for Product Details");
	
		begin();
	
		sp = new SearchProductPage(driver);
		pd = new ProductDetailsPage(driver);
	}
	
	@DataProvider(name = "productDetails")
	public Object[][] productDetails() throws SQLException{
		
		ArrayList<Object[]> data = new ArrayList<Object[]>();
		
		DB_Utils.connectionDb();
		
		ResultSet rs = DB_Utils.executeQuery("select product_name from product_data");
		
		while(rs.next()) {
			
			data.add(new Object[] {rs.getString("product_name")});
		}
		
		DB_Utils.closeDb();
		
		return data.toArray(new Object[0][0]);
	}
	
	@Test(dataProvider = "productDetails")
	@Story("As a User, I want to open product details page so that I can view product information")
	@Description("Verify product details page opens correctly when the product is fetched from the database")
	@Severity(SeverityLevel.NORMAL)
	
	public void ATC_018_Verify_product_details_page(String prodName) {
		
		Log.info("Executing ATC_018 - Verify the product :" +prodName);
		
		//searching for the product
		sp.searchProd(prodName);
		
		//open the product details page
		pd.openProd(prodName);
		
		//verify if the page is opened
		Assert.assertTrue(pd.isProdDetPageOpen(), "The product details page was not opened");
		
		//verify the product title
		String actualTitle = pd.getProdTitleText();
		
		Assert.assertEquals(actualTitle.trim(), prodName.trim(), "Product title is incorrect");
		
		Log.info("The product details were successfully verified for :"+prodName);
	}
	
	@AfterMethod
	public void tearDownT(ITestResult rst)
	{
		
		Log.info("Product Details Test Ended");
		
		tearDown(rst);	
	}
}
