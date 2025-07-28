package org.example.page_pom;

import org.example.base.CommonToAllPages;
import org.example.utils.PropertiesReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage extends CommonToAllPages {
    WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    private By expand= By.xpath("//button[@aria-label=\"Expand Account and Lists\"]");
    private By signin= By.xpath("//span[text()=\"Sign in\"]");
    private By username=By.xpath("//input[@id=\"ap_email_login\"]");
    private By password=By.xpath("//input[@type=\"password\"]");
    private By submit =By.xpath("//input[@type='submit']");
    private By userloggedin =By.xpath("//span[@id=\"nav-link-accountList-nav-line-1\"]");

    public void verifyLoginPage(String user, String pwd){
        driver.get(PropertiesReader.readKey("url"));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(expand)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(signin)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(username)).sendKeys(user);
        driver.findElement(submit).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(password)).sendKeys(pwd);
        driver.findElement(submit).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(userloggedin));
        driver.quit();
    }
}
