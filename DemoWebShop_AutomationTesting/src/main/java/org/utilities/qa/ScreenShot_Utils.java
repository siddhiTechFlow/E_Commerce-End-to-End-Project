package org.utilities.qa;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import io.qameta.allure.Allure;

public class ScreenShot_Utils {

    public static void captureSs(WebDriver driver, String testName) {

        try {
            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

            File folder = new File(System.getProperty("user.dir") + "/Screenshots");
            if (!folder.exists()) {
                folder.mkdirs();
            }

            File dest = new File(folder + "/" + testName + ".png");

            Files.copy(src.toPath(), dest.toPath(), StandardCopyOption.REPLACE_EXISTING);

            Log.info("Screenshot captured for failed test: " + testName);

            byte[] screenshotBytes = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);

            Allure.addAttachment(testName + " Failure Screenshot", "image/png", new ByteArrayInputStream(screenshotBytes),".png");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}