package org.pages.qa;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.utilities.qa.Element_Utils;

public class RegisterPage {

	WebDriver driver;

	public RegisterPage(WebDriver driver) {
		this.driver = driver;
	}

	// ATC_015
	By gender = By.xpath("//input[@id='gender-female']");
	By fname = By.xpath("//input[@name='FirstName']");
	By lname = By.cssSelector("input[name='LastName']");
	By email = By.cssSelector("input[name='Email']");
	By pwd = By.xpath("//input[@id='Password']");
	By confirmPwd = By.xpath("//input[@id='ConfirmPassword']");
	By registerBtn = By.xpath("//input[@id='register-button']");

	By successMsg = By.xpath("//div[@class='result']");

	// Validation when the fields are left blank
	By fnameValidationMsg = By.xpath("//span[@for='FirstName']");
	By lnameValidationMsg = By.cssSelector("span[data-valmsg-for='LastName']");
	By emailValidationMsg = By.cssSelector("span[data-valmsg-for='Email']");
	By pwdValidationMsg = By.cssSelector("span[data-valmsg-for='Password']");
	By confirmPwdValidationMsg = By.cssSelector("span[data-valmsg-for='ConfirmPassword']");

	public void register(String fstName, String lstName, String mail, String pass) {

		Element_Utils.selectRadioButton(driver, gender);
		Element_Utils.enterText(driver, fname, fstName);
		Element_Utils.enterText(driver, lname, lstName);
		Element_Utils.enterText(driver, email, mail);
		Element_Utils.enterText(driver, pwd, pass);
		Element_Utils.enterText(driver, confirmPwd, pass);
		Element_Utils.clickElement(driver, registerBtn);
	}

	public String successMsg() {
		return Element_Utils.getText(driver, successMsg);
	}

	public void clkRegBtnWithoutData() {
		Element_Utils.clickElement(driver, registerBtn);
	}

	public boolean validationMsgIsDisplayed() {

		return Element_Utils.isDisplayed(driver, fnameValidationMsg)
				&& Element_Utils.isDisplayed(driver, lnameValidationMsg)
				&& Element_Utils.isDisplayed(driver, emailValidationMsg)
				&& Element_Utils.isDisplayed(driver, pwdValidationMsg)
				&& Element_Utils.isDisplayed(driver, confirmPwdValidationMsg);

	}

}
