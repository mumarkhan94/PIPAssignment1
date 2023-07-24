package pageEvents;

import Utils.Constants;
import Utils.ElementFetch;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import io.netty.util.Constant;
import org.testng.Assert;
import pageObjects.HomePageElements;
import pageObjects.LoginPageElements;

public class LoginPageEvents {

    ElementFetch ele=new ElementFetch();

    public void Login(ExtentTest test){
        // This method is used for login.
        ele.getWebElement("XPATH", LoginPageElements.Username).sendKeys(Constants.Username);
        test.log(Status.INFO, MarkupHelper.createLabel("Username entered", ExtentColor.GREEN));
        ele.getWebElement("XPATH", LoginPageElements.NextButton).click();
        ele.getWebElement("XPATH", LoginPageElements.Password).sendKeys(Constants.Password);
        test.log(Status.INFO, MarkupHelper.createLabel("Password entered", ExtentColor.GREEN));
        ele.getWebElement("XPATH", LoginPageElements.Login_Button).click();
        test.log(Status.INFO, MarkupHelper.createLabel("Login Button clicked", ExtentColor.GREEN));
    }

    public void Login(ExtentTest test,String user,String pass){
        // This method allow user to login with help of parameter.
        ele.getWebElement("XPATH", LoginPageElements.Username).sendKeys(user);
        test.log(Status.INFO, MarkupHelper.createLabel("Username entered", ExtentColor.GREEN));
        ele.getWebElement("XPATH", LoginPageElements.NextButton).click();
        ele.getWebElement("XPATH", LoginPageElements.Password).sendKeys(pass);
        test.log(Status.INFO, MarkupHelper.createLabel("Password entered", ExtentColor.GREEN));
        ele.getWebElement("XPATH", LoginPageElements.Login_Button).click();
        test.log(Status.INFO, MarkupHelper.createLabel("Login Button clicked", ExtentColor.GREEN));
    }

    public boolean logout(ExtentTest test){
        // This method is used for log out.
        ele.getWebElement("XPATH",LoginPageElements.profil_photo).click();
        ele.getWebElement("XPATH",LoginPageElements.logout).click();
        ele.getWebElement("XPATH",LoginPageElements.logoutConfirm).click();
        test.log(Status.INFO,MarkupHelper.createLabel("Logout successfull ",ExtentColor.GREEN));
        return true;
    }

}
