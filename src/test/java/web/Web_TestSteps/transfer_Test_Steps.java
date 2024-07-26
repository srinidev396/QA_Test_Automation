package web.Web_TestSteps;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.web_api_BaseClass;
import web.pageObjects.grid_Navigation_PO;
import web.pageObjects.transfer_PO;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;

public class transfer_Test_Steps extends web_api_BaseClass {

    WebDriver driver;

    /**
     * Class constructor define webdriver
     * @param remoteDriver = Mention webdriver */
    public transfer_Test_Steps(WebDriver remoteDriver) {
        driver = remoteDriver;
    }

    public transfer_PO transferPo;
    public grid_Navigation_PO gridNavigationPo;
    public grid_Test_Steps gridTestSteps;
    public WebDriverWait wait;
    public ArrayList<String> getTransferLocationList = new ArrayList<>();
    public ArrayList<String> getReverseTrackingHistoryList = new ArrayList<>();
    public ArrayList<String> getTrackingHistoryList = new ArrayList<>();
    public String getNewCreatedRecordID = null;
    public String getContainerContentReport = null;

    /** Create New Record */
    public void enterNewPaperFilesRecord() throws InterruptedException {
        transferPo = new transfer_PO(webDriver);
        gridTestSteps = new grid_Test_Steps(webDriver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        SimpleDateFormat todayDate = new SimpleDateFormat("M/dd/yyyy");
        Date date = new Date();

        transferPo.newRecordButton.click();
        wait.until(ExpectedConditions.visibilityOf(transferPo.transferPopup));

        Select drpBoxesName = new Select(transferPo.selectBoxesNameDropDown);
        drpBoxesName.selectByVisibleText("Automation Request Box");

        transferPo.enterFieldData("DateOpened",todayDate.format(date));
        transferPo.enterFieldData("DateClosed",todayDate.format(date));
        transferPo.enterFieldData("DateCreated",todayDate.format(date));
        transferPo.enterFieldData("FileName","AutomationTest.txt");
        transferPo.enterFieldData("Description","AutomationTest");
        transferPo.saveButton.click();
        wait.until(ExpectedConditions.visibilityOf(transferPo.verifyRecordAddedSuccessMSG));
        softAssert.assertTrue(transferPo.verifyRecordAddedSuccessMSG.isDisplayed());
        wait.until(ExpectedConditions.invisibilityOf(transferPo.verifyRecordAddedSuccessMSG));

        for (int i = 1; i <= 10; i++)
        {
            if (transferPo.loadingWheel.isDisplayed())
            {
                Thread.sleep(2000);
                System.out.println(i);
                if (transferPo.loadingWheel.isDisplayed() && i == 10)
                {
                    webDriver.navigate().refresh();
                    gridTestSteps.selectNavigationOption("AutomationWG");
                    gridTestSteps.selectNavigationOption("PaperFiles");
                    gridTestSteps.selectNavigationOption("All PaperFiles");
                    gridTestSteps.verifyQueryPopupAndPressOk();
                    gridTestSteps.verifySelectedViewIsOpened("All PaperFiles");
                    selectRecordFromGrid("AutomationTest.txt");
                    break;
                }
            }
        }

        softAssert.assertAll();
    }

    /** Select Transfer Selected Option */
    public void selectTransferSelectedOption()
    {
        transferPo = new transfer_PO(webDriver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        transferPo.selectPaperPlaneIcon.click();
        wait.until(ExpectedConditions.visibilityOf(transferPo.selectTransferSelectedOption));
        transferPo.selectTransferSelectedOption.click();
        wait.until(ExpectedConditions.visibilityOf(transferPo.transferPopup));
    }

    /** Select Container Location For Transfer Data To The Location
     * @param containerName = Enter Main Container Name (EX: Locations, Employees)
     * @param subContainerName = Enter Sub Container Name (EX: Locations, Employees)
     * @param containerOptionsName = Enter Container Options Name (EX: Central File Room, Yoe Howell)*/
    public void selectContainerFromTheTransfer(String containerName, String subContainerName, String containerOptionsName) throws InterruptedException
    {
        transferPo = new transfer_PO(webDriver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        transferPo.selectTransferDropDown(containerName);
        transferPo.selectSubTransferDropDownOptions(subContainerName);
        transferPo.selectTransferOptionOfTransfer(containerOptionsName);
        enterDueDate();
        transferPo.selectTransferButton.click();
        wait.until(ExpectedConditions.visibilityOf(transferPo.verifyTransferSuccessMSG));
        softAssert.assertTrue(transferPo.verifyTransferSuccessMSG.isDisplayed());
        wait.until(ExpectedConditions.invisibilityOf(transferPo.verifyTransferSuccessMSG));
        softAssert.assertAll();
    }

    /** Enter Due Date Of Transfer Record */
    public void enterDueDate()
    {
        SimpleDateFormat day = new SimpleDateFormat("dd");
        SimpleDateFormat month = new SimpleDateFormat("MM");
        SimpleDateFormat year = new SimpleDateFormat("yyyy");
        Date date = new Date();

        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        js.executeScript("document.getElementById('txtDueBack').value = '"+year.format(date)+"-"+month.format(date)+"-"+day.format(date)+"';");
    }

    /** Get Current Location Or Record From The Current Location Window And Store In ArrayList */
    public void getCurrentLocationOfRecord()
    {
        transferPo = new transfer_PO(webDriver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        transferPo.showHideCurrentLocationWindow.click();
        wait.until(ExpectedConditions.visibilityOf(transferPo.getCurrentRecordLocationFromWindow));

        String getOriginalString = transferPo.getCurrentRecordLocationFromWindow.getText();
        String[] list = getOriginalString.split("Description ");

        getTransferLocationList.add(list[1]);
        System.out.println(getTransferLocationList);
        transferPo.showHideCurrentLocationWindow.click();
    }

    /** Select Record from Grid
     * @param enterFileName = Enter File Name */
    public void selectRecordFromGrid(String enterFileName)
    {
        transferPo = new transfer_PO(webDriver);
        transferPo.selectRecordFromGrid(enterFileName);
    }

    /** Select Tracking History Option From Selected Record From Grid
     * @param enterFileName = Enter Retention Code Of The Record */
    public void selectTrackingHistoryOption(String enterFileName)
    {
        transferPo = new transfer_PO(webDriver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        transferPo.selectRecordMenuFromGrid(enterFileName);
        wait.until(ExpectedConditions.visibilityOf(transferPo.selectTrackingHistoryOption));
        transferPo.selectTrackingHistoryOption.click();
        wait.until(ExpectedConditions.visibilityOf(transferPo.trackingHistoryReport));
        softAssert.assertTrue(transferPo.trackingHistoryReport.isDisplayed());
        softAssert.assertAll();
    }

    /** Get First 2 Transfer Location Because We Have Transfer Record 2 Times */
    public void getFirst2TransferLocation()
    {
        transferPo = new transfer_PO(webDriver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        for (WebElement e : transferPo.getTrackingHistory)
        {
            getTrackingHistoryList.add(e.getText().replaceAll("[^a-zA-Z\\s+]","").split("L ")[1]);
        }
        int getTrackingListSize = getTrackingHistoryList.size();
        getTrackingHistoryList.remove(getTrackingListSize-1);

        for (int i = getTrackingHistoryList.size() - 1; i >= 0; i--)
        {
            getReverseTrackingHistoryList.add(getTrackingHistoryList.get(i));
        }
    }

    /** Verify Transferred Locations List And Tracking History Location List*/
    public void verifyTransferAndTrackingLocationList()
    {
        softAssert.assertEquals(getTransferLocationList,getReverseTrackingHistoryList);
        softAssert.assertAll();
    }

    /** Delete Created Record After Test */
    public void deleteCreatedRecord()
    {
        transferPo = new transfer_PO(webDriver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        transferPo.selectPaperPlaneIcon.click();
        transferPo.selectDeleteRecord.click();
        wait.until(ExpectedConditions.visibilityOf(transferPo.verifyDeleteItemPopup));
        transferPo.yesDeleteButton.click();
        wait.until(ExpectedConditions.visibilityOf(transferPo.verifyRecordDeletedSuccessfullyMSG));
        softAssert.assertTrue(transferPo.verifyRecordDeletedSuccessfullyMSG.isDisplayed());
        softAssert.assertAll();
    }

    /** Get New Created Record ID */
    public void getNewCreatedRecordID()
    {
        transferPo = new transfer_PO(webDriver);
        getNewCreatedRecordID = transferPo.getNewCreatedRecordID("AutomationTest.txt");
        System.out.println(getNewCreatedRecordID);
    }

    /** Verify And Open Barcode Tracking Page */
    public void openBarcodeTrackingFromMenu()
    {
        transferPo = new transfer_PO(webDriver);
        gridNavigationPo = new grid_Navigation_PO(webDriver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        gridNavigationPo.selectNavbarMenuOption("Tracking");

        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));

        wait.until(ExpectedConditions.visibilityOf(transferPo.verifyBarcodeTrackingPage));
        softAssert.assertTrue(transferPo.verifyBarcodeTrackingPage.isDisplayed());
        softAssert.assertAll();
    }

    /** Enter Destination Barcode And Object Barcode
     * @param enterDestinationBarcode = Enter Destination Barcode With Prefix And ID (EX: L2) */
    public void enterDestinationBarcode(String enterDestinationBarcode)
    {
        transferPo = new transfer_PO(webDriver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        Actions actions = new Actions(webDriver);
        wait.until(ExpectedConditions.visibilityOf(transferPo.enterDestinationBarCode));
        transferPo.enterDestinationBarCode.click();
        transferPo.enterDestinationBarCode.clear();
        transferPo.enterDestinationBarCode.sendKeys(enterDestinationBarcode);
        actions.click(transferPo.destinationBarcodeDescription).perform();
    }

    /** Verify Destination Barcode Description
     * @param enterDestinationLocationInWords = Enter Destination Location In Words (Ex: Central File Room)*/
    public void verifyDestinationBarcodeDescription(String enterDestinationLocationInWords)
    {
        transferPo = new transfer_PO(webDriver);
        transferPo.verifyDestinationBarcodeDescription(enterDestinationLocationInWords);
    }

    /** Enter Object Barcode They Take New Created Record ID And Passing Into It*/
    public void enterObjectBarcode()
    {
        transferPo = new transfer_PO(webDriver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        Actions actions = new Actions(webDriver);

        actions.scrollToElement(transferPo.enterObjectBarcode).perform();
        wait.until(ExpectedConditions.visibilityOf(transferPo.enterObjectBarcode));
        transferPo.enterObjectBarcode.sendKeys("Y"+getNewCreatedRecordID);
        transferPo.transferFolderButton.click();
    }

    /** Verify Barcode Transferred Location And Record
     * @param enterDestinationName = Enter Destination Name in Words */
    public void verifyBarcodeTransferredLocationAndRecord(String enterDestinationName)
    {
        transferPo = new transfer_PO(webDriver);
        transferPo.verifyTrackedItems(enterDestinationName,getNewCreatedRecordID);
    }

    /** Get Back To Main Grid */
    public void goBackToGrid()
    {
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(0));
    }

    /** Enter And Select Container Description Name
     * @param containerDescriptionName = Enter Container Description Name */
    public void selectContainerRecordMenu(String containerDescriptionName)
    {
        transferPo = new transfer_PO(webDriver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        transferPo.selectContainerRecord(containerDescriptionName);
        transferPo.selectContainerRecordMenu(containerDescriptionName);
        transferPo.selectContentsOptionFromContainer.click();
        wait.until(ExpectedConditions.visibilityOf(transferPo.verifyContainerContentsReport));
    }

    /** Get Transferred Record Container Report */
    public void getContainerContentReport()
    {
        transferPo = new transfer_PO(webDriver);
        getContainerContentReport = transferPo.getTransferRecordContentsReport.getText();
    }

    /** Verify Transferred Record And Container Content Report */
    public void verifyTransferredRecordAndContainerContentReport()
    {
        softAssert.assertTrue(getContainerContentReport.contains(getNewCreatedRecordID));
        softAssert.assertAll();
    }

    /** Select Record From Grid */
    public void sortListCreatedRecordFromGrid()
    {
        transferPo = new transfer_PO(webDriver);
        Actions actions = new Actions(webDriver);

        actions.moveToElement(transferPo.sortListRecord).perform();
        actions.click(transferPo.sortListRecord).perform();
        actions.click(transferPo.sortListRecord).perform();
        transferPo.sortListAndSelectRecordFromGrid(getNewCreatedRecordID);
    }
}
