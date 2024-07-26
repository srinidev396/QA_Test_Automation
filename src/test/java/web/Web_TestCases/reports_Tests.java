package web.Web_TestCases;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import utilities.web_api_BaseClass;
import web.Web_TestSteps.grid_Test_Steps;
import web.Web_TestSteps.login_Test_Steps;
import web.Web_TestSteps.reports_Test_Steps;

import java.io.IOException;

public class reports_Tests extends web_api_BaseClass {

    public login_Test_Steps loginTestSteps;
    public reports_Test_Steps reportsTestSteps;
    public grid_Test_Steps gridTestSteps;

    /** FUS - 12880 Generating Audit Report with proper data */
    @Test(groups = {"Smoke"})
    public void generating_Audit_Report_With_Proper_Data() throws IOException, InterruptedException
    {
        testName.set("Generating Audit Report With Proper Data Of Table");

        loginTestSteps = new login_Test_Steps(webDriver);
        gridTestSteps = new grid_Test_Steps(webDriver);
        reportsTestSteps = new reports_Test_Steps(webDriver);

        loginTestSteps.openLoginPage();
        loginTestSteps.selectDatabase(prop.getProperty("Web_Database"));
        loginTestSteps.enterUsernamePassword(prop.getProperty("Web_Valid_Username"),prop.getProperty("Web_Password"));
        loginTestSteps.signInButton();
        loginTestSteps.verifyHomePage();
        loginTestSteps.verifyUserLoginSuccess();

        gridTestSteps.selectNavigationOption("AutomationWG");
        gridTestSteps.selectNavigationOption("Suppliers");
        gridTestSteps.selectNavigationOption("All Suppliers");
        gridTestSteps.verifyQueryPopupAndPressOk();
        gridTestSteps.verifySelectedViewIsOpened("All Suppliers");

        reportsTestSteps.addedNewRecord();
        reportsTestSteps.getCreatedDataID("TestDemo");
        reportsTestSteps.openReportsPage();
        reportsTestSteps.selectAuditReportsOption();
        reportsTestSteps.selectUserName("administrator");
        reportsTestSteps.selectTable("Suppliers");
        reportsTestSteps.enterFromDate();
        reportsTestSteps.enterToDate();
        reportsTestSteps.selectCheckBox();
        reportsTestSteps.verifyAddedRecordInAuditReport("Add");
    }

    /** FUS - 13241 Generating an existing Custom Report with proper data */
    @Test(groups = {"Smoke"})
    public void generating_Existing_Custom_Report_With_Proper_Data() throws IOException, InterruptedException
    {
        testName.set("Generating Existing Custom Report With Proper Data of Table");

        loginTestSteps = new login_Test_Steps(webDriver);
        gridTestSteps = new grid_Test_Steps(webDriver);
        reportsTestSteps = new reports_Test_Steps(webDriver);

        loginTestSteps.openLoginPage();
        loginTestSteps.selectDatabase(prop.getProperty("Web_Database"));
        loginTestSteps.enterUsernamePassword(prop.getProperty("Web_Valid_Username"),prop.getProperty("Web_Password"));
        loginTestSteps.signInButton();
        loginTestSteps.verifyHomePage();
        loginTestSteps.verifyUserLoginSuccess();

        gridTestSteps.selectNavigationOption("AutomationWG");
        gridTestSteps.selectNavigationOption("Suppliers");
        gridTestSteps.selectNavigationOption("All Suppliers");
        gridTestSteps.verifyQueryPopupAndPressOk();
        gridTestSteps.verifySelectedViewIsOpened("All Suppliers");

        reportsTestSteps.getTotalRecord();
        reportsTestSteps.openReportsPage();
        reportsTestSteps.selectCustomReportOption();
        reportsTestSteps.getTotalCustomReportRecord();
        reportsTestSteps.verifyGridAndCustomReportTotalRecords();
    }

    /** Tear Down method executing according to test after complete test
     * @param result = They get test method name */
    @AfterMethod(groups = {"setUp"},alwaysRun = true)
    public void tearDown(ITestResult result) throws IOException
    {
        testReport(result);

        if (result.getStatus() == result.SUCCESS || result.getStatus() == result.FAILURE)
        {
            if (result.getMethod().getMethodName().equals("generating_Audit_Report_With_Proper_Data"))
            {
                try {
                    reportsTestSteps.deleteCreatedRecord();
                    reportsTestSteps.selectAuditReportsOption();
                    reportsTestSteps.selectUserName("administrator");
                    reportsTestSteps.selectTable("Suppliers");
                    reportsTestSteps.enterFromDate();
                    reportsTestSteps.enterToDate();
                    reportsTestSteps.selectCheckBox();
                    reportsTestSteps.verifyDeletedRecordInAuditReport("Delete");
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
