package content;

import dependency.IPropertyLoader;

public class Root extends Node {

    public Root() {
        initDefaultValue();
    }

    public Root(IPropertyLoader iPropertyLoader) {
        super(iPropertyLoader);
        initDefaultValue();
    }

    public void removeAll() {
        this.setChildren(null);
    }

    public void initDefaultValue() {
        setId(propertiesLoader.getProperty("root.id"));
        setContent(propertiesLoader.getProperty("root.content"));
        setColor(propertiesLoader.getProperty("root.color"));
        Topic topic = new Topic(propertiesLoader, "1", "Topic 1");
        Topic topic2 = new Topic(propertiesLoader, "1", "Topic 2");
        Topic topic3 = new Topic(propertiesLoader, "1", "Topic 3");
        Topic topic4 = new Topic(propertiesLoader, "1", "Topic 4");
        this.getChildren().add(topic);
        this.getChildren().add(topic2);
        this.getChildren().add(topic3);
        this.getChildren().add(topic4);
    }


}
