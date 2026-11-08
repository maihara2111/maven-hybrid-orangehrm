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

public class Level_09_Switch_Url_Role extends BaseTest {

    @Parameters({"userUrl", "adminUrl", "browser"})
    @BeforeClass
    public void beforeClass(String userURL, String adminURL, String browserName) {
        this.userURL = userURL;
        this.adminURL = adminURL;

        adminUser = "maihara";
        adminPassword = "P@ssword2111";
        userFirstname = "Mai";
        userLastname = "Hara";
        userEmailAddress = "maicustomer" + getRandomNumber() + "@mail.com";
        userPassword = "P@ssword";

        //Open browser lên sẽ là trang user
        driver = getBrowserDriver(userURL, browserName);
        userHomePage = PageGenerator.getPage(UserHomePO.class, driver);
    }

    @Test(enabled = false)
    public void OpenCart_01_Login_And_Logout() {
        //Giống hành vi User
        userHomePage.clickToMyAccountAtFooter();
        userLoginPage = PageGenerator.getPage(UserLoginPO.class, driver);

        userRegisterPage = userLoginPage.clickToContinueButton();

        userRegisterPage.enterToFirstName(userFirstname);
        userRegisterPage.enterToLastName(userLastname);
        userRegisterPage.enterToEmailAddress(userEmailAddress);
        userRegisterPage.enterToPassword(userPassword);
        userRegisterPage.acceptPrivacyCheckbox();
        userRegisterPage.clickContinueButton();

        Assert.assertTrue(userRegisterPage.isSuccessMessageDisplayed());

        userHomePage = userRegisterPage.clickToLogoutLinkAtUserSite(driver);

        userHomePage.openAdminSite(driver, adminURL);
        adminLoginPage = PageGenerator.getPage(AdminLoginPO.class, driver);

        adminLoginPage.enterToUsername(adminUser);
        adminLoginPage.enterToPassword(adminPassword);
        adminDashboardPage = adminLoginPage.clickToLoginButton();

        adminCustomerPage = adminDashboardPage.openCustomerPage();

        adminLoginPage = adminCustomerPage.clickToLogoutLinkAdminSite(driver);

        //Admin >> User
        userHomePage = adminLoginPage.openUserSite(driver, userURL);

        userHomePage.clickToMyAccountAtFooter();
        userLoginPage = PageGenerator.getPage(UserLoginPO.class, driver);

        userLoginPage.enterToEmailAddressTextbox(userEmailAddress);
        userLoginPage.enterToEmailPasswordTextbox(userPassword);
        userMyAccountPage = userLoginPage.clickToLoginButton();

        Assert.assertTrue(userMyAccountPage.isMyAccountPageDisplayed());

        //User >> Admin
        userMyAccountPage.openAdminSite(driver, adminURL);
        adminLoginPage = PageGenerator.getPage(AdminLoginPO.class, driver);

        adminLoginPage.enterToUsername(adminUser);
        adminLoginPage.enterToPassword(adminPassword);
        adminDashboardPage = adminLoginPage.clickToLoginButton();


        //Admin >> User
        userHomePage = adminDashboardPage.openUserSite(driver, userURL);

        userHomePage = adminLoginPage.openUserSite(driver, userURL);

    }

