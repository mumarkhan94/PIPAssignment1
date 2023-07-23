package QA.TestNG;

import Utils.Constants;
import Utils.ElementFetch;
import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageEvents.HomePageEvents;
import pageEvents.LoginPageEvents;
import pageObjects.HomePageElements;
import pageObjects.LoginPageElements;

public class Testcase2 extends BaseTest {

    LoginPageEvents ele=new LoginPageEvents();
    ElementFetch ef=new ElementFetch();
    HomePageEvents hpe =new HomePageEvents() ;


    @Test
    public void Tweet_Creation() throws InterruptedException {
        test = extent.createTest("Test Case 1 Tweet Creation");
        ele.Login(test);
        hpe.Create_post(test, Constants.tweet_message);
        boolean flag = hpe.VerifyPost(test, Constants.tweet_message,driver);
        Assert.assertTrue(flag==true,"Tweet not Found!");
    }
}
