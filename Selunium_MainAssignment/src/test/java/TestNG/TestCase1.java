package TestNG;

import Utility_package.Comman_variable;
import Utility_package.Utility;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import  org.testng.annotations.*;
public class TestCase1 {


    WebDriver driver = null;


    @BeforeSuite
    public void beforeSuit(){
        System.out.println("Before Suit");
//        Utility utility = new Utility();
//        driver = utility.Driver_set_up();

    }

    @AfterSuite
    public void afterSuit(){
        System.out.println("After Suit");
    }

    @BeforeTest
    public void beforeTest(){
        System.out.println("Before test");

    }

    @AfterTest
    public void Aftertest(){
        System.out.println("after test");
    }

    @BeforeClass
    public void beforeClass(){
        System.out.println("Before Class is excuted");
    }

    @AfterClass
    public void afterClass(){
        System.out.println("After Class is excuted");
    }

    @BeforeMethod
    public void beforMethod(){
        System.out.println("Before Method is excuted");
    }

    @AfterMethod
    public void aftreMethod(){
        System.out.println("After Method is excuted");
    }
    @Test
    public void test1(){
        System.out.println("test 1 ....");
    }

    @Test
    public void test2(){
        System.out.println("Test 2 .....");
    }
}
