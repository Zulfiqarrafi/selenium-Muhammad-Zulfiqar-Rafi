package com.juaracoding.namapeserta.drivers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.time.Duration;

public class DriverSingleton {
    private static DriverSingleton instance = null;
    private static WebDriver driver;

    private DriverSingleton() {
        // Selenium 4 otomatis mengelola driver binary (tidak perlu System.setProperty)
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
    }

    public static DriverSingleton getInstance() {
        if (instance == null) {
            instance = new DriverSingleton();
        }
        return instance;
    }

    public static WebDriver getDriver() {
        return driver;
    }

    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            instance = null;
        }
    }
}
