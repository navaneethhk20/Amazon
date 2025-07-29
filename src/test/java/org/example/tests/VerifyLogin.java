package org.example.tests;

import org.example.page_pom.LoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class VerifyLogin {

    @Test
    public void LoginTest(){
        WebDriver driver = new ChromeDriver();
        LoginPage loginPage = new LoginPage(driver);
        String userlogged = loginPage.ValidLogin("hknavaneeth@gmail.com","Nithu@123");
        Assert.assertEquals(userlogged,"Hello, Navaneeth");

    }

}
