package Utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class Reusable_method {

    public String getData_from_json(String name) {
        // Path to the JSON file
        String filePath = "./DataFile/user.json";
        String name1=null;
        // Read and parse JSON from the file
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode rootNode = objectMapper.readTree(new File(filePath));

            // Get the "name" value from the JSON
             name1 = rootNode.get(name).asText();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return name1;
    }


    public String convertUTCToIST(String utcTimestamp) {
        try {
            // Parse the UTC timestamp to Instant
            Instant instant = Instant.parse(utcTimestamp);

            // Convert to IST (Indian Standard Time)
            ZoneId istZoneId = ZoneId.of("Asia/Kolkata");
            LocalDateTime istDateTime = instant.atZone(istZoneId).toLocalDateTime();

            // Format the IST LocalDateTime to a desired output format
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String istFormatted = istDateTime.format(formatter);

            return istFormatted;
        } catch (Exception e) {
            // In case of any parsing error, return an empty string or handle the error as needed
            e.printStackTrace();
            return "";
        }
    }

}

