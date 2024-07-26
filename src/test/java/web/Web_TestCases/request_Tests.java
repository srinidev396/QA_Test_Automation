package web.Web_TestCases;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import utilities.web_api_BaseClass;
import web.Web_TestSteps.grid_Test_Steps;
import web.Web_TestSteps.login_Test_Steps;
import web.Web_TestSteps.reports_Test_Steps;
import web.Web_TestSteps.request_Test_Steps;

import java.io.IOException;

public class request_Tests extends web_api_BaseClass {

    public login_Test_Steps loginTestSteps;
    public reports_Test_Steps reportsTestSteps;
    public grid_Test_Steps gridTestSteps;
    public request_Test_Steps requestTestSteps;

    /** FUS - 13242 Request Window Shows Only Active Level-2 Containers  */
    @Test(groups = {"Smoke"})
    public void request_Window_Shows_Only_Active_Level2_Containers() throws IOException, InterruptedException
    {
        testName.set("Verify Request Window Shows only Active Level2 Containers");

        loginTestSteps = new login_Test_Steps(webDriver);
        gridTestSteps = new grid_Test_Steps(webDriver);
        reportsTestSteps = new reports_Test_Steps(webDriver);
        requestTestSteps = new request_Test_Steps(webDriver);

        loginTestSteps.openLoginPage();
        loginTestSteps.selectDatabase(prop.getProperty("Web_Database"));
        loginTestSteps.enterUsernamePassword(prop.getProperty("Web_Valid_Username"),prop.getProperty("Web_Password"));
        loginTestSteps.signInButton();
        loginTestSteps.verifyHomePage();
        loginTestSteps.verifyUserLoginSuccess();

        gridTestSteps.selectNavigationOption("Tracking");
        gridTestSteps.selectNavigationOption("Boxes");
        gridTestSteps.selectNavigationOption("All Boxes");
        gridTestSteps.verifyQueryPopupAndPressOk();
        gridTestSteps.verifySelectedViewIsOpened("All Boxes");

        requestTestSteps.selectRecordFromBoxesGrid("Inactive Employee Records");
        requestTestSteps.selectRequestOption();
        requestTestSteps.verifySelectedRecord("Inactive Employee Records");
        requestTestSteps.verifyActiveLevel2ContainerDisplayed();
    }

    /** FUS - 13243 Request Workflow New - In Process - Fulfilled */
    @Test(groups = {"Smoke"})
    public void request_WorkFlow_New_InProcess_Fulfilled() throws IOException, InterruptedException
    {
        testName.set("Request Workflow Of New InProcess Fulfilled");

        loginTestSteps = new login_Test_Steps(webDriver);
        gridTestSteps = new grid_Test_Steps(webDriver);
        reportsTestSteps = new reports_Test_Steps(webDriver);
        requestTestSteps = new request_Test_Steps(webDriver);

        loginTestSteps.openLoginPage();
        loginTestSteps.selectDatabase(prop.getProperty("Web_Database"));
        loginTestSteps.enterUsernamePassword(prop.getProperty("Web_Valid_Username"),prop.getProperty("Web_Password"));
        loginTestSteps.signInButton();
        loginTestSteps.verifyHomePage();
        loginTestSteps.verifyUserLoginSuccess();

        gridTestSteps.selectNavigationOption("Tracking");
        gridTestSteps.selectNavigationOption("Boxes");
        gridTestSteps.selectNavigationOption("All Boxes");
        gridTestSteps.verifyQueryPopupAndPressOk();
        gridTestSteps.verifySelectedViewIsOpened("All Boxes");

        requestTestSteps.selectRecordFromBoxesGrid("Automation Request Box");
        requestTestSteps.selectRequestOption();
        requestTestSteps.verifySelectedRecord("Automation Request Box");
        requestTestSteps.selectPersonName("Yoe Howell");
        requestTestSteps.selectRequestButton();
        requestTestSteps.verifyRequestedStatus("Yoe Howell","New");
        requestTestSteps.verifyOnReports();

        gridTestSteps.selectNavigationOption("Requestor Reports");
        gridTestSteps.selectNavigationOption("New Requests");

        requestTestSteps.selectRequestorDataFromTheGrid("Yoe Howell");
        requestTestSteps.refreshDataGrid();
        requestTestSteps.selectRecordFromBoxesGrid("Automation Request Box");
        requestTestSteps.verifyRequestedStatus("Yoe Howell","In Process");

        requestTestSteps.selectTransferSelectedOption();
        requestTestSteps.selectContainerFromTheTransfer("Employees","Employees","Yoe Howell");
        requestTestSteps.verifyTransferredRecordLocationInCurrentLocationWindow("Yoe Howell");
    }

    /** FUS - 13244 Request WaitList Workflow Validation */
    @Test(groups = {"Smoke"})
    public void request_WaitList_WorkFlow_Validation() throws InterruptedException, IOException
    {
        testName.set("Request WaitList WorkFlow Validation");

        loginTestSteps = new login_Test_Steps(webDriver);
        gridTestSteps = new grid_Test_Steps(webDriver);
        reportsTestSteps = new reports_Test_Steps(webDriver);
        requestTestSteps = new request_Test_Steps(webDriver);

        loginTestSteps.openLoginPage();
        loginTestSteps.selectDatabase(prop.getProperty("Web_Database"));
        loginTestSteps.enterUsernamePassword(prop.getProperty("Web_Valid_Username"),prop.getProperty("Web_Password"));
        loginTestSteps.signInButton();
        loginTestSteps.verifyHomePage();
        loginTestSteps.verifyUserLoginSuccess();

        gridTestSteps.selectNavigationOption("Tracking");
        gridTestSteps.selectNavigationOption("Boxes");
        gridTestSteps.selectNavigationOption("All Boxes");
        gridTestSteps.verifyQueryPopupAndPressOk();
        gridTestSteps.verifySelectedViewIsOpened("All Boxes");

        requestTestSteps.selectRecordFromBoxesGrid("Sales Waitlist");
        requestTestSteps.selectRequestOption();
        requestTestSteps.verifySelectedRecord("Sales Waitlist");
        requestTestSteps.selectPersonName("Yoe Howell");
        requestTestSteps.selectRequestButton();
        requestTestSteps.verifyRequestedStatus("Yoe Howell","New");

        requestTestSteps.selectRequestOption();
        requestTestSteps.verifySelectedRecord("Sales Waitlist");
        requestTestSteps.selectPersonName("Dane Joseph");
        requestTestSteps.selectRequestButton();
        requestTestSteps.verifyRequestedStatus("Dane Joseph","WaitList");
    }

