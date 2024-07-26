package web.Web_TestCases;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import utilities.web_api_BaseClass;
import web.Web_TestSteps.docViewer_Test_Steps;
import web.Web_TestSteps.grid_Test_Steps;
import web.Web_TestSteps.login_Test_Steps;
import web.Web_TestSteps.retention_Test_Steps;

import java.io.IOException;

public class docViewer_Tests extends web_api_BaseClass {

    public login_Test_Steps loginTestSteps;
    public grid_Test_Steps gridTestSteps;
    public retention_Test_Steps retentionTestSteps;
    public docViewer_Test_Steps docViewerTestSteps;

    /** FUS - 12867	Add new document through Attachment Preview */
    @Test(groups = {"Smoke"})
    public void add_New_Document_Through_Attachment_Preview() throws IOException, InterruptedException
    {
        testName.set("Add New Document Through Attachment Preview");

        loginTestSteps = new login_Test_Steps(webDriver);
        gridTestSteps = new grid_Test_Steps(webDriver);
        retentionTestSteps = new retention_Test_Steps(webDriver);
        docViewerTestSteps = new docViewer_Test_Steps(webDriver);

        loginTestSteps.openLoginPage();
        loginTestSteps.selectDatabase(prop.getProperty("Web_Database"));
        loginTestSteps.enterUsernamePassword(prop.getProperty("Web_Valid_Username"),prop.getProperty("Web_Password"));
        loginTestSteps.signInButton();
        loginTestSteps.verifyHomePage();
        loginTestSteps.verifyUserLoginSuccess();

        gridTestSteps.selectNavigationOption("AutomationWG");
        gridTestSteps.selectNavigationOption("EFiles");
        gridTestSteps.selectNavigationOption("All EFiles");
        gridTestSteps.verifyQueryPopupAndPressOk();
        gridTestSteps.verifySelectedViewIsOpened("All EFiles");

        retentionTestSteps.createEFileNewRecord();

        docViewerTestSteps.selectRecordForAttachment();
        docViewerTestSteps.selectBrowserFileLink();
        docViewerTestSteps.uploadAttachmentDocument("SinglePageDocument.pdf");
    }

    /** FUS - 12869	Download a document through Attachment Preview */
    @Test(groups = {"Smoke"})
    public void download_Document_Through_Attachment_Preview() throws IOException, InterruptedException
    {
        testName.set("Download Document Through Attachment Preview");

        loginTestSteps = new login_Test_Steps(webDriver);
        gridTestSteps = new grid_Test_Steps(webDriver);
        retentionTestSteps = new retention_Test_Steps(webDriver);
        docViewerTestSteps = new docViewer_Test_Steps(webDriver);

        loginTestSteps.openLoginPage();
        loginTestSteps.selectDatabase(prop.getProperty("Web_Database"));
        loginTestSteps.enterUsernamePassword(prop.getProperty("Web_Valid_Username"),prop.getProperty("Web_Password"));
        loginTestSteps.signInButton();
        loginTestSteps.verifyHomePage();
        loginTestSteps.verifyUserLoginSuccess();

        gridTestSteps.selectNavigationOption("AutomationWG");
        gridTestSteps.selectNavigationOption("EFiles");
        gridTestSteps.selectNavigationOption("All EFiles");
        gridTestSteps.verifyQueryPopupAndPressOk();
        gridTestSteps.verifySelectedViewIsOpened("All EFiles");

        retentionTestSteps.createEFileNewRecord();

        docViewerTestSteps.selectRecordForAttachment();
        docViewerTestSteps.selectBrowserFileLink();
        docViewerTestSteps.uploadAttachmentDocument("SinglePageDocument.pdf");
        docViewerTestSteps.downloadAttachedDocumentFromPopup();
    }

