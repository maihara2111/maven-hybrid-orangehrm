package pageObjects.orangeHRM;

import core.BasePage;
import org.openqa.selenium.WebDriver;
import pageObjects.PageGenerator;
import pageObjects.orangeHRM.editNavigation.PersonalDetailPageObject;
import pageUIs.orangeHRM.AddEmployeePageUI;

public class AddEmployeePageObject extends BasePage {
    private WebDriver driver;

    public AddEmployeePageObject(WebDriver driver) {
        this.driver = driver;
    }

    public void enterToFirstNameTextbox(String firstname) {
        waitElementVisible(driver, AddEmployeePageUI.FIRST_NAME_TEXTBOX);
        sendKeyToElement(driver, AddEmployeePageUI.FIRST_NAME_TEXTBOX, firstname);
    }

    public void enterToLastNameTextbox(String lastname) {
        waitElementVisible(driver, AddEmployeePageUI.LAST_NAME_TEXTBOX);
        sendKeyToElement(driver, AddEmployeePageUI.LAST_NAME_TEXTBOX, lastname);
    }

    public String getEmployeeID() {
        waitElementVisible(driver, AddEmployeePageUI.EMPLOYEE_ID_TEXTBOX);
        return getElementDOMProperty(driver, AddEmployeePageUI.EMPLOYEE_ID_TEXTBOX, "value");
    }

    public PersonalDetailPageObject clickToSaveButton() {
        waitElementClickable(driver, AddEmployeePageUI.SAVE_BUTTON);
        waitListElementInvisible(driver, AddEmployeePageUI.SPINNER_ICON);
        clickToElement(driver, AddEmployeePageUI.SAVE_BUTTON);
        return PageGenerator.getPage(PersonalDetailPageObject.class, driver);
    }
}
