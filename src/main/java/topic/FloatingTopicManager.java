package topic;

import content.Topic;
import dependency.IFloatingTopicManager;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class FloatingTopicManager implements IFloatingTopicManager {
    private List<Topic> floatingTopicList;

    public FloatingTopicManager() {
        this.floatingTopicList = new ArrayList<>();
    }

    public Topic createNewFloatingTopic() {
        Topic topic = new Topic();
        this.floatingTopicList.add(topic);
        return topic;
    }

    public List<Topic> addFloatTopic(Topic t){
        this.floatingTopicList.add(t);
        return this.floatingTopicList;
    }

    public void removeTopic(String topicId) {
        this.floatingTopicList.removeIf(t -> t.getId().equals(topicId));
    }
}
