package web.Web_TestSteps;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.web_api_BaseClass;
import web.pageObjects.grid_Navigation_PO;
import web.pageObjects.reports_PO;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;

public class reports_Test_Steps extends web_api_BaseClass {

    WebDriver driver;

    /**
     * Class constructor define webdriver
     * @param remoteDriver = Mention webdriver */
    public reports_Test_Steps(WebDriver remoteDriver) {
        driver = remoteDriver;
    }

    public WebDriverWait wait;
    public reports_PO reportsPo;
    public grid_Navigation_PO gridNavigationPo;
    public String getSupplierID = null;
    public String gridTotalRecord = null;
    public String getTotalCustomReportRecord = null;

    /** Create and Add New Record of supplier */
    public void addedNewRecord()
    {
        reportsPo = new reports_PO(webDriver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        reportsPo.selectNewButton.click();
        wait.until(ExpectedConditions.visibilityOf(reportsPo.verifyAddNewRowPopup));
        softAssert.assertTrue(reportsPo.verifyAddNewRowPopup.isDisplayed());
        reportsPo.selectFieldAndEnterValue("SupplierName","TestDemo");
        reportsPo.selectFieldAndEnterValue("SupplierType","Automation");
        reportsPo.selectSupplierActiveStatus.click();
        reportsPo.selectFieldAndEnterValue("SupplierName","TestAutomation");
        Select drpDepartmentName = new Select(reportsPo.selectDepartmentName);
        drpDepartmentName.selectByVisibleText("HR");
        reportsPo.saveButton.click();
        wait.until(ExpectedConditions.visibilityOf(reportsPo.verifyRecordAddedSuccessMSG));
        softAssert.assertTrue(reportsPo.verifyRecordAddedSuccessMSG.isDisplayed());
        wait.until(ExpectedConditions.invisibilityOf(reportsPo.verifyRecordAddedSuccessMSG));
        softAssert.assertAll();
    }

    /** Get New Created Added Data ID
     * @param supplierName = Enter Supplier Name */
    public void getCreatedDataID(String supplierName)
    {
        reportsPo = new reports_PO(webDriver);
        getSupplierID = reportsPo.getRecordID(supplierName);
        System.out.println(getSupplierID);
    }

    /** Open reports page from nav panel menu*/
    public void openReportsPage()
    {
        reportsPo = new reports_PO(webDriver);
        gridNavigationPo = new grid_Navigation_PO(webDriver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        gridNavigationPo.selectNavbarMenuOption("Reports");

        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));
        wait.until(ExpectedConditions.visibilityOf(reportsPo.selectAuditReportsOption));
    }

