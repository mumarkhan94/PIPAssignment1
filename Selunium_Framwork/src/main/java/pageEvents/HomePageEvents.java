package pageEvents;

import Utils.ElementFetch;
import pageObjects.HomePageElements;

public class HomePageEvents {
ElementFetch ef=new ElementFetch();
	public void clickNextButton()
	{
		ef.getWebElement("XPATH",HomePageElements.NextButton).click();
	}
}
