import findElementIndexAlgorithms.BinarySearch;
import findElementIndexAlgorithms.FindAlgorithm;
import findElementIndexAlgorithms.SequentialSearch;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SearchAlgorithmsUnitTests {

    private FindAlgorithm sequentialSearch;
    private FindAlgorithm binarySearch;

    private final int DATA_SIZE = 100;
    private int[] data;
    private int[] sorted;

    @BeforeEach
    public void before() {
        int seed = new Random().nextInt();
        data = new Random(seed).ints(DATA_SIZE).toArray();
        sorted = Arrays.copyOf(data, data.length);
        Arrays.sort(sorted);

        sequentialSearch = new SequentialSearch(data);
        binarySearch = new BinarySearch(sorted);
    }

    @Test
    public void searchRandomElementTest() {
        int randomIndex = new Random().nextInt(DATA_SIZE);
        int sequentialElement = data[randomIndex];
        int binaryElement = sorted[randomIndex];

        int sequentialIndex = sequentialSearch.findElementIndex(sequentialElement);
        int binaryIndex = binarySearch.findElementIndex(binaryElement);
        assertEquals(data[randomIndex], data[sequentialIndex]);
        assertEquals(data[randomIndex], data[binaryIndex]);
    }

    @Test
    public void searchFirstElementTest() {
        int index = 0;
        searchIndexTest(index);
    }

    @Test
    public void searchLastElementTest() {
        int index = DATA_SIZE - 1;
        searchIndexTest(index);
    }

    @Test
    public void searchMiddleElementTest() {
        int index = DATA_SIZE / 2;
        searchIndexTest(index);
    }

    private void searchIndexTest(int index) {
        int sequentialElement = data[index];
        int binaryElement = sorted[index];

        int sequentialIndex = sequentialSearch.findElementIndex(sequentialElement);
        int binaryIndex = binarySearch.findElementIndex(binaryElement);
        assertEquals(data[index], data[sequentialIndex]);
        assertEquals(data[index], data[binaryIndex]);
    }
}
