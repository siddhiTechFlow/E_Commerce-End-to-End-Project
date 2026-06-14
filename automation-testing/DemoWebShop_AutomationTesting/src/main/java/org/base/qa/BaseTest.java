package org.base.qa;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.utilities.qa.Log;
import org.utilities.qa.ScreenShot_Utils;

import org.testng.ITestResult;

public class BaseTest {

	public static WebDriver driver;
	public static Properties prop;
	
	public BaseTest() throws IOException {
		
		FileInputStream file = new FileInputStream(System.getProperty("user.dir") + "/application.properties");
		prop = new Properties();
		
		prop.load(file);
	}
	
	public void begin() {
		
		String bws = prop.getProperty("browser");
		
		if(bws.equalsIgnoreCase("Chrome")) {
			driver = new ChromeDriver();
		}else if(bws.equalsIgnoreCase("Edge")){
			driver = new EdgeDriver();
		}else if(bws.equalsIgnoreCase("Firefox")){
			driver = new FirefoxDriver();
		}else {
			throw new RuntimeException("Browser not found :" +bws);
		}
		
		
		driver.get(prop.getProperty("url"));
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		Log.info("Browser opened and DemoWebShop Tricentis launched successfully");	
	}
	
	public void tearDown(ITestResult result)
	{
		
		if(result.getStatus() == ITestResult.FAILURE)
		{
			
			ScreenShot_Utils.captureSs(driver, result.getName());
			Log.error("Test failed :" + result.getName());
		}
		
		if(driver != null) {
			driver.quit();
			Log.info("Browser closed successfully...");
		}
		
	}

}
