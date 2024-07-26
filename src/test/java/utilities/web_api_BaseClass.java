package utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.aventstack.extentreports.reporter.configuration.ViewName;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.maven.shared.utils.io.FileUtils;
import org.codehaus.plexus.util.IOUtil;
import org.json.simple.JSONObject;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import java.awt.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

/** In this class define the base of the Web Tests and Api Tests */
public class web_api_BaseClass {

    public static String screenFlag;
    public static ExtentSparkReporter htmlReporter;
    public static ExtentReports reports;
    public static ExtentTest test;
    public static ThreadLocal<String> testName = new ThreadLocal<>();
    public static WebDriver webDriver;
    public static FileInputStream fileInputStream;
    public static Properties prop = new Properties();
    public SoftAssert softAssert = new SoftAssert();

    public static String URL;
    public static RequestSpecification httpRequest;
    public static JSONObject requestParams;
    public static Response response;
    public static String api_token;

    /** In this section mentioned Extent Report */
    @Parameters({"prod"})
    @BeforeTest(groups = {"setUp"})
    public void extentReport(@Optional String prod)
    {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
        Date date = new Date();
        htmlReporter = new ExtentSparkReporter("./Test_Report/Tab_FusionRMS_Report_"+formatter.format(date)+".html")
                .viewConfigurer()
                .viewOrder()
                .as(new ViewName[] { ViewName.DASHBOARD, ViewName.TEST, ViewName.CATEGORY, ViewName.AUTHOR,
                ViewName.DEVICE, ViewName.EXCEPTION, ViewName.LOG}).apply();
        reports = new ExtentReports();
        reports.attachReporter(htmlReporter);

        reports.setSystemInfo("User","Automation Test");

        if(prod.equalsIgnoreCase("api"))
        {
            reports.setSystemInfo("Platform","API");
        }
        else
        {
            reports.setSystemInfo("Platform",prod.toUpperCase());
        }

        htmlReporter.config().setDocumentTitle("Tab FusionRMS Automation Report");
        htmlReporter.config().setReportName("Tab FusionRMS Automation Report");
        htmlReporter.config().setTheme(Theme.STANDARD);
        htmlReporter.config().setTimeStampFormat("EEE, MMMM dd, yyyy, hh:mm a '('zzz')'");
    }

    /**
     * In this section mentioned test on which , which environment , which region
     * @param prod = Mention which product you have to select (Ex: web, api)
     * @param env = (Optional) Mention which environment you have to select (Ex: uat, prod, saas, demo, test)
     * @param region = (Optional) Mention which region URL you have to open during execution and
     *                 if you don't have any region then mention nothing (Ex: us, ca)
     * */
    @Parameters({"prod","env","region"})
    @BeforeMethod(groups = {"setUp"})
    public void setUp(String prod,@Optional String env, @Optional String region) throws IOException
    {
        fileInputStream = new FileInputStream("./framework_Tools/variables.properties");
        prop.load(fileInputStream);

        if (prod.equalsIgnoreCase("web"))
        {
            webDriver = new ChromeDriver();

            if (env.equalsIgnoreCase("uat"))
            {
                switch (region.toLowerCase())
                {
                    case "us":
                        webDriver.navigate().to("https://uat.tabfusionrms.com");
                        break;
                    case "ca":
                        webDriver.navigate().to("https://uat.tabfusionrms.ca");
                        break;
                    default:
                        System.out.println("You have selected wrong environment!!!");
                        break;
                }
            }
            else if (env.equalsIgnoreCase("prod"))
            {
                switch (region.toLowerCase())
                {
                    case "us":
                        webDriver.navigate().to("https://fusion11.tabfusionrms.com");
                        break;
                    case "ca":
                        webDriver.navigate().to("https://fusion11.tabfusionrms.ca");
                        break;
                    default:
                        System.out.println("You have selected wrong environment!!!");
                        break;
                }
            }
            else if (env.equalsIgnoreCase("demo"))
            {
                webDriver.navigate().to("https://f11.tabfusionrms.com");
            }
            else if (env.equalsIgnoreCase("test"))
            {
                webDriver.navigate().to("https://qa.tabfusionrms.com");
            }
            else if (env.equalsIgnoreCase("saas"))
            {
                webDriver.navigate().to("https://fsaas.tabfusionrms.com");
            }
            else if (env.equalsIgnoreCase("local"))
            {
                webDriver.navigate().to("http://localhost:85/");
            }
            else
            {
                System.out.println("You have selected wrong option or parameter!!!");
            }

            webDriver.manage().window().maximize();
            webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

            //Verify Screen Size and according to them the toggle menu bar is working
            Dimension size = Toolkit.getDefaultToolkit().getScreenSize();

            int width = (int) size.getWidth();
            int height = (int) size.getHeight();
            System.out.println("Screen Width : " + width + "Screen Height : " + height);

            //Verifying screen size and update the flag whether toggle menu is there or not
            if (width >= 1293)
            {
                screenFlag = "False";
            }
            else
            {
                screenFlag = "True";
            }
        }
        else if (prod.equalsIgnoreCase("api"))
        {
            if (env.equalsIgnoreCase("uat"))
            {
                switch (region.toLowerCase())
                {
                    case "us":
                        URL = "https://connectuat.tabfusionrms.comm";
                        break;
                    case "ca":
                        URL = "https://connectuat.tabfusionrms.ca";
                        break;
                    default:
                        System.out.println("You have selected wrong environment!!!");
                        break;
                }
            }
            else if (env.equalsIgnoreCase("prod"))
            {
                switch (region.toLowerCase())
                {
                    case "us":
                        URL = "https://connect.tabfusionrms.com";
                        break;
                    case "ca":
                        URL = "https://connect.tabfusionrms.ca";
                        break;
                    default:
                        System.out.println("You have selected wrong environment!!!");
                        break;
                }
            }
            else if (env.equalsIgnoreCase("demo"))
            {
                URL = "https://connectdemo.tabfusionrms.com";
            }
            else if (env.equalsIgnoreCase("test"))
            {
                URL = "https://restapi.tabfusionrms.com";
            }
            else if (env.equalsIgnoreCase("local"))
            {
                URL = "https://localhost:8999";
            }
            else
            {
                System.out.println("You have selected wrong option or parameter!!!");
            }

            //Specify base URI
            RestAssured.baseURI = URL;

            //Request object
            httpRequest = RestAssured.given();

            //Request and sending a parameters for POST Request
            requestParams = new JSONObject();

            System.out.println("Selected URL : " + URL);
        }
        else
        {
            System.out.println("You Have Selected Wrong Product Value !!!");
        }
    }

