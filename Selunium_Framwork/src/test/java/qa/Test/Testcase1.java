package qa.Test;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import Utils.ElementFetch;
import base.BaseTest;
import pageEvents.HomePageEvents;
import pageEvents.LoginPageEvents;
import pageObjects.HomePageElements;

public class Testcase1 extends BaseTest{
	ElementFetch ele=new ElementFetch();
	HomePageEvents homepage=new HomePageEvents();
	LoginPageEvents loginpage = new LoginPageEvents();
	
  @Test
  public void sampleMethodforenteringCredential() {
	  System.out.print("hello");
	  BaseTest.driver.findElement(By.xpath(HomePageElements.NextButton)).click();
	  homepage.clickNextButton();
  }
  
  @Test
  public void test2() {
	  homepage.clickNextButton();
  }
  
  @Test
  public void test3(){
      System.out.println("test 3 ....");
  }
}
