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

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class ShopPageSeleniumTest {

    @Autowired
    private WebDriver driver;

    @BeforeEach
    void setUp() {
        driver.get("http://localhost:3000/shop");
    }

    @AfterEach
    void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    void testShopPageLoads() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement header = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("header")));

        WebElement productList = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".product")));

        WebElement footer = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("footer")));

        Assertions.assertTrue(header.isDisplayed(), "Header should be visible");
        Assertions.assertTrue(productList.isDisplayed(), "Product list should be visible");
        Assertions.assertTrue(footer.isDisplayed(), "Footer should be visible");
    }

//    @Test
//    void testBackToTopButton() {
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//        WebElement backToTopButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".back-to-top")));
//
//        backToTopButton.click();
//
//        Assertions.assertTrue(driver.getCurrentUrl().contains("http://localhost:3000/shop"), "The page should be at the top");
//    }

    @Test
    void testProductListIsDisplayed() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement productList = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".product"))); // Ensure this is the correct selector for ListProduct

        Assertions.assertTrue(productList.isDisplayed(), "Product list should be visible");
    }
}