import findElementIndexAlgorithms.BinarySearch;
import findElementIndexAlgorithms.SequentialSearch;
import sortAlgorithms.*;

import java.util.Arrays;

public class SortAndSearch {

//    SortAlgorithm sa;
//    BinarySearch bs;
//
//    public SortAndBinarySearch(SortAlgorithm sa){
//        this.sa = sa;
//        bs = new BinarySearch();
//    }
//
//    public int sortAndSearch(int[] arr, int x) {
//        int[] arrSorted = sa.sort(arr);
//        bs.setArr(arrSorted);
//        return bs.findElementIndex(x);
//    }

    public static int[] bubbleSortAndSequentialSearch(int[] arr, int[] x) {
        int[] arrSorted = new InsertSort().sort(arr);
        return Arrays.stream(x).map(xx -> new SequentialSearch(arrSorted).findElementIndex(xx)).toArray();
    }

    public static int[] insertSortAndSequentialSearch(int[] arr, int[] x) {
        int[] arrSorted = new InsertSort().sort(arr);
        return Arrays.stream(x).map(xx -> new SequentialSearch(arrSorted).findElementIndex(xx)).toArray();
    }

    public static int[] javaDefaultSortAndSequentialSearch(int[] arr, int[] x) {
        int[] arrSorted = new InsertSort().sort(arr);
        return Arrays.stream(x).map(xx -> new SequentialSearch(arrSorted).findElementIndex(xx)).toArray();
    }

    public static int[] mergeSortAndSequentialSearch(int[] arr, int[] x) {
        int[] arrSorted = new InsertSort().sort(arr);
        return Arrays.stream(x).map(xx -> new SequentialSearch(arrSorted).findElementIndex(xx)).toArray();
    }

    public static int[] quickSortAndSequentialSearch(int[] arr, int[] x) {
        int[] arrSorted = new InsertSort().sort(arr);
        return Arrays.stream(x).map(xx -> new SequentialSearch(arrSorted).findElementIndex(xx)).toArray();
    }


}
