package utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Logger;

/**
 * Utility for loading properties values from a properties file.
 * <p>
 * Usage:
 * - PropertyUtil.getProperty("key") -> reads from default classpath file 'config.properties'
 * - PropertyUtil.getProperty("key", "my.properties") -> reads from classpath or filesystem path
 */
public final class PropertyUtil {

    private static final Logger LOGGER = Logger.getLogger(PropertyUtil.class.getName());
    private Properties properties;

    public PropertyUtil(String filePath) {
        File file = new File(filePath);
        try {
            FileInputStream fileInputStream = new FileInputStream(file);//Read
            properties = new Properties();
            try {
                properties.load(fileInputStream);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }

    public static String getProperty(String key, String filePath) {
        File file = new File(filePath);
        Properties properties = null;
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            properties = new Properties();
            try {
                properties.load(fileInputStream);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return properties.getProperty(key);
    }

}

