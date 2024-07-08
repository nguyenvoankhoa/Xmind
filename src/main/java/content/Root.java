package content;

import org.springframework.beans.factory.annotation.Value;

import java.util.List;

public class Root extends Node {
    @Value("${board.id}")
    private String id;
    @Value("${board.content}")
    private String content;
    @Value("${board.color}")
    private String color;
    public Root() {
        super();
        this.id = 
    }

    public Root(String id, String content, List<Node> children) {
        super(id, content, children);
    }

    public void removeAll() {
        this.setChildren(null);
    }

    public boolean checkInRange(Position position, Position parentPosition) {
        return position.getX() <= parentPosition.getX() + 1 && position.getX() >= parentPosition.getX() - 1
                && position.getY() <= parentPosition.getY() + 1 && position.getY() >= parentPosition.getY() - 1;
    }

    public Node findNodeInRange(Position position) {
        if (checkInRange(position, getPosition())) {
            return this;
        }
        return this.getChildren().stream().filter(node -> checkInRange(position, node.getPosition())).findFirst().orElse(null);
    }
}
