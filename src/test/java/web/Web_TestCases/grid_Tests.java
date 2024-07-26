package web.Web_TestCases;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import utilities.web_api_BaseClass;
import web.Web_TestSteps.grid_Test_Steps;
import web.Web_TestSteps.login_Test_Steps;

import java.io.IOException;

public class grid_Tests extends web_api_BaseClass {

    public login_Test_Steps loginTestSteps;
    public grid_Test_Steps gridTestSteps;

    /** FUS - 12857	User can add/edit/delete items in the grid view */
    @Test(groups = {"Smoke"})
    public void grid_New_Edit_Delete_Record() throws IOException, InterruptedException
    {
        testName.set("Create New Grid Record - Edit Grid Record - Delete Grid Record");

        loginTestSteps = new login_Test_Steps(webDriver);
        gridTestSteps = new grid_Test_Steps(webDriver);

        loginTestSteps.openLoginPage();
        loginTestSteps.selectDatabase(prop.getProperty("Web_Database"));
        loginTestSteps.enterUsernamePassword(prop.getProperty("Non_Admin_Username"),prop.getProperty("Web_Password"));
        loginTestSteps.signInButton();
        loginTestSteps.verifyHomePage();
        loginTestSteps.verifyUserLoginSuccess();

        gridTestSteps.selectNavigationOption("AutomationWG");
        gridTestSteps.selectNavigationOption("Department");
        gridTestSteps.selectNavigationOption("All Department");
        gridTestSteps.verifyQueryPopupAndPressOk();
        gridTestSteps.verifySelectedViewIsOpened("All Department");
        gridTestSteps.createNewDocumentRecord();
        gridTestSteps.verifyRecordAddedSuccessfullyAcknowledgementMessage();
        gridTestSteps.verifyNewDocumentRecord();
        gridTestSteps.editCreatedDocumentRecord();
        gridTestSteps.verifyRecordUpdatedSuccessfullyAcknowledgementMessage();
        gridTestSteps.verifyEditedDocumentRecord();
        gridTestSteps.deleteRecentCreatedDataRecord();
        gridTestSteps.verifyRecordDeletedSuccessfullyAcknowledgementMessage();
    }

    /** FUS - 12858	Exporting selected items from Grid to txt/csv */
    @Test(groups = {"Smoke"})
    public void export_Selected_Items_From_Grid_TXT_CSV() throws IOException, InterruptedException
    {
        testName.set("Export Selected Record From Grid TXT & CSV File");

        loginTestSteps = new login_Test_Steps(webDriver);
        gridTestSteps = new grid_Test_Steps(webDriver);

        loginTestSteps.openLoginPage();
        loginTestSteps.selectDatabase(prop.getProperty("Web_Database"));
        loginTestSteps.enterUsernamePassword(prop.getProperty("Non_Admin_Username"),prop.getProperty("Web_Password"));
        loginTestSteps.signInButton();
        loginTestSteps.verifyHomePage();
        loginTestSteps.verifyUserLoginSuccess();

        gridTestSteps.selectNavigationOption("AutomationWG");
        gridTestSteps.selectNavigationOption("Department");
        gridTestSteps.selectNavigationOption("All Department");
        gridTestSteps.verifyQueryPopupAndPressOk();
        gridTestSteps.verifySelectedViewIsOpened("All Department");
        gridTestSteps.selectExportSelected("txt");
        gridTestSteps.verifyFileSize("txt");
        gridTestSteps.selectExportSelected("csv");
        gridTestSteps.verifyFileSize("csv");
    }

    /** FUS - 12859	Exporting all items from Grid to txt/csv */
    @Test(groups = {"Smoke"})
    public void export_All_Items_From_Grid_TXT_CSV() throws IOException, InterruptedException
    {
        testName.set("Export All Record From Grid TXT & CSV File");

        loginTestSteps = new login_Test_Steps(webDriver);
        gridTestSteps = new grid_Test_Steps(webDriver);

        loginTestSteps.openLoginPage();
        loginTestSteps.selectDatabase(prop.getProperty("Web_Database"));
        loginTestSteps.enterUsernamePassword(prop.getProperty("Non_Admin_Username"),prop.getProperty("Web_Password"));
        loginTestSteps.signInButton();
        loginTestSteps.verifyHomePage();
        loginTestSteps.verifyUserLoginSuccess();

        gridTestSteps.selectNavigationOption("AutomationWG");
        gridTestSteps.selectNavigationOption("Department");
        gridTestSteps.selectNavigationOption("All Department");
        gridTestSteps.verifyQueryPopupAndPressOk();
        gridTestSteps.verifySelectedViewIsOpened("All Department");
        gridTestSteps.selectExportAll("txt");
        gridTestSteps.verifyFileSize("txt");
        gridTestSteps.selectExportAll("csv");
        gridTestSteps.verifyFileSize("csv");
    }

