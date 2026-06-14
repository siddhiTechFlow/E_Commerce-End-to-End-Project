package org.pages.qa;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.utilities.qa.Element_Utils;

import org.utilities.qa.Log;

public class CartPage {

	WebDriver driver;

	public CartPage(WebDriver driver) {
		this.driver = driver;
	}

	// after adding the product to the cart. The product details in the cart
	By cartItem = By.xpath("//tr[@class='cart-item-row']");

	By quantityInput = By.xpath("//input[@class='qty-input']");
	By updateShoppingCartBtn = By.name("updatecart");
	By removeProdCb = By.xpath("//input[@name='removefromcart']");

	By emptyCartMsg = By.xpath("//*[contains(text(),'Your Shopping Cart is empty')]");

	By aggreTermsOfServices = By.xpath("//input[@id='termsofservice']");
	By checkoutBtn = By.xpath("//button[@type='submit']");
	
	By cartQtyHeader =  By.xpath("//span[@class='cart-qty']");
	By prodSubTotal = By.xpath("//span[@class='product-subtotal']");

	public boolean isProdThereInTheCart() {

		Log.info("Checking if the product is there in the cart");
		return Element_Utils.isDisplayed(driver, cartItem);
	}

	public void updateProdQty(String qty) {

		Log.info("Updating the product quantity");
		Element_Utils.enterText(driver, quantityInput, qty);
		
		Log.info("Clicking on the Update Shopping Cart Button");
		Element_Utils.clickElement(driver, updateShoppingCartBtn);

	}

	public void removeProdFromCart() {

		Log.info("Removing the product from the cart by clicking on the checkbox");
		Element_Utils.selectCheckbox(driver, removeProdCb);
		
		Log.info("Clicking on the Update Shopping Cart Button");
		Element_Utils.clickElement(driver, updateShoppingCartBtn);
	}

	public boolean ifCartIsEmpty() {

		Log.info("Checking for the validation message if the cart is empty");
		return Element_Utils.isDisplayed(driver, emptyCartMsg);
	}

	public void checkoutProcess() {

		Log.info("Agreeing to the terms and proceeding to checkout");
		Element_Utils.selectCheckbox(driver, aggreTermsOfServices);
		
		Log.info("Clicking on the checkout button");
		Element_Utils.clickElement(driver, checkoutBtn);

	}
	
	public String cartCount() {
		
		Log.info("Getting the count of the cart from the header");
		return Element_Utils.getText(driver, cartQtyHeader);
	}
	
	public String prodSubTotal() {
		
		Log.info("Getting the sub total of the product from cart");
		return Element_Utils.getText(driver, prodSubTotal);
	}
}
