import sorting_and_searching.*;
import sorting_and_searching.enums.QuickSortPartitionType;
import sorting_and_searching.enums.SortType;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(113);
        list.add(14);
        list.add(12);
        list.add(9);
        list.add(2);
        list.add(42);
        list.add(68);
        list.add(1123);
        list.add(33);
        list.add(91);

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

        Sorter<Integer> sorter = new Sorter<>(list);
        sorter.printList();
        sorter.setQuickSortPartitionType(QuickSortPartitionType.LAST_AS_PIVOT_METHOD2).sort(SortType.QUICK_SORT);
        sorter.printList();

//        Searcher<Integer> searcher = new Searcher<>(list);
//        System.out.println(searcher.search(SearchType.BINARY_SEARCH , 3));

    }



}