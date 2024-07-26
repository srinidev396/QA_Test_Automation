package web.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.web_api_BaseClass;

import java.time.Duration;
import java.util.List;

public class queryAndFavorite_PO extends web_api_BaseClass {

    /** This file contains all the xpaths of the Home page of the Tab FusionRMS
     * @param webDriver = initialized Webdriver
     * */
    public queryAndFavorite_PO(WebDriver webDriver) {
        web_api_BaseClass.webDriver = webDriver;
        PageFactory.initElements(webDriver,this);
    }

    public WebDriverWait wait;
    public String selectConditionFromQueryFieldDropdown = "//td[@title='%s']/parent::tr/td/select";
    public String enterConditionValueFromQueryField = "//td[@title='%s']/parent::tr/td[3]/input";
    public String verifyQueryCreatedSuccessMSG = "//div[contains(text(),'%s has been created successfully')]";
    public String deleteAndVerifyQueryDeletedFromMyQueries = "//a[contains(text(),'%s')]/parent::div/parent::div/div/i";
    public String enterNewFavoriteName = "//input[@id='%s']";
    public String selectCreatedFavoriteNameFromDropDown = "//select[@id='%s']";
    public String selectFavoriteFromMyFavorite = "//a[@data-viewname='%s']";

    /** Select condition from dropdown of any field from query popup
     * @param fieldName = Enter field label name (EX: DeptId, DepartmentName)
     * @param enterCondition = Enter Regular Expression for condition
     *                       (EX: =,<>,>,>=,<,<=,Between,BEG,Ends with,Contains,Not contains) */
    public void selectConditionFromQueryFieldDropdown(String fieldName, String enterCondition)
    {
        Select drpQueryFieldConditionDropdown = new Select(webDriver.findElement(By.xpath(String.format(selectConditionFromQueryFieldDropdown,fieldName))));
        drpQueryFieldConditionDropdown.selectByValue(enterCondition);
    }

    /** Enter condition value of the field
     * @param fieldName = Enter field label name (EX: DeptId, DepartmentName)
     * @param enterValue = Enter value of the field (EX: 4, Sales, 22/11/2024, etc.) */
    public void enterConditionValueFromQueryField(String fieldName, String enterValue)
    {
        webDriver.findElement(By.xpath(String.format(enterConditionValueFromQueryField,fieldName))).sendKeys(enterValue);
    }

    /** verify acknowledgement message after created new query
     * @param savedQueryName = Enter Query Name whatever you have given at the time of save
     *                       (EX: Test Demo, Department, etc.) */
    public void verifyQueryCreatedSuccessMSG(String savedQueryName)
    {
        wait = new WebDriverWait(webDriver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOf(webDriver.findElement(By.xpath(String.format(verifyQueryCreatedSuccessMSG,savedQueryName)))));
        softAssert.assertTrue(webDriver.findElement(By.xpath(String.format(verifyQueryCreatedSuccessMSG,savedQueryName))).isDisplayed());
        wait.until(ExpectedConditions.invisibilityOf(webDriver.findElement(By.xpath(String.format(verifyQueryCreatedSuccessMSG,savedQueryName)))));
        softAssert.assertAll();
    }

    /** Delete and verify query from my queries
     * @param queryName = Enter Saved Query Name (EX: Test Demo) */
    public void deleteAndVerifyQueryDeletedFromMyQueries(String queryName)
    {
        wait = new WebDriverWait(webDriver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.visibilityOf(webDriver.findElement(By.xpath(String.format(deleteAndVerifyQueryDeletedFromMyQueries,queryName)))));
        softAssert.assertTrue(webDriver.findElement(By.xpath(String.format(deleteAndVerifyQueryDeletedFromMyQueries,queryName))).isDisplayed());
        webDriver.findElement(By.xpath(String.format(deleteAndVerifyQueryDeletedFromMyQueries,queryName))).click();
        wait.until(ExpectedConditions.invisibilityOf(webDriver.findElement(By.xpath(String.format(deleteAndVerifyQueryDeletedFromMyQueries,queryName)))));
        softAssert.assertAll();
    }

    /** Enter New Favorite Name
     * @param name = Enter New Favorite Name */
    public void enterNewFavoriteName(String name)
    {
        webDriver.findElement(By.xpath(String.format(enterNewFavoriteName,"newfavinput"))).click();
        webDriver.findElement(By.xpath(String.format(enterNewFavoriteName,"newfavinput"))).sendKeys(name);
    }

    /** Select Created favorite name from dropdown
     * @param favName = Enter Favorite Name */
    public void selectCreatedFavoriteNameFromDropDown(String favName)
    {
        Select drpFavName = new Select(webDriver.findElement(By.xpath(String.format(selectCreatedFavoriteNameFromDropDown,"DDLfavorite"))));
        drpFavName.selectByVisibleText(favName);
    }

    /** Select Favorite from My Favorites
     * @param favName = Enter Favorite Name*/
    public void selectFavoriteFromMyFavorite(String favName)
    {
        webDriver.findElement(By.xpath(String.format(selectFavoriteFromMyFavorite,favName))).click();
    }

    @FindBy(xpath = "//table[@class='htCore']//tr//td[2]")
    public List<WebElement> getDeptIdList;

    @FindBy(xpath = "//input[@id='btnQuery']")
    public WebElement selectQueryButton;

    @FindBy(xpath = "//label[@id='QuerylblTitle']")
    public WebElement editingQueryName;

    @FindBy(xpath = "//input[@id='QuerySaveInput']")
    public WebElement enterQueryName;

    @FindBy(xpath = "//button[@id='btnSaveQuery']")
    public WebElement selectSaveQueryButton;

    @FindBy(xpath = "//button[@id='btnCancel']")
    public WebElement selectCancelButton;

    @FindBy(xpath = "//div[@id='divFavOptions']")
    public WebElement selectHeartIcon;

    @FindBy(xpath = "//a[@id='btnAddFavourite']")
    public WebElement selectNewFavoriteOption;

    @FindBy(xpath = "//label[@id='dlgBsTitle']")
    public WebElement verifyNewFavoritePopup;

    @FindBy(xpath = "//button[@id='favoriveOk']")
    public WebElement saveFavoriteButton;

    @FindBy(xpath = "//div[contains(text(),'My Favorite has been created successfully')]")
    public WebElement verifyNewFavoriteCreatedAcknowledgementMSG;

    @FindBy(xpath = "//a[@id='btnUpdateFavourite']")
    public WebElement selectAddToFavoriteOption;

    @FindBy(xpath = "//button[@id='AddtofavoriteOK']")
    public WebElement saveAddToFavoriteButton;

    @FindBy(xpath = "//div[contains(text(),'My Favorite has been updated with selected record/s successfully')]")
    public WebElement verifyAddToFavoriteAcknowledgementMSG;

    @FindBy(xpath = "//table[@class='htCore']//tr//td[3]//div")
    public List<WebElement> verifyAddedFavoriteVisibleInGrid;

}
