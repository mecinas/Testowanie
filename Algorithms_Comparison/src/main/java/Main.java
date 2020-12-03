import findElementIndexAlgorithms.*;
import sortAlgorithms.*;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        int[] arr = {1,8,4,12,98,0,-12,4,-123,934,574,-908,-431};
        //int[] arr = {1,1,1,1,1};

        //SortAlgorithm sortAlgorithm = new BubbleSort(arr);
        //SortAlgorithm sortAlgorithm = new InsertSort(arr);
        //SortAlgorithm sortAlgorithm = new MergeSort(arr);
        SortAlgorithm sortAlgorithm = new Qsort(arr);



        sortAlgorithm.sort(arr);
        //Arrays.sort(arr);
        //Arrays.parallelSort(arr);
        System.out.println(Arrays.toString(arr));

        //FindAlgorithm findAlgorithm = new SequentialSearch(arr);
        FindAlgorithm findAlgorithm = new BinarySearch(arr);
        System.out.println(findAlgorithm.findElementIndex(0));
    }
}
