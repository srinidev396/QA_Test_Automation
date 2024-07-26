package web.Web_TestSteps;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.web_api_BaseClass;
import web.pageObjects.dashboard_PO;
import web.pageObjects.grid_Navigation_PO;

import java.time.Duration;
import java.util.ArrayList;

public class dashboard_Test_Steps extends web_api_BaseClass {

    WebDriver driver;

    /**
     * Class constructor define webdriver
     * @param remoteDriver = Mention webdriver */
    public dashboard_Test_Steps(WebDriver remoteDriver) {
        driver = remoteDriver;
    }

    public dashboard_PO dashboardPo;
    public grid_Navigation_PO gridNavigationPo;

    public WebDriverWait wait;

    /** Select Dashboard From Menu */
    public void selectDashboardFromMenu()
    {
        dashboardPo = new dashboard_PO(webDriver);
        gridNavigationPo = new grid_Navigation_PO(webDriver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        gridNavigationPo.selectNavbarMenuOption("Dashboard");

        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));
        wait.until(ExpectedConditions.visibilityOf(dashboardPo.selectNewDashboardButton));
    }

    /** Select New Dashboard Option */
    public void selectCreateNewDashboard()
    {
        dashboardPo = new dashboard_PO(webDriver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        dashboardPo.selectNewDashboardButton.click();
        wait.until(ExpectedConditions.visibilityOf(dashboardPo.verifyAddNewDashboardPopup));
        softAssert.assertTrue(dashboardPo.verifyAddNewDashboardPopup.isDisplayed());
        softAssert.assertAll();
    }

    /** Created And Enter New Dashboard Name
     * @param dashboardName = Enter Dashboard Name */
    public void enterNewDashboardName(String dashboardName)
    {
        dashboardPo = new dashboard_PO(webDriver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        dashboardPo.enterNewDashboardName.sendKeys(dashboardName);
        dashboardPo.saveDashboardButton.click();
        wait.until(ExpectedConditions.visibilityOf(dashboardPo.verifyAddNewDashboardMSG));
        wait.until(ExpectedConditions.invisibilityOf(dashboardPo.verifyAddNewDashboardMSG));
    }

    /** Verify Created Dashboard Is Visible In Dashboard List
     * @param dashboardName = Enter Dashboard Name */
    public void verifyCreatedDashboardInList(String dashboardName)
    {
        dashboardPo = new dashboard_PO(webDriver);
        ArrayList<String> dashboardList = new ArrayList<String>();

        for (WebElement element : dashboardPo.verifyCreatedDashboardInList)
        {
            dashboardList.add(element.getText());
        }
        System.out.println(dashboardList);
        softAssert.assertTrue(dashboardList.contains(dashboardName));
        softAssert.assertAll();
    }

    /** Select Dashboard From List
     * @param dashboardName = Enter Dashboard Name */
    public void selectDashboardFromList(String dashboardName)
    {
        dashboardPo = new dashboard_PO(webDriver);
        dashboardPo.selectDashboardFromList(dashboardName);
    }

    /** Select Add Widget */
    public void selectAddWidget()
    {
        dashboardPo = new dashboard_PO(webDriver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        dashboardPo.selectNewWidgetButton.click();
        wait.until(ExpectedConditions.visibilityOf(dashboardPo.verifyWidgetPopupVisible));
    }

    /** Select Chart From Chart Options
     * @param chartName = Enter Chart Name (EX: BAR, PIE, DATA, CHART_1, CHART_2, CHART_3)*/
    public void selectChartFromOptions(String chartName)
    {
        dashboardPo = new dashboard_PO(webDriver);
        dashboardPo.selectChartFromOptions(chartName);
    }

    /** Added All The Chart Widget Details */
    public void enterWidgetDetails() {
        dashboardPo = new dashboard_PO(webDriver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        dashboardPo.enterWidgetName.sendKeys("Test Automation");
        dashboardPo.selectWorkGroupOptionsFromDropDown("AutomationWG");
        dashboardPo.selectObjectTableOptionsFromDropDown("Department");
        dashboardPo.selectViewOptionsFromDropDown("All Department");
        dashboardPo.selectColumnOptionsFromDropDown("DepartmentName");
        dashboardPo.addButton.click();
        wait.until(ExpectedConditions.visibilityOf(dashboardPo.verifyChartAddedSuccessfullyMSG));
        softAssert.assertTrue(dashboardPo.verifyChartAddedSuccessfullyMSG.isDisplayed());
        wait.until(ExpectedConditions.invisibilityOf(dashboardPo.verifyChartAddedSuccessfullyMSG));
        softAssert.assertAll();
    }

    /** Verify Chart Widget Added In Frame */
    public void verifyChartWidgetAddedInFrame(String chartWidgetName)
    {
        dashboardPo = new dashboard_PO(webDriver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.visibilityOf(dashboardPo.verifyCreatedWidgetVisible));
        softAssert.assertEquals(dashboardPo.verifyCreatedWidgetVisible.getText(),chartWidgetName);
        softAssert.assertAll();
    }

    /** Delete Dashboard From The Dashboard List */
    public void deleteDashboardFromList()
    {
        dashboardPo = new dashboard_PO(webDriver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        dashboardPo.deleteDashboardButton.click();
        wait.until(ExpectedConditions.visibilityOf(dashboardPo.verifyDeleteDashboardPopup));
        softAssert.assertTrue(dashboardPo.verifyDeleteDashboardPopup.isDisplayed());
        dashboardPo.yesDeleteDashboardButton.click();
        wait.until(ExpectedConditions.visibilityOf(dashboardPo.verifyDeleteDashboardSuccessfullyMSG));
        softAssert.assertTrue(dashboardPo.verifyDeleteDashboardSuccessfullyMSG.isDisplayed());
        softAssert.assertAll();
    }
}