    /** FUS - 12870	Opening a multipage document in Document Viewer */
    @Test(groups = {"Smoke"})
    public void opening_MultiPage_Document_In_Attachment_Viewer() throws IOException, InterruptedException
    {
        testName.set("Opening MultiPage Document In Attachment Viewer");

        loginTestSteps = new login_Test_Steps(webDriver);
        gridTestSteps = new grid_Test_Steps(webDriver);
        retentionTestSteps = new retention_Test_Steps(webDriver);
        docViewerTestSteps = new docViewer_Test_Steps(webDriver);

        loginTestSteps.openLoginPage();
        loginTestSteps.selectDatabase(prop.getProperty("Web_Database"));
        loginTestSteps.enterUsernamePassword(prop.getProperty("Web_Valid_Username"),prop.getProperty("Web_Password"));
        loginTestSteps.signInButton();
        loginTestSteps.verifyHomePage();
        loginTestSteps.verifyUserLoginSuccess();

        gridTestSteps.selectNavigationOption("AutomationWG");
        gridTestSteps.selectNavigationOption("EFiles");
        gridTestSteps.selectNavigationOption("All EFiles");
        gridTestSteps.verifyQueryPopupAndPressOk();
        gridTestSteps.verifySelectedViewIsOpened("All EFiles");

        retentionTestSteps.createEFileNewRecord();

        docViewerTestSteps.selectRecordForAttachment();
        docViewerTestSteps.selectBrowserFileLink();
        docViewerTestSteps.uploadAttachmentDocument("MultiPageDocument.pdf");
    }

    /** FUS - 12871	Creating new versions of an attachment in Document Viewer */
    @Test(groups = {"Smoke"})
    public void creating_New_Version_Of_Attachment_In_Document_Viewer() throws IOException, InterruptedException
    {
        testName.set("Creating New Version Of Attachment In Document Viewer");

        loginTestSteps = new login_Test_Steps(webDriver);
        gridTestSteps = new grid_Test_Steps(webDriver);
        retentionTestSteps = new retention_Test_Steps(webDriver);
        docViewerTestSteps = new docViewer_Test_Steps(webDriver);

        loginTestSteps.openLoginPage();
        loginTestSteps.selectDatabase(prop.getProperty("Web_Database"));
        loginTestSteps.enterUsernamePassword(prop.getProperty("Web_Valid_Username"),prop.getProperty("Web_Password"));
        loginTestSteps.signInButton();
        loginTestSteps.verifyHomePage();
        loginTestSteps.verifyUserLoginSuccess();

        gridTestSteps.selectNavigationOption("AutomationWG");
        gridTestSteps.selectNavigationOption("EFiles");
        gridTestSteps.selectNavigationOption("All EFiles");
        gridTestSteps.verifyQueryPopupAndPressOk();
        gridTestSteps.verifySelectedViewIsOpened("All EFiles");

        retentionTestSteps.createEFileNewRecord();

        docViewerTestSteps.selectRecordForAttachment();
        docViewerTestSteps.selectBrowserFileLink();
        docViewerTestSteps.uploadAttachmentDocument("SinglePageDocument.pdf");
        docViewerTestSteps.openDocumentViewerFromAttachmentViewer();
        docViewerTestSteps.selectAttachmentFromAttachmentListFromDocumentViewer("Attachment 1","[V 1]");
        docViewerTestSteps.selectTabsFromDocumentViewer("Attachment");
        docViewerTestSteps.createNewVersion();
        docViewerTestSteps.uploadFileOnWindowsPopup("SinglePageDocument.pdf");
        docViewerTestSteps.waitUntilAttachmentUploadOnDocumentViewer();
        docViewerTestSteps.selectTabsFromDocumentViewer("Attachment");
        docViewerTestSteps.verifyNewVersionCreated("[V 2]");
    }

    /** FUS - 12872	Deleting Attachments in Document Viewer */
    @Test(groups = {"Smoke"})
    public void deleting_Attachments_In_Document_Viewer() throws IOException, InterruptedException
    {
        testName.set("Deleting Attachment In Document Viewer");

        loginTestSteps = new login_Test_Steps(webDriver);
        gridTestSteps = new grid_Test_Steps(webDriver);
        retentionTestSteps = new retention_Test_Steps(webDriver);
        docViewerTestSteps = new docViewer_Test_Steps(webDriver);

        loginTestSteps.openLoginPage();
        loginTestSteps.selectDatabase(prop.getProperty("Web_Database"));
        loginTestSteps.enterUsernamePassword(prop.getProperty("Web_Valid_Username"),prop.getProperty("Web_Password"));
        loginTestSteps.signInButton();
        loginTestSteps.verifyHomePage();
        loginTestSteps.verifyUserLoginSuccess();

        gridTestSteps.selectNavigationOption("AutomationWG");
        gridTestSteps.selectNavigationOption("EFiles");
        gridTestSteps.selectNavigationOption("All EFiles");
        gridTestSteps.verifyQueryPopupAndPressOk();
        gridTestSteps.verifySelectedViewIsOpened("All EFiles");

        retentionTestSteps.createEFileNewRecord();

        docViewerTestSteps.selectRecordForAttachment();
        docViewerTestSteps.openDocumentViewerFromAttachmentViewer();
        docViewerTestSteps.addNewAttachmentFromDocumentViewer();
        docViewerTestSteps.uploadFileOnWindowsPopup("SinglePageDocument.pdf");
        docViewerTestSteps.addNewAttachmentNameFromPopup("Test_Demo");
        docViewerTestSteps.selectTabsFromDocumentViewer("Attachment");
        docViewerTestSteps.verifyNewVersionCreated("[V 1]");
        docViewerTestSteps.selectAndDeleteAttachmentFromDocumentViewer("Test_Demo","[V 1]");
    }

