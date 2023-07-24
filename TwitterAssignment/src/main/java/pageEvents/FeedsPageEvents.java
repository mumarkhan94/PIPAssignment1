package pageEvents;

import Utils.ElementFetch;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import org.openqa.selenium.*;
import org.testng.Assert;
import pageObjects.FeedsPageElements;
import pageObjects.HomePageElements;

import java.awt.*;
import java.time.Duration;
import java.util.List;

public class FeedsPageEvents {
    ElementFetch ele = new ElementFetch();

    public void feeds_search(ExtentTest test, String username, WebDriver driver) throws InterruptedException {
        // Search the celebrity.
        ele.getWebElement("XPATH", HomePageElements.Search_box).sendKeys(username);
        ele.getWebElement("XPATH", HomePageElements.Search_box).sendKeys(Keys.ENTER);
        ele.getWebElement("XPATH", HomePageElements.People).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        String text = driver.findElement(By.xpath("//*[text()='" + username + "']")).getText();
        driver.findElement(By.xpath("//*[text()='" + username + "']")).click();
        test.log(Status.INFO, MarkupHelper.createLabel("Celebrity found ", ExtentColor.GREEN));
//        follow the celebrity and navigate back to timeline page.
        ele.getWebElement("XPATH", FeedsPageElements.follow_sleb_button).click();
        ele.getWebElement("XPATH", FeedsPageElements.home_button).click();
        ele.getWebElement("XPATH", FeedsPageElements.following_home_page_button).click();
        test.log(Status.INFO, MarkupHelper.createLabel("Celebrity follow done back to timeline. ", ExtentColor.GREEN));
        // Validate that related feed is present or not if feeds not found scroll down and check again..
        boolean flag = false;
        for (int i = 0; i <=20; i++) {
            if(driver.getPageSource().contains(text)){
                test.log(Status.INFO, MarkupHelper.createLabel("Celebrity tweet found ", ExtentColor.GREEN));
                flag=true;
            }else {
                JavascriptExecutor jse = (JavascriptExecutor) driver;
                Thread.sleep(1000);
                jse.executeScript("window.scrollBy(0,600)", "");
            }

//
        }
        Assert.assertTrue(flag, "Related feed not found");
        // Search the celebrity.
        ele.getWebElement("XPATH", HomePageElements.Search_box).sendKeys(username);
        ele.getWebElement("XPATH", HomePageElements.Search_box).sendKeys(Keys.ENTER);
        ele.getWebElement("XPATH", HomePageElements.People).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.findElement(By.xpath("//*[text()='" + username + "']")).click();
        test.log(Status.INFO, MarkupHelper.createLabel("Celebrity found again", ExtentColor.GREEN));
        // unfollow celebrity.
        ele.getWebElement("XPATH", FeedsPageElements.follow_sleb_button).click();
        ele.getWebElement("XPATH",FeedsPageElements.unfollow_confirm).click();
        test.log(Status.INFO, MarkupHelper.createLabel("Unfollow Celebrity successful", ExtentColor.GREEN));
    }


}

