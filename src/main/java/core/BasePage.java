package core;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.swing.*;
import java.time.Duration;
import java.util.List;
import java.util.Set;

public class BasePage {
    //1 - Access Modifier:
    // public: all các class trong cùng/ khác package đều sử dụng đc các hàm này
    // Chỉ muốn cho class nào kế thừa mới dùng thôi >> ko dùng public
    // protected: chỉ class nào kế thừa mới dùng được >> sẽ dùng
    // private: chỉ cho các hàm trong cùng class này sử dụng
    // default: chì cho các class trong cùng package sử dụng >> ko dùng default
    // các class trong cùng package pageObjects ko dùng được

    //2 - Kiểu dữ liệu của hàm (Data type): void/ int/ string/ boolean/ WebElement/ List<WebElement>/,,,
    //    Nó sẽ liên quan đến cái chức năng mình viết trong thân hàm
    //    Dùng hàm nào của Selenium thì nó trả về cái gì >> Define kiểu dữ liệu của hàm tương ứng

    //3 - Tên hàm: Đặt tên có nghĩa theo chức năng đang cần viết
    //    Convention tuân theo chuẩn của từng ngôn ngữ lập trình (Java)
    //    lowerCamelCase: từ đầu tiên viết thường - chữ cái đầu tiên của các từ tiếp theo sẻ viết hoa

    //4 - Có tham số hay ko (tuỳ vào chức năng cần viết)
    // Dùng hàm nào của Selenium thì

    //5 - Kiểu dữ liệu trả về cho hàm
    //    Nếu như có return dữ liệu thì sẽ khớp vs kiểu dữ liệu ở số 2
    //    Nếu như có return thì nó là cái step cuối cùng

    //Hàm static có nhiệm vụ lấy ra instance của chính class naỳ
    //1 biến static/hàm static có thể gọi ra truc tiếp từ phạm vi class
    public static BasePage getInstance(){
        return new BasePage();
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

    private By getByXpath(String locator) {
        return By.xpath(locator);
    }

    private WebElement getWebElement(WebDriver driver, String locator) {
        return driver.findElement(getByXpath(locator));
    }

    private List<WebElement> getListElement(WebDriver driver, String locator) {
        return driver.findElements(getByXpath(locator));
    }

    public void clickToElement(WebDriver driver, String locator) {
        getWebElement(driver, locator).click();
    }

    public void sendKeyToElement(WebDriver driver, String locator, String keyToSend) {
        getWebElement(driver, locator).sendKeys(keyToSend);
    }

    public void selectItemInDropdown(WebDriver driver, String locator, String valueItem) {
        new Select(getWebElement(driver, locator))
                .selectByVisibleText(valueItem);
    }

    public String getSelectItemInDropdown(WebDriver driver, String locator) {
        return new Select(getWebElement(driver, locator))
                .getFirstSelectedOption().getText();
    }

    public boolean isDropdownMultiple(WebDriver driver, String locator) {
        return new Select(getWebElement(driver, locator)).isMultiple();
    }

    public void selectItemInSelectableCustomDropdown(WebDriver driver, String parentLocator, String childLocator, String textItem) {
        // Click 1 element cho xổ all items in dropdown -> Parent element
        clickToElement(driver, parentLocator);
        sleepInSecond(3);

        new WebDriverWait(driver, Duration.ofSeconds(LONG_TIMEOUT)).until(ExpectedConditions
                .presenceOfAllElementsLocatedBy(By.cssSelector(childLocator)));

        //- wait for all items load successfully -> Child element
        //Lấy all items lưu vào list element
        List<WebElement> allItems = getListElement(driver, childLocator);

        //Duyệt qua từng item
        for (WebElement item : allItems) {
            //kiểm tra điều kiện: nếu text của item lấy ra bằng với mong đợi
            if (item.getText().trim().equals(textItem)) {
                //   + Item cần chọn nó hiển thị -> click vào item đó luôn
                //  + Item cần chọn ko hiển thị (ẩn bên dưới)
                //   -> Scroll đến item đó -> Ckick vào item
                if (!item.isDisplayed()) {
                    JavascriptExecutor jsExecutor;
                    jsExecutor = (JavascriptExecutor) driver;
                    jsExecutor.executeScript("arguments[0].scrollIntoView(true);", item);
                    sleepInSecond(1);
                }
                item.click();
                sleepInSecond(1);
                break;
            }
        }
    }

    public String getElementDOMAttribute(WebDriver driver, String locator, String attributeName) {
        return getWebElement(driver, locator).getDomAttribute(attributeName);
    }

    public String getElementDOMProperty(WebDriver driver, String locator, String propertyName) {
        return getWebElement(driver, locator).getDomProperty(propertyName);
    }

    public String getElementText(WebDriver driver, String locator) {
        return getWebElement(driver, locator).getText();
    }

    public String getElementCss(WebDriver driver, String locator, String propertyName) {
        return getWebElement(driver, locator).getCssValue(propertyName);
    }

    public String getHexaByRGBA(String rgbaColor) {
        return Color.fromString(rgbaColor).asHex().toUpperCase();
    }

    public int getListElementNumber(WebDriver driver, String locator) {
        return getListElement(driver, locator).size();
    }

    public void checkToCheckbox(WebDriver driver, String locator) {
        if (!isElementSelected(driver, locator)) {
            getWebElement(driver, locator).click();
        }
    }

    public void uncheckToCheckbox(WebDriver driver, String locator) {
        if (isElementSelected(driver, locator)) {
            getWebElement(driver, locator).click();
        }
    }

    public boolean isElementDisplayed(WebDriver driver, String locator) {
        return getWebElement(driver, locator).isDisplayed();
    }

    public boolean isElementSelected(WebDriver driver, String locator) {
        return getWebElement(driver, locator).isSelected();
    }

    public boolean isElementEnabled(WebDriver driver, String locator) {
        return getWebElement(driver, locator).isEnabled();
    }

    public void switchToFrame(WebDriver driver, String locator) {
        driver.switchTo().frame(getWebElement(driver, locator));
    }

    public void switchToDefaultContent(WebDriver driver) {
        driver.switchTo().defaultContent();
    }

    public void doubleClick(WebDriver driver, String locator) {
        new Actions(driver).doubleClick(getWebElement(driver, locator)).perform();
    }

    public void rightClick(WebDriver driver, String locator) {
        new Actions(driver).contextClick(getWebElement(driver, locator)).perform();
    }

    public void moveToElement(WebDriver driver, String locator) {
        new Actions(driver).moveToElement(getWebElement(driver, locator)).perform();
    }

    public void dragAndDrop(WebDriver driver, String sourceLocator, String targetLocator) {
        new Actions(driver).dragAndDrop(getWebElement(driver, sourceLocator),
                getWebElement(driver, targetLocator)).perform();
    }

    public void sendKeyBoardToElement(WebDriver driver, String locator, Keys keys) {
        new Actions(driver).sendKeys(getWebElement(driver, locator), keys).perform();
    }

    public Object executeForBrowser(WebDriver driver, String javaScript) {
        return ((JavascriptExecutor) driver).executeScript(javaScript);
    }

    public void scrollToBottomPage(WebDriver driver) {
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,document.body.scrollHeight)");
    }