    /** FUS - 13245 Request WaitList Report Validation */
    @Test(groups = {"Smoke"})
    public void request_WaitList_Report_Validation() throws InterruptedException, IOException
    {
        testName.set("Request WaitList Report Validation");

        loginTestSteps = new login_Test_Steps(webDriver);
        gridTestSteps = new grid_Test_Steps(webDriver);
        reportsTestSteps = new reports_Test_Steps(webDriver);
        requestTestSteps = new request_Test_Steps(webDriver);

        loginTestSteps.openLoginPage();
        loginTestSteps.selectDatabase(prop.getProperty("Web_Database"));
        loginTestSteps.enterUsernamePassword(prop.getProperty("Web_Valid_Username"),prop.getProperty("Web_Password"));
        loginTestSteps.signInButton();
        loginTestSteps.verifyHomePage();
        loginTestSteps.verifyUserLoginSuccess();

        gridTestSteps.selectNavigationOption("Tracking");
        gridTestSteps.selectNavigationOption("Boxes");
        gridTestSteps.selectNavigationOption("All Boxes");
        gridTestSteps.verifyQueryPopupAndPressOk();
        gridTestSteps.verifySelectedViewIsOpened("All Boxes");

        requestTestSteps.selectRecordFromBoxesGrid("Sales Waitlist");
        requestTestSteps.selectRequestOption();
        requestTestSteps.verifySelectedRecord("Sales Waitlist");
        requestTestSteps.selectPersonName("Yoe Howell");
        requestTestSteps.selectRequestButton();
        requestTestSteps.verifyRequestedStatus("Yoe Howell","New");

        requestTestSteps.selectRequestOption();
        requestTestSteps.verifySelectedRecord("Sales Waitlist");
        requestTestSteps.selectPersonName("Dane Joseph");
        requestTestSteps.selectRequestButton();
        requestTestSteps.verifyRequestedStatus("Dane Joseph","WaitList");

        requestTestSteps.verifyOnReports();

        gridTestSteps.selectNavigationOption("Requestor Reports");
        gridTestSteps.selectNavigationOption("Wait List");

        requestTestSteps.verifyWaitListRecordReport("Boxes");
    }

    /** Tear Down method executing according to test after complete test
     * @param result = They get test method name */
    @AfterMethod(groups = {"setUp"},alwaysRun = true)
    public void tearDown(ITestResult result) throws IOException
    {
        testReport(result);

        if (result.getStatus() == result.SUCCESS || result.getStatus() == result.FAILURE)
        {
            if (result.getMethod().getMethodName().equals("request_WorkFlow_New_InProcess_Fulfilled"))
            {
                try
                {
                    requestTestSteps.goBackToGrid();
                    webDriver.navigate().refresh();
                    gridTestSteps.selectNavigationOption("Tracking");
                    gridTestSteps.selectNavigationOption("Boxes");
                    gridTestSteps.selectNavigationOption("All Boxes");
                    gridTestSteps.verifyQueryPopupAndPressOk();
                    gridTestSteps.verifySelectedViewIsOpened("All Boxes");

                    requestTestSteps.selectRecordFromBoxesGrid("Automation Request Box");
                    requestTestSteps.selectTransferSelectedOption();
                    requestTestSteps.selectContainerFromTheTransfer("Locations","Locations","Central File Room");
                    requestTestSteps.verifyTransferredRecordLocationInCurrentLocationWindow("Central File Room");
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
            else if (result.getMethod().getMethodName().equals("request_WaitList_WorkFlow_Validation") ||
                    result.getMethod().getMethodName().equals("request_WaitList_Report_Validation"))
            {
                try
                {
                    requestTestSteps.goBackToGrid();
                    webDriver.navigate().refresh();
                    gridTestSteps.selectNavigationOption("Tracking");
                    gridTestSteps.selectNavigationOption("Boxes");
                    gridTestSteps.selectNavigationOption("All Boxes");
                    gridTestSteps.verifyQueryPopupAndPressOk();
                    gridTestSteps.verifySelectedViewIsOpened("All Boxes");

                    requestTestSteps.selectRecordFromBoxesGrid("Sales Waitlist");
                    requestTestSteps.selectTransferSelectedOption();
                    requestTestSteps.selectContainerFromTheTransfer("Employees","Employees","Yoe Howell");
                    requestTestSteps.selectTransferSelectedOption();
                    requestTestSteps.selectContainerFromTheTransfer("Employees","Employees","Dane Joseph");
                    requestTestSteps.selectTransferSelectedOption();
                    requestTestSteps.selectContainerFromTheTransfer("Locations","Locations","Central File Room");

                    requestTestSteps.verifyTransferredRecordLocationInCurrentLocationWindow("Central File Room");
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
