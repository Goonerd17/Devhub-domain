package goonerd.devhub.common.component;

import goonerd.devhub.common.event.GenericEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class EventPublisher {

    private final ApplicationEventPublisher publisher;

    public EventPublisher(ApplicationEventPublisher publisher) {
        this.publisher = publisher;
    }

    public <T> void publish(String type, T payload) {
        publisher.publishEvent(new GenericEvent<>(type, payload));
    }
}
