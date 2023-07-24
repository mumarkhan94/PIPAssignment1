package pageEvents;

import Utils.Constants;
import Utils.ElementFetch;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import pageObjects.HomePageElements;
import pageObjects.MessagePageElements;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class MessagePageEvents {

	ElementFetch ele = new ElementFetch();

	public String Send(ExtentTest test, String message,WebDriver driver,String username) {

		// This method is used for send message.
		// This three line used to open the message box with help of username.
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2000));
		ele.getWebElement("XPATH", MessagePageElements.messege_box_open).click();
		driver.findElement(By.xpath("//*[text()='"+username+"']")).click();

		//These three line used for selecting input box and entering the message and clicking on send.
		Actions action=new Actions(driver);
		WebElement link=ele.getWebElement("XPATH",MessagePageElements.messege_box_input);
		action.clickAndHold(link).sendKeys(message).sendKeys(Keys.ENTER).perform();

		// These line is used for validating message is sent and message is displaying or not in message box.
		boolean flag=false;
		List<WebElement> allLinks = ele.getWebElements("XPATH",MessagePageElements.send_message);
		for (WebElement we : allLinks) {
			if (we.getText().contains(message)) {
				test.log(Status.INFO, MarkupHelper.createLabel("message Found " + we.getText(), ExtentColor.BLUE));
				Assert.assertEquals(message, we.getText());
				flag=true;
				break;   // Break condition is used because text is found and new page is open that's why we don't want to iterate more element.
			}
		}
		Assert.assertTrue(flag,"Message not sent");

		return message;
	}

	public String Received(ExtentTest test, String message,String received_message,WebDriver driver,String username) {

		// This method is used for to validate that message is received or not first it will navigate to the message box with help of username.
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2000));
		ele.getWebElement("XPATH", MessagePageElements.messege_box_open).click();
		driver.findElement(By.xpath("//*[text()='"+username+"']")).click();

// This line is used for sending message form receiver side.
		Actions action=new Actions(driver);
		WebElement link=ele.getWebElement("XPATH",MessagePageElements.messege_box_input);
		action.clickAndHold(link).sendKeys(message).sendKeys(Keys.ENTER).perform();
//
		// This line is used for validating received message.
		boolean flag=false;
		List<WebElement> allLinks = ele.getWebElements("XPATH",MessagePageElements.send_message);
		for (WebElement we : allLinks) {
			if (we.getText().contains(received_message)) {
				test.log(Status.INFO, MarkupHelper.createLabel("message Found " + we.getText(), ExtentColor.BLUE));
				Assert.assertEquals(received_message, we.getText());
				flag=true;
				break;   // Break condition is used because text is found and new page is open that's why we don't want to iterate more element.
			}
		}
		Assert.assertTrue(flag,"Message not sent");

		// This line is used for validating sent message.
		boolean flag1=false;
		List<WebElement> allLinks1 = ele.getWebElements("XPATH",MessagePageElements.send_message);
		for (WebElement we : allLinks1) {
			if (we.getText().contains(message)) {
				test.log(Status.INFO, MarkupHelper.createLabel("message Found " + we.getText(), ExtentColor.BLUE));
				Assert.assertEquals(message, we.getText());
				flag=true;
				break;   // Break condition is used because text is found and new page is open that's why we don't want to iterate more element.
			}
		}
		Assert.assertTrue(flag,"Message not sent");

		return message;
	}


}