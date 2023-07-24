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
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HomePageEvents {

	ElementFetch ele = new ElementFetch();

	public void Create_post(ExtentTest test, String message) {
//		This method is for creating post.
		ele.getWebElement("XPATH", HomePageElements.Postbox).sendKeys(message);
		test.log(Status.INFO, MarkupHelper.createLabel("Post message is entered ", ExtentColor.GREEN));
		ele.getWebElement("XPATH", HomePageElements.Post_Buttton).click();
		test.log(Status.INFO, MarkupHelper.createLabel("Post button is clicked ", ExtentColor.GREEN));
		ele.getWebElement("XPATH", HomePageElements.close_post_success_button).click();
	}

	public boolean VerifyPost(ExtentTest test, String message, WebDriver driver) throws InterruptedException {
		// Its return true when tweet is found.
		// Click on profile Button.
		ele.getWebElement("XPATH", HomePageElements.Profile_button).click();
		test.log(Status.INFO, MarkupHelper.createLabel("Profile Button clicked", ExtentColor.GREEN));

//		 Scroll Down
		Thread.sleep(9000);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
//		jse.executeScript("window.scrollTo(0, document.body.scrollHeight);");
		jse.executeScript("window.scrollBy(0,200)", "");

		// This for loop is used to find the tweet it will iterate throw the element and get the text then it will compare the text.
		Boolean flag = false;
		List<WebElement> allLinks = ele.getWebElements("XPATH", HomePageElements.tweet_check);
		for (WebElement we : allLinks) {
			if (we.getText().contains(message)) {
				test.log(Status.INFO, MarkupHelper.createLabel("Tweet Found " + we.getText(), ExtentColor.BLUE));
				Assert.assertEquals(message, we.getText());
				// Below line is use for to verify the tweet is public or not
				we.click();
				Boolean check = ele.getWebElement("XPATH", HomePageElements.verify_public_tweet).isDisplayed();
				Assert.assertTrue(check, "Tweet is not public");
				test.log(Status.INFO, MarkupHelper.createLabel("Tweet is public", ExtentColor.GREEN));
				flag = true;
				break;   // Break condition is used because text is found and new page is open that's why we don't want to iterate more element.
			}
		}

		return flag;
	}

	public void follow_unfollow(ExtentTest test, String username, WebDriver driver, String follow) throws InterruptedException {

//		This method is use for following users withe help of users name.
		// Click on search box search users in people section.
		ele.getWebElement("XPATH", HomePageElements.Search_box).sendKeys(username);
		ele.getWebElement("XPATH", HomePageElements.Search_box).sendKeys(Keys.ENTER);
		ele.getWebElement("XPATH", HomePageElements.People).click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		driver.findElement(By.xpath("//*[text()='" + username + "']")).click();
		test.log(Status.INFO, MarkupHelper.createLabel("User Found ", ExtentColor.GREEN));
		int old_following_count = Integer.parseInt(ele.getWebElement("XPATH", HomePageElements.Following_count_element).getText());
		if (follow == "follow") // This line is used for follow and unfollow if user pass follow then it will follow.
			ele.getWebElement("XPATH", HomePageElements.follow_button).click();
		else {// This two line is used for unfollow.
			ele.getWebElement("XPATH", HomePageElements.unfollow_Button).click();
			ele.getWebElement("XPATH", HomePageElements.unfollow_confirm).click();
		}

		driver.navigate().refresh();
		Thread.sleep(4000); //These below two line check the followers count is increased or not.
		int new_following_count = Integer.parseInt(ele.getWebElement("XPATH", HomePageElements.Following_count_element).getText());
		System.out.println(old_following_count + " == " + new_following_count);

		if (follow == "follow") { //This if condition check follower is added in users followers list or not.
			Assert.assertTrue(old_following_count < new_following_count, "Follower not increased");
			test.log(Status.INFO, MarkupHelper.createLabel("New Follwing count is " + new_following_count, ExtentColor.GREEN));
			// Verify followed user appears in the "Following" section
			ele.getWebElement("XPATH", HomePageElements.Following_count_element).click();
			Boolean check_username = driver.findElement(By.xpath("//*[text()='@" + Constants.Username + "']")).isDisplayed();
			Assert.assertTrue(check_username, "Username is not added in follower list");
		} else {
			Assert.assertTrue(old_following_count > new_following_count, "Follower not increased");
			test.log(Status.INFO, MarkupHelper.createLabel("Follow count is " + new_following_count, ExtentColor.GREEN));
			// Verify followed user appears in the "Following" section
			ele.getWebElement("XPATH", HomePageElements.Following_count_element).click();
			Boolean check_username = driver.getPageSource().contains("@" + Constants.Username);
			Assert.assertFalse(check_username, "Username is found in follower list");
		}
	}


	public void Trending_topic(ExtentTest test, WebDriver driver) throws InterruptedException {

		ele.getWebElement("XPATH", HomePageElements.Side_Search).click();
		ele.getWebElement("XPATH", HomePageElements.trending_button).click();
		test.log(Status.INFO,MarkupHelper.createLabel("Trending Button is clicked",ExtentColor.GREEN));
		// Capture the initial state of the trending topics
		List<String> initialState = captureTrendingTopics(driver);
		// Wait for a certain amount of time (e.g., 30 seconds) to check if the page is dynamic
		try {
			Thread.sleep(30000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		driver.navigate().refresh();
		test.log(Status.INFO,MarkupHelper.createLabel("Trending topic is copy and waiting for change.",ExtentColor.GREEN));

		// Capture the state of the trending topics after waiting
		List<String> finalState = captureTrendingTopics(driver);
		// Compare the two states to check if there are any changes
		boolean isDynamic = !initialState.equals(finalState);


		if (isDynamic) {
			test.log(Status.INFO,MarkupHelper.createLabel("The Twitter trending page is dynamic.",ExtentColor.GREEN));
		} else {
			test.log(Status.INFO,MarkupHelper.createLabel("The Twitter trending page is not dynamic.",ExtentColor.GREEN));
		}


		String topic_text = ele.getWebElement("XPATH", HomePageElements.trending_topic).getText();
		ele.getWebElement("XPATH", HomePageElements.trending_topic).click();
//		System.out.println(topic_text);
		test.log(Status.INFO,MarkupHelper.createLabel("Trending topic is selected.",ExtentColor.GREEN));


		for (int i = 0; i <= 10; i++) {
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			Thread.sleep(1000);
			jse.executeScript("window.scrollBy(0,600)", "");
		}
		test.log(Status.INFO,MarkupHelper.createLabel("Verifying the related #tag is present or not.",ExtentColor.GREEN));
		List<WebElement> element = driver.findElements(By.xpath("//*[text()='" + topic_text + "']"));
		Assert.assertTrue(element.size() >= 3, "Related topic tag not found " + element.size());
	}






	public void Hashtag_Navigation(ExtentTest test, WebDriver driver) throws InterruptedException {

		// This method used for hashtag related feeds is present in timeline or not
		// These four line will select the trending hashtag.
		ele.getWebElement("XPATH", HomePageElements.Side_Search).click();
		test.log(Status.INFO,MarkupHelper.createLabel("Side search button is clicked.",ExtentColor.GREEN));


		String topic_text = ele.getWebElement("XPATH", HomePageElements.trending_topic).getText();
		ele.getWebElement("XPATH", HomePageElements.trending_topic).click();
//		System.out.println(topic_text);
		test.log(Status.INFO,MarkupHelper.createLabel("Hashtag is selected.",ExtentColor.GREEN));

		// Below line is used to validate related feeds is present that's why its scrolling down.
		for (int i = 0; i <= 10; i++) {
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			Thread.sleep(1000);
			jse.executeScript("window.scrollBy(0,600)", "");
		}
		test.log(Status.INFO,MarkupHelper.createLabel("Verifying the related #tag is present or not.",ExtentColor.GREEN));
		List<WebElement> element = driver.findElements(By.xpath("//*[text()='" + topic_text + "']"));
		Assert.assertTrue(element.size() >= 3, "Related topic tag not found " + element.size());
	}




	private static List<String> captureTrendingTopics(WebDriver driver) {
		// Find the trending elements using a more general XPath
		List<WebElement> trendingElements = driver.findElements(By.xpath(HomePageElements.trending_topic_count));

		// Store the text of each trending topic in a list
		List<String> trendingTopicNames = new ArrayList<>();
		for (WebElement element : trendingElements) {
			trendingTopicNames.add(element.getText());
		}

		return trendingTopicNames;
	}
}