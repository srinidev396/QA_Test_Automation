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


public class request_PO extends web_api_BaseClass {

    /** This file contains all the xpaths of the Home page of the Tab FusionRMS
     * @param webDriver = initialized Webdriver
     * */
    public request_PO(WebDriver webDriver) {
        web_api_BaseClass.webDriver = webDriver;
        PageFactory.initElements(webDriver,this);
    }

    public WebDriverWait wait;
    public String selectRecordFromGrid = "//div[@class='truncated' and contains(text(),'%s')]";
    public String selectRequestPersonName = "//tbody[@id='employeeContainer']//tr//td//span[@name='rdlEmployees' and contains(text(),'%s')]";
    public String getRequestedDataStatus = "//tbody[@id='trackingbody']//tr//td[contains(text(),'%s')]/parent::tr//td[contains(text(),'%s')]/parent::tr//td[6]";
    public String selectRequestorData = "//div[@id='handsOnTableContainer']//table//tbody//tr//td[contains(text(),'%s')]";
    public String selectTransferDropDown = "//div[@class='tablebox']//label[contains(text(),'%s')]";
    public String selectTransferDropDownOption = "//table[@id='DestinationsTable']//tbody[@id='lsTaransferTypeTableContainer']//input[@data-tablename='%s']";
    public String selectOptionsFromLocationsOfTransfer = "//span[@name='trDestinationsItem' and contains(text(),'%s')]";

    /** Select Record Form Boxes Grid
     * @param enterName = Enter Description Name */
    public void selectRecordFromBoxesGrid(String enterName)
    {
        if (webDriver.findElement(By.xpath(String.format(selectRecordFromGrid,enterName))).isDisplayed())
        {
            wait = new WebDriverWait(webDriver, Duration.ofSeconds(30));
            Actions actions = new Actions(webDriver);
            wait.until(ExpectedConditions.visibilityOf(webDriver.findElement(By.xpath(String.format(selectRecordFromGrid,enterName)))));
            actions.scrollToElement(webDriver.findElement(By.xpath(String.format(selectRecordFromGrid,enterName)))).perform();
            actions.click(webDriver.findElement(By.xpath(String.format(selectRecordFromGrid,enterName)))).perform();
        }
    }

    /** Select Request Person Name From Active Level 2
     * @param enterName = Enter Person Name */
    public void selectRequestPersonName(String enterName) throws InterruptedException
    {
        wait = new WebDriverWait(webDriver, Duration.ofSeconds(30));
        for (int i = 1; i < 10; i++)
        {
            if (!webDriver.findElement(By.xpath(String.format(selectRequestPersonName,enterName))).isDisplayed())
            {
                Thread.sleep(2000);
            }
        }
        wait.until(ExpectedConditions.visibilityOf(webDriver.findElement(By.xpath(String.format(selectRequestPersonName,enterName)))));
        webDriver.findElement(By.xpath(String.format(selectRequestPersonName,enterName))).click();
    }

    /** Get Status Of Requested Data
     * @param enterName = Enter Requested Person Name
     * @return Return Status Of Requested Data From The Status Panel Of Bottom*/
    public String getStatusOfRequestedData(String enterName,String enterStatus)
    {
        wait = new WebDriverWait(webDriver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.visibilityOf(webDriver.findElement(By.xpath(String.format(getRequestedDataStatus,enterName,enterStatus)))));
        return webDriver.findElement(By.xpath(String.format(getRequestedDataStatus,enterName,enterStatus))).getText();
    }

    /** Select Requestor Data From Grid Of Report
     * @param enterName = Enter Person Name Of Level 2 Container */
    public void selectRequestorData(String enterName)
    {
        wait = new WebDriverWait(webDriver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.visibilityOf(webDriver.findElement(By.xpath(String.format(selectRequestorData,enterName)))));
        webDriver.findElement(By.xpath(String.format(selectRequestorData,enterName))).click();
        softAssert.assertTrue(webDriver.findElement(By.xpath(String.format(selectRequestorData,enterName))).isDisplayed());
        softAssert.assertAll();
    }

    /** Select Transfer DropDown
     * @param enterValue = Enter DropDown Name (EX: Location, Employees)*/
    public void selectTransferDropDown(String enterValue)
    {
        wait = new WebDriverWait(webDriver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.visibilityOf(webDriver.findElement(By.xpath(String.format(selectTransferDropDown,enterValue)))));
        webDriver.findElement(By.xpath(String.format(selectTransferDropDown,enterValue))).click();
    }

    /** Select Transfer DropDown
     * @param enterOption = Enter Option Name (EX: Location, Employees)*/
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

    @FindBy(xpath = "(//div[@class='btn-group'])[2]")
    public WebElement selectPaperPlaneIcon;

    @FindBy(xpath = "//a[@id='btnRequest']")
    public WebElement requestOption;

    @FindBy(xpath = "//label[@id='dlgBsTitle']")
    public WebElement requestPopup;

    @FindBy(xpath = "//ul[@id='requestlst']//li")
    public WebElement getSelectedRecordOnRequestPopup;

    @FindBy(xpath = "//tbody[@id='employeeContainer']//tr//td//span[@name='rdlEmployees']")
    public List<WebElement> getAllLevel2Data;

    @FindBy(xpath = "//a[@id='btnTransfer']")
    public WebElement selectTransferSelectedOption;

    @FindBy(xpath = "//button[@id='btnSubmitRequest']")
    public WebElement requestButton;

    @FindBy(xpath = "//div[contains(text(),'Record/s has been Requested successfully')]")
    public WebElement verifyRequestSuccessMSG;

    @FindBy(xpath = "//div[contains(text(),'Requests Wait List')]/a")
    public WebElement expandTrackingStatusWindow;

    @FindBy(xpath = "//div[contains(text(),'Requests Wait List')]/a")
    public WebElement hideTrackingStatusWindow;

    @FindBy(xpath = "//a[contains(text(),'Requestor Reports')]")
    public WebElement verifyRequestorReportsVisible;

    @FindBy(xpath = "//input[@id='sendcheckedItems']")
    public WebElement sendSelectedItemsIntoPullListButton;

    @FindBy(xpath = "//h1[@id='ReportTitle' and contains(text(),'Pull List Report')]")
    public WebElement verifyAfterRecordMoveToPullList;

    @FindBy(xpath = "//div[@id='ToolBar']//button//img[contains(@src,'refresh')]")
    public WebElement refreshGridButton;

    @FindBy(xpath = "//div[@id='spinningWheel' and @style='display: block;']")
    public WebElement gridRefreshLoadingIcon;

    @FindBy(xpath = "//button[@id='btnTransferItems']")
    public WebElement selectTransferButton;

    @FindBy(xpath = "//div[contains(text(),'Record/s has been Transferred successfully')]")
    public WebElement verifyTransferSuccessMSG;

    @FindBy(xpath = "//div[@id='TrackingLeft']//span[@id='lblTracking']")
    public WebElement verifyAfterTransferInCurrentLocation;
}
