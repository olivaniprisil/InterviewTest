package com.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class AppTest {

    WebDriver driver;
    WebDriverWait wait;

    @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test
    public void testLoginSuccess() {
        driver.get("https://your-app-url.com");

        driver.findElement(By.id("user_name_input")).sendKeys("admin222");
        driver.findElement(By.id("password_input")).sendKeys("Test@123");
        driver.findElement(By.id("login_button")).click();

        // Verify toaster message appears immediately after login
        WebElement toaster = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("toaster_success")));
        assert toaster.getText().equals("Logged in successfully");

        // Verify dashboard loads successfully
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("dashboard_main_menu")));
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}