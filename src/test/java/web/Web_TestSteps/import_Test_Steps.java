package web.Web_TestSteps;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Optional;
import utilities.web_api_BaseClass;
import web.pageObjects.grid_Navigation_PO;
import web.pageObjects.grid_Table_PO;
import web.pageObjects.import_PO;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class import_Test_Steps extends web_api_BaseClass {

    WebDriver driver;

    /**
     * Class constructor define webdriver
     * @param remoteDriver = Mention webdriver */
    public import_Test_Steps(WebDriver remoteDriver)
    {
        driver = remoteDriver;
    }

    public WebDriverWait wait;
    public import_PO importPo;
    public grid_Navigation_PO gridNavigationPo;
    public grid_Table_PO gridTablePo;

    /** Demo Import */
    public void selectImportFromMenu() throws IOException {
        importPo = new import_PO(webDriver);
        gridNavigationPo = new grid_Navigation_PO(webDriver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        gridNavigationPo.selectNavbarMenuOption("Import");

        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));
        wait.until(ExpectedConditions.visibilityOf(importPo.selectSavedImportFileFromDropDown));
        Select drpDestination = new Select(importPo.selectSavedImportFileFromDropDown);
        drpDestination.selectByVisibleText("DepartmentTestImport");
        importPo.selectRunButton.click();
        String filePath = System.getProperty("user.dir")+"\\Framework_Tools\\test_ImportDataFile\\ImportTestData.txt";
        System.out.println(filePath);
        ProcessBuilder processBuilder = new ProcessBuilder(System.getProperty("user.dir")+"\\framework_Tools\\uploadFile_AutoIT_Script_EXE\\uploadFile.exe",filePath);
        processBuilder.start();
        wait.until(ExpectedConditions.visibilityOf(importPo.verifyImportSuccessfullyMSG));
        softAssert.assertTrue(importPo.verifyImportSuccessfullyMSG.isDisplayed());
        wait.until(ExpectedConditions.invisibilityOf(importPo.verifyImportSuccessfullyMSG));
        softAssert.assertAll();
    }

    /** Get List Of Department Name from Grid */
    public void verifyImportedDataIntoGrid()
    {
        importPo = new import_PO(webDriver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        Actions actions = new Actions(webDriver);

        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(0));

        importPo.refreshGridButton.click();
        wait.until(ExpectedConditions.invisibilityOf(importPo.gridRefreshLoadingIcon));
//        actions.doubleClick(importPo.descendingOrderDepartID).perform();

        List<String> verifyImportedData = new ArrayList<>()
        {{add("IT_Imported");add("HR_Imported");
        }};
        ArrayList<String> departmentNameList = new ArrayList<String>();

        for (WebElement element : importPo.departmentNameList)
        {
            actions.scrollToElement(element).perform();
            departmentNameList.add(element.getText());
        }
        System.out.println(departmentNameList);
        softAssert.assertTrue(departmentNameList.containsAll(verifyImportedData));
        softAssert.assertAll();
    }

    /** Select and Delete imported data from grid table */
    public void selectAndDeleteImportedDataFromGrid(String enterDataName)
    {
        importPo = new import_PO(webDriver);
        gridTablePo = new grid_Table_PO(webDriver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(0));

        importPo.refreshGridButton.click();
        wait.until(ExpectedConditions.invisibilityOf(importPo.gridRefreshLoadingIcon));

        importPo.selectParticularRowData(enterDataName);
        gridTablePo.selectPaperPlaneIcon.click();
        importPo.selectDeleteOptionFromPaperPlane.click();
        wait.until(ExpectedConditions.visibilityOf(gridTablePo.verifyDeleteItemPopup));
        importPo.yesDeleteButton.click();
        wait.until(ExpectedConditions.visibilityOf(importPo.verifyRecordDeletedSuccessfullyMSG));
        softAssert.assertTrue(importPo.verifyRecordDeletedSuccessfullyMSG.isDisplayed());
        wait.until(ExpectedConditions.invisibilityOf(importPo.verifyRecordDeletedSuccessfullyMSG));
        softAssert.assertAll();
    }


    /** Verify And Open Vault Page */
    public void openImportFromMenu()
    {
        importPo = new import_PO(webDriver);
        gridNavigationPo = new grid_Navigation_PO(webDriver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        gridNavigationPo.selectNavbarMenuOption("Import");

        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));

        wait.until(ExpectedConditions.visibilityOf(importPo.selectSavedImportFileFromDropDown));
        softAssert.assertTrue(importPo.selectSavedImportFileFromDropDown.isDisplayed());
        softAssert.assertAll();
    }

    /** Create New Import Configuration Enter File Name
     * @param enterFileName = Enter File Name */
    public void createNewImport(String enterFileName) throws IOException {
        importPo = new import_PO(webDriver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        importPo.newImportButton.click();
        String filePath = System.getProperty("user.dir")+"\\framework_Tools\\test_ImportDataFile\\"+enterFileName;
        System.out.println(filePath);
        ProcessBuilder processBuilder = new ProcessBuilder(System.getProperty("user.dir")+"\\framework_Tools\\uploadFile_AutoIT_Script_EXE\\uploadFile.exe",filePath);
        processBuilder.start();
        wait.until(ExpectedConditions.visibilityOf(importPo.enterImportName));
        softAssert.assertTrue(importPo.enterImportName.isDisplayed());
        softAssert.assertAll();
    }

    /** Enter Import Name And Select Checkbox For First Row Contains Header
     * @param enterImportName = Enter Import Name
     * @param enterFirstRowCheckboxFlagValue = Select First Row Checkbox Flag And It is Optional */
    public void firstStageOfImportFileConfiguration(String enterImportName, @Optional String enterFirstRowCheckboxFlagValue) throws InterruptedException {
        importPo = new import_PO(webDriver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        Actions actions = new Actions(webDriver);

        softAssert.assertTrue(importPo.verifyFirstStageOfImport.getCssValue("color").contains("rgba(0, 161, 225, 1)"));
        importPo.enterImportName.sendKeys(enterImportName);
        if (enterFirstRowCheckboxFlagValue.equalsIgnoreCase("Yes"))
        {
            importPo.selectFirstRowContainsFieldNamesCheckbox.click();
        }
        for(int i = 0; i <= 10; i++)
        {
            Thread.sleep(3000);
            if (!importPo.selectDestinationDropDown.isDisplayed())
            {
                actions.moveToElement(importPo.nextButton).perform();
                actions.click(importPo.nextButton).perform();
                break;
            }
        }
        wait.until(ExpectedConditions.visibilityOf(importPo.selectDestinationDropDown));
        softAssert.assertTrue(importPo.selectDestinationDropDown.isDisplayed());
        softAssert.assertAll();
    }

    /** Select Destination of Table Where You Have To Add Imported Data
     * @param selectDestinationTable = Select Destination Table */
    public void secondStageOfImportSelectDestinationTable(String selectDestinationTable) {
        importPo = new import_PO(webDriver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        softAssert.assertTrue(importPo.verifySecondStageOfImport.getCssValue("color").contains("rgba(0, 161, 225, 1)"));
        Select drpDestination = new Select(importPo.selectDestinationDropDown);
        drpDestination.selectByVisibleText(selectDestinationTable);
        wait.until(ExpectedConditions.visibilityOf(importPo.waitForSelectedTableAvailableField));
        softAssert.assertAll();
    }

    /** Select And Add Available Fields Of The Selected Table */
    public void secondStageOfImportSelectAndAddAvailableFields() throws InterruptedException
    {
        importPo = new import_PO(webDriver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        Actions actions = new Actions(webDriver);
        ArrayList<String> availableFieldList = new ArrayList<String>();

        Select drpSelectAvailableFields = new Select(importPo.selectAvailableFields);
        List<WebElement> allOptions = drpSelectAvailableFields.getOptions();

        for (WebElement e : allOptions)
        {
            availableFieldList.add(e.getText());
        }
        System.out.println(availableFieldList);

        for (String s : availableFieldList)
        {
            if (s.equals("<<SKIP FIELD>>"))
            {
                break;
            }
            else
            {
                drpSelectAvailableFields.selectByVisibleText(s);
                importPo.moveSelectedArrowInAvailableFields.click();
                if (importPo.nextButton.isDisplayed())
                {
                    Thread.sleep(2000);
                }
            }
        }
        actions.click(importPo.nextButton).perform();
    }

    /** Select Imported Table Privileges Of The Table */
    public void thirdStageOfImportDataPrivilegeOfTable()
    {
        importPo = new import_PO(webDriver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        softAssert.assertTrue(importPo.verifyThirdStageOfImport.getCssValue("color").contains("rgba(0, 161, 225, 1)"));

        Select drpOverwriteAdd = new Select(importPo.selectOverWriteOrAddDropDown);
        drpOverwriteAdd.selectByVisibleText("Add New Records Only");

        Select drpImportBy = new Select(importPo.selectImportByDropDown);
        drpImportBy.selectByVisibleText("(None) - for Adding New Records Only");

        importPo.finishImportButton.click();
        wait.until(ExpectedConditions.visibilityOf(importPo.importFileConfigurationSuccessMSG));
        softAssert.assertTrue(importPo.importFileConfigurationSuccessMSG.isDisplayed());
        wait.until(ExpectedConditions.invisibilityOf(importPo.importFileConfigurationSuccessMSG));
        softAssert.assertAll();
    }

    /** Select And Run New Created Imported File Configuration Data
     * @param enterImportName = Enter Import Name And Select From DropDown
     * @param enterFileName = Enter File Name*/
    public void selectAndRunNewImportedFileConfiguration(String enterImportName, String enterFileName) throws IOException
    {
        importPo = new import_PO(webDriver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        wait.until(ExpectedConditions.visibilityOf(importPo.selectSavedImports));
        Select drpSavedImports = new Select(importPo.selectSavedImports);
        drpSavedImports.selectByVisibleText(enterImportName);
        importPo.runImportButton.click();
        String filePath = System.getProperty("user.dir")+"\\framework_Tools\\test_ImportDataFile\\"+enterFileName;
        System.out.println(filePath);
        ProcessBuilder processBuilder = new ProcessBuilder(System.getProperty("user.dir")+"\\framework_Tools\\uploadFile_AutoIT_Script_EXE\\uploadFile.exe",filePath);
        processBuilder.start();
        wait.until(ExpectedConditions.visibilityOf(importPo.verifySelectedImportCompleteSuccessMSG));
        softAssert.assertTrue(importPo.verifySelectedImportCompleteSuccessMSG.isDisplayed());
        wait.until(ExpectedConditions.invisibilityOf(importPo.verifySelectedImportCompleteSuccessMSG));
        softAssert.assertAll();
    }

    /** Go Back To Grid For Verify Imported Data */
    public void goBackToGridForVerifyImportedData()
    {
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(0));
    }

    /** Sort In Descending Order Record Of Grid */
    public void sortInDescendingRecordGrid()
    {
        importPo = new import_PO(webDriver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        Actions actions = new Actions(webDriver);

        actions.moveToElement(importPo.sortSuppliersIdColumn).perform();
        actions.click(importPo.sortSuppliersIdColumn).perform();
        actions.click(importPo.sortSuppliersIdColumn).perform();
    }

    /** Verify New Imported Data Into Grid */
    public void verifyNewImportedDataIntoGrid()
    {
        importPo = new import_PO(webDriver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        List<String> verifyImportedData = new ArrayList<>()
        {{add("Import New TestData1");add("Import New TestData2");
        }};

        ArrayList<String> supplierNameList = new ArrayList<String>();

        for (WebElement element : importPo.supplierNameList)
        {
            supplierNameList.add(element.getText());
        }
        System.out.println(supplierNameList);
        softAssert.assertTrue(supplierNameList.containsAll(verifyImportedData));
        softAssert.assertAll();
    }

    /** Select And Delete Record From Grid
     * @param enterSupplierName = Enter Supplier Name*/
    public void selectAndDeleteRecordFromGrid(String enterSupplierName)
    {
        importPo = new import_PO(webDriver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        importPo.selectRecordFromGrid(enterSupplierName);
        importPo.selectPaperPlaneIcon.click();
        importPo.selectDeleteRecord.click();
        wait.until(ExpectedConditions.visibilityOf(importPo.verifyDeleteRecordPopup));
        importPo.yesDeleteRecordButton.click();
        wait.until(ExpectedConditions.visibilityOf(importPo.verifyRecordDeletedSuccessfullyMSG));
        softAssert.assertTrue(importPo.verifyRecordDeletedSuccessfullyMSG.isDisplayed());
        wait.until(ExpectedConditions.invisibilityOf(importPo.verifyRecordDeletedSuccessfullyMSG));
        softAssert.assertAll();
    }

    /** Go Back To Import Page */
    public void goBackToImportPage()
    {
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));
    }

    /** Select And Remove New Imported
     * @param enterImportName = Enter Import Name */
    public void selectAndRemoveNewImported(String enterImportName)
    {
        importPo = new import_PO(webDriver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        Select drpSavedImports = new Select(importPo.selectSavedImports);
        drpSavedImports.selectByVisibleText(enterImportName);

        importPo.removeImportButton.click();
        wait.until(ExpectedConditions.visibilityOf(importPo.verifyDeleteImportPopup));
        importPo.yesDeleteImportButton.click();
        wait.until(ExpectedConditions.visibilityOf(importPo.verifyImportedDataFileConfigurationRemoveSuccessMSG));
        softAssert.assertTrue(importPo.verifyImportedDataFileConfigurationRemoveSuccessMSG.isDisplayed());
        wait.until(ExpectedConditions.invisibilityOf(importPo.verifyImportedDataFileConfigurationRemoveSuccessMSG));
        softAssert.assertAll();
    }
}
