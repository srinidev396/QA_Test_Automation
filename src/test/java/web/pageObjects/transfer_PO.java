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

public class transfer_PO extends web_api_BaseClass {

    /** This file contains all the xpaths of the Home page of the Tab FusionRMS
     * @param webDriver = initialized Webdriver
     * */
    public transfer_PO(WebDriver webDriver) {
        web_api_BaseClass.webDriver = webDriver;
        PageFactory.initElements(webDriver,this);
    }

    public WebDriverWait wait;
    public String selectFields = "//td[contains(@data-columnmeta,'%s')]//input";
    public String selectTransferDropDown = "//div[@class='tablebox']//label[contains(text(),'%s')]";
    public String selectTransferDropDownOption = "//table[@id='DestinationsTable']//tbody[@id='lsTaransferTypeTableContainer']//input[@data-tablename='%s']";
    public String selectOptionsFromLocationsOfTransfer = "//span[@name='trDestinationsItem' and contains(text(),'%s')]";
    public String selectRecordMenu = "//div[@class='truncated' and contains(text(),'%s')]/parent::td/parent::tr//td/a/span[@title='Menu']";
    public String selectRecord = "//div[@class='truncated' and contains(text(),'%s')]";
    public String verifyDestinationBarcodeName = "//label[@id='lblDestination' and contains(text(),'%s')]";
    public String verifyTrackedItem = "//table[@id='lstTracked']//tr//td[contains(text(),'%s')]/parent::tr/following-sibling::tr//td[contains(text(),'%s')]";
    public String getNewCreatedRecordID = "//div[@class='truncated' and contains(text(),'%s')]/parent::td/parent::tr//td[@dir='ltr']";
    public String selectContainerDescriptionRecord = "//div[@class='truncated' and contains(text(),'%s')]";
    public String selectContainerRecordMenu = "//div[@class='truncated' and contains(text(),'%s')]/parent::td/parent::tr//td//span[@title='Menu']";
    public String selectSortListedRecordByID = "//td[contains(@class,'current') and contains(text(),'%s')]";

    /** Enter Data Into Form
     * @param enterFieldName = Enter Filed Label Name
     * @param enterValue = Enter Field Data You Have To Enter */
    public void enterFieldData(String enterFieldName, String enterValue)
    {
        if (enterFieldName.equalsIgnoreCase("FileName") || enterFieldName.equalsIgnoreCase("Description"))
        {
            webDriver.findElement(By.xpath(String.format(selectFields,enterFieldName))).click();
        }
        webDriver.findElement(By.xpath(String.format(selectFields,enterFieldName))).sendKeys(enterValue);
    }

    /** Select Transfer DropDown
     * @param enterValue = Enter DropDown Name (EX: Locations, Employees)*/
    public void selectTransferDropDown(String enterValue)
    {
        wait = new WebDriverWait(webDriver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.visibilityOf(webDriver.findElement(By.xpath(String.format(selectTransferDropDown,enterValue)))));
        webDriver.findElement(By.xpath(String.format(selectTransferDropDown,enterValue))).click();
    }

    /** Select Transfer DropDown
     * @param enterOption = Enter Option Name (EX: Locations, Employees)*/
    public void selectSubTransferDropDownOptions(String enterOption) throws InterruptedException
    {
        wait = new WebDriverWait(webDriver, Duration.ofSeconds(30));
        for (int i = 1; i < 10; i++)
        {
            if (!webDriver.findElement(By.xpath(String.format(selectTransferDropDownOption,enterOption))).isDisplayed())
            {
                Thread.sleep(2000);
            }
        }
        wait.until(ExpectedConditions.visibilityOf(webDriver.findElement(By.xpath(String.format(selectTransferDropDownOption,enterOption)))));
        webDriver.findElement(By.xpath(String.format(selectTransferDropDownOption,enterOption))).click();
    }

    /** Select Option From Location Of Transfer
     * @param enterLocation = Enter Location Name */
    public void selectTransferOptionOfTransfer(String enterLocation) throws InterruptedException
    {
        wait = new WebDriverWait(webDriver, Duration.ofSeconds(30));
        for (int i = 1; i < 10; i++)
        {
            if (!webDriver.findElement(By.xpath(String.format(selectOptionsFromLocationsOfTransfer,enterLocation))).isDisplayed())
            {
                Thread.sleep(2000);
            }
        }
        wait.until(ExpectedConditions.visibilityOf(webDriver.findElement(By.xpath(String.format(selectOptionsFromLocationsOfTransfer,enterLocation)))));
        webDriver.findElement(By.xpath(String.format(selectOptionsFromLocationsOfTransfer,enterLocation))).click();
    }