    /** FUS - 12873	Add documents to the Attachment Cart */
    @Test(groups = {"Smoke"})
    public void add_Document_To_The_Attachment_Cart() throws IOException, InterruptedException
    {
        testName.set("Add Document Into The Attachment Cart");

        loginTestSteps = new login_Test_Steps(webDriver);
        gridTestSteps = new grid_Test_Steps(webDriver);
        retentionTestSteps = new retention_Test_Steps(webDriver);
        docViewerTestSteps = new docViewer_Test_Steps(webDriver);

        loginTestSteps.openLoginPage();
        loginTestSteps.selectDatabase(prop.getProperty("Web_Database"));
        loginTestSteps.enterUsernamePassword(prop.getProperty("Web_Valid_Username"),prop.getProperty("Web_Password"));
        loginTestSteps.signInButton();
        loginTestSteps.verifyHomePage();
        loginTestSteps.verifyUserLoginSuccess();

        gridTestSteps.selectNavigationOption("AutomationWG");
        gridTestSteps.selectNavigationOption("EFiles");
        gridTestSteps.selectNavigationOption("All EFiles");
        gridTestSteps.verifyQueryPopupAndPressOk();
        gridTestSteps.verifySelectedViewIsOpened("All EFiles");

        retentionTestSteps.createEFileNewRecord();

        docViewerTestSteps.selectRecordForAttachment();
        docViewerTestSteps.openDocumentViewerFromAttachmentViewer();
        docViewerTestSteps.addNewAttachmentFromDocumentViewer();
        docViewerTestSteps.uploadFileOnWindowsPopup("SinglePageDocument.pdf");
        docViewerTestSteps.addNewAttachmentNameFromPopup("Test_Demo");
        docViewerTestSteps.selectTabsFromDocumentViewer("Attachment");
        docViewerTestSteps.verifyNewVersionCreated("[V 1]");
        docViewerTestSteps.selectAttachmentDocument("Test_Demo","[V 1]");
        docViewerTestSteps.addAttachmentInCart();
        docViewerTestSteps.openAttachmentsCartFromMenu();
        docViewerTestSteps.verifyAddedAttachmentExistInCart("Test_Demo - [V 1]");
    }

    /** FUS - 12874	Download documents from the Attachment Cart */
    @Test(groups = {"Smoke"})
    public void download_Document_From_The_Attachment_Cart() throws IOException, InterruptedException
    {
        testName.set("Download Document From The Attachment Cart");

        loginTestSteps = new login_Test_Steps(webDriver);
        gridTestSteps = new grid_Test_Steps(webDriver);
        retentionTestSteps = new retention_Test_Steps(webDriver);
        docViewerTestSteps = new docViewer_Test_Steps(webDriver);

        loginTestSteps.openLoginPage();
        loginTestSteps.selectDatabase(prop.getProperty("Web_Database"));
        loginTestSteps.enterUsernamePassword(prop.getProperty("Web_Valid_Username"),prop.getProperty("Web_Password"));
        loginTestSteps.signInButton();
        loginTestSteps.verifyHomePage();
        loginTestSteps.verifyUserLoginSuccess();

        gridTestSteps.selectNavigationOption("AutomationWG");
        gridTestSteps.selectNavigationOption("EFiles");
        gridTestSteps.selectNavigationOption("All EFiles");
        gridTestSteps.verifyQueryPopupAndPressOk();
        gridTestSteps.verifySelectedViewIsOpened("All EFiles");

        retentionTestSteps.createEFileNewRecord();

        docViewerTestSteps.selectRecordForAttachment();
        docViewerTestSteps.openDocumentViewerFromAttachmentViewer();
        docViewerTestSteps.addNewAttachmentFromDocumentViewer();
        docViewerTestSteps.uploadFileOnWindowsPopup("SinglePageDocument.pdf");
        docViewerTestSteps.addNewAttachmentNameFromPopup("Test_Demo");
        docViewerTestSteps.selectTabsFromDocumentViewer("Attachment");
        docViewerTestSteps.verifyNewVersionCreated("[V 1]");
        docViewerTestSteps.selectAttachmentDocument("Test_Demo","[V 1]");
        docViewerTestSteps.addAttachmentInCart();
        docViewerTestSteps.openAttachmentsCartFromMenu();
        docViewerTestSteps.verifyAddedAttachmentExistInCart("Test_Demo - [V 1]");
        docViewerTestSteps.openAttachmentsCartFromMenu();
        docViewerTestSteps.selectAndDownloadAttachmentFromCart("Test_Demo - [V 1]");
    }

