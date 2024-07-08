package setting;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesLoader {
    private final Properties properties = new Properties();

    private PropertiesLoader(String propertiesFileName) throws IOException{
        InputStream input = getClass().getClassLoader().getResourceAsStream(propertiesFileName);
        properties.load(input);
    }

    public static PropertiesLoader getInstance() throws IOException {
        return new PropertiesLoader("application.properties");
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }

    public String getProperty(String key, String defaultValue) {
        return properties.getProperty(key, defaultValue);
    }
}
