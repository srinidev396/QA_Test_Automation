package web.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.web_api_BaseClass;

import java.time.Duration;

public class retention_PO extends web_api_BaseClass {

    /** This file contains all the xpaths of the Home page of the Tab FusionRMS
     * @param webDriver = initialized Webdriver */
    public retention_PO(WebDriver webDriver) {
        web_api_BaseClass.webDriver = webDriver;
        PageFactory.initElements(webDriver,this);
    }

    public WebDriverWait wait;
    public String enterFieldValue = "//td[contains(@data-columnmeta,'%s')]/input";
    public String selectGridRecordMenu = "//div[@class='truncated' and contains(text(),'%s')]/parent::td/parent::tr//td/a/span[@title='Menu']";
    public String selectRecord = "//div[@class='truncated' and contains(text(),'%s')]";
    public String selectRetentionCodeFromDropDownGrid = "//div[@class='truncated' and contains(text(),'%s')]/parent::td/parent::tr//td[5]//div[@class='htAutocompleteArrow']";
    public String selectRetentionOptionsFromDropDownGrid = "//td[@class='listbox htDimmed' and contains(text(),'%s')]";
    public String getRetentionCodeOfRecordGrid = "(//div[@class='truncated' and contains(text(),'%s')]/parent::td/parent::tr//td[contains(@class,'htAutocomplete')])[1]";
    public String verifyRetentionHoldInformationAddedInTable = "//div[@id='handsOnTableRetinfo']//table//tbody//tr//td[contains(@class,'htDimmed') and contains(text(),'%s')]";
    public String verifyRetentionHoldInReportTable = "//div[@id='handsOnTableContainer']//table//tbody//tr//td[contains(text(),'%s')]";

    /** Select Field And Enter Value
     * @param fieldName = Enter Field Name (EX: EFileName, DateCreated, DateOpened) */
    public void selectFieldAndEnterValue(String fieldName, String value)
    {
        webDriver.findElement(By.xpath(String.format(enterFieldValue,fieldName))).sendKeys(value);
    }

    /** Select Grid Record Menu
     * @param fileName = Enter File Name Whatever Record Menu You Have To Open (TestData.txt)*/
    public void selectGridRecordMenu(String fileName)
    {
        webDriver.findElement(By.xpath(String.format(selectGridRecordMenu,fileName))).click();
    }

    /** Select Record From Grid
     * @param recordName = Enter File Name */
    public void selectRecordFromGrid(String recordName)
    {
        webDriver.findElement(By.xpath(String.format(selectRecord,recordName))).click();
    }

    /** Select Retention Code DropDown
     * @param fileName = Enter File Name */
    public void selectRetentionCodeFromDropDownGrid(String fileName)
    {
        wait = new WebDriverWait(webDriver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.visibilityOf(webDriver.findElement(By.xpath(String.format(selectRetentionCodeFromDropDownGrid,fileName)))));
        webDriver.findElement(By.xpath(String.format(selectRetentionCodeFromDropDownGrid,fileName))).click();
    }

    /** Select Retention Options From The DropDown
     * @param retentionOptionName = Enter Retention Options From The DropDown */
    public void selectRetentionOptionsFromDropDownGrid(String retentionOptionName)
    {
        wait = new WebDriverWait(webDriver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.visibilityOf(webDriver.findElement(By.xpath(String.format(selectRetentionOptionsFromDropDownGrid,retentionOptionName)))));
        webDriver.findElement(By.xpath(String.format(selectRetentionOptionsFromDropDownGrid,retentionOptionName))).click();
    }

    /** Get Retention Code Form Selected Record From Grid
     * @param enterFileName = Enter File Name
     * @return They Return The Current Selected Record Retention Code From Grid */
    public String getRetentionCodeOfRecordGrid(String enterFileName)
    {
        return webDriver.findElement(By.xpath(String.format(getRetentionCodeOfRecordGrid,enterFileName))).getText().replaceAll("[^a-zA-Z0-9_]","");
    }

