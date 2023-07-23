package Compare_two_map;

import java.util.HashMap;
import java.util.Map;

public class CompareMaps {


    public static void main(String[] args) {
        Map<String, String> map1 = new HashMap<>();
        map1.put("a", "apple");
        map1.put("b", "bat");
        map1.put("c", "cat");
        map1.put("d", "dog");

        Map<String, String> map2 = new HashMap<>();
        map2.put("b", "bat");
        map2.put("c", "cat");
        map2.put("a", "ant");
        map2.put("d", "dog");

        compareMaps(map1, map2);


    }

    public static void compareMaps(Map<String, String> map1, Map<String, String> map2) {
        // Compare keys and values of map1 with map2
        for (Map.Entry<String, String> entry : map1.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();


            // Check if map2 does not contain the key or the values are different
            if (!map2.containsKey(key) || !map2.get(key).equals(value)) {
                System.out.println("Key: " + key);
            }
        }

        // Compare keys and values of map2 with map1
        for (Map.Entry<String, String> entry : map2.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();

            // Check if map1 does not contain the key or the values are different
            if (!map1.containsKey(key) || !map1.get(key).equals(value)) {
                System.out.println("Key: " + key);
            }
        }
    }

}
