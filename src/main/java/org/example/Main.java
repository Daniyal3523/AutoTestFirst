package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

public class Main {

    WebDriver driver = null;
    WebDriver driver1 = null;
    String expectedZipCodeErrorMessage = "Enter a valid 5-digit ZIP Code.";
   @Test
    public void stateFarmHomePage(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://www.statefarm.com");
    }

    @Test
    public void validateInsurancePage() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://www.google.com");
        driver.quit();
    }

    @Test
    public void validateFacebookPage() {
       WebDriverManager.chromedriver().setup();
       driver = new ChromeDriver();
       driver.get("https:www.facebook.com");

    }

    @Test
    public void ValidateStateFarmHomePage() {
       WebDriverManager.chromedriver().setup();
       driver = new ChromeDriver();
       driver.get("https:www.statefarm.com");
       driver.findElement(By.id("quote-main-zip-code-input")).sendKeys("19047");
       int lengthOfZipcode = driver.findElement(By.id("quote-main-zip-code-input")).getAttribute("value").length();
        System.out.println(lengthOfZipcode);
        Assert.assertEquals(lengthOfZipcode, 5);
    }

    @Test
    public void validateIfZipcodeTakesMoreThan5Integers() {
       WebDriverManager.chromedriver().setup();
       driver = new ChromeDriver();
       driver.get("https:www.statefarm.com");
       driver.findElement(By.id("quote-main-zip-code-input")).sendKeys("190474573893320");
       int lenghtOfZipCode = driver.findElement(By.id("quote-main-zip-code-input")).getAttribute("value").length();
        System.out.println(lenghtOfZipCode);
        Assert.assertEquals(lenghtOfZipCode, 5);
    }

    @Test
    public void validateIfZipcodeTakesCharacters() {
       WebDriverManager.chromedriver().setup();
       driver = new ChromeDriver();
       driver.get("https:www.statefarm.com");
       driver.findElement(By.id("quote-main-zip-code-input")).sendKeys("abcdefg");
       int lengthOfZipcode = driver.findElement(By.id("quote-main-zip-code-input")).getAttribute("value").length();
        System.out.println(lengthOfZipcode);
        Assert.assertEquals(lengthOfZipcode, 0);
    }

    @Test
    public void validateZipcodeErrorMessage() throws InterruptedException {
       WebDriverManager.chromedriver().setup();
       driver = new ChromeDriver();
       driver.get("https:www.statefarm.com");
       driver.findElement(By.id("quote-main-zip-code-input")).sendKeys("00098", Keys.ENTER);
       Thread.sleep(2000);
       String errorMessage = driver.findElement(By.cssSelector("#quote-error-alert")).getText();
        System.out.println(errorMessage); //-> Not to use in professional world
        Assert.assertEquals(errorMessage, expectedZipCodeErrorMessage);



    }
    @AfterTest
    public void tearDown() {
       driver.quit();
    }


}