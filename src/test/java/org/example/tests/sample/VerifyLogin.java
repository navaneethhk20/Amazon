package org.example.tests.sample;

import org.example.base.CommonToAllTests;
import org.example.page_pom.LoginPage;
import org.example.utils.PropertiesReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class VerifyLogin {

    @Test
    public void LoginTest(){
        WebDriver driver = new ChromeDriver();
        LoginPage loginPage = new LoginPage(driver);
        String userlogged = loginPage.ValidLogin(PropertiesReader.readKey("username"),PropertiesReader.readKey("password"));
        Assert.assertEquals(userlogged,PropertiesReader.readKey("expecteduser"));
    }

}
