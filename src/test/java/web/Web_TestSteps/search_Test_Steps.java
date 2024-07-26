package web.Web_TestSteps;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.web_api_BaseClass;
import web.pageObjects.grid_Table_PO;
import web.pageObjects.search_PO;

import java.time.Duration;

public class search_Test_Steps extends web_api_BaseClass {

    WebDriver driver;

    /**
     * Class constructor define webdriver
     * @param remoteDriver = Mention webdriver */
    public search_Test_Steps(WebDriver remoteDriver) {
        driver = remoteDriver;
    }

    public search_PO searchPo;
    public grid_Table_PO gridTablePo;
    public WebDriverWait wait;
    public int getFoundRecordCountOnPopup ;
    
    /** Enter Global Search Text
     * @param enterSearchText = Enter Search Text*/
    public void enterGlobalSearch(String enterSearchText)
    {
        searchPo = new search_PO(webDriver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        searchPo.globalSearchField.click();
        searchPo.globalSearchField.sendKeys(enterSearchText);
        searchPo.globalSearchButton.click();
        wait.until(ExpectedConditions.visibilityOf(searchPo.verifyPopupOpened));
        softAssert.assertTrue(searchPo.verifyPopupOpened.isDisplayed());
        softAssert.assertAll();
    }

    /** Expand searched result from popup and verify table visible
     * @param tableName = Enter Table Name you want to verify */
    public void expandSearchListFromPopupAndVerifyTableVisible(String tableName)
    {
        searchPo = new search_PO(webDriver);
        gridTablePo = new grid_Table_PO(webDriver);

        wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        getFoundRecordCountOnPopup = Integer.parseInt(webDriver.findElement(By.xpath(searchPo.getFoundRecordCountOnPopup)).getText().replaceAll("[^0-9]",""));

        if (searchPo.verifyResultIsDisplayed.isDisplayed())
        {
            System.out.println(webDriver.findElement(By.xpath(searchPo.getFoundRecordCountOnPopup)).getText());
            searchPo.expandSearchResultShowAllLink.click();
            searchPo.verifySearchedDataTableVisible(tableName);
            gridTablePo.selectClosePopupButton.click();
        }
        else
        {
            softAssert.assertTrue(searchPo.verifyNoResultFoundGlobalSearch.isDisplayed());
            System.out.println(searchPo.verifyNoResultFoundGlobalSearch.getText());
        }
        softAssert.assertAll();
    }

    /** Verify search Data is visible in grid table */
    public void verifySearchedDataIsVisible(String enterSearchedText)
    {
        searchPo = new search_PO(webDriver);
        int getGridDisplayedRecordSize = webDriver.findElements(By.xpath(searchPo.getXpath(searchPo.getGridDisplayedRecordSize,enterSearchedText))).size();
        softAssert.assertEquals(getFoundRecordCountOnPopup,getGridDisplayedRecordSize);
        softAssert.assertAll();
    }
}
