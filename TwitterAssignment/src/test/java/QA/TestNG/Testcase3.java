package QA.TestNG;

import Utils.Constants;
import Utils.ElementFetch;
import base.BaseTest;
import com.aventstack.extentreports.ExtentReports;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageEvents.HomePageEvents;
import pageEvents.LoginPageEvents;

public class Testcase3 extends BaseTest {


    LoginPageEvents ele=new LoginPageEvents();
    ElementFetch ef=new ElementFetch();
    HomePageEvents hpe =new HomePageEvents() ;


    @Test
    public void Follow_Unfollow() throws InterruptedException {
        test = extent.createTest("Test Case 1 Tweet Creation");
        ele.Login(test);
        hpe.search_user(test,Constants.Search_User,driver,"follow");
        hpe.search_user(test,Constants.Search_User,driver,"unfollow");

    }
}






