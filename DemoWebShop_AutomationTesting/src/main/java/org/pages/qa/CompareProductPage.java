package org.pages.qa;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.utilities.qa.Element_Utils;
import org.utilities.qa.Log;

public class CompareProductPage {

	WebDriver driver;

	public CompareProductPage(WebDriver driver) {
		this.driver = driver;
	}

	By compareProdText = By.xpath("//div[@class='page-title']/h1");
	By compareAllProds = By.xpath("//table[@class='compare-products-table']");

	public boolean isCompareProdsPageDisplayed() {

		Log.info("Checking for the compare products page");
		return Element_Utils.isDisplayed(driver, compareProdText) 
				&& Element_Utils.isDisplayed(driver, compareAllProds);
	}
	
	public boolean isCompareTableDisplayed() {

        Log.info("Checking if compare products table is displayed");
        return Element_Utils.isDisplayed(driver, compareAllProds);
    }

	public String comparePgTitle() {

		Log.info("Getting the page title for the compare product page");
		return Element_Utils.getText(driver, compareProdText);
	}

}
