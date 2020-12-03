package findElementIndexAlgorithms;

public class SequentialSearch implements FindAlgorithm {
    private int[] arr;

    public SequentialSearch(int[] arr) {
        this.arr = arr;
    }

    public void setArr(int[] arr) {
        this.arr = arr;
    }

    @Override
    public int findElementIndex(int wantedVariable) {
            for (int i=0; i<arr.length; i++)
                if (arr[i] == wantedVariable)
                    return i;
            return -1;
    }
}
