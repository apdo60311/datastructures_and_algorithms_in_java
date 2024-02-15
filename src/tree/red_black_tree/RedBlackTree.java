package tree.red_black_tree;

import queue.Queue;
import tree.INode;
import tree.ITree;
import tree.TreeTraverseType;
import tree.avl_tree.AvlNode;

public class RedBlackTree<T extends Comparable<T>> implements ITree<T> {

    private RBNode<T> root;

    @Override
    public boolean isEmpty() { return root == null; }

    @Override
    public T getMin() throws Exception {
        if (isEmpty()) throw new Exception("Empty tree exception");
        return getMin(root);
    }

    private T getMin(RBNode<T> node) {
        if (node.left != null) {
            node = node.left;
        }
        return node.value;
    }

    private RBNode<T> getMinNode(RBNode<T> node) {
        while (node.left != null) {
            node = node.left;

        }
        return node;
    }

    @Override
    public T getMax() throws Exception {
        if (isEmpty()) throw new Exception("Empty tree exception");
        return getMax(root);
    }

    private T getMax(RBNode<T> node) {
        if (node.right != null) {
            node = node.right;
        }
        return node.value;
    }
    public void traverse(TreeTraverseType treeTraverseType) {
        switch (treeTraverseType) {
            case Preorder -> preorder(root);
            case Inorder -> inorder(root);
            case Postorder -> postorder(root);
            case BFT -> breadthFirstTraversal(root);
        }
    }

    private void preorder(RBNode<T> node) {
        if (node != null) {
            System.out.println(node.value);
            preorder(node.left);
            preorder(node.right);
        }
    }
    private void postorder(RBNode<T> node) {
        if (node != null) {
            postorder(node.left);
            postorder(node.right);
            System.out.println(node.value);
        }
    }
    private void inorder(RBNode<T> node) {
        if (node != null) {
            inorder(node.left);
            System.out.println(node.value);
            inorder(node.right);
        }
    }
    private void breadthFirstTraversal(RBNode<T> node) {
        Queue<AvlNode<T>> dftQueue = new Queue<>();

    }

    @Override
    public ITree<T> insert(T value) {
        RBNode<T> nodeToInsert = new RBNode<>(value);
        root = insert(root,nodeToInsert);
        coloringAndRotation(nodeToInsert);
        return this;
    }

    private void coloringAndRotation(RBNode<T> node) {
        RBNode<T> parent = node.parent;
        if (node != root && parent.color == NodeColor.RED) {
            RBNode<T> grandParent = node.getGrandParent();
            RBNode<T> uncle = node.getUncle();
            
            if (uncle != null && uncle.color == NodeColor.RED) {
                handleRecoloring(parent , uncle , grandParent);
            } else if (parent.isLeftChild()) {
                // left-heavy or left-right
                handleLeftSituation(node , parent , grandParent);
            } else if (parent.isRightChild()) {
                // right-heavy or right-left
                handleRightSituation(node , parent , grandParent);
            }
        }
        root.color = NodeColor.BLACK;
    }

    private void handleLeftSituation(RBNode<T> node , RBNode<T> parent , RBNode<T> grandParent) {

        boolean isLeftChild = node.isLeftChild();

        if (!isLeftChild) {
            leftRotate(parent);
        }
        parent.flipColor();
        grandParent.flipColor();

        rightRotate(grandParent);
        if (isLeftChild) {
            coloringAndRotation(parent);
        } else {
            coloringAndRotation(grandParent);
        }
    }
    private void handleRightSituation(RBNode<T> node , RBNode<T> parent , RBNode<T> grandParent) {
        boolean isRightChild = node.isRightChild();

        if (!isRightChild) {
            rightRotate(parent);
        }

        parent.flipColor();
        grandParent.flipColor();

        leftRotate(grandParent);
        if (isRightChild) {
            coloringAndRotation(parent);
        } else {
            coloringAndRotation(grandParent);
        }
    }

    private void handleRecoloring(RBNode<T> parent , RBNode<T> uncle , RBNode<T> grandParent) {
        parent.flipColor();
        uncle.flipColor();
        grandParent.flipColor();

        coloringAndRotation(grandParent);
    }
    private RBNode<T> insert(RBNode<T> node , RBNode<T> nodeToInsert) {
        if (node == null) {
            return nodeToInsert;
        }
        if (nodeToInsert.value.compareTo(node.value) > 0) {
            node.right = insert(node.right , nodeToInsert);
            updateNodeParent(node.right , node);
        } else {
            node.left = insert(node.left , nodeToInsert);
            updateNodeParent(node.left , node);
        }
        return node;
    }
    private void leftRotate(RBNode<T> node) {
        RBNode<T> rightNode = node.right;
        RBNode<T> centerNode = rightNode.left;

        rightNode.left = node;

        node.right = centerNode;
        if (centerNode != null) {
            centerNode.parent = node;
        }

        rightNode.parent = node.parent;
        updateParentNodeChild(node,rightNode);
        node.parent = rightNode;
    }
    private void rightRotate(RBNode<T> node) {
        RBNode<T> leftNode = node.left;
        RBNode<T> centerNode = leftNode.right;

        leftNode.right = node;

        node.left = centerNode;
        if (centerNode != null) {
            centerNode.parent = node;
        }

        leftNode.parent = node.parent;
        updateParentNodeChild(node,leftNode);
        node.parent = leftNode;

    }
    private void updateParentNodeChild(RBNode<T> node , RBNode<T> newChild) {
        RBNode<T> parent = node.parent;

        if (parent == null) {
            root = newChild;
        } else if (node.isLeftChild()) {
            parent.left = newChild;
        } else if(node.isRightChild()) {
            parent.right = newChild;
        }

    }
    private void updateNodeParent(RBNode<T> node , RBNode<T> parent) {
        node.parent = parent;
    }
    @Override
    public ITree<T> delete(T value) {
        if (isEmpty()) return null;
        RBNode<T> nodeToDelete = (RBNode<T>) find(value);
        if (nodeToDelete == null) return this;
        NodeColor originColor = nodeToDelete.color;

        RBNode<T> x , y = nodeToDelete;

        if (nodeToDelete.left == null) {
            x = nodeToDelete.right;
            transplant(nodeToDelete , x);
        } else if (nodeToDelete.right == null) {
            x = nodeToDelete.left;
            transplant(nodeToDelete , x);
        } else {
            y = getMinNode(nodeToDelete.right);
            originColor = y.color;
            x = y.right;

            if (y.parent == nodeToDelete) {
                if (x != null) x.parent = y;
            } else {
                transplant(y, y.right);
                y.right = nodeToDelete.right;
                y.right.parent = y;
            }
            transplant(nodeToDelete, y);
            y.left = nodeToDelete.left;
            y.left.parent = y;
            y.color = nodeToDelete.color;
        }

        if (originColor == NodeColor.BLACK) { fixDelete(x); }
        return this;
    }

