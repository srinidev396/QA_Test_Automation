package api.Api_TestCases;

import api.Api_TestSteps.authToken_Test_Steps;
import api.Api_TestSteps.dbSchema_Test_Steps;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import utilities.web_api_BaseClass;

import java.io.IOException;

public class getDBSchema extends web_api_BaseClass {

    public authToken_Test_Steps authTokenTestSteps;
    public dbSchema_Test_Steps dbSchemaTestSteps;

    /** Validate Response Include All Tables From Database Schema */
    @Test(groups = {"Smoke"})
    public void validate_Response_Include_All_Tables_From_Database_Schema()
    {
        testName.set("Validate Response Include All Tables From Database Schema");

        authTokenTestSteps = new authToken_Test_Steps();
        dbSchemaTestSteps = new dbSchema_Test_Steps();

        authTokenTestSteps.requestBody(prop.getProperty("ValidUsername"),prop.getProperty("API_Password"),prop.getProperty("API_Database"));
        authTokenTestSteps.storeTokenInGlobalVariable();

        dbSchemaTestSteps.requestBody();
        dbSchemaTestSteps.verifyStatusCode();
        dbSchemaTestSteps.verifyTotalSchemaTableCountNotZero();
        dbSchemaTestSteps.getAllTableNameCount();
        dbSchemaTestSteps.verifyTotalSchemaTableAndTableNameCount();
    }

    /** Verify Table Column Total Count And Table Column Name Count Of Table Is Same */
    @Test(groups = "Smoke")
    public void verify_Table_Column_Total_Count_And_Table_Column_Count_Of_Table_Is_Equal()
    {
        testName.set("Verify Table Column Total Count And Table Column Name Count Of Table Is Same");

        authTokenTestSteps = new authToken_Test_Steps();
        dbSchemaTestSteps = new dbSchema_Test_Steps();

        authTokenTestSteps.requestBody(prop.getProperty("ValidUsername"),prop.getProperty("API_Password"),prop.getProperty("API_Database"));
        authTokenTestSteps.storeTokenInGlobalVariable();

        dbSchemaTestSteps.requestBody();
        dbSchemaTestSteps.verifyStatusCode();
        dbSchemaTestSteps.verifyTotalSchemaTableCountNotZero();
        dbSchemaTestSteps.verifyTotalColumnCountAndTotalColumnNameCount();
    }

    /** Tear Down method executing according to test after complete test
     * @param result = They get test method name */
    @AfterMethod(groups = {"setUp"},alwaysRun = true)
    public void tearDown(ITestResult result) throws IOException
    {
        testReport(result);
    }
}
