package web.Web_TestCases;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import utilities.web_api_BaseClass;
import web.Web_TestSteps.grid_Test_Steps;
import web.Web_TestSteps.login_Test_Steps;
import web.Web_TestSteps.retention_Test_Steps;
import web.Web_TestSteps.vault_Test_Steps;

import java.io.IOException;

public class vault_Tests extends web_api_BaseClass {

    public login_Test_Steps loginTestSteps;
    public grid_Test_Steps gridTestSteps;
    public retention_Test_Steps retentionTestSteps;
    public vault_Test_Steps vaultTestSteps;

    /** FUS - 13252 Add Orphan Files To Vault Module */
    @Test(groups = {"Smoke"})
    public void add_Orphan_Files_To_Vault_Module() throws IOException
    {
        testName.set("Add Orphan Files To Vault Module");

        loginTestSteps = new login_Test_Steps(webDriver);
        vaultTestSteps = new vault_Test_Steps(webDriver);

        loginTestSteps.openLoginPage();
        loginTestSteps.selectDatabase(prop.getProperty("Web_Database"));
        loginTestSteps.enterUsernamePassword(prop.getProperty("Web_Valid_Username"),prop.getProperty("Web_Password"));
        loginTestSteps.signInButton();
        loginTestSteps.verifyHomePage();
        loginTestSteps.verifyUserLoginSuccess();

        vaultTestSteps.openVaultFromMenu();
        vaultTestSteps.uploadVaultFile("VaultAttachmentDocument.pdf");
        vaultTestSteps.verifyUploadedDocumentAttachmentInVault("VaultAttachmentDocument");
    }

    /** FUS - 13253 Add An Attachment To Record From Vault */
    @Test(groups = {"Smoke"})
    public void add_An_Attachment_To_Record_From_Vault() throws IOException, InterruptedException
    {
        testName.set("Add An Attachment To Record From Vault");

        loginTestSteps = new login_Test_Steps(webDriver);
        gridTestSteps = new grid_Test_Steps(webDriver);
        retentionTestSteps = new retention_Test_Steps(webDriver);
        vaultTestSteps = new vault_Test_Steps(webDriver);

        loginTestSteps.openLoginPage();
        loginTestSteps.selectDatabase(prop.getProperty("Web_Database"));
        loginTestSteps.enterUsernamePassword(prop.getProperty("Web_Valid_Username"),prop.getProperty("Web_Password"));
        loginTestSteps.signInButton();
        loginTestSteps.verifyHomePage();
        loginTestSteps.verifyUserLoginSuccess();

        vaultTestSteps.openVaultFromMenu();
        vaultTestSteps.uploadVaultFile("VaultAttachmentDocument.pdf");
        vaultTestSteps.verifyUploadedDocumentAttachmentInVault("VaultAttachmentDocument");
        vaultTestSteps.goBackToGrid();

        gridTestSteps.selectNavigationOption("AutomationWG");
        gridTestSteps.selectNavigationOption("EFiles");
        gridTestSteps.selectNavigationOption("All EFiles");
        gridTestSteps.verifyQueryPopupAndPressOk();
        gridTestSteps.verifySelectedViewIsOpened("All EFiles");

        retentionTestSteps.createEFileNewRecord();

        vaultTestSteps.selectAttachFromVaultOption();
        vaultTestSteps.selectVaultDocumentAndMoveAttachment("VaultAttachmentDocument");
        vaultTestSteps.goBackToGridAfterMoveAttachment();
        vaultTestSteps.sortRecordFromGrid("TestAutomation.txt");
        vaultTestSteps.selectRecordForAttachment();
        vaultTestSteps.getAttachedAttachmentNameFromViewerOfRecord();
        vaultTestSteps.verifyAttachedAttachmentName("VaultAttachmentDocument");
    }

    /** Tear Down method executing according to test after complete test
     * @param result = They get test method name */
    @AfterMethod(groups = {"setUp"},alwaysRun = true)
    public void tearDown(ITestResult result) throws IOException, InterruptedException {
        testReport(result);

        if (result.getStatus() == result.SUCCESS || result.getStatus() == result.FAILURE)
        {
            if (result.getMethod().getMethodName().equals("add_Orphan_Files_To_Vault_Module"))
            {
                try
                {
                    webDriver.navigate().refresh();
                    vaultTestSteps.deleteUploadedVaultAttachment("VaultAttachmentDocument");
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
            else if(result.getMethod().getMethodName().equals("add_An_Attachment_To_Record_From_Vault"))
            {
                webDriver.navigate().refresh();
                gridTestSteps.selectNavigationOption("AutomationWG");
                gridTestSteps.selectNavigationOption("EFiles");
                gridTestSteps.selectNavigationOption("All EFiles");
                gridTestSteps.verifyQueryPopupAndPressOk();
                gridTestSteps.verifySelectedViewIsOpened("All EFiles");

                vaultTestSteps.sortRecordFromGrid("TestAutomation.txt");
                vaultTestSteps.deleteCreatedRecord();
            }
            else
            {
                System.out.println("In this test case don't have any Tear Down steps !!!");
            }
        }
        closeBrowser();
    }
}
