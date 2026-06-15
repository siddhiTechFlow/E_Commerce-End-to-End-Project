package org.pages.qa;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.utilities.qa.Element_Utils;
import org.utilities.qa.Log;

public class SearchProductPage {

	WebDriver driver;

	public SearchProductPage(WebDriver driver) {
		this.driver = driver;
	}

	By searchBox = By.xpath("//input[@value='Search store']");
	By searchBtn = By.cssSelector("input[value='Search']");

	By searchResProd = By.cssSelector(".product-title a");
	
	By invalidProductSearch = By.xpath("//*[contains(text(),'No products were found')]");

	// search the product using the keywords
	public void searchProd(String prodName) {

		Log.info("Searching for the product :" + prodName);
		Element_Utils.enterText(driver, searchBox, prodName);
		Element_Utils.clickElement(driver, searchBtn);
	}

	// valid product search result
	public boolean isProdDisplayed() {

		Log.info("Verifying product display");
		return Element_Utils.isDisplayed(driver, searchResProd);
	}

	// verify invalid product search validation
	public boolean invalidProd() {

		Log.info("Verifying invalid product search message");
		return Element_Utils.isDisplayed(driver, invalidProductSearch);
	}

	// get invalid product search msg
	public String getInvalidProdMsg() {

		Log.info("Validating invalid product search message");
		return Element_Utils.getText(driver, invalidProductSearch);
	}

	

}