    /** Select Audit reports Option */
    public void selectAuditReportsOption()
    {
        reportsPo = new reports_PO(webDriver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        reportsPo.selectAuditReportsOption.click();
        wait.until(ExpectedConditions.visibilityOf(reportsPo.verifyAuditReportPopup));
        softAssert.assertTrue(reportsPo.verifyAuditReportPopup.isDisplayed());
        softAssert.assertAll();
    }

    /** Select UserName Form Dropdown
     * @param userName = Enter User Name (EX: administrator)*/
    public void selectUserName(String userName)
    {
        reportsPo = new reports_PO(webDriver);
        Select drpUsers = new Select(reportsPo.selectUser);
        drpUsers.selectByVisibleText(userName);
    }

    /** Select Table Name Form DropDown
     * @param tableName = Enter Table Name (EX: Supplier)*/
    public void selectTable(String tableName)
    {
        reportsPo = new reports_PO(webDriver);
        Select drpUsers = new Select(reportsPo.selectTable);
        drpUsers.selectByVisibleText(tableName);
    }

    /** Enter From Date Of The Record */
    public void enterFromDate()
    {
        SimpleDateFormat formatter = new SimpleDateFormat("M/dd/yyyy");
        Date date = new Date();
        reportsPo = new reports_PO(webDriver);
        reportsPo.selectFromDate.sendKeys(formatter.format(date));
    }

    /** Enter To Date Of The Record */
    public void enterToDate()
    {
        SimpleDateFormat formatter = new SimpleDateFormat("M/dd/yyyy");
        Date date = new Date();
        reportsPo = new reports_PO(webDriver);
        reportsPo.selectToDate.sendKeys(formatter.format(date));
    }

    /** Select Add Edit Delete Checkbox */
    public void selectCheckBox()
    {
        reportsPo = new reports_PO(webDriver);
        reportsPo.selectAddEditDeleteCheckbox.click();
        reportsPo.okAuditReportButton.click();
    }

    /** Verify Added Record In Audit Report
     * @param actionType = Enter Verify Action In Supplier Table (Add, Delete, Edit)*/
    public void verifyAddedRecordInAuditReport(String actionType)
    {
        reportsPo = new reports_PO(webDriver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        Actions actions = new Actions(webDriver);

        wait.until(ExpectedConditions.visibilityOf(reportsPo.doubleClickOnDateTimeOnAuditReportTable));

        actions.moveToElement(reportsPo.doubleClickOnDateTimeOnAuditReportTable).perform();
        actions.click(reportsPo.doubleClickOnDateTimeOnAuditReportTable).perform();
//        actions.click(reportsPo.doubleClickOnDateTimeOnAuditReportTable).perform();

        actions.moveToElement(reportsPo.doubleClickOnDataAfterOnAuditReportTable).perform();
        actions.click(reportsPo.doubleClickOnDataAfterOnAuditReportTable).perform();
        actions.click(reportsPo.doubleClickOnDataAfterOnAuditReportTable).perform();
        reportsPo.verifyAddedOrDeletedRecordInAuditReport(actionType,getSupplierID);
        driver.close();
    }

    /** Delete Created Record */
    public void deleteCreatedRecord()
    {
        reportsPo = new reports_PO(webDriver);
        gridNavigationPo = new grid_Navigation_PO(webDriver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());

        driver.switchTo().window(tabs.get(0));
        reportsPo.selectPaperPlaneIcon.click();
        reportsPo.selectDeleteRecord.click();
        wait.until(ExpectedConditions.visibilityOf(reportsPo.verifyDeleteItemPopup));
        reportsPo.yesDeleteButton.click();
        wait.until(ExpectedConditions.visibilityOf(reportsPo.verifyRecordDeletedSuccessfullyMSG));
        softAssert.assertTrue(reportsPo.verifyRecordDeletedSuccessfullyMSG.isDisplayed());
        wait.until(ExpectedConditions.invisibilityOf(reportsPo.verifyRecordDeletedSuccessfullyMSG));
        gridNavigationPo.reportsOption.click();

        ArrayList<String> tabs1 = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs1.get(1));
        wait.until(ExpectedConditions.visibilityOf(reportsPo.selectAuditReportsOption));
        softAssert.assertAll();
    }

    /** Verify Deleted Record In Audit Report
     * @param actionType = Enter Verify Action In Supplier Table (Add, Delete, Edit)*/
    public void verifyDeletedRecordInAuditReport(String actionType) {
        reportsPo = new reports_PO(webDriver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        Actions actions = new Actions(webDriver);
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());

        wait.until(ExpectedConditions.visibilityOf(reportsPo.doubleClickOnDateTimeOnAuditReportTable));

        actions.moveToElement(reportsPo.doubleClickOnDateTimeOnAuditReportTable).perform();
        actions.click(reportsPo.doubleClickOnDateTimeOnAuditReportTable).perform();
//        actions.click(reportsPo.doubleClickOnDateTimeOnAuditReportTable).perform();

        actions.moveToElement(reportsPo.doubleClickOnDataBeforeOnAuditReportTable).perform();
        actions.click(reportsPo.doubleClickOnDataBeforeOnAuditReportTable).perform();
        actions.click(reportsPo.doubleClickOnDataBeforeOnAuditReportTable).perform();
        reportsPo.verifyAddedOrDeletedRecordInAuditReport(actionType,getSupplierID);
        driver.close();
        driver.switchTo().window(tabs.get(0));
    }

    /** Get Grid Total Record Count */
    public void getTotalRecord()
    {
        reportsPo = new reports_PO(webDriver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.visibilityOf(reportsPo.getGridTotalRecord));
        gridTotalRecord = reportsPo.getGridTotalRecord.getText();
    }

    /** Select Custom Report Option */
    public void selectCustomReportOption()
    {
        reportsPo = new reports_PO(webDriver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        reportsPo.selectCustomReportOption.click();
        wait.until(ExpectedConditions.visibilityOf(reportsPo.selectCustomReportViewTable));
        reportsPo.selectCustomReportViewTable.click();
        wait.until(ExpectedConditions.visibilityOf(reportsPo.verifyQueryPopup));
        reportsPo.okQueryButton.click();
        wait.until(ExpectedConditions.visibilityOf(reportsPo.verifyDataTableVisible));
    }

    /** Get Total Record Of Custom Report */
    public void getTotalCustomReportRecord() throws InterruptedException {
        reportsPo = new reports_PO(webDriver);
        Actions actions = new Actions(webDriver);

        for (int i = 1; i < 10; i++)
        {
            if (!reportsPo.getCustomReportTotalRecord.isDisplayed())
            {
                System.out.println("Loop");
                Thread.sleep(2000);
                actions.scrollToElement(reportsPo.getCustomReportTotalRecord).perform();
                break;
            }
        }

        getTotalCustomReportRecord = reportsPo.getCustomReportTotalRecord.getText();
    }

    /** Verify Grid And Custom Report Total Record Data is same or not */
    public void verifyGridAndCustomReportTotalRecords()
    {
        softAssert.assertEquals(getTotalCustomReportRecord,gridTotalRecord);
        softAssert.assertAll();
    }
}

