package sorting_and_searching;

import sorting_and_searching.enums.SearchType;

import java.util.List;

public class Searcher <T extends Comparable<T>>{
    List<T> list;
    public List<T> getList() {return list; }

    public Searcher(List<T> list) {
        this.list = list;
    }

    public int search(SearchType searchType , T item) {

        try {
            return switch (searchType) {
                case LINEAR_SEARCH -> linearSearch(item);
                case BINARY_SEARCH -> binarySearch(item);
                case JUMP_SEARCH -> jumpSearch(item);
                default -> throw new IllegalStateException("Unexpected value: " + searchType);
            };
        }catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
        return -1;
    }

    private int linearSearch(T item) {
        try {
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).equals(item)) {
                    return i;
                }
            }
            throw new Exception("Element Not Found!");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private int binarySearch(T item) {
        try {
            if (isSorted()) throw new Exception("List not Sorted");
            int start = 0;
            int end = list.size() - 1;
            int mid;

            while (start <= end) {
                mid = start + (end - start) / 2;
                if (list.get(mid).equals(item)) {
                    return mid;
                } else if (list.get(mid).compareTo(item) > 0) {
                    end = mid - 1;
                } else {
                    start = mid + 1;
                }
            }
            return -1;
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
        return -1;
    }

    private int jumpSearch(T item) {
        try {
            if (!isSorted()) throw new Exception("List must be sorted");
            int itemIndex = -1;
            int n = list.size();
            int m = (int) Math.sqrt(n);
            for (int low = 0 , high = m; low < n; ) {
                boolean isIndexInBound = high < n;
                if (isIndexInBound && item.compareTo(list.get(low)) > 0 && item.compareTo(list.get(high)) < 0 ) {
                    return sequentialSearchForJumpSearch(low , high , item , list);
                } else if (isIndexInBound && item.compareTo(list.get(high)) > 0 ) {
                    low = high;
                    high += m;
                } else {
                    return sequentialSearchForJumpSearch(high , n - 1 , item , list);
                }
            }
            return itemIndex;

        } catch (Exception exception) {
            System.out.println(exception.getMessage());
            return -1;
        }
    }

    private int sequentialSearchForJumpSearch(int startIndex , int endIndex,T item ,List<T> list) {
        for (int i = startIndex; i <= endIndex; i++) {
            if (list.get(i).compareTo(item) == 0) {
                return i;
            }
        }
        return -1;
    }

    private int metaBinarySearch(T item) {
        try {
            if (isSorted()) throw new Exception("List not Sorted");
            int start = 0;
            int end = list.size() - 1;
            int mid;

            while (start <= end) {
                mid = start + (end - start) / 2;
                if (list.get(mid).equals(item)) {
                    return mid;
                } else if (list.get(mid).compareTo(item) > 0) {
                    end = mid - 1;
                } else {
                    start = mid + 1;
                }
            }
            return -1;
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
        return -1;
    }

    boolean isSorted() {
        for (int i = 1; i < list.size(); i++) {
            if (list.get(i - 1).compareTo(list.get(i)) > 0) {
                return true;
            }
        }
        return false;
    }

}
