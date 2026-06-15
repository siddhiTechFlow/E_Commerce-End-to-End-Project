package org.pages.qa;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.utilities.qa.Element_Utils;
import org.utilities.qa.Log;

public class MyAccountPage {

	WebDriver driver;

	public MyAccountPage(WebDriver driver) {
		this.driver = driver;
	}

	By accLink = By.xpath("//a[text()='My account']");
	By accTitle = By.xpath("//div[@class='page-title']");
	By orderLink = By.xpath("//div[@class='column my-account']//a[@href='/customer/orders']");

	public void openMyAcc() {

		Log.info("Opening My Account");
		Element_Utils.clickElement(driver, accLink);
	}

	
	public boolean accDisplayed() {

		Log.info("Checking if My Account is open");
		return Element_Utils.isDisplayed(driver, accTitle);
	}

	public String seeTitle() {

		Log.info("My Account page title");
		return Element_Utils.getText(driver, accTitle);

	}
	

	public boolean isOrderlnkDisplayed() {
		
		Log.info("Checking if the link for orders is displayed");
		return Element_Utils.isDisplayed(driver, orderLink);
		
	}

	public void linkForOrdersSetion() {

		Log.info("Opening the orders section");
		Element_Utils.clickElement(driver, orderLink);
	}
}
