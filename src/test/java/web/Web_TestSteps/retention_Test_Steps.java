package web.Web_TestSteps;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.web_api_BaseClass;
import web.pageObjects.grid_Navigation_PO;
import web.pageObjects.retention_PO;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;

public class retention_Test_Steps extends web_api_BaseClass {

    WebDriver driver;

    /**
     * Class constructor define webdriver
     * @param remoteDriver = Mention webdriver */
    public retention_Test_Steps(WebDriver remoteDriver) {
        driver = remoteDriver;
    }

    public retention_PO retentionPo;
    public grid_Navigation_PO gridNavigationPo;
    public grid_Test_Steps gridTestSteps;
    public WebDriverWait wait;
    public String getDefaultRetentionCode = null;
    public String getUpdatedRetentionCode = null;

    /** Create New Record For EFile */
    public void createEFileNewRecord() throws InterruptedException {
        retentionPo = new retention_PO(webDriver);
        gridTestSteps = new grid_Test_Steps(webDriver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        SimpleDateFormat formatter = new SimpleDateFormat("M/dd/yyyy");
        Date date = new Date();

        retentionPo.selectNewButton.click();
        wait.until(ExpectedConditions.visibilityOf(retentionPo.verifyAddNewPopup));
        softAssert.assertTrue(retentionPo.verifyAddNewPopup.isDisplayed());
        retentionPo.selectFieldAndEnterValue("EFileName","TestAutomation.txt");
        retentionPo.selectFieldAndEnterValue("DateCreated",formatter.format(date));
        retentionPo.selectFieldAndEnterValue("DateOpened",formatter.format(date));
        retentionPo.selectFieldAndEnterValue("DateClosed",formatter.format(date));
        retentionPo.selectFieldAndEnterValue("DateOther",formatter.format(date));
        retentionPo.saveButton.click();
        wait.until(ExpectedConditions.visibilityOf(retentionPo.verifyRecordAddedSuccessMSG));
        softAssert.assertTrue(retentionPo.verifyRecordAddedSuccessMSG.isDisplayed());
        wait.until(ExpectedConditions.invisibilityOf(retentionPo.verifyRecordAddedSuccessMSG));

        for (int i = 1; i <= 10; i++)
        {
            if (retentionPo.loadingWheel.isDisplayed())
            {
                Thread.sleep(2000);
                System.out.println(i);
                if (retentionPo.loadingWheel.isDisplayed() && i == 10)
                {
                    webDriver.navigate().refresh();
                    gridTestSteps.selectNavigationOption("AutomationWG");
                    gridTestSteps.selectNavigationOption("PaperFiles");
                    gridTestSteps.selectNavigationOption("All PaperFiles");
                    gridTestSteps.verifyQueryPopupAndPressOk();
                    gridTestSteps.verifySelectedViewIsOpened("All PaperFiles");
                    sortRecordFromGrid("TestAutomation.txt");
                    break;
                }
            }
        }

        softAssert.assertAll();
    }

    /** Open Retention Info Popup From Selected Record
     * @param fileName = Enter File Name You Want To Open Retention Info */
    public void openRetentionInfoFroRecordMenu(String fileName)
    {
        retentionPo = new retention_PO(webDriver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        retentionPo.selectGridRecordMenu(fileName);
        retentionPo.selectRetentionInfoFromMenu.click();
        wait.until(ExpectedConditions.visibilityOf(retentionPo.verifyRetentionInformationPopup));
        softAssert.assertTrue(retentionPo.verifyRetentionInformationPopup.isDisplayed());
        softAssert.assertAll();
    }

    /** Verify Created Record Retention Code And retention Info Popup Code */
    public void verifyRetentionCode()
    {
        retentionPo = new retention_PO(webDriver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        softAssert.assertEquals(retentionPo.getSelectedRetentionCodeInDropdown.getText(),getDefaultRetentionCode);
        retentionPo.closeRetentionInfoPopup.click();
        wait.until(ExpectedConditions.invisibilityOf(retentionPo.verifyRetentionInformationPopup));
        softAssert.assertAll();
    }

    /** Delete Created Record After Test */
    public void deleteCreatedRecord()
    {
        retentionPo = new retention_PO(webDriver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(0));

        retentionPo.selectPaperPlaneIcon.click();
        retentionPo.selectDeleteRecord.click();
        wait.until(ExpectedConditions.visibilityOf(retentionPo.verifyDeleteItemPopup));
        retentionPo.yesDeleteButton.click();
        wait.until(ExpectedConditions.visibilityOf(retentionPo.verifyRecordDeletedSuccessfullyMSG));
        softAssert.assertTrue(retentionPo.verifyRecordDeletedSuccessfullyMSG.isDisplayed());
        softAssert.assertAll();
    }

    /** Select Record From Grid and Sort List the Record
     * @param recordName = Enter File Name */
    public void sortRecordFromGrid(String recordName)
    {
        retentionPo = new retention_PO(webDriver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        Actions actions = new Actions(webDriver);

        actions.moveToElement(retentionPo.sortingIDSelectCreatedRecord).perform();
        actions.click(retentionPo.sortingIDSelectCreatedRecord).perform();
        actions.click(retentionPo.sortingIDSelectCreatedRecord).perform();

        retentionPo.selectRecordFromGrid(recordName);
    }

    /** Get Default Retention Code From DropDown Of Selected Record
     * @param enterFileName = Enter File Name */
    public void getDefaultRetentionCodeFromDropDownOfSelectedRecord(String enterFileName)
    {
        retentionPo = new retention_PO(webDriver);
        getDefaultRetentionCode = retentionPo.getRetentionCodeOfRecordGrid(enterFileName);
    }

    /** Select Retention Code From Grid DropDown
     * @param enterFileName = Enter File Name
     * @param retentionOptionName = Enter Retention Option Name */
    public void selectRetentionCodeOfRecord(String enterFileName, String retentionOptionName)
    {
        retentionPo = new retention_PO(webDriver);
        retentionPo.selectRetentionCodeFromDropDownGrid(enterFileName);
        retentionPo.selectRetentionOptionsFromDropDownGrid(retentionOptionName);
    }

    /** Select Save Edit Button And Verify Edit Success Message */
    public void saveEditRecord()
    {
        retentionPo = new retention_PO(webDriver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        retentionPo.saveEditButton.click();
        wait.until(ExpectedConditions.visibilityOf(retentionPo.recordUpdatedSuccessMSG));
        softAssert.assertTrue(retentionPo.recordUpdatedSuccessMSG.isDisplayed());
        wait.until(ExpectedConditions.invisibilityOf(retentionPo.recordUpdatedSuccessMSG));
        softAssert.assertAll();
    }

    /** Get Updated Retention Code From DropDown Of Selected Record
     * @param enterFileName = Enter File Name */
    public void getUpdatedRetentionCodeFromDropDownOfSelectedRecord(String enterFileName)
    {
        retentionPo = new retention_PO(webDriver);
        getUpdatedRetentionCode = retentionPo.getRetentionCodeOfRecordGrid(enterFileName);
    }

    /** Verify Retention Code Is Different After Change Or Updated */
    public void verifyRetentionCodeAfterUpdate()
    {
        softAssert.assertNotEquals(getDefaultRetentionCode,getUpdatedRetentionCode);
        softAssert.assertAll();
    }

    /** Add Retention Hold Information */
    public void addRetentionHold()
    {
        retentionPo = new retention_PO(webDriver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        retentionPo.addRetentionHoldButton.click();
        wait.until(ExpectedConditions.visibilityOf(retentionPo.retentionHoldPopup));
        retentionPo.retentionHoldCheckbox.click();
        retentionPo.holdReason.sendKeys("Hold For Testing");
        retentionPo.snoozeButton.click();
        retentionPo.okRetentionHoldButton.click();
    }

    /** Verify Added retention Hold Information On Popup Table
     * @param enterHoldReason = Enter Retention Hold Reason */
    public void verifyAddedRetentionHoldInfoOnPopup(String enterHoldReason)
    {
        retentionPo = new retention_PO(webDriver);
        retentionPo.verifyRetentionHoldInformationAddedInTable(enterHoldReason);
    }

    /** Save Retention Hold Information After Added Retention Hold */
    public void saveRetentionHoldInfo()
    {
        retentionPo = new retention_PO(webDriver);
        retentionPo.saveRetentionInfoButton.click();
    }

    /** Open Retention Report From Nav Toggle Menu */
    public void openRetentionReport()
    {
        retentionPo = new retention_PO(webDriver);
        gridNavigationPo = new grid_Navigation_PO(webDriver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        gridNavigationPo.selectNavbarMenuOption("Reports");

        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));
        wait.until(ExpectedConditions.visibilityOf(retentionPo.verifyRetentionReportsVisible));
        softAssert.assertTrue(retentionPo.verifyRetentionReportsVisible.isDisplayed());
        softAssert.assertAll();
    }

    /** Verify retention Hold In Retention Hold Report Table
     * @param enterHoldReason = Enter Retention Hold Reason */
    public void verifyRetentionHoldReasonOnHoldReport(String enterHoldReason)
    {
        retentionPo = new retention_PO(webDriver);
        retentionPo.verifyRetentionHoldInReportTable(enterHoldReason);
    }

    /** Get Back To Main Grid */
    public void goBackToGrid()
    {
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(0));
    }
}
