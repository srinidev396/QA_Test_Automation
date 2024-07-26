package web.Web_TestCases;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import utilities.web_api_BaseClass;
import web.Web_TestSteps.grid_Test_Steps;
import web.Web_TestSteps.login_Test_Steps;
import web.Web_TestSteps.transfer_Test_Steps;

import java.io.IOException;

public class transfer_Tests extends web_api_BaseClass {

    public login_Test_Steps loginTestSteps;
    public grid_Test_Steps gridTestSteps;
    public transfer_Test_Steps transferTestSteps;

    /** FUS - 13249 Transfer Selected Records And Check Tracking History Using GRID */
    @Test(groups = {"Smoke"})
    public void transfer_Selected_Record_And_Check_Tracking_History_In_Grid() throws IOException, InterruptedException
    {
        testName.set("Transfer Selected Record And Check Tracking History Using Grid");

        loginTestSteps = new login_Test_Steps(webDriver);
        gridTestSteps = new grid_Test_Steps(webDriver);
        transferTestSteps = new transfer_Test_Steps(webDriver);

        loginTestSteps.openLoginPage();
        loginTestSteps.selectDatabase(prop.getProperty("Web_Database"));
        loginTestSteps.enterUsernamePassword(prop.getProperty("Web_Valid_Username"),prop.getProperty("Web_Password"));
        loginTestSteps.signInButton();
        loginTestSteps.verifyHomePage();
        loginTestSteps.verifyUserLoginSuccess();

        gridTestSteps.selectNavigationOption("AutomationWG");
        gridTestSteps.selectNavigationOption("PaperFiles");
        gridTestSteps.selectNavigationOption("All PaperFiles");
        gridTestSteps.verifyQueryPopupAndPressOk();
        gridTestSteps.verifySelectedViewIsOpened("All PaperFiles");

        transferTestSteps.enterNewPaperFilesRecord();
        transferTestSteps.selectTransferSelectedOption();
        transferTestSteps.selectContainerFromTheTransfer("Locations","Locations","Sales Files");
        transferTestSteps.getCurrentLocationOfRecord();
        transferTestSteps.selectTransferSelectedOption();
        transferTestSteps.selectContainerFromTheTransfer("Locations","Locations","Central File Room");
        transferTestSteps.getCurrentLocationOfRecord();
        transferTestSteps.selectTrackingHistoryOption("AutomationTest.txt");
        transferTestSteps.getFirst2TransferLocation();
        transferTestSteps.verifyTransferAndTrackingLocationList();
    }

    /** FUS - 13250 Transfer Record Using Barcode Track */
    @Test(groups = {"Smoke"})
    public void transfer_Record_Using_Barcode_Tracking() throws IOException, InterruptedException {
        testName.set("Transfer Record Using Barcode Tracking");

        loginTestSteps = new login_Test_Steps(webDriver);
        gridTestSteps = new grid_Test_Steps(webDriver);
        transferTestSteps = new transfer_Test_Steps(webDriver);

        loginTestSteps.openLoginPage();
        loginTestSteps.selectDatabase(prop.getProperty("Web_Database"));
        loginTestSteps.enterUsernamePassword(prop.getProperty("Web_Valid_Username"),prop.getProperty("Web_Password"));
        loginTestSteps.signInButton();
        loginTestSteps.verifyHomePage();
        loginTestSteps.verifyUserLoginSuccess();

        gridTestSteps.selectNavigationOption("AutomationWG");
        gridTestSteps.selectNavigationOption("PaperFiles");
        gridTestSteps.selectNavigationOption("All PaperFiles");
        gridTestSteps.verifyQueryPopupAndPressOk();
        gridTestSteps.verifySelectedViewIsOpened("All PaperFiles");

        transferTestSteps.enterNewPaperFilesRecord();
        transferTestSteps.getNewCreatedRecordID();
        transferTestSteps.openBarcodeTrackingFromMenu();

        transferTestSteps.enterDestinationBarcode("L5");
        transferTestSteps.verifyDestinationBarcodeDescription("Sales Files");
        transferTestSteps.enterObjectBarcode();
        transferTestSteps.verifyBarcodeTransferredLocationAndRecord("Sales Files");

        webDriver.navigate().refresh();

        transferTestSteps.enterDestinationBarcode("L2");
        transferTestSteps.verifyDestinationBarcodeDescription("Central File Room");
        transferTestSteps.enterObjectBarcode();
        transferTestSteps.verifyBarcodeTransferredLocationAndRecord("Central File Room");
    }

