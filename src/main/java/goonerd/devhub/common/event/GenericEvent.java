package goonerd.devhub.common.event;

public class GenericEvent<T> {

    private final String type;
    private final T payload;

    public GenericEvent(String type, T payload) {
        this.type = type;
        this.payload = payload;
    }

    public String getType() {
        return type;
    }

    public T getPayload() {
        return payload;
    }
}