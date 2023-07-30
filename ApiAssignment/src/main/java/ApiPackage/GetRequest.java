package ApiPackage;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;

import java.util.List;
import java.util.Map;

public class GetRequest {

    public void validateQueryParamNumberAndPageNnumberAreSame(String url,String PageNumber) {
        // Send a GET request to the API
        System.out.println(url+PageNumber);
        Response response = RestAssured.get(url+PageNumber);
        // Validate query parameter values for "page" and "per_page"
        int queryParamPageNumber = Integer.parseInt(response.jsonPath().getString("page"));
        Assert.assertEquals(queryParamPageNumber, Integer.parseInt(PageNumber), "Page number is not "+PageNumber);
    }


    public void validatePerPageCountAndTotalDataLength(String apiurl) {
        // Send a GET request to the API
        Response response = RestAssured.get(apiurl);
        // Validate "per_page" count and total data length
        int perPageCount = Integer.parseInt(response.jsonPath().getString("per_page"));
        int totalDataLength = response.jsonPath().getList("data").size();
        Assert.assertEquals(perPageCount, totalDataLength, "Per_page count and total data length are not same");
    }

    public void validateTotalGreaterThanPerPageCount(String apiUrl) {
        // Send a GET request to the API
        Response response = RestAssured.get(apiUrl);

        // Validate "total" is greater than "per_page" count if "total_pages" count is more than 1
        int totalPages = Integer.parseInt(response.jsonPath().getString("total_pages"));
        int perPageCount = Integer.parseInt(response.jsonPath().getString("per_page"));

        if (totalPages > 1) {
            int total = Integer.parseInt(response.jsonPath().getString("total"));
            Assert.assertTrue(total > perPageCount, "Total is not greater than per_page count");
        }
    }

    public void validateIdStartsFrom1AndInAscendingOrder(String apiUrl) {
        // Send a GET request to the API
        Response response = RestAssured.get(apiUrl);

        // Validate that "id" starts from 1 and is in ascending order
        List<Integer> ids = response.jsonPath().getList("data.id");
        int previousId = 0;

        for (int id : ids) {
            Assert.assertTrue(id > 0, "Id should be greater than 0");
            Assert.assertTrue(id > previousId, "Ids are not in ascending order");
            previousId = id;
        }
    }

    public void validateDataKeysAndValues(String apiUrl) {
        // Send a GET request to the API
        Response response = RestAssured.get(apiUrl);

        // Validate keys and non-empty values in the "data" section
        List<Map<String, ?>> dataList = response.jsonPath().getList("data");

        for (Map<String, ?> data : dataList) {
            Assert.assertTrue(data.containsKey("id"), "Data does not contain 'id' key");
            Assert.assertTrue(data.containsKey("email"), "Data does not contain 'email' key");
            Assert.assertTrue(data.containsKey("first_name"), "Data does not contain 'first_name' key");
            Assert.assertTrue(data.containsKey("last_name"), "Data does not contain 'last_name' key");
            Assert.assertTrue(data.containsKey("avatar"), "Data does not contain 'avatar' key");

            Object idValue = data.get("id");
            Assert.assertTrue(idValue instanceof Integer, "Id should be of numeric type");

            validateStringValue(data, "email");
            validateStringValue(data, "first_name");
            validateStringValue(data, "last_name");
            validateStringValue(data, "avatar");
        }
    }
    private void validateStringValue(Map<String, ?> data, String key) {
        Object value = data.get(key);
        Assert.assertTrue(value instanceof String, key + " should be of string type");
        Assert.assertFalse(value.toString().isEmpty(), key + " value is empty");
    }


    public void validateDataSingleSetOfResponse(String apiUrl) {
        Response response = RestAssured.get(apiUrl);
        // Verify that the response status code is 200 (OK)
        Assert.assertEquals(response.getStatusCode(), 200);
        System.out.println(response.getBody().asString());
        // Get the size of the "data" section
        int dataSize = response.jsonPath().getInt("size()");
        // The data size should be 2, as there are two sets of data in the "data" section
        Assert.assertEquals(dataSize, 2, "The 'data' section should contain a single set of data.");

    }

    public void validateDataIdAndEndpointId(String apiUrl,int userIdFromResponse) {
        String endpointUrl = apiUrl+userIdFromResponse;

        Response response = RestAssured.get(endpointUrl);
        Assert.assertEquals(response.getStatusCode(), 200);

        // Get the id from the "data" section of the response
        int dataId = response.jsonPath().getInt("data.id");
        // Validate that the "data.id" matches the endpoint id
        Assert.assertEquals(dataId, userIdFromResponse, "The 'data.id' should match the endpoint id.");
    }






}
