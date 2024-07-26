package web.Web_TestCases;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import utilities.web_api_BaseClass;
import web.Web_TestSteps.misc_Test_Steps;
import web.Web_TestSteps.login_Test_Steps;

import java.io.IOException;

public class misc_Tests extends web_api_BaseClass {

    public login_Test_Steps loginTestSteps;
    public misc_Test_Steps miscTestSteps;

    /** FUS - 12876	FusionRMS landing page displays content from the newly added newspanel URL */
    @Test(groups = {"Smoke"})
    public void content_From_Newly_Added_NewsPanel_URL() throws IOException
    {
        testName.set("Content From Newly Added News Panel URL");

        loginTestSteps = new login_Test_Steps(webDriver);
        miscTestSteps = new misc_Test_Steps(webDriver);

        loginTestSteps.openLoginPage();
        loginTestSteps.selectDatabase(prop.getProperty("Web_Database"));
        loginTestSteps.enterUsernamePassword(prop.getProperty("Web_Valid_Username"),prop.getProperty("Web_Password"));
        loginTestSteps.signInButton();
        loginTestSteps.verifyHomePage();
        loginTestSteps.verifyUserLoginSuccess();

        miscTestSteps.enterURL();
        miscTestSteps.verifyNewsPanelAddedSuccessfully();
    }

    /** FUS - 12882 Version and related information in the About Info page */
    @Test(groups = {"Smoke"})
    public void version_And_Related_About_Information_Page() throws IOException
    {
        testName.set("Verify Version And AboutUs Information");

        loginTestSteps = new login_Test_Steps(webDriver);
        miscTestSteps = new misc_Test_Steps(webDriver);

        loginTestSteps.openLoginPage();
        loginTestSteps.selectDatabase(prop.getProperty("Web_Database"));
        loginTestSteps.enterUsernamePassword(prop.getProperty("Web_Valid_Username"),prop.getProperty("Web_Password"));
        loginTestSteps.signInButton();
        loginTestSteps.verifyHomePage();
        loginTestSteps.verifyUserLoginSuccess();

        miscTestSteps.openAboutPage();
        miscTestSteps.verifyAboutPageInfo(prop.getProperty("Version"));
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
