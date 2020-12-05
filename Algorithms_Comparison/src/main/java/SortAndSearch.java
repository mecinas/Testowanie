import findElementIndexAlgorithms.BinarySearch;
import findElementIndexAlgorithms.SequentialSearch;
import sortAlgorithms.*;

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

    public static int bubbleSortAndSequentialSearch(int[] arr, int x) {
        int[] arrSorted = new BubbleSort().sort(arr);
        return new SequentialSearch(arrSorted).findElementIndex(x);
    }

    public static int insertSortAndSequentialSearch(int[] arr, int x) {
        int[] arrSorted = new InsertSort().sort(arr);
        return new SequentialSearch(arrSorted).findElementIndex(x);
    }

    public static int javaDefaultSortAndSequentialSearch(int[] arr, int x) {
        int[] arrSorted = new JavaDefaultSort().sort(arr);
        return new SequentialSearch(arrSorted).findElementIndex(x);
    }

    public static int mergeSortAndSequentialSearch(int[] arr, int x) {
        int[] arrSorted = new MergeSort().sort(arr);
        return new SequentialSearch(arrSorted).findElementIndex(x);
    }

    public static int quickSortAndSequentialSearch(int[] arr, int x) {
        int[] arrSorted = new Qsort().sort(arr);
        return new SequentialSearch(arrSorted).findElementIndex(x);
    }


}
