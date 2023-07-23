package pageEvents;

import Utils.Constants;
import Utils.ElementFetch;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import org.openqa.selenium.*;
import org.testng.Assert;
import pageObjects.HomePageElements;
import pageObjects.LoginPageElements;

import java.time.Duration;
import java.util.Iterator;
import java.util.List;

public class HomePageEvents {

	ElementFetch ele=new ElementFetch();

	public void Create_post(ExtentTest test,String message) {

		ele.getWebElement("XPATH",HomePageElements.Postbox).sendKeys(message);
		test.log(Status.INFO,MarkupHelper.createLabel("Post message is entered ", ExtentColor.GREEN));
		ele.getWebElement("XPATH",HomePageElements.Post_Buttton).click();
		test.log(Status.INFO,MarkupHelper.createLabel("Post button is clicked ", ExtentColor.GREEN));
		ele.getWebElement("XPATH",HomePageElements.close_post_success_button).click();
	}
	public boolean VerifyPost(ExtentTest test, String message, WebDriver driver) throws InterruptedException {
		// Its return true when tweet is found.
		// Click on profile Button.
		ele.getWebElement("XPATH",HomePageElements.Profile_button).click();
		test.log(Status.INFO, MarkupHelper.createLabel("Profile Button clicked",ExtentColor.GREEN));

//		 Scroll Down
		Thread.sleep(9000);
		JavascriptExecutor jse = (JavascriptExecutor)driver;
//		jse.executeScript("window.scrollTo(0, document.body.scrollHeight);");
		jse.executeScript("window.scrollBy(0,200)", "");

		// This for loop is used to find the tweet it will iterate throw the element and get the text then it will compare the text.
		Boolean flag=false;
		List<WebElement> allLinks = ele.getWebElements("XPATH",HomePageElements.tweet_check);
		for ( WebElement we: allLinks ){
			if ( we.getText().contains( message ) ){
				test.log(Status.INFO,MarkupHelper.createLabel("Tweet Found "+we.getText() ,ExtentColor.BLUE));
				Assert.assertEquals(message,we.getText());
				// Below line is use for to verify the tweet is public or not
				we.click();
				Boolean check = ele.getWebElement("XPATH",HomePageElements.verify_public_tweet).isDisplayed();
				Assert.assertTrue(check,"Tweet is not public");
				test.log(Status.INFO,MarkupHelper.createLabel("Tweet is public", ExtentColor.GREEN));
				flag=true;
				break;   // Break condition is used because text is found and new page is open that's why we don't want to iterate more element.
			}
		}

		return flag;
	}

	public void search_user(ExtentTest test, String username ,WebDriver driver,String follow) throws InterruptedException {
		ele.getWebElement("XPATH",HomePageElements.Search_box).sendKeys(username);
		ele.getWebElement("XPATH",HomePageElements.Search_box).sendKeys(Keys.ENTER);
		ele.getWebElement("XPATH",HomePageElements.People).click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		driver.findElement(By.xpath("//*[text()='"+username+"']")).click();
		test.log(Status.INFO,MarkupHelper.createLabel("User Found ",ExtentColor.GREEN));
		int old_following_count =Integer.parseInt(ele.getWebElement("XPATH",HomePageElements.Following_count_element).getText());
		if(follow == "follow")
		ele.getWebElement("XPATH",HomePageElements.follow_button).click();
		else{
			ele.getWebElement("XPATH",HomePageElements.unfollow_Button).click();
			ele.getWebElement("XPATH",HomePageElements.unfollow_confirm).click();
		}

		driver.navigate().refresh();
		Thread.sleep(4000);
		int new_following_count =Integer.parseInt(ele.getWebElement("XPATH",HomePageElements.Following_count_element).getText());
		System.out.println(old_following_count+" == "+new_following_count);

		if(follow == "follow") {
			Assert.assertTrue(old_following_count < new_following_count, "Follower not increased");
			test.log(Status.INFO, MarkupHelper.createLabel("New Follwing count is " + new_following_count, ExtentColor.GREEN));
			// Verify followed user appears in the "Following" section
			ele.getWebElement("XPATH", HomePageElements.Following_count_element).click();
			Boolean check_username = driver.findElement(By.xpath("//*[text()='@" + Constants.Username + "']")).isDisplayed();
			Assert.assertTrue(check_username, "Username is not added in follower list");
		}
		else{
			Assert.assertTrue(old_following_count > new_following_count, "Follower not increased");
			test.log(Status.INFO, MarkupHelper.createLabel("Follow count is " + new_following_count, ExtentColor.GREEN));
			// Verify followed user appears in the "Following" section
			ele.getWebElement("XPATH", HomePageElements.Following_count_element).click();
			Boolean check_username = driver.getPageSource().contains("@"+Constants.Username);
			Assert.assertFalse(check_username, "Username is found in follower list");
		}
	}

}
