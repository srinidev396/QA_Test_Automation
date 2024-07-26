package web.Web_TestSteps;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.web_api_BaseClass;
import web.pageObjects.grid_Navigation_PO;
import web.pageObjects.grid_Table_PO;

import java.io.File;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class grid_Test_Steps extends web_api_BaseClass {

    WebDriver driver;

    /**
     * Class constructor define webdriver
     * @param remoteDriver = Mention webdriver */
    public grid_Test_Steps(WebDriver remoteDriver) {
        driver = remoteDriver;
    }

    public WebDriverWait wait;
    public grid_Navigation_PO gridNavigationPo;
    public grid_Table_PO gridTablePo;

    /** In this method Select any table from the navigation just give table name
     * @param navigationOptionName = Enter Table name, View Name, Query Name,
     *                             Container Name, Retation Name, Reports, Tracking */
    public void selectNavigationOption(String navigationOptionName) throws InterruptedException {
        gridTablePo = new grid_Table_PO(webDriver);
        Actions actions = new Actions(webDriver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        for (int i = 1; i < 20; i++)
        {
            Thread.sleep(2000);
            if (webDriver.findElement(By.xpath(gridTablePo.getXpath(gridTablePo.selectNavigationOption,navigationOptionName))).isDisplayed())
            {
                wait.until(ExpectedConditions.visibilityOf(webDriver.findElement(By.xpath(gridTablePo.getXpath(gridTablePo.selectNavigationOption,navigationOptionName)))));
                webDriver.findElement(By.xpath(gridTablePo.getXpath(gridTablePo.selectNavigationOption,navigationOptionName))).click();
                break;
            }
            else
            {
                if (!webDriver.findElement(By.xpath(gridTablePo.getXpath(gridTablePo.selectNavigationOption,navigationOptionName))).isDisplayed())
                {
                    if (gridTablePo.scrollBar.isDisplayed())
                    {
                        wait.until(ExpectedConditions.visibilityOf(gridTablePo.scrollBar));
                        actions.scrollToElement(webDriver.findElement(By.xpath(gridTablePo.getXpath(gridTablePo.selectNavigationOption,navigationOptionName)))).perform();
                        actions.moveToElement(webDriver.findElement(By.xpath(gridTablePo.getXpath(gridTablePo.selectNavigationOption,navigationOptionName)))).perform();
                        wait.until(ExpectedConditions.visibilityOf(webDriver.findElement(By.xpath(gridTablePo.getXpath(gridTablePo.selectNavigationOption,navigationOptionName)))));
                        actions.click(webDriver.findElement(By.xpath(gridTablePo.getXpath(gridTablePo.selectNavigationOption,navigationOptionName)))).perform();
                        break;
                    }
                }
            }
        }
    }

    /**
     * Verify Query popup is visible when click on view of record */
    public void verifyQueryPopupAndPressOk()
    {
        gridNavigationPo = new grid_Navigation_PO(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.visibilityOf(gridNavigationPo.verifyPopupWhenOpenView));
        softAssert.assertTrue(gridNavigationPo.verifyPopupWhenOpenView.isDisplayed());
        gridNavigationPo.okButton.click();
        softAssert.assertAll();
    }

    /**
     * Verify Selected view record table is displayed */
    public void verifySelectedViewIsOpened(String tableViewName)
    {
        gridTablePo = new grid_Table_PO(webDriver);
        gridTablePo.verifySelectedViewDisplayed(tableViewName);
    }

    /** Create new record for Document view */
    public void createNewDocumentRecord()
    {
        gridTablePo = new grid_Table_PO(webDriver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        gridTablePo.selectNewButton.click();
        wait.until(ExpectedConditions.visibilityOf(gridTablePo.verifyAddNewRecordPopup));
        softAssert.assertTrue(gridTablePo.verifyAddNewRecordPopup.isDisplayed());
        gridTablePo.enterDepartmentName.sendKeys("Quality Test");
        gridTablePo.saveButton.click();
        softAssert.assertAll();
    }

    /** Verify record added successful acknowledgement message */
    public void verifyRecordAddedSuccessfullyAcknowledgementMessage()
    {
        gridTablePo = new grid_Table_PO(webDriver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOf(gridTablePo.verifyRecordAddedSuccessfullyMSG));
        softAssert.assertTrue(gridTablePo.verifyRecordAddedSuccessfullyMSG.isDisplayed());
        wait.until(ExpectedConditions.invisibilityOf(gridTablePo.verifyToastMSGInvisible));
        softAssert.assertAll();
    }

    /** Verify new record added is existing in table or not */
    public void verifyNewDocumentRecord()
    {
        gridTablePo = new grid_Table_PO(webDriver);
        softAssert.assertEquals(gridTablePo.getVerifyAddedRecordFromTable.getText(),"Quality Test");
        softAssert.assertAll();
    }

    /** Edit new added record from table */
    public void editCreatedDocumentRecord()
    {
        gridTablePo = new grid_Table_PO(webDriver);
        Actions actions = new Actions(webDriver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        actions.doubleClick(gridTablePo.getVerifyAddedRecordFromTable).perform();
        gridTablePo.editDataRecord.sendKeys(" Edit");
        gridTablePo.saveEditButton.click();
    }

    /** Verify record edit/updated successful acknowledgement message */
    public void verifyRecordUpdatedSuccessfullyAcknowledgementMessage()
    {
        gridTablePo = new grid_Table_PO(webDriver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOf(gridTablePo.verifyRecordUpdatedSuccessfullyMSG));
        softAssert.assertTrue(gridTablePo.verifyRecordUpdatedSuccessfullyMSG.isDisplayed());
        wait.until(ExpectedConditions.invisibilityOf(gridTablePo.verifyToastMSGInvisible));
        softAssert.assertAll();
    }

    /** Verify edited record is updated in table or not */
    public void verifyEditedDocumentRecord()
    {
        gridTablePo = new grid_Table_PO(webDriver);
        softAssert.assertEquals(gridTablePo.getVerifyAddedRecordFromTable.getText(),"Quality Test Edit");
        softAssert.assertAll();
    }

    /** Delete recent created data record */
    public void deleteRecentCreatedDataRecord()
    {
        gridTablePo = new grid_Table_PO(webDriver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        gridTablePo.selectPaperPlaneIcon.click();
        gridTablePo.selectDeleteRecord.click();
        wait.until(ExpectedConditions.visibilityOf(gridTablePo.verifyDeleteItemPopup));
        softAssert.assertTrue(gridTablePo.verifyDeleteItemPopup.isDisplayed());
        gridTablePo.yesDeleteButton.click();
        softAssert.assertAll();
    }

    /** Verify record Deleted successful acknowledgement message */
    public void verifyRecordDeletedSuccessfullyAcknowledgementMessage()
    {
        gridTablePo = new grid_Table_PO(webDriver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOf(gridTablePo.verifyRecordDeletedSuccessfullyMSG));
        softAssert.assertTrue(gridTablePo.verifyRecordDeletedSuccessfullyMSG.isDisplayed());
        wait.until(ExpectedConditions.invisibilityOf(gridTablePo.verifyToastMSGInvisible));
        softAssert.assertAll();
    }

    /** Select which file extension you have to export the selected Record
     * @param exportFileExtensionName = Enter file extension type EX: txt, csv*/
    public void selectExportSelected(String exportFileExtensionName)
    {
        gridTablePo = new grid_Table_PO(webDriver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        gridTablePo.selectNotePaperIcon.click();
        if (exportFileExtensionName.equalsIgnoreCase("txt"))
        {
            gridTablePo.selectExportSelectedTXT.click();
            wait.until(ExpectedConditions.visibilityOf(gridTablePo.verifyExportTXTRecordPopup));
            softAssert.assertTrue(gridTablePo.verifyExportTXTRecordPopup.isDisplayed());
        }
        else if (exportFileExtensionName.equalsIgnoreCase("csv"))
        {
            gridTablePo.selectExportSelectedCSV.click();
            wait.until(ExpectedConditions.visibilityOf(gridTablePo.verifyExportCSVRecordPopup));
            softAssert.assertTrue(gridTablePo.verifyExportCSVRecordPopup.isDisplayed());
        }
        else
        {
            System.out.println("You have selected wrong file exportation file !!!");
        }
        gridTablePo.yesExportSelectedButton.click();
        softAssert.assertAll();
    }

    /** Get file location from download folder and verify file size
     * and verify file should nor empty
     * @param fileName = select file extension and get size of file EX: txt, csv */
    public void verifyFileSize(String fileName) throws IOException, InterruptedException
    {
        String getTableName = webDriver.findElement(By.xpath("//span[@class='drilldown_Parent']")).getText().replaceAll("[^a-zA-Z]","");

        for (int i = 1; i < 10; i++)
        {
            Thread.sleep(2000);
            if (fileName.equalsIgnoreCase("txt"))
            {
                Path filetxt = Paths.get(System.getProperty("user.home")+"\\Downloads\\"+getTableName+".txt");
                FileChannel fileChannelTXT = FileChannel.open(filetxt);
                softAssert.assertTrue(fileChannelTXT.size() > 21);
                fileChannelTXT.close();
                break;
            }
            else if (fileName.equalsIgnoreCase("csv"))
            {
                Path filecsv = Paths.get(System.getProperty("user.home")+"\\Downloads\\"+getTableName+".csv");
                FileChannel fileChannelCSV = FileChannel.open(filecsv);
                softAssert.assertTrue(fileChannelCSV.size() > 23);
                fileChannelCSV.close();
                break;
            }
            else
            {
                System.out.println("You have selected wrong file extension !!!");
            }
        }
        softAssert.assertAll();
    }

    /** This is tear down method after completed the Export Selected test file get deleted
     * @param fileName = Enter name of the file with the extension EX: Department.txt*/
    public void tearDown_Export_Selected_TXT_CSV(String fileName)
    {
        File file = new File(System.getProperty("user.home")+"\\Downloads\\"+fileName);
        if (file.exists())
        {
            file.delete();
        }
    }

    /** Select which file extension you have to export the selected Record
     * @param exportFileExtensionName = Enter file extension type EX: txt, csv*/
    public void selectExportAll(String exportFileExtensionName)
    {
        gridTablePo = new grid_Table_PO(webDriver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        gridTablePo.selectNotePaperIcon.click();
        if (exportFileExtensionName.equalsIgnoreCase("txt"))
        {
            gridTablePo.selectExportAllTXT.click();
            wait.until(ExpectedConditions.visibilityOf(gridTablePo.verifyExportTXTRecordPopup));
            softAssert.assertTrue(gridTablePo.verifyExportTXTRecordPopup.isDisplayed());
        }
        else if (exportFileExtensionName.equalsIgnoreCase("csv"))
        {
            gridTablePo.selectExportAllCSV.click();
            wait.until(ExpectedConditions.visibilityOf(gridTablePo.verifyExportCSVRecordPopup));
            softAssert.assertTrue(gridTablePo.verifyExportCSVRecordPopup.isDisplayed());
        }
        else
        {
            System.out.println("You have selected wrong file exportation file !!!");
        }
        gridTablePo.yesExportAllButton.click();
        softAssert.assertAll();
    }

    /** Select Black and White option from the options */
    public void selectBlackAndWhite() throws InterruptedException {
        gridTablePo = new grid_Table_PO(webDriver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        gridTablePo.selectNotePaperIcon.click();
        gridTablePo.selectBlackAndWhite.click();
        wait.until(ExpectedConditions.visibilityOf(gridTablePo.verifyBlackAndWhitePopup));
        softAssert.assertTrue(gridTablePo.verifyBlackAndWhitePopup.isDisplayed());
        gridTablePo.downloadBlackAndWhiteButton.click();
        softAssert.assertAll();
    }

    /** Get the latest downloaded file from Downloads folder specially use for
     * Encoded file name downloaded */
    public void tearDown_Delete_Latest_Downloaded_File_Location() throws InterruptedException
    {
        String directory = System.getProperty("user.home")+"\\Downloads";
        File folder = new File(directory);

        for (int i = 1; i < 10; i++)
        {
            Thread.sleep(5000);
            File[] files = folder.listFiles();
            if (files == null || files.length == 0)
            {
                System.out.println("No files found in the folder");
                return;
            }

            // Sort files by last modified date in descending order
            Arrays.sort(files, Comparator.comparingLong(File::lastModified).reversed());

            File latestFile = files[0];

            if (latestFile.delete())
            {
                System.out.println("Deleted latest file: " + latestFile.getName());
                break;
            }
            else
            {
                System.out.println("Failed to delete the latest file");
            }
        }
    }

    /** Select TabQuik option from the options */
    public void selectTabquik()
    {
        gridTablePo = new grid_Table_PO(webDriver);
        gridTablePo.selectNotePaperIcon.click();
        gridTablePo.selectTabquik.click();
    }

    /** Verify TabQuik window is open */
    public void verifyTabquikWindowOpened()
    {
        gridTablePo = new grid_Table_PO(webDriver);
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));
        softAssert.assertTrue(webDriver.getCurrentUrl().contains("TabQuik"));
        driver.close();
        driver.switchTo().window(tabs.get(0));
        softAssert.assertAll();
    }

    /** Verify created query condition value displayed in table */
    public void verifyAccordingMyQueryConditionRecordVisible()
    {
        gridTablePo = new grid_Table_PO(webDriver);
        List<WebElement> elementList = gridTablePo.getDeptIdList;

        for (WebElement e : elementList)
        {
            softAssert.assertTrue(Integer.parseInt(e.getText()) > 4);
        }
        softAssert.assertAll();
    }

    /** Verify created query condition value displayed in table */
    public void verifyAccordingMyFavoriteConditionRecordVisible()
    {
        gridTablePo = new grid_Table_PO(webDriver);

        List<WebElement> elementList = gridTablePo.getDeptNameList;
        List<String> expectedList = new ArrayList<>()
        {{add("Sales");add("Purchase");add("Legal");
        }};
        List<String> finalList = new ArrayList<>();
        for (WebElement e : elementList)
        {
            finalList.add(e.getText());
        }
        softAssert.assertEquals(finalList,expectedList);
        softAssert.assertAll();
    }
}
