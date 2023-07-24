package QA.TestNG;

import Utils.ElementFetch;
import base.BaseTest;
import org.testng.annotations.Test;
import pageEvents.HomePageEvents;
import pageEvents.LoginPageEvents;
import pageEvents.ProfilePageEvents;

public class TestCase8 extends BaseTest {


    LoginPageEvents ele = new LoginPageEvents();
    ElementFetch ef = new ElementFetch();
    HomePageEvents hpe = new HomePageEvents();
    ProfilePageEvents ppe = new ProfilePageEvents();


    @Test
    public void Hashtag() throws InterruptedException {
        test = extent.createTest("Test Case 1 Hashtag verification");
        ele.Login(test);
        hpe.Hashtag_Navigation(test, driver);

    }
}