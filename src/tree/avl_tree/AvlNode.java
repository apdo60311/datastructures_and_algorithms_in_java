package tree.avl_tree;


import tree.INode;

public class AvlNode<T extends Comparable<T>> implements INode<T> {
    T value;
    AvlNode<T> left;
    AvlNode<T> right;
    int height = 1;
    AvlNode(T value) {
        this.value = value;
    }
}
