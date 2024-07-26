package api.Api_TestCases;

import api.Api_TestSteps.authToken_Test_Steps;
import api.Api_TestSteps.editRecordByColumn_Test_Steps;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import utilities.web_api_BaseClass;

import java.io.IOException;

public class postEditRecordByColumn extends web_api_BaseClass {

    public authToken_Test_Steps authTokenTestSteps;
    public editRecordByColumn_Test_Steps editRecordByColumnTestSteps;

    /** Edit Record By Column Single Columns Record To View Of The Permitted User */
    @Test(groups = {"Smoke"})
    public void edit_Record_By_Column_Single_Columns_Record_To_View_Of_The_Permitted_User() throws IOException
    {
        testName.set("Edit Record By Column Single Columns Record To View Of The Permitted User");

        authTokenTestSteps = new authToken_Test_Steps();
        editRecordByColumnTestSteps = new editRecordByColumn_Test_Steps();

        authTokenTestSteps.requestBody(prop.getProperty("ValidUsername"),prop.getProperty("API_Password"),prop.getProperty("API_Database"));
        authTokenTestSteps.storeTokenInGlobalVariable();

        editRecordByColumnTestSteps.requestBody("./framework_Tools/api_RequestPayloads/Edit Record By Column - Single ColumnEdit.json");
        editRecordByColumnTestSteps.verifyStatusCodeAndRecord();
        editRecordByColumnTestSteps.verifyEditedRecordSuccessfully();
    }

    /** Edit Record By Column Multiple Columns Record To View Of The Permitted User */
    @Test(groups = {"Smoke"})
    public void edit_Record_By_Column_Multiple_Columns_Record_To_View_Of_The_Permitted_User() throws IOException
    {
        testName.set("Edit Record By Column Multiple Columns Record To View Of The Permitted User");

        authTokenTestSteps = new authToken_Test_Steps();
        editRecordByColumnTestSteps = new editRecordByColumn_Test_Steps();

        authTokenTestSteps.requestBody(prop.getProperty("ValidUsername"),prop.getProperty("API_Password"),prop.getProperty("API_Database"));
        authTokenTestSteps.storeTokenInGlobalVariable();

        editRecordByColumnTestSteps.requestBody("./framework_Tools/api_RequestPayloads/Edit Record By Column - Multiple ColumnEdit.json");
        editRecordByColumnTestSteps.verifyStatusCodeAndRecord();
        editRecordByColumnTestSteps.verifyEditedRecordSuccessfully();
    }

    /** Verify Data Formats Support Of Record To View Of The Permitted User */
    @Test(groups = {"Smoke"})
    public void verify_Data_Formats_Support_Of_Record_To_View_Of_The_Permitted_User() throws IOException
    {
        testName.set("Verify Data Formats Support Of Record To View Of The Permitted User");

        authTokenTestSteps = new authToken_Test_Steps();
        editRecordByColumnTestSteps = new editRecordByColumn_Test_Steps();

        authTokenTestSteps.requestBody(prop.getProperty("ValidUsername"),prop.getProperty("API_Password"),prop.getProperty("API_Database"));
        authTokenTestSteps.storeTokenInGlobalVariable();

        //Verify Data Format Of Float To Integer
        editRecordByColumnTestSteps.requestBody("./framework_Tools/api_RequestPayloads/Edit Record By Column - Data Format Support (Integer to Float).json");
        editRecordByColumnTestSteps.verifyStatusCodeAndRecord();
        editRecordByColumnTestSteps.verifyDataFormatsFloatToIntError();

        //Verify Data Format Of Date To String
        editRecordByColumnTestSteps.requestBody("./framework_Tools/api_RequestPayloads/Edit Record By Column - Data Format Support (Date to String).json");
        editRecordByColumnTestSteps.verifyStatusCodeAndRecord();
        editRecordByColumnTestSteps.verifyDataFormatsDateToStringError();
    }

    /** Verify Record By Column Unique I'd Error To View Of The Permitted User */
    @Test(groups = {"Smoke"})
    public void verify_Record_By_Column_Unique_Id_Error_To_View_Of_The_Permitted_User() throws IOException
    {
        testName.set("Verify Record By Column Unique I'd Error To View Of The Permitted User");

        authTokenTestSteps = new authToken_Test_Steps();
        editRecordByColumnTestSteps = new editRecordByColumn_Test_Steps();

        authTokenTestSteps.requestBody(prop.getProperty("ValidUsername"),prop.getProperty("API_Password"),prop.getProperty("API_Database"));
        authTokenTestSteps.storeTokenInGlobalVariable();

        editRecordByColumnTestSteps.requestBody("./framework_Tools/api_RequestPayloads/Edit Record By Column - Unique ID Error Handling.json");
        editRecordByColumnTestSteps.verifyStatusCodeAndRecord();
        editRecordByColumnTestSteps.verifyUniqueIdError();
    }

    /** Verify Record By Column Of Invalid Column Value Error To View Of The Permitted User */
    @Test(groups = {"Smoke"})
    public void verify_Record_By_Column_Of_Invalid_Column_Value_Error_To_View_Of_The_Permitted_User() throws IOException
    {
        testName.set("Verify Record By Column Of Invalid Column Error To View Of The Permitted User");

        authTokenTestSteps = new authToken_Test_Steps();
        editRecordByColumnTestSteps = new editRecordByColumn_Test_Steps();

        authTokenTestSteps.requestBody(prop.getProperty("ValidUsername"),prop.getProperty("API_Password"),prop.getProperty("API_Database"));
        authTokenTestSteps.storeTokenInGlobalVariable();

        editRecordByColumnTestSteps.requestBody("./framework_Tools/api_RequestPayloads/Edit Record By Column - Invalid Column Value Error.json");
        editRecordByColumnTestSteps.verifyStatusCodeAndRecord();
        editRecordByColumnTestSteps.verifyInvalidColumnValueError();
    }

    /** Tear Down method executing according to test after complete test
     * @param result = They get test method name */
    @AfterMethod(groups = {"setUp"},alwaysRun = true)
    public void tearDown(ITestResult result) throws IOException
    {
        testReport(result);

        if (result.getStatus() == result.SUCCESS || result.getStatus() == result.FAILURE || result.getStatus() == result.SKIP)
        {
            if (result.getMethod().getMethodName().equals("edit_Record_By_Column_Single_Columns_Record_To_View_Of_The_Permitted_User"))
            {
                try
                {
                    editRecordByColumnTestSteps.requestBody("./framework_Tools/api_RequestPayloads/Edit Record By Column - Single ColumnEdit (Rollback To Original Data).json");
                    editRecordByColumnTestSteps.verifyStatusCodeAndRecord();
                    editRecordByColumnTestSteps.verifyEditedRecordSuccessfully();
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
            else if (result.getMethod().getMethodName().equals("edit_Record_By_Column_Multiple_Columns_Record_To_View_Of_The_Permitted_User"))
            {
                try
                {
                    editRecordByColumnTestSteps.requestBody("./framework_Tools/api_RequestPayloads/Edit Record By Column - Multiple ColumnEdit (Rollback To Original Data).json");
                    editRecordByColumnTestSteps.verifyStatusCodeAndRecord();
                    editRecordByColumnTestSteps.verifyEditedRecordSuccessfully();
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
