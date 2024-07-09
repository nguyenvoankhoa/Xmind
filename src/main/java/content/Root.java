package content;

import dependency.IPropertyLoader;

public class Root extends Node {

    public Root(IPropertyLoader iPropertyLoader) {
        super(iPropertyLoader);
        initDefaultValue();
    }

    public void removeAll() {
        this.setChildren(null);
    }

    public void initDefaultValue() {
        this.setId(propertiesLoader.getProperty("root.id"));
        this.setContent(propertiesLoader.getProperty("root.content"));
        this.setColor(propertiesLoader.getProperty("root.color"));
        Topic topic = new Topic(propertiesLoader, "1", "Topic 1");
        Topic topic2 = new Topic(propertiesLoader, "2", "Topic 2");
        Topic topic3 = new Topic(propertiesLoader, "3", "Topic 3");
        Topic topic4 = new Topic(propertiesLoader, "4", "Topic 4");
        this.getChildren().add(topic);
        this.getChildren().add(topic2);
        this.getChildren().add(topic3);
        this.getChildren().add(topic4);
    }


}
