package dependency;

import content.Topic;

import java.util.List;

public interface IFloatingTopicManager {
    void removeTopic(String topicId);
    Topic createNewFloatingTopic();
    List<Topic> addFloatTopic(Topic t);
}
