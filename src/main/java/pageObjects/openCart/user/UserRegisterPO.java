package pageObjects.openCart.user;

import core.BasePage;
import org.openqa.selenium.WebDriver;
import pageObjects.PageGenerator;
import pageUIs.openCart.user.UserRegisterPageUI;

public class UserRegisterPO extends BasePage {
    WebDriver driver;

    public UserRegisterPO(WebDriver driver) {
        this.driver = driver;
    }

    public UserHomePO clickContinueButton() {
        waitElementClickable(driver, UserRegisterPageUI.CONTINUE_BUTTON);
        clickToElement(driver, UserRegisterPageUI.CONTINUE_BUTTON);
        return PageGenerator.getPage(UserHomePO.class, driver);
    }

    public void enterToFirstName(String firstname) {
        waitElementVisible(driver, UserRegisterPageUI.FIRST_NAME_TEXTBOX);
        sendKeyToElement(driver, UserRegisterPageUI.FIRST_NAME_TEXTBOX, firstname);
    }

    public void enterToLastName(String lastname) {
        waitElementVisible(driver, UserRegisterPageUI.LAST_NAME_TEXTBOX);
        sendKeyToElement(driver, UserRegisterPageUI.LAST_NAME_TEXTBOX, lastname);
    }

    public void enterToPassword(String password) {
        waitElementVisible(driver, UserRegisterPageUI.PASSWORD_TEXTBOX);
        sendKeyToElement(driver, UserRegisterPageUI.PASSWORD_TEXTBOX, password);
    }

    public void enterToEmailAddress(String email) {
        waitElementVisible(driver, UserRegisterPageUI.EMAIL_TEXTBOX);
        sendKeyToElement(driver, UserRegisterPageUI.EMAIL_TEXTBOX, email);
    }

    public void acceptPrivacyCheckbox() {
        waitElementVisible(driver, UserRegisterPageUI.AGREE_CHECKBOX);
        checkToCheckbox(driver, UserRegisterPageUI.AGREE_CHECKBOX);
    }

    public boolean isSuccessMessageDisplayed() {
        waitElementVisible(driver, UserRegisterPageUI.CREATED_ACCOUNT_SUCCESS_MESSAGE);
        return isElementDisplayed(driver, UserRegisterPageUI.CREATED_ACCOUNT_SUCCESS_MESSAGE);
    }
}
