package QA.TestNG;


import ApiPackage.GetRequest;
import Utils.Constants;

import base.BaseTest1;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import org.testng.annotations.Test;

public class TestCase1 extends BaseTest1 implements Constants{

    GetRequest getRequest=new GetRequest();


    @Test
    public void validatePageNumber() {
        // Send a GET request to the API
        extentTest.get().info("Test Case 1 Page number Validation.");
        getRequest.validateQueryParamNumberAndPageNnumberAreSame(Base_url+Resource_url+"?page=","1");
        extentTest.get().log(Status.INFO,MarkupHelper.createLabel("Page number found",ExtentColor.BLUE));
    }

    @Test
    public void ValidatePerPageCountAndTotalDataLengthShouldBeSame() {
        // Send a GET request to the API
        extentTest.get().info("Test Case 1.1 Per_page count and total data length should be same.");
        getRequest.validatePerPageCountAndTotalDataLength(Base_url+Resource_url+"?page=1");
        extentTest.get().log(Status.INFO,MarkupHelper.createLabel("Data length is same",ExtentColor.BLUE));
    }

    @Test
    public void ValidateTotalIsGreaterThanPerPageCount () {
        // Send a GET request to the API
        extentTest.get().info("Test Case 1.2 Validate total is greater than Per PageCount");
        getRequest.validateTotalGreaterThanPerPageCount(Base_url+Resource_url+"?page=1");
        extentTest.get().log(Status.INFO,MarkupHelper.createLabel("total count is greater than per page count",ExtentColor.BLUE));
    }

    @Test
    public void ValidateIdAscendingOrder() {
        // Send a GET request to the API
        extentTest.get().info("Test Case 1.3 Validate Id starts with 1 and displayed in ascending order");
        getRequest.validateIdStartsFrom1AndInAscendingOrder(Base_url+Resource_url+"?page=1");
        extentTest.get().log(Status.INFO,MarkupHelper.createLabel("Id is greater than previous Id and its staring with 1",ExtentColor.BLUE));
    }


    @Test
    public void ValidateUserData() {
        // Send a GET request to the API
        extentTest.get().createNode("jcdjckacajc");
        extentTest.get().info("Test Case 1.4 Validate data has id, email, firstname, lastname, avatar keys and validate all keys has staring values except Id having number");
        getRequest.validateDataKeysAndValues(Base_url+Resource_url+"?page=1");
        extentTest.get().log(Status.INFO,MarkupHelper.createLabel("Id is greater than previous Id and its staring with 1",ExtentColor.BLUE));
    }





}
