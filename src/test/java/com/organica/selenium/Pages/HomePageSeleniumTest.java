package com.organica.selenium.Pages;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Duration;

@SpringBootTest
public class HomePageSeleniumTest {

    @Autowired
    private WebDriver driver;

    @BeforeEach
    void setUp() {
        driver.get("http://localhost:3000/");
    }

    @AfterEach
    void tearDown() {
        // Close the browser after each test
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void testHomePageLoads() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement header = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("header")));
        WebElement hero = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".hero")));

        Assertions.assertTrue(header.isDisplayed(), "Header should be visible on the page");
        Assertions.assertTrue(hero.isDisplayed(), "Hero section should be visible on the page");
    }

    @Test
    public void testProductListIsDisplayed() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement productList = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".product")));

        Assertions.assertTrue(productList.isDisplayed(), "Product list should be visible on the page");
    }

//    @Test
//    void testBackToTopButton() {
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//        WebElement backToTopButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".back-to-top")));
//
//        backToTopButton.click();
//
//        wait.until(ExpectedConditions.stalenessOf(backToTopButton)); // Wait for button to disappear (as a result of scrolling)
//
//        Assertions.assertTrue(driver.getCurrentUrl().contains("http://localhost:3000/#top"), "The page should be at the top");
//    }

    @Test
    void testFooterIsVisible() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement footer = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("footer"))); // Wait for footer to be visible

        Assertions.assertTrue(footer.isDisplayed(), "Footer should be visible on the page");
    }
}