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
import java.util.List;

public class dashboard_PO extends web_api_BaseClass {

    /** This file contains all the xpaths of the Home page of the Tab FusionRMS
     * @param webDriver = initialized Webdriver
     * */
    public dashboard_PO(WebDriver webDriver) {
        web_api_BaseClass.webDriver = webDriver;
        PageFactory.initElements(webDriver,this);
    }

    public WebDriverWait wait;
    public String selectDashboard = "//ul[@id='ulDashboardList']//li//a//xmp[contains(text(),'%s')]";
    public String selectChart = "(//tr[contains(@onclick,'%s')]//img)[2]";
    public String selectWorkGroupOptionFromDropDown = "//input[@id='chartlstWorkGroup-selectized']/parent::div/parent::div//div//div//div[contains(text(),'%s')]";
    public String selectObjectTableOptionFromDropDown = "//input[@id='chartListTable-selectized']/parent::div/parent::div//div//div//div[contains(text(),'%s')]";
    public String selectViewOptionFromDropDown = "//input[@id='chartlstView-selectized']/parent::div/parent::div//div//div//div[contains(text(),'%s')]";
    public String selectColumnOptionFromDropDown = "//input[@id='chartlstCol-selectized']/parent::div/parent::div//div//div//div[contains(text(),'%s')]";

    /** Select Dashboard From List
     * @param dashboardName = Enter Dashboard Name */
    public void selectDashboardFromList(String dashboardName)
    {
        webDriver.findElement(By.xpath(String.format(selectDashboard,dashboardName))).click();
    }

    /** Select Chart Form Options
     * @param chartName = Enter Chart Name (EX: BAR, PIE, DATA, CHART_1, CHART_2, CHART_3) */
    public void selectChartFromOptions(String chartName)
    {
        wait = new WebDriverWait(webDriver, Duration.ofSeconds(30));
        webDriver.findElement(By.xpath(String.format(selectChart,chartName))).click();
        wait.until(ExpectedConditions.visibilityOf(verifyChartDetailPopup));
    }

    /** Select Work Group Options From DropDown
     * @param workGroupName = Enter Work Group Option Name */
    public void selectWorkGroupOptionsFromDropDown(String workGroupName)
    {
        wait = new WebDriverWait(webDriver, Duration.ofSeconds(30));
        selectWorkGroupDropDown.click();
        wait.until(ExpectedConditions.visibilityOf(webDriver.findElement(By.xpath(String.format(selectWorkGroupOptionFromDropDown,workGroupName)))));
        webDriver.findElement(By.xpath(String.format(selectWorkGroupOptionFromDropDown,workGroupName))).click();
    }

    /** Select Object Table Option From DropDown
     * @param objectTableName = Enter Object/Table Name */
    public void selectObjectTableOptionsFromDropDown(String objectTableName)
    {
        wait = new WebDriverWait(webDriver, Duration.ofSeconds(30));
        selectObjectTableDropDown.click();
        wait.until(ExpectedConditions.visibilityOf(webDriver.findElement(By.xpath(String.format(selectObjectTableOptionFromDropDown,objectTableName)))));
        webDriver.findElement(By.xpath(String.format(selectObjectTableOptionFromDropDown,objectTableName))).click();
    }

    /** Select View Options From DropDown
     * @param viewName = Enter View Name */
    public void selectViewOptionsFromDropDown(String viewName)
    {
        wait = new WebDriverWait(webDriver, Duration.ofSeconds(30));
        selectViewDropDown.click();
        wait.until(ExpectedConditions.visibilityOf(webDriver.findElement(By.xpath(String.format(selectViewOptionFromDropDown,viewName)))));
        webDriver.findElement(By.xpath(String.format(selectViewOptionFromDropDown,viewName))).click();
    }

    /** Select Column Options From Drop Down
     * @param columnName = Enter Table Column Name */
    public void selectColumnOptionsFromDropDown(String columnName)
    {
        wait = new WebDriverWait(webDriver, Duration.ofSeconds(30));
        selectColumnDropDown.click();
        wait.until(ExpectedConditions.visibilityOf(webDriver.findElement(By.xpath(String.format(selectColumnOptionFromDropDown,columnName)))));
        webDriver.findElement(By.xpath(String.format(selectColumnOptionFromDropDown,columnName))).click();
    }

    @FindBy(xpath = "//button[@id='new-dashboard']")
    public WebElement selectNewDashboardButton;

    @FindBy(xpath = "//h4[@id='myModalmodalAddNewDashboard']")
    public WebElement verifyAddNewDashboardPopup;

    @FindBy(xpath = "//input[@id='txtNewDashboardName']")
    public WebElement enterNewDashboardName;

    @FindBy(xpath = "//button[@id='btnSaveDashboardName']")
    public WebElement saveDashboardButton;

    @FindBy(xpath = "//xmp[contains(text(),'Added Successfully')]")
    public WebElement verifyAddNewDashboardMSG;

    @FindBy(xpath = "//ul[@id='ulDashboardList']//li//a//xmp")
    public List<WebElement> verifyCreatedDashboardInList;

    @FindBy(xpath = "//button[@id='new-widget']")
    public WebElement selectNewWidgetButton;

    @FindBy(xpath = "//h4[contains(text(),'Choose a Widget')]")
    public WebElement verifyWidgetPopupVisible;

    @FindBy(xpath = "//h4[@id='titleAddEditChart']")
    public WebElement verifyChartDetailPopup;

    @FindBy(xpath = "//input[@id='chartWidgetName']")
    public WebElement enterWidgetName;

    @FindBy(xpath = "//input[@id='chartlstWorkGroup-selectized']/parent::div")
    public WebElement selectWorkGroupDropDown;

    @FindBy(xpath = "//input[@id='chartListTable-selectized']/parent::div")
    public WebElement selectObjectTableDropDown;

    @FindBy(xpath = "//input[@id='chartlstView-selectized']/parent::div")
    public WebElement selectViewDropDown;

    @FindBy(xpath = "//input[@id='chartlstCol-selectized']/parent::div")
    public WebElement selectColumnDropDown;

    @FindBy(xpath = "//button[@id='btnAddChart']")
    public WebElement addButton;

    @FindBy(xpath = "//xmp[contains(text(),'Added Successfully')]")
    public WebElement verifyChartAddedSuccessfullyMSG;

    @FindBy(xpath = "//h1[@id='spanWidgetHeading']//xmp")
    public WebElement verifyCreatedWidgetVisible;

    @FindBy(xpath = "//button[@id='del-dash']")
    public WebElement deleteDashboardButton;

    @FindBy(xpath = "//h4[@id='myModalDeleteDashboard']")
    public WebElement verifyDeleteDashboardPopup;

    @FindBy(xpath = "//button[@id='btnDeleteDashboard']")
    public WebElement yesDeleteDashboardButton;

    @FindBy(xpath = "//xmp[contains(text(),'Deleted Successfully')]")
    public WebElement verifyDeleteDashboardSuccessfullyMSG;
}