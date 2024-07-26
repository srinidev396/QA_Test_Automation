package web.Web_TestSteps;

import utilities.web_api_BaseClass;
import web.pageObjects.grid_Navigation_PO;
import web.pageObjects.login_PO;
import org.apache.hc.client5.http.utils.Base64;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.time.Duration;

/**
 * In this class mentioned all the steps method of login test
 * either is pass, fail, validation, verification
 * */
public class login_Test_Steps extends web_api_BaseClass {

    WebDriver driver;

    /**
     * Class constructor define webdriver
     * @param remoteDriver = Mention webdriver */
    public login_Test_Steps(WebDriver remoteDriver) {
        driver = remoteDriver;
    }

    public WebDriverWait wait;
    public login_PO loginPo;
    public grid_Navigation_PO gridNavigationPo;

    /** In this method opening login page */
    public void openLoginPage()
    {
        loginPo = new login_PO(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        wait.until(ExpectedConditions.visibilityOf(loginPo.verifyLoginPage));

        try
        {
            if (loginPo.verifySignInMessagePopup.isDisplayed())
            {
                wait.until(ExpectedConditions.visibilityOf(loginPo.signInMessagePopupOKButton));
                softAssert.assertTrue(loginPo.verifySignInMessagePopup.isDisplayed());
                loginPo.signInMessagePopupOKButton.click();
                wait.until(ExpectedConditions.visibilityOf(loginPo.enterUsername));
            }
        }
        catch (Exception e)
        {
            System.out.println(" ");
        }

        softAssert.assertTrue(loginPo.enterUsername.isDisplayed());
        softAssert.assertAll();
    }

    /** In this section verify database filed is textbox or dropdown
     * and according to given parameter they execute
     * @param databaseName = Give database name same in dropdown or TextBox */
    public void selectDatabase(String databaseName) {
        loginPo = new login_PO(driver);

        try
        {
            if (loginPo.selectDatabase.isDisplayed())
            {
                softAssert.assertTrue(loginPo.selectDatabase.isDisplayed());
                Select drpDatabase = new Select(loginPo.selectDatabase);
                drpDatabase.selectByVisibleText(databaseName);
                softAssert.assertEquals(loginPo.verifyDatabaseSelected.getText(),databaseName);
            }
        }
        catch (Exception e)
        {
            System.out.println(" ");
        }

        try
        {
            if (loginPo.enterDatabase.isDisplayed())
            {
                softAssert.assertTrue(loginPo.enterDatabase.isDisplayed());
                loginPo.enterDatabase.click();
                loginPo.enterDatabase.sendKeys(databaseName);
                softAssert.assertEquals(loginPo.enterDatabase.getAttribute("value"),databaseName);
            }
        }
        catch (Exception e)
        {
            System.out.println(" ");
        }

        softAssert.assertAll();
    }

    /** In this section mention username and password of the user
     * @param userName = Enter username according to the test
     * @param password = Enter password according to the test */
    public void enterUsernamePassword(String userName,String password) throws IOException {
        loginPo = new login_PO(driver);

        loginPo.enterUsername.click();
        loginPo.enterUsername.sendKeys(userName);

        byte[] decodedString = Base64.decodeBase64(password);
        loginPo.enterPassword.sendKeys(new String(decodedString));
    }

    /** In this section mention click on login button*/
    public void signInButton()
    {
        loginPo = new login_PO(driver);
        loginPo.signInButton.click();
    }

    /** In this section mention after login with
     * the login credential verify Home page of the system */
    public void verifyHomePage()
    {
        loginPo = new login_PO(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.visibilityOf(loginPo.verifyHomePage));
        softAssert.assertTrue(loginPo.verifyHomePage.isDisplayed());
        softAssert.assertAll();
    }

    /** Verify invalid user verification message on login page */
    public void verifyInvalidUser()
    {
        loginPo = new login_PO(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.visibilityOf(loginPo.verifyInvalidUser));
        softAssert.assertTrue(loginPo.verifyInvalidUser.isDisplayed());
        softAssert.assertAll();
    }

    /** Verify User Login Successfully */
    public void verifyUserLoginSuccess()
    {
        loginPo = new login_PO(driver);
        gridNavigationPo = new grid_Navigation_PO(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        try
        {
            gridNavigationPo.selectNavbarMenuOption("");
            for (int i = 1; i < 15; i++)
            {
                Thread.sleep(2000);
                if (gridNavigationPo.signOutOption.isDisplayed())
                {
                    wait.until(ExpectedConditions.visibilityOf(gridNavigationPo.signOutOption));
                    softAssert.assertTrue(gridNavigationPo.signOutOption.isDisplayed());
                    break;
                }
            }
            gridNavigationPo.selectNavbarMenuOption("");

            wait.until(ExpectedConditions.visibilityOf(loginPo.verifyAutomationWG));
            softAssert.assertTrue(loginPo.verifyAutomationWG.isDisplayed());
            softAssert.assertAll();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
