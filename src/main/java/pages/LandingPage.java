package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class LandingPage {

    public WebDriver driver;
    private By signInLink = By.cssSelector("a[href*='sign_in']");
    private By featureCourseTitle = By.cssSelector("#content .text-center > h2");
    private By mainNavigationRight = By.cssSelector(".nav.navbar-nav.navbar-right");

    public LandingPage(WebDriver driver) {
        this.driver = driver;
    }

    public LoginPage clickLogIn() {
         driver.findElement(signInLink).click();
          return  new LoginPage(driver);

    }

    public WebElement getFeatureCourseTitle() {
        return driver.findElement(featureCourseTitle);
    }

    public WebElement getMainNavigationLink() {
        return driver.findElement(mainNavigationRight);
    }
}
