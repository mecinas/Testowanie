package findElementIndexAlgorithms;

public class BinarySearch implements FindAlgorithm{
    private int[] arr;

    public BinarySearch(int[] arr) {
        this.arr = arr;
    }

    public void setArr(int[] arr) {
        this.arr = arr;
    }

    @Override
    public int findElementIndex(int wantedVariable) {
        int leftIndex = 0;
        int rightIndex = arr.length - 1;
        while (leftIndex <= rightIndex) {
            int middleIndex = leftIndex + (rightIndex - leftIndex) / 2;

            if (arr[middleIndex] == wantedVariable)
                return middleIndex;

            if (arr[middleIndex] < wantedVariable)
                leftIndex = middleIndex + 1;

            else
                rightIndex = middleIndex - 1;
        }
        return -1;
    }
}
