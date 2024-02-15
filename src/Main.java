import tree.binary_search_tree.BinarySearchTree;
import tree.TreeTraverseType;
import tree.heaps.MaxHeap;
import tree.heaps.MinHeap;
import tree.red_black_tree.RBNode;
import tree.red_black_tree.RedBlackTree;
import tree.splay_tree.STNode;
import tree.splay_tree.SplayTree;
import tree.trie.Trie;
import tree.trie.TrieNode;

public class Main {
    public static void main(String[] args) throws Exception {
//        ArrayList<Integer> list = new ArrayList<>();
//        list.add(113);
//        list.add(14);
//        list.add(12);
//        list.add(9);
//        list.add(2);
//        list.add(42);
//        list.add(68);
//        list.add(1123);
//        list.add(33);
//        list.add(91);

//        list.add(1);
//        list.add(2);
//        list.add(3);
//        list.add(4);
//        list.add(5);
//        list.add(6);
//        list.add(7);
//        list.add(8);
//        list.add(9);
//        list.add(10);

//        Sorter<Integer> sorter = new Sorter<>(list);
//        sorter.printList();
//        sorter.setQuickSortPartitionType(QuickSortPartitionType.LAST_AS_PIVOT_METHOD2).sort(SortType.QUICK_SORT);
//        sorter.printList();

//        Searcher<Integer> searcher = new Searcher<>(list);
//        System.out.println(searcher.search(SearchType.BINARY_SEARCH , 3));

        /*
                 (20)
           (10)        (30)
                    (25)   (35)

        * */
//
//        BinarySearchTree<Integer> binarySearchTree = new BinarySearchTree<>();
//        binarySearchTree.insert(20);
//        binarySearchTree.insert(30);
//        binarySearchTree.insert(10);
//        binarySearchTree.insert(35);
//        binarySearchTree.insert(25);
//        binarySearchTree.insert(45);
//        binarySearchTree.insert(26);
//        binarySearchTree.insert(47);
//
//        System.out.println(binarySearchTree.getHeight());
//
////        binarySearchTree.delete(25);
////        binarySearchTree.visualize();
//        binarySearchTree.traverse(TreeTraverseType.Preorder);
//
//        System.out.println(binarySearchTree.contains(20));

//        MinHeap<Integer> maxHeap = new MinHeap<>();
//        maxHeap.insert(300);
//        maxHeap.insert(100);
//        maxHeap.insert(200);
//        maxHeap.insert(50);
//        maxHeap.insert(500);
//
//        System.out.println(maxHeap.extractMin());
//        System.out.println(maxHeap.extractMin());
//        System.out.println(maxHeap.extractMin());

        /*
        *                          100 b
        *                    60 r           160 b
        *                 20 b    70 b      140 r    180 r
        *                   55 r
        * */

//        RedBlackTree<Integer> redBlackTree = new RedBlackTree<>();
//
//        redBlackTree.insert(7);
//        redBlackTree.insert(3);
//        redBlackTree.insert(18);
//        redBlackTree.insert(10);
//        redBlackTree.insert(22);
//        redBlackTree.insert(8);
//        redBlackTree.insert(11);
//        redBlackTree.insert(26);
//        redBlackTree.insert(2);
//        redBlackTree.insert(6);
//        redBlackTree.insert(13);
//
//        redBlackTree.delete(10);
//
//        redBlackTree.traverse(TreeTraverseType.Inorder);
//        redBlackTree.traverse(TreeTraverseType.BFT);
//
//        System.out.println("\nDeleting 18, 11, 3, 10, 22\n");
//
//        redBlackTree.delete(11);
//        redBlackTree.delete(18);
//        redBlackTree.delete(3);
//        redBlackTree.delete(10);
//        redBlackTree.delete(22);
//
//        redBlackTree.traverse(TreeTraverseType.Inorder);
//        redBlackTree.traverse(TreeTraverseType.BFT);

//        SplayTree<String> splayTree = new SplayTree<>();
//
//        splayTree.insert("A");
//        splayTree.insert("B");
//        splayTree.insert("C");
//        splayTree.insert("P");
//        splayTree.insert("F");
//        splayTree.insert("L");
//
//        splayTree.traverse(TreeTraverseType.Preorder);
//
//        splayTree.find("P");
//
//        System.out.println("______________________________________________");
//
//        splayTree.traverse(TreeTraverseType.Preorder);


        Trie trie = new Trie();

        trie.insert("train");
        trie.insert("car");
        trie.insert("google");
        trie.insert("go");
        trie.insert("good");
        trie.insert("goal");


        System.out.println(trie.contains("go"));
        System.out.println(trie.containsPrefix("go"));
        System.out.println(trie.wordsWithPrefix("go"));
        trie.delete("ago");
        System.out.println(trie.wordsWithPrefix("go"));
        //        System.out.println(trie.contains("tr"));

    }
}