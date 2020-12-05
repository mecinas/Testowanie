import findElementIndexAlgorithms.SequentialSearch;
import org.junit.jupiter.api.Test;
import sortAlgorithms.*;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class MacroTests {

//    private final List<SortAlgorithm> algorithms = Arrays.asList(new BubbleSort(), new InsertSort(), new JavaDefaultSort(), new MergeSort(), new Qsort());
    private final int[] DATA_SIZE = {100000};

//    @Test
//    public void sortRandomVectorTest() {
//        int seed = new Random().nextInt();
//        System.out.println("Seed: " + seed);
//        for (int DATA_SIZE_ : DATA_SIZE) {
//            int[] data = new Random(seed).ints(DATA_SIZE_).toArray();
//            for (SortAlgorithm alg : algorithms) {
//                int[] dataCopy = Arrays.copyOf(data, data.length);
//                int x = data[Math.abs(new Random(seed).nextInt() % DATA_SIZE_)];
//                SortAndBinarySearch sabs = new SortAndBinarySearch(alg);
//                int result = sabs.sortAndSearch(dataCopy, x);
//                System.out.println(result);
//            }
//        }
//    }

    @Test
    public void test() {
        int seed = 111;
        System.out.println("Seed: " + seed);
        for (int DATA_SIZE_ : DATA_SIZE) {
            int[] data = new Random(seed).ints(DATA_SIZE_).toArray();
            int x = data[Math.abs(new Random(seed).nextInt() % DATA_SIZE_)];
            int[] dataCopy;
            dataCopy = Arrays.copyOf(data, data.length);
            new SequentialSearch(dataCopy).findElementIndex(x);

            dataCopy = Arrays.copyOf(data, data.length);
            System.out.println(SortAndSearch.insertSortAndSequentialSearch(dataCopy, x));
            dataCopy = Arrays.copyOf(data, data.length);
            System.out.println(SortAndSearch.bubbleSortAndSequentialSearch(dataCopy, x));
            dataCopy = Arrays.copyOf(data, data.length);
            System.out.println(SortAndSearch.javaDefaultSortAndSequentialSearch(dataCopy, x));
            dataCopy = Arrays.copyOf(data, data.length);
            System.out.println(SortAndSearch.mergeSortAndSequentialSearch(dataCopy, x));
            dataCopy = Arrays.copyOf(data, data.length);
            System.out.println(SortAndSearch.quickSortAndSequentialSearch(dataCopy, x));
        }
    }

}
