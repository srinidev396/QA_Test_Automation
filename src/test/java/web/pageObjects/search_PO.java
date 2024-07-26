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

public class search_PO extends web_api_BaseClass {

    /** This file contains all the xpaths of the Home page of the Tab FusionRMS
     * @param webDriver = initialized Webdriver
     * */
    public search_PO(WebDriver webDriver) {
        web_api_BaseClass.webDriver = webDriver;
        PageFactory.initElements(webDriver,this);
    }

    public WebDriverWait wait;
    public String getFoundRecordCountOnPopup = "//div[@id='dlgbodyId']/table//tr[1]/td/b";
    public String getGridDisplayedRecordSize = "//div[contains(text(),'%s')]/parent::td";
    public String verifySearchedDataTableVisible = "//a[contains(text(),'Show All') and @data-viewname='%s']";

    /** By using this method get the dynamic XPATH
     * @param dynamicXpath = Enter Xpath with dynamic value symbol with %s
     * @param dynamicValue = Enter dynamic value to enter into the xpath of %s */
    public String getXpath(String dynamicXpath, String dynamicValue)
    {
        return String.format(dynamicXpath,dynamicValue);
    }

    /** Verify Searched Data Table Is Visible Or Not After Click On Show All Link From Popup
     * @param tableName = Enter Table Name Where you want to find record */
    public void verifySearchedDataTableVisible(String tableName)
    {
        wait = new WebDriverWait(webDriver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.visibilityOf(webDriver.findElement(By.xpath(getXpath(verifySearchedDataTableVisible,tableName)))));
        softAssert.assertTrue(webDriver.findElement(By.xpath(getXpath(verifySearchedDataTableVisible,tableName))).isDisplayed());
    }

    @FindBy(xpath = "//label[@id='dlgBsTitle']")
    public WebElement verifyPopupOpened;

    @FindBy(xpath = "//input[@id='txtSearch']")
    public WebElement globalSearchField;

    @FindBy(xpath = "//button[@id='btnSearch']")
    public WebElement globalSearchButton;

    @FindBy(xpath = "//div[@id='dlgbodyId']/table")
    public WebElement verifyResultIsDisplayed;

    @FindBy(xpath = "//div[contains(text(),'No matching results found')]")
    public WebElement verifyNoResultFoundGlobalSearch;

    @FindBy(xpath = "//a[contains(text(),'Show All')]")
    public WebElement expandSearchResultShowAllLink;

}
