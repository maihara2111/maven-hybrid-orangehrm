package ui.orangehrm.user;

//Import từ thư viện

import core.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Login_02_BasePage_II_Static {
    private WebDriver driver;
    private BasePage basePage;
    private String appUrl = "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login";

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        basePage = BasePage.getInstance();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().window().maximize();
    }

    @Test //Upper Camel Case + Underscore
    public void Login_01_Empty() {
        basePage.openPageUrl(driver, appUrl);

        basePage.sendKeyToElement(driver, "//input[@name='username']", "");
        basePage.sendKeyToElement(driver, "//input[@name='password']", "");
        basePage.clickToElement(driver, "//button[contains(@class, 'orangehrm-login-button')]");

        Assert.assertEquals(basePage.getElementText(driver, "//input[@name='username']/parent::div/following-sibling::span"), "Required");
        Assert.assertEquals(basePage.getElementText(driver, "//input[@name='password']/parent::div/following-sibling::span"), "Required");
    }

    @Test
    public void Login_02_Invalid_Username() {
        basePage.openPageUrl(driver, appUrl);

        basePage.sendKeyToElement(driver, "//input[@name='username']", "mai@gmail.com");
        basePage.sendKeyToElement(driver, "//input[@name='password']", "admin123");
        basePage.clickToElement(driver, "//button[contains(@class, 'orangehrm-login-button')]");

        Assert.assertEquals(basePage.getElementText(driver, "//div[@class='orangehrm-login-form']//p[contains(@class,'oxd-alert-content-text')]"),"Invalid credentials");
    }

    @Test
    public void Login_03_Invalid_Password() {
        basePage.openPageUrl(driver, appUrl);

        basePage.sendKeyToElement(driver, "//input[@name='username']", "Admin");
        basePage.sendKeyToElement(driver, "//input[@name='password']", "admin123MH");
        basePage.clickToElement(driver, "//button[contains(@class, 'orangehrm-login-button')]");

        Assert.assertEquals(basePage.getElementText(driver, "//div[@class='orangehrm-login-form']//p[contains(@class,'oxd-alert-content-text')]"),"Invalid credentials");
    }

    @Test
    public void Login_04_Valid_User_Password() {
        basePage.openPageUrl(driver, appUrl);

        basePage.sendKeyToElement(driver, "//input[@name='username']", "Admin");
        basePage.sendKeyToElement(driver, "//input[@name='password']", "admin123");
        basePage.clickToElement(driver, "//button[contains(@class, 'orangehrm-login-button')]");

        Assert.assertTrue(isAllLoadingSpinnerInvisible());

        Assert.assertEquals(basePage.getElementText(driver, "//div[@class='oxd-topbar-header-title']//h6"),"Dashboard");
    }

    public boolean isAllLoadingSpinnerInvisible() {
        return basePage.waitListElementInvisible(driver,"//div[@class='oxd-loading-spinner']");
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
