package sortAlgorithms;

public class MergeSort implements SortAlgorithm{

    private int[] arr;

    public MergeSort(int[] arr) {
        this.arr = arr;
    }

    public MergeSort() {}

    public void setArr(int[] arr) {
        this.arr = arr;
    }

    private void merge(int leftIndex, int middleIndex, int rightIndex)
    {
        int leftArrayLength = middleIndex - leftIndex + 1;
        int rightArrayLength = rightIndex - middleIndex;

        int[] leftArray = new int[leftArrayLength];
        int[] rightArray = new int[rightArrayLength];

        if (leftArrayLength >= 0) System.arraycopy(arr, leftIndex, leftArray, 0, leftArrayLength);
        for (int j = 0; j < rightArrayLength; ++j)
            rightArray[j] = arr[middleIndex + 1 + j];

        int i = 0, j = 0;

        int k = leftIndex;
        while (i < leftArrayLength && j < rightArrayLength) {
            if (leftArray[i] <= rightArray[j]) {
                arr[k] = leftArray[i];
                i++;
            }
            else {
                arr[k] = rightArray[j];
                j++;
            }
            k++;
        }

        while (i < leftArrayLength) {
            arr[k] = leftArray[i];
            i++;
            k++;
        }

        while (j < rightArrayLength) {
            arr[k] = rightArray[j];
            j++;
            k++;
        }
    }

    private void graphSort(int leftIndex, int rightIndex)
    {
        if (leftIndex < rightIndex) {
            int middleIndex = (leftIndex + rightIndex) / 2;
            graphSort(leftIndex, middleIndex);
            graphSort(middleIndex + 1, rightIndex);
            merge(leftIndex, middleIndex, rightIndex);
        }
    }


    @Override
    public int[] sort(int[] arr) {
        this.arr = arr;
        graphSort(0 ,arr.length-1);
        return arr;
    }
}
