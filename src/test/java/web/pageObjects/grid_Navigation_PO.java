package web.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Optional;
import utilities.web_api_BaseClass;

import java.time.Duration;

public class grid_Navigation_PO extends web_api_BaseClass {

    /** This file contains all the xpaths of the Home page of the Tab FusionRMS
     * @param webDriver = initialized Webdriver
     * */
    public grid_Navigation_PO(WebDriver webDriver) {
        web_api_BaseClass.webDriver = webDriver;
        PageFactory.initElements(webDriver,this);
    }

    public WebDriverWait wait;

    @FindBy(xpath = "//label[@id='QuerylblTitle']")
    public WebElement verifyPopupWhenOpenView;

    @FindBy(xpath = "//button[@id='OkQuery']")
    public WebElement okButton;

    @FindBy(xpath = "//div[@id='QueryContentDialog']")
    public WebElement verifyQueryContentPopup;

    @FindBy(xpath = "//div[@class='navbar-header']//button")
    public WebElement selectNavbarToggleMenu;

    @FindBy(xpath = "//a[@id='hlBackgroundStatus']")
    public WebElement backgroundStatusOption;

    @FindBy(xpath = "//li[@id='hlAdmin']/a")
    public WebElement adminOption;

    @FindBy(xpath = "//li[@id='hlLabelManager']/a")
    public WebElement labelManagerOption;

    @FindBy(xpath = "//li[@id='hlImport']/a")
    public WebElement importOption;

    @FindBy(xpath = "//a[@id='lbTools']")
    public WebElement toolsOption;

    @FindBy(xpath = "//a[contains(text(),'Vault')]")
    public WebElement vaultOption;

    @FindBy(xpath = "//li[@id='hlTracking']/a")
    public WebElement trackingOption;

    @FindBy(xpath = "//a[contains(text(),'Dashboard')]")
    public WebElement dashboardOption;

    @FindBy(xpath = "//a[contains(text(),'Reports')]")
    public WebElement reportsOption;

    @FindBy(xpath = "//a[@title='Help']")
    public WebElement helpOption;

    @FindBy(xpath = "//a[@id='hlAboutUs']")
    public WebElement aboutOption;

    @FindBy(xpath = "//a[@id='hlSignout']")
    public WebElement signOutOption;

    /** Select Options From Navbar Toggle Menu
     * @param optionName = Enter Name of The Option (EX: Background Status, Admin, Label Manager,
     *                   Import, Tools, Vault, Tracking, Dashboard, Reports, Help, About, Sign Out)*/
    public void selectNavbarMenuOption(@Optional String optionName)
    {
        wait = new WebDriverWait(webDriver, Duration.ofSeconds(30));

        System.out.println("Flag Value : " + screenFlag);

        // Verify Navbar Toggle Menu Is Visible Or Not
        if (screenFlag.equals("True"))
        {
            wait.until(ExpectedConditions.visibilityOf(selectNavbarToggleMenu));
            selectNavbarToggleMenu.click();
        }
        else if (screenFlag.equals("False"))
        {
            System.out.println("Navbar Toggle Menu Not Available");
        }
        else
        {
            System.out.println("Wrong Flag You Have Get !!!");
        }

        // Select Options From Navigation Menu
        switch (optionName)
        {
            case "Background Status":
                wait.until(ExpectedConditions.visibilityOf(backgroundStatusOption));
                backgroundStatusOption.click();
                break;
            case "Admin":
                wait.until(ExpectedConditions.visibilityOf(adminOption));
                adminOption.click();
                break;
            case "Label Manager":
                wait.until(ExpectedConditions.visibilityOf(labelManagerOption));
                labelManagerOption.click();
                break;
            case "Import":
                wait.until(ExpectedConditions.visibilityOf(importOption));
                importOption.click();
                break;
            case "Tools":
                wait.until(ExpectedConditions.visibilityOf(toolsOption));
                toolsOption.click();
                break;
            case "Vault":
                wait.until(ExpectedConditions.visibilityOf(vaultOption));
                vaultOption.click();
                break;
            case "Tracking":
                wait.until(ExpectedConditions.visibilityOf(trackingOption));
                trackingOption.click();
                break;
            case "Dashboard":
                wait.until(ExpectedConditions.visibilityOf(dashboardOption));
                dashboardOption.click();
                break;
            case "Reports":
                wait.until(ExpectedConditions.visibilityOf(reportsOption));
                reportsOption.click();
                break;
            case "Help":
                wait.until(ExpectedConditions.visibilityOf(helpOption));
                helpOption.click();
                break;
            case "About":
                wait.until(ExpectedConditions.visibilityOf(aboutOption));
                aboutOption.click();
                break;
            case "Sign Out":
                wait.until(ExpectedConditions.visibilityOf(signOutOption));
                signOutOption.click();
                break;
            default:
                break;
        }
    }
}
