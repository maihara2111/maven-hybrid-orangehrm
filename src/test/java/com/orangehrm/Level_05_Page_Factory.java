package com.orangehrm;

//Import từ thư viện

import core.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageFactory.orangeHRM.*;

public class Level_05_Page_Factory extends BaseTest {

    @Parameters({"appUrl", "browser"})
    @BeforeClass
    public void beforeClass(String appURL, String browserName) {
        driver = getBrowserDriver(appURL, browserName);

        loginPage = new LoginPageObject(driver);

        adminUsername = "maihara";
        adminPassword = "M@idtn2111";
        employeeFirstName = "Mai";
        employeeLastName = "Hara";
    }

    @Test //Upper Camel Case + Underscore
    public void Employee_01_CreateNewEmployee() {
        //Action of Login
        loginPage.entertoUsernameTextbox(adminUsername);
        loginPage.entertoPasswordTextbox(adminPassword);
        loginPage.clicktoLoginButton();

        dashboardPage = new DashboardPageObject(driver);
        Assert.assertTrue(dashboardPage.isLoadingSpinnerDisappear());
        dashboardPage.sleepInSecond(2);

        dashboardPage.clickToPIMModule();
        employeeListPage = new EmployeeListPageObject(driver);
        Assert.assertTrue(employeeListPage.isLoadingSpinnerDisappear());

        employeeListPage.clickToAddEmployeeButton();
        addEmployeePage = new AddEmployeePageObject(driver);
        Assert.assertTrue(addEmployeePage.isLoadingSpinnerDisappear());

        addEmployeePage.enterToFirstNameTextbox(employeeFirstName);
        addEmployeePage.enterToLastNameTextbox(employeeLastName);
        employeeID = addEmployeePage.getEmployeeID();

        addEmployeePage.clickToSaveButton();
        Assert.assertTrue(addEmployeePage.isLoadingSpinnerDisappear());

        personalDetailPage = new PersonalDetailPageObject(driver);
        Assert.assertTrue(personalDetailPage.isLoadingSpinnerDisappear());
        personalDetailPage.sleepInSecond(2);

        Assert.assertEquals(personalDetailPage.getFirstNameTextboxValue(), employeeFirstName);
        Assert.assertEquals(personalDetailPage.getLastNameTextboxValue(), employeeLastName);
        Assert.assertEquals(personalDetailPage.getEmployeeIDTextboxValue(), employeeID);

    }

    //@Test
    public void Employee_02_EditEmployee() {
    }

    //@Test
    public void Login_03_Invalid_Password() {
    }

    //@Test
    public void Login_04_Valid_User_Password() {
    }

    @AfterClass
    public void afterClass() {
        closeBrowser();
    }

    private WebDriver driver;
    private LoginPageObject loginPage;
    private DashboardPageObject dashboardPage;
    private EmployeeListPageObject employeeListPage;
    private AddEmployeePageObject addEmployeePage;
    private PersonalDetailPageObject personalDetailPage;
    private String employeeID, adminUsername, adminPassword, employeeFirstName, employeeLastName;
}
