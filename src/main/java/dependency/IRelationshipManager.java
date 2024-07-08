package dependency;

import content.Node;
import relationship.Relationship;

import java.util.List;

public interface IRelationshipManager {
    void removeRelationship(Relationship relationship);

    List<Relationship> addRelationship(Node src, Node target);

    List<Relationship> getRelationships();


}
