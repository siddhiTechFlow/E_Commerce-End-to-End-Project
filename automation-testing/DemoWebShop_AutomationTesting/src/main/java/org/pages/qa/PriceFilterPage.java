package org.pages.qa;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.utilities.qa.Element_Utils;
import org.utilities.qa.Log;

public class PriceFilterPage {

	WebDriver driver;

	public PriceFilterPage(WebDriver driver) {

		this.driver = driver;
	}

	By apparelShoesCat = By.xpath("//ul[@class='top-menu']//a[@href='/apparel-shoes']");

	By dropdownPriceFil = By.xpath("//select[@id='products-orderby']");

	By prodPrice = By.xpath("//span[@class='price actual-price']");

	public void openApandShCat() {

		Log.info("Opening Apparel & Shoes Category");
		Element_Utils.clickElement(driver, apparelShoesCat);
	}

	public void selectPriceLowToHigh() {

		Log.info("Selecting price Low to High");
		Element_Utils.selectDropdownByText(driver, dropdownPriceFil, "Price: Low to High");
	}

	public void selectPriceHighToLow() {

		Log.info("Selecting price High to Low");
		Element_Utils.selectDropdownByText(driver, dropdownPriceFil, "Price: High to Low");

	}

	public ArrayList<Double> allProdPrice() {

		Log.info("Getting the price for all products");

		List<WebElement> price = driver.findElements(prodPrice);

		ArrayList<Double> pricels = new ArrayList<>();

		for (WebElement prices : price) {

			String pricetxt = prices.getText().replace("$", "").replace(",", "").trim();

			if (!pricetxt.isEmpty()) {
				pricels.add(Double.parseDouble(pricetxt));
			}

		}
		return pricels;

	}
	
	public boolean priceSortByLowToHigh() {
		 
		ArrayList<Double> p = allProdPrice();
		Log.info("Prices after Low to High sorting :"+p);
		
		for(int i = 0; i < p.size()-1; i++) {
			
			if(p.get(i) > p.get(i + 1)) {
				
				return false;
			}
		}
		return true;
	}
	
	public boolean priceSortByHighToLow() {
		
		ArrayList<Double> hp = allProdPrice();
		
		Log.info("Prices after High to Low Sorting :"+hp);
		
		for(int i = 0; i < hp.size()-1; i++) {
			
			if(hp.get(i) < hp.get(i + 1)) {
				return false;
			}
		}
		return true;
		
	}
}
