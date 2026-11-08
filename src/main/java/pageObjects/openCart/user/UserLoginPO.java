package pageObjects.openCart.user;

import core.BasePage;
import org.openqa.selenium.WebDriver;
import pageObjects.PageGenerator;
import pageUIs.openCart.user.UserLoginPageUI;

public class UserLoginPO extends BasePage {
    WebDriver driver;

    public UserLoginPO(WebDriver driver) {
        this.driver = driver;
    }

    public UserRegisterPO clickToContinueButton() {
        waitElementClickable(driver, UserLoginPageUI.NEW_CUSTOMER_CONTINUE_BUTTON);
        clickToElement(driver, UserLoginPageUI.NEW_CUSTOMER_CONTINUE_BUTTON);
        return PageGenerator.getPage(UserRegisterPO.class, driver);
    }

    public void enterToEmailAddressTextbox(String emailAddress) {
        waitElementVisible(driver, UserLoginPageUI.EMAIL_ADDRESS_TEXTBOX);
        sendKeyToElement(driver, UserLoginPageUI.EMAIL_ADDRESS_TEXTBOX, emailAddress);
    }

    public void enterToEmailPasswordTextbox(String password) {
        waitElementVisible(driver, UserLoginPageUI.PASSWORD_TEXTBOX);
        sendKeyToElement(driver, UserLoginPageUI.PASSWORD_TEXTBOX, password);
    }

    public UserMyAccountPO clickToLoginButton() {
        waitElementClickable(driver, UserLoginPageUI.LOGIN_BUTTON);
        clickToElement(driver, UserLoginPageUI.LOGIN_BUTTON);
        return PageGenerator.getPage(UserMyAccountPO.class, driver);
    }
}
