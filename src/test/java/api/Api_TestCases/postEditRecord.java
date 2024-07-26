package api.Api_TestCases;

import api.Api_TestSteps.authToken_Test_Steps;
import api.Api_TestSteps.editRecord_Test_Steps;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import utilities.web_api_BaseClass;

import java.io.IOException;

public class postEditRecord extends web_api_BaseClass {

    public authToken_Test_Steps authTokenTestSteps;
    public editRecord_Test_Steps editRecordTestSteps;

    /** Edit Single Column Record To View Of The Permitted User */
    @Test(groups = {"Smoke"})
    public void edit_Single_Column_Record_To_View_Of_The_Permitted_User() throws IOException
    {
        testName.set("Edit Single Column Record To View Of The Permitted User");

        authTokenTestSteps = new authToken_Test_Steps();
        editRecordTestSteps = new editRecord_Test_Steps();

        authTokenTestSteps.requestBody(prop.getProperty("ViewPermitUsername"),prop.getProperty("API_Password"),prop.getProperty("API_Database"));
        authTokenTestSteps.storeTokenInGlobalVariable();

        editRecordTestSteps.requestBody("./framework_Tools/api_RequestPayloads/Edit Record - Single ColumnEdit.json");
        editRecordTestSteps.verifyStatusCodeAndRecord();
        editRecordTestSteps.verifyEditedRecordSuccessfully();
    }

    /** Edit Multiple Column Record To View Of The Permitted User */
    @Test(groups = {"Smoke"})
    public void edit_Multiple_Column_Record_To_View_Of_The_Permitted_User() throws IOException
    {
        testName.set("Edit Multiple Column Record To View Of The Permitted User");

        authTokenTestSteps = new authToken_Test_Steps();
        editRecordTestSteps = new editRecord_Test_Steps();

        authTokenTestSteps.requestBody(prop.getProperty("ValidUsername"),prop.getProperty("API_Password"),prop.getProperty("API_Database"));
        authTokenTestSteps.storeTokenInGlobalVariable();

        editRecordTestSteps.requestBody("./framework_Tools/api_RequestPayloads/Edit Record - Multiple ColumnEdit.json");
        editRecordTestSteps.verifyStatusCodeAndRecord();
        editRecordTestSteps.verifyEditedRecordSuccessfully();
    }

    /** Verify Non-Existing Table Error To View Of The Permitted User */
    @Test(groups = {"Smoke"})
    public void verify_Non_Existing_Table_Error_To_View_Of_The_Permitted_User() throws IOException
    {
        testName.set("Verify Non-Existing Table Error To View Of The Permitted User");

        authTokenTestSteps = new authToken_Test_Steps();
        editRecordTestSteps = new editRecord_Test_Steps();

        authTokenTestSteps.requestBody(prop.getProperty("ValidUsername"),prop.getProperty("API_Password"),prop.getProperty("API_Database"));
        authTokenTestSteps.storeTokenInGlobalVariable();

        editRecordTestSteps.requestBody("./framework_Tools/api_RequestPayloads/Edit Record - Non Existing Table Error.json");
        editRecordTestSteps.verifyStatusCodeAndRecord();
        editRecordTestSteps.verifyNonExistingTableError();
    }

    /** Verify Unique I'd Error To View Of The Permitted User */
    @Test(groups = {"Smoke"})
    public void verify_Unique_Id_Error_To_View_Of_The_Permitted_User() throws IOException
    {
        testName.set("Verify Unique I'd Error To View Of The Permitted User");

        authTokenTestSteps = new authToken_Test_Steps();
        editRecordTestSteps = new editRecord_Test_Steps();

        authTokenTestSteps.requestBody(prop.getProperty("ValidUsername"),prop.getProperty("API_Password"),prop.getProperty("API_Database"));
        authTokenTestSteps.storeTokenInGlobalVariable();

        editRecordTestSteps.requestBody("./framework_Tools/api_RequestPayloads/Edit Record - Unique ID Error.json");
        editRecordTestSteps.verifyStatusCodeAndRecord();
        editRecordTestSteps.verifyUniqueIdError();
    }

    /** Verify Invalid Column Value Error To View Of The Permitted User */
    @Test(groups = {"Smoke"})
    public void verify_Invalid_Column_Value_Error_To_View_Of_The_Permitted_User() throws IOException
    {
        testName.set("Verify Invalid Column Error To View Of The Permitted User");

        authTokenTestSteps = new authToken_Test_Steps();
        editRecordTestSteps = new editRecord_Test_Steps();

        authTokenTestSteps.requestBody(prop.getProperty("ValidUsername"),prop.getProperty("API_Password"),prop.getProperty("API_Database"));
        authTokenTestSteps.storeTokenInGlobalVariable();

        editRecordTestSteps.requestBody("./framework_Tools/api_RequestPayloads/Edit Record - Invalid Column Value Error.json");
        editRecordTestSteps.verifyStatusCodeAndRecord();
        editRecordTestSteps.verifyInvalidColumnValueError();
    }

    /** Tear Down method executing according to test after complete test
     * @param result = They get test method name */
    @AfterMethod(groups = {"setUp"},alwaysRun = true)
    public void tearDown(ITestResult result) throws IOException
    {
        testReport(result);

        if (result.getStatus() == result.SUCCESS || result.getStatus() == result.FAILURE || result.getStatus() == result.SKIP)
        {
            if (result.getMethod().getMethodName().equals("edit_Single_Record_To_View_Of_The_Permitted_User"))
            {
                try
                {
                    editRecordTestSteps.requestBody("./framework_Tools/api_RequestPayloads/Edit Record - Single ColumnEdit (Rollback To Original Data).json");
                    editRecordTestSteps.verifyStatusCodeAndRecord();
                    editRecordTestSteps.verifyEditedRecordSuccessfully();
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
            else if (result.getMethod().getMethodName().equals("edit_Multiple_Column_Record_To_View_Of_The_Permitted_User"))
            {
                try
                {
                    editRecordTestSteps.requestBody("./framework_Tools/api_RequestPayloads/Edit Record - Multiple ColumnEdit (Rollback To Original Data).json");
                    editRecordTestSteps.verifyStatusCodeAndRecord();
                    editRecordTestSteps.verifyEditedRecordSuccessfully();
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
    }
}
