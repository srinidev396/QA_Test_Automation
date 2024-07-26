package web.pageObjects;

import utilities.web_api_BaseClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class login_PO extends web_api_BaseClass {

    /** This file contains all the xpaths of the Home page of the Tab FusionRMS
     * @param webDriver = initialized Webdriver
     * */
    public login_PO(WebDriver webDriver)
    {
        web_api_BaseClass.webDriver = webDriver;
        PageFactory.initElements(webDriver,this);
    }

    @FindBy(xpath = "//div[@class='col-xs-12 col-md-6 login-text']")
    public WebElement verifyLoginPage;

    @FindBy(xpath = "//div[@id='dvWarningMessage']")
    public WebElement verifySignInMessagePopup;

    @FindBy(xpath = "//input[@id='ContentPlaceHolder1_btnSignIn']")
    public WebElement signInMessagePopupOKButton;

    @FindBy(xpath = "//select[@id='ContentPlaceHolder1_ddlDatabase']")
    public WebElement selectDatabase;

    @FindBy(xpath = "//input[@id='ContentPlaceHolder1_txtCustomerKey']")
    public WebElement enterDatabase;

    @FindBy(xpath = "//option[@selected='selected']")
    public WebElement verifyDatabaseSelected;

    @FindBy(xpath = "//input[@id='ContentPlaceHolder1_txtUserName']")
    public WebElement enterUsername;

    @FindBy(xpath = "//input[@id='ContentPlaceHolder1_txtPassword']")
    public WebElement enterPassword;

    @FindBy(xpath = "//input[@id='ContentPlaceHolder1_btnSignIn']")
    public WebElement signInButton;

    @FindBy(xpath = "//img[@alt='Tab Fusion']")
    public WebElement verifyHomePage;

    @FindBy(xpath = "//span[contains(text(),'The user name and/or password are invalid')]")
    public WebElement verifyInvalidUser;

    @FindBy(xpath = "//li[@class='hasSubs']//a[starts-with(text(),'AutomationWG')]")
    public WebElement verifyAutomationWG;
}
