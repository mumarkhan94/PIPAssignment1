package Utils;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import base.BaseTest;

public class ElementFetch {

	
	public WebElement getWebElement(String indentifireType ,String identifireValue) {
		// This method is used to fetch the elements.
		BaseTest baseTest=new BaseTest();
		switch(indentifireType){
		
		case "XPATH":
			return baseTest.driver.findElement(By.xpath(identifireValue));
		case "CSS":
			return BaseTest.driver.findElement(By.cssSelector(identifireValue));
		case "ID":
			return BaseTest.driver.findElement(By.id(identifireValue));
		case "NAME":
			return BaseTest.driver.findElement(By.name(identifireValue));
		case "TAGNAME":
			return BaseTest.driver.findElement(By.tagName(identifireValue));
		default:
			return null;
			
		}
	}
	
	
	public List<WebElement> getWebElements(String indentifireType ,String identifireValue) {
		// This method is used to fetch the list of elements.
		switch(indentifireType){
		
		case "XPATH":
			return BaseTest.driver.findElements(By.xpath(identifireValue));
		case "CSS":
			return BaseTest.driver.findElements(By.cssSelector(identifireValue));
		case "ID":
			return BaseTest.driver.findElements(By.id(identifireValue));
		case "NAME":
			return BaseTest.driver.findElements(By.name(identifireValue));
		case "TAGNAME":
			return BaseTest.driver.findElements(By.tagName(identifireValue));
		default:
			return null;
			
		}
	}

}
