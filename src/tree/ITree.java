package tree;

public interface ITree<T extends Comparable<T>> {
    boolean isEmpty();
    T getMin() throws Exception;
    T getMax() throws Exception;
    void traverse(TreeTraverseType treeTraverseType);
    ITree<T> insert(T value);
    ITree<T> delete(T value);
    INode<T> find(T value);
    boolean contains(T value);

    int getHeight();
    int getHeight(T value);
}
