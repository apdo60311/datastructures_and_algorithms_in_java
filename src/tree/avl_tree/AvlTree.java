package tree.avl_tree;

import queue.Queue;
import tree.INode;
import tree.ITree;
import tree.TreeTraverseType;
import tree.binary_search_tree.BstNode;


public class AvlTree <T extends Comparable<T>> implements ITree<T> {
    private AvlNode<T> root;

    @Override
    public boolean isEmpty() {
        return root == null;
    }

    @Override
    public T getMin() throws Exception {
        if (isEmpty()) throw new Exception("Empty tree exception");
        return getMin(root);
    }

    private T getMin(AvlNode<T> node) {
        if (node.left != null) {
            node = node.left;
        }
        return node.value;
    }

    @Override
    public T getMax() throws Exception {
        if (isEmpty()) throw new Exception("Empty tree exception");
        return getMax(root);
    }

    private T getMax(AvlNode<T> node) {
        if (node.right != null) {
            node = node.right;
        }
        return node.value;
    }

    @Override
    public void traverse(TreeTraverseType treeTraverseType) {
        switch (treeTraverseType) {
            case Preorder -> preorder(root);
            case Inorder -> inorder(root);
            case Postorder -> postorder(root);
            case BFT -> breadthFirstTraversal(root);
        }
    }

    private void preorder(AvlNode<T> node) {
        if (node != null) {
            System.out.println(node.value);
            preorder(node.left);
            preorder(node.right);
        }
    }
    private void postorder(AvlNode<T> node) {
        if (node != null) {
            postorder(node.left);
            postorder(node.right);
            System.out.println(node.value);
        }
    }
    private void inorder(AvlNode<T> node) {
        if (node != null) {
            inorder(node.left);
            System.out.println(node.value);
            inorder(node.right);
        }
    }

    private void breadthFirstTraversal(AvlNode<T> node) {
        Queue<AvlNode<T>> dftQueue = new Queue<>();

    }
    @Override
    public ITree<T> insert(T value) {

        root = insert(root,value);

        return this;
    }

    AvlNode<T> insert(AvlNode<T> node , T value) {
        if (node == null) {
            return new AvlNode<>(value);
        }
        if (value.compareTo(node.value) > 0) {
            node.right = insert(node.right , value);
        } else {
            node.left = insert(node.left , value);
        }
        updateNodeHeight(node);
        return applyRotation(node);
    }

    private AvlNode<T> applyRotation(AvlNode<T> node) {
        int nodeBalance = balance(node);
        if (nodeBalance > 1) {
            int balanceOfLeftChild = balance(node.left);
            if (balanceOfLeftChild < 0) {
                node.left = leftRotate(node.left);
            }
            return rightRotate(node);
        }

        if (nodeBalance < -1) {
            int balanceOfRightChild = balance(node.right);
            if (balanceOfRightChild > 0) {
                node.right = rightRotate(node.right);
            }
            return leftRotate(node);
        }
        return node;
    }

    private AvlNode<T> leftRotate(AvlNode<T> node) {
        AvlNode<T> rightNode = node.right;
        AvlNode<T> centerNode = rightNode.left;
        rightNode.left = node;
        node.right = centerNode;

        updateNodeHeight(node);
        updateNodeHeight(rightNode);
        return rightNode;
    }

    private AvlNode<T> rightRotate(AvlNode<T> node) {
        AvlNode<T> leftNode = node.left;
        AvlNode<T> centerNode = leftNode.right;
        leftNode.right = node;
        node.left = centerNode;

        updateNodeHeight(node);
        updateNodeHeight(leftNode);
        return leftNode;
    }

    private void updateNodeHeight(AvlNode<T> node) {
        int leftNodeHeight = (node.left != null)? node.left.height : 0;
        int rightNodeHeight = (node.right != null)? node.right.height : 0;
        node.height = Math.max(leftNodeHeight , rightNodeHeight);
    }

    @Override
    public INode<T> find(T value) {
        return findHelper(root , value);
    }
    private INode<T> findHelper(INode<T> node , T value) {
        if (node == null) return null;
        if (value.compareTo(((AvlNode<T>)node).value) > 0) {
            return findHelper(((AvlNode<T>)node).right , value);
        } else if (value.compareTo(((AvlNode<T>)node).value) < 0) {
            return findHelper(((AvlNode<T>)node).left , value);
        }
        return node;
    }
    @Override
    public boolean contains(T value) {
        return find(value) != null;
    }

    @Override
    public int getHeight() {
        return getHeight(root);
    }

    @Override
    public int getHeight(T value) {
        return getHeight(find(value));
    }

    private int getHeight(INode<T> node) {
        if (node == null) return 0;
        return Math.max(1 + getHeight(((AvlNode<T>)node).left) , 1 + getHeight(((AvlNode<T>)node).right));
    }

    private int balance(AvlNode<T> node) {
        if (node == null) return 0;
        return getHeight(node.left) - getHeight(node.right);
    }
    @Override
    public ITree<T> delete(T value) {
        if (isEmpty()) return null;
        AvlNode<T> nodeToDelete = (AvlNode<T>) find(value);
        root = delete(root,value);
        return this;
    }

    private AvlNode<T> delete(AvlNode<T> node , T value) {

        if (value.compareTo(node.value) > 0) {
            node.right = delete(node.right , value);
        } else if (value.compareTo(node.value) < 0) {
            node.left = delete(node.left , value);
        } else {
            if (node.left == null) {
                return node.right;
            } else if (node.right == null) {
                return node.left;
            } else {
                node.value = getMax(node.left);
                node.left = delete(node.left , node.value);
            }
        }
        updateNodeHeight(node);
        return applyRotation(node);
    }

}
