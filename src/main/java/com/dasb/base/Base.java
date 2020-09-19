package com.dasb.base;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class Base {

    public static WebDriver driver;
    public Properties properties;

    public WebDriver initilaizeDriver()  {

        properties = new Properties();
        FileInputStream inputStream = null;
        try {
            inputStream = new FileInputStream(System.getProperty("user.dir") + "/src/main/resources/data.properties");
            properties.load(inputStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        //   String browserName = properties.getProperty("browser"); // when reading from property file

        String browserName = System.getProperty("browser");  // when passing browser as maven command in cmd mvn test -Dbrowser=chrome

        if (browserName.equals("chrome")) {

            System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/resources/chromedriver");
            driver = new ChromeDriver();

        } else if (browserName.equals("firefox")) {

            System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "/resources/geckodriver");
            driver = new FirefoxDriver();

        } else if (browserName.equals("IE")) {
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
