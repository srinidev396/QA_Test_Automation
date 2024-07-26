package web.Web_TestCases;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import utilities.web_api_BaseClass;
import web.Web_TestSteps.grid_Test_Steps;
import web.Web_TestSteps.login_Test_Steps;
import web.Web_TestSteps.search_Test_Steps;

import java.io.IOException;

public class search_Tests extends web_api_BaseClass {

    public login_Test_Steps loginTestSteps;
    public grid_Test_Steps gridTestSteps;
    public search_Test_Steps searchTestSteps;

    /** FUS - 12875	Search of table contents using the Global Search and access the data in the Grid */
    @Test(groups = {"Smoke"})
    public void search_Table_Contents_Using_Global_Search_And_Access_Grid_Data() throws IOException, InterruptedException
    {
        testName.set("Search Table Contents Using Global Search");

        loginTestSteps = new login_Test_Steps(webDriver);
        gridTestSteps = new grid_Test_Steps(webDriver);
        searchTestSteps = new search_Test_Steps(webDriver);

        loginTestSteps.openLoginPage();
        loginTestSteps.selectDatabase(prop.getProperty("Web_Database"));
        loginTestSteps.enterUsernamePassword(prop.getProperty("Web_Valid_Username"),prop.getProperty("Web_Password"));
        loginTestSteps.signInButton();
        loginTestSteps.verifyHomePage();
        loginTestSteps.verifyUserLoginSuccess();

        gridTestSteps.selectNavigationOption("AutomationWG");
        gridTestSteps.selectNavigationOption("Suppliers");
        gridTestSteps.selectNavigationOption("All Suppliers");
        gridTestSteps.verifyQueryPopupAndPressOk();
        gridTestSteps.verifySelectedViewIsOpened("All Suppliers");

        searchTestSteps.enterGlobalSearch("Jane Doe");
        searchTestSteps.expandSearchListFromPopupAndVerifyTableVisible("All Suppliers");
        searchTestSteps.verifySearchedDataIsVisible("Jane Doe");
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
