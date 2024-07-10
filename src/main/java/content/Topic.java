package content;

import dependency.IFloatingTopicManager;
import dependency.IPropertyLoader;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sheet.Sheet;

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

    public void changeParent(Node newParent) {
        newParent.addChild(this);
        this.parent.removeChild(this);
    }

    public void changeToFloat(IFloatingTopicManager floatMng) {
        this.setParent(null);
        setFloating(true);
        floatMng.addFloatTopic(this);
    }

}
