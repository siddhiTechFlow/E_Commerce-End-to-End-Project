package org.tests.qa;

import java.io.IOException;

import org.base.qa.BaseTest;
import org.pages.qa.CartPage;
import org.pages.qa.CouponPage;
import org.pages.qa.HomePage;
import org.pages.qa.ProductDetailsPage;
import org.pages.qa.SearchProductPage;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.SkipException;
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
@Feature("Coupon Module")
public class CouponTest extends BaseTest {

	SearchProductPage sp;
	ProductDetailsPage pd;
	HomePage hm;
	CartPage cp;
	CouponPage coup;

	public CouponTest() throws IOException {
		super();

	}

	@BeforeMethod
	public void setUp() {

		Log.info("Test started for Coupon Module");

		begin();

		sp = new SearchProductPage(driver);
		pd = new ProductDetailsPage(driver);
		hm = new HomePage(driver);
		cp = new CartPage(driver);
		coup = new CouponPage(driver);
	}

	public void addProdToCart() {

		String pn = "Blue Jeans";

		Log.info("Adding product to cart : " + pn);

		sp.searchProd(pn);

		pd.openProd(pn);

		pd.addProdToCart();

		Assert.assertTrue(pd.successMsgDisplayed(), "Product add to cart message is not displayed");

		hm.cart();

		Assert.assertTrue(cp.isProdThereInTheCart(), "Product is not present in cart");
	}

	@Test(priority = 1)
	@Story("As a User, I want to apply coupon code")
	@Description("Blocked because valid coupon code is not available in Demo Web Shop")
	@Severity(SeverityLevel.NORMAL)

	public void ATC_011_Apply_valid_coupon() {

		throw new SkipException("Blocked : Valid coupon code is not available in Demo Web Shop application");
	}

	@Test(priority = 2)
	@Story("As a user, I should see error message for invalid coupon")
	@Description("Apply invalid coupon and verify error message")
	@Severity(SeverityLevel.NORMAL)

	public void ATC_012_Apply_invalid_coupon() {

		Log.info("Executing ATC_012 - Apply invalid coupon and verify error message");

		addProdToCart();

		coup.enterCouponValue("TIRA20BEAUTY");

		Assert.assertTrue(coup.couponMsgDisplay(), "No Message was displayed");

		String amsg = coup.copMsgGet();

		Assert.assertEquals(amsg,"The coupon code you entered couldn't be applied to your order", "The coupon error message is not correct");

		Log.info("Test ATC_012 passed successfully");
	}

	@AfterMethod
	public void tearDownT(ITestResult res) {

		Log.info("Test ended for coupon module");

		tearDown(res);
	}

}
