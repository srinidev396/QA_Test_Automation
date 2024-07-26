package web.Web_TestCases;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import utilities.web_api_BaseClass;
import web.Web_TestSteps.login_Test_Steps;
import org.testng.annotations.Test;

import java.io.IOException;

/**
 * In this class mentioned all the login test either is valid, invalid, verification, validating */
public class login_Tests extends web_api_BaseClass {

    public login_Test_Steps loginTestSteps;

    /**
     * In this test method define administrator user login successfully
     * FUS - 12854	Administrator user successful login with valid credentials */
    @Test(groups = {"Smoke"})
    public void administrator_User_Login_Successfully() throws IOException
    {
        testName.set("User Login Successfully With Administrator Privileges");

        loginTestSteps = new login_Test_Steps(webDriver);
        loginTestSteps.openLoginPage();
        loginTestSteps.selectDatabase(prop.getProperty("Web_Database"));
        loginTestSteps.enterUsernamePassword(prop.getProperty("Web_Valid_Username"),prop.getProperty("Web_Password"));
        loginTestSteps.signInButton();
        loginTestSteps.verifyHomePage();
        loginTestSteps.verifyUserLoginSuccess();
    }

    /**
     * In this test method define non administrator user login successfully
     * FUS - 12855	Non-administrator successful login with valid credentials */
    @Test(groups = {"Smoke"})
    public void non_Administrator_User_Login_Successfully() throws IOException
    {
        testName.set("User Login Successfully With Non Administrator Privileges");

        loginTestSteps = new login_Test_Steps(webDriver);
        loginTestSteps.openLoginPage();
        loginTestSteps.selectDatabase(prop.getProperty("Web_Database"));
        loginTestSteps.enterUsernamePassword(prop.getProperty("Non_Admin_Username"),prop.getProperty("Web_Password"));
        loginTestSteps.signInButton();
        loginTestSteps.verifyHomePage();
        loginTestSteps.verifyUserLoginSuccess();
    }

    /**
     * In this test method define disable user login unsuccessful
     * FUS - 12856	Disabled User cannot login to the application */
    @Test(groups = {"Smoke"})
    public void disable_User_Login_Unsuccessful() throws IOException
    {
        testName.set("Disable User Login Unsuccessfully");

        loginTestSteps = new login_Test_Steps(webDriver);
        loginTestSteps.openLoginPage();
        loginTestSteps.selectDatabase(prop.getProperty("Web_Database"));
        loginTestSteps.enterUsernamePassword(prop.getProperty("DisabledUser"),prop.getProperty("Web_Password"));
        loginTestSteps.signInButton();
        loginTestSteps.verifyInvalidUser();
    }

    /** Tear Down method executing according to test after complete test
     * @param result = They get test method name */
    @AfterMethod(groups = {"setUp"},alwaysRun = true)
    public void tearDown(ITestResult result) throws IOException
    {
        testReport(result);
        closeBrowser();
    }
}