    /** Tear Down method executing according to test after complete test
     * @param result = They get test method name */
    @AfterMethod(groups = {"setUp"},alwaysRun = true)
    public void tearDown(ITestResult result) throws IOException, InterruptedException
    {
        testReport(result);

        if (result.getStatus() == result.SUCCESS || result.getStatus() == result.FAILURE)
        {
            if (result.getMethod().getMethodName().equals("add_New_Document_Through_Attachment_Preview") ||
                    result.getMethod().getMethodName().equals("opening_MultiPage_Document_In_Attachment_Viewer"))
            {
                try
                {
                    webDriver.navigate().refresh();
                    gridTestSteps.selectNavigationOption("AutomationWG");
                    gridTestSteps.selectNavigationOption("EFiles");
                    gridTestSteps.selectNavigationOption("All EFiles");
                    gridTestSteps.verifyQueryPopupAndPressOk();
                    gridTestSteps.verifySelectedViewIsOpened("All EFiles");

                    retentionTestSteps.sortRecordFromGrid("TestAutomation.txt");

                    docViewerTestSteps.deleteCreatedRecord();
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
            else if (result.getMethod().getMethodName().equals("creating_New_Version_Of_Attachment_In_Document_Viewer") ||
                    result.getMethod().getMethodName().equals("deleting_Attachments_In_Document_Viewer") ||
                    result.getMethod().getMethodName().equals("add_Document_To_The_Attachment_Cart"))
            {
                try
                {
                    docViewerTestSteps.gotoDocumentViewer();
                    webDriver.navigate().refresh();
                    gridTestSteps.selectNavigationOption("AutomationWG");
                    gridTestSteps.selectNavigationOption("EFiles");
                    gridTestSteps.selectNavigationOption("All EFiles");
                    gridTestSteps.verifyQueryPopupAndPressOk();
                    gridTestSteps.verifySelectedViewIsOpened("All EFiles");

                    retentionTestSteps.sortRecordFromGrid("TestAutomation.txt");

                    docViewerTestSteps.deleteCreatedRecord();
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
            else if (result.getMethod().getMethodName().equals("download_Document_Through_Attachment_Preview"))
            {
                try
                {
                    webDriver.navigate().refresh();
                    gridTestSteps.selectNavigationOption("AutomationWG");
                    gridTestSteps.selectNavigationOption("EFiles");
                    gridTestSteps.selectNavigationOption("All EFiles");
                    gridTestSteps.verifyQueryPopupAndPressOk();
                    gridTestSteps.verifySelectedViewIsOpened("All EFiles");

                    retentionTestSteps.sortRecordFromGrid("TestAutomation.txt");

                    docViewerTestSteps.deleteCreatedRecord();

                    gridTestSteps.tearDown_Export_Selected_TXT_CSV("Attachment 1.pdf");
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
            else if (result.getMethod().getMethodName().equals("download_Document_From_The_Attachment_Cart"))
            {
                try
                {
                    docViewerTestSteps.gotoDocumentViewer();
                    webDriver.navigate().refresh();
                    gridTestSteps.selectNavigationOption("AutomationWG");
                    gridTestSteps.selectNavigationOption("EFiles");
                    gridTestSteps.selectNavigationOption("All EFiles");
                    gridTestSteps.verifyQueryPopupAndPressOk();
                    gridTestSteps.verifySelectedViewIsOpened("All EFiles");

                    retentionTestSteps.sortRecordFromGrid("TestAutomation.txt");

                    docViewerTestSteps.deleteCreatedRecord();

                    gridTestSteps.tearDown_Export_Selected_TXT_CSV("Attachments.zip");
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
