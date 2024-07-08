package relationship;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Relationship {
    private String sourceNode;
    private String targetNode;
    private String style;
    private String color;

    private String name;

    public Relationship() {
        this.style = "Thin";
        this.color = "Black";
        this.name = "Relationship";
    }

    public Relationship(String sourceNode, String targetNode) {
        this();
        this.sourceNode = sourceNode;
        this.targetNode = targetNode;
    }

}
