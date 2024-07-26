package web.Web_TestSteps;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.web_api_BaseClass;
import web.pageObjects.grid_Navigation_PO;
import web.pageObjects.vault_PO;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;

public class vault_Test_Steps extends web_api_BaseClass {

    WebDriver driver;

    /**
     * Class constructor define webdriver
     * @param remoteDriver = Mention webdriver */
    public vault_Test_Steps(WebDriver remoteDriver) {
        driver = remoteDriver;
    }

    public WebDriverWait wait;
    public vault_PO vaultPo;
    public grid_Navigation_PO gridNavigationPo;
    public ProcessBuilder processBuilder;
    public String getAttachedAttachmentNameFromViewerOfRecord = null;

    /** Verify And Open Vault Page */
    public void openVaultFromMenu()
    {
        vaultPo = new vault_PO(webDriver);
        gridNavigationPo = new grid_Navigation_PO(webDriver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        gridNavigationPo.selectNavbarMenuOption("Vault");

        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));

        wait.until(ExpectedConditions.visibilityOf(vaultPo.verifyVaultPage));
        softAssert.assertTrue(vaultPo.verifyVaultPage.isDisplayed());
        softAssert.assertAll();
    }

    /** Upload Vault File
     * @param fileName = Enter File Name With Extension */
    public void uploadVaultFile(String fileName) throws IOException
    {
        vaultPo = new vault_PO(webDriver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        vaultPo.browseFilesButton.click();
        String filePath = System.getProperty("user.dir")+"\\framework_Tools\\test_Documents\\"+fileName;
        System.out.println(filePath);
        processBuilder = new ProcessBuilder(System.getProperty("user.dir")+"\\framework_Tools\\uploadFile_AutoIT_Script_EXE\\uploadFile.exe",filePath);
        processBuilder.start();

        wait.until(ExpectedConditions.visibilityOf(vaultPo.verifyVaultAttachmentSuccessMSG));
        softAssert.assertTrue(vaultPo.verifyVaultAttachmentSuccessMSG.isDisplayed());
        wait.until(ExpectedConditions.invisibilityOf(vaultPo.verifyVaultAttachmentSuccessMSG));
        softAssert.assertAll();
    }

    /** Verify Uploaded Document Attachment File In Vault
     * @param enterFileName = Enter File Name */
    public void verifyUploadedDocumentAttachmentInVault(String enterFileName)
    {
        vaultPo = new vault_PO(webDriver);
        vaultPo.verifyUploadedDocumentToVault(enterFileName);
    }

    /** Delete Uploaded Vault Document From Attachment
     * @param enterFileName = Enter File Name */
    public void deleteUploadedVaultAttachment(String enterFileName)
    {
        vaultPo = new vault_PO(webDriver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        wait.until(ExpectedConditions.visibilityOf(vaultPo.verifyVaultPage));
        vaultPo.selectVaultDocumentCheckbox(enterFileName);
        wait.until(ExpectedConditions.visibilityOf(vaultPo.deleteVaultButton));
        vaultPo.deleteVaultButton.click();
        wait.until(ExpectedConditions.visibilityOf(vaultPo.verifyDeletePopup));
        vaultPo.yesDeleteButton.click();
        wait.until(ExpectedConditions.visibilityOf(vaultPo.verifyVaultAttachmentDeleteSuccessMSG));
        softAssert.assertTrue(vaultPo.verifyVaultAttachmentDeleteSuccessMSG.isDisplayed());
        softAssert.assertAll();
    }

    /** Go Back To Grid */
    public void goBackToGrid()
    {
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.close();
        driver.switchTo().window(tabs.get(0));
        driver.navigate().refresh();
    }

    /** Select Attach From Vault Option From Grid */
    public void selectAttachFromVaultOption()
    {
        vaultPo = new vault_PO(webDriver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        vaultPo.selectPaperPlaneIcon.click();
        wait.until(ExpectedConditions.visibilityOf(vaultPo.selectAttachFromVaultOption));
        vaultPo.selectAttachFromVaultOption.click();
        wait.until(ExpectedConditions.visibilityOf(vaultPo.verifyVaultOpenedInGrid));
    }

    /** Select Vault Document And Move Attachment
     * @param enterFileName = Enter File Name */
    public void selectVaultDocumentAndMoveAttachment(String enterFileName)
    {
        vaultPo = new vault_PO(webDriver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        vaultPo.selectVaultDocumentCheckbox(enterFileName);
        vaultPo.moveAttachmentButton.click();
        wait.until(ExpectedConditions.visibilityOf(vaultPo.verifyVaultAttachmentMoveSuccessMSG));
        softAssert.assertTrue(vaultPo.verifyVaultAttachmentMoveSuccessMSG.isDisplayed());
        wait.until(ExpectedConditions.invisibilityOf(vaultPo.verifyVaultAttachmentMoveSuccessMSG));
        softAssert.assertAll();
    }

    /** Go Back To Grid After Move Attachment */
    public void goBackToGridAfterMoveAttachment()
    {
        vaultPo = new vault_PO(webDriver);
        vaultPo.goBackToGridFromNavigation.click();
    }

    /** Select Record From Grid and Sort List the Record
     * @param enterFileName = Enter File Name */
    public void sortRecordFromGrid(String enterFileName)
    {
        vaultPo = new vault_PO(webDriver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        Actions actions = new Actions(webDriver);

        actions.moveToElement(vaultPo.sortingIDSelectCreatedRecord).perform();
        actions.click(vaultPo.sortingIDSelectCreatedRecord).perform();
        actions.click(vaultPo.sortingIDSelectCreatedRecord).perform();

        vaultPo.selectRecordFromGrid(enterFileName);
    }

    /** Select Attachment option from grid of the record */
    public void selectRecordForAttachment()
    {
        vaultPo = new vault_PO(webDriver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        vaultPo.selectFirstRowOfAttachmentPaperPinIcon.click();
        wait.until(ExpectedConditions.visibilityOf(vaultPo.verifyAttachmentPopupVisible));
        softAssert.assertTrue(vaultPo.verifyAttachmentPopupVisible.isDisplayed());
        softAssert.assertAll();
    }

    /** Get Attached Attachment Name From Viewer Of Record */
    public void getAttachedAttachmentNameFromViewerOfRecord()
    {
        vaultPo = new vault_PO(webDriver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        wait.until(ExpectedConditions.visibilityOf(vaultPo.getAttachedDocumentNameFromRecordAttachmentViewer));
        getAttachedAttachmentNameFromViewerOfRecord = vaultPo.getAttachedDocumentNameFromRecordAttachmentViewer.getText();
    }

    /** Verify Attached Attachment Name */
    public void verifyAttachedAttachmentName(String attachmentFileName)
    {
        softAssert.assertEquals(getAttachedAttachmentNameFromViewerOfRecord,attachmentFileName);
    }

    /** Delete Created Record After Test */
    public void deleteCreatedRecord()
    {
        vaultPo = new vault_PO(webDriver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(0));

        vaultPo.selectPaperPlaneIcon.click();
        vaultPo.selectDeleteRecord.click();
        wait.until(ExpectedConditions.visibilityOf(vaultPo.verifyDeleteItemPopup));
        vaultPo.yesDeleteRecordButton.click();
        wait.until(ExpectedConditions.visibilityOf(vaultPo.verifyRecordDeletedSuccessfullyMSG));
        softAssert.assertTrue(vaultPo.verifyRecordDeletedSuccessfullyMSG.isDisplayed());
        softAssert.assertAll();
    }
}
