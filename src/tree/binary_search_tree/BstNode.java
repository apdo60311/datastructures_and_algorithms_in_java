package tree.binary_search_tree;

import tree.INode;

public class BstNode<T extends Comparable<T>> implements INode<T> {
    T value;
    BstNode<T> left;
    BstNode<T> right;

    BstNode(T value) {
        this.value = value;
    }
}