    /** Select Record Menu From Grid
     * @param enterFileName = Enter File Name Of Record Field For Shortlisted*/
    public void selectRecordMenuFromGrid(String enterFileName)
    {
        wait = new WebDriverWait(webDriver, Duration.ofSeconds(30));
        Actions actions = new Actions(webDriver);

        refreshGridButton.click();
        if (loadingWheel.isDisplayed())
        {
            wait.until(ExpectedConditions.invisibilityOf(loadingWheel));
        }
        actions.moveToElement(sortListRecord).perform();
        actions.click(sortListRecord).perform();
        actions.click(sortListRecord).perform();
        webDriver.findElement(By.xpath(String.format(selectRecordMenu,enterFileName))).click();
    }

    /** Select Record From Grid
     * @param enterFileName = Enter File Name */
    public void selectRecordFromGrid(String enterFileName)
    {
        Actions actions = new Actions(webDriver);
        actions.moveToElement(sortListRecord).perform();
        actions.click(sortListRecord).perform();
        actions.click(sortListRecord).perform();
        webDriver.findElement(By.xpath(String.format(selectRecord,enterFileName))).click();
    }

    /** Verify Destination Barcode Description
     * @param enterTransferLocationName = Enter Transfer Location Name In Words (NOTE: Don't Use Barcode)*/
    public void verifyDestinationBarcodeDescription(String enterTransferLocationName)
    {
        Actions actions = new Actions(webDriver);
        wait = new WebDriverWait(webDriver, Duration.ofSeconds(30));

        if(!webDriver.findElement(By.xpath(String.format(verifyDestinationBarcodeName,enterTransferLocationName))).isDisplayed())
        {
            actions.scrollToElement(scroll).perform();
        }
        wait.until(ExpectedConditions.visibilityOf(webDriver.findElement(By.xpath(String.format(verifyDestinationBarcodeName,enterTransferLocationName)))));
        softAssert.assertTrue(webDriver.findElement(By.xpath(String.format(verifyDestinationBarcodeName,enterTransferLocationName))).isDisplayed());
        softAssert.assertAll();
    }

    /** Verify Transferred Record From Barcode In Tracked Items
     * @param enterDestinationName = Enter Destination Name In Words (Ex: Central File Room)
     * @param recordId = Enter Created Record ID */
    public void verifyTrackedItems(String enterDestinationName, String recordId)
    {
        wait = new WebDriverWait(webDriver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.visibilityOf(webDriver.findElement(By.xpath(String.format(verifyTrackedItem,enterDestinationName,recordId)))));
        softAssert.assertTrue(webDriver.findElement(By.xpath(String.format(verifyTrackedItem,enterDestinationName,recordId))).isDisplayed());
        softAssert.assertAll();
    }

    /** Get New Created Record ID
     * @param enterFileName = Enter File Name Of The Created Record From Grid
     * @return Return New Created Record ID */
    public String getNewCreatedRecordID(String enterFileName)
    {
        return webDriver.findElement(By.xpath(String.format(getNewCreatedRecordID,enterFileName))).getText();
    }

    /** Enter And Select Container Description Record Name
     * @param containerDescriptionName = Enter Container Description Name */
    public void selectContainerRecord(String containerDescriptionName)
    {
        wait = new WebDriverWait(webDriver, Duration.ofSeconds(30));
        Actions actions = new Actions(webDriver);

        if (!webDriver.findElement(By.xpath(String.format(selectContainerDescriptionRecord,containerDescriptionName))).isDisplayed())
        {
            actions.scrollToElement(webDriver.findElement(By.xpath(String.format(selectContainerDescriptionRecord,containerDescriptionName)))).perform();
        }
        wait.until(ExpectedConditions.visibilityOf(webDriver.findElement(By.xpath(String.format(selectContainerDescriptionRecord,containerDescriptionName)))));
        webDriver.findElement(By.xpath(String.format(selectContainerDescriptionRecord,containerDescriptionName))).click();
    }

    /** Enter And Select Container Description Name
     * @param containerDescriptionName = Enter Container Description Name */
    public void selectContainerRecordMenu(String containerDescriptionName)
    {
        webDriver.findElement(By.xpath(String.format(selectContainerRecordMenu,containerDescriptionName))).click();
    }