    void transplant(RBNode<T> node , RBNode<T> nodeChild) {
        if (node.parent == null) {
            root = nodeChild;
        } else if (node.isLeftChild()) {
            node.parent.left = nodeChild;
        } else {
            node.parent.right = nodeChild;
        }
        if (nodeChild != null) {
            nodeChild.parent = node.parent;
        }
    }
    private void fixDelete(RBNode<T> node) {
        while (node != root && node.color == NodeColor.BLACK) {
            RBNode<T> nodeSibling = node.getSibling();
            RBNode<T> nodeSiblingLeftChild = (nodeSibling != null) ? nodeSibling.left : null;
            RBNode<T> nodeSiblingRightChild = (nodeSibling != null) ? nodeSibling.right : null;

            if (node.isLeftChild()) {
                node = fixLeftDelete(node, nodeSibling, nodeSiblingRightChild, nodeSiblingLeftChild);
            } else {
                node = fixRightDelete(node, nodeSibling, nodeSiblingLeftChild, nodeSiblingRightChild);
            }

            node.color = NodeColor.BLACK;
        }
    }

    private RBNode<T> fixRightDelete(RBNode<T> node, RBNode<T> nodeSibling, RBNode<T> nodeSiblingLeftChild, RBNode<T> nodeSiblingRightChild) {
        // case 1
        if (nodeSibling != null && nodeSibling.color == NodeColor.RED) {
            nodeSibling.color = NodeColor.BLACK;
            node.parent.color = NodeColor.RED;
            rightRotate(node.parent);
            nodeSibling = node.parent.left;
        }
        // case 2
        if ((node.right == null || node.left == null) || node.right.color == NodeColor.BLACK && node.left.color == NodeColor.BLACK) {
            node.color = NodeColor.RED;
            node = node.parent;
        }
        else {
            // case 3
            if (nodeSibling != null && nodeSiblingLeftChild != null && nodeSiblingLeftChild.color == NodeColor.BLACK) {
                nodeSiblingRightChild.color = NodeColor.BLACK;
                nodeSibling.color = NodeColor.RED;
                leftRotate(nodeSibling);
                nodeSibling = node.parent.left;
            }
            // case 4
            if (nodeSibling != null) { nodeSibling.color = node.parent.color; }
            if (node.parent != null) { node.parent.color = NodeColor.BLACK; }
            if (nodeSiblingLeftChild != null) { nodeSiblingLeftChild.color = NodeColor.BLACK; }
            if (node.parent != null) { rightRotate(node.parent); }
            node = root;
        }
        return node;
    }

    private RBNode<T> fixLeftDelete(RBNode<T> node, RBNode<T> nodeSibling, RBNode<T> nodeSiblingRightChild, RBNode<T> nodeSiblingLeftChild) {
        // case 1
        if (nodeSibling != null && nodeSibling.color == NodeColor.RED) {
            nodeSibling.color = NodeColor.BLACK;
            node.parent.color = NodeColor.RED;
            leftRotate(node.parent);
            nodeSibling = node.parent.right;
        }
        // case 2
        if (node.left.color == NodeColor.BLACK && node.right.color == NodeColor.BLACK) {
            node.color = NodeColor.RED;
            node = node.parent;
        }
        else {
            // case 3
            if (nodeSibling != null && nodeSiblingRightChild.color == NodeColor.BLACK) {
                nodeSiblingLeftChild.color = NodeColor.BLACK;
                nodeSibling.color = NodeColor.RED;
                rightRotate(nodeSibling);
                nodeSibling = node.parent.right;
            }
            // case 4
            if (nodeSibling != null) {nodeSibling.color = node.parent.color;}
            node.parent.color = NodeColor.BLACK;
            nodeSiblingRightChild.color = NodeColor.BLACK;
            leftRotate(node.parent);
            node = root;
        }
        return node;
    }
    @Override
    public INode<T> find(T value) {
        return findHelper(root , value);
    }
    private INode<T> findHelper(INode<T> node , T value) {
        if (node == null) return null;
        if (value.compareTo(((RBNode<T>)node).value) > 0) {
            return findHelper(((RBNode<T>)node).right , value);
        } else if (value.compareTo(((RBNode<T>)node).value) < 0) {
            return findHelper(((RBNode<T>)node).left , value);
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
        return Math.max(1 + getHeight(((RBNode<T>)node).left) , 1 + getHeight(((RBNode<T>)node).right));
    }

    public RBNode<T> getRoot() { return root; }
}
