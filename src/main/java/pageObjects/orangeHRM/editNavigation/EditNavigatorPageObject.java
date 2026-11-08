package pageObjects.orangeHRM.editNavigation;

import core.BasePage;
import org.openqa.selenium.WebDriver;
import pageObjects.PageGenerator;
import pageUIs.orangeHRM.editNavigation.EditNavigatorPageUI;

public class EditNavigatorPageObject extends BasePage {
     WebDriver driver;

     public EditNavigatorPageObject(WebDriver driver){
         this.driver = driver;
     }

     //9 pages nằm trong Edit Employee
     //Cách 1 - 9 Page là có đúng duy nhất 9 hàm và ko trùng lặp ở bất kì 1 vị trí nào
     public JobPageObject openJobPage() {
         waitElementClickable(driver, EditNavigatorPageUI.JOB_LINK);
         clickToElement(driver, EditNavigatorPageUI.JOB_LINK);
         return PageGenerator.getPage(JobPageObject.class, driver);
     }

    public ContactDetailPageObject openContactDetailPage(){
        waitElementClickable(driver, EditNavigatorPageUI.CONTACT_DETAILS_LINK);
        clickToElement(driver, EditNavigatorPageUI.CONTACT_DETAILS_LINK);
        return PageGenerator.getPage(ContactDetailPageObject.class, driver);
    }

    public DependentsPageObject openDependentsPage() {
        waitElementClickable(driver, EditNavigatorPageUI.DEPENDENTS_LINK);
        clickToElement(driver, EditNavigatorPageUI.DEPENDENTS_LINK);
        return PageGenerator.getPage(DependentsPageObject.class, driver);
    }

    public PersonalDetailPageObject openPersonalDetailPage() {
        waitElementClickable(driver, EditNavigatorPageUI.PERSONAL_DETAILS_LINK);
        clickToElement(driver, EditNavigatorPageUI.PERSONAL_DETAILS_LINK);
        return PageGenerator.getPage(PersonalDetailPageObject.class, driver);
    }

    //Cách 2 - 1 hàm cho 10 pages
    public EditNavigatorPageObject openEditNavigatorPageByName(String pageName){
         waitElementClickable(driver, EditNavigatorPageUI.DYNAMIC_LINK_BY_PAGE_NAME, pageName);
         clickToElement(driver, EditNavigatorPageUI.DYNAMIC_LINK_BY_PAGE_NAME, pageName);

         switch (pageName){
             case "Personal Details":
             return PageGenerator.getPage(PersonalDetailPageObject.class, driver);
             case "Dependents":
             return PageGenerator.getPage(DependentsPageObject.class, driver);
             case "Contact Details":
             return PageGenerator.getPage(ContactDetailPageObject.class, driver);
             case "Job":
             return PageGenerator.getPage(JobPageObject.class, driver);
             default:
                 throw new IllegalArgumentException("Page name is not valid: " + pageName);
         }
    }

    //Cách 3 - 1 hàm cho 10 pages ko cần switch case và return
    public void openEditNavigatorByName(String pageName) {
        waitElementClickable(driver, EditNavigatorPageUI.DYNAMIC_LINK_BY_PAGE_NAME, pageName);
        clickToElement(driver, EditNavigatorPageUI.DYNAMIC_LINK_BY_PAGE_NAME, pageName);
    }

}
