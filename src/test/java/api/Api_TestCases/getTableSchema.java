package api.Api_TestCases;

import api.Api_TestSteps.authToken_Test_Steps;
import api.Api_TestSteps.tableSchema_Test_Steps;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import utilities.web_api_BaseClass;

import java.io.IOException;

public class getTableSchema extends web_api_BaseClass
{
    public authToken_Test_Steps authTokenTestSteps;
    public tableSchema_Test_Steps tableSchemaTestSteps;

    /** Validate that the column count and list of columns for each table */
    @Test(groups = {"Smoke"})
    public void validate_That_The_Column_Count_And_List_Of_Columns_For_Each_Table()
    {
        testName.set("Validate that the column count and list of columns for each table");

        authTokenTestSteps = new authToken_Test_Steps();
        tableSchemaTestSteps = new tableSchema_Test_Steps();

        authTokenTestSteps.requestBody(prop.getProperty("ValidUsername"),prop.getProperty("API_Password"),prop.getProperty("API_Database"));
        authTokenTestSteps.storeTokenInGlobalVariable();

        tableSchemaTestSteps.requestBodyParams(prop.getProperty("tableSchemaName"));
        tableSchemaTestSteps.requestBody();
        tableSchemaTestSteps.verifyStatusCode();
        tableSchemaTestSteps.verifyTotalSchemaTableCountNotZero();
        tableSchemaTestSteps.getAllColumnNameCount();
        tableSchemaTestSteps.verifyTotalColumnCountAndColumnNameCount();
    }

    /** Validate error handling for requests for schemas of nonexistent tables */
    @Test(groups = {"Smoke"})
    public void validate_Error_Handling_For_Requested_Schema_Of_Nonexistent_Table()
    {
        testName.set("Validate error handling for requests for schemas of nonexistent tables");

        authTokenTestSteps = new authToken_Test_Steps();
        tableSchemaTestSteps = new tableSchema_Test_Steps();

        authTokenTestSteps.requestBody(prop.getProperty("ValidUsername"),prop.getProperty("API_Password"),prop.getProperty("API_Database"));
        authTokenTestSteps.storeTokenInGlobalVariable();

        tableSchemaTestSteps.requestBodyParams(prop.getProperty("nonExistentTableName"));
        tableSchemaTestSteps.requestBody();
        tableSchemaTestSteps.verifyStatusCode();
        tableSchemaTestSteps.verifyNonexistentTableNameError();
    }

    /** Validate Schema Retrieval Works Irrespective Of The Casing Of The Table Name Parameter */
    @Test(groups = {"Smoke"})
    public void validate_Schema_Retrieval_Irrespective_Of_The_Case_Insensitivity_Of_Table_Name()
    {
        testName.set("Validate Schema Retrieval Irrespective Of The Case Insensitivity Of Table Name");

        authTokenTestSteps = new authToken_Test_Steps();
        tableSchemaTestSteps = new tableSchema_Test_Steps();

        authTokenTestSteps.requestBody(prop.getProperty("ValidUsername"),prop.getProperty("API_Password"),prop.getProperty("API_Database"));
        authTokenTestSteps.storeTokenInGlobalVariable();

        tableSchemaTestSteps.requestBodyParams(prop.getProperty("tableSchemaName"));
        tableSchemaTestSteps.requestBody();
        tableSchemaTestSteps.verifyStatusCode();
        tableSchemaTestSteps.verifyTableNameParameterCaseInsensitivity();
    }

    /** Validate error handling for improperly formatted table names (e.g. leading spaces) */
    @Test(groups = {"Smoke"})
    public void validate_Error_Handling_For_Improperly_Formatted_Table_Names_Leading()
    {
        testName.set("validate Error Handling For Improperly Formatted Table Names Leading");

        authTokenTestSteps = new authToken_Test_Steps();
        tableSchemaTestSteps = new tableSchema_Test_Steps();

        authTokenTestSteps.requestBody(prop.getProperty("ValidUsername"),prop.getProperty("API_Password"),prop.getProperty("API_Database"));
        authTokenTestSteps.storeTokenInGlobalVariable();

        tableSchemaTestSteps.requestBodyParams(" Boxes");
        tableSchemaTestSteps.requestBody();
        tableSchemaTestSteps.verifyStatusCode();
        tableSchemaTestSteps.verifyLeadingAndTrailingOfGivenTableName();
    }

    /** Verifying Columns Are Not Duplicated In The Table Schema Response */
    @Test(groups = {"Smoke"})
    public void verifying_Columns_Are_Not_Duplicated_In_The_Table_Schema_Response()
    {
        testName.set("Verifying Columns Are Not Duplicated In The Table Schema Response");

        authTokenTestSteps = new authToken_Test_Steps();
        tableSchemaTestSteps = new tableSchema_Test_Steps();

        authTokenTestSteps.requestBody(prop.getProperty("ValidUsername"),prop.getProperty("API_Password"),prop.getProperty("API_Database"));
        authTokenTestSteps.storeTokenInGlobalVariable();

        tableSchemaTestSteps.requestBodyParams(prop.getProperty("tableSchemaName"));
        tableSchemaTestSteps.requestBody();
        tableSchemaTestSteps.verifyStatusCode();
        tableSchemaTestSteps.getAllColumnNameCount();
        tableSchemaTestSteps.verifyColumnNameNotContainsDuplicated();
    }

    /** Tear Down method executing according to test after complete test
     * @param result = They get test method name */
    @AfterMethod(groups = {"setUp"},alwaysRun = true)
    public void tearDown(ITestResult result) throws IOException
    {
        testReport(result);
    }
}
