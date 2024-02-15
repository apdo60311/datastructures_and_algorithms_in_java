package tree.splay_tree;

import queue.Queue;
import tree.INode;
import tree.ITree;
import tree.TreeTraverseType;
import tree.binary_search_tree.BstNode;
import tree.red_black_tree.RBNode;

public class SplayTree <T extends Comparable<T>> implements ITree<T> {
    private STNode<T> root;

    @Override
    public boolean isEmpty() {
        return root == null;
    }

    @Override
    public T getMin() throws Exception {
        if (isEmpty()) throw new Exception("Empty tree exception");
        return getMin(root);
    }

    private T getMin(STNode<T> node) {
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

    private T getMax(STNode<T> node) {
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

    private void preorder(STNode<T> node) {
        if (node != null) {
            System.out.println(node.value);
            preorder(node.left);
            preorder(node.right);
        }
    }
    private void postorder(STNode<T> node) {
        if (node != null) {
            postorder(node.left);
            postorder(node.right);
            System.out.println(node.value);
        }
    }
    private void inorder(STNode<T> node) {
        if (node != null) {
            inorder(node.left);
            System.out.println(node.value);
            inorder(node.right);
        }
    }

    private void breadthFirstTraversal(STNode<T> node) {
        Queue<BstNode<T>> dftQueue = new Queue<>();
        //TODO
    }
    @Override
    public ITree<T> insert(T value) {
        STNode<T> newNode = new STNode<>(value);
        root = insert(root,newNode);
        splay(newNode);
        return this;
    }

    private void splay(STNode<T> node) {
        while (node.parent != null) {
            if (node.getGrandParent() == null) {
                // case 1: root is node parent
                if (node.isLeftChild()) {
                    // zig
                    rightRotate(node.parent);
                } else {
                    // zag
                    leftRotate(node.parent);
                }
                // case 2: node is left child and parent is left child (Zig - Zig)
            } else if (node.isLeftChild() && node.parent.isLeftChild()) {
                rightRotate(node.getGrandParent());
                rightRotate(node.parent);
                // case 3: node is right child and parent is right child (Zag - Zag)
            } else if (node.isRightChild() && node.parent.isRightChild()) {
                leftRotate(node.getGrandParent());
                leftRotate(node.parent);
                // case 4: node is right child and parent is left child (Zig - Zag)
            } else if (node.isRightChild() && node.parent.isLeftChild()) {
                leftRotate(node.parent);
                rightRotate(node.parent);
                // case 5: node is right child and parent is right child (Zag - Zig)
            } else {
                rightRotate(node.parent);
                leftRotate(node.parent);
            }
        }
    }

    private void leftRotate(STNode<T> node) {
        STNode<T> rightNode = node.right;
        STNode<T> centerNode = rightNode.left;

        rightNode.left = node;

        node.right = centerNode;
        if (centerNode != null) {
            centerNode.parent = node;
        }

        rightNode.parent = node.parent;
        updateParentNodeChild(node,rightNode);
        node.parent = rightNode;
    }
    private void rightRotate(STNode<T> node) {
        STNode<T> leftNode = node.left;
        STNode<T> centerNode = leftNode.right;

        leftNode.right = node;

        node.left = centerNode;
        if (centerNode != null) {
            centerNode.parent = node;
        }

        leftNode.parent = node.parent;
        updateParentNodeChild(node,leftNode);
        node.parent = leftNode;

    }

    private void updateParentNodeChild(STNode<T> node , STNode<T> newChild) {
        STNode<T> parent = node.parent;

        if (parent == null) {
            root = newChild;
        } else if (node.isLeftChild()) {
            parent.left = newChild;
        } else if(node.isRightChild()) {
            parent.right = newChild;
        }

    }
    private void updateNodeParent(STNode<T> node , STNode<T> parent) {
        node.parent = parent;
    }


    STNode<T> insert(STNode<T> node , STNode<T> newNode) {
        if (node == null) {
            return newNode;
        }
        if (newNode.value.compareTo(node.value) > 0) {
            node.right = insert(node.right , newNode);
            updateNodeParent(node.right , node);
        } else {
            node.left = insert(node.left , newNode);
            updateNodeParent(node.left , node);
        }
        return node;
    }
    @Override
    public INode<T> find(T value) {
        STNode<T> node = (STNode<T>) findHelper(root , value);
        splay(node);
        return node;
    }
    private INode<T> findHelper(INode<T> node , T value) {
        if (node == null) return null;
        if (value.compareTo(((STNode<T>)node).value) > 0) {
            return findHelper(((STNode<T>)node).right , value);
        } else if (value.compareTo(((STNode<T>)node).value) < 0) {
            return findHelper(((STNode<T>)node).left , value);
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
        return Math.max(1 + getHeight(((STNode<T>)node).left) , 1 + getHeight(((STNode<T>)node).right));
    }

    @Override
    public ITree<T> delete(T value) {
        if (isEmpty()) return null;
        STNode<T> nodeToDelete = (STNode<T>)find(value);
        root = delete(root,value);
        // We can do splay on delete also
        return this;
    }

    private STNode<T> delete(STNode<T> node , T value) {

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
}
