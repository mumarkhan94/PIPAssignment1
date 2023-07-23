package QA.TestNG;

import Utils.ElementFetch;
import base.BaseTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageEvents.LoginPageEvents;
import pageObjects.HomePageElements;
import pageObjects.LoginPageElements;

public class TestCase1 extends BaseTest{

    LoginPageEvents ele=new LoginPageEvents();
    ElementFetch ef=new ElementFetch();

    @Test
    public void Login_test() {
        test = extent.createTest("Test Case 1 Login");
        ele.Login(test);
        String text = ef.getWebElement("XPATH", LoginPageElements.Verify_home_page).getText();
        Assert.assertTrue(text.equals("Home"));

    }
//
//    @Test
//    public void test2(){
//        System.out.println("test 2 ....");
//        test = extent.createTest("Test Case 2 loged");
//
//        // Your test code here
//        test.log(Status.INFO, "Starting Test Case 2");
////        test.log(Status.FAIL, MarkupHelper.createLabel("Test Case 2 is Failed", ExtentColor.RED));
//
//    }


}
