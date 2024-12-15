package com.organica.selenium.Pages;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Duration;
import java.util.UUID;

@SpringBootTest
public class SignupAndLoginSeleniumTest {

    @Autowired
    private WebDriver driver;

    private String uniqueEmail;

    @BeforeEach
    void setUp() {
        uniqueEmail = "selenium_test_" + UUID.randomUUID() + "@example.com";
    }

    @AfterEach
    void tearDown() {
        // Close the browser after each test
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    void testSignupAndLogin() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        // === Signup Test ===
        driver.get("http://localhost:3000/singup");

        // Fill the signup form
        driver.findElement(By.name("name")).sendKeys("Test User");
        driver.findElement(By.name("email")).sendKeys(uniqueEmail);
        driver.findElement(By.name("password")).sendKeys("password1234");
        driver.findElement(By.name("confirmPassword")).sendKeys("password1234");

        // Submit the signup form
        driver.findElement(By.id("submit-id-submit")).click();

        // Wait for signup success toast
        WebElement signupToast = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".Toastify__toast--success"))
        );
        Assertions.assertEquals("Signup Successful!", signupToast.getText(), "Signup failed");

        // Wait for the redirection to the login page
        wait.until(ExpectedConditions.urlToBe("http://localhost:3000/login"));
        Assertions.assertEquals("http://localhost:3000/login", driver.getCurrentUrl(), "Signup did not redirect to login page");

        // Pause to ensure backend registration
        Thread.sleep(3000);

        // Fill the login form
        driver.findElement(By.name("email")).sendKeys(uniqueEmail);
        driver.findElement(By.name("password")).sendKeys("password1234");

        // Submit the login form
        driver.findElement(By.id("submit-id-submit")).click();

        // Wait for login success toast
        WebElement loginToast = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".Toastify__toast--success"))
        );
        Assertions.assertEquals("Login Successfull!!", loginToast.getText(), "Login failed");

        // Verify redirection to homepage
        wait.until(ExpectedConditions.urlToBe("http://localhost:3000/"));
        Assertions.assertEquals("http://localhost:3000/", driver.getCurrentUrl());
    }
}