    public void hightlightElement(WebDriver driver, String locator) {
        WebElement element = getWebElement(driver, locator);
        String originalStyle = element.getDomAttribute("style");
        ((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('style', arguments[1])", element, "border: 2px solid red; border-style: dashed;");
        sleepInSecond(2);
        ((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('style', arguments[1])", element, originalStyle);
    }

    public void clickToElementByJS(WebDriver driver, String locator) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", getWebElement(driver, locator));
        sleepInSecond(3);
    }

    public String getElementTextByJS(WebDriver driver, String locator) {
        return (String) ((JavascriptExecutor) driver).executeScript("return arguments[0].textContent;", getWebElement(driver, locator));
    }

    public void scrollToElementOnTop(WebDriver driver, String locator) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", getWebElement(driver, locator));
    }

    public void scrollToElementOnDown(WebDriver driver, String locator) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(false);", getWebElement(driver, locator));
    }

    public String getAttributeInDOM(WebDriver driver, String locator, String attributeName) {
        return (String) ((JavascriptExecutor) driver).executeScript("return arguments[0].getAttribute('" + attributeName + "');", getWebElement(driver, locator));
    }

    public String getElementValidationMessage(WebDriver driver, String locator) {
        return (String) ((JavascriptExecutor) driver).executeScript("return arguments[0].validationMessage;", getWebElement(driver, locator));
    }

    public boolean isImageLoaded(WebDriver driver, String locator) {
        return (boolean) ((JavascriptExecutor) driver)
                .executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != 'undefined' && arguments[0].naturalWidth > 0",
                        getWebElement(driver, locator));
    }

    public WebElement waitElementVisible(WebDriver driver, String locator) {
        return new WebDriverWait(driver, Duration.ofSeconds(LONG_TIMEOUT))
                .until(ExpectedConditions.visibilityOfElementLocated(getByXpath(locator)));
    }

    public List<WebElement> waitListElementVisible(WebDriver driver, String locator) {
        return new WebDriverWait(driver, Duration.ofSeconds(LONG_TIMEOUT))
                .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getByXpath(locator)));
    }

    public boolean waitElementSelected(WebDriver driver, String locator) {
       return new WebDriverWait(driver, Duration.ofSeconds(LONG_TIMEOUT))
                .until(ExpectedConditions.elementToBeSelected(getByXpath(locator)));
    }

    public WebElement waitElementClickable(WebDriver driver, String locator) {
        return new WebDriverWait(driver, Duration.ofSeconds(LONG_TIMEOUT))
                .until(ExpectedConditions.elementToBeClickable(getByXpath(locator)));
    }

    public boolean waitElementInvisible(WebDriver driver, String locator) {
       return new WebDriverWait(driver, Duration.ofSeconds(LONG_TIMEOUT))
                .until(ExpectedConditions.invisibilityOfElementLocated(getByXpath(locator)));
    }

    public boolean waitListElementInvisible(WebDriver driver, String locator) {
       return new WebDriverWait(driver, Duration.ofSeconds(LONG_TIMEOUT))
                .until(ExpectedConditions.invisibilityOfAllElements(getListElement(driver, locator)));
    }

    public WebElement waitElementPresence(WebDriver driver, String locator) {
       return new WebDriverWait(driver, Duration.ofSeconds(LONG_TIMEOUT))
                .until(ExpectedConditions.presenceOfElementLocated(getByXpath(locator)));
    }

    public List<WebElement> waitListElementPresence(WebDriver driver, String locator) {
       return new WebDriverWait(driver, Duration.ofSeconds(LONG_TIMEOUT))
                .until(ExpectedConditions.presenceOfAllElementsLocatedBy(getByXpath(locator)));
    }

    private static final int LONG_TIMEOUT = 30;
    private static final int SHORT_TIMEOUT = 10;

}
