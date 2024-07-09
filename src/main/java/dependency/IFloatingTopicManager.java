package dependency;

import content.Topic;

public interface IFloatingTopicManager {
    void removeTopic(String topicId);
    Topic createNewFloatingTopic();
}
