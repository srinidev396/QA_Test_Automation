package web.pageObjects;

import utilities.web_api_BaseClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class misc_PO extends web_api_BaseClass {

    /** This file contains all the xpaths of the Home page of the Tab FusionRMS
     * @param webDriver = initialized Webdriver
     * */
    public misc_PO(WebDriver webDriver) {
        web_api_BaseClass.webDriver = webDriver;
        PageFactory.initElements(webDriver,this);
    }

    @FindBy(xpath = "//input[@id='txtNewsURL']")
    public WebElement enterNewsPanelURL;

    @FindBy(xpath = "//input[@id='btnSaveNewsURL']")
    public WebElement newsPanelSaveButton;

    @FindBy(xpath = "//iframe[@id='NewsFrame']")
    public WebElement verifyNewsPanelAddedSuccessfully;

    @FindBy(xpath = "//h4[@id='title_AboutUs']")
    public WebElement verifyAboutPopup;

    @FindBy(xpath = "//span[@class='about-para']")
    public WebElement verifyVersion;
}
