package pageObjects.orangeHRM;

import org.openqa.selenium.WebDriver;
import pageObjects.orangeHRM.editNavigation.ContactDetailPageObject;
import pageObjects.orangeHRM.editNavigation.PersonalDetailPageObject;

public class PageGeneratorManager {

    public static LoginPageObject getLoginPage(WebDriver driver){
        return new LoginPageObject(driver);
    }

    public static DashboardPageObject getDashboardPage(WebDriver driver){
        return new DashboardPageObject(driver);
    }

    public static AddEmployeePageObject getAddEmployeePage(WebDriver driver){
        return new AddEmployeePageObject(driver);
    }

    public static EmployeeListPageObject getEmployeeListPage(WebDriver driver){
        return new EmployeeListPageObject(driver);
    }

    public static PersonalDetailPageObject getPersonalDetailPage(WebDriver driver){
        return new PersonalDetailPageObject(driver);
    }

    public static ContactDetailPageObject getContactDetailPage(WebDriver driver){
        return new ContactDetailPageObject(driver);
    }
}
