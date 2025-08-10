package org.example.base;


import org.example.utils.PropertiesReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.example.driver.DriverManager.getDriver;

public class CommonToAllPages {

    public CommonToAllPages(){

    }
    public void clickElement(By by){
        getDriver().findElement(by).click();
    }
    public void clickElement(WebElement by){
        by.click();
    }
    public void openUrl(){
        getDriver().get(PropertiesReader.readKey("url"));
    }

    public void enterInput(By by, String key){
        getDriver().findElement(by).sendKeys(key);
    }
    public void enterInput(WebElement by, String key){
        by.sendKeys(key);
    }
    public void getText(By by){
        getDriver().findElement(by).getText();
    }
    public WebElement getElement(By key){
        return getDriver().findElement(key);
    }
    public WebElement visiblityofElement(By location){
        return new WebDriverWait(getDriver(),Duration.ofSeconds(20)).until(ExpectedConditions.visibilityOfElementLocated(location));
    }
    public WebElement presenceOfElement(By location){
        return  new WebDriverWait(getDriver(), Duration.ofSeconds(10)).until(ExpectedConditions.presenceOfElementLocated(location));
    }



}
