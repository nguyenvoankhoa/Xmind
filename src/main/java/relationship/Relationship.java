package relationship;

import content.Node;
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

    public Relationship() {
        this.style = "Thin";
        this.color = "Black";
        this.name = "Relationship";
    }

    public Relationship(Node sourceNode, Node targetNode) {
        this();
        this.sourceNode = sourceNode;
        this.targetNode = targetNode;
    }

    public void changeTargetRelationship(Node targetNode) {
        this.targetNode = targetNode;
    }

}