    /** FUS - 12860	Black & White printing for selected records in Grid */
    @Test(groups = {"Smoke"})
    public void black_And_White_Printing_Selected_Record_Grid() throws IOException, InterruptedException
    {
        testName.set("Verify Black And White Printing Selected Record From Grid");

        loginTestSteps = new login_Test_Steps(webDriver);
        gridTestSteps = new grid_Test_Steps(webDriver);

        loginTestSteps.openLoginPage();
        loginTestSteps.selectDatabase(prop.getProperty("Web_Database"));
        loginTestSteps.enterUsernamePassword(prop.getProperty("Non_Admin_Username"),prop.getProperty("Web_Password"));
        loginTestSteps.signInButton();
        loginTestSteps.verifyHomePage();
        loginTestSteps.verifyUserLoginSuccess();

        gridTestSteps.selectNavigationOption("AutomationWG");
        gridTestSteps.selectNavigationOption("Department");
        gridTestSteps.selectNavigationOption("All Department");
        gridTestSteps.verifyQueryPopupAndPressOk();
        gridTestSteps.verifySelectedViewIsOpened("All Department");
        gridTestSteps.selectBlackAndWhite();
    }

    /** FUS - 12861	Access Tabquick portal from FusionRMS Grid View */
    @Test(groups = {"Smoke"})
    public void access_TabQuik_Portal_From_Grid() throws IOException, InterruptedException
    {
        testName.set("Access TabQuick Portal From Grid");

        loginTestSteps = new login_Test_Steps(webDriver);
        gridTestSteps = new grid_Test_Steps(webDriver);

        loginTestSteps.openLoginPage();
        loginTestSteps.selectDatabase(prop.getProperty("Web_Database"));
        loginTestSteps.enterUsernamePassword(prop.getProperty("Non_Admin_Username"),prop.getProperty("Web_Password"));
        loginTestSteps.signInButton();
        loginTestSteps.verifyHomePage();
        loginTestSteps.verifyUserLoginSuccess();

        gridTestSteps.selectNavigationOption("AutomationWG");
        gridTestSteps.selectNavigationOption("Department");
        gridTestSteps.selectNavigationOption("All Department");
        gridTestSteps.verifyQueryPopupAndPressOk();
        gridTestSteps.verifySelectedViewIsOpened("All Department");
        gridTestSteps.selectTabquik();
        gridTestSteps.verifyTabquikWindowOpened();
    }

    /** FUS - 12862	Accessing saved My Queries to the Grid View */
    @Test(groups = {"Smoke"})
    public void access_Saved_My_Queries_To_Grid() throws IOException, InterruptedException
    {
        testName.set("Access Saved My Queries To Grid");

        loginTestSteps = new login_Test_Steps(webDriver);
        gridTestSteps = new grid_Test_Steps(webDriver);

        loginTestSteps.openLoginPage();
        loginTestSteps.selectDatabase(prop.getProperty("Web_Database"));
        loginTestSteps.enterUsernamePassword(prop.getProperty("Web_Valid_Username"),prop.getProperty("Web_Password"));
        loginTestSteps.signInButton();
        loginTestSteps.verifyHomePage();
        loginTestSteps.verifyUserLoginSuccess();

        gridTestSteps.selectNavigationOption("My Queries");
        gridTestSteps.selectNavigationOption("NewDepartments ");
        gridTestSteps.verifySelectedViewIsOpened("All Department");
        gridTestSteps.verifyAccordingMyQueryConditionRecordVisible();
    }

    /** FUS - 12863	Accessing saved My Favorites to the Grid View */
    @Test(groups = {"Smoke"})
    public void access_Saved_My_Favorites_To_Grid() throws IOException, InterruptedException
    {
        testName.set("Access Saved My Favorite To Grid");

        loginTestSteps = new login_Test_Steps(webDriver);
        gridTestSteps = new grid_Test_Steps(webDriver);

        loginTestSteps.openLoginPage();
        loginTestSteps.selectDatabase(prop.getProperty("Web_Database"));
        loginTestSteps.enterUsernamePassword(prop.getProperty("Web_Valid_Username"),prop.getProperty("Web_Password"));
        loginTestSteps.signInButton();
        loginTestSteps.verifyHomePage();
        loginTestSteps.verifyUserLoginSuccess();

        gridTestSteps.selectNavigationOption("My Favorites");
        gridTestSteps.selectNavigationOption("MyFavDepartments");
        gridTestSteps.verifySelectedViewIsOpened("All Department");
        gridTestSteps.verifyAccordingMyFavoriteConditionRecordVisible();
    }

    /** Tear Down method executing according to test after complete test
     * @param result = They get test method name */
    @AfterMethod(groups = {"setUp"},alwaysRun = true)
    public void tearDown(ITestResult result) throws InterruptedException, IOException
    {
        testReport(result);
        
        if (result.getStatus() == result.SUCCESS || result.getStatus() == result.FAILURE)
        {
            if (result.getMethod().getMethodName().equals("export_Selected_Items_From_Grid_TXT_CSV") ||
                    result.getMethod().getMethodName().equals("export_All_Items_From_Grid_TXT_CSV"))
            {
                try
                {
                    gridTestSteps.tearDown_Export_Selected_TXT_CSV("Department.txt");
                    gridTestSteps.tearDown_Export_Selected_TXT_CSV("Department.csv");
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
            else if (result.getMethod().getMethodName().equals("black_And_White_Printing_Selected_Record_Grid"))
            {
                try
                {
                    gridTestSteps.tearDown_Delete_Latest_Downloaded_File_Location();
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