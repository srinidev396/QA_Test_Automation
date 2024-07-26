package web.Web_TestCases;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import utilities.web_api_BaseClass;
import web.Web_TestSteps.grid_Test_Steps;
import web.Web_TestSteps.login_Test_Steps;
import web.Web_TestSteps.retention_Test_Steps;

import java.io.IOException;

public class retention_Tests extends web_api_BaseClass {

    public login_Test_Steps loginTestSteps;
    public grid_Test_Steps gridTestSteps;
    public retention_Test_Steps retentionTestSteps;

    /** FUS - 13246 Retention Info For Newly Added Record */
    @Test(groups = {"Smoke"})
    public void retention_Info_For_Newly_Added_Record() throws IOException, InterruptedException
    {
        testName.set("Verify retention Information For Newly Added record");

        loginTestSteps = new login_Test_Steps(webDriver);
        gridTestSteps = new grid_Test_Steps(webDriver);
        retentionTestSteps = new retention_Test_Steps(webDriver);

        loginTestSteps.openLoginPage();
        loginTestSteps.selectDatabase(prop.getProperty("Web_Database"));
        loginTestSteps.enterUsernamePassword(prop.getProperty("Web_Valid_Username"),prop.getProperty("Web_Password"));
        loginTestSteps.signInButton();
        loginTestSteps.verifyHomePage();
        loginTestSteps.verifyUserLoginSuccess();

        gridTestSteps.selectNavigationOption("AutomationWG");
        gridTestSteps.selectNavigationOption("EFiles");
        gridTestSteps.selectNavigationOption("All EFiles");
        gridTestSteps.verifyQueryPopupAndPressOk();
        gridTestSteps.verifySelectedViewIsOpened("All EFiles");

        retentionTestSteps.createEFileNewRecord();
        retentionTestSteps.getDefaultRetentionCodeFromDropDownOfSelectedRecord("TestAutomation.txt");
        retentionTestSteps.openRetentionInfoFroRecordMenu("TestAutomation.txt");
        retentionTestSteps.verifyRetentionCode();
    }

    /** FUS - 13247 Change Retention Code For An Object */
    @Test(groups = {"Smoke"})
    public void change_Retention_Code_For_An_Object() throws IOException, InterruptedException
    {
        testName.set("Change Retention Code For An Object");

        loginTestSteps = new login_Test_Steps(webDriver);
        gridTestSteps = new grid_Test_Steps(webDriver);
        retentionTestSteps = new retention_Test_Steps(webDriver);

        loginTestSteps.openLoginPage();
        loginTestSteps.selectDatabase(prop.getProperty("Web_Database"));
        loginTestSteps.enterUsernamePassword(prop.getProperty("Web_Valid_Username"),prop.getProperty("Web_Password"));
        loginTestSteps.signInButton();
        loginTestSteps.verifyHomePage();
        loginTestSteps.verifyUserLoginSuccess();

        gridTestSteps.selectNavigationOption("AutomationWG");
        gridTestSteps.selectNavigationOption("EFiles");
        gridTestSteps.selectNavigationOption("All EFiles");
        gridTestSteps.verifyQueryPopupAndPressOk();
        gridTestSteps.verifySelectedViewIsOpened("All EFiles");

        retentionTestSteps.createEFileNewRecord();
        retentionTestSteps.getDefaultRetentionCodeFromDropDownOfSelectedRecord("TestAutomation.txt");
        retentionTestSteps.selectRetentionCodeOfRecord("TestAutomation.txt","RET002_Inactive");
        retentionTestSteps.saveEditRecord();
        retentionTestSteps.getUpdatedRetentionCodeFromDropDownOfSelectedRecord("TestAutomation.txt");
        retentionTestSteps.verifyRetentionCodeAfterUpdate();
    }

    /** FUS - 13248 Put Objects To Retention Hold */
    @Test(groups = {"Smoke"})
    public void put_Object_To_Retention_Hold() throws IOException, InterruptedException
    {
        testName.set("Add Retention Hold And Verify On Retention Hold Report");

        loginTestSteps = new login_Test_Steps(webDriver);
        gridTestSteps = new grid_Test_Steps(webDriver);
        retentionTestSteps = new retention_Test_Steps(webDriver);

        loginTestSteps.openLoginPage();
        loginTestSteps.selectDatabase(prop.getProperty("Web_Database"));
        loginTestSteps.enterUsernamePassword(prop.getProperty("Web_Valid_Username"),prop.getProperty("Web_Password"));
        loginTestSteps.signInButton();
        loginTestSteps.verifyHomePage();
        loginTestSteps.verifyUserLoginSuccess();

        gridTestSteps.selectNavigationOption("AutomationWG");
        gridTestSteps.selectNavigationOption("EFiles");
        gridTestSteps.selectNavigationOption("All EFiles");
        gridTestSteps.verifyQueryPopupAndPressOk();
        gridTestSteps.verifySelectedViewIsOpened("All EFiles");

        retentionTestSteps.createEFileNewRecord();
        retentionTestSteps.openRetentionInfoFroRecordMenu("TestAutomation.txt");
        retentionTestSteps.addRetentionHold();
        retentionTestSteps.verifyAddedRetentionHoldInfoOnPopup("Hold For Testing");
        retentionTestSteps.saveRetentionHoldInfo();
        retentionTestSteps.openRetentionReport();

        gridTestSteps.selectNavigationOption("Retention Reports");
        gridTestSteps.selectNavigationOption("Records On Hold");

        retentionTestSteps.verifyRetentionHoldReasonOnHoldReport("Hold For Testing");
    }

    /** Tear Down method executing according to test after complete test
     * @param result = They get test method name */
    @AfterMethod(groups = {"setUp"},alwaysRun = true)
    public void tearDown(ITestResult result) throws IOException
    {
        testReport(result);

        if (result.getStatus() == result.SUCCESS || result.getStatus() == result.FAILURE)
        {
            if (result.getMethod().getMethodName().equals("retention_Info_For_Newly_Added_Record") ||
                    result.getMethod().getMethodName().equals("change_Retention_Code_For_An_Object") ||
                    result.getMethod().getMethodName().equals("put_Object_To_Retention_Hold"))
            {
                try
                {
                    retentionTestSteps.goBackToGrid();
                    webDriver.navigate().refresh();
                    gridTestSteps.selectNavigationOption("AutomationWG");
                    gridTestSteps.selectNavigationOption("EFiles");
                    gridTestSteps.selectNavigationOption("All EFiles");
                    gridTestSteps.verifyQueryPopupAndPressOk();
                    gridTestSteps.verifySelectedViewIsOpened("All EFiles");
                    retentionTestSteps.sortRecordFromGrid("TestAutomation.txt");
                    retentionTestSteps.deleteCreatedRecord();
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
