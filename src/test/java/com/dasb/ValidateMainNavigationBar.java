package com.dasb;

import com.dasb.base.Base;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.LandingPage;

import java.io.IOException;


public class ValidateMainNavigationBar extends Base {
    public WebDriver driver;
    public static Logger log = LogManager.getLogger(ValidateMainNavigationBar.class.getName());

    @BeforeTest
    public void driverSetUp() throws IOException {
        driver = initilaizeDriver();
        driver.get(properties.getProperty("siteurl"));
    }

    @Test
    public void validateMainNavBar() throws IOException {
        LandingPage landingPage = new LandingPage(driver);
        Assert.assertTrue(landingPage.getMainNavigationLink().isDisplayed());
        log.info("validation of main navigation bar on right is successfull");
    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }
}
