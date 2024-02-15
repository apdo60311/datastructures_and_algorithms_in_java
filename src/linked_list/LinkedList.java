package linked_list;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedList<T extends Comparable<? super T>> implements Iterable<T> {

    private Node<T> head;
    private Node<T> tail;
    private int size;

    public LinkedList() {
        head = null;
        tail = null;
        size = 0;
    }

    public LinkedList<T> insertAtHead(T value) {
        Node<T> newNode = new Node<T>();
        newNode.value = value;
        if (head == null) {
            tail = newNode;
        } else {
            head.prev = newNode;
            newNode.next = head;
        }
        head = newNode;
        size++;
        return this;
    }

    public LinkedList<T> insertAtTail(T value) {
        Node<T> newNode = new Node<T>();
        newNode.value = value;
        if (tail == null) {
            head = newNode;
        } else {
            newNode.prev = tail;
            tail.next = newNode;
        }
        tail = newNode;
        size++;
        return this;
    }

    public LinkedList<T> deleteAtHead() throws EmptyListException {
        if (head == null) {
            throw new EmptyListException();
        } else if (head == tail) {
            head = tail = null;
        } else {
            head = head.next;
            head.prev = null;
        }
        size--;
        return this;
    }

    public LinkedList<T> deleteAtTail() throws EmptyListException {
        if (tail == null) {
            throw new EmptyListException();
        } else if (head == tail) {
            head = tail = null;
        } else {
            tail = tail.prev;
            tail.next = null;
        }
        size--;
        return this;
    }

    public LinkedList<T> deleteNode(T value) throws EmptyListException {
        if (head == tail) {
            if (head.value == value) {
                head = tail = null;
                size--;
            } else {
                throw new NoSuchElementException("Element you Search for is not available");
            }
        } else {
            if (head.value == value) {
                deleteAtHead();
            } else if (tail.value == value) {
                deleteAtTail();
            } else {
                Node<T> ptr = head;
                while (ptr.next.value != value) {
                    ptr = ptr.next;
                }
                ptr.next = ptr.next.next;
                ptr.next.next.prev = ptr;
                size--;
            }
        }
        return this;
    }

    public void reverse() {
    }

    public void sort() {
        
    }

    public void swap(Node<T> A , Node<T> B) {
        T temp = A.value;
        A.value = B.value;
        B.value = temp;
    }

    public LinkedList<T> insertSorted(T value) {
        if (head == null) {
            insertAtTail(value);
            return this;
        }

        if (value.compareTo(head.value) == -1) {
            insertAtHead(value);
            return this;
        } 
        if (value.compareTo(tail.value) == 1) {
            insertAtTail(value);
            return this;
        }

        Node<T> ptr = head;

        while (ptr.next != null && ptr.next.value.compareTo(value) != 1) {
            ptr = ptr.next;
        }
        Node<T> newNode = new Node<T>();
        newNode.value = value;
        newNode.next = ptr.next;
        ptr.next = newNode;
        // 10 20 30 40 50 60
        return this;
    }
    // [-12] [5] [9] [10] [15] [20] [40]

    
    public ArrayList<T> toList() {
        ArrayList<T> list = new ArrayList<T>();

        Node<T> ptr = head;
        while (ptr != null) {
            list.add(ptr.value);
            ptr = ptr.next;
        }
        return list;
    }

    public LinkedList<T> clone() {
        LinkedList<T> newList = new LinkedList<T>();
        Node<T> ptr = head;
        while (ptr != null) {
            newList.insertAtTail(ptr.value);
            ptr = ptr.next;
        }
        return newList;
    }

    public T At(int index) {
        if (index < 0) { throw new IndexOutOfBoundsException("index Cannot be negative"); }
        if (index == 0) { return head.value; }
        if (index == size - 1) { return tail.value; }
        if (index >= size) { throw new IndexOutOfBoundsException(); }
        
        T value;
        Node<T> ptr = head;
        int i = 0;

        while (i != index && ptr != null) {
            ptr = ptr.next;
            i++;
        }
        if (ptr != null) {
            value = ptr.value;
        } else {
            throw new NoSuchElementException();            
        }

        return value;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((head == null) ? 0 : head.hashCode());
        result = prime * result + ((tail == null) ? 0 : tail.hashCode());
        result = prime * result + size;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        final LinkedList<T> otherlist = LinkedList.class.cast(obj);

        if (this.size != otherlist.getSize() || !this.head.value.equals(otherlist.getHead().value)
                || !this.tail.value.equals(otherlist.getTail().value)) {
            return false;
        }

        Node<T> otherListPtr = otherlist.getHead().next;
        Node<T> thisListPtr = this.head.next;

        while ((otherListPtr != null || thisListPtr != null) && otherListPtr.value.equals(thisListPtr.value)) {
            otherListPtr = otherListPtr.next;
            thisListPtr = thisListPtr.next;
        }

        return otherListPtr == null && thisListPtr == null;
    }

    @Override
    public String toString() {
        String str = "";

        Node<T> ptr = head;
        while (ptr != null) {
            str += "[" + ptr.value.toString() + "]" + " -> ";
            ptr = ptr.next;
        }

        str += "NULL";

        return str;
    }

    public Node<T> getHead() {
        return head;
    }

    public Node<T> getTail() {
        return tail;
    }

    public int getSize() {
        return size;
    }

    class LinkedListIterator implements Iterator<T> {
            private Node<T> iteratorPointer = head;

            @Override
            public boolean hasNext() {
                return iteratorPointer != null;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }

                T value = iteratorPointer.value;
                iteratorPointer = iteratorPointer.next;
                return value;
            }

        };

    @Override
    public Iterator<T> iterator() {
        return new LinkedListIterator();
    }

}

final class Node<T> {
    public T value;
    public Node<T> next;
    public Node<T> prev;
}
