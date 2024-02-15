package tree.binary_search_tree;

import queue.Queue;
import tree.INode;
import tree.ITree;
import tree.TreeTraverseType;

public class BinarySearchTree<T extends Comparable<T>>  implements ITree<T> {
    private BstNode<T> root;

    @Override
    public boolean isEmpty() {
        return root == null;
    }

    @Override
    public T getMin() throws Exception {
         if (isEmpty()) throw new Exception("Empty tree exception");
        return getMin(root);
    }

    private T getMin(BstNode<T> node) {
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

    private T getMax(BstNode<T> node) {
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

    private void preorder(BstNode<T> node) {
        if (node != null) {
            System.out.println(node.value);
            preorder(node.left);
            preorder(node.right);
        }
    }
    private void postorder(BstNode<T> node) {
        if (node != null) {
            postorder(node.left);
            postorder(node.right);
            System.out.println(node.value);
        }
    }
    private void inorder(BstNode<T> node) {
        if (node != null) {
            inorder(node.left);
            System.out.println(node.value);
            inorder(node.right);
        }
    }

    private void breadthFirstTraversal(BstNode<T> node) {
        Queue<BstNode<T>> dftQueue = new Queue<>();
        //TODO
    }
    @Override
    public ITree<T> insert(T value) {

        root = insert(root,value);

        return this;
    }

    BstNode<T> insert(BstNode<T> node , T value) {
        if (node == null) {
            return new BstNode<>(value);
        }
        if (value.compareTo(node.value) > 0) {
            node.right = insert(node.right , value);
        } else {
            node.left = insert(node.left , value);
        }
        return node;
    }
    @Override
    public INode<T> find(T value) {
        return findHelper(root , value);
    }
    private INode<T> findHelper(INode<T> node , T value) {
        if (node == null) return null;
        if (value.compareTo(((BstNode<T>)node).value) > 0) {
            return findHelper(((BstNode<T>)node).right , value);
        } else if (value.compareTo(((BstNode<T>)node).value) < 0) {
            return findHelper(((BstNode<T>)node).left , value);
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
        return Math.max(1 + getHeight(((BstNode<T>)node).left) , 1 + getHeight(((BstNode<T>)node).right));
    }

    @Override
    public ITree<T> delete(T value) {
        if (isEmpty()) return null;
        BstNode<T> nodeToDelete = (BstNode<T>)find(value);
        root = delete(root,value);
        return this;
    }

    private BstNode<T> delete(BstNode<T> node , T value) {

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
        return node;
    }

    public void visualize() {
        visualizeHelper(root , 0);
    }

    private void visualizeHelper(BstNode<T> node , int level) {
        if (node != null) {
            printNodes(node , level);
            visualizeHelper(node.left , level + 1);
            visualizeHelper(node.right , level + 1);
        }
    }

    private void printNodes(BstNode<T> node , int level) {
        int numberOfNodesPerLevel = (int) Math.pow(2 , level);
        int nodeWidth = 4;

        for (int i = 0; i < numberOfNodesPerLevel; i++) {
            String spaces = " ".repeat(16 - numberOfNodesPerLevel * nodeWidth);
            System.out.print(spaces+"("+ node.value +")");
        }
        System.out.println();
    }
}
