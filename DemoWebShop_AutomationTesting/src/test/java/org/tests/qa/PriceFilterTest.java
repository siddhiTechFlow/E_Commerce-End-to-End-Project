package org.tests.qa;

import java.io.IOException;

import org.base.qa.BaseTest;
import org.pages.qa.PriceFilterPage;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.utilities.qa.Log;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Epic("Demo Web Shop Automation (Tricentis)")
@Feature("Price Filter Module")
public class PriceFilterTest extends BaseTest {

	PriceFilterPage pf;

	public PriceFilterTest() throws IOException {
		super();

	}

	@BeforeMethod
	public void setUp() {

		Log.info("Test started for price filter module");

		begin();

		pf = new PriceFilterPage(driver);
	}

	@Test
	@Story("As a User, I want to sort products by price")
	@Description("Verify products are sorted Low to High and High to Low")
	@Severity(SeverityLevel.NORMAL)

	public void ATC_005_Verify_prod_sort() {

		Log.info("Executing ATC_005 - Verify product price sorting");

		pf.openApandShCat();

		// low to high sorting

		pf.selectPriceLowToHigh();

		Assert.assertTrue(pf.priceSortByLowToHigh(), "Products are not sorted from low to high");

		Log.info("Products are sorted low to high successfully");

		// high to low sorting

		pf.selectPriceHighToLow();

		Assert.assertTrue(pf.priceSortByHighToLow(), "Products are not sorted from high to low");

		Log.info("test ATC_005 passed successfully");
	}

	@AfterMethod
	public void tearDownT(ITestResult res) {

		Log.info("Price Filter Test Ended");

		tearDown(res);
	}

}
