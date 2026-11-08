package javaSDET;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;

public class Topic_17_Soft_Assert {
    WebDriver driver;
    SoftAssert softAssert;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        softAssert = new SoftAssert();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        driver.get("http://live.techpanda.org/customer/account/login/");
    }

    @Test
    public void TC_01_Login_Empty_Email_Password() {
        System.out.println("Step 01 - Input to email textbox");
        driver.findElement(By.id("email")).sendKeys("");

        System.out.println("Step 02 - Input to password textbox");
        driver.findElement(By.id("pass")).sendKeys("");

        System.out.println("Step 03 - Click to Login button");
        driver.findElement(By.id("send2")).click();

        // First Pass (5)
        System.out.println("Step 05 - Verify error message displayed");
        softAssert.assertEquals(driver.findElement(By.id("advice-required-entry-pass")).getText(), "This is a required field.");

        //...

        // Second Fail (10)
        System.out.println("Step 10 - Verify error message displayed");
        softAssert.assertEquals(driver.findElement(By.id("advice-required-entry-email")).getText(), "This is a required field");


        // Pass (45)
        System.out.println("Step 45 - Verify .....");
        softAssert.assertTrue(driver.findElement(By.xpath("//button[@id='send2']")).isDisplayed());

        // Third Fail (50)
        System.out.println("Step 50 - Verify .....");
        softAssert.assertTrue(driver.findElement(By.xpath("//div[@id='header-account']//a[@title='My Account']")).isDisplayed());

        // Pass (65)
        System.out.println("Step 65 - Verify .....");
        softAssert.assertTrue(driver.findElement(By.xpath("//button[@id='send2']")).isDisplayed());

        // Fourth Fail (76)
        System.out.println("Step 76 - Verify .....");
        softAssert.assertTrue(driver.findElement(By.xpath("//div[@id='header-account']//a[@title='My Account']")).isDisplayed());

        softAssert.assertAll();
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}