    /** FUS - 13251 Content Report For A Container After A Transfer */
    @Test(groups = {"Smoke"})
    public void content_Report_For_Container_After_Transfer() throws IOException, InterruptedException
    {
        testName.set("Content Report For A Container After A Transfer");

        loginTestSteps = new login_Test_Steps(webDriver);
        gridTestSteps = new grid_Test_Steps(webDriver);
        transferTestSteps = new transfer_Test_Steps(webDriver);

        loginTestSteps.openLoginPage();
        loginTestSteps.selectDatabase(prop.getProperty("Web_Database"));
        loginTestSteps.enterUsernamePassword(prop.getProperty("Web_Valid_Username"),prop.getProperty("Web_Password"));
        loginTestSteps.signInButton();
        loginTestSteps.verifyHomePage();
        loginTestSteps.verifyUserLoginSuccess();

        gridTestSteps.selectNavigationOption("AutomationWG");
        gridTestSteps.selectNavigationOption("PaperFiles");
        gridTestSteps.selectNavigationOption("All PaperFiles");
        gridTestSteps.verifyQueryPopupAndPressOk();
        gridTestSteps.verifySelectedViewIsOpened("All PaperFiles");

        transferTestSteps.enterNewPaperFilesRecord();
        transferTestSteps.getNewCreatedRecordID();

        ////////Sales Files Transfer Content Verification////////

        transferTestSteps.selectTransferSelectedOption();
        transferTestSteps.selectContainerFromTheTransfer("Locations","Locations","Sales Files");

        webDriver.navigate().refresh();

        gridTestSteps.selectNavigationOption("Tracking");
        gridTestSteps.selectNavigationOption("Location");
        gridTestSteps.selectNavigationOption("All Location");
        gridTestSteps.verifyQueryPopupAndPressOk();
        gridTestSteps.verifySelectedViewIsOpened("All Location");

        transferTestSteps.selectContainerRecordMenu("Sales Files");
        transferTestSteps.getContainerContentReport();
        transferTestSteps.verifyTransferredRecordAndContainerContentReport();

        ////////Sales Files Transfer Content Verification////////

        ////////Central File Room Transfer Content Verification////////

        webDriver.navigate().refresh();

        gridTestSteps.selectNavigationOption("AutomationWG");
        gridTestSteps.selectNavigationOption("PaperFiles");
        gridTestSteps.selectNavigationOption("All PaperFiles");
        gridTestSteps.verifyQueryPopupAndPressOk();
        gridTestSteps.verifySelectedViewIsOpened("All PaperFiles");

        transferTestSteps.sortListCreatedRecordFromGrid();

        transferTestSteps.selectTransferSelectedOption();
        transferTestSteps.selectContainerFromTheTransfer("Locations","Locations","Central File Room");

        webDriver.navigate().refresh();

        gridTestSteps.selectNavigationOption("Tracking");
        gridTestSteps.selectNavigationOption("Location");
        gridTestSteps.selectNavigationOption("All Location");
        gridTestSteps.verifyQueryPopupAndPressOk();
        gridTestSteps.verifySelectedViewIsOpened("All Location");

        transferTestSteps.selectContainerRecordMenu("Central File Room");
        transferTestSteps.getContainerContentReport();
        transferTestSteps.verifyTransferredRecordAndContainerContentReport();

        ////////Central File Room Transfer Content Verification////////
    }

    /** Tear Down method executing according to test after complete test
     * @param result = They get test method name */
    @AfterMethod(groups = {"setUp"},alwaysRun = true)
    public void tearDown(ITestResult result) throws IOException
    {
        testReport(result);

        if (result.getStatus() == result.SUCCESS || result.getStatus() == result.FAILURE)
        {
            if (result.getMethod().getMethodName().equals("transfer_Selected_Record_And_Check_Tracking_History_In_Grid") ||
                    result.getMethod().getMethodName().equals("transfer_Record_Using_Barcode_Tracking") ||
                    result.getMethod().getMethodName().equals("content_Report_For_Container_After_Transfer"))
            {
                try
                {
                    transferTestSteps.goBackToGrid();
                    webDriver.navigate().refresh();
                    gridTestSteps.selectNavigationOption("AutomationWG");
                    gridTestSteps.selectNavigationOption("PaperFiles");
                    gridTestSteps.selectNavigationOption("All PaperFiles");
                    gridTestSteps.verifyQueryPopupAndPressOk();
                    gridTestSteps.verifySelectedViewIsOpened("All PaperFiles");

                    transferTestSteps.selectRecordFromGrid("AutomationTest.txt");
                    transferTestSteps.deleteCreatedRecord();
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
