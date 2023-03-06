package org.example;

import com.beust.ah.A;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

////////////////*******State Farm Website********////////////////////
public class exampleOne {
    WebDriver driver = null;
    @Test
    public void stateFarmHomePage() throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://www.statefarm.com");
        Thread.sleep(1500);
        driver.quit();
    }
    @Test
    public void stateFarmHomePage1() throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https:www.statefarm.com");
        Thread.sleep(1500);
        driver.quit();
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
    @Test
    public void testDropDown() throws InterruptedException {
        List<String> expectedDropDown = new ArrayList<>(Arrays.asList("Select", "Option1", "Option2", "Option3"));
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://rahulshettyacademy.com/AutomationPractice/");
        Select select = new Select(driver.findElement(By.id("dropdown-class-example")));
        Thread.sleep(4000);
        select.selectByIndex(3);
        Thread.sleep(3000);
        select.selectByVisibleText("Option2");
        Thread.sleep(3000);
        select.selectByValue("option1"); ///Not Working?????????????
        List<WebElement> e = select.getOptions();
        for (int i=0; i<e.size(); i++){
            Assert.assertEquals(e.get(i).getText(), expectedDropDown.get(i));
        }
        Thread.sleep(1500);
        driver.quit();
    }
    @Test
    public void alertMessage() throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://rahulshettyacademy.com/AutomationPractice/");
        driver.findElement(By.id("alertbtn")).click();
        driver.switchTo().alert().accept();
        Thread.sleep(500);
        driver.findElement(By.id("confirmbtn")).click();
        driver.switchTo().alert().dismiss();
        Thread.sleep(400);
        driver.findElement(By.id("confirmbtn")).click();
        String allertMessageForConrifmBtn = driver.switchTo().alert().getText();
        String expectedalertForConfirmBtn = "Hello , Are you sure you want to confirm?";
        Assert.assertEquals(allertMessageForConrifmBtn, expectedalertForConfirmBtn);
        System.out.println(allertMessageForConrifmBtn);
        System.out.println(expectedalertForConfirmBtn);
        driver.quit();
    }
    @Test
    public void switchTab() throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://rahulshettyacademy.com/AutomationPractice/");
        String mainWindow = driver.getWindowHandle();
        driver.findElement(By.id("opentab")).click();
        Thread.sleep(4000);
        Set<String> wins = driver.getWindowHandles();
        for (String w : wins){
            if (!w.equalsIgnoreCase(mainWindow)){
                driver.switchTo().window(w); //window2
                Thread.sleep(4000);
                String courses = driver.findElement(By.linkText("Courses")).getText();
                driver.findElement(By.linkText("Courses")).isDisplayed();
                Assert.assertEquals(courses, "Courses");
                //driver.quit();
            }
            //Above code is working but when I run below it shows error??????????????????????
            driver.switchTo().window(mainWindow);
            driver.findElement(By.id("opentab")).isDisplayed();
            Thread.sleep(3000);
            //driver.quit();
        }
    }
    @Test
    public void howToHandleWebTable() throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://rahulshettyacademy.com/AutomationPractice/");
        List<WebElement> cloumNumber = driver.findElements(By.xpath("//table[@id='product']/tbody/tr/th")); //Where did this xpath come from?????
        int columCount = cloumNumber.size();
        System.out.println("Num of colum in table: " + columCount);
        List<WebElement> rowNumber = driver.findElements(By.xpath("//table[@class='table-display']/tbody/tr/td")); //Where did this xpath come from?????
        Thread.sleep(2000);
        int rowCount = rowNumber.size();
        System.out.println("Num of rows in table: " + rowCount); //Not correct?????????????????????????
        for (int C=0; C<columCount;C++){
            System.out.println("Colum Name: " + cloumNumber.get(C).getText());
        }
        for (int R=0; R<rowCount;R++){
            System.out.println("Rows Value: " + rowNumber.get(R).getText());
        }
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println(cloumNumber.get(0).getText());
        System.out.println(cloumNumber.get(1).getText());
        System.out.println(cloumNumber.get(2).getText());
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println(rowNumber.get(0).getText());
        System.out.println(rowNumber.get(1).getText());
        System.out.println(rowNumber.get(2).getText());
        System.out.println(rowNumber.get(3).getText());
        System.out.println(rowNumber.get(4).getText());
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println(cloumNumber.get(1).getText() +" "+ rowNumber.get(2).getText());
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        driver.quit();
    }
    @Test
    public void validateAutoSuggest() throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://rahulshettyacademy.com/AutomationPractice/");
        driver.findElement(By.xpath("//input[@id='autocomplete']")).sendKeys("United st");
        Thread.sleep(1400);
        driver.findElement(By.xpath("//div[contains(@id,'ui-id')]")).click(); //The XPATH is received is different from what mentioned here????????
        Thread.sleep(2000);
        driver.quit();
    }

    @AfterTest
    public void tearDown(){
        driver.quit();
    }




}
