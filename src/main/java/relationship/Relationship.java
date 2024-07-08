package relationship;

import content.Node;
import lombok.Getter;
import lombok.Setter;
import setting.PropertiesLoader;

import java.io.IOException;

@Getter
@Setter
public class Relationship {
    private Node sourceNode;
    private Node targetNode;
    private String style;
    private String color;

    private String name;
    private PropertiesLoader propertiesLoader = PropertiesLoader.getInstance();

    public Relationship() throws IOException {
        this.style = propertiesLoader.getProperty("relationship.style");
        this.color = propertiesLoader.getProperty("relationship.color");
        this.name = propertiesLoader.getProperty("relationship.name");
    }

    public Relationship(Node sourceNode, Node targetNode) throws IOException {
        this();
        this.sourceNode = sourceNode;
        this.targetNode = targetNode;
    }

    public void changeTargetRelationship(Node targetNode) {
        this.targetNode = targetNode;
    }

}
