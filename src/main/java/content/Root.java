package content;

import org.springframework.beans.factory.annotation.Value;

import java.io.IOException;
import java.util.List;

public class Root extends Node {
    @Value("${board.id}")
    private String id;
    @Value("${board.content}")
    private String content;
    @Value("${board.color}")
    private String color;

    public Root() throws IOException {
        super();
        setId(id);
        setContent(content);
        setColor(color);
    }

    public Root(String id, String content, List<Node> children) throws IOException {
        super(id, content, children);
    }

    public void removeAll() {
        this.setChildren(null);
    }

}
