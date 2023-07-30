package pageEvents;

import Utils.Constants;
import Utils.ElementFetch;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import pageObjects.HomePageElements;
import pageObjects.ProfilePageElements;

import java.time.Duration;
import java.util.List;

public class ProfilePageEvents {

    ElementFetch ele=new ElementFetch();
    public void Retweet(ExtentTest test, WebDriver driver,String tweettopic) throws InterruptedException {

        //This method is used for retweet verification.
        // Fist it will find trending tweet.
        ele.getWebElement("XPATH", HomePageElements.Search_box).sendKeys(tweettopic);
        ele.getWebElement("XPATH",HomePageElements.Search_box).sendKeys(Keys.ENTER);
        ele.getWebElement("XPATH",HomePageElements.Latest).click();
        test.log(Status.INFO,MarkupHelper.createLabel("Latest Tweet is showing now.",ExtentColor.GREEN));
// Retweeting latest trending tweet.
        Thread.sleep(5000); // Because of catch its displaying previous data for one send after that its refreshing i want new data that's why i used sleep.
        String username = ele.getWebElement("XPATH",ProfilePageElements.retweet_user).getText();
        ele.getWebElement("XPATH", ProfilePageElements.retweet_element).click();
        ele.getWebElement("XPATH",ProfilePageElements.retweet_confirm).click();
        ele.getWebElement("XPATH",HomePageElements.Profile_button).click();
//        ele.getWebElement("XPATH",ProfilePageElements.close_buuton).click();
        test.log(Status.INFO,MarkupHelper.createLabel("Latest tweet is retweeted and clicked on profile button.",ExtentColor.GREEN));

// Validating retweeted tweet is displaying or not in users profile.
        String text = ele.getWebElement("XPATH",ProfilePageElements.verify_you_retweeted).getText();
        Assert.assertTrue(text.equals(ProfilePageElements.retweeted_message),"Retweeted message not found");

        Boolean check_username = driver.getPageSource().contains(username);
        Assert.assertTrue(check_username,"Same retweet user not found ");
        test.log(Status.INFO,MarkupHelper.createLabel("Retweet is verified and the user name is "+username,ExtentColor.GREEN));

    }
}
