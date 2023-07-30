package QA.TestNG;

import ApiPackage.GetRequest;
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

public class TestCase2 extends BaseTest1 implements Constants {

    GetRequest getRequest=new GetRequest();
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
    public void validateSingleSetOfResponse() {
        // Send a GET request to the API
        extentTest.get().info("Test Case 2 Validate data having single set of response.");
        getRequest.validateDataSingleSetOfResponse(Base_url+Resource_url+"/"+userIdFromResponse);
        extentTest.get().log(Status.INFO, MarkupHelper.createLabel("Single set response found", ExtentColor.BLUE));
    }

    @Test
    public void validateIdAndEndpointIdSame() {
        // Send a GET request to the API
        extentTest.get().info("Test Case 2.1 Validate data having single set of response.");
        getRequest.validateDataIdAndEndpointId(Base_url+Resource_url+"/",userIdFromResponse);
        extentTest.get().log(Status.INFO, MarkupHelper.createLabel("Single set response found", ExtentColor.BLUE));
    }


}
