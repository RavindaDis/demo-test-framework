package app.demo.utils;

import app.demo.api.base.ApiBase;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JsonFileReader extends ApiBase {

    private static JSONParser parser = new JSONParser();

    /**
     * Read a JSON file and extract String value of the provided key
     *
     * @param fileName
     * @param key
     * @return value
     */
    public static String readStringFromJsonFile(String fileName, String key) {

        String value = "";

        try {
            Object obj = parser.parse(new FileReader(Constants.TEST_DATA_LOCATION + Constants.TEST_DATA_FUOTA_PAYLOAD_LOCATION + fileName));
            JSONObject jsonObject = (JSONObject) obj;
            value = (String) jsonObject.get(key);

        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

        return value;
    }

    /**
     * Read a JSON file and extract Integer value of the provided key
     *
     * @param fileName
     * @param key
     * @return valueInt
     */
    public static int readIntegerFromJsonFile(String fileName, String key) {

        long value = 0;

        try {
            Object obj = parser.parse(new FileReader(Constants.TEST_DATA_LOCATION + Constants.TEST_DATA_FUOTA_PAYLOAD_LOCATION + fileName));
            JSONObject jsonObject = (JSONObject) obj;
            value = (long) jsonObject.get(key);

        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        int valueInInt = (int) value;

        return valueInInt;
    }

    /**
     * Read a JSON file and extract List<String> values of the provided key
     *
     * @param fileName
     * @param key
     * @return list
     */
    public static List<String> readStringArrayFromJsonFile(String fileName, String key) {

        List<String> list = new ArrayList<>();

        try {
            Object obj = parser.parse(new FileReader(Constants.TEST_DATA_LOCATION + Constants.TEST_DATA_FUOTA_PAYLOAD_LOCATION + fileName));
            JSONObject jsonObject = (JSONObject) obj;
            JSONArray array = (JSONArray) jsonObject.get(key);

            for (Object o : array) {
                list.add((String) o);
            }

        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return list;
    }
}
