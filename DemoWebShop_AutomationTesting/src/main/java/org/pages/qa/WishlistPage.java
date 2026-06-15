package org.pages.qa;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.utilities.qa.Element_Utils;
import org.utilities.qa.Log;


public class WishlistPage{

	WebDriver driver;

	public WishlistPage(WebDriver driver) {
		this.driver = driver;
	}
	
	By wlProd = By.cssSelector(".cart-item-row");
	By wlCount = By.xpath("//span[@class='wishlist-qty']");
	By wishlistLink = By.xpath("//span[@class='wishlist-qty']");
	
	public void clkWishlist() {
		
		Log.info("Opening the wishlist page");
		Element_Utils.clickElement(driver, wishlistLink);
	}
	
	public String wishlistCount() {
		
		Log.info("Getting the count of wishlist");
		return Element_Utils.getText(driver, wlCount);
	}
	
	public boolean checkIfTheProdIsThereInTheWl() {
		
		Log.info("Checking if the Product is there in the Wishlist");
		return Element_Utils.isDisplayed(driver, wlProd);
	}

}
