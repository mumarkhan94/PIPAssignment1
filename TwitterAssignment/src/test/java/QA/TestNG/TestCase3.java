package QA.TestNG;

import Utils.ElementFetch;
import base.BaseTest;
import org.testng.annotations.Test;
import pageEvents.FeedsPageEvents;
import pageEvents.HomePageEvents;
import pageEvents.LoginPageEvents;
import pageEvents.ProfilePageEvents;

public class TestCase3 extends BaseTest {

    LoginPageEvents ele=new LoginPageEvents();
    FeedsPageEvents fpe=new FeedsPageEvents();


    @Test
    public void Search_for_celebrity() throws InterruptedException {
        test = extent.createTest("Test Case 4 Search for celebrity and validate the feeds. ");
        ele.Login(test);// login method call
        fpe.feeds_search(test,"@iamsrk",driver); // Search for celebrity and follow the celebrity and validate the feeds.

    }
}
