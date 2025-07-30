package org.example.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.testng.annotations.Test;


import java.time.Duration;

public class Sampletest {

    @Test
    public void verifyLogin() {
    ChromeOptions chromeoptions = new ChromeOptions();
    chromeoptions.addArguments("--start-maximized");
    WebDriver driver = new ChromeDriver(chromeoptions);
    driver.get("https://www.amazon.in/");
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@aria-label=\"Expand Account and Lists\"]"))).click();
    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()=\"Sign in\"]"))).click();
    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id=\"ap_email_login\"]"))).sendKeys("hknavaneeth@gmail.com");
    driver.findElement(By.xpath("//input[@type='submit']")).click();
    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@type=\"password\"]"))).sendKeys("Nithu@123");
    driver.findElement(By.xpath("//input[@type='submit']")).click();
    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@id=\"nav-link-accountList-nav-line-1\"]")));
    }
}