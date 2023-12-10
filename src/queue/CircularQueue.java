package queue;

import queue.Exceptions.EmptyQueueException;

@SuppressWarnings("unchecked")

public class CircularQueue <T> {
    T[] data;
    int size;
    int front = -1;
    int rear = -1;
    public CircularQueue(int size) {
        data = (T[]) new Object[size];
        this.size = size;
    }

    public void enqueue(T value) {
        if (front == -1) {
            front = rear = 0;
            data[rear] = value;
        } else {
            rear = (rear + 1) % size;
            data[rear] = value;
        }
    }

    public T dequeue() {
        T deletedValue = null;
        try {
            if (isEmpty()) {
                throw new EmptyQueueException();
            } else {
                deletedValue = data[front++];
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } 
        return deletedValue;
    }

    public void print() {
        for (int i = 0; i < size; i++) {
            System.out.println(data[i]);
        }
    }

    public boolean isEmpty() {
        return front == -1;
    }
}
