package pageObjects.gofile;

import core.BasePage;
import org.openqa.selenium.WebDriver;
import pageUIs.gofile.HomePageUI;

public class HomePageObject extends BasePage {
    private WebDriver driver;

    public HomePageObject(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isLoadingIconDisappear() {
        return waitListElementInvisible(driver, HomePageUI.LOADING_ICON);
    }

    public boolean isProgressBarIconDisappear() {
        return waitListElementInvisible(driver, HomePageUI.PROGRESS_BAR_ICON);
    }

    public String getSuccessLink() {
        waitElementVisible(driver, HomePageUI.SUCCESS_CARD_LINK);
        return getElementText(driver, HomePageUI.SUCCESS_CARD_LINK);
    }

    public boolean isFileUploadedSuccess(String fileName) {
        waitElementVisible(driver, HomePageUI.UPLOADED_FILE_NAME, fileName);
        return isElementDisplayed(driver, HomePageUI.UPLOADED_FILE_NAME, fileName);
    }
}
