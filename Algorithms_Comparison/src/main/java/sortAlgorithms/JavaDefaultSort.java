package sortAlgorithms;

import java.util.Arrays;

public class JavaDefaultSort implements SortAlgorithm{
    @Override
    public int[] sort(int[] arr) {
        Arrays.sort(arr);
        return arr;
    }
}
