package api.Api_TestCases;

import api.Api_TestSteps.authToken_Test_Steps;
import api.Api_TestSteps.newRecordMulti_Test_Steps;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import utilities.web_api_BaseClass;

import java.io.IOException;

public class postNewRecordMulti extends web_api_BaseClass {

    public authToken_Test_Steps authTokenTestSteps;
    public newRecordMulti_Test_Steps newRecordMultiTestSteps;

    /** Add Multiple Record To View Of The Permitted User */
    @Test(groups = {"Smoke"})
    public void add_Multiple_Record_To_View_Of_The_Permitted_User() throws IOException
    {
        testName.set("Add Multiple Record To View Of The Permitted User");

        authTokenTestSteps = new authToken_Test_Steps();
        newRecordMultiTestSteps = new newRecordMulti_Test_Steps();

        authTokenTestSteps.requestBody(prop.getProperty("ValidUsername"),prop.getProperty("API_Password"),prop.getProperty("API_Database"));
        authTokenTestSteps.storeTokenInGlobalVariable();

        newRecordMultiTestSteps.requestBody("./framework_Tools/api_RequestPayloads/New Record Multi - Multiple Records Addition.json");
        newRecordMultiTestSteps.verifyStatusCodeAndRecord();
        newRecordMultiTestSteps.verifyAddedSuccessfullyRecord();
    }

    /** Add Single Column Multiple Record To View Of The Permitted User */
    @Test(groups = {"Smoke"})
    public void add_Single_Column_Multiple_Record_To_View_Of_The_Permitted_User() throws IOException
    {
        testName.set("Add Single Column Multiple Record To View Of The Permitted User");

        authTokenTestSteps = new authToken_Test_Steps();
        newRecordMultiTestSteps = new newRecordMulti_Test_Steps();

        authTokenTestSteps.requestBody(prop.getProperty("ValidUsername"),prop.getProperty("API_Password"),prop.getProperty("API_Database"));
        authTokenTestSteps.storeTokenInGlobalVariable();

        newRecordMultiTestSteps.requestBody("./framework_Tools/api_RequestPayloads/New Record Multi - Single Column Multiple Addition.json");
        newRecordMultiTestSteps.verifyStatusCodeAndRecord();
        newRecordMultiTestSteps.verifyAddedSuccessfullyRecord();
    }

    /** Add Mixed Record Types To View Of The Permitted User */
    @Test(groups = {"Smoke"})
    public void add_Mixed_Record_Types_To_View_Of_The_Permitted_User() throws IOException
    {
        testName.set("Add Mixed Record Types To View Of The Permitted User");

        authTokenTestSteps = new authToken_Test_Steps();
        newRecordMultiTestSteps = new newRecordMulti_Test_Steps();

        authTokenTestSteps.requestBody(prop.getProperty("ValidUsername"),prop.getProperty("API_Password"),prop.getProperty("API_Database"));
        authTokenTestSteps.storeTokenInGlobalVariable();

        newRecordMultiTestSteps.requestBody("./framework_Tools/api_RequestPayloads/New Record Multi - Mixed Records Types Addition.json");
        newRecordMultiTestSteps.verifyStatusCodeAndRecord();
        newRecordMultiTestSteps.verifyAddedSuccessfullyRecord();
    }

    /** Tear Down method executing according to test after complete test
     * @param result = They get test method name */
    @AfterMethod(groups = {"setUp"},alwaysRun = true)
    public void tearDown(ITestResult result) throws IOException
    {
        testReport(result);
    }
}
