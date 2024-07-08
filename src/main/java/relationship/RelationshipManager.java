package relationship;

import content.Node;
import dependency.IRelationshipManager;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

@Setter
@Getter
public class RelationshipManager implements IRelationshipManager {
    private List<Relationship> relationships;
    public RelationshipManager() {
        this.relationships = new ArrayList<>();
    }


    public void removeRelationship(Relationship relationship) {
        relationships.stream()
                .filter(r -> r.getSourceNode().equals(relationship.getSourceNode()) && r.getTargetNode().equals(relationship.getTargetNode()))
                .findFirst()
                .ifPresent(r -> relationships.remove(r));
    }

    public List<Relationship> addRelationship(Node src, Node target) throws IOException {
        relationships.add(new Relationship(src, target));
        return this.relationships;
    }
}
