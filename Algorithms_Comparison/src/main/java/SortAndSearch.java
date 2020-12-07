import findElementIndexAlgorithms.SequentialSearch;
import sortAlgorithms.*;

import java.util.Arrays;

public class SortAndSearch {

    public static int[] bubbleSortAndSequentialSearch(int[] arr, int[] x) {
        int[] arrSorted = new BubbleSort().sort(arr);
        return Arrays.stream(x).map(xx -> new SequentialSearch(arrSorted).findElementIndex(xx)).toArray();
    }

    public static int[] insertSortAndSequentialSearch(int[] arr, int[] x) {
        int[] arrSorted = new InsertSort().sort(arr);
        return Arrays.stream(x).map(xx -> new SequentialSearch(arrSorted).findElementIndex(xx)).toArray();
    }

    public static int[] javaDefaultSortAndSequentialSearch(int[] arr, int[] x) {
        int[] arrSorted = new JavaDefaultSort().sort(arr);
        return Arrays.stream(x).map(xx -> new SequentialSearch(arrSorted).findElementIndex(xx)).toArray();
    }

    public static int[] mergeSortAndSequentialSearch(int[] arr, int[] x) {
        int[] arrSorted = new MergeSort().sort(arr);
        return Arrays.stream(x).map(xx -> new SequentialSearch(arrSorted).findElementIndex(xx)).toArray();
    }

    public static int[] quickSortAndSequentialSearch(int[] arr, int[] x) {
        int[] arrSorted = new Qsort().sort(arr);
        return Arrays.stream(x).map(xx -> new SequentialSearch(arrSorted).findElementIndex(xx)).toArray();
    }


}
