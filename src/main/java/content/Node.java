package content;

import setting.PropertiesLoader;
import setting.Structure;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Getter
@Setter
public class Node {
    private String id;
    private String content;
    private String color;
    private String background;
    private List<Node> children;
    private boolean isOpen;
    private String shape;
    private String font;
    private Structure structure;

    private PropertiesLoader propertiesLoader = PropertiesLoader.getInstance();

    public Node() throws IOException {
        this.color = propertiesLoader.getProperty("root.color");
        this.children = new ArrayList<>();
        this.isOpen = true;
        this.font = propertiesLoader.getProperty("board.globalFont");
        this.background = propertiesLoader.getProperty("board.background");
        this.shape = propertiesLoader.getProperty("root.shape");
        this.structure = Structure.FISH_BONE;
    }

    public Node(String id, String content) throws IOException {
        this();
        this.id = id;
        this.content = content;
    }

    public Node(String id, String content, List<Node> children) throws IOException {
        this(id, content);
        this.children = children;
    }


    public void setStructure(Structure structure) {
        this.structure = structure;
        Optional.ofNullable(children)
                .ifPresent(nodes -> nodes.forEach(node -> node.setStructure(structure)));
    }

    public List<Node> addChild(Node child) {
        Optional.of(child)
                .filter(Leaf.class::isInstance)
                .map(c -> (Leaf) c)
                .ifPresent(leaf -> leaf.setParent(this));
        children.add(child);
        return children;
    }

    public List<Node> removeChild(String childId) {
        this.children.removeIf(n -> n.id.equals(childId));
        return this.children;
    }

    public void collapse() {
        setOpen(false);
    }

    public void expand() {
        setOpen(true);
    }

    public Node findById(String id) {
        return Optional.of(this)
                .filter(node -> getId().equals(id))
                .orElseGet(() -> getChildren().stream()
                        .map(node -> node.findById(id))
                        .filter(Objects::nonNull)
                        .findFirst()
                        .orElse(null));
    }
}
