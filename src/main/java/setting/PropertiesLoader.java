package setting;

import dependency.IPropertyLoader;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesLoader implements IPropertyLoader {
    private final Properties properties = new Properties();

    public PropertiesLoader(String propertiesFileName) throws IOException {
        InputStream input = getClass().getClassLoader().getResourceAsStream(propertiesFileName);
        properties.load(input);
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }

    public String getProperty(String key, String defaultValue) {
        return properties.getProperty(key, defaultValue);
    }
}
