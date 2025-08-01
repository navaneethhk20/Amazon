package org.example.page_pom;


import org.example.base.CommonToAllPages;
import org.example.utils.PropertiesReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage extends CommonToAllPages {
    WebDriver driver;

    public LoginPage(WebDriver driver){
        this.driver = driver;
    }

    private By expandbutton = By.xpath("//button[@aria-label='Expand Account and Lists']");
    private By signin=By.xpath("//span[text()='Sign in']");
    private By usernamebutton = By.xpath("//input[@id='ap_email_login']");
    private By passwordbutton = By.xpath("//input[@type='password']");
   // private By continueshopping = By.xpath("//button[@type='submit']");
    private By submitbutton = By.xpath("//input[@type='submit']");
    private By name = By.xpath(" //span[@id='nav-link-accountList-nav-line-1']");

    public String ValidLogin(String user, String pwd){
        openUrl();
        //visiblityofElement(continueshopping).click();
        visiblityofElement(expandbutton).click();
        visiblityofElement(signin).click();
        visiblityofElement(usernamebutton).sendKeys(user);
        clickElement(submitbutton);
        visiblityofElement(passwordbutton).sendKeys(pwd);
        clickElement(submitbutton);
        String nameofuser = driver.findElement(name).getText();
        return nameofuser;
    }

}
