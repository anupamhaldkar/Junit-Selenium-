package com.example.demo;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.List;

public class SeleniumTest {
    public static void main(String[] args) throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("useAutomationExtension", false);
        options.addArguments("--remote-debugging-port=9225");
        WebDriver driver = new ChromeDriver(options);
        driver.get("http://localhost:8080/animal");
        WebElement inputField = driver.findElement(By.id("animalText"));
        inputField.sendKeys("Manatee");

        inputField = driver.findElement(By.id("adjective"));
        inputField.sendKeys("Whirling");

        //the fields don’t clear on submit for our simple app, so just submit it 5 times
        for(int i = 0; i < 5; i++) {
            inputField.submit();

            List<WebElement> trainingResults = driver.findElements(By.className("trainingMessage"));
            System.out.println("trainingResults.size() = " + trainingResults.size());
        }

        // then get the element by the class conclusionMessage and print it
        WebElement conclusionResult = driver.findElement(By.className("conclusionMessage"));
        System.out.println("conclusionResult.getText() = " + conclusionResult.getText());

        Thread.sleep(5000);
        driver.quit();
    }
}
