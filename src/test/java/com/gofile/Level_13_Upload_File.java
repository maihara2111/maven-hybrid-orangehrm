package com.gofile;

//Import từ thư viện

import core.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.PageGenerator;
import pageObjects.gofile.HomePageObject;

public class Level_13_Upload_File extends BaseTest {

    @Parameters({"url", "browser"})
    @BeforeClass
    public void beforeClass(String url, String browserName) {

        //Mở browser lên sẽ là trang user
        driver = getBrowserDriver(url, browserName);
        homePage = PageGenerator.getPage(HomePageObject.class, driver);
    }

    @Test(enabled = true)
    public void Upload_01_Multiple() {
        Assert.assertTrue(homePage.isLoadingIconDisappear());

        homePage.uploadMultipleFiles(driver, barchartFileName, chartFileName, posterFileName);

        Assert.assertTrue(homePage.isProgressBarIconDisappear());

        String successUrl = homePage.getSuccessLink();

        homePage.openPageUrl(driver, successUrl);

        Assert.assertTrue(homePage.isLoadingIconDisappear());
        homePage.sleepInSecond(2);

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
