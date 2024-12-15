package com.organica.selenium.Config;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;

@Configuration
public class SeleniumConfig {

    @Bean(destroyMethod = "quit")
    public WebDriver webDriver() {
        WebDriverManager.edgedriver().setup();
        return new EdgeDriver();
    }
}
