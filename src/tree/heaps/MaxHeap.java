package tree.heaps;

public class MaxHeap<T extends Comparable<T>> extends Heap<T> {
    @Override
    protected void heapifyUp() {
        int index = position;
        int parentIndex = parent(index);

        while (parentIndex >=0 && heap[index].compareTo(heap[parentIndex]) > 0) {
            swap(index , parentIndex);
            index = parentIndex;
            parentIndex = parent(index);
        }
    }
    @Override
    protected void heapifyDown() throws Exception {
        if (position == -1) throw new Exception("Empty Heap");
        int index = 0;
        int size = position + 1;

        while (index < position) {
            int leftChild = leftChildIndex(index);
            int rightChild = rightChildIndex(index);
            int largestIndex = index;

            if (leftChild < size && heap[leftChild].compareTo(heap[largestIndex]) > 0) {
                largestIndex = leftChild;
            }
            if (rightChild < size && heap[rightChild].compareTo(heap[largestIndex]) > 0) {
                largestIndex = rightChild;
            }

            if (largestIndex != index) {
                swap(index , largestIndex);
                index = largestIndex;
            } else {
                break;
            }
        }
    }
    public T getMax() throws Exception {
        return getRoot();
    }
    public T extractMax() throws Exception {
        if (isEmpty()) throw new Exception();

        T result = heap[0];
        heap[0] = heap[position--];
        heap[position + 1] = null;
        heapifyDown();
        return result;
    }


    @Override
    public void sort() {

    }
}
