package ApiPackage;

import Utils.Reusable_method;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;

import java.io.File;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class PostRequest {
    Reusable_method reusable_method=new Reusable_method();

    public void testCreateUser(String apiUrl) {
        // Read data from the JSON file
        File jsonFile = new File("./DataFile/user.json");

        // Send a POST request to the API with JSON data from the file
        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(jsonFile)
                .post(apiUrl);
        // Get local time after response.
        LocalDateTime currentTimeIST = LocalDateTime.now(ZoneId.of("Asia/Kolkata"));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String currentTimeISTFormatted = currentTimeIST.format(formatter);

        // Verify that the response status code is 201 (Created)
        Assert.assertEquals(response.getStatusCode(), 201);
        System.out.println(response.getBody().asString());

        // Validate Response name and job are matching with request body's name and job
        String nameFromJson = response.jsonPath().getString("name");
        String jobFromJson = response.jsonPath().getString("job");


        String  jsondataname= reusable_method.getData_from_json("name");
        String jsondatajob = reusable_method.getData_from_json("job");

        Assert.assertEquals(nameFromJson, jsondataname, "Response name does not match the request body's name");
        Assert.assertEquals(jobFromJson, jsondatajob, "Response job does not match the request body's job");

        // Validate CreatedAt date and time are matching with the date and time of the API call happened
        String datetime = response.jsonPath().getString("createdAt");
        String apiCallTimeFormatted = reusable_method.convertUTCToIST(datetime);
        Assert.assertEquals(apiCallTimeFormatted, currentTimeISTFormatted, "CreatedAt date and time do not match the API call time in IST");

    }
}

