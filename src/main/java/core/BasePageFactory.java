package core;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Set;

public class BasePageFactory {
    public static BasePageFactory getInstance(){
        return new BasePageFactory();
    }

    public void openPageUrl(WebDriver driver, String pageUrl) {
        driver.get(pageUrl);
    }

    public String getPageTitle(WebDriver driver) {
        return driver.getTitle();
    }

    public String getPageUrl(WebDriver driver) {
        return driver.getCurrentUrl();
    }

    public String getPageSource(WebDriver driver) {
        return driver.getPageSource();
    }

    public void backToPage(WebDriver driver) {
        driver.navigate().back();
    }

    public void forwardToPage(WebDriver driver) {
        driver.navigate().forward();
    }

    public void refreshToPage(WebDriver driver) {
        driver.navigate().refresh();
    }

    private Alert waitAlertPresence(WebDriver driver) {
        return new WebDriverWait(driver, Duration.ofSeconds(LONG_TIMEOUT))
                .until(ExpectedConditions.alertIsPresent());
    }

    public void acceptToAlert(WebDriver driver) {
        waitAlertPresence(driver).accept();
    }

    public void cancelToAlert(WebDriver driver) {
        waitAlertPresence(driver).dismiss();
    }

    public void sendKeyToAlert(WebDriver driver, String keysToSend) {
        waitAlertPresence(driver).sendKeys(keysToSend);
    }

    public void getAlertText(WebDriver driver) {
        waitAlertPresence(driver).getText();
    }

    public void sleepInSecond(long timeInSecond) {
        try {
            Thread.sleep(timeInSecond * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    //Dùng cho đúng duy nhất 2 windows and tab
    //Kiem tra cái id trước
    //Khác vs parent >> thì mới switch
    public void switchToWindowByID(WebDriver driver, String windowID) {
        //Get ra all tab/Window đang có
        Set<String> allWindows = driver.getWindowHandles();

        //Dùng vòng lặp để duyệt qua từng Window
        for (String id : allWindows) {
            //Nếu như id nào mà khác vs window id
            if (!id.equals(windowID)) {
                //Switch vào id đó
                driver.switchTo().window(id);
            }
        }
    }

    //Dùng cho 2 hoặc nhiều hơn 2 windows/tab
    //Switch vào từng window trước
    //Get ra title của window đó
    //Kiem tra với title mong muốn
    //Nếu như mà = thì stop ko kiem tra tiếp nữa
    public void switchToWindowByTitle(WebDriver driver, String expectedTitle) {
        //Get ra tất cả các tab/Window đang có
        Set<String> allWindows = driver.getWindowHandles();

        //Dùng vòng lặp để duyệt qua từng window
        for (String id : allWindows) {
            //Switch trước vào từng window
            driver.switchTo().window(id);

            //Lấy ra page Title đang active
            String windowTitle = driver.getTitle();

            //Nếu như mà =  thì stop ko kiem tra tiếp nữa
            if (windowTitle.equals(expectedTitle)) {
                break;
            }
        }
        sleepInSecond(2);
    }

    //Dùng cho từ 2 windows trở lên
    public void switchToWindowBycontainTitle(WebDriver driver, String expectedTitle) {
        //Get ra tất cả các tab/Window đang có
        Set<String> allWindows = driver.getWindowHandles();

        //Dùng vòng lặp để duyệt qua từng window
        for (String window : allWindows) {

            //Switch trước vào từng window
            driver.switchTo().window(window);

            //Lấy ra page title đang active
            //Kiểm tra nếu Page title hiện tại bằng với title mong đợi
            if (driver.getTitle().contains(expectedTitle)) {
                break;
            }
        }
        sleepInSecond(2);
    }

    //Close tất cả các Window/ Tab ngoại trừ trang gốc
    public void closeAllWindowsWithoutParent(WebDriver driver, String parentID) {
        //Get ra tất cả các tab/ window đang có
        Set<String> allWindows = driver.getWindowHandles();

        //Dùng vòng lặp để duyệt qua từng Window
        for (String id : allWindows) {

            //Nếu ID nào khác voi ID truyền vào
            if (!id.equals((parentID))) {

                //Thì switch qua
                driver.switchTo().window(id);

                //Close tab đó đi
                driver.close();
            }
        }
        driver.switchTo().window(parentID);
    }

    //Thao tác vs Element
    //PageFactory là define kiểu WebElement

    public void clickToElement(WebElement element) {
        element.click();
    }

    public void sendKeyToElement(WebElement element, String keyToSend) {
        element.clear();
        element.sendKeys(keyToSend);
    }

    public String getElementDOMAttribute(WebElement element, String attributeName) {
        return element.getDomAttribute(attributeName);
    }

    public String getElementDOMProperty(WebElement element, String propertyName) {
        return element.getDomProperty(propertyName);
    }

    public String getElementText(WebElement element) {
        return element.getText();
    }


    public boolean isElementDisplayed(WebElement element) {
        return element.isDisplayed();
    }

    public boolean isElementSelected(WebElement element) {
        return element.isSelected();
    }


    public WebElement waitElementVisible(WebDriver driver, WebElement element) {
        return new WebDriverWait(driver, Duration.ofSeconds(LONG_TIMEOUT))
                .until(ExpectedConditions.visibilityOf(element));
    }

    public List<WebElement> waitListElementVisible(WebDriver driver, List<WebElement> elements) {
        return new WebDriverWait(driver, Duration.ofSeconds(LONG_TIMEOUT))
                .until(ExpectedConditions.visibilityOfAllElements(elements));
    }

    public boolean waitElementSelected(WebDriver driver, WebElement element) {
       return new WebDriverWait(driver, Duration.ofSeconds(LONG_TIMEOUT))
                .until(ExpectedConditions.elementToBeSelected(element));
    }

    public WebElement waitElementClickable(WebDriver driver, WebElement element) {
        return new WebDriverWait(driver, Duration.ofSeconds(LONG_TIMEOUT))
                .until(ExpectedConditions.elementToBeClickable(element));
    }

    public boolean waitElementInvisible(WebDriver driver, WebElement element) {
       return new WebDriverWait(driver, Duration.ofSeconds(LONG_TIMEOUT))
                .until(ExpectedConditions.invisibilityOf(element));
    }

    public boolean waitListElementInvisible(WebDriver driver, List<WebElement> elements) {
       return new WebDriverWait(driver, Duration.ofSeconds(LONG_TIMEOUT))
                .until(ExpectedConditions.invisibilityOfAllElements(elements));
    }

    private static final int LONG_TIMEOUT = 30;
    private static final int SHORT_TIMEOUT = 10;

}
