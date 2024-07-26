package web.Web_TestCases;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import utilities.web_api_BaseClass;
import web.Web_TestSteps.grid_Test_Steps;
import web.Web_TestSteps.import_Test_Steps;
import web.Web_TestSteps.login_Test_Steps;

import java.io.IOException;

public class import_Tests extends web_api_BaseClass {

    public login_Test_Steps loginTestSteps;
    public import_Test_Steps importTestSteps;
    public grid_Test_Steps gridTestSteps;

    /** FUS - 12877	Import records to table using the Import feature */
    @Test(groups = {"Smoke"})
    public void import_Records_To_Table_Using_The_Import_Feature() throws IOException, InterruptedException
    {
        testName.set("Import Record To Table Using Import Feature");

        loginTestSteps = new login_Test_Steps(webDriver);
        gridTestSteps = new grid_Test_Steps(webDriver);
        importTestSteps = new import_Test_Steps(webDriver);

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

        importTestSteps.selectImportFromMenu();
        importTestSteps.verifyImportedDataIntoGrid();
    }

    /** FUS - 13254 Setup New Import And Run The Import */
    @Test(groups = {"Smoke"})
    public void setup_New_Import_And_Run_The_Import() throws IOException, InterruptedException {
        testName.set("Setup New Import And Run The Import");

        loginTestSteps = new login_Test_Steps(webDriver);
        gridTestSteps = new grid_Test_Steps(webDriver);
        importTestSteps = new import_Test_Steps(webDriver);

        loginTestSteps.openLoginPage();
        loginTestSteps.selectDatabase(prop.getProperty("Web_Database"));
        loginTestSteps.enterUsernamePassword(prop.getProperty("Web_Valid_Username"),prop.getProperty("Web_Password"));
        loginTestSteps.signInButton();
        loginTestSteps.verifyHomePage();
        loginTestSteps.verifyUserLoginSuccess();

        importTestSteps.openImportFromMenu();
        importTestSteps.createNewImport("SuppliersNewDataImport.txt");
        importTestSteps.firstStageOfImportFileConfiguration("NewImportSetupSupplierDemo","Yes");
        importTestSteps.secondStageOfImportSelectDestinationTable("Suppliers");
        importTestSteps.secondStageOfImportSelectAndAddAvailableFields();
        importTestSteps.thirdStageOfImportDataPrivilegeOfTable();
        importTestSteps.selectAndRunNewImportedFileConfiguration("NewImportSetupSupplierDemo","SuppliersNewDataImport.txt");
        importTestSteps.goBackToGridForVerifyImportedData();

        webDriver.navigate().refresh();
        gridTestSteps.selectNavigationOption("AutomationWG");
        gridTestSteps.selectNavigationOption("Suppliers");
        gridTestSteps.selectNavigationOption("All Suppliers");
        gridTestSteps.verifyQueryPopupAndPressOk();
        gridTestSteps.verifySelectedViewIsOpened("All Suppliers");

        importTestSteps.sortInDescendingRecordGrid();
        importTestSteps.verifyNewImportedDataIntoGrid();
    }

    /** Tear Down method executing according to test after complete test
     * @param result = They get test method name */
    @AfterMethod(groups = {"setUp"},alwaysRun = true)
    public void tearDown(ITestResult result) throws IOException
    {
        testReport(result);

        if (result.getStatus() == result.SUCCESS || result.getStatus() == result.FAILURE)
        {
            if (result.getMethod().getMethodName().equals("import_Records_To_Table_Using_The_Import_Feature"))
            {
                try
                {
                    importTestSteps.selectAndDeleteImportedDataFromGrid("IT_Imported");
                    importTestSteps.selectAndDeleteImportedDataFromGrid("HR_Imported");
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
            else if (result.getMethod().getMethodName().equals("setup_New_Import_And_Run_The_Import"))
            {
                try
                {
                    importTestSteps.goBackToGridForVerifyImportedData();
                    webDriver.navigate().refresh();
                    gridTestSteps.selectNavigationOption("AutomationWG");
                    gridTestSteps.selectNavigationOption("Suppliers");
                    gridTestSteps.selectNavigationOption("All Suppliers");
                    gridTestSteps.verifyQueryPopupAndPressOk();
                    gridTestSteps.verifySelectedViewIsOpened("All Suppliers");
                    importTestSteps.sortInDescendingRecordGrid();
                    importTestSteps.selectAndDeleteRecordFromGrid("Import New TestData1");
                    importTestSteps.selectAndDeleteRecordFromGrid("Import New TestData2");

                    importTestSteps.goBackToImportPage();
                    importTestSteps.selectAndRemoveNewImported("NewImportSetupSupplierDemo");
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
