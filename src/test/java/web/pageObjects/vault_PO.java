package web.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.web_api_BaseClass;

import java.time.Duration;

public class vault_PO extends web_api_BaseClass {

    /** This file contains all the xpaths of the Home page of the Tab FusionRMS
     * @param webDriver = initialized Webdriver
     * */
    public vault_PO(WebDriver webDriver) {
        web_api_BaseClass.webDriver = webDriver;
        PageFactory.initElements(webDriver,this);
    }

    public WebDriverWait wait;
    public String verifyUploadedDocumentToVault = "//div[@id='AttachmentModalBody']//div[@id='ThumbnailDetails']//a//div[contains(text(),'%s')]";
    public String selectVaultDocumentCheckbox = "//div[@id='AttachmentModalBody']//div[@id='ThumbnailDetails']//a//div[contains(text(),'%s')]/parent::a/parent::div//div//input";
    public String selectRecord = "//div[@class='truncated' and contains(text(),'%s')]";

    /** Verify Uploaded Document File In Vault
     * @param enterFileName = Enter File Name */
    public void verifyUploadedDocumentToVault(String enterFileName)
    {
        softAssert.assertTrue(webDriver.findElement(By.xpath(String.format(verifyUploadedDocumentToVault,enterFileName))).isDisplayed());
        softAssert.assertAll();
    }

    /** Select Vault Document Checkbox
     * @param enterFileName = Enter File Name */
    public void selectVaultDocumentCheckbox(String enterFileName)
    {
        wait = new WebDriverWait(webDriver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.visibilityOf(webDriver.findElement(By.xpath(String.format(selectVaultDocumentCheckbox,enterFileName)))));
        webDriver.findElement(By.xpath(String.format(selectVaultDocumentCheckbox,enterFileName))).click();
    }

    /** Select Record From Grid
     * @param recordName = Enter File Name */
    public void selectRecordFromGrid(String recordName)
    {
        wait = new WebDriverWait(webDriver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.visibilityOf(webDriver.findElement(By.xpath(String.format(selectRecord,recordName)))));
        webDriver.findElement(By.xpath(String.format(selectRecord,recordName))).click();
    }

    @FindBy(xpath = "//a[@id='vaultLabel']")
    public WebElement verifyVaultPage;

    @FindBy(xpath = "//button[@id='btnBrowseFile']")
    public WebElement browseFilesButton;

    @FindBy(xpath = "//div[contains(text(),'Attachment/s added successfully into Vault')]")
    public WebElement verifyVaultAttachmentSuccessMSG;

    @FindBy(xpath = "//button[@id='btnDeleteAttachment']")
    public WebElement deleteVaultButton;

    @FindBy(xpath = "//h4[@id='myModalDelete']")
    public WebElement verifyDeletePopup;

    @FindBy(xpath = "//button[@id='btnDelete']")
    public WebElement yesDeleteButton;

    @FindBy(xpath = "//div[contains(text(),'Attachment/s deleted successfully from the Vault')]")
    public WebElement verifyVaultAttachmentDeleteSuccessMSG;

    @FindBy(xpath = "(//div[@class='btn-group'])[2]")
    public WebElement selectPaperPlaneIcon;

    @FindBy(xpath = "//a[@id='OrphanAttachid']")
    public WebElement selectAttachFromVaultOption;

    @FindBy(xpath = "//ul[@id='breadcrumbs']//li[@data-original-title='Attch from vault']")
    public WebElement verifyVaultOpenedInGrid;

    @FindBy(xpath = "//div[@id='btnMoveAttachments']//button")
    public WebElement moveAttachmentButton;

    @FindBy(xpath = "//div[contains(text(),'Selected Attachment/s has been moved successfully')]")
    public WebElement verifyVaultAttachmentMoveSuccessMSG;

    @FindBy(xpath = "//ul[@id='breadcrumbs']//li[@data-viewname='All EFiles']//a")
    public WebElement goBackToGridFromNavigation;

    @FindBy(xpath = "(//span[contains(@class,'colHeader columnSorting sortAction')]//span[contains(text(),'Id')])[1]")
    public WebElement sortingIDSelectCreatedRecord;

    @FindBy(xpath = "(//td[@class='htDimmed currentRow'])[1]")
    public WebElement selectFirstRowOfAttachmentPaperPinIcon;

    @FindBy(xpath = "//label[@id='AttachmentsFormHeader']")
    public WebElement verifyAttachmentPopupVisible;

    @FindBy(xpath = "//div[@id='ThumbnailDetails']//div[@class='Thmbnail-header']")
    public WebElement getAttachedDocumentNameFromRecordAttachmentViewer;

    @FindBy(xpath = "//a[@id='btndeleterow']")
    public WebElement selectDeleteRecord;

    @FindBy(xpath = "//label[@id='dlgBsTitle']")
    public WebElement verifyDeleteItemPopup;

    @FindBy(xpath = "//a[contains(text(),'Yes')]")
    public WebElement yesDeleteRecordButton;

    @FindBy(xpath = "//div[contains(text(),'Record/s has been deleted successfully')]")
    public WebElement verifyRecordDeletedSuccessfullyMSG;
}
