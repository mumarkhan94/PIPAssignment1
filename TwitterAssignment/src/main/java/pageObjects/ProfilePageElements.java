package pageObjects;

public interface ProfilePageElements {

    String retweeted_message = "You Retweeted";
    String retweet_element= "(//div[@data-testid=\"retweet\"])[1]";
    String retweet_confirm = "//div[@data-testid=\"retweetConfirm\"]";
    String retweet_user="(//div[@class=\"css-1dbjc4n r-1wbh5a2 r-dnmrzs\"])[2]";
    String verify_you_retweeted = "(//span[@data-testid=\"socialContext\"])[1]";
    String close_buuton = "//div[@data-testid=\"app-bar-close\"]";



}
