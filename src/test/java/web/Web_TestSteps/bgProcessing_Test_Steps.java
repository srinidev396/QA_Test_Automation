package web.Web_TestSteps;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.web_api_BaseClass;
import web.pageObjects.bgProcessing_PO;
import web.pageObjects.grid_Navigation_PO;

import java.io.File;
import java.time.Duration;
import java.util.ArrayList;

public class bgProcessing_Test_Steps extends web_api_BaseClass {

    WebDriver driver;

    /**
     * Class constructor define webdriver
     * @param remoteDriver = Mention webdriver */
    public bgProcessing_Test_Steps(WebDriver remoteDriver)
    {
        driver = remoteDriver;
    }

    public WebDriverWait wait;
    public bgProcessing_PO bgProcessingPo;
    public grid_Navigation_PO gridNavigationPo;
    public String backgroundExtendedDownloadedFileName = null;

    /** Verify Export acknowledgement success message */
    public void verifyExportSuccessfullyMSG()
    {
        bgProcessingPo = new bgProcessing_PO(webDriver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.visibilityOf(bgProcessingPo.verifyExportSuccessPopup));
        softAssert.assertTrue(bgProcessingPo.verifyBackgroundDataExportSuccessfulMSG.isDisplayed());
        softAssert.assertAll();
    }

    /** Get exported file name from success popup */
    public void getExportedFileNameFromPopup()
    {
        bgProcessingPo = new bgProcessing_PO(webDriver);
        backgroundExtendedDownloadedFileName = bgProcessingPo.getExportedFileNameFromBackgroundSuccessPopup.getText();
        System.out.println("File Name : " + backgroundExtendedDownloadedFileName);
        bgProcessingPo.selectClosePopupButton.click();
    }

    /** Open background status window from NavBar Toggle menu */
    public void openBackgroundStatusWindow()
    {
        gridNavigationPo = new grid_Navigation_PO(webDriver);
        gridNavigationPo.selectNavbarMenuOption("Background Status");
    }

    /** Verify Background status page is open and execute refresh
     * method till we get completed status for exported record file
     * and download from there as well as */
    public void verifyBackgroundExported() throws InterruptedException {
        bgProcessingPo = new bgProcessing_PO(webDriver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));
        wait.until(ExpectedConditions.visibilityOf(bgProcessingPo.verifyBackgroundStatusPageOpened));
        softAssert.assertTrue(bgProcessingPo.verifyBackgroundStatusPageOpened.isDisplayed());
        bgProcessingPo.verifyBackgroundExportedStatusAndDownloadRecord(backgroundExtendedDownloadedFileName);
        driver.close();
        driver.switchTo().window(tabs.get(0));
        softAssert.assertAll();
    }

    /** Tear Down Method for deleting Background Process Record File Downloaded */
    public void tearDown_Delete_Background_Process_Exported_Downloaded_File() throws InterruptedException
    {
        for (int i = 1; i < 30; i++)
        {
            Thread.sleep(2000);
            File file = new File(System.getProperty("user.home")+"\\Downloads\\"+backgroundExtendedDownloadedFileName+".txt");
            if(file.exists())
            {
                file.delete();
                break;
            }
        }
    }
}
