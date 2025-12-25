package goonerd.devhub.common.event;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class GenericEventListener {

    @EventListener
    public void handleGenericEvent(GenericEvent<?> event) {}
}