package org.pages.qa;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.utilities.qa.Element_Utils;

public class HomePage{

	WebDriver driver;

	public HomePage(WebDriver driver) {
		this.driver = driver;
	}

	By registerLink = By.xpath("//a[@class='ico-register']");
	By loginLink = By.xpath("//a[@href='/login']");
	By logoutLink = By.xpath("//a[@href='/logout']");
	/*
	 * By searchBox = By.xpath("//input[@value='Search store']"); By searchButton =
	 * By.cssSelector("input[value='Search']");
	 */
	By booksCategory = By.linkText("Books");
	By computersCategory = By.linkText("Computers");
	By cartLink = By.xpath("//li[@id='topcartlink']/a");
	By wishlistLink = By.xpath("//span[text()='Wishlist']/parent::a");

	public void clkRegister() {
		Element_Utils.clickElement(driver, registerLink);
	}

	public void clkLogin() {
		Element_Utils.clickElement(driver, loginLink);
	}

	public void clkLogout() {
		Element_Utils.clickElement(driver, logoutLink);
	}

	public boolean isLogoutLinkDisplayed() {
		return Element_Utils.isDisplayed(driver, logoutLink);
	}

	/*
	 * public void searchProd(String productName) { Element_Utils.enterText(driver,
	 * searchBox, productName); Element_Utils.clickElement(driver, searchButton); }
	 */

	public void clickBooksCtgry() {
		Element_Utils.clickElement(driver, booksCategory);
	}

	public void clickComputersCtgry() {
		Element_Utils.clickElement(driver, computersCategory);
	}

	public void cart() {
		Element_Utils.clickElement(driver, cartLink);
	}

	public void wishlist() {
		Element_Utils.clickElement(driver, wishlistLink);
	}
}
