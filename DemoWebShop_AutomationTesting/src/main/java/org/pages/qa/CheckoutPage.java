package org.pages.qa;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.utilities.qa.Element_Utils;
import org.utilities.qa.Log;

public class CheckoutPage {

	WebDriver driver;

	public CheckoutPage(WebDriver driver) {
		this.driver = driver;
	}

	By fname = By.xpath("//input[@id='BillingNewAddress_FirstName']");
	By lname = By.xpath("//input[@id='BillingNewAddress_LastName']");
	By email = By.xpath("//input[@name='BillingNewAddress.Email']");

	By countryDropDown = By.xpath("//select[@id='BillingNewAddress_CountryId']");
	By stateDropDown = By.xpath("//select[@id='BillingNewAddress_StateProvinceId']");

	By city = By.xpath("//input[@id='BillingNewAddress_City']");
	By addressLine1 = By.xpath("//input[@id='BillingNewAddress_Address1']");

	By zipCode = By.xpath("//input[@id='BillingNewAddress_ZipPostalCode']");
	By phoneNo = By.xpath("//input[@id='BillingNewAddress_PhoneNumber']");

	By billingAddDd = By.xpath("//select[@id='billing-address-select']");

	By ctnBtnAddress = By.xpath("//input[@onclick='Billing.save()']");

	By shippingAddressCtn = By.cssSelector("input[onclick='Shipping.save()']");
	By shippingMethodCnt = By.cssSelector("input[onclick='ShippingMethod.save()']");
	By paymentMethodCnt = By.cssSelector("input[onclick='PaymentMethod.save()']");
	By paymentInfoCnt = By.cssSelector("input[onclick='PaymentInfo.save()']");
	By confirmBtn = By.cssSelector("input[value='Confirm']");

	// shipping methods
	By ground = By.xpath("//label[@for='shippingoption_0']");
	By nextDayAir = By.xpath("//label[@for='shippingoption_1']");
	By secondDayAir = By.xpath("//label[@for='shippingoption_2']");

	// payment methods
	By cod = By.xpath("//label[@for='paymentmethod_0']");
	By checkMoney = By.xpath("//label[@for='paymentmethod_1']");
	By creditCard = By.xpath("//label[@for='paymentmethod_2']");
	By purchaseOrder = By.xpath("//label[@for='paymentmethod_3']");

	By orderSuccessMsg = By.xpath("//*[contains(text(),'Your order has been successfully processed!')]");

	
	
	public boolean billinAddDdPresent() {

		try {
			return driver.findElements(billingAddDd).size() > 0;
		} catch (Exception e) {
			return false;
		}
	}

	
	public void fillBillingAdd(String fn, String ln, String mail, String country, String cityName, String add,
			String zip, String ph) {

		if(billinAddDdPresent()) {
			
			Log.info("The existing billing address was found. Selecting existing billing address");
			
			Element_Utils.selectDropdownByIndex(driver, billingAddDd, 0);
			
			Log.info("Clicking the billilng address continue button");
			Element_Utils.clickElement(driver, ctnBtnAddress);
		}
		
		else {
		Log.info("No existing billing address found. Filling new billing address");

		Element_Utils.enterText(driver, fname, fn);
		Element_Utils.enterText(driver, lname, ln);
		Element_Utils.enterText(driver, email, mail);
		Element_Utils.selectDropdownByText(driver, countryDropDown, country);
		Element_Utils.enterText(driver, city, cityName);
		Element_Utils.enterText(driver, addressLine1, add);
		Element_Utils.enterText(driver, zipCode, zip);
		Element_Utils.enterText(driver, phoneNo, ph);

		Log.info("Clicking the continue button after filling billing details.");
		Element_Utils.clickElement(driver, ctnBtnAddress);
		}
	}

	public void contShippingAdd() {
		Log.info("Continuing with the selected shipping address");
		Element_Utils.clickElement(driver, shippingAddressCtn);
	}

	public void selectNextAirDayShipping() {
		Log.info("Selecting the Next Air Day Shipping Method");
		Element_Utils.selectRadioButton(driver, nextDayAir);

		Log.info("Clicking on the continue button after selecting the shipping method");
		Element_Utils.clickElement(driver, shippingMethodCnt);
	}

	public void selectCODPaymentMethod() {

		Log.info("Selecting the COD payment method");
		Element_Utils.selectRadioButton(driver, cod);

		Log.info("Clicking on the continue button after selecting the payment method");
		Element_Utils.clickElement(driver, paymentMethodCnt);

	}

	public void continuePaymentInformation() {
		Log.info("Continuing the payment information step");
		Element_Utils.clickElement(driver, paymentInfoCnt);
	}

	public void cnfmOrder() {

		Log.info("Click the confirm order button");
		Element_Utils.clickElement(driver, confirmBtn);
	}

	public void completeCODChkoutFlow() {
		contShippingAdd();
		selectNextAirDayShipping();
		selectCODPaymentMethod();
		continuePaymentInformation();
		cnfmOrder();

	}

	public boolean successMsgAfterChkout() {

		Log.info("Checking for the order success message");
		return Element_Utils.isDisplayed(driver, orderSuccessMsg);
	}

}
