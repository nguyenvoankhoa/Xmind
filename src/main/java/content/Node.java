package content;

import dependency.IPropertyLoader;
import lombok.NoArgsConstructor;
import setting.Structure;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Getter
@Setter
@NoArgsConstructor
public class Node {
    protected String id;

    protected String content;
    protected String color;
    protected String background;
    protected List<Node> children;
    protected boolean isOpen;
    protected String shape;
    protected String font;
    protected Structure structure;
    protected IPropertyLoader propertiesLoader;

    public Node(IPropertyLoader iPropertyLoader) {
        this.propertiesLoader = iPropertyLoader;
        this.color = propertiesLoader.getProperty("root.color");
        this.children = new ArrayList<>();
        this.isOpen = Boolean.parseBoolean(propertiesLoader.getProperty("root.isOpen"));
        this.font = propertiesLoader.getProperty("board.globalFont");
        this.background = propertiesLoader.getProperty("board.background");
        this.shape = propertiesLoader.getProperty("root.shape");
        this.structure = Structure.FISH_BONE;
    }

    public Node(IPropertyLoader iPropertyLoader, String id, String content) {
        this(iPropertyLoader);
        this.id = id;
        this.content = content;
    }


    public void setStructure(Structure structure) {
        this.structure = structure;
        Optional.ofNullable(children)
                .ifPresent(nodes -> nodes.forEach(node -> node.setStructure(structure)));
    }

    public List<Node> addChild(Topic child) {
        child.setParent(this);
        children.add(child);
        return children;
    }

    public List<Node> removeChild(Node child) {
        this.children.removeIf(n -> n.equals(child));
        return this.children;
    }

    public void collapse() {
        setOpen(false);
    }

    public void expand() {
        setOpen(true);
    }


    public String clearText() {
        setContent("");
        return this.content;
    }
}
