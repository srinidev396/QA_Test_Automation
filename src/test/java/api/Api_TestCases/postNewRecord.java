package api.Api_TestCases;

import api.Api_TestSteps.authToken_Test_Steps;
import api.Api_TestSteps.newRecord_Test_Steps;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import utilities.web_api_BaseClass;

import java.io.IOException;

public class postNewRecord extends web_api_BaseClass {

    public authToken_Test_Steps authTokenTestSteps;
    public newRecord_Test_Steps newRecordTestSteps;

    /** Add New Single Record To View Of The Permitted User */
    @Test(groups = {"Smoke"})
    public void add_New_Single_Record_To_View_Of_The_Permitted_User() throws IOException
    {
        testName.set("Add New Single Record To View Of The Permitted User");

        authTokenTestSteps = new authToken_Test_Steps();
        newRecordTestSteps = new newRecord_Test_Steps();

        authTokenTestSteps.requestBody(prop.getProperty("ValidUsername"),prop.getProperty("API_Password"),prop.getProperty("API_Database"));
        authTokenTestSteps.storeTokenInGlobalVariable();

        newRecordTestSteps.requestBody("./framework_Tools/api_RequestPayloads/New Record - Single Record Addition.json");
        newRecordTestSteps.verifyStatusCodeAndRecord();
        newRecordTestSteps.verifyAddedSuccessfullyRecord();
    }

    /** Add Single Column Record To View Of The Permitted User */
    @Test(groups = {"Smoke"})
    public void add_Single_Column_Record_To_View_Of_The_Permitted_User() throws IOException
    {
        testName.set("Add Single Column Record To View Of The Permitted User");

        authTokenTestSteps = new authToken_Test_Steps();
        newRecordTestSteps = new newRecord_Test_Steps();

        authTokenTestSteps.requestBody(prop.getProperty("ValidUsername"),prop.getProperty("API_Password"),prop.getProperty("API_Database"));
        authTokenTestSteps.storeTokenInGlobalVariable();

        newRecordTestSteps.requestBody("./framework_Tools/api_RequestPayloads/New Record - Single Column Addition.json");
        newRecordTestSteps.verifyStatusCodeAndRecord();
        newRecordTestSteps.verifyAddedSuccessfullyRecord();
    }

    /** Verify Multiple Column Record Error */
    @Test(groups = {"Smoke"})
    public void verify_Multiple_Column_Error() throws IOException
    {
        testName.set("Verify Multiple Column Record Error");

        authTokenTestSteps = new authToken_Test_Steps();
        newRecordTestSteps = new newRecord_Test_Steps();

        authTokenTestSteps.requestBody(prop.getProperty("ValidUsername"),prop.getProperty("API_Password"),prop.getProperty("API_Database"));
        authTokenTestSteps.storeTokenInGlobalVariable();

        newRecordTestSteps.requestBody("./framework_Tools/api_RequestPayloads/New Record - Multiple Column Error.json");
        newRecordTestSteps.verifyStatusCodeAndRecord();
        newRecordTestSteps.verifyMultipleColumnError();
    }

    /** Verify Unique Id Record Error */
    @Test(groups = {"Smoke"})
    public void verify_Unique_Id_Record_Error() throws IOException
    {
        testName.set("Verify Unique Id Record Error");

        authTokenTestSteps = new authToken_Test_Steps();
        newRecordTestSteps = new newRecord_Test_Steps();

        authTokenTestSteps.requestBody(prop.getProperty("ValidUsername"),prop.getProperty("API_Password"),prop.getProperty("API_Database"));
        authTokenTestSteps.storeTokenInGlobalVariable();

        newRecordTestSteps.requestBody("framework_Tools/api_RequestPayloads/New Record - Unique ID Error.json");
        newRecordTestSteps.verifyStatusCodeAndRecord();
        newRecordTestSteps.verifyUniqueIdRecordError();
    }

    /** Tear Down method executing according to test after complete test
     * @param result = They get test method name */
    @AfterMethod(groups = {"setUp"},alwaysRun = true)
    public void tearDown(ITestResult result) throws IOException
    {
        testReport(result);
    }
}
