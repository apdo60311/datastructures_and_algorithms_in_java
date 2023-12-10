package queue.entities;



public class QueueItem <T> implements Item<T> {
    private T value;
    private int priority;

    public QueueItem() {
    }

    public QueueItem(T value, int priority) {
        this.value = value;
        this.priority = priority;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    @Override
    public String toString() {
        return "value = " + value + ", priority = " + priority + "";
    }

}
