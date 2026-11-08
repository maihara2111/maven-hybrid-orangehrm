package pageFactory.orangeHRM;

import core.BasePageFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class LoginPageObject extends BasePageFactory {
    private WebDriver driver;

    @CacheLookup
    //Define các Locator/Element
    @FindBy(how = How.XPATH, using = "//input[@name='username']")
    private WebElement usernameTextbox;

    @CacheLookup
    @FindBy(xpath = "//input[@name='password']")
    private WebElement passwordTextbox;

    @CacheLookup
    @FindBy(xpath = "//button[contains(@class,'orangehrm-login-button')]")
    private WebElement loginButton;

    public LoginPageObject(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    //Bắt buộc các Locator/ Element là kiểu dữ liệu WebElement
    //Sự thay đổi về common Class BasePage

    public void entertoUsernameTextbox(String username) {
        //Ráp Action + UI với nhau
        waitElementVisible(driver, usernameTextbox);
        sendKeyToElement(usernameTextbox, username);
    }

    public void entertoPasswordTextbox(String password) {
        waitElementVisible(driver, passwordTextbox);
        sendKeyToElement(passwordTextbox, password);
    }

    public void clicktoLoginButton() {
        waitElementClickable(driver, loginButton);
        clickToElement(loginButton);
    }
}
