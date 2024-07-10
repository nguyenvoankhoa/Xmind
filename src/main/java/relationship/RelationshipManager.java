package relationship;

import content.Node;
import dependency.IPropertyLoader;
import dependency.IRelationshipManager;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;

@Setter
@Getter
public class RelationshipManager implements IRelationshipManager {
    private List<Relationship> relationships;

    public RelationshipManager() {
        this.relationships = new ArrayList<>();
    }


    public void removeRelationship(Relationship rel) {
        for (Map.Entry<Node, Node> entry : rel.getRela().entrySet()) {
            Node sourceNode = entry.getKey();
            Node targetNode = entry.getValue();
            relationships.removeIf(r -> r.getRela().containsKey(sourceNode) && r.getRela().get(sourceNode).equals(targetNode));
        }
    }

    public List<Relationship> addRelationship(IPropertyLoader propertyLoader, Node src, Node target) {
        relationships.add(new Relationship(propertyLoader, src, target));
        return this.relationships;
    }
}
