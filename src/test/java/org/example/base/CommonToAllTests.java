package org.example.base;


import org.example.driver.DriverManager;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

public class CommonToAllTests {

    @BeforeMethod
    public void setup(){
        DriverManager.init();
    }
    @AfterMethod
    public void tearDown(){
        DriverManager.down();
    }

}
