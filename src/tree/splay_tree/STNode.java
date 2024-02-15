package tree.splay_tree;

import tree.INode;
import tree.red_black_tree.NodeColor;

import java.util.Objects;

public class STNode <T extends Comparable<T>> implements INode<T> {
    T value;
    STNode<T> left;
    STNode<T> right;
    STNode<T> parent;
    public STNode(T value) {
        this.value = value;
    }

    STNode<T> getGrandParent() {
        if (parent != null) return parent.parent;
        else return null;
    }
    boolean isLeftChild() {
        if (parent != null) {
            return parent.left == this;
        }else {
            return false;
        }
    }
    boolean isRightChild() {
        if (parent != null) {
            return parent.right == this;
        }else {
            return false;
        }
    }
}
