package web.Web_TestSteps;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.web_api_BaseClass;
import web.pageObjects.grid_Navigation_PO;
import web.pageObjects.queryAndFavorite_PO;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class queryAndFavorite_Test_Steps extends web_api_BaseClass {

    WebDriver driver;

    /**
     * Class constructor define webdriver
     * @param remoteDriver = Mention webdriver */
    public queryAndFavorite_Test_Steps(WebDriver remoteDriver)
    {
        driver = remoteDriver;
    }

    public WebDriverWait wait;
    public queryAndFavorite_PO queryAndFavoritePo;
    public grid_Navigation_PO gridNavigationPo;

    /** Click on Query button from grid */
    public void selectQueryButton()
    {
        queryAndFavoritePo = new queryAndFavorite_PO(webDriver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.visibilityOf(queryAndFavoritePo.selectQueryButton));
        queryAndFavoritePo.selectQueryButton.click();
    }

    /** Verify Query popup is visible when click on view of record */
    public void verifyMyQueryPopupVisible()
    {
        gridNavigationPo = new grid_Navigation_PO(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.visibilityOf(gridNavigationPo.verifyQueryContentPopup));
        softAssert.assertTrue(gridNavigationPo.verifyQueryContentPopup.isDisplayed());
        softAssert.assertAll();
    }

    /** Select Condition and Enter value for same */
    public void selectConditionAndEnterValue()
    {
        queryAndFavoritePo = new queryAndFavorite_PO(webDriver);
        queryAndFavoritePo.selectConditionFromQueryFieldDropdown("DeptId",">");
        queryAndFavoritePo.enterConditionValueFromQueryField("DeptId","4");
    }

    /** Enter query name and save with it */
    public void saveQueryWithName() throws InterruptedException {
        queryAndFavoritePo = new queryAndFavorite_PO(webDriver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        queryAndFavoritePo.editingQueryName.click();
        queryAndFavoritePo.enterQueryName.clear();
        queryAndFavoritePo.enterQueryName.sendKeys("Test Demo");
        queryAndFavoritePo.selectSaveQueryButton.click();
        queryAndFavoritePo.verifyQueryCreatedSuccessMSG("Test Demo");
        queryAndFavoritePo.selectCancelButton.click();
        wait.until(ExpectedConditions.invisibilityOf(queryAndFavoritePo.selectCancelButton));
    }

    /** Verify Selected Query popup is visible */
    public void verifySelectedQueryPopupVisible()
    {
        gridNavigationPo = new grid_Navigation_PO(driver);
        queryAndFavoritePo = new queryAndFavorite_PO(webDriver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.visibilityOf(queryAndFavoritePo.enterQueryName));
        softAssert.assertTrue(queryAndFavoritePo.enterQueryName.isDisplayed());
        gridNavigationPo.okButton.click();
        softAssert.assertAll();
    }

    /** Delete query from My Queries */
    public void tearDown_Delete_Query_From_My_Queries()
    {
        queryAndFavoritePo = new queryAndFavorite_PO(webDriver);
        queryAndFavoritePo.deleteAndVerifyQueryDeletedFromMyQueries("Test Demo");
    }

    /** Select New favorite option form dropdown */
    public void selectNewFavorite()
    {
        queryAndFavoritePo = new queryAndFavorite_PO(webDriver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.visibilityOf(queryAndFavoritePo.selectHeartIcon));
        queryAndFavoritePo.selectHeartIcon.click();
        queryAndFavoritePo.selectNewFavoriteOption.click();
        wait.until(ExpectedConditions.visibilityOf(queryAndFavoritePo.verifyNewFavoritePopup));
    }

    /** Enter New favorite Name */
    public void enterCreateNewFavoriteName()
    {
        queryAndFavoritePo = new queryAndFavorite_PO(webDriver);
        queryAndFavoritePo.enterNewFavoriteName("Demo Favorite");
        queryAndFavoritePo.saveFavoriteButton.click();
    }

    /** Verify acknowledgement message after created new favorite */
    public void verifyNewFavoriteCreatedSuccessfullyMSG()
    {
        queryAndFavoritePo = new queryAndFavorite_PO(webDriver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOf(queryAndFavoritePo.verifyNewFavoriteCreatedAcknowledgementMSG));
        softAssert.assertTrue(queryAndFavoritePo.verifyNewFavoriteCreatedAcknowledgementMSG.isDisplayed());
        wait.until(ExpectedConditions.invisibilityOf(queryAndFavoritePo.verifyNewFavoriteCreatedAcknowledgementMSG));
        softAssert.assertAll();
    }

    /** Select Add To favorite option form dropdown */
    public void selectAddToFavorite()
    {
        queryAndFavoritePo = new queryAndFavorite_PO(webDriver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        queryAndFavoritePo.selectHeartIcon.click();
        queryAndFavoritePo.selectAddToFavoriteOption.click();
        wait.until(ExpectedConditions.visibilityOf(queryAndFavoritePo.verifyNewFavoritePopup));
    }

    /** Select Added favorite name from dropdown */
    public void selectFavoriteNameFromDropdown()
    {
        queryAndFavoritePo = new queryAndFavorite_PO(webDriver);
        queryAndFavoritePo.selectCreatedFavoriteNameFromDropDown("Demo Favorite");
        queryAndFavoritePo.saveAddToFavoriteButton.click();
    }

    /** Verify Add To Favorite acknowledgement message */
    public void verifyAddToFavoriteSuccessfullyMSG()
    {
        queryAndFavoritePo = new queryAndFavorite_PO(webDriver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOf(queryAndFavoritePo.verifyAddToFavoriteAcknowledgementMSG));
        softAssert.assertTrue(queryAndFavoritePo.verifyAddToFavoriteAcknowledgementMSG.isDisplayed());
        wait.until(ExpectedConditions.invisibilityOf(queryAndFavoritePo.verifyAddToFavoriteAcknowledgementMSG));
        softAssert.assertAll();
    }

    /** Select Created Favorite from my favorites
     * @param favName = Enter Created Favorite Name */
    public void selectCreatedFavoriteFromMyFavorite(String favName)
    {
        queryAndFavoritePo = new queryAndFavorite_PO(webDriver);
        queryAndFavoritePo.selectFavoriteFromMyFavorite(favName);
    }

    /** Verify created query condition value displayed in table */
    public void verifyAccordingMyQueryConditionRecordVisible()
    {
        queryAndFavoritePo = new queryAndFavorite_PO(webDriver);
        List<WebElement> elementList = queryAndFavoritePo.getDeptIdList;

        for (WebElement e : elementList)
        {
            softAssert.assertTrue(Integer.parseInt(e.getText()) > 4);
        }
        softAssert.assertAll();
    }

    /** Verify selected favorite shows data according to added data into grid */
    public void verifyAddedFavoriteDataIsVisibleIntoGrid()
    {
        queryAndFavoritePo = new queryAndFavorite_PO(webDriver);

        List<WebElement> elementList = queryAndFavoritePo.verifyAddedFavoriteVisibleInGrid;
        List<String> expectedList = new ArrayList<>()
        {{add("HR");
        }};
        List<String> finalList = new ArrayList<>();
        for (WebElement e : elementList)
        {
            finalList.add(e.getText());
        }
        softAssert.assertEquals(finalList,expectedList);
        softAssert.assertAll();
    }

    /** Delete added favorite from my favorites
     * @param favName = Enter Favorite Name */
    public void tearDown_Delete_Created_Favorite_From_MyFavorite(String favName)
    {
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.visibilityOf(webDriver.findElement(By.xpath("//a[@data-viewname='"+favName+"']//parent::div//parent::div//div[2]//i"))));
        softAssert.assertTrue(webDriver.findElement(By.xpath("//a[@data-viewname='"+favName+"']//parent::div//parent::div//div[2]//i")).isDisplayed());
        webDriver.findElement(By.xpath("//a[@data-viewname='"+favName+"']//parent::div//parent::div//div[2]//i")).click();
        softAssert.assertAll();
    }
}
