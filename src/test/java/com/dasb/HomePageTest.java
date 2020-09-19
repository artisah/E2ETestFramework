package com.dasb;

import com.dasb.base.Base;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.LandingPage;
import pages.LoginPage;

import java.io.IOException;

public class HomePageTest extends Base {
    public WebDriver driver;

    public static Logger log = LogManager.getLogger(HomePageTest.class.getName());

    @BeforeTest
    public void driverSetUp() throws IOException {
        driver = initilaizeDriver();
        log.info("Driver is initilized in Homepage");
    }


    @Test(dataProvider = "getData")
    public void goToHomepage(String userName, String password) throws IOException {

        driver.get(properties.getProperty("siteurl"));
        LandingPage landingPage = new LandingPage(driver);

        LoginPage loginPage = landingPage.clickLogIn();
        loginPage.getEmail().sendKeys(userName);
        loginPage.getPassword().sendKeys(password);

        loginPage.getLogin().click();
        log.info("Login is successfull");
    }

    @AfterTest
    public void tearDown() {

        driver.quit();
    }

    @DataProvider
    public Object[][] getData() {
        // Object[][] -> rows(no of different values for test),
        // columns (no of fields in test case ie. email, password etc)
        Object[][] data = new Object[2][2];  // Starts with index 1

        data[0][0] = "email1@gmail.com";  // Array index starts with 0
        data[0][1] = "password123";

        data[1][0] = "email2@gmail.com";
        data[1][1] = "password345";

        return data;
    }


}
