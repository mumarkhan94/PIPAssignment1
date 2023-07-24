package QA.TestNG;

import Utils.ElementFetch;
import Utils.ReadDataFile;
import base.BaseTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pageEvents.LoginPageEvents;
import pageObjects.LoginPageElements;

import java.io.IOException;
import java.util.Locale;

public class TestCase1 extends BaseTest{

    LoginPageEvents ele = new LoginPageEvents();
    ElementFetch ef = new ElementFetch();

//    @Test
//    public void Login_test() {
//        test = extent.createTest("Test Case 1 Login");
//        ele.Login(test);
//        String text = ef.getWebElement("XPATH", LoginPageElements.Verify_home_page).getText();
//        Assert.assertTrue(text.equals("Home"));
//
//    }

    @Test(dataProvider = "LoginData")
    public void Login_test2(String username, String password, String expected) throws InterruptedException {
        System.out.println(username+" "+password+" "+expected);
        test = extent.createTest("Test Case 1.1 Data driven Login");
        ele.Login(test, username, password);
        test.log(Status.INFO, MarkupHelper.createLabel("Login method is call", ExtentColor.GREEN));
        String text = ef.getWebElement("XPATH", LoginPageElements.Verify_home_page).getText();
        System.out.println(text);
        if (expected.toLowerCase(Locale.ROOT).equals("invalid")) {
            test.log(Status.FAIL, MarkupHelper.createLabel("Login successful that's why ist fail", ExtentColor.RED));
            Assert.assertFalse(text.equals("Home"), "Login successful that's why ist fail");
        } else {
            Thread.sleep(2000);
            test.log(Status.PASS, MarkupHelper.createLabel("Login successful", ExtentColor.GREEN));
            Assert.assertTrue(text.equals("Home"), "Login successful");
        }

    }

    @DataProvider(name = "LoginData")
    public Object[][] getData() throws IOException {
        ReadDataFile reader = new ReadDataFile();
        Object[][] loginData = reader.read_data();
        return loginData;
    }

}
