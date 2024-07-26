package web.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.web_api_BaseClass;

import java.time.Duration;

public class bgProcessing_PO extends web_api_BaseClass {

    public WebDriverWait wait;

    /** This file contains all the xpaths of the Home page of the Tab FusionRMS
     * @param webDriver = initialized Webdriver
     * */
    public bgProcessing_PO(WebDriver webDriver) {
        web_api_BaseClass.webDriver = webDriver;
        PageFactory.initElements(webDriver,this);
    }

    String getBackgroundRecordProcessingStatus = "//table[@id='grdDrive']//tr//td[contains(text(),'%s')]/parent::tr//td[@aria-describedby='grdDrive_Status']//b";
    String getBackgroundRecordProcessingDownloadFile = "//table[@id='grdDrive']//tr//td[contains(text(),'%s')]/parent::tr//td[@aria-describedby='grdDrive_DownloadLocation']/a";
    String moveToDownloadFileColumn = "//table[@id='grdDrive']//tr//td[contains(text(),'%s')]";

    /** Verify background Export Status and download record file from there
     * @param exportedFileName = Enter Exported File Name */
    public void verifyBackgroundExportedStatusAndDownloadRecord(String exportedFileName) throws InterruptedException {
        wait = new WebDriverWait(webDriver, Duration.ofSeconds(30));
        Actions actions = new Actions(webDriver);

        wait.until(ExpectedConditions.visibilityOf(webDriver.findElement(By.xpath(String.format(getBackgroundRecordProcessingStatus,exportedFileName)))));

        for (int i = 1; i <= 30; i++)
        {
            String getStatus = webDriver.findElement(By.xpath(String.format(getBackgroundRecordProcessingStatus,exportedFileName))).getText();
            System.out.println("Status : " + getStatus);

            if(getStatus.equals("Pending") || getStatus.equals("InQue"))
            {
                Thread.sleep(10000);
                refreshBackgroundStatusTableButton.click();
                wait.until(ExpectedConditions.invisibilityOf(loadingIcon));
            }
            else if(getStatus.equals("Completed"))
            {
                wait.until(ExpectedConditions.invisibilityOf(loadingIcon));
                actions.moveToElement(webDriver.findElement(By.xpath(String.format(moveToDownloadFileColumn,exportedFileName))));
                wait.until(ExpectedConditions.visibilityOf(webDriver.findElement(By.xpath(String.format(getBackgroundRecordProcessingDownloadFile,exportedFileName)))));
                webDriver.findElement(By.xpath(String.format(getBackgroundRecordProcessingDownloadFile,exportedFileName))).click();
                break;
            }
            else
            {
                System.out.println("The Exported Data Record Not Ready To Export !!!");
                softAssert.assertTrue(getStatus.equalsIgnoreCase("Completed"));
            }
        }
    }

    @FindBy(xpath = "//label[@id='dlgBsTitle']")
    public WebElement verifyExportSuccessPopup;

    @FindBy(xpath = "//div[@id='dlgBsContent']//div[@id='dlgbodyId']//p[contains(text(),'Your Export added successfully in background.')]")
    public WebElement verifyBackgroundDataExportSuccessfulMSG;

    @FindBy(xpath = "//div[@id='dlgBsContent']//div[@id='dlgbodyId']//p[2]/b")
    public WebElement getExportedFileNameFromBackgroundSuccessPopup;

    @FindBy(xpath = "//button[@id='closedlg']")
    public WebElement selectClosePopupButton;

    @FindBy(xpath = "//span[@id='title']")
    public WebElement verifyBackgroundStatusPageOpened;

    @FindBy(xpath = "//div[@id='load_grdDrive' and contains(@style,'display: block;')]")
    public WebElement loadingIcon;

    @FindBy(xpath = "//td[@id='refresh_grdDrive']")
    public WebElement refreshBackgroundStatusTableButton;
}
