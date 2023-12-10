package queue;

import queue.Exceptions.EmptyQueueException;

import java.util.ArrayList;



public class Queue<T> {
    ArrayList<T> queue;

    public Queue() {
        queue = new ArrayList<>();
    }

    public void enqueue(T value) {
        queue.add(value);
    }

    public T peak() {
        try {
            if (isEmpty()) {
                throw new EmptyQueueException();
            }
            return queue.get(0);
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public T dequeue() {
        T value = null;

        try {
            if (isEmpty()) {
                throw new EmptyQueueException();
            } else {
                value = queue.remove(0); 

            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return value;
}

    public int getSize() {
        return queue.size();
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }
}