    /** Select Record From Grid
     * @param enterCreatedRecordID = Enter New Created Record ID */
    public void sortListAndSelectRecordFromGrid(String enterCreatedRecordID)
    {
        wait = new WebDriverWait(webDriver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.visibilityOf(webDriver.findElement(By.xpath(String.format(selectSortListedRecordByID,enterCreatedRecordID)))));
        webDriver.findElement(By.xpath(String.format(selectSortListedRecordByID,enterCreatedRecordID))).click();
    }

    @FindBy(xpath = "//input[@id='btnNew']")
    public WebElement newRecordButton;

    @FindBy(xpath = "//label[@id='dlgBsTitle']")
    public WebElement transferPopup;

    @FindBy(xpath = "//td[contains(@data-columnmeta,'Boxes.Name')]/select")
    public WebElement selectBoxesNameDropDown;

    @FindBy(xpath = "//input[@id='BtnAddNewRow']")
    public WebElement saveButton;

    @FindBy(xpath = "//div[contains(text(),'A New record created successfully')]")
    public WebElement verifyRecordAddedSuccessMSG;

    @FindBy(xpath = "(//div[@class='btn-group'])[2]")
    public WebElement selectPaperPlaneIcon;

    @FindBy(xpath = "//a[@id='btnTransfer']")
    public WebElement selectTransferSelectedOption;

    @FindBy(xpath = "//button[@id='btnTransferItems']")
    public WebElement selectTransferButton;

    @FindBy(xpath = "//div[contains(text(),'Record/s has been Transferred successfully')]")
    public WebElement verifyTransferSuccessMSG;

    @FindBy(xpath = "//div[contains(text(),'Requests Wait List')]/a")
    public WebElement showHideCurrentLocationWindow;

    @FindBy(xpath = "//span[@id='lblTracking']")
    public WebElement getCurrentRecordLocationFromWindow;

    @FindBy(xpath = "//ul[@class='Customdrilldown']//a[contains(text(),'Tracking History')]")
    public WebElement selectTrackingHistoryOption;

    @FindBy(xpath = "//h3[@id='ReportTitle']")
    public WebElement trackingHistoryReport;

    @FindBy(xpath = "//div[@id='handsOnTableContainer']//table//tbody//tr//td[2]")
    public List<WebElement> getTrackingHistory;

    @FindBy(xpath = "(//span[contains(@class,'colHeader columnSorting sortAction')]//div//span[contains(text(),'FileId')])[1]")
    public WebElement sortListRecord;

    @FindBy(xpath = "//a[@id='btndeleterow']")
    public WebElement selectDeleteRecord;

    @FindBy(xpath = "//label[@id='dlgBsTitle']")
    public WebElement verifyDeleteItemPopup;

    @FindBy(xpath = "//a[contains(text(),'Yes')]")
    public WebElement yesDeleteButton;

    @FindBy(xpath = "//div[contains(text(),'Record/s has been deleted successfully')]")
    public WebElement verifyRecordDeletedSuccessfullyMSG;

    @FindBy(xpath = "//span[@class='barCodeTitle']")
    public WebElement verifyBarcodeTrackingPage;

    @FindBy(xpath = "//input[@id='txtDestination']")
    public WebElement enterDestinationBarCode;

    @FindBy(xpath = "//label[@id='lblDestination']")
    public WebElement destinationBarcodeDescription;

    @FindBy(xpath = "//input[@id='txtObject']")
    public WebElement enterObjectBarcode;

    @FindBy(xpath = "//a[@id='btnTransfer']")
    public WebElement transferFolderButton;

    @FindBy(xpath = "//h2[@class='trackableItemInfo']")
    public WebElement scroll;

    @FindBy(xpath = "//ul[@class='Customdrilldown']//a[contains(text(),'Contents')]")
    public WebElement selectContentsOptionFromContainer;

    @FindBy(xpath = "//h3[@id='ReportTitle']")
    public WebElement verifyContainerContentsReport;

    @FindBy(xpath = "//table[@class='htCore']//tbody//tr[1]//td[2]")
    public WebElement getTransferRecordContentsReport;

    @FindBy(xpath = "//div[@class='loaderMain']")
    public WebElement loadingWheel;

    @FindBy(xpath = "//button[@class='btn btn-secondary tab_btn']")
    public WebElement refreshGridButton;
}
