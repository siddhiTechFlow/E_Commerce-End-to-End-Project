package org.pages.qa;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.utilities.qa.Element_Utils;
import org.utilities.qa.Log;

public class OrderPage {

	WebDriver driver;

	public OrderPage(WebDriver driver) {
		this.driver = driver;
	}


	//By orderSuccessMsg = By.xpath("//*[contains(text(),'Your order has been successfully processed!')]");

	By orderID = By.xpath("//div[@class='order-number']");
	By orderDetailsLink = By.xpath("//input[@value='Details']");

	By orderDetTitle = By.xpath("//div[@class='page-title']/h1");
	
	By orderDataRow = By.cssSelector(".order-list .section");
	By orderInfo = By.xpath("//div[@class='order-list']");
	
	/*
	 * public boolean checkIfOrderIsPlacedSuccessfully() {
	 * Log.info("Checking if the order is placed successfully"); return
	 * Element_Utils.isDisplayed(driver, orderSuccessMsg); }
	 */

	public boolean orderNo() {

		Log.info("Checking for the order No. being displayed");
		return Element_Utils.isDisplayed(driver, orderID);
	}
	
	public String textOfOrderNo() {
		
		Log.info("Getting the order No.");
		return Element_Utils.getText(driver, orderID);
	}

	public void openOrderDetailsPage() {

		Log.info("Opening the order details page");
		Element_Utils.clickElement(driver, orderDetailsLink);
	}
	
	public boolean orderDetailsPgDisplay() {
		
		Log.info("Checking if the order details page is displayed");
		return Element_Utils.isDisplayed(driver, orderDetTitle);
	}
	
	public String orderDetailsTitle() {
		
		Log.info("Getting the title for details page");
		return Element_Utils.getText(driver, orderDetTitle);
	}
	
	public boolean orderHistoryDisplayed() {
		
		Log.info("Checking if order history is displayed");
		return Element_Utils.isDisplayed(driver, orderInfo);
	}

	
	public boolean isAnyOrderPresent() {
		
		Log.info("Checking if at least 1 order is present");
		return Element_Utils.isDisplayed(driver, orderDataRow);
	}
}
