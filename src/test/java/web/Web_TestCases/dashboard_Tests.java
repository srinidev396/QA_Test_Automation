package web.Web_TestCases;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import utilities.web_api_BaseClass;
import web.Web_TestSteps.dashboard_Test_Steps;
import web.Web_TestSteps.login_Test_Steps;

import java.io.IOException;

public class dashboard_Tests extends web_api_BaseClass {

    public login_Test_Steps loginTestSteps;
    public dashboard_Test_Steps dashboardTestSteps;

    /** FUS - 12878	Creating a new Dashboard report using the available widgets */
    @Test(groups = {"Smoke"})
    public void creating_New_Dashboard_Report_Using_Available_Widgets() throws IOException
    {
        testName.set("Creating New Dashboard Report Using Available Widgets");

        loginTestSteps = new login_Test_Steps(webDriver);
        dashboardTestSteps = new dashboard_Test_Steps(webDriver);

        loginTestSteps.openLoginPage();
        loginTestSteps.selectDatabase(prop.getProperty("Web_Database"));
        loginTestSteps.enterUsernamePassword(prop.getProperty("Web_Valid_Username"),prop.getProperty("Web_Password"));
        loginTestSteps.signInButton();
        loginTestSteps.verifyHomePage();
        loginTestSteps.verifyUserLoginSuccess();

        dashboardTestSteps.selectDashboardFromMenu();
        dashboardTestSteps.selectCreateNewDashboard();
        dashboardTestSteps.enterNewDashboardName("Test Automation");
        dashboardTestSteps.verifyCreatedDashboardInList("Test Automation");
        dashboardTestSteps.selectDashboardFromList("Test Automation");
        dashboardTestSteps.selectAddWidget();
        dashboardTestSteps.selectChartFromOptions("BAR");
        dashboardTestSteps.enterWidgetDetails();
        dashboardTestSteps.verifyChartWidgetAddedInFrame("TEST AUTOMATION");
    }

    /** FUS - 12879 Opening an existing Dashboard report using the available widgets */
    @Test(groups = {"Smoke"})
    public void opening_Existing_Dashboard_Report_Using_Available_Widgets() throws IOException
    {
        testName.set("Opening Existing Dashboard Report Using Available Widgets");

        loginTestSteps = new login_Test_Steps(webDriver);
        dashboardTestSteps = new dashboard_Test_Steps(webDriver);

        loginTestSteps.openLoginPage();
        loginTestSteps.selectDatabase(prop.getProperty("Web_Database"));
        loginTestSteps.enterUsernamePassword(prop.getProperty("Web_Valid_Username"),prop.getProperty("Web_Password"));
        loginTestSteps.signInButton();
        loginTestSteps.verifyHomePage();
        loginTestSteps.verifyUserLoginSuccess();

        dashboardTestSteps.selectDashboardFromMenu();
        dashboardTestSteps.selectDashboardFromList("Org Chart");
        dashboardTestSteps.verifyChartWidgetAddedInFrame("ORG CHART DASHBOARD");
    }

    /** Tear Down method executing according to test after complete test
     * @param result = They get test method name */
    @AfterMethod(groups = {"setUp"},alwaysRun = true)
    public void tearDown(ITestResult result) throws IOException
    {
        testReport(result);

        if (result.getStatus() == result.SUCCESS || result.getStatus() == result.FAILURE)
        {
            if (result.getMethod().getMethodName().equals("creating_New_Dashboard_Report_Using_Available_Widgets"))
            {
                try {
                    webDriver.navigate().refresh();
                    dashboardTestSteps.selectDashboardFromList("Test Automation");
                    dashboardTestSteps.deleteDashboardFromList();
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
            else
            {
                System.out.println("In this test case don't have any Tear Down steps !!!");
            }
        }

        closeBrowser();
    }
}
