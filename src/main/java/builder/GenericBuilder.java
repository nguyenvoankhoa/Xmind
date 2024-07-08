package builder;

import content.Leaf;
import content.Node;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public abstract class GenericBuilder<T extends GenericBuilder<T>> {
    protected String id;

    protected String content;
    protected String color;
    protected String background;
    protected List<Node> children = new ArrayList<>();
    protected boolean isOpen;
    protected String font;

    public T addId(String id) {
        this.id = id;
        return self();
    }

    public T addBackground(String background) {
        this.background = background;
        return self();
    }


    public T addOpen(boolean status) {
        this.isOpen = status;
        return self();
    }

    public T addFont(String font) {
        this.font = font;
        return self();
    }

    public T addIsOpen(boolean isOpen) {
        this.isOpen = isOpen;
        return self();
    }

    public T addChildren(String... children) throws IOException {
        for (String str : children) {
            Leaf leaf = new Leaf(UUID.randomUUID().toString(), str);
            this.children.add(leaf);
        }
        return self();
    }

    public T addContent(String content) {
        this.content = content;
        return self();
    }

    public T addColor(String color) {
        this.color = color;
        return self();
    }

    protected abstract T self();

    public abstract Node build() throws IOException;
}
