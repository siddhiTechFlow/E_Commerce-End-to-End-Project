package org.utilities.qa;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class Element_Utils {

	// click an element
	public static void clickElement(WebDriver driver, By locator) {
		Wait_Utils.waitForClickable(driver, locator).click();
	}

	// enter text
	public static void enterText(WebDriver driver, By locator, String text) {
		
		WebElement el = Wait_Utils.waitForVisible(driver, locator);
		el.clear();
		el.sendKeys(text);
	}

	// check whether the element is displayed
	public static boolean isDisplayed(WebDriver driver, By locator) {
		return Wait_Utils.waitForVisible(driver, locator).isDisplayed();
	}

	// get text from element
	public static String getText(WebDriver driver, By locator) {
		return Wait_Utils.waitForVisible(driver, locator).getText();
	}

	// to select CheckBox
	public static void selectCheckbox(WebDriver driver, By loctor) {
		WebElement cb = Wait_Utils.waitForClickable(driver, loctor);

		if (!cb.isSelected()) {
			cb.click();
		}
	}

	// select radio button
	public static void selectRadioButton(WebDriver driver, By locator) {
		WebElement rb = Wait_Utils.waitForClickable(driver, locator);

		if (!rb.isSelected()) {
			rb.click();
		}
	}

	// select DropDown by visible text
	public static void selectDropdownByText(WebDriver driver, By locator, String text) {
		Select dd = new Select(Wait_Utils.waitForVisible(driver, locator));
		dd.selectByVisibleText(text);
	}

	// select DropDown by index
	public static void selectDropdownByIndex(WebDriver driver, By locator, int index) {
		Select dd = new Select(Wait_Utils.waitForVisible(driver, locator));
		dd.selectByIndex(index);
	}

	// select DropDown by value
	public static void selectDropdownByValue(WebDriver driver, By locator, String value) {
		Select dd = new Select(Wait_Utils.waitForVisible(driver, locator));
		dd.selectByValue(value);
	}

	// scroll handling
	public static void scroll(WebDriver driver, By loctor) {

		WebElement el = Wait_Utils.waitForPresence(driver, loctor);
		((org.openqa.selenium.JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", el);

	}

	// click element using JS if normal click fails
	public static void jsClk(WebDriver driver, By locator) {

		WebElement el = Wait_Utils.waitForPresence(driver, locator);
		((org.openqa.selenium.JavascriptExecutor) driver).executeScript("argument[0].click();", el);
	}
}
