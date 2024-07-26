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
import java.util.List;

public class docViewer_PO extends web_api_BaseClass {

    public WebDriverWait wait;

    /** This file contains all the xpaths of the Home page of the Tab FusionRMS
     * @param webDriver = initialized Webdriver
     * */
    public docViewer_PO(WebDriver webDriver) {
        web_api_BaseClass.webDriver = webDriver;
        PageFactory.initElements(webDriver,this);
    }

    public String selectAttachmentCheckbox = "//div[@id='attachmentsList']//a[contains(text(),'%s')]//span[contains(text(),'%s')]/parent::a/parent::li//input[@type='checkbox']";
    public String verifyAttachmentExist = "//div[@id='attachmentsList']//a[contains(text(),'%s')] //span[contains(text(),'%s')]";
    public String selectAttachmentFromAttachmentList = "//div[@id='attachmentsList']//a[contains(text(),'%s')] //span[contains(text(),'%s')]";
    public String verifyAddedAttachmentInCart = "//a[contains(text(),'%s')]";
    public String selectCheckboxFromAttachmentCartPopup = "//a[contains(text(),'%s')]/parent::td/parent::tr//td//input[@type='checkbox']";

    /** Verify Attachment Exist
     * @param attachmentName = Enter Attachment Name
     * @param attachmentVersion = Enter Attachment Version */
    public void verifyAttachmentExist(String attachmentName, String attachmentVersion)
    {
        softAssert.assertTrue(webDriver.findElement(By.xpath(String.format(verifyAttachmentExist,attachmentName,attachmentVersion))).isDisplayed());
        softAssert.assertAll();
    }

    /** Select Attachment Checkbox Form Document Viewer
     * @param attachmentName = Enter Attachment Name
     * @param attachmentVersion = Enter Attachment Version */
    public void selectAttachmentCheckboxFromDocumentViewer(String attachmentName, String attachmentVersion)
    {
        wait = new WebDriverWait(webDriver, Duration.ofSeconds(30));
        if(webDriver.findElement(By.xpath(String.format(selectAttachmentCheckbox,attachmentName,attachmentVersion))).isDisplayed())
        {
            wait.until(ExpectedConditions.visibilityOf(webDriver.findElement(By.xpath(String.format(selectAttachmentCheckbox,attachmentName,attachmentVersion)))));
            webDriver.findElement(By.xpath(String.format(selectAttachmentCheckbox,attachmentName,attachmentVersion))).click();
        }
    }

    /** Select Attachment From Attachment List Of Document Viewer
     * @param attachmentName = Enter Attachment Name
     * @param attachmentVersion = Enter Attachment Version */
    public void selectAttachmentFromAttachmentListOfDocumentViewer(String attachmentName, String attachmentVersion)
    {
        wait = new WebDriverWait(webDriver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.visibilityOf(webDriver.findElement(By.xpath(String.format(selectAttachmentFromAttachmentList,attachmentName,attachmentVersion)))));
        webDriver.findElement(By.xpath(String.format(selectAttachmentFromAttachmentList,attachmentName,attachmentVersion))).click();
    }

    /** Verify Added Attachment Into The Cart Popup
     * @param attachmentName = Enter Attachment Name (EX: DemoDocu - [V 1])*/
    public void verifyAddedAttachmentInCart(String attachmentName)
    {
        softAssert.assertTrue(webDriver.findElement(By.xpath(String.format(verifyAddedAttachmentInCart,attachmentName))).isDisplayed());
        softAssert.assertAll();
    }

    /** Select Checkbox From Attachment Cart Popup
     * @param attachmentName = Enter Attachment Name (EX: DemoDocu - [V 1]) */
    public void selectCheckboxFromAttachmentCartPopup(String attachmentName)
    {
        wait = new WebDriverWait(webDriver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.visibilityOf(webDriver.findElement(By.xpath(String.format(selectCheckboxFromAttachmentCartPopup,attachmentName)))));
        webDriver.findElement(By.xpath(String.format(selectCheckboxFromAttachmentCartPopup,attachmentName))).click();
    }

