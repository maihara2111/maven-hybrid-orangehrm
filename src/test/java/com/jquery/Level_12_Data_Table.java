package com.jquery;

//Import từ thư viện

import core.BaseTest;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.PageGenerator;
import pageObjects.jquery.HomePageObject;

import java.util.List;

public class Level_12_Data_Table extends BaseTest {

    @Parameters({"url", "browser"})
    @BeforeClass
    public void beforeClass(String url, String browserName) {

        //Mở browser lên sẽ là trang user
        driver = getBrowserDriver(url, browserName);
        homePage = PageGenerator.getPage(HomePageObject.class, driver);
    }

    @Test(enabled = true)
    public void Table_01_Paging() {
        //1- Mở ra 1 trang bất kì dựa vào số trang truyền vào
        homePage.openPageByNumber("10");
        homePage.closePopup();
        Assert.assertTrue(homePage.isPageActivedByNumber("10"));

        homePage.openPageByNumber("6");
        Assert.assertTrue(homePage.isPageActivedByNumber("6"));

        homePage.openPageByNumber("12");
        Assert.assertTrue(homePage.isPageActivedByNumber("12"));
    }

    @Test(enabled = false)
    public void Table_02_Search() {
        //2- Search ở bất kì 1 header textbox nào dựa vào
        //3- Verify bất k thông tin của 1 country nào (Male/Country/Female/Total/...)
        homePage.enterToHeaderTextboxByName("Females", "777");
        homePage.sleepInSecond(3);
        Assert.assertTrue(homePage.isPageInfoDisplayed("777", "Antigua and Barbuda", "803", "1580"));
        homePage.refreshPage(driver);

        homePage.enterToHeaderTextboxByName("Country", "AFRICA");
        homePage.sleepInSecond(3);
        Assert.assertTrue(homePage.isPageInfoDisplayed("12253515", "AFRICA", "12599691", "24853148"));
        homePage.refreshPage(driver);

        homePage.enterToHeaderTextboxByName("Males", "16456");
        Assert.assertTrue(homePage.isPageInfoDisplayed("15999", "Armenia", "16456", "32487"));
    }

    @Test(enabled = false)
    public void Table_03_Action() {
        //4- Có thể xoá/edit bâ kì 1 country nào dựa vào tên country
        homePage.enterToHeaderTextboxByName("Country", "AFRICA");
        homePage.sleepInSecond(3);
        homePage.clickToActionByCountryName("AFRICA", "remove");
        homePage.refreshPage(driver);

        homePage.enterToHeaderTextboxByName("Country", "AFRICA");
        homePage.sleepInSecond(3);
        homePage.clickToActionByCountryName("AFRICA", "edit");
    }

    @Test(enabled = false)
    public void Table_04_Index() {
        homePage.openPageUrl(driver, "https://www.jqueryscript.net/demo/jQuery-Dynamic-Data-Grid-Plugin-appendGrid/");

        homePage.clickLoadDataButton();
        homePage.enterToTextboxByColumnNameAndRowIndex("Company", "2", "Portt");
        homePage.enterToTextboxByColumnNameAndRowIndex("Contact Person", "2", "Mai Hara");
        homePage.enterToTextboxByColumnNameAndRowIndex("Order Placed", "2", "11");
        homePage.selectToDropdownByColumnNameAndRowIndex("Country", "2", "Hong Kong");
        homePage.checkToCheckboxByColumnNameAndRowIndex("NPO?", "2");
        homePage.actionToRowByRowIndex("2", "Move Up");
        homePage.sleepInSecond(3);

        homePage.enterToTextboxByColumnNameAndRowIndex("Company", "4", "HDS");
        homePage.enterToTextboxByColumnNameAndRowIndex("Contact Person", "4", "Mai Hara");
        homePage.enterToTextboxByColumnNameAndRowIndex("Order Placed", "4", "11");
        homePage.selectToDropdownByColumnNameAndRowIndex("Country", "4", "Taiwan");
        homePage.checkToCheckboxByColumnNameAndRowIndex("NPO?", "4");
        homePage.actionToRowByRowIndex("4", "Insert");
        homePage.sleepInSecond(3);
    }

    @Test(enabled = false)
    public void Table_05_Get_All_Value() {
        List<String> countryActualValue = homePage.getColumnAllValueByColumnName("Country");
        System.out.println(countryActualValue.size());


    }
//    @AfterClass
//    public void afterClass() {
//        closeBrowser(driver);
//    }

    //Non-Static
    private WebDriver driver;
    private HomePageObject homePage;
}
