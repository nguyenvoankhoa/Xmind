package relationship;

import content.Node;
import dependency.IPropertyLoader;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class Relationship {
    Map<Node, Node> rela;
    private String style;
    private String color;

    private String name;
    private IPropertyLoader propertiesLoader;

    public Relationship(IPropertyLoader iPropertyLoader) {
        this.rela = new HashMap<>();
        this.propertiesLoader = iPropertyLoader;
        this.style = propertiesLoader.getProperty("relationship.style");
        this.color = propertiesLoader.getProperty("relationship.color");
        this.name = propertiesLoader.getProperty("relationship.name");
    }

    public Relationship(IPropertyLoader iPropertyLoader, Node sourceNode, Node targetNode) {
        this(iPropertyLoader);
        this.rela.put(sourceNode, targetNode);
    }

    public void changeTargetRelationship(Node targetNode) {
        Node sourceNode = rela.keySet().iterator().next();
        rela.put(sourceNode, targetNode);
    }

}
