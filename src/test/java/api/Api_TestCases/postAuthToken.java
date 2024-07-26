package api.Api_TestCases;

import api.Api_TestSteps.authToken_Test_Steps;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import utilities.web_api_BaseClass;
import org.testng.annotations.Test;

import java.io.IOException;

public class postAuthToken extends web_api_BaseClass {

    public authToken_Test_Steps authTokenTestSteps;

    /** Verify Token Generated With Valid Credential */
    @Test(groups = {"Smoke"})
    public void generate_Token_With_Valid_Credential()
    {
        testName.set("Verify token not generated with Valid credentials");

        authTokenTestSteps = new authToken_Test_Steps();

        authTokenTestSteps.requestBody(prop.getProperty("ValidUsername"),prop.getProperty("API_Password"),prop.getProperty("API_Database"));
        authTokenTestSteps.verifyStatusCode();
        authTokenTestSteps.storeTokenInGlobalVariable();
        authTokenTestSteps.verifyTokenNotNull();
    }

    /** Verify Token Generated Or Not With Invalid Credentials */
    @Test(groups = {"Smoke"})
    public void generate_Token_With_Invalid_Credential()
    {
        testName.set("Verify token not generated with Invalid credentials");

        authTokenTestSteps = new authToken_Test_Steps();

        authTokenTestSteps.requestBody(prop.getProperty("InvalidUsername"),prop.getProperty("API_Password"),prop.getProperty("API_Database"));
        authTokenTestSteps.verifyStatusCode();
        authTokenTestSteps.storeTokenInGlobalVariable();
        authTokenTestSteps.verifyTokenNotNull();
    }

    /** Tear Down method executing according to test after complete test
     * @param result = They get test method name */
    @AfterMethod(groups = {"setUp"},alwaysRun = true)
    public void tearDown(ITestResult result) throws IOException
    {
        testReport(result);
    }
}
