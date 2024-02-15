package tree.heaps;

public interface IHeap <T extends  Comparable<T>> {
    IHeap<T> insert(T value);

    int parent(int nodeIndex);
    int leftChildIndex(int nodeIndex);
    int rightChildIndex(int nodeIndex);
    int getSize();
    void swap(int firstNodeIndex , int secondNodeIndex);
    void sort();
}
