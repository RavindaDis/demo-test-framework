package app.demo.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Reads config.properties file and maps values
 **/
public class ConfigProperties {

    private ConfigProperties() {
    }

    public static final Logger log = LoggerFactory.getLogger(ConfigProperties.class);
    private static Map<String, String> configMap;
    private static Properties properties = new Properties();

    public static Map<String, String> populateConfigs() {
        try (FileInputStream fileInputStream = new FileInputStream(System.getProperty("user.dir") + Constants.PROPERTY_FILE_LOCATION)) {
            configMap = new HashMap<>();
            properties.load(fileInputStream);
            configMap.put(Constants.CONTENT_TYPE, properties.getProperty(Constants.CONTENT_TYPE));
            configMap.put(Constants.X_REQUESTED_WITH, properties.getProperty(Constants.X_REQUESTED_WITH));
            configMap.put(Constants.CSRF_TOKEN, properties.getProperty(Constants.CSRF_TOKEN));
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Error when mapping values from config.properties file", e);
        }
        return configMap;
    }

    public static Map<String, String> getConfigs() {
        if (configMap == null) {
            populateConfigs();
        }
        return configMap;

    }

}
