package com.dasb.base;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class Base {

    public static WebDriver driver;
    public Properties properties;

    public WebDriver initilaizeDriver() throws IOException {

        properties = new Properties();
        FileInputStream inputStream = new FileInputStream(System.getProperty("user.dir") + "/src/main/resources/data.properties");

        properties.load(inputStream);

        String driverValueInPropertyFile = properties.getProperty("browser");

        if (driverValueInPropertyFile.equals("chrome")) {

            System.setProperty("webdriver.chrome.driver", "/Users/development/Selenium/Downloads/chromedriver");
            driver = new ChromeDriver();

        } else if (driverValueInPropertyFile.equals("firefox")) {

            driver = new FirefoxDriver();

        } else if (driverValueInPropertyFile.equals("IE")) {
            //Write code for IE
        }

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        return driver;

    }

    public String captureScreenshot(String testCaseName, WebDriver driver ) throws IOException {
        TakesScreenshot screenshot = (TakesScreenshot) driver;
        File source = screenshot.getScreenshotAs(OutputType.FILE);
        String  pathForScreenshotFolder = System.getProperty("user.dir")+"/reports/"+testCaseName+".png";
        FileUtils.copyFile(source, new File(pathForScreenshotFolder));
        System.out.println("captureScreenshot" + pathForScreenshotFolder);
        return pathForScreenshotFolder;

    }

}
