package web.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.web_api_BaseClass;

import java.time.Duration;
import java.util.List;

public class import_PO extends web_api_BaseClass {

    /** This file contains all the xpaths of the Home page of the Tab FusionRMS
     * @param webDriver = initialized Webdriver
     * */
    public import_PO(WebDriver webDriver)
    {
        web_api_BaseClass.webDriver = webDriver;
        PageFactory.initElements(webDriver,this);
    }

    public WebDriverWait wait;
    public String selectParticularRowData = "//div[@class='truncated' and contains(text(),'%s')]";
    public String selectRecord = "//div[@class='truncated' and contains(text(),'%s')]";

    /** Select data from grid table
     * @param enterRowDataName = Enter data name you want to select */
    public void selectParticularRowData(String enterRowDataName)
    {
        Actions actions = new Actions(webDriver);
        if (webDriver.findElement(By.xpath(String.format(selectParticularRowData,enterRowDataName))).isDisplayed())
        {
            actions.scrollToElement(webDriver.findElement(By.xpath(String.format(selectParticularRowData,enterRowDataName)))).perform();
            actions.click(webDriver.findElement(By.xpath(String.format(selectParticularRowData,enterRowDataName)))).perform();
        }
    }

    /** Select Record From Grid
     * @param recordName = Enter File Name */
    public void selectRecordFromGrid(String recordName)
    {
        wait = new WebDriverWait(webDriver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.visibilityOf(webDriver.findElement(By.xpath(String.format(selectRecord,recordName)))));
        webDriver.findElement(By.xpath(String.format(selectRecord,recordName))).click();
    }

    @FindBy(xpath = "//select[@id='slcImportLoads']")
    public WebElement selectSavedImportFileFromDropDown;

    @FindBy(xpath = "//input[@id='btnRun']")
    public WebElement selectRunButton;

    @FindBy(xpath = "//div[contains(text(),'Import has completed successfully without any Errors')]")
    public WebElement verifyImportSuccessfullyMSG;

    @FindBy(xpath = "//div[@id='ToolBar']//button//img[contains(@src,'refresh')]")
    public WebElement refreshGridButton;

    @FindBy(xpath = "//div[@id='spinningWheel' and @style='display: block;']")
    public WebElement gridRefreshLoadingIcon;

    @FindBy(xpath = "(//div[@class='btn-group'])[2]")
    public WebElement selectPaperPlaneIcon;

    @FindBy(xpath = "//a[@id='btndeleterow']")
    public WebElement selectDeleteRecord;

    @FindBy(xpath = "//a[contains(text(),'Yes')]")
    public WebElement yesDeleteRecordButton;

    @FindBy(xpath = "//div[@class='truncated']")
    public List<WebElement> departmentNameList;

    @FindBy(xpath = "//a[@id='btndeleterow']")
    public WebElement selectDeleteOptionFromPaperPlane;

    @FindBy(xpath = "//a[contains(text(),'Yes')]")
    public WebElement yesDeleteButton;

    @FindBy(xpath = "//div[contains(text(),'Record/s has been deleted successfully')]")
    public WebElement verifyRecordDeletedSuccessfullyMSG;

    @FindBy(xpath = "//input[@id='NewLoadId']")
    public WebElement newImportButton;

    @FindBy(xpath = "//a[@id='tabMain']")
    public WebElement verifyFirstStageOfImport;

    @FindBy(xpath = "//input[@id='LoadName']")
    public WebElement enterImportName;

    @FindBy(xpath = "//input[@id='FirstRowHeader']")
    public WebElement selectFirstRowContainsFieldNamesCheckbox;

    @FindBy(xpath = "//input[@id='btnNext']")
    public WebElement nextButton;

    @FindBy(xpath = "//a[@id='tabFormat']")
    public WebElement verifySecondStageOfImport;

    @FindBy(xpath = "//select[@id='FileName']")
    public WebElement selectDestinationDropDown;

    @FindBy(xpath = "//select[@id='bootstrap-duallistbox-nonselected-list_duallistbox_demo']//option[@value='SupplierId']")
    public WebElement waitForSelectedTableAvailableField;

    @FindBy(xpath = "//select[@id='bootstrap-duallistbox-nonselected-list_duallistbox_demo']")
    public WebElement selectAvailableFields;

    @FindBy(xpath = "//button[@title='Move Selected']")
    public WebElement moveSelectedArrowInAvailableFields;

    @FindBy(xpath = "//a[@id='tabField']")
    public WebElement verifyThirdStageOfImport;

    @FindBy(xpath = "//select[@id='Duplicate']")
    public WebElement selectOverWriteOrAddDropDown;

    @FindBy(xpath = "//select[@id='IdFieldName']")
    public WebElement selectImportByDropDown;

    @FindBy(xpath = "//input[@id='btnFinish']")
    public WebElement finishImportButton;

    @FindBy(xpath = "//div[contains(text(),'Import Data Configuration has been created successfully')]")
    public WebElement importFileConfigurationSuccessMSG;

    @FindBy(xpath = "//select[@id='slcImportLoads']")
    public WebElement selectSavedImports;

    @FindBy(xpath = "//input[@id='btnRun']")
    public WebElement runImportButton;

    @FindBy(xpath = "//div[contains(text(),'Import has completed successfully without any Errors')]")
    public WebElement verifySelectedImportCompleteSuccessMSG;

    @FindBy(xpath = "(//span[contains(@class,'colHeader columnSorting sortAction')]//span[contains(text(),'SupplierId')])[1]")
    public WebElement sortSuppliersIdColumn;

    @FindBy(xpath = "//td[3]//div[@class='truncated']")
    public List<WebElement> supplierNameList;

    @FindBy(xpath = "//input[@id='btnRemove']")
    public WebElement removeImportButton;

    @FindBy(xpath = "//div[contains(text(),'Saved Import Configuration has been deleted successfully')]")
    public WebElement verifyImportedDataFileConfigurationRemoveSuccessMSG;

    @FindBy(xpath = "//h3[contains(text(),'Confirm Delete')]")
    public WebElement verifyDeleteImportPopup;

    @FindBy(xpath = "//button[@id='okButton']")
    public WebElement yesDeleteImportButton;

    @FindBy(xpath = "//label[@id='dlgBsTitle']")
    public WebElement verifyDeleteRecordPopup;
}
