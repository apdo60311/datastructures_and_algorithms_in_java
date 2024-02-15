package tree.heaps;

import java.util.Arrays;

public abstract class Heap<T extends Comparable<T>> implements IHeap<T> {
    protected T[] heap;
    protected int position = -1;

    public Heap() {
        int initialCapacity = 2;
        heap = (T[]) new Comparable[initialCapacity];
    }


    @Override
    public int leftChildIndex(int nodeIndex) {
        return 2 * nodeIndex + 1;
    }

    @Override
    public int rightChildIndex(int nodeIndex) {
        return 2 * nodeIndex + 2;
    }

    @Override
    public int parent(int nodeIndex) {
        return (nodeIndex - 1) / 2;
    }

    @Override
    public void swap(int firstNodeIndex, int secondNodeIndex) {
        T temp = heap[firstNodeIndex];
        heap[firstNodeIndex] = heap[secondNodeIndex];
        heap[secondNodeIndex] = temp;
    }

    @Override
    public IHeap<T> insert(T value) {
        if (isFull()) {
            resize(2 * heap.length);
        }
        heap[++position] = value;
        heapifyUp();
        return this;
    }

    protected abstract void heapifyUp();
    protected abstract void heapifyDown() throws Exception;

    private void resize(int newCapacity) {
        System.arraycopy(heap , 0 , heap = (T[]) new Comparable[newCapacity],0,position + 1);
    }

    @Override
    public int getSize() {
        return heap.length;
    }

    public T getRoot() throws Exception {
        if (isEmpty()) throw new Exception();
        return heap[0];
    }

    private boolean isFull() {
        return position == heap.length - 1;
    }
    protected boolean isEmpty() {
        return heap.length == 0;
    }
}