    /**
     * In this section take fail test cases screenshot
     * @param driver = Mention driver
     * @param screenShotName = Mention screenshot name to store with mentioned name
     * @return base64 = Return base64 encoded image
     * */
    public String capture(WebDriver driver, String screenShotName) throws IOException
    {
        TakesScreenshot ts = (TakesScreenshot)driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        String dest = System.getProperty("user.dir") +"\\Screenshots\\"+screenShotName+".PNG";
        System.out.println("dest : " + dest);
        File destination = new File(dest);
        FileUtils.copyFile(source, destination);
        System.out.println("destination : " + destination);

        InputStream is = new FileInputStream(dest);
        byte[] ssBytes = IOUtil.toByteArray(is);
        String base64 = Base64.getEncoder().encodeToString(ssBytes);

        return base64;
    }

    /** Read JSON File And Converted in String */
    public static String readJsonFile(String filePath) throws IOException {
        return new String(Files.readAllBytes(Paths.get(filePath)));
    }

    /**
     * In this mentioned after test method is completed what happen and also webDriver is quit
     * @param result = ITestResult is return the test result like pass or fail
     * */
    public void testReport(ITestResult result) throws IOException
    {
        String customTestName = testName.get();
        testName.remove();

        //Get the result of the test and mentioned in report
        if (result.getStatus() == ITestResult.SUCCESS)
        {
            System.out.println("Test Case Name : " + result.getMethod().getMethodName() + " PASS");
            test = result.getMethod().getRealClass().getPackageName().contains("api") ? reports.createTest(customTestName).assignCategory(result.getTestClass().getName()).assignDevice("API").assignAuthor("QA Automation") : reports.createTest(customTestName).assignCategory(result.getTestClass().getName()).assignDevice("Chrome").assignAuthor("QA Automation");
            test.log(Status.PASS, MarkupHelper.createLabel(result.getName() + " PASS ", ExtentColor.GREEN));
        }
        else if (result.getStatus() == ITestResult.FAILURE)
        {
            System.out.println("Test Case Name : " + result.getMethod().getMethodName() + " FAIL");
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd MM yyyy_HH mm ss");
            String timestamp = dateFormat.format(new Date());

            test = result.getMethod().getRealClass().getPackageName().contains("api") ? reports.createTest(customTestName).assignCategory(result.getTestClass().getName()).assignDevice("API").assignAuthor("QA Automation") : reports.createTest(customTestName).assignCategory(result.getTestClass().getName()).assignDevice("Chrome").assignAuthor("QA Automation");

            if(result.getMethod().getRealClass().getPackageName().contains("api"))
            {
                test.log(Status.FAIL, MarkupHelper.createLabel(result.getName() + " FAIL ", ExtentColor.RED));
            }
            else if(result.getMethod().getRealClass().getPackageName().contains("web"))
            {
                String abc = capture(webDriver,result.getName()+"_"+timestamp);

                test.log(Status.FAIL, MarkupHelper.createLabel(result.getName() + " FAIL ", ExtentColor.RED))
                        .addScreenCaptureFromBase64String(abc,result.getName())
                        .fail(result.getThrowable());
            }
            else
            {
                System.out.println("Wrong Statement !!!");
            }
        }
        else if (result.getStatus() == ITestResult.SKIP)
        {
            System.out.println("Test Case Name : " + result.getMethod().getMethodName() + " SKIP");
            test = result.getMethod().getRealClass().getPackageName().contains("api") ? reports.createTest(customTestName).assignCategory(result.getTestClass().getName()).assignDevice("API").assignAuthor("QA Automation") : reports.createTest(customTestName).assignCategory(result.getTestClass().getName()).assignDevice("Chrome").assignAuthor("QA Automation");
            test.log(Status.SKIP, MarkupHelper.createLabel(result.getName() + " SKIP ", ExtentColor.YELLOW)).skip(result.getThrowable());
        }
        else
        {
            System.out.println("Test Case Name : " + result.getMethod().getMethodName() + " Status Not Get");
        }
    }

    /** This method is used for closing the browser after test will complete */
    public void closeBrowser()
    {
        webDriver.quit();
    }

    /**
     * In this section after all the tests completed flush(Export) report
     * and store mentioned location
     * */
    @AfterTest(groups = {"setUp"})
    public void flushReport()
    {
        reports.flush();
    }
}
