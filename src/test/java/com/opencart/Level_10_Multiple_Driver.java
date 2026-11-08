package com.opencart;

//Import từ thư viện

import core.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.PageGenerator;
import pageObjects.openCart.admin.AdminCustomerPO;
import pageObjects.openCart.admin.AdminDashboardPO;
import pageObjects.openCart.admin.AdminLoginPO;
import pageObjects.openCart.user.UserHomePO;
import pageObjects.openCart.user.UserLoginPO;
import pageObjects.openCart.user.UserMyAccountPO;
import pageObjects.openCart.user.UserRegisterPO;

public class Level_10_Multiple_Driver extends BaseTest {

    @Parameters({"userUrl", "adminUrl", "browser"})
    @BeforeClass
    public void beforeClass(String userURL, String adminURL, String browserName) {
        this.userURL = userURL;
        this.adminURL = adminURL;
        userDriver = getBrowserDriver(userURL, browserName);

        userHomePage = PageGenerator.getPage(UserHomePO.class, userDriver);
        adminUser = "maihara";
        adminPassword = "P@ssword2111";
        userFirstname = "Mai";
        userLastname = "Hara";
        userEmailAddress = "maicustomer" + getRandomNumber() + "@mail.com";
        userPassword = "P@ssword";

        //Mở browser lên sẽ là trang user
        userDriver = getBrowserDriver(userURL, browserName);
        userHomePage = PageGenerator.getPage(UserHomePO.class, userDriver);

        adminDriver = getBrowserDriver(adminURL, browserName);
        adminLoginPage = PageGenerator.getPage(AdminLoginPO.class, adminDriver);
    }

    @Test
    public void OpenCart_02_Logging_Without_Logout() {
        //Firefox của user
        userHomePage.clickToMyAccountAtFooter();
        userLoginPage = PageGenerator.getPage(UserLoginPO.class, userDriver);

        userRegisterPage = userLoginPage.clickToContinueButton();

        userRegisterPage.enterToFirstName(userFirstname);
        userRegisterPage.enterToLastName(userLastname);
        userRegisterPage.enterToEmailAddress(userEmailAddress);
        userRegisterPage.enterToPassword(userPassword);
        userRegisterPage.acceptPrivacyCheckbox();
        userRegisterPage.clickContinueButton();

        Assert.assertTrue(userRegisterPage.isSuccessMessageDisplayed());

        adminLoginPage = PageGenerator.getPage(AdminLoginPO.class, adminDriver);

        //Firefox của admin chạy tiếp
        adminLoginPage.enterToUsername(adminUser);
        adminLoginPage.enterToPassword(adminPassword);
        adminDashboardPage = adminLoginPage.clickToLoginButton();

        Assert.assertTrue(adminDashboardPage.isDasboardHeaderDisplayed());

        adminCustomerPage = adminDashboardPage.openCustomerPage();
        Assert.assertTrue(adminCustomerPage.isCustomerHeaderDisplayed());

        //Firefox của user chạy tiếp
        userHomePage.clickToMyAccountAtFooter();
        userMyAccountPage = PageGenerator.getPage(UserMyAccountPO.class, userDriver);

        Assert.assertTrue(userMyAccountPage.isMyAccountPageDisplayed());
    }

    @AfterClass
    public void afterClass() {
        closeBrowser(userDriver);
        closeBrowser(adminDriver);
    }

    //Non-Static
    private WebDriver userDriver;
    private WebDriver adminDriver;
    private String userURL, adminURL;
    private AdminCustomerPO adminCustomerPage;
    private AdminLoginPO adminLoginPage;
    private AdminDashboardPO adminDashboardPage;
    private UserHomePO userHomePage;
    private UserRegisterPO userRegisterPage;
    private UserLoginPO userLoginPage;
    private UserMyAccountPO userMyAccountPage;
    private String adminUser, adminPassword;
    private String userWindowID, adminWindowID;
    private String userFirstname, userLastname, userEmailAddress, userPassword;
}
