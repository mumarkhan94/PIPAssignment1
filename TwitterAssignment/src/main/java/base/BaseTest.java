package base;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import Utils.Constants;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.testng.ITestResult;
import org.testng.annotations.*;


public class BaseTest {

    public static WebDriver driver;
    public ExtentReports extent;
    public ExtentTest test;


    @BeforeClass
    public void setUp() {
        // Specify the location and name of the report file
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter("test-output/ExtentReport.html");

        // Create an ExtentReports object
        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);
    }

    // This method will except browser name from testng.xml
    @BeforeMethod
    @Parameters("browser")
    public void setBrowser(String browser, ITestResult result) {
//		test = reports.createTest(result.getName()); // It will log the test Name.
        setupDriver(browser);
        driver.manage().window().maximize(); // This line is used to maximize the window.
        driver.get(Constants.url); // This will get url address from utils/constants.java.
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));  // Its will wait 20 seconds for elements visible.

    }


    @AfterMethod
    public void getResult(ITestResult result) throws InterruptedException {
        // Capture test case status and add to the report
        if (result.getStatus() == ITestResult.FAILURE) {
            test.log(Status.FAIL, MarkupHelper.createLabel(result.getName() + " - Test Case Failed", ExtentColor.RED));
            test.log(Status.FAIL, MarkupHelper.createLabel(result.getThrowable() + " - Test case Failed", ExtentColor.RED));
            String relativePath = attachScreenshot(result.getName());
            test.log(Status.FAIL, "Screenshot of the failure", MediaEntityBuilder.createScreenCaptureFromPath(relativePath).build());

        } else if (result.getStatus() == ITestResult.SKIP) {
            test.log(Status.SKIP, MarkupHelper.createLabel(result.getName() + " - Test Case Skip", ExtentColor.ORANGE));
        } else if (result.getStatus() == ITestResult.SUCCESS) {
            test.log(Status.PASS, MarkupHelper.createLabel(result.getName() + " - Test Case Passed", ExtentColor.GREEN));
            String relativePath = attachScreenshot(result.getName());
            test.log(Status.PASS, "Screenshot of the Success", MediaEntityBuilder.createScreenCaptureFromPath(relativePath).build());

        }
    }


    public String attachScreenshot(String testName) throws InterruptedException {

        String screenshotPath = "";
        String relativePath = "";
        Thread.sleep(2000);
        try {
            TakesScreenshot screenshot = (TakesScreenshot) driver;
            File srcFile = screenshot.getScreenshotAs(OutputType.FILE);
            String screenshotName = testName + "screenshot.png";
            screenshotPath = "test-output/" + screenshotName;
            FileUtils.copyFile(srcFile, new File(screenshotPath));

            // Attach the screenshot to the report
            relativePath = new File(screenshotPath).getName();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return relativePath;
    }


    @AfterClass
    public void closeBrowser() throws InterruptedException {
        Thread.sleep(20000);
        driver.close();
    }

    @AfterTest
    public void tearDown() {
        // Flush the report and close resources
        System.out.println("teardown run");
        extent.flush();
    }


    public void setupDriver(String browser) {
        System.out.println("Browser code");
        if(browser.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();}
        else if(browser.equalsIgnoreCase("chrome_head_less")) {
            ChromeOptions opt =new ChromeOptions();
            opt.setHeadless(true);
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver(opt);
        } else if (browser.equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        } else if (browser.equalsIgnoreCase("edge")) {
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();
        }
    }

}

