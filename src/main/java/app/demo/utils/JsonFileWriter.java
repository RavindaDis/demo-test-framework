package app.demo.utils;

import app.demo.api.base.ApiBase;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class JsonFileWriter extends ApiBase {

    private static JSONParser parser = new JSONParser();

    /**
     * Update information to a JSON file
     *
     * @param fileName
     * @param key
     * @param value
     * @return
     */
    public static void writeToJsonFile(String fileName, String key, String value) {
        String filePath = Constants.TEST_DATA_LOCATION + Constants.TEST_DATA_FUOTA_PAYLOAD_LOCATION + fileName;

        try {
            Object obj = parser.parse(new FileReader(filePath));
            JSONObject jsonObject = (JSONObject) obj;
            jsonObject.put(key, value);

            FileWriter writer = new FileWriter(filePath, false);
            writer.write(jsonObject.toString());
            writer.close();

        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }
}
