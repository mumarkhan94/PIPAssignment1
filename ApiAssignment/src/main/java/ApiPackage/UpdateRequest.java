package ApiPackage;

import Utils.Reusable_method;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.testng.Assert;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class UpdateRequest {
    Reusable_method reusable_method=new Reusable_method();


    public void testUpdateUser(String apiUrl,int userId) throws IOException {
        // Read data from the "user.json" file
        File jsonFile = new File("./DataFile/user.json");
        // Get the ID from the previous API response or use any valid ID

        // Send a PUT request to the API with the JSON object as the body
        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(jsonFile)
                .put(apiUrl + "/" + userId);

        // Verify that the response status code is 200 (OK)
        Assert.assertEquals(response.getStatusCode(), 200);
        System.out.println(response.getBody().asString());
        // Get local time after response.
        LocalDateTime currentTimeIST = LocalDateTime.now(ZoneId.of("Asia/Kolkata"));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String currentTimeISTFormatted = currentTimeIST.format(formatter);

        // Validate CreatedAt date and time are matching with the date and time of the API call happened
        String datetime = response.jsonPath().getString("updatedAt");
        String apiCallTimeFormatted = reusable_method.convertUTCToIST(datetime);
        Assert.assertEquals(apiCallTimeFormatted, currentTimeISTFormatted, "updatedAt date and time do not match the API call time in IST");


        // Validate Response name and job are matching with request body's name and job
        String nameFromJson = response.jsonPath().getString("name");
        String jobFromJson = response.jsonPath().getString("job");


        String  jsondataname= reusable_method.getData_from_json("name");
        String jsondatajob = reusable_method.getData_from_json("job");

        Assert.assertEquals(nameFromJson, jsondataname, "Response name does not match the request body's name");
        Assert.assertEquals(jobFromJson, jsondatajob, "Response job does not match the request body's job");

    }
}

