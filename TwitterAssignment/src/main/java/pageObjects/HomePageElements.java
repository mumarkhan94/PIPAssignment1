package pageObjects;

import com.aventstack.extentreports.ExtentTest;

public interface HomePageElements {


    String Postbox = "//div[@data-testid=\"tweetTextarea_0\"]";
    String Post_Buttton = "//div[@data-testid=\"tweetButtonInline\"]";
    String Profile_button = "//a[@aria-label=\"Profile\"]";
    String tweet_check = "//div[@data-testid=\"tweetText\"]";
    String verify_public_tweet = "//a[@data-testid=\"analyticsButton\"]";
    String close_post_success_button = "//div[@aria-label=\"Close\"]";
    String Search_box = "//input[@data-testid=\"SearchBox_Search_Input\"]";
    String People = "(//div[@role=\"presentation\"])[4]";
    String Following_count_element = "(//span[@class=\"css-901oao css-16my406 r-poiln3 r-bcqeeo r-qvutc0\"])[13]";
    String follow_button = "//div[@data-testid=\"1682986005790617606-follow\"]";
    String unfollow_Button = "//div[@data-testid=\"1682986005790617606-unfollow\"]";
    String unfollow_confirm ="//div[@data-testid=\"confirmationSheetConfirm\"]";




}
