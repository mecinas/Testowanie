import org.junit.jupiter.api.Test;
import sortAlgorithms.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class SortAlgorithmsUnitTests {

    private final List<SortAlgorithm> algorithms = Arrays.asList(new BubbleSort(), new InsertSort(), new MergeSort(), new Qsort());
    private final int DATA_SIZE = 100;

    @Test
    public void sortRandomVectorTest() {
        int seed = new Random().nextInt();
        System.out.println("Seed: " + seed);
        int[] data = new Random(seed).ints(DATA_SIZE).toArray();
        int[] sorted = Arrays.copyOf(data, data.length);
        Arrays.sort(sorted);
        for(SortAlgorithm alg : algorithms){
            assertArrayEquals(sorted, alg.sort(Arrays.copyOf(data, data.length)), "Error in: " + alg.getClass());
        }
    }

    @Test
    public void sortEmptyVectorTest() {
        int[] data = new int[0];
        for(SortAlgorithm alg : algorithms){
            assertArrayEquals(data, alg.sort(Arrays.copyOf(data, data.length)), "Error in: " + alg.getClass());
        }
    }

    @Test
    public void sortReversedOrderSortedVectorTest() {
        int[] data = new int[DATA_SIZE];
        for (int i = 0; i < DATA_SIZE; i++) {
            data[i] = DATA_SIZE - i;
        }
        int[] sorted = Arrays.copyOf(data, data.length);
        Arrays.sort(sorted);
        for(SortAlgorithm alg : algorithms){
            assertArrayEquals(sorted, alg.sort(Arrays.copyOf(data, data.length)), "Error in: " + alg.getClass());
        }
    }
}
