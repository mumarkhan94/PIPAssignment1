package base;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.testng.ITestResult;
import org.testng.annotations.*;

public class BaseTest1 {

    public static ThreadLocal<ExtentReports> extentReports = new ThreadLocal<>();
    public static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();

    @BeforeSuite
    public void setUp() {
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter("test-output/ExtentReport.html");
        ExtentReports extent = new ExtentReports();
        extent.attachReporter(sparkReporter);
        extentReports.set(extent);
    }

    @BeforeMethod
    public void startTest(ITestResult result) {
        String methodName = result.getMethod().getMethodName();
        ExtentTest test = extentReports.get().createTest(getClass().getSimpleName(), "Test Case Method name "+methodName);
        extentTest.set(test);
    }

    @AfterMethod
    public void getResult(ITestResult result) {
        // Capture test case status and add to the report
        if (result.getStatus() == ITestResult.FAILURE) {
            extentTest.get().log(Status.FAIL, MarkupHelper.createLabel(result.getName() + " - Test Case Failed", ExtentColor.RED));
            extentTest.get().log(Status.FAIL, MarkupHelper.createLabel(result.getThrowable() + " - Test case Failed", ExtentColor.RED));

        } else if (result.getStatus() == ITestResult.SKIP) {
            extentTest.get().log(Status.SKIP, MarkupHelper.createLabel(result.getName() + " - Test Case Skip", ExtentColor.ORANGE));
        } else if (result.getStatus() == ITestResult.SUCCESS) {
            extentTest.get().log(Status.PASS, MarkupHelper.createLabel(result.getName() + " - Test Case Passed", ExtentColor.GREEN));
        }
    }

    @AfterSuite
    public void tearDown() {
        extentReports.get().flush();
    }
}
