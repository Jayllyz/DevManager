package org.main;

import java.io.FileReader;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class Main {
    public static void main(String[] args) {
        JSONParser parser = new JSONParser();
        try {
            String dataPath = "src/main/java/org/main/data/data.json";
            Object obj = parser.parse(new FileReader(dataPath));
            JSONObject jsonObject = (JSONObject) obj;

            JSONArray developers = (JSONArray) jsonObject.get("Developers");

            for (Object developerObj : developers) {
                JSONObject developer = (JSONObject) developerObj;
                String name = (String) developer.get("name");
                long experience = (long) developer.get("experience");

                System.out.println("Name: " + name);
                System.out.println("Experience: " + experience);
                System.out.println();
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}