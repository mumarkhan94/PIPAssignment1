package qa.Test;

import org.testng.annotations.Test;

import Utils.ElementFetch;
import pageEvents.HomePageEvents;
import pageEvents.LoginPageEvents;

public class Testcase2 {

	ElementFetch ele=new ElementFetch();
	HomePageEvents homepage=new HomePageEvents();
	LoginPageEvents loginpage = new LoginPageEvents();
	  
	    @Test
	    public void test1(){
	        System.out.println("test 1 ....");
	        System.out.print("hello");
	  	  homepage.clickNextButton();
	    }

	    @Test
	    public void test2(){
	        System.out.println("Test 2 .....");
	    }

	
}
