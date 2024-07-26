package web.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import utilities.web_api_BaseClass;

import java.time.Duration;
import java.util.List;

public class grid_Table_PO extends web_api_BaseClass {

    public WebDriverWait wait;

    /** This file contains all the xpaths of the Home page of the Tab FusionRMS
     * @param webDriver = initialized Webdriver
     * */
    public grid_Table_PO(WebDriver webDriver) {
        web_api_BaseClass.webDriver = webDriver;
        PageFactory.initElements(webDriver,this);
    }

    public String verifySelectedViewDisplayed = "//ul[@id='breadcrumbs']/li[contains(text(),'%s')]";
    public String selectNavigationOption = "//li[@class='hasSubs']//a[starts-with(text(),'%s')]";

    /** By using this method get the dynamic XPATH
     * @param dynamicXpath = Enter Xpath with dynamic value symbol with %s
     * @param dynamicValue = Enter dynamic value to enter into the xpath of %s */
    public String getXpath(String dynamicXpath, String dynamicValue)
    {
        return String.format(dynamicXpath,dynamicValue);
    }

    /** In this method verify table view is opened
     * @param tableViewName = Enter view name */
    public void verifySelectedViewDisplayed(String tableViewName)
    {
        wait = new WebDriverWait(webDriver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.visibilityOf(webDriver.findElement(By.xpath(getXpath(verifySelectedViewDisplayed,tableViewName)))));
        Assert.assertTrue(webDriver.findElement(By.xpath(getXpath(verifySelectedViewDisplayed,tableViewName))).isDisplayed());
    }

    @FindBy(xpath = "//div[@class='mCSB_dragger_bar']")
    public WebElement scrollBar;

    @FindBy(xpath = "//input[@id='btnNew']")
    public WebElement selectNewButton;

    @FindBy(xpath = "//label[@id='dlgBsTitle']")
    public WebElement verifyAddNewRecordPopup;

    ///////////////////Add New Edit Delete Department//////////////
    @FindBy(xpath = "//td[contains(@data-columnmeta,'DepartmentName')]/input")
    public WebElement enterDepartmentName;

    @FindBy(xpath = "//input[@id='BtnAddNewRow']")
    public WebElement saveButton;

    @FindBy(xpath = "//div[@id='toast-container']")
    public WebElement verifyToastMSGInvisible;

    @FindBy(xpath = "//div[contains(text(),'A New record created successfully')]")
    public WebElement verifyRecordAddedSuccessfullyMSG;

    @FindBy(xpath = "//div[@class='ht_master handsontable']//table[@class='htCore']//tbody//tr[1]//td[2]//div")
    public WebElement getVerifyAddedRecordFromTable;

    @FindBy(xpath = "//textarea[@class='handsontableInput']")
    public WebElement editDataRecord;

    @FindBy(xpath = "//input[@id='saveRow']")
    public WebElement saveEditButton;

    @FindBy(xpath = "//div[contains(text(),'Record has been updated successfully')]")
    public WebElement verifyRecordUpdatedSuccessfullyMSG;

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

    @FindBy(xpath = "(//div[@class='btn-group'])[1]")
    public WebElement selectNotePaperIcon;

    @FindBy(xpath = "//a[@id='btnExportTXT']")
    public WebElement selectExportSelectedTXT;

    @FindBy(xpath = "//a[@id='btnExportCSV']")
    public WebElement selectExportSelectedCSV;

    @FindBy(xpath = "//label[contains(text(),'Export Data into text file format')]")
    public WebElement verifyExportTXTRecordPopup;

    @FindBy(xpath = "//label[contains(text(),'Export Data into csv file format')]")
    public WebElement verifyExportCSVRecordPopup;

    @FindBy(xpath = "//input[@id='ExportToCSVorTXT']")
    public WebElement yesExportSelectedButton;

    @FindBy(xpath = "//a[@id='btnExportTXTAll']")
    public WebElement selectExportAllTXT;

    @FindBy(xpath = "//a[@id='btnExportCSVAll']")
    public WebElement selectExportAllCSV;

    @FindBy(xpath = "//input[@id='ExportToCSVorTXTAll']")
    public WebElement yesExportAllButton;

    @FindBy(xpath = "//a[@id='btnBlackWhite']")
    public WebElement selectBlackAndWhite;

    @FindBy(xpath = "//label[contains(text(),'Black and White Label Information')]")
    public WebElement verifyBlackAndWhitePopup;

    @FindBy(xpath = "//a[@id='btnbcodeDownload']")
    public WebElement downloadBlackAndWhiteButton;

    @FindBy(xpath = "//a[@id='tabquikId']")
    public WebElement selectTabquik;

    @FindBy(xpath = "//table[@class='htCore']//tr//td[2]")
    public List<WebElement> getDeptIdList;

    @FindBy(xpath = "//table[@class='htCore']//tr//td[3]//div")
    public List<WebElement> getDeptNameList;

    @FindBy(xpath = "//button[@id='closedlg']")
    public WebElement selectClosePopupButton;
}
