package QA.TestNG;

import Utils.Constants;
import Utils.ElementFetch;
import base.BaseTest;
import org.testng.annotations.Test;
import pageEvents.HomePageEvents;
import pageEvents.LoginPageEvents;
import pageEvents.ProfilePageEvents;

public class TestCase7 extends BaseTest {

    LoginPageEvents ele=new LoginPageEvents();
    ElementFetch ef=new ElementFetch();
    HomePageEvents hpe =new HomePageEvents() ;
    ProfilePageEvents ppe= new ProfilePageEvents();


    @Test
    public void Retweet() throws InterruptedException {
        test = extent.createTest("Test Case 1 Retweet");
        ele.Login(test);
        ppe.Retweet(test,driver,"tweet");


    }
}
