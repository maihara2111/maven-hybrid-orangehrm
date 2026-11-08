package com.orangehrm;

//Import từ thư viện

import core.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.PageGenerator;
import pageObjects.orangeHRM.AddEmployeePageObject;
import pageObjects.orangeHRM.DashboardPageObject;
import pageObjects.orangeHRM.EmployeeListPageObject;
import pageObjects.orangeHRM.LoginPageObject;
import pageObjects.orangeHRM.editNavigation.ContactDetailPageObject;
import pageObjects.orangeHRM.editNavigation.DependentsPageObject;
import pageObjects.orangeHRM.editNavigation.JobPageObject;
import pageObjects.orangeHRM.editNavigation.PersonalDetailPageObject;

public class Level_11_By_Locator extends BaseTest {

    @Parameters({"appUrl", "browser"})
    @BeforeClass
    public void beforeClass(String appURL, String browserName) {
        driver = getBrowserDriver(appURL, browserName);

        loginPage = PageGenerator.getPage(LoginPageObject.class, driver);

        adminUsername = "maihara";
        adminPassword = "M@idtn2111";
        employeeFirstName = "Mai";
        employeeLastName = "Hara";
    }

    @Test//Upper Camel Case + Underscore
    public void Employee_01_CreateNewEmployee() {
        //Action of Login
        loginPage.entertoUsernameTextbox(adminUsername);
        loginPage.entertoPasswordTextbox(adminPassword);

        dashboardPage = loginPage.clicktoLoginButton();

        Assert.assertTrue(dashboardPage.isLoadingSpinnerDisappear(driver));
        dashboardPage.sleepInSecond(2);

        employeeListPage = dashboardPage.clickToPIMModule();

        Assert.assertTrue(employeeListPage.isLoadingSpinnerDisappear(driver));

        addEmployeePage = employeeListPage.clickToAddEmployeeButton();

        Assert.assertTrue(addEmployeePage.isLoadingSpinnerDisappear(driver));

        addEmployeePage.enterToFirstNameTextbox(employeeFirstName);
        addEmployeePage.enterToLastNameTextbox(employeeLastName);
        employeeID = addEmployeePage.getEmployeeID();

        personalDetailPage = addEmployeePage.clickToSaveButton();

        Assert.assertTrue(personalDetailPage.isLoadingSpinnerDisappear(driver));
        personalDetailPage.sleepInSecond(2);

        Assert.assertEquals(personalDetailPage.getFirstNameTextboxValue(), employeeFirstName);
        Assert.assertEquals(personalDetailPage.getLastNameTextboxValue(), employeeLastName);
        Assert.assertEquals(personalDetailPage.getEmployeeIDTextboxValue(), employeeID);

    }

    @Test
    public void Employee_02_Dynamic_Page() {
        //Từ Personal qua Contact
        contactDetailPage = (ContactDetailPageObject) personalDetailPage.openEditNavigatorPageByName("Contact Details");

        //Từ Contact qua Job
        jobPage = (JobPageObject) contactDetailPage.openEditNavigatorPageByName("Job");

        //Từ Job qua Dependent
        dependentsPage = (DependentsPageObject) jobPage.openEditNavigatorPageByName("Dependents");

        //Từ Dependent qua Personal
        personalDetailPage = (PersonalDetailPageObject) dependentsPage.openEditNavigatorPageByName("Personal Details");

        //Từ Personal qua Job
        jobPage = (JobPageObject) personalDetailPage.openEditNavigatorPageByName("Job");

        contactDetailPage = (ContactDetailPageObject) jobPage.openEditNavigatorPageByName("Contact Details");

        dependentsPage = (DependentsPageObject) contactDetailPage.openEditNavigatorPageByName("Dependents");

    }

    @Test
    public void Employee_03_Dynamic_Page() {
        //Từ Personal qua Contact
        personalDetailPage.openEditNavigatorByName("Contact Details");
        contactDetailPage = PageGenerator.getPage(ContactDetailPageObject.class, driver);

        //Từ Contact qua Job
        contactDetailPage.openEditNavigatorByName("Job");
        jobPage = PageGenerator.getPage(JobPageObject.class, driver);

        //Từ Job qua Dependent
        jobPage.openEditNavigatorByName("Dependents");
        dependentsPage = PageGenerator.getPage(DependentsPageObject.class, driver);

        //Từ Dependent qua Personal
        dependentsPage.openEditNavigatorByName("Personal Details");
        personalDetailPage = PageGenerator.getPage(PersonalDetailPageObject.class, driver);

        //Từ Personal qua Job
        personalDetailPage.openEditNavigatorByName("Job");
        jobPage = PageGenerator.getPage(JobPageObject.class, driver);

        jobPage.openEditNavigatorByName("Contact Details");
        contactDetailPage = PageGenerator.getPage(ContactDetailPageObject.class, driver);

        contactDetailPage.openEditNavigatorByName("Dependents");
        dependentsPage = PageGenerator.getPage(DependentsPageObject.class, driver);
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
    private ContactDetailPageObject contactDetailPage;
    private DependentsPageObject dependentsPage;
    private JobPageObject jobPage;
    private String employeeID, adminUsername, adminPassword, employeeFirstName, employeeLastName;
}
