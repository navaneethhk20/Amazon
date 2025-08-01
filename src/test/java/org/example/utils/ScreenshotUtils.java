package org.example.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.example.driver.DriverManager;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ScreenshotUtils {

    private static final Logger logger = LogManager.getLogger(ScreenshotUtils.class);
    private static final String SCREENSHOT_BASE_DIR = "screenshots/";

    /**
     * Takes screenshot for failed tests
     * @param testName Name of the test
     * @return Path to screenshot file
     */
    public static String captureFailureScreenshot(String testName) {
        return captureScreenshot(testName, "failed-tests/");
    }

    /**
     * Takes screenshot for passed tests (optional)
     * @param testName Name of the test
     * @return Path to screenshot file
     */
    public static String captureSuccessScreenshot(String testName) {
        return captureScreenshot(testName, "passed-tests/");
    }

    /**
     * Takes screenshot at any point during test execution
     * @param testName Name of the test
     * @param step Description of the step
     * @return Path to screenshot file
     */
    public static String captureStepScreenshot(String testName, String step) {
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss"));
        String fileName = String.format("%s_%s_%s.png", testName, step.replaceAll("\\s+", "_"), timestamp);
        return captureScreenshot(fileName, "step-screenshots/");
    }

    /**
     * Generic method to capture screenshot
     * @param fileName Name for the screenshot file
     * @param subDir Subdirectory under screenshots/
     * @return Full path to the screenshot file
     */
    private static String captureScreenshot(String fileName, String subDir) {
        try {
            WebDriver driver = DriverManager.getDriver();
            if (driver == null) {
                logger.error("WebDriver is null, cannot take screenshot");
                return null;
            }

            // Create directory structure
            String fullDir = SCREENSHOT_BASE_DIR + subDir;
            File screenshotDir = new File(fullDir);
            if (!screenshotDir.exists()) {
                screenshotDir.mkdirs();
                logger.debug("Created screenshot directory: {}", fullDir);
            }

            // Generate filename with timestamp if not provided
            if (!fileName.contains("_")) {
                String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss"));
                fileName = fileName + "_" + timestamp;
            }

            if (!fileName.endsWith(".png")) {
                fileName += ".png";
            }

            String fullPath = fullDir + fileName;

            // Take screenshot
            TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
            byte[] screenshotBytes = takesScreenshot.getScreenshotAs(OutputType.BYTES);

            // Save to file
            try (FileOutputStream fos = new FileOutputStream(fullPath)) {
                fos.write(screenshotBytes);
            }

            logger.info("Screenshot saved: {}", fileName);
            return new File(fullPath).getAbsolutePath();

        } catch (Exception e) {
            logger.error("Failed to capture screenshot '{}': {}", fileName, e.getMessage(), e);
            return null;
        }
    }

    /**
     * Returns screenshot as base64 string for HTML reports
     * @return Base64 encoded screenshot
     */
    public static String getBase64Screenshot() {
        try {
            WebDriver driver = DriverManager.getDriver();
            if (driver != null) {
                TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
                return takesScreenshot.getScreenshotAs(OutputType.BASE64);
            }
        } catch (Exception e) {
            logger.error("Failed to get base64 screenshot: {}", e.getMessage());
        }
        return null;
    }

    /**
     * Cleanup old screenshots (optional utility method)
     * @param daysOld Delete screenshots older than specified days
     */
    public static void cleanupOldScreenshots(int daysOld) {
        try {
            File screenshotDir = new File(SCREENSHOT_BASE_DIR);
            if (screenshotDir.exists()) {
                long cutoffTime = System.currentTimeMillis() - (daysOld * 24 * 60 * 60 * 1000L);
                deleteOldFiles(screenshotDir, cutoffTime);
                logger.info("Cleaned up screenshots older than {} days", daysOld);
            }
        } catch (Exception e) {
            logger.error("Failed to cleanup old screenshots: {}", e.getMessage());
        }
    }

    private static void deleteOldFiles(File directory, long cutoffTime) {
        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    deleteOldFiles(file, cutoffTime);
                } else if (file.lastModified() < cutoffTime) {
                    file.delete();
                }
            }
        }
    }
}
