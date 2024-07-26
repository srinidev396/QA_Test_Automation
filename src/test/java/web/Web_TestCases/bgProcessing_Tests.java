package web.Web_TestCases;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import utilities.web_api_BaseClass;
import web.Web_TestSteps.bgProcessing_Test_Steps;
import web.Web_TestSteps.grid_Test_Steps;
import web.Web_TestSteps.login_Test_Steps;

import java.io.IOException;

public class bgProcessing_Tests extends web_api_BaseClass {

    public login_Test_Steps loginTestSteps;
    public grid_Test_Steps gridTestSteps;
    public bgProcessing_Test_Steps bgProcessingTestSteps;

    /** FUS - 12866	Background Processing for Grid Data export */
    @Test(groups = {"Smoke"})
    public void background_Processing_For_Grid_Export() throws IOException, InterruptedException
    {
        testName.set("Background Processing For Grid Export");

        loginTestSteps = new login_Test_Steps(webDriver);
        gridTestSteps = new grid_Test_Steps(webDriver);
        bgProcessingTestSteps = new bgProcessing_Test_Steps(webDriver);

        loginTestSteps.openLoginPage();
        loginTestSteps.selectDatabase(prop.getProperty("Web_Database"));
        loginTestSteps.enterUsernamePassword(prop.getProperty("Web_Valid_Username"),prop.getProperty("Web_Password"));
        loginTestSteps.signInButton();
        loginTestSteps.verifyHomePage();
        loginTestSteps.verifyUserLoginSuccess();

        gridTestSteps.selectNavigationOption("AutomationWG");
        gridTestSteps.selectNavigationOption("Vendors");
        gridTestSteps.selectNavigationOption("All Vendors");
        gridTestSteps.verifyQueryPopupAndPressOk();
        gridTestSteps.verifySelectedViewIsOpened("All Vendors");
        gridTestSteps.selectExportAll("txt");

        bgProcessingTestSteps.verifyExportSuccessfullyMSG();
        bgProcessingTestSteps.getExportedFileNameFromPopup();
        bgProcessingTestSteps.openBackgroundStatusWindow();
        bgProcessingTestSteps.verifyBackgroundExported();
    }

    /** Tear Down method executing according to test after complete test
     * @param result = They get test method name */
    @AfterMethod(groups = {"setUp"},alwaysRun = true)
    public void tearDown(ITestResult result) throws InterruptedException, IOException
    {
        testReport(result);

        if (result.getStatus() == result.SUCCESS || result.getStatus() == result.FAILURE)
        {
            if (result.getMethod().getMethodName().equals("background_Processing_For_Grid_Export"))
            {
                try
                {
                    bgProcessingTestSteps.tearDown_Delete_Background_Process_Exported_Downloaded_File();
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
