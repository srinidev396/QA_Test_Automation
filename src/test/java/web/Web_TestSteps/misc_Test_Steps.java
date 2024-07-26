package web.Web_TestSteps;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.web_api_BaseClass;
import web.pageObjects.grid_Navigation_PO;
import web.pageObjects.misc_PO;

import java.time.Duration;

public class misc_Test_Steps extends web_api_BaseClass {

    WebDriver driver;

    /**
     * Class constructor define webdriver
     * @param remoteDriver = Mention webdriver */
    public misc_Test_Steps(WebDriver remoteDriver) {
        driver = remoteDriver;
    }

    public WebDriverWait wait;
    public misc_PO miscPo;
    public grid_Navigation_PO gridNavigationPo;

    /** Enter News Panel URL */
    public void enterURL()
    {
        miscPo = new misc_PO(webDriver);
        miscPo.enterNewsPanelURL.sendKeys("https://tabquik.com");
        miscPo.newsPanelSaveButton.click();
    }

    /** Verify News Panel URL Added Successfully */
    public void verifyNewsPanelAddedSuccessfully()
    {
        miscPo = new misc_PO(webDriver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.visibilityOf(miscPo.verifyNewsPanelAddedSuccessfully));
        softAssert.assertTrue(miscPo.verifyNewsPanelAddedSuccessfully.isDisplayed());
        softAssert.assertAll();
    }

    /** Open About Page Form Toggle Menu */
    public void openAboutPage()
    {
        miscPo = new misc_PO(webDriver);
        gridNavigationPo = new grid_Navigation_PO(webDriver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        gridNavigationPo.selectNavbarMenuOption("About");
        wait.until(ExpectedConditions.visibilityOf(miscPo.verifyAboutPopup));
    }

    /** Verify About Page Version Information */
    public void verifyAboutPageInfo(String versionName)
    {
        miscPo = new misc_PO(webDriver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        softAssert.assertTrue(miscPo.verifyVersion.getText().contains(versionName));
        softAssert.assertAll();
    }
}
