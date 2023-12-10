package queue;

import queue.Exceptions.EmptyQueueException;
import queue.Exceptions.QueueException;
import queue.entities.Item;
import queue.entities.NullItem;
import queue.entities.Pair;
import queue.entities.QueueItem;

import java.util.ArrayList;

public class PriorityQueue<T> {
    private ArrayList<QueueItem<T>> priorityQueueItems;

    public PriorityQueue(int initialSize) {
        priorityQueueItems = new ArrayList<>(initialSize);
    }

    public PriorityQueue() {
        priorityQueueItems = new ArrayList<QueueItem<T>>();
    }

    public void enqueue(T value, int priority) {
        QueueItem<T> newItem = new QueueItem<T>(value, priority);
        priorityQueueItems.add(newItem);
    }

    private Pair<Integer, Item<T>> internalPeak() {

        int index = -1;
        int maxPriority = Integer.MIN_VALUE;

        QueueItem<T> item = new QueueItem<T>();

        for (int i = 0; i < getSize(); i++) {
            QueueItem<T> currrentItem = priorityQueueItems.get(i);
            if (currrentItem.getPriority() > maxPriority) {
                index = i;
                item = currrentItem;
                maxPriority = currrentItem.getPriority();
            }
        }
        return new Pair<Integer, Item<T>>(index, (Item<T>) item);
    }

    public int peekIndex() {
        return internalPeak().getLeft();
    }

    public Item<T> peekItem() {
        return internalPeak().getRight();
    }

    public void deQueueAll() {
        try {
            if (isEmpty()) {
                throw new EmptyQueueException();
            }
            while (getSize() != 0) {
                dequeue();
            }

        } catch (QueueException e) {
            System.out.println(e.getMessage());
        }
    }

    public void deQueueAllAndPrintValues() {
        while (getSize() != 0) {
            System.out.println(dequeue());
        }
    }

    public Item<T> dequeue() {
        Item<T> deletedElement = new NullItem<>();
        try {
            if (isEmpty()) {
                throw new EmptyQueueException();
            }
            int index = peekIndex();
            deletedElement = (Item<T>) priorityQueueItems.remove(index);
            return deletedElement;
            
        } catch (Exception e) {
            return deletedElement;
        }

    }

    public int getSize() {
        return priorityQueueItems.size();
    }

    public boolean isEmpty() {
        return priorityQueueItems.isEmpty();
    }

}

