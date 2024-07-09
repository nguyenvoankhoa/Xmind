package content;

import dependency.IPropertyLoader;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Optional;

@Getter
@Setter
@NoArgsConstructor
public class Topic extends Node {

    private Node parent;

    private boolean isFloating;

    public Topic(IPropertyLoader iPropertyLoader, String id, String content) {
        super(iPropertyLoader, id, content);
    }

    public void changeParent(String parentId, Root root) {
        Node newParent = root.findById(parentId);
        Optional.ofNullable(this.getParent())
                .ifPresent(p -> p.removeChild(this.getId()));
        Optional.ofNullable(newParent)
                .ifPresentOrElse(p -> p.addChild(this), () -> {
                    this.setFloating(true);
                    this.setParent(newParent);
                });
    }
}
