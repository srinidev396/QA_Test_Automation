package web.Web_TestSteps;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.web_api_BaseClass;
import web.pageObjects.grid_Navigation_PO;
import web.pageObjects.request_PO;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;

public class request_Test_Steps extends web_api_BaseClass {

    WebDriver driver;

    /**
     * Class constructor define webdriver
     * @param remoteDriver = Mention webdriver */
    public request_Test_Steps(WebDriver remoteDriver) {
        driver = remoteDriver;
    }

    public request_PO requestPo;
    public grid_Navigation_PO gridNavigationPo;
    public WebDriverWait wait;

    FileInputStream fileInputStream;
    Properties properties = new Properties();

    /** Get all level 2 employees name from table
     * @param descriptionName = Enter Description Name*/
    public void selectRecordFromBoxesGrid(String descriptionName)
    {
        requestPo = new request_PO(webDriver);
        requestPo.selectRecordFromBoxesGrid(descriptionName);
    }

    /** Select Request option */
    public void selectRequestOption()
    {
        requestPo = new request_PO(webDriver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        requestPo.selectPaperPlaneIcon.click();
        requestPo.requestOption.click();
        wait.until(ExpectedConditions.visibilityOf(requestPo.requestPopup));
    }

    /** Verify Selected Record Is Displayed On Request Popup
     * @param recordDescription = Enter Record Description */
    public void verifySelectedRecord(String recordDescription)
    {
        requestPo = new request_PO(webDriver);
        softAssert.assertTrue(requestPo.getSelectedRecordOnRequestPopup.getText().contains(recordDescription));
        softAssert.assertAll();
    }

    /** Verify Active Level 2 Container Data */
    public void verifyActiveLevel2ContainerDisplayed() throws IOException
    {
        requestPo = new request_PO(webDriver);
        ArrayList<String> getActiveLevel2ContainerData = new ArrayList<String>();

        File file = new File(System.getProperty("user.dir") + "\\framework_Tools\\variables.properties");
        fileInputStream = new FileInputStream(file);
        properties.load(fileInputStream);

        for (WebElement e : requestPo.getAllLevel2Data)
        {
            getActiveLevel2ContainerData.add(e.getText());
        }

        String getExpectedData = properties.getProperty("ExpectedActiveLevel2Data");
        String[] getExpectedDataList = getExpectedData.split(",");

        for (String s : getExpectedDataList)
        {
            softAssert.assertTrue(getActiveLevel2ContainerData.contains(s));
        }
        softAssert.assertAll();
    }

    /** Select Person Name
     * @param enterName = Enter Person Name */
    public void selectPersonName(String enterName) throws InterruptedException
    {
        requestPo = new request_PO(webDriver);
        requestPo.selectRequestPersonName(enterName);
    }

    /** Select Request Button*/
    public void selectRequestButton()
    {
        requestPo = new request_PO(webDriver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        requestPo.requestButton.click();
        wait.until(ExpectedConditions.visibilityOf(requestPo.verifyRequestSuccessMSG));
        softAssert.assertTrue(requestPo.verifyRequestSuccessMSG.isDisplayed());
        wait.until(ExpectedConditions.invisibilityOf(requestPo.verifyRequestSuccessMSG));
        softAssert.assertAll();
    }

    /** Verify Requested Status
     * @param enterName = Enter Requestor Name (EX: Yoe Howell, Dane Joseph, etc)
     * @param enterStatus = Enter Status You Want To Verify (EX: New, In Process, WaitList)*/
    public void verifyRequestedStatus(String enterName, String enterStatus) throws InterruptedException {
        requestPo = new request_PO(webDriver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        requestPo.expandTrackingStatusWindow.click();
        for (int i = 1; i < 10; i++)
        {
            if (requestPo.hideTrackingStatusWindow.isDisplayed())
            {
                Thread.sleep(2000);
                break;
            }
        }
        softAssert.assertTrue(requestPo.getStatusOfRequestedData(enterName,enterStatus).contains(enterStatus));
        wait.until(ExpectedConditions.visibilityOf(requestPo.hideTrackingStatusWindow));
        requestPo.hideTrackingStatusWindow.click();
        softAssert.assertAll();
    }

    /** Select NavBar Menu And Verify On Requestor Reports */
    public void verifyOnReports()
    {
        requestPo = new request_PO(webDriver);
        gridNavigationPo = new grid_Navigation_PO(webDriver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        gridNavigationPo.selectNavbarMenuOption("Reports");

        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));
        wait.until(ExpectedConditions.visibilityOf(requestPo.verifyRequestorReportsVisible));
        softAssert.assertTrue(requestPo.verifyRequestorReportsVisible.isDisplayed());
        softAssert.assertAll();
    }

    /** Select Requestor Data From The Grid
     * @param enterName = Enter Description Name */
    public void selectRequestorDataFromTheGrid(String enterName)
    {
        requestPo = new request_PO(webDriver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        requestPo.selectRequestorData(enterName);
        requestPo.sendSelectedItemsIntoPullListButton.click();
        wait.until(ExpectedConditions.visibilityOf(requestPo.verifyAfterRecordMoveToPullList));
        requestPo.selectRequestorData(enterName);
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.close();
    }

    /** Refresh The Grid Data */
    public void refreshDataGrid()
    {
        requestPo = new request_PO(webDriver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(0));
        requestPo.refreshGridButton.click();
        wait.until(ExpectedConditions.invisibilityOf(requestPo.gridRefreshLoadingIcon));
    }

    /** Select Transfer Selected Option */
    public void selectTransferSelectedOption()
    {
        requestPo = new request_PO(webDriver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        requestPo.selectPaperPlaneIcon.click();
        wait.until(ExpectedConditions.visibilityOf(requestPo.selectTransferSelectedOption));
        requestPo.selectTransferSelectedOption.click();
        wait.until(ExpectedConditions.visibilityOf(requestPo.requestPopup));
    }

    /** Select Container Location For Transfer Data To The Location
     * @param containerName = Enter Main Container Name (EX: Locations, Employees)
     * @param subContainerName = Enter Sub Container Name (EX: Locations, Employees)
     * @param containerOptionsName = Enter Container Options Name (EX: Central File Room, Yoe Howell)*/
    public void selectContainerFromTheTransfer(String containerName, String subContainerName, String containerOptionsName) throws InterruptedException {
        requestPo = new request_PO(webDriver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        requestPo.selectTransferDropDown(containerName);
        requestPo.selectSubTransferDropDownOptions(subContainerName);
        requestPo.selectTransferOptionOfTransfer(containerOptionsName);
        enterDueDate();
        requestPo.selectTransferButton.click();
        wait.until(ExpectedConditions.visibilityOf(requestPo.verifyTransferSuccessMSG));
        softAssert.assertTrue(requestPo.verifyTransferSuccessMSG.isDisplayed());
        wait.until(ExpectedConditions.invisibilityOf(requestPo.verifyTransferSuccessMSG));
        softAssert.assertAll();
    }

    /** Enter Due Date Of Transfer Record On Level 2 Container */
    public void enterDueDate()
    {
        requestPo = new request_PO(webDriver);

        SimpleDateFormat day = new SimpleDateFormat("dd");
        SimpleDateFormat month = new SimpleDateFormat("MM");
        SimpleDateFormat year = new SimpleDateFormat("yyyy");
        Date date = new Date();

        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        js.executeScript("document.getElementById('txtDueBack').value = '"+year.format(date)+"-"+month.format(date)+"-"+day.format(date)+"';");
    }

    /** Verify Transferred record Location In Current Location Window
     * @param enterTransferLocationName = Enter Transfer Location Name (EX: Central File Room, Yoe Howell)*/
    public void verifyTransferredRecordLocationInCurrentLocationWindow(String enterTransferLocationName) {
        requestPo = new request_PO(webDriver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        requestPo.expandTrackingStatusWindow.click();
        wait.until(ExpectedConditions.visibilityOf(requestPo.verifyAfterTransferInCurrentLocation));
        softAssert.assertTrue(requestPo.verifyAfterTransferInCurrentLocation.getText().contains(enterTransferLocationName));
        requestPo.hideTrackingStatusWindow.click();
        softAssert.assertAll();
    }

    /** Get Back To Main Grid */
    public void goBackToGrid()
    {
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(0));
    }

    /** Select And Verify WaitList Data From The Report
     * @param enterName = Enter Table Name */
    public void verifyWaitListRecordReport(String enterName)
    {
        requestPo = new request_PO(webDriver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        requestPo.selectRequestorData(enterName);
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.close();
    }
}

