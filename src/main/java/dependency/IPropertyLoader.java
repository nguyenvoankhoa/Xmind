package dependency;

public interface IPropertyLoader {
    String getProperty(String key);

    String getProperty(String key, String defaultValue);
}
