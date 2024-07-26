package api.Api_TestCases;

import api.Api_TestSteps.authToken_Test_Steps;
import api.Api_TestSteps.editIfNotExistAdd_Test_Steps;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import utilities.web_api_BaseClass;

import java.io.IOException;

public class postEditIfNotExistAdd extends web_api_BaseClass {

    public authToken_Test_Steps authTokenTestSteps;
    public editIfNotExistAdd_Test_Steps editIfNotExistAddTestSteps;

    /** Edit If Not Exist Add - Insert New Record To View Of The Permitted User */
    @Test(groups = {"Smoke"})
    public void edit_If_Not_Exist_Add_Insert_New_Record_To_View_Of_The_Permitted_User() throws IOException
    {
        testName.set("Edit If Not Exist Add - Insert New Record To View Of The Permitted User");

        authTokenTestSteps = new authToken_Test_Steps();
        editIfNotExistAddTestSteps = new editIfNotExistAdd_Test_Steps();

        authTokenTestSteps.requestBody(prop.getProperty("ValidUsername"),prop.getProperty("API_Password"),prop.getProperty("API_Database"));
        authTokenTestSteps.storeTokenInGlobalVariable();

        editIfNotExistAddTestSteps.requestBody("./framework_Tools/api_RequestPayloads/Edit If Not Exist Add - Insert New Record.json");
        editIfNotExistAddTestSteps.verifyStatusCodeAndRecord();
        editIfNotExistAddTestSteps.verifyInsertNewRecordSuccessfully();
    }

    /** Edit If Not Exist Add - Updated Record To View Of The Permitted User */
    @Test(groups = {"Smoke"})
    public void edit_If_Not_Exist_Add_Updated_Record_To_View_Of_The_Permitted_User() throws IOException
    {
        testName.set("Edit If Not Exist Add - Updated Record To View Of The Permitted User");

        authTokenTestSteps = new authToken_Test_Steps();
        editIfNotExistAddTestSteps = new editIfNotExistAdd_Test_Steps();

        authTokenTestSteps.requestBody(prop.getProperty("ValidUsername"),prop.getProperty("API_Password"),prop.getProperty("API_Database"));
        authTokenTestSteps.storeTokenInGlobalVariable();

        editIfNotExistAddTestSteps.requestBody("./framework_Tools/api_RequestPayloads/Edit If Not Exist Add - Updated Existing.json");
        editIfNotExistAddTestSteps.verifyStatusCodeAndRecord();
        editIfNotExistAddTestSteps.verifyUpdatedRecordSuccessfully();
    }

    /** Tear Down method executing according to test after complete test
     * @param result = They get test method name */
    @AfterMethod(groups = {"setUp"},alwaysRun = true)
    public void tearDown(ITestResult result) throws IOException
    {
        testReport(result); 
    }
}
