package pageFactory.orangeHRM;

import core.BasePageFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class AddEmployeePageObject extends BasePageFactory {
    private WebDriver driver;

    @CacheLookup
    @FindBy(name = "firstName")
    private WebElement firstNameTextbox;

    @CacheLookup
    @FindBy(name = "lastName")
    private WebElement lastNameTextbox;

    @CacheLookup
    @FindBy(xpath = "//label[text()='Employee Id']/parent::div/following-sibling::div/input")
    private WebElement employeeIDTextbox;

    @FindBy(xpath = "//button[contains(string(),'Save')]")
    private WebElement saveButton;

    @FindBy (xpath = "//div[@class='oxd-loading-spinner']")
    private List<WebElement> loadingSpinner;

    public AddEmployeePageObject(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void enterToFirstNameTextbox(String firstname) {
        waitElementVisible(driver, firstNameTextbox);
        sendKeyToElement(firstNameTextbox, firstname);
    }

    public void enterToLastNameTextbox(String lastname) {
        waitElementVisible(driver, lastNameTextbox);
        sendKeyToElement(lastNameTextbox, lastname);
    }

    public String getEmployeeID() {
        waitElementVisible(driver, employeeIDTextbox);
        return getElementDOMProperty(employeeIDTextbox, "value");
    }

    public void clickToSaveButton() {
        waitElementClickable(driver, saveButton);
        clickToElement(saveButton);
    }

    public boolean isLoadingSpinnerDisappear() {
        return waitListElementInvisible(driver, loadingSpinner);
    }
}
