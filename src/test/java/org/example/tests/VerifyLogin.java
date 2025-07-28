package org.example.tests;

import org.example.driver.DriverManager;
import org.example.page_pom.LoginPage;
import org.example.utils.PropertiesReader;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.edge.EdgeDriver;

import org.openqa.selenium.edge.EdgeOptions;
import org.testng.annotations.Test;



public class VerifyLogin {
    @Test
    public void verifyLogin(){

        LoginPage loginPage = new LoginPage(DriverManager.getDriver());
        loginPage.verifyLoginPage(PropertiesReader.readKey("username"),PropertiesReader.readKey("passwprd"));
    }
}
