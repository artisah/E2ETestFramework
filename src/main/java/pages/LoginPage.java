package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {
    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public WebDriver driver;

    By loginEmail = By.cssSelector("#user_email");
    By loginPassword = By.cssSelector("#user_password");
    By submitButton = By.cssSelector("input[name='commit']");

    public WebElement getEmail() {
        return driver.findElement(loginEmail);
    }

    public WebElement getPassword() {
        return driver.findElement(loginPassword);
    }

    public WebElement getLogin() {
        return driver.findElement(submitButton);
    }

}
