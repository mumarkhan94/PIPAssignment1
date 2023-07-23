package pageObjects;

import org.testng.Assert;

public interface LoginPageElements {

    String Username = "//input[@autocomplete=\"username\"]";
    String Password = "//input[@autocomplete=\"current-password\"]";
    String NextButton = "(//div[@dir=\"ltr\"])[6]";
    String Login_Button ="(//div[@dir=\"ltr\"])[8]";
    String Verify_home_page = "(//span[@class=\"css-901oao css-16my406 r-poiln3 r-bcqeeo r-qvutc0\"])[3]";

}
