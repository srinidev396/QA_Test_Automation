package web.Web_TestSteps;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.web_api_BaseClass;
import web.pageObjects.docViewer_PO;
import web.pageObjects.retention_PO;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;

public class docViewer_Test_Steps extends web_api_BaseClass {

    WebDriver driver;

    /**
     * Class constructor define webdriver
     * @param remoteDriver = Mention webdriver */
    public docViewer_Test_Steps(WebDriver remoteDriver) {
        driver = remoteDriver;
    }

    public docViewer_PO docViewerPo;
    public retention_PO retentionPo;
    public WebDriverWait wait;
    public ProcessBuilder processBuilder;

    /** Select Attachment option from grid of the record */
    public void selectRecordForAttachment()
    {
        docViewerPo = new docViewer_PO(webDriver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        docViewerPo.selectFirstRowOfAttachmentPaperPinIcon.click();
        wait.until(ExpectedConditions.visibilityOf(docViewerPo.verifyAttachmentPopupVisible));
        softAssert.assertTrue(docViewerPo.verifyAttachmentPopupVisible.isDisplayed());
        softAssert.assertAll();
    }

    /** Select Browse Files Link */
    public void selectBrowserFileLink()
    {
        docViewerPo = new docViewer_PO(webDriver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        docViewerPo.browseFilesLink.click();
    }

    /** Upload Attachment Document on attachment viewer
     * @param fileName = Give File Name Whatever Stored Inside The Framework
     *                 uploadFile_AutoIT_Script_EXE Folder (EX: SinglePageDocument.pdf) */
    public void uploadAttachmentDocument(String fileName) throws IOException, InterruptedException {
        docViewerPo = new docViewer_PO(webDriver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        uploadFileOnWindowsPopup(fileName);
        wait.until(ExpectedConditions.visibilityOf(docViewerPo.verifyDocumentUploadSuccessfully));
        softAssert.assertTrue(docViewerPo.verifyDocumentUploadSuccessfully.isDisplayed());
        softAssert.assertAll();
    }

    /** Upload File On Windows Popup
     * @param fileName = Enter File Name */
    public void uploadFileOnWindowsPopup(String fileName) throws IOException {
        String filePath = System.getProperty("user.dir")+"\\framework_Tools\\test_Documents\\"+fileName;
        System.out.println(filePath);
        processBuilder = new ProcessBuilder(System.getProperty("user.dir")+"\\framework_Tools\\uploadFile_AutoIT_Script_EXE\\uploadFile.exe",filePath);
        processBuilder.start();
    }

    /** Wait Until Attachment Upload On Document Viewer */
    public void waitUntilAttachmentUploadOnDocumentViewer()
    {
        docViewerPo = new docViewer_PO(webDriver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.invisibilityOf(docViewerPo.loadingIcon));
        wait.until(ExpectedConditions.visibilityOf(docViewerPo.verifyCurrentAttachmentVisibleAsPageView));
    }

    /** Download Attachment from Popup */
    public void downloadAttachedDocumentFromPopup()
    {
        docViewerPo = new docViewer_PO(webDriver);
        docViewerPo.downloadAddedAttachmentFromPopup.click();
    }

    /** Open Document Viewer From Attachment Viewer */
    public void openDocumentViewerFromAttachmentViewer()
    {
        docViewerPo = new docViewer_PO(webDriver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        docViewerPo.openAttachmentViewerFromPopup.click();
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));
        wait.until(ExpectedConditions.visibilityOf(docViewerPo.verifyAttachmentViewerPageOpen));
        softAssert.assertTrue(docViewerPo.verifyAttachmentViewerPageOpen.isDisplayed());
        softAssert.assertAll();
    }

    /** Select Attachment From Attachment List From Document Viewer */
    public void selectAttachmentFromAttachmentListFromDocumentViewer(String attachmentName, String attachmentVersion)
    {
        docViewerPo = new docViewer_PO(webDriver);
        docViewerPo.selectAttachmentFromAttachmentListOfDocumentViewer(attachmentName,attachmentVersion);
    }

    /** Select Tabs From Navigation From Document Viewer*/
    public void selectTabsFromDocumentViewer(String tabName)
    {
        docViewerPo = new docViewer_PO(webDriver);
        if (tabName.equalsIgnoreCase("Attachment"))
        {
            docViewerPo.selectAttachmentTab.click();
        }
        else if (tabName.equalsIgnoreCase("Pages"))
        {
            docViewerPo.selectPageTab.click();
        }
        else
        {
            System.out.println("You have selected wrong option !!!");
        }
    }

    /** Create A New Version Of The Attachment */
    public void createNewVersion() {
        docViewerPo = new docViewer_PO(webDriver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.visibilityOf(docViewerPo.selectPaperPinFromDocumentViewer));
        docViewerPo.selectPaperPinFromDocumentViewer.click();
        wait.until(ExpectedConditions.visibilityOf(docViewerPo.selectAddVersionFromDocumentViewer));
        docViewerPo.selectAddVersionFromDocumentViewer.click();
    }

    /** Verify New Created Version Of The Attachment
     * @param versionName = Enter Version Name (EX: [V 1], [V 2]) */
    public void verifyNewVersionCreated(String versionName)
    {
        docViewerPo = new docViewer_PO(webDriver);
        ArrayList<String> versionList = new ArrayList<String>();

        for (WebElement e : docViewerPo.verifyAttachmentVersion)
        {
            versionList.add(e.getText());
        }
        System.out.println("Version List : " + versionList);
        softAssert.assertTrue(versionList.contains(versionName));
        softAssert.assertAll();
    }

    /** TearDown Method Delete Attached Document After Completed Test */
    public void tearDown_Delete_Uploaded_Attachment_From_Attachment_Viewer()
    {
        docViewerPo = new docViewer_PO(webDriver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        docViewerPo.openAttachmentViewerFromPopup.click();
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));
        wait.until(ExpectedConditions.visibilityOf(docViewerPo.verifyAttachmentViewerPageOpen));
        softAssert.assertTrue(docViewerPo.verifyAttachmentViewerPageOpen.isDisplayed());
        softAssert.assertAll();
    }

    /** Verify Attachment Exist In Attachment List
     * @param attachmentName = Enter Attachment Name
     * @param attachmentVersion = Enter Attachment Version */
    public void verifyAttachmentExistInAttachmentList(String attachmentName, String attachmentVersion)
    {
        docViewerPo = new docViewer_PO(webDriver);
        docViewerPo.verifyAttachmentExist(attachmentName,attachmentVersion);
    }

    /** Select Checkbox Of Particular Of Attachment And Delete From Document Viewer
     * @param attachmentName = Enter Attachment Name
     * @param attachmentVersion = Enter Attachment Version */
    public void selectAndDeleteAttachmentFromDocumentViewer(String attachmentName, String attachmentVersion)
    {
        docViewerPo = new docViewer_PO(webDriver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        docViewerPo.selectAttachmentCheckboxFromDocumentViewer(attachmentName,attachmentVersion);
        if (docViewerPo.deleteAttachmentButton.isDisplayed())
        {
            wait.until(ExpectedConditions.visibilityOf(docViewerPo.deleteAttachmentButton));
            docViewerPo.deleteAttachmentButton.click();
            wait.until(ExpectedConditions.visibilityOf(docViewerPo.verifyDeletePopupVisible));
            docViewerPo.deleteOKButton.click();
            wait.until(ExpectedConditions.invisibilityOf(docViewerPo.verifyDeletePopupVisible));
            wait.until(ExpectedConditions.invisibilityOf(docViewerPo.loadingIcon));
            wait.until(ExpectedConditions.visibilityOf(docViewerPo.verifyAttachmentViewerPageOpen));
        }
        else
        {
            System.out.println("Unable To Select Attachment");
        }
    }

    /** Add New Attachment From the Document Viewer */
    public void addNewAttachmentFromDocumentViewer()
    {
        docViewerPo = new docViewer_PO(webDriver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.visibilityOf(docViewerPo.selectPaperPinFromDocumentViewer));
        docViewerPo.selectPaperPinFromDocumentViewer.click();
        wait.until(ExpectedConditions.visibilityOf(docViewerPo.selectAddAttachmentFromDocumentViewer));
        docViewerPo.selectAddAttachmentFromDocumentViewer.click();
    }

    /** Add New Attachment Name From Popup
     * @param enterAttachmentName = Enter Attachment Name (EX: DemoTest)*/
    public void addNewAttachmentNameFromPopup(String enterAttachmentName)
    {
        docViewerPo = new docViewer_PO(webDriver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.visibilityOf(docViewerPo.verifyNewAttachmentPopupVisible));
        docViewerPo.enterNewAttachmentName.sendKeys(enterAttachmentName);
        docViewerPo.oKAddNewAttachmentPopup.click();
        wait.until(ExpectedConditions.invisibilityOf(docViewerPo.verifyNewAttachmentPopupVisible));
        wait.until(ExpectedConditions.invisibilityOf(docViewerPo.loadingIcon));
    }

    /** Select Attachment Document Form Navigation From Document Viewer
     * @param attachmentName = Enter Attachment Name
     * @param attachmentVersion = Enter Attachment Version */
    public void selectAttachmentDocument(String attachmentName, String attachmentVersion)
    {
        docViewerPo = new docViewer_PO(webDriver);
        docViewerPo.selectAttachmentCheckboxFromDocumentViewer(attachmentName,attachmentVersion);
    }

    /** Add Attachment In Cart */
    public void addAttachmentInCart()
    {
        docViewerPo = new docViewer_PO(webDriver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOf(docViewerPo.selectCartButtonFromOptions));
        docViewerPo.selectCartButtonFromOptions.click();
        wait.until(ExpectedConditions.visibilityOf(docViewerPo.verifyAttachmentAddSuccessfullyInCartMSG));
        softAssert.assertTrue(docViewerPo.verifyAttachmentAddSuccessfullyInCartMSG.isDisplayed());
        wait.until(ExpectedConditions.invisibilityOf(docViewerPo.verifyAttachmentAddSuccessfullyInCartMSG));
        softAssert.assertAll();
    }

    /** Open Attachments Cart From Menu */
    public void openAttachmentsCartFromMenu()
    {
        docViewerPo = new docViewer_PO(webDriver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.visibilityOf(docViewerPo.selectAttachmentsCartFromMenu));
        docViewerPo.selectAttachmentsCartFromMenu.click();
        wait.until(ExpectedConditions.visibilityOf(docViewerPo.verifyAttachmentCartPopupVisible));
    }

    /** Verify Added Attachment Displayed Or Not */
    public void verifyAddedAttachmentExistInCart(String attachmentName)
    {
        docViewerPo = new docViewer_PO(webDriver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        docViewerPo.verifyAddedAttachmentInCart(attachmentName);
        docViewerPo.closeAttachmentCartPopup.click();
        wait.until(ExpectedConditions.invisibilityOf(docViewerPo.verifyAttachmentCartPopupVisible));
    }

    /** Select and remove attachment from cart */
    public void selectAndRemoveAttachmentFromCart(String attachmentName)
    {
        docViewerPo = new docViewer_PO(webDriver);
        docViewerPo.selectCheckboxFromAttachmentCartPopup(attachmentName);
        docViewerPo.selectRemoveCartButton.click();
        docViewerPo.closeAttachmentCartPopup.click();
        wait.until(ExpectedConditions.invisibilityOf(docViewerPo.verifyAttachmentCartPopupVisible));
    }

    /** Remove attachment for document viewer for added attachment into cart */
    public void selectAndRemoveAttachmentFromViewerOfCart()
    {
        docViewerPo = new docViewer_PO(webDriver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.visibilityOf(docViewerPo.deleteAttachmentButton));
        docViewerPo.deleteAttachmentButton.click();
        wait.until(ExpectedConditions.visibilityOf(docViewerPo.verifyDeletePopupVisible));
        docViewerPo.deleteOKButton.click();
        wait.until(ExpectedConditions.invisibilityOf(docViewerPo.verifyDeletePopupVisible));
        wait.until(ExpectedConditions.invisibilityOf(docViewerPo.loadingIcon));
        wait.until(ExpectedConditions.visibilityOf(docViewerPo.verifyAttachmentViewerPageOpen));
    }

    /** Select and remove attachment from cart */
    public void selectAndDownloadAttachmentFromCart(String attachmentName)
    {
        docViewerPo = new docViewer_PO(webDriver);
        docViewerPo.selectCheckboxFromAttachmentCartPopup(attachmentName);
        docViewerPo.selectDownloadCartButton.click();
        wait.until(ExpectedConditions.invisibilityOf(docViewerPo.loadingIcon));
        docViewerPo.closeAttachmentCartPopup.click();
        wait.until(ExpectedConditions.invisibilityOf(docViewerPo.verifyAttachmentCartPopupVisible));
    }

    /** Delete Created Record After Test */
    public void deleteCreatedRecord()
    {
        retentionPo = new retention_PO(webDriver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(0));

        retentionPo.selectPaperPlaneIcon.click();
        retentionPo.selectDeleteRecord.click();
        wait.until(ExpectedConditions.visibilityOf(retentionPo.verifyDeleteItemPopup));
        retentionPo.yesDeleteButton.click();
        wait.until(ExpectedConditions.visibilityOf(retentionPo.verifyRecordDeletedSuccessfullyMSG));
        softAssert.assertTrue(retentionPo.verifyRecordDeletedSuccessfullyMSG.isDisplayed());
        softAssert.assertAll();
    }

    /** Go back to document viewer */
    public void gotoDocumentViewer()
    {
        retentionPo = new retention_PO(webDriver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.close();
        driver.switchTo().window(tabs.get(0));
    }
}
