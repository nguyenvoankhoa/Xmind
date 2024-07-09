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
        floatingTopicList = new ArrayList<>();
    }

    public Topic createNewFloatingTopic() {
        Topic topic = new Topic();
        floatingTopicList.add(topic);
        return topic;
    }

    public void removeTopic(String topicId) {
        this.floatingTopicList.removeIf(t -> t.getId().equals(topicId));
    }
}