    @FindBy(xpath = "(//td[@class='htDimmed currentRow'])[1]")
    public WebElement selectFirstRowOfAttachmentPaperPinIcon;

    @FindBy(xpath = "//label[@id='AttachmentsFormHeader']")
    public WebElement verifyAttachmentPopupVisible;

    @FindBy(xpath = "//a[@id='ModalAddAttachment']")
    public WebElement browseFilesLink;

    @FindBy(xpath = "//div[contains(text(),'Attachment 1')]")
    public WebElement verifyDocumentUploadSuccessfully;

    @FindBy(xpath = "//span[@id='openAttachViewer']")
    public WebElement openAttachmentViewerFromPopup;

    @FindBy(xpath = "//div[@id='attachmentsList']")
    public WebElement verifyAttachmentViewerPageOpen;

    @FindBy(xpath = "//button[@id='delete_files_id']")
    public WebElement deleteAttachmentButton;

    @FindBy(xpath = "//h4[@id='DeleteModalLabel']")
    public WebElement verifyDeletePopupVisible;

    @FindBy(xpath = "//input[@id='DelAttachmentsaccept']")
    public WebElement deleteOKButton;

    @FindBy(xpath = "//a[@title='Download']")
    public WebElement downloadAddedAttachmentFromPopup;

    @FindBy(xpath = "//div[@id='attachmentDivButton_id']")
    public WebElement selectPaperPinFromDocumentViewer;

    @FindBy(xpath = "//a[@id='AddVersion_Id']")
    public WebElement selectAddVersionFromDocumentViewer;

    @FindBy(xpath = "//a[@id='AddAttachmentId']")
    public WebElement selectAddAttachmentFromDocumentViewer;

    @FindBy(xpath = "//h4[contains(text(),'Name Attachment')]")
    public WebElement verifyNewAttachmentPopupVisible;

    @FindBy(xpath = "//input[@id='AttachmentID']")
    public WebElement enterNewAttachmentName;

    @FindBy(xpath = "//button[@id='AddAttachmentBtnOk']")
    public WebElement oKAddNewAttachmentPopup;

    @FindBy(xpath = "//a[@id='attachmentBtn']")
    public WebElement selectAttachmentTab;

    @FindBy(xpath = "//a[@id='thumbnailsTabBtn']")
    public WebElement selectPageTab;

    @FindBy(xpath = "//div[@id='load_grdDrive' and contains(@style,'display: block;')]")
    public WebElement loadingIcon;

    @FindBy(xpath = "//i[@id='showFilename_id']")
    public WebElement verifyCurrentAttachmentVisibleAsPageView;

    @FindBy(xpath = "//div[@id='attachmentsList']/ul//li//a//span")
    public List<WebElement> verifyAttachmentVersion;

    @FindBy(xpath = "//button[@id='Addtocart_id']")
    public WebElement selectCartButtonFromOptions;

    @FindBy(xpath = "//div[contains(text(),'Attachment(s) added successfully into the Cart')]")
    public WebElement verifyAttachmentAddSuccessfullyInCartMSG;

    @FindBy(xpath = "//a[@id='cartList_Id']")
    public WebElement selectAttachmentsCartFromMenu;

    @FindBy(xpath = "//h4[contains(text(),'Attachment Cart')]")
    public WebElement verifyAttachmentCartPopupVisible;

    @FindBy(xpath = "//h4[contains(text(),'Attachment Cart')]/parent::div//button[@class='close']")
    public WebElement closeAttachmentCartPopup;

    @FindBy(xpath = "//button[@id='removeFromCart']")
    public WebElement selectRemoveCartButton;

    @FindBy(xpath = "//button[@id='downloadCart']")
    public WebElement selectDownloadCartButton;

}
