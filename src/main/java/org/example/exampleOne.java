package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
////////////////*******State Farm Website********////////////////////
public class exampleOne {
    WebDriver driver = null;
    @Test
    public void stateFarmHomePage(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://www.statefarm.com");
    }
    @Test
    public void stateFarmHomePage1() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https:www.statefarm.com");
    }
    @Test
    public void stateFarmHomePageZipCodeValidate1() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https:www.statefarm.com");
        driver.findElement(By.id("quote-main-zip-code-input")).sendKeys("07508");
        int lengthOfZipCode = driver.findElement(By.id("quote-main-zip-code-input")).getAttribute("value").length();
        //System.out.println(lengthOfZipCode); -> this is just to test manually (not to use in real world)
        Assert.assertEquals(lengthOfZipCode, 5); // -> we will be using this statement as real world example
        driver.quit();
    }
    @Test
    public void validateIfZipCodeTakesMoreThan5Integers() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https:www.statefarm.com");
        driver.findElement(By.id("quote-main-zip-code-input")).sendKeys("0750812");
        int longZipCode = driver.findElement(By.id("quote-main-zip-code-input")).getAttribute("value").length();
        //System.out.println(longZipCode);
        Assert.assertEquals(longZipCode, 5);
        driver.quit();
    }
    @Test
    public void validateIfZipCodeTakesAlphabets() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https:www.statefarm.com");
        driver.findElement(By.id("quote-main-zip-code-input")).sendKeys("abcdef");
        int AlphaZipCode = driver.findElement(By.id("quote-main-zip-code-input")).getAttribute("value").length();
        //System.out.println(AlphaZipCode);
        Assert.assertEquals(AlphaZipCode, 0);
        driver.quit();
    }
    @Test
    public void validateZipCodeErrorMessage() throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https:www.statefarm.com");
        driver.findElement(By.id("quote-main-zip-code-input")).sendKeys("00098", Keys.ENTER);
        Thread.sleep(3000);
        String errorMessage = "Enter a valid 5-digit ZIP Code.";
        String validateErrorMessage = driver.findElement(By.cssSelector("#quote-error-alert")).getText();
        System.out.println(validateErrorMessage);
        System.out.println(errorMessage);
        Assert.assertEquals(validateErrorMessage,errorMessage);
        driver.quit();
    }

    //Quick actions -> on HOME page
    @Test
    public void validateFileaClaim() throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https:www.statefarm.com");
        driver.findElement(By.xpath("//a[normalize-space()='File a claim']")).sendKeys(Keys.ENTER);
        Thread.sleep(4000);
        driver.quit();
    }
    @Test
    public void validateTrackaClaim() throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https:www.statefarm.com");
        driver.findElement(By.xpath("//a[normalize-space()='Track a claim']")).sendKeys(Keys.ENTER);
        Thread.sleep(4000);
        driver.quit();
    }
    @Test
    public void validateTrackaClaimFileaClaim() throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https:www.statefarm.com");
        driver.findElement(By.xpath("//a[normalize-space()='Track a claim']")).sendKeys(Keys.ENTER);
        Thread.sleep(4000);
        driver.get("https:www.statefarm.com");
        driver.findElement(By.xpath("//a[normalize-space()='File a claim']")).sendKeys(Keys.ENTER);
        Thread.sleep(4000);
        driver.quit();
    }
    @Test
    public void validateStartaQuoteButton() throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https:www.statefarm.com");
        driver.findElement(By.id("quote-main-zip-code-input")).sendKeys("07508");
        driver.findElement(By.id("quote-main-zip-btn")).click();
        Thread.sleep(4000);
        driver.quit();
    }




}
