package api.Api_TestCases;

import api.Api_TestSteps.authToken_Test_Steps;
import api.Api_TestSteps.userViews_Test_Steps;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import utilities.web_api_BaseClass;

import java.io.IOException;

public class getUserViews extends web_api_BaseClass {

    public authToken_Test_Steps authTokenTestSteps;
    public userViews_Test_Steps userViewsTestSteps;

    /** Validate that users receive only views they are permitted to access */
    @Test(groups = {"Smoke"})
    public void validate_That_Users_Receive_Only_Views_They_Are_Permitted_To_Access()
    {
        testName.set("Validate that users receive only views they are permitted to access");

        authTokenTestSteps = new authToken_Test_Steps();
        userViewsTestSteps = new userViews_Test_Steps();

        authTokenTestSteps.requestBody(prop.getProperty("ViewPermitUsername"),prop.getProperty("API_Password"),prop.getProperty("API_Database"));
        authTokenTestSteps.storeTokenInGlobalVariable();

        userViewsTestSteps.requestBody();
        userViewsTestSteps.verifyStatusCode();
        userViewsTestSteps.getAllViewNameCount();
        userViewsTestSteps.verifyViewNameCountOfPermittedUser();
    }

    /** Validate view information is not duplicated in the response */
    @Test(groups = {"Smoke"})
    public void validate_View_Name_Not_Duplicated()
    {
        testName.set("Validate view information is not duplicated in the response");

        authTokenTestSteps = new authToken_Test_Steps();
        userViewsTestSteps = new userViews_Test_Steps();

        authTokenTestSteps.requestBody(prop.getProperty("ViewPermitUsername"),prop.getProperty("API_Password"),prop.getProperty("API_Database"));
        authTokenTestSteps.storeTokenInGlobalVariable();

        userViewsTestSteps.requestBody();
        userViewsTestSteps.verifyStatusCode();
        userViewsTestSteps.getAllViewNameCount();
        userViewsTestSteps.verifyViewNameNotContainsDuplicated();
    }

    /** Validate that disabled users receive an appropriate error message and no views in the response */
    @Test(groups = {"Smoke"})
    public void validate_Disabled_User_Error_Message_And_Views_Not_Display()
    {
        testName.set("Validate Disabled User Error Message And Views Not Display");

        authTokenTestSteps = new authToken_Test_Steps();
        userViewsTestSteps = new userViews_Test_Steps();

        authTokenTestSteps.requestBody(prop.getProperty("DisabledUser"),prop.getProperty("API_Password"),prop.getProperty("API_Database"));
        userViewsTestSteps.verifyDisabledUserStatusCodeAndErrorMessage();
        authTokenTestSteps.storeTokenInGlobalVariable();

        userViewsTestSteps.requestBody();
        userViewsTestSteps.verifyDisabledUserViewStatusCode();
    }

    /** Validate users with no view access, expecting a 204 or 404 response */
    @Test(groups = {"Smoke"})
    public void validate_Users_With_No_View_Access_Shows_204_Or_404_Response()
    {
        testName.set("Validate users with no view access, expecting a 204 or 404 response");

        authTokenTestSteps = new authToken_Test_Steps();
        userViewsTestSteps = new userViews_Test_Steps();

        authTokenTestSteps.requestBody(prop.getProperty("NoViewUsername"),prop.getProperty("API_Password"),prop.getProperty("API_Database"));
        authTokenTestSteps.storeTokenInGlobalVariable();

        userViewsTestSteps.requestBody();
        userViewsTestSteps.verifyViewNameCountOfNoViewUser();
    }

    /** Tear Down method executing according to test after complete test
     * @param result = They get test method name */
    @AfterMethod(groups = {"setUp"},alwaysRun = true)
    public void tearDown(ITestResult result) throws IOException
    {
        testReport(result);
    }
}
