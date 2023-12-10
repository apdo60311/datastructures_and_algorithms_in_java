package queue.entities;

public class NullItem<T> implements Item<T> {
    private Object value = null;
    private Object priority = null;

    public NullItem() {
    }

    public Object getValue() {
        return value;
    }

    public Object getPriority() {
        return priority;
    }

    @Override
    public String toString() {
        return "No Item";
    }

}
