package content;

import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.util.Optional;

@Getter
@Setter
public class Leaf extends Node {
    private Node parent;

    private boolean isFloating;


    public Leaf(String id, String content) throws IOException {
        super(id, content);
    }

    public Leaf(String id, String content, Node parent) throws IOException {
        super(id, content);
        this.parent = parent;
    }

    public void changeParent(String parentId, Root root) {
        Node parent = root.findById(parentId);
        Optional.ofNullable(this.getParent())
                .ifPresent(p -> p.removeChild(this.getId()));
        Optional.ofNullable(parent)
                .ifPresent(p -> p.addChild(this));
        this.setFloating(true);
    }
}
