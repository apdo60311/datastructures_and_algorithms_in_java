package sorting_and_searching;

import com.sun.jdi.InvalidTypeException;
import sorting_and_searching.enums.QuickSortPartitionType;
import sorting_and_searching.enums.SortType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;


public class Sorter<T extends  Comparable<T>> {

    public List<T> getList() {
        return list;
    }
    List<T> list;
    private boolean isSorted = false;
    public Sorter(List<T> list) {
        this.list = list;
    }
    private SortType sortType;
    private List<Integer> tags;
    private QuickSortPartitionType quickSortPartitionType = QuickSortPartitionType.LAST_AS_PIVOT_METHOD1;
    public void sort(SortType sortType) {
        this.sortType = sortType;
        try {
            if (!isSorted) {
                switch (sortType) {
                    case INSERTION_SORT -> insertionSort();
                    case BUBBLE_SORT -> bubbleSort();
                    case SELECTION_SORT -> selectionSort();
                    case MERGE_SORT -> mergeSort();
                    case BUCKET_SORT -> bucketSort();
                    case COUNTING_SORT -> countSort();
                    case RADIX_SORT -> radixSort();
                    case HEAP_SORT -> heapSort();
                    case QUICK_SORT -> quickSort(quickSortPartitionType);
                    case BOGO_SORT -> bogoSort();
                    case SLEEP_SORT -> sleepSort();
                    case TAG_SORT -> tagSort();
                    default -> throw new IllegalStateException("Unexpected value: " + sortType);
                }
                isSorted = true;
            } else {
                System.out.println("Already Sorted");
            }

        } catch (Throwable exception) {
            System.out.println(exception.getMessage());
        }
    }



