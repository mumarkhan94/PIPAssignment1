package QA.TestNG;

import Utils.ElementFetch;
import base.BaseTest;
import org.testng.annotations.Test;
import pageEvents.HomePageEvents;
import pageEvents.LoginPageEvents;
import pageEvents.MessagePageEvents;
import pageEvents.ProfilePageEvents;

public class TestCase6 extends BaseTest {


    LoginPageEvents ele = new LoginPageEvents();
    ElementFetch ef = new ElementFetch();
    HomePageEvents hpe = new HomePageEvents();
    ProfilePageEvents ppe = new ProfilePageEvents();
    MessagePageEvents mpe=new MessagePageEvents();


    @Test
    public void Send_and_recive() throws InterruptedException {
        test = extent.createTest("Test Case 1 Hashtag verification");
        ele.Login(test,"khankhan94516","Umarkhan.up");
        String check_send_message=mpe.Send(test,"hello world",driver,"@updarkshadow");
        ele.logout(test);
        ele.Login(test,"updarkshadow","Umarkhan.up");
        mpe.Received(test,"hi fre",check_send_message,driver,"@umarkhan94516");

    }
}
