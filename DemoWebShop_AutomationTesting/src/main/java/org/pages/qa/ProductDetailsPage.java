package org.pages.qa;

import org.utilities.qa.Element_Utils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductDetailsPage {

	WebDriver driver;

	public ProductDetailsPage(WebDriver driver) {
		this.driver = driver;
	}

	By prodTitle = By.cssSelector(".product-name h1");

	By addToCartBtn = By.cssSelector("input[value='Add to cart']");
	By addToWishlistBtn = By.cssSelector("input[value='Add to wishlist']");
	By addToCompareBtn = By.cssSelector("input[value='Add to compare list']");

	By successMsg = By.className("bar-notification");

	public void openProd(String prodName) {
		
		By prodLink = By.xpath("//h2[@class='product-title']/a[normalize-space()=\"" + prodName + "\"]");
		Element_Utils.clickElement(driver, prodLink);

	}

	// verify whether the product details page opened
	public boolean isProdDetPageOpen() {
		return Element_Utils.isDisplayed(driver, prodTitle);
	}

	// get the text of the product title
	public String getProdTitleText() {
		return Element_Utils.getText(driver, prodTitle);
	}

	public void addProdToCart() {
		Element_Utils.clickElement(driver, addToCartBtn);
	}

	public void addProdToWishlist() {
		Element_Utils.clickElement(driver, addToWishlistBtn);
	}

	public void addProdToCompareList() {
		Element_Utils.clickElement(driver, addToCompareBtn);
	}

	public boolean successMsgDisplayed() {
		return Element_Utils.isDisplayed(driver, successMsg);
	}
}
