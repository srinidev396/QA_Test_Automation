package web.Web_TestCases;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import utilities.web_api_BaseClass;
import web.Web_TestSteps.grid_Test_Steps;
import web.Web_TestSteps.login_Test_Steps;
import web.Web_TestSteps.queryAndFavorite_Test_Steps;

import java.io.IOException;

public class queryAndFavorite_Tests extends web_api_BaseClass {

    public login_Test_Steps loginTestSteps;
    public grid_Test_Steps gridTestSteps;
    public queryAndFavorite_Test_Steps queryAndFavoriteTestSteps;

    /** FUS - 12864	Creating a new My Query and deleting the same */
    @Test(groups = {"Smoke"})
    public void create_New_My_Query_And_Delete_Same() throws IOException, InterruptedException
    {
        testName.set("Create New My Query And Delete Same");

        loginTestSteps = new login_Test_Steps(webDriver);
        gridTestSteps = new grid_Test_Steps(webDriver);
        queryAndFavoriteTestSteps = new queryAndFavorite_Test_Steps(webDriver);

        loginTestSteps.openLoginPage();
        loginTestSteps.selectDatabase(prop.getProperty("Web_Database"));
        loginTestSteps.enterUsernamePassword(prop.getProperty("Web_Valid_Username"),prop.getProperty("Web_Password"));
        loginTestSteps.signInButton();
        loginTestSteps.verifyHomePage();
        loginTestSteps.verifyUserLoginSuccess();

        gridTestSteps.selectNavigationOption("AutomationWG");
        gridTestSteps.selectNavigationOption("Department");
        gridTestSteps.selectNavigationOption("All Department");
        gridTestSteps.verifyQueryPopupAndPressOk();
        gridTestSteps.verifySelectedViewIsOpened("All Department");

        queryAndFavoriteTestSteps.selectQueryButton();
        queryAndFavoriteTestSteps.verifyMyQueryPopupVisible();
        queryAndFavoriteTestSteps.selectConditionAndEnterValue();
        queryAndFavoriteTestSteps.saveQueryWithName();
        gridTestSteps.selectNavigationOption("My Queries");
        gridTestSteps.selectNavigationOption("Test Demo");
        queryAndFavoriteTestSteps.verifySelectedQueryPopupVisible();
        gridTestSteps.verifySelectedViewIsOpened("All Department");
        queryAndFavoriteTestSteps.verifyAccordingMyQueryConditionRecordVisible();
    }

    /** FUS - 12865	Creating a new My Favorite and deleting the same */
    @Test(groups = {"Smoke"})
    public void create_New_My_Favorite_And_Delete_Same() throws IOException, InterruptedException
    {
        testName.set("Create New My Favorite And Delete Same");

        loginTestSteps = new login_Test_Steps(webDriver);
        gridTestSteps = new grid_Test_Steps(webDriver);
        queryAndFavoriteTestSteps = new queryAndFavorite_Test_Steps(webDriver);

        loginTestSteps.openLoginPage();
        loginTestSteps.selectDatabase(prop.getProperty("Web_Database"));
        loginTestSteps.enterUsernamePassword(prop.getProperty("Web_Valid_Username"),prop.getProperty("Web_Password"));
        loginTestSteps.signInButton();
        loginTestSteps.verifyHomePage();
        loginTestSteps.verifyUserLoginSuccess();

        gridTestSteps.selectNavigationOption("AutomationWG");
        gridTestSteps.selectNavigationOption("Department");
        gridTestSteps.selectNavigationOption("All Department");
        gridTestSteps.verifyQueryPopupAndPressOk();
        gridTestSteps.verifySelectedViewIsOpened("All Department");

        queryAndFavoriteTestSteps.selectNewFavorite();
        queryAndFavoriteTestSteps.enterCreateNewFavoriteName();
        queryAndFavoriteTestSteps.verifyNewFavoriteCreatedSuccessfullyMSG();
        queryAndFavoriteTestSteps.selectAddToFavorite();
        queryAndFavoriteTestSteps.selectFavoriteNameFromDropdown();
        queryAndFavoriteTestSteps.verifyAddToFavoriteSuccessfullyMSG();
        queryAndFavoriteTestSteps.selectCreatedFavoriteFromMyFavorite("Demo Favorite");
        gridTestSteps.verifySelectedViewIsOpened("All Department");
        queryAndFavoriteTestSteps.verifyAddedFavoriteDataIsVisibleIntoGrid();
    }

    /** Tear Down method executing according to test after complete test
     * @param result = They get test method name */
    @AfterMethod(groups = {"setUp"},alwaysRun = true)
    public void tearDown(ITestResult result) throws InterruptedException, IOException
    {
        testReport(result);

        if (result.getStatus() == result.SUCCESS || result.getStatus() == result.FAILURE)
        {
            if (result.getMethod().getMethodName().equals("create_New_My_Query_And_Delete_Same"))
            {
                try
                {
                    webDriver.navigate().refresh();
                    gridTestSteps.selectNavigationOption("My Queries");
                    queryAndFavoriteTestSteps.tearDown_Delete_Query_From_My_Queries();
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
            else if (result.getMethod().getMethodName().equals("create_New_My_Favorite_And_Delete_Same"))
            {
                try
                {
                    webDriver.navigate().refresh();
                    gridTestSteps.selectNavigationOption("My Favorites");
                    queryAndFavoriteTestSteps.tearDown_Delete_Created_Favorite_From_MyFavorite("Demo Favorite");
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

        closeBrowser();
    }


}
