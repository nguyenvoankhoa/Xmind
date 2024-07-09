package relationship;

import content.Node;
import dependency.IPropertyLoader;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Relationship {
    private Node sourceNode;
    private Node targetNode;
    private String style;
    private String color;

    private String name;
    private IPropertyLoader propertiesLoader;

    public Relationship(IPropertyLoader iPropertyLoader) {
        this.propertiesLoader = iPropertyLoader;
        this.style = propertiesLoader.getProperty("relationship.style");
        this.color = propertiesLoader.getProperty("relationship.color");
        this.name = propertiesLoader.getProperty("relationship.name");
    }

    public Relationship(IPropertyLoader iPropertyLoader,Node sourceNode, Node targetNode) {
        this(iPropertyLoader);
        this.sourceNode = sourceNode;
        this.targetNode = targetNode;
    }

    public void changeTargetRelationship(Node targetNode) {
        this.targetNode = targetNode;
    }

}
