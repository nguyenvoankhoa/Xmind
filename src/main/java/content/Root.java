package content;

import java.io.IOException;
import java.util.List;

public class Root extends Node {


    public Root() throws IOException {
        super();
    }

    public Root(String id, String content, List<Node> children) throws IOException {
        super(id, content, children);
    }

    public void removeAll() {
        this.setChildren(null);
    }

}