    @SuppressWarnings("unchecked")
    private synchronized void sleepSort() {
        try {

            List <Integer> copyList = new ArrayList<>();
            ExecutorService es = Executors.newCachedThreadPool();
            for (T t : list) {
                SleepSort sleepSort = new SleepSort((Integer) t, copyList);
                es.execute(sleepSort);
            }

            es.shutdown();

            boolean finished = es.awaitTermination(2, TimeUnit.MINUTES);

            if (finished) {
                list = (List<T>) copyList;
            } else {
                throw new RuntimeException();
            }


        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void quickSort(QuickSortPartitionType quickSortPartitionType) {
        QuickSort quickSort = new QuickSort(quickSortPartitionType);
        quickSort.sort(0 , list.size() - 1, list);
    }

    private void heapSort() {}
    private void bubbleSort() {
        try {
            boolean isChanged = false;
            for (int i = 0; i < list.size();i++) {
                for (int j = i + 1; j < list.size(); j++) {
                    if (list.get(i).compareTo(list.get(j)) > 0) {
                        // swap logic
                        T temp = list.get(i);
                        list.set(i, list.get(j));
                        list.set(j , temp);
                        isChanged = true;
                    }
                }
                if (!isChanged) {
                    break;
                }
            }
        } catch (Throwable exception) {
            System.out.println(exception.getMessage());
        }
    }
    private void insertionSort() {
        try {
            for (int i = 1 ; i < list.size();i++) {
                int j = i - 1;
                T currentElement = list.get(i);
                while (i != 0 && list.get(j).compareTo(currentElement) > 0) {
                    list.set(i , list.get(j));
                    i--;
                    j--;
                }
                list.set(i , currentElement);
            }

        } catch (Throwable exception) {
            System.out.println(exception.getMessage());
        }
    }
    private  void selectionSort() {
        try {
            for (int i = 0; i < list.size() - 1; i++) {
                int smallestIndex = i + 1;
                for (int j = i + 1; j < list.size(); j++) {
                    if (list.get(j).compareTo(list.get(smallestIndex)) < 0) {
                        smallestIndex = j;
                    }
                }
                if (list.get(i).compareTo(list.get(smallestIndex)) > 0) {
                    // swap logic
                    T temp = list.get(i);
                    list.set(i, list.get(smallestIndex));
                    list.set(smallestIndex , temp);
                }
            }

        } catch (Throwable exception) {
            System.out.println(exception.getMessage());
        }
    }
    private void mergeSort() {
        try {
            MergeSort mergeSort = new MergeSort();
            mergeSort.sort(0 , list.size() - 1 , list);
        } catch (Throwable exception) {
            System.out.println(exception.getMessage());
        }
    }
    private void bucketSort() {
        BucketSort bucketSort = new BucketSort();
        bucketSort.sort(list);
    }
    @SuppressWarnings("unchecked")
    private void countSort() {
        try {
            if (list.get(0) instanceof Integer) {
                int i = 0;

                // [1] calculate the range of the list
                int range = (Integer) Collections.max(list);

                // [2] create a new list with initial size equal to the range + 1
                // And initialize it with zeros
                Integer[] frequencyList = new Integer[range + 1];
                Arrays.fill(frequencyList, 0);
                for (i = 0; i < list.size();i++) {
                    int currentInputListElement = (Integer) list.get(i);
                    frequencyList[currentInputListElement]++;
                }

                // [3] create cumulative sum for frequency list
                for (i = 1;i < frequencyList.length; i++) {
                    frequencyList[i] += frequencyList[i - 1];
                }

                // [4] Iterate over input List and do sorting logic
                // SORTING-LOGIC -> outputArray[countArray[inputArray[index]] - 1]
                List<Integer> outputList = new ArrayList<>(Collections.nCopies(list.size(), 0));

                for (i = list.size() - 1 ;i >= 0;i--) {
                    Integer currentInputListElement = (Integer) list.get(i);
                    int currentFreqListElement = frequencyList[currentInputListElement];
                    outputList.set(currentFreqListElement - 1 , currentInputListElement);
                    frequencyList[currentInputListElement] = --currentFreqListElement;
                }
                list = (List<T>) outputList;
            } else {
                throw new InvalidTypeException("Only Integers are supported");
            }
        } catch (Throwable exception) {
            System.out.println(exception.getMessage());
        }
    }
    private void radixSort() {
        try {
            if (list.get(0) instanceof Integer) {
                RadixSort radixSort = new RadixSort();
                radixSort.sort(list);
            } else {
                throw new InvalidTypeException("Only Integers are supported");
            }
        } catch (Throwable exception) {
            System.out.println(exception.getMessage());
        }
    }
    private void bogoSort() {
        try {
            BogoSort bogoSort = new BogoSort();
            bogoSort.sort();
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }
    private void tagSort() {
        try {
            // initialize tags' list
            tags = new ArrayList<>();
            for (int i = 0; i < list.size(); i++) {
                tags.add(i);
            }

            boolean isChanged = false;
            for (int i = 0; i < list.size();i++) {
                for (int j = i + 1; j < list.size(); j++) {
                    if (list.get(i).compareTo(list.get(j)) > 0) {
                        // swap logic
                        int temp = tags.get(i);
                        tags.set(i, tags.get(j));
                        tags.set(j , temp);
                        isChanged = true;
                    }
                }
                if (!isChanged) {
                    break;
                }
            }

        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }
    private void genomeSort(){}

    public Sorter<T> setQuickSortPartitionType(QuickSortPartitionType quickSortPartitionType) {
        this.quickSortPartitionType = quickSortPartitionType;
        return this;
    }

    private class QuickSort {
        QuickSortPartitionType quickSortPartitionType;
        QuickSort(QuickSortPartitionType quickSortPartitionType) {
            this.quickSortPartitionType = quickSortPartitionType;
            System.out.println("Sorting using " + quickSortPartitionType.toString());
        }
        private int partitionUsingLastAsPivotMethod1(int start , int end , List<T> list) {
            int i = start, j = end;
            T pivot = list.get(end);

            while (i < j) {
                while (list.get(i).compareTo(pivot) < 0 && i <= end) {
                    i++;
                }
                while (list.get(j).compareTo(pivot) > 0 && j >= start) {
                    j--;
                }
                swap(i , j , list);
            }
            return i;
        }
        private int partitionUsingLastAsPivotMethod2(int start , int end , List<T> list) {
            T pivot = list.get(end);
            int bound = start;

            for (int i = start; i < end; i++) {
                if (list.get(i).compareTo(pivot) <= 0) {
                    if (bound != i) swap(i, bound , list);
                    bound++;
                }
            }
            swap(bound , end , list);
            return bound;
        }

        private void swap(int i , int j , List<T> list) {
            T temp = list.get(i);
            list.set(i , list.get(j));
            list.set(j , (T) temp);
        }
        private void sortWithLastElementAsPivotMethod2(int start , int end , List<T> list) {
            if (start < end) {
                int pivot = partitionUsingLastAsPivotMethod2(start , end , list);
                sortWithLastElementAsPivotMethod2(start , pivot - 1, list);
                sortWithLastElementAsPivotMethod2(pivot + 1, end , list);
            }
        }
        void sort(int start , int end , List<T> list) {
            switch (quickSortPartitionType) {
                case LAST_AS_PIVOT_METHOD1 -> sortWithLastElementAsPivotMethod1(start , end , list);
                case LAST_AS_PIVOT_METHOD2 -> sortWithLastElementAsPivotMethod2(start , end , list);
            }
        }
        private void sortWithLastElementAsPivotMethod1(int start , int end , List<T> list) {
            if (start < end) {
                int pivot = partitionUsingLastAsPivotMethod1(start , end , list);
                sortWithLastElementAsPivotMethod2(start , pivot - 1, list);
                sortWithLastElementAsPivotMethod2(pivot + 1, end , list);
            }
        }
    }
    @SuppressWarnings("unchecked")
    private class RadixSort {

        private void modifiedCountingSort(int place) {
            int i = 0;

            // [1] calculate the range of the list
            int range = (Integer) Collections.max(list , (o1 , o2) -> {
                int num1 = getSignificantPlace((Integer) o1 , place);
                int num2 = getSignificantPlace((Integer) o2 , place);
                return Integer.compare(num1, num2);
            });

            // [2] create a new list with initial size equal to the range + 1
            // And initialize it with zeros
            Integer[] frequencyList = new Integer[range + 1];
            Arrays.fill(frequencyList, 0);
            for (i = 0; i < list.size();i++) {
                int currentInputListElement = getSignificantPlace((Integer) list.get(i) , place);
                frequencyList[currentInputListElement]++;
            }

            // [3] create cumulative sum for frequency list
            for (i = 1;i < frequencyList.length; i++) {
                frequencyList[i] += frequencyList[i - 1];
            }

            // [4] Iterate over input List and do sorting logic
            // SORTING-LOGIC -> outputArray[countArray[inputArray[index]] - 1]
            List<Integer> outputList = new ArrayList<>(Collections.nCopies(list.size(), 0));

            for (i = list.size() - 1 ;i >= 0;i--) {
                Integer currentInputListElement = (Integer) list.get(i);
                int currentInputListElementIndex = getSignificantPlace((Integer) list.get(i) , place);
                int currentFreqListElement = frequencyList[currentInputListElementIndex];
                outputList.set(currentFreqListElement - 1 , currentInputListElement);
                frequencyList[currentInputListElementIndex] = --currentFreqListElement;
            }
            list = (List<T>) outputList;
        }

        private int getSignificantPlace(Integer number, int digit) {
            int num = number;
            for(int i = 0;i < digit;i++) {
                num /= 10;
            }
            return num % 10;
        }

        private int getMax(List<T> list) {
            int max = Integer.MIN_VALUE;
            for (T num : list) {
                max = Math.max(max, getIntegerLength((Integer) num));
            }
            return max;
        }
        private int getIntegerLength(Integer number) {
            if (number == 0) {
                return 1;
            }

            int length = 0;
            number = Math.abs(number);

            while (number > 0) {
                length++;
                number /= 10;
            }
            return length;
        }
        public void sort(List<T> list) {
            int maxIntLength = getMax(list);

            for (int i = 0; i < maxIntLength; i++) {
                modifiedCountingSort(i);
            }
        }
    }
    private class BucketSort {
        @SuppressWarnings("unchecked")
        public void sort(List<T> list) {
            try {
                if (list.get(0) instanceof Double) {

                    int bucketsCount = getMaxFirstFloatingPoint(list);

                    ArrayList<T>[] buckets = new ArrayList[bucketsCount + 1];

                    int i = 0, k = 0;

                    // initialize empty buckets
                    for (i = 0; i < buckets.length; i++){
                        buckets[i] = new ArrayList<>();
                    }
                    // scatter elements into buckets
                    for (i = 0; i < list.size(); i++) {
                        T currentElment = list.get(i);
                        double parsedElement = Double.parseDouble(list.get(i).toString());
                        int index = (int) ((parsedElement * 10) % 10);
                        buckets[index].add(currentElment);
                    }
                    // sort each Bucket using any sorting algorithm
                    for (ArrayList<T> bucket: buckets) {
                        Collections.sort(bucket);
                    }
                    // copy merge sorted buckets into the main array
                    for (ArrayList<T> bucket: buckets) {
                        for (i = 0; i < bucket.size(); i++) {
                            list.set(k,bucket.get(i));
                            k++;
                        }
                    }
                } else {
                    throw new InvalidTypeException("Only Double is Supported");
                }
            } catch (Throwable exception) {
                System.out.println(exception.getMessage());
            }
        }

        private int getMaxFirstFloatingPoint(List<T> list) {
            return (int) (Double.parseDouble(Collections.max(list, (element, comp) -> {
                double first = Double.parseDouble(element.toString());
                double second = Double.parseDouble(comp.toString());

                int firstNumberFirstFloatingNum = (int) (first * 10) % 10;
                int secondNumberFirstFloatingNum = (int) (second * 10) % 10;

                return Integer.compare(firstNumberFirstFloatingNum, secondNumberFirstFloatingNum);
            }).toString()) * 10 );
        }
    }
    private class MergeSort {
        private void merge(int start , int mid , int end , List<T> list) {
            int leftSize = mid - start + 1;
            int rightSize = end - mid;
            List<T> leftSubArray = new ArrayList<>();
            List<T> rightSubArray = new ArrayList<>();

            for (int i = 0; i < leftSize; i++) {
                leftSubArray.add(list.get(start + i));
            }
            for (int i = 0; i < rightSize; i++) {
                rightSubArray.add(list.get(mid + i + 1));
            }

            int i = 0, j = 0 , k = start;

            while (i < leftSize && j < rightSize) {
                if (leftSubArray.get(i).compareTo(rightSubArray.get(j)) <= 0) {
                    list.set(k , leftSubArray.get(i));
                    i++;
                } else {
                    list.set(k , rightSubArray.get(j));
                    j++;
                }
                k++;
            }

            while (i < leftSize) {
                list.set(k , leftSubArray.get(i));
                i++;
                k++;
            }

            while (j < rightSize) {
                list.set(k , rightSubArray.get(j));
                j++;
                k++;
            }

        }
        private  void sort(int start , int end , List<T> list) {
            if (start < end) {
                int mid = (start + end) / 2;
                sort(start , mid , list);
                sort(mid + 1 , end , list);
                merge(start, mid, end, list);
            }
        }

    }
    private class BogoSort {
        private boolean isSorted() {
            boolean isSorted = true;
            for (int i = 1; i < list.size(); i++) {
                if ((Integer)list.get(i) < (Integer) list.get(i - 1)) {
                    isSorted = false;
                    break;
                }
            }
            return isSorted;
        }

        @SuppressWarnings("unchecked")
        private void swap(int i , int j) {
            Integer temp = (Integer) list.get(i);
            list.set(i , list.get(j));
            list.set(j , (T) temp);
        }
        private void shuffle() {
            for (int i = 0; i < list.size(); i++)
                swap(i, (int)(Math.random() * i));
        }
        public void sort() {
            while (!isSorted()) {
                shuffle();
            }
        }
    }
    private record SleepSort(Integer currentValue, List<Integer> copyList) implements Runnable {


        @Override
            public void run() {
                try {
                    Thread.sleep(currentValue);
                    copyList.add(currentValue);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        public void printList() {
        if(isSorted) {
            if (sortType == SortType.TAG_SORT) {
                System.out.println("Sorted Tags: ");
                print(tags);
            } else {
                System.out.println("Sorted List: ");
                print(list);
            }
        } else {
            System.out.println("UnSorted List: ");
            print(list);
        }
    }
    @SuppressWarnings("unchecked")
    private void print(List list) {
        list.forEach((element) -> {
            System.out.print(element + " ");
        });
        System.out.println();
    }
}
