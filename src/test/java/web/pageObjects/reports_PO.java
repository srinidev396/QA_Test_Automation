package web.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.web_api_BaseClass;

public class reports_PO extends web_api_BaseClass {

    /** This file contains all the xpaths of the Home page of the Tab FusionRMS
     * @param webDriver = initialized Webdriver
     * */
    public reports_PO(WebDriver webDriver) {
        web_api_BaseClass.webDriver = webDriver;
        PageFactory.initElements(webDriver,this);
    }

    public WebDriverWait wait;
    public String selectField = "//td[contains(@data-columnmeta,'%s')]//input";
    public String verifyAddedOrDeletedRecordInAuditReport = "//td[@class='htDimmed currentRow' and contains(text(),'%s Record')]/parent::tr//td[contains(text(),'SupplierId: %s')]";
    public String getRecordID = "//div[@class='truncated' and contains(text(),'%s')]/parent::td/parent::tr//td[@dir='ltr']";

    /** Select Field From Popup and Enter Data into it
     * @param fieldName = Enter Field Label Name
     * @param value = Enter Value OF The Field */
    public void selectFieldAndEnterValue(String fieldName, String value)
    {
        webDriver.findElement(By.xpath(String.format(selectField,fieldName))).sendKeys(value);
    }

    /** Verify Record Action is displayed in Audit Report or not so Enter Action Type And Record Id
     * @param actionType = Enter Action Type (EX: Add, Delete, Edit)
     * @param id = Enter Record Primary Key ID (EX: 80, 79, etc.)*/
    public void verifyAddedOrDeletedRecordInAuditReport(String actionType, String id)
    {
        softAssert.assertTrue(webDriver.findElement(By.xpath(String.format(verifyAddedOrDeletedRecordInAuditReport,actionType,id))).isDisplayed());
        softAssert.assertAll();
    }

    /** Get Record ID from table
     * @param enterName = Enter SupplierName (Ex: DemoTest)
     * @return Return A Selected Record ID */
    public String getRecordID(String enterName)
    {
        return webDriver.findElement(By.xpath(String.format(getRecordID,enterName))).getText();
    }

    @FindBy(xpath = "//input[@id='btnNew']")
    public WebElement selectNewButton;

    @FindBy(xpath = "//label[@id='dlgBsTitle']")
    public WebElement verifyAddNewRowPopup;

    @FindBy(xpath = "//td[contains(@data-columnmeta,'SupplierActiveStatus')]//input")
    public WebElement selectSupplierActiveStatus;

    @FindBy(xpath = "//td[contains(@data-columnmeta,'Department.DepartmentName')]//select")
    public WebElement selectDepartmentName;

    @FindBy(xpath = "//input[@id='BtnAddNewRow']")
    public WebElement saveButton;

    @FindBy(xpath = "//div[contains(text(),'A New record created successfully')]")
    public WebElement verifyRecordAddedSuccessMSG;

    @FindBy(xpath = "//a[contains(text(),'Audit Reports')]")
    public WebElement selectAuditReportsOption;

    @FindBy(xpath = "//label[@id='dlgBsTitleAudit']")
    public WebElement verifyAuditReportPopup;

    @FindBy(xpath = "//select[@id='ddlUser']")
    public WebElement selectUser;

    @FindBy(xpath = "//select[@id='ddlObject']")
    public WebElement selectTable;

    @FindBy(xpath = "//input[@id='dtStartDate']")
    public WebElement selectFromDate;

    @FindBy(xpath = "//input[@id='dtEndDate']")
    public WebElement selectToDate;

    @FindBy(xpath = "//input[@id='chkAddEditDel']")
    public WebElement selectAddEditDeleteCheckbox;

    @FindBy(xpath = "//input[@id='chkConfidential']")
    public WebElement selectConfidentialDataAccessCheckbox;

    @FindBy(xpath = "//input[@id='auditReportOk']")
    public WebElement okAuditReportButton;

    @FindBy(xpath = "//div[@id='handsOnTableContainer']/div[2]/div/div/div/table/thead/tr/th[3]/div/span")
    public WebElement doubleClickOnDateTimeOnAuditReportTable;

    @FindBy(xpath = "//div[@id='handsOnTableContainer']/div[2]/div/div/div/table/thead/tr/th[8]/div/span")
    public WebElement doubleClickOnDataAfterOnAuditReportTable;

    @FindBy(xpath = "//div[@id='handsOnTableContainer']/div[2]/div/div/div/table/thead/tr/th[7]/div/span")
    public WebElement doubleClickOnDataBeforeOnAuditReportTable;

    @FindBy(xpath = "(//div[@class='btn-group'])[2]")
    public WebElement selectPaperPlaneIcon;

    @FindBy(xpath = "//a[@id='btndeleterow']")
    public WebElement selectDeleteRecord;

    @FindBy(xpath = "//label[@id='dlgBsTitle']")
    public WebElement verifyDeleteItemPopup;

    @FindBy(xpath = "//a[contains(text(),'Yes')]")
    public WebElement yesDeleteButton;

    @FindBy(xpath = "//div[contains(text(),'Record/s has been deleted successfully')]")
    public WebElement verifyRecordDeletedSuccessfullyMSG;

    @FindBy(xpath = "//span[@id='spanTotalRecords']")
    public WebElement getGridTotalRecord;

    @FindBy(xpath = "//a[contains(text(),'Custom Reports')]")
    public WebElement selectCustomReportOption;

    @FindBy(xpath = "//a[contains(text(),'Custom Supplier Report')]")
    public WebElement selectCustomReportViewTable;

    @FindBy(xpath = "//label[@id='dlgBsTitle']")
    public WebElement verifyQueryPopup;

    @FindBy(xpath = "//button[@id='OkQuery']")
    public WebElement okQueryButton;

    @FindBy(xpath = "//div[@id='handsOnTableContainer']")
    public WebElement verifyDataTableVisible;

    @FindBy(xpath = "//div[@id='divTotalRecords']//span")
    public WebElement getCustomReportTotalRecord;
}
