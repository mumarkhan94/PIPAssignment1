package QA.TestNG;

import ApiPackage.PostRequest;
import Utils.Constants;
import base.BaseTest1;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import org.testng.annotations.Test;

public class TestCase3 extends BaseTest1 implements Constants {

    PostRequest postrequest=new PostRequest();



    @Test
    public void CreateNewUser() {

        extentTest.get().info("Test Case 3 Validate data having single set of response.");
        postrequest.testCreateUser(Base_url+Resource_url);
        extentTest.get().log(Status.INFO, MarkupHelper.createLabel("Single set response found", ExtentColor.BLUE));

    }



}
