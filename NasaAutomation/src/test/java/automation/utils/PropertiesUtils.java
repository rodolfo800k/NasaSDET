package automation.utils;

import automation.commons.Constants;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesUtils {

    public static String readProperty(String property){
        Properties properties = new Properties();
        try(FileInputStream fileInputStream = new FileInputStream(Constants.TEST_PROPERTIES_FILE)){
            properties.load(fileInputStream);
            return properties.getProperty(property);
        }catch (IOException ioException){
            System.err.println("Error loading properties file: " + Constants.TEST_PROPERTIES_FILE);
        }
        return null;
    }

}
