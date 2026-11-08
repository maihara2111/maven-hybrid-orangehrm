package com.jquery;

//Import từ thư viện

import core.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.PageGenerator;
import pageObjects.jquery.HomePageObject;

import java.util.List;

public class Level_13_Upload_File extends BaseTest {

    @Parameters({"url", "browser"})
    @BeforeClass
    public void beforeClass(String url, String browserName) {

        //Mở browser lên sẽ là trang user
        driver = getBrowserDriver(url, browserName);
        homePage = PageGenerator.getPage(HomePageObject.class, driver);
    }

    @Test(enabled = true)
    public void Upload_01_Single() {
        homePage.uploadMultipleFiles(driver, barchartFileName);
        homePage.uploadMultipleFiles(driver, chartFileName);
        homePage.uploadMultipleFiles(driver, posterFileName);

        Assert.assertTrue(homePage.isFileLoadedSuccess(barchartFileName));
        Assert.assertTrue(homePage.isFileLoadedSuccess(chartFileName));
        Assert.assertTrue(homePage.isFileLoadedSuccess(posterFileName));

        homePage.clickStartUpload();

        Assert.assertTrue(homePage.isFileUploadedSuccess(barchartFileName));
        Assert.assertTrue(homePage.isFileUploadedSuccess(chartFileName));
        Assert.assertTrue(homePage.isFileUploadedSuccess(posterFileName));
    }

    @Test(enabled = true)
    public void Upload_02_Multiple() {
        homePage.refreshPage(driver);
        homePage.uploadMultipleFiles(driver, barchartFileName, chartFileName, posterFileName);

        Assert.assertTrue(homePage.isFileLoadedSuccess(barchartFileName));
        Assert.assertTrue(homePage.isFileLoadedSuccess(chartFileName));
        Assert.assertTrue(homePage.isFileLoadedSuccess(barchartFileName));

        homePage.clickStartUpload();

        Assert.assertTrue(homePage.isFileUploadedSuccess(barchartFileName));
        Assert.assertTrue(homePage.isFileUploadedSuccess(chartFileName));
        Assert.assertTrue(homePage.isFileUploadedSuccess(posterFileName));
    }

    @AfterClass
    public void afterClass() {
        closeBrowser(driver);
    }

    //Non-Static
    private WebDriver driver;
    private HomePageObject homePage;
    String barchartFileName = "barchart.png";
    String chartFileName = "Chart.png";
    String posterFileName = "poster.png";
    String[] fileName = {barchartFileName, chartFileName, posterFileName};
}
