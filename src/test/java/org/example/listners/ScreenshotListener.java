package org.example.listners;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.example.driver.DriverManager;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class ScreenshotListener  implements ITestListener {

    private static final Logger logger = LogManager.getLogger(ScreenshotListener.class);
    private static final String SCREENSHOT_DIR = "screenshots/failed-tests/";

    @Override
    public void onTestStart(ITestResult result) {
        logger.info("Starting test: {}", result.getMethod().getMethodName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        logger.info("Test passed: {}", result.getMethod().getMethodName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        String testName = result.getMethod().getMethodName();
        String className = result.getTestClass().getName();

        logger.error("Test failed: {}.{}", className, testName);
        logger.error("Failure reason: {}", result.getThrowable().getMessage());

        // Take screenshot on failure
        String screenshotPath = takeScreenshot(testName, className);
        if (screenshotPath != null) {
            logger.info("Screenshot saved at: {}", screenshotPath);

            // Set screenshot path as system property for reporting tools
            System.setProperty("screenshot.path", screenshotPath);

            // Attach screenshot path to TestNG result for ExtentReports or other reporting tools
            result.setAttribute("screenshotPath", screenshotPath);
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        logger.warn("Test skipped: {}", result.getMethod().getMethodName());
    }

    /**
     * Takes screenshot and saves it to the screenshots directory
     * @param testName Name of the failed test
     * @param className Name of the test class
     * @return Path to the saved screenshot file
     */
    private String takeScreenshot(String testName, String className) {
        try {
            // Get WebDriver instance from your DriverManager
            WebDriver driver = DriverManager.getDriver();
            if (driver == null) {
                logger.error("WebDriver is null, cannot take screenshot");
                return null;
            }

            // Create screenshots directory if it doesn't exist
            File screenshotDir = new File(SCREENSHOT_DIR);
            if (!screenshotDir.exists()) {
                screenshotDir.mkdirs();
            }

            // Generate timestamp for unique filename
            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss"));

            // Create filename: ClassName_TestName_Timestamp.png
            String fileName = String.format("%s_%s_%s.png",
                    className.substring(className.lastIndexOf('.') + 1),
                    testName,
                    timestamp);

            String filePath = SCREENSHOT_DIR + fileName;

            // Take screenshot
            TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
            byte[] screenshotBytes = takesScreenshot.getScreenshotAs(OutputType.BYTES);

            // Save screenshot to file
            try (FileOutputStream fos = new FileOutputStream(filePath)) {
                fos.write(screenshotBytes);
            }

            logger.info("Screenshot captured successfully: {}", fileName);
            return new File(filePath).getAbsolutePath();

        } catch (Exception e) {
            logger.error("Failed to take screenshot: {}", e.getMessage(), e);
            return null;
        }
    }

    /**
     * Alternative method to take screenshot and return as base64 string
     * Useful for HTML reports that embed images
     */
    public static String getBase64Screenshot() {
        try {
            WebDriver driver = DriverManager.getDriver();
            if (driver != null) {
                TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
                return takesScreenshot.getScreenshotAs(OutputType.BASE64);
            }
        } catch (Exception e) {
            LogManager.getLogger(ScreenshotListener.class)
                    .error("Failed to capture base64 screenshot: {}", e.getMessage());
        }
        return null;
    }
}
