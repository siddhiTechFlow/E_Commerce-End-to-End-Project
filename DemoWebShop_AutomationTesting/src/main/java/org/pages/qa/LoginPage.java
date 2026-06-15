package org.pages.qa;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.utilities.qa.Element_Utils;

public class LoginPage {

	WebDriver driver;

	public LoginPage(WebDriver driver) {
		this.driver = driver;
	}

	By email = By.xpath("//input[@class='email']");
	By pwd = By.xpath("//input[@class='password']");
	By loginBtn = By.xpath("//input[@class='button-1 login-button']");
	By validationMsg = By.xpath("//div[@class='validation-summary-errors']");
	
	public void login(String userEmail, String userPass) {
		
		Element_Utils.enterText(driver, email, userEmail);
		Element_Utils.enterText(driver, pwd, userPass);
		Element_Utils.clickElement(driver, loginBtn);
	}
	
	public boolean validationIsDisplayed() {
		
		return Element_Utils.isDisplayed(driver, validationMsg);
	}
}
