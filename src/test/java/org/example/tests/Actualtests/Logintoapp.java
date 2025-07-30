package org.example.tests.Actualtests;

import org.example.base.CommonToAllTests;
import org.example.driver.DriverManager;
import org.example.page_pom.LoginPage;
import org.example.utils.PropertiesReader;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Logintoapp extends CommonToAllTests {
    @Test
    public void logintoamazn(){
        LoginPage loginPage = new LoginPage(DriverManager.getDriver());
        String userlogged = loginPage.ValidLogin(PropertiesReader.readKey("username"),PropertiesReader.readKey("password"));
        Assert.assertEquals(userlogged,PropertiesReader.readKey("expecteduser"));

    }
}
