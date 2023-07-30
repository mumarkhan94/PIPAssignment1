package QA.TestNG;

import ApiPackage.UpdateRequest;
import Utils.Constants;
import base.BaseTest1;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;

public class TestCase4 extends BaseTest1 implements Constants {


    UpdateRequest updateRequest=new UpdateRequest();
    private int userIdFromResponse;
    private int Idnum= 4;
    @BeforeClass
    public void getIdFromResponse() {
        Response response = RestAssured.get(Base_url+Resource_url+"?page=1");
        Assert.assertEquals(response.getStatusCode(), 200);
        // Get the id from the first data element in the response
        userIdFromResponse = response.jsonPath().getInt("data["+(Idnum-1)+"].id");
    }



    @Test
    public void UpdateUser() throws IOException {

        extentTest.get().info("Test Case 4 Validate updated data response details.");
        updateRequest.testUpdateUser(Base_url+Resource_url,userIdFromResponse);
        extentTest.get().log(Status.INFO, MarkupHelper.createLabel("Correct updated response found", ExtentColor.BLUE));

    }


}
