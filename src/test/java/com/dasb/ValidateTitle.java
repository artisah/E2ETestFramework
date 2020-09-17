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

public class ValidateTitle extends Base {
    public WebDriver driver;
    public static Logger log = LogManager.getLogger(ValidateTitle.class.getName());

    @BeforeTest
    public void driverSetUp() throws IOException {
        driver = initilaizeDriver();
        driver.get(properties.getProperty("siteurl"));

    }

    @Test
    public void validateTitle() throws IOException {
        LandingPage landingPage = new LandingPage(driver);
        String FeatureCourseTitleText = landingPage.getFeatureCourseTitle().getText();
        Assert.assertEquals(FeatureCourseTitleText, "FEATURED COURSES123");
        Assert.assertTrue(landingPage.getMainNavigationLink().isDisplayed());
        log.info("Validation of text in home page successful");
    }

    @AfterTest
    public void tearDown() {
        driver.close();
    }
}

