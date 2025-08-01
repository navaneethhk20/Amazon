package org.example.tests.Actualtests;

import org.example.base.CommonToAllTests;
import org.example.driver.DriverManager;
import org.example.listners.RetryAnalyzer;
import org.example.page_pom.LoginPage;
import org.example.utils.PropertiesReader;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Test(retryAnalyzer = RetryAnalyzer.class)
public class Logintoapp extends CommonToAllTests {
    private static final Logger logger = LogManager.getLogger(Logintoapp.class);

    @Test
    public void logintoamazn(){
        logger.info("Starting Amazon login test");
        logger.debug("Creating new LoginPage instance");

        LoginPage loginPage = new LoginPage(DriverManager.getDriver());
        String userlogged = loginPage.ValidLogin(PropertiesReader.readKey("username"),PropertiesReader.readKey("password"));
        Assert.assertEquals(userlogged,PropertiesReader.readKey("expecteduser"));
        logger.info("Login test completed successfully");


    }
}
