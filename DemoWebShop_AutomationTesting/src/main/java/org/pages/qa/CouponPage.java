package org.pages.qa;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.utilities.qa.Element_Utils;
import org.utilities.qa.Log;

public class CouponPage {

	WebDriver driver;
	
	public CouponPage(WebDriver driver) {
	
		this.driver = driver;
	}
	
	By couponTb = By.xpath("//input[@name='discountcouponcode']");
	By applyCouponBtn = By.xpath("//input[@value='Apply coupon']");
	
	By couponMsg = By.xpath("//div[@class='message']");

	public void enterCouponValue(String couponcode) {
		
		Log.info("Entering coupon code :"+couponcode);
		Element_Utils.enterText(driver, couponTb, couponcode);
		
		Log.info("Clicked on Apply coupon button");
		Element_Utils.clickElement(driver, applyCouponBtn);
	}
	
	public boolean couponMsgDisplay() {
		
		Log.info("Checking if the copoun message is displayed");
		return Element_Utils.isDisplayed(driver, couponMsg);
	}
	
	public String copMsgGet() {
		
		Log.info("Getting coupon message text");
		return Element_Utils.getText(driver, couponMsg);
	}
	
}
