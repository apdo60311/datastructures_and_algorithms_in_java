package tree.red_black_tree;

import queue.entities.Pair;
import tree.INode;

import java.util.Objects;

public class RBNode <T extends Comparable<T>> implements INode<T> {
    T value;
    RBNode<T> left;
    RBNode<T> right;
    NodeColor color = NodeColor.RED;
    RBNode<T> parent;
    public RBNode(T value) {
        this.value = value;
    }
    public RBNode(T value , NodeColor color) {
        this.value = value;
        this.color = color;
    }
    void flipColor() {
        if (color == NodeColor.RED) {
            color = NodeColor.BLACK;
        } else {
            color = NodeColor.RED;
        }
    }
    RBNode<T> getGrandParent() {
     if (parent != null) return parent.parent;
     else return null;
    }
    RBNode<T> getUncle() {
        RBNode<T> grandParent = getGrandParent();
        if (grandParent != null) {
            return (grandParent.left == parent) ? grandParent.right : grandParent.left;
        }
        else {
            return null;
        }
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
    RBNode<T> getSibling() {
        if (parent == null) return null;
        if (isLeftChild()) return parent.right;
        else return parent.left;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RBNode<?> rbNode = (RBNode<?>) o;
        return Objects.equals(value, rbNode.value) && Objects.equals(left, rbNode.left) && Objects.equals(right, rbNode.right) && color == rbNode.color && Objects.equals(parent, rbNode.parent);
    }
    @Override
    public int hashCode() {
        return Objects.hash(value, left, right, color, parent);
    }
    @Override
    public String toString() {
        String node = "RBNode{ " +
                "value = " + value;

            if (left != null) {
                node += ", left = " + left.value;
            } else {
                node += ", left = null";
            }
            if (right != null) {
                node += ", right = " + right.value;
            } else {
                node += ", right = null";
            }

            if (parent != null) {
                node += ", parent = " + parent.value;
            } else {
                node += ", parent = null";
            }

       node +=  ", color = " + color +
                '}';


        return node;
    }
}