    /** Verify Retention Hold Information Added In Table
     * @param enterRetentionHoldReason = Enter Retention Hold Reason */
    public void verifyRetentionHoldInformationAddedInTable(String enterRetentionHoldReason)
    {
        wait = new WebDriverWait(webDriver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.visibilityOf(webDriver.findElement(By.xpath(String.format(verifyRetentionHoldInformationAddedInTable,enterRetentionHoldReason)))));
        softAssert.assertTrue(webDriver.findElement(By.xpath(String.format(verifyRetentionHoldInformationAddedInTable,enterRetentionHoldReason))).isDisplayed());
        softAssert.assertAll();
    }

    /** Verify retention Hold In Retention Hold Report Table
     * @param enterHoldReason = Enter Retention Hold Reason */
    public void verifyRetentionHoldInReportTable(String enterHoldReason)
    {
        wait = new WebDriverWait(webDriver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.visibilityOf(verifyRetentionHoldReportOpen));
        softAssert.assertTrue(webDriver.findElement(By.xpath(String.format(verifyRetentionHoldInReportTable,enterHoldReason))).isDisplayed());
        softAssert.assertAll();
    }

    @FindBy(xpath = "//input[@id='btnNew']")
    public WebElement selectNewButton;

    @FindBy(xpath = "//label[@id='dlgBsTitle']")
    public WebElement verifyAddNewPopup;

    @FindBy(xpath = "//input[@id='BtnAddNewRow']")
    public WebElement saveButton;

    @FindBy(xpath = "//div[contains(text(),'A New record created successfully')]")
    public WebElement verifyRecordAddedSuccessMSG;

    @FindBy(xpath = "//ul[@class='Customdrilldown']//a[contains(text(),'Retention Info')]")
    public WebElement selectRetentionInfoFromMenu;

    @FindBy(xpath = "//label[contains(text(),'Retention Information')]")
    public WebElement verifyRetentionInformationPopup;

    @FindBy(xpath = "//select[@id='ddlRetentionCode']/option[@selected]")
    public WebElement getSelectedRetentionCodeInDropdown;

    @FindBy(xpath = "//button[@id='closedlg']")
    public WebElement closeRetentionInfoPopup;

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

    @FindBy(xpath = "(//span[contains(@class,'colHeader columnSorting sortAction')]//span[contains(text(),'Id')])[1]")
    public WebElement sortingIDSelectCreatedRecord;

    @FindBy(xpath = "//input[@id='saveRow']")
    public WebElement saveEditButton;

    @FindBy(xpath = "//div[contains(text(),'Record has been updated successfully')]")
    public WebElement recordUpdatedSuccessMSG;

    @FindBy(xpath = "//button[@id='btnAddRetin']")
    public WebElement addRetentionHoldButton;

    @FindBy(xpath = "//label[@id='DlgHoldingTitle']")
    public WebElement retentionHoldPopup;

    @FindBy(xpath = "//input[@id='chkHoldTypeRetention']")
    public WebElement retentionHoldCheckbox;

    @FindBy(xpath = "//textarea[@id='holdReason']")
    public WebElement holdReason;

    @FindBy(xpath = "//input[@id='btnSnooze']")
    public WebElement snoozeButton;

    @FindBy(xpath = "//input[@id='btnHoldOk']")
    public WebElement okRetentionHoldButton;

    @FindBy(xpath = "//button[@id='btnOkRetin']")
    public WebElement saveRetentionInfoButton;

    @FindBy(xpath = "//a[contains(text(),'Retention Reports')]")
    public WebElement verifyRetentionReportsVisible;

    @FindBy(xpath = "//h1[contains(text(),'All Records On Hold')]")
    public WebElement verifyRetentionHoldReportOpen;

    @FindBy(xpath = "//div[@class='loaderMain']")
    public WebElement loadingWheel;

}