    @Test(enabled = false)
    public void OpenCart_02_Logging_Without_Logout() {
        //Tiện cho việc Develop/Testing
        //User dky tai khoan rồi mua hàng
        //User does not logout
        //Move to Admin page >> login 1 lần
        //Admin verify đơn hàng
        //Admin ko logout
        //Chuyển qua trang User
        //Chuyển qua trang Admin

        userHomePage.clickToMyAccountAtFooter();
        userLoginPage = PageGenerator.getPage(UserLoginPO.class, driver);

        userRegisterPage = userLoginPage.clickToContinueButton();

        userRegisterPage.enterToFirstName(userFirstname);
        userRegisterPage.enterToLastName(userLastname);
        userRegisterPage.enterToEmailAddress(userEmailAddress);
        userRegisterPage.enterToPassword(userPassword);
        userRegisterPage.acceptPrivacyCheckbox();
        userRegisterPage.clickContinueButton();

        Assert.assertTrue(userRegisterPage.isSuccessMessageDisplayed());

        //Ko hề logout nên nó vẫn đang ở trang Register
        //User >> Admin
        userRegisterPage.openAdminSite(driver, adminURL);
        adminLoginPage = PageGenerator.getPage(AdminLoginPO.class, driver);

        //Login
        adminLoginPage.enterToUsername(adminUser);
        adminLoginPage.enterToPassword(adminPassword);
        adminDashboardPage = adminLoginPage.clickToLoginButton();

        adminCustomerPage = adminDashboardPage.openCustomerPage();

        //Ko h logout nên n vẫn đang ở trang Customer
        //Admin >> User
        userHomePage = adminCustomerPage.openUserSite(driver, userURL);

        userHomePage.clickToMyAccountAtFooter();
        userMyAccountPage = PageGenerator.getPage(UserMyAccountPO.class, driver);

        Assert.assertTrue(userMyAccountPage.isMyAccountPageDisplayed());

        //User >> Admin
        userMyAccountPage.openAdminSite(driver, adminURL);
        adminDashboardPage = PageGenerator.getPage(AdminDashboardPO.class, driver);

        //Có verify trang Dashboard hiển thị thì sẽ lỗi
        Assert.assertTrue(adminDashboardPage.isDasboardHeaderDisplayed()); //Failed vì đang có bug
    }

    @Test
    public void OpenCart_03_Multiple_Tab() {
        userHomePage.clickToMyAccountAtFooter();
        userLoginPage = PageGenerator.getPage(UserLoginPO.class, driver);

        userRegisterPage = userLoginPage.clickToContinueButton();

        userRegisterPage.enterToFirstName(userFirstname);
        userRegisterPage.enterToLastName(userLastname);
        userRegisterPage.enterToEmailAddress(userEmailAddress);
        userRegisterPage.enterToPassword(userPassword);
        userRegisterPage.acceptPrivacyCheckbox();
        userRegisterPage.clickContinueButton();

        Assert.assertTrue(userRegisterPage.isSuccessMessageDisplayed());
        userWindowID = userRegisterPage.getCurrentWindowID(driver);

        //Ko hề logout nên nó vẫn đang ở trang Register
        userRegisterPage.openUrlByNewTab(driver, adminURL);
        adminLoginPage = PageGenerator.getPage(AdminLoginPO.class, driver);

        //Login
        adminLoginPage.enterToUsername(adminUser);
        adminLoginPage.enterToPassword(adminPassword);
        adminDashboardPage = adminLoginPage.clickToLoginButton();

        adminCustomerPage = adminDashboardPage.openCustomerPage();
        adminWindowID = adminCustomerPage.getCurrentWindowID(driver);

        //Admin >> User
        adminCustomerPage.switchToWindowByID(driver, adminWindowID);

        userRegisterPage = PageGenerator.getPage(UserRegisterPO.class, driver);

        userHomePage = userRegisterPage.openHomeLogo(driver);

        userHomePage = adminCustomerPage.openUserSite(driver, userURL);

        userHomePage.clickToMyAccountAtFooter();
        userMyAccountPage = PageGenerator.getPage(UserMyAccountPO.class, driver);

        Assert.assertTrue(userMyAccountPage.isMyAccountPageDisplayed());

        //User >> Admin
        userMyAccountPage.switchToWindowByID(driver, userWindowID);
        adminCustomerPage = PageGenerator.getPage(AdminCustomerPO.class, driver);

        Assert.assertTrue(adminCustomerPage.isCustomerHeaderDisplayed());
    }

    @AfterClass
    public void afterClass() {
        closeBrowser();
    }

    //Non-Static
    private WebDriver driver;
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
