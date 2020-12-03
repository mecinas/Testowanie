package sortAlgorithms;

public class Qsort implements SortAlgorithm{
    private int[] arr;

    public Qsort(int[] arr) {
        this.arr = arr;
    }

    public Qsort() {}

    public void setArr(int[] arr) {
        this.arr = arr;
    }

    private void swap(int firstIndex, int secoundIndex){
        int tmp = arr[firstIndex];
        arr[firstIndex] = arr[secoundIndex];
        arr[secoundIndex] = tmp;
    }
    private int partition(int leftIndex, int rightIndex)
    {
        int pivot = arr[rightIndex];
        int i = (leftIndex-1);
        for (int j=leftIndex; j<rightIndex; j++)
        {
            if (arr[j] < pivot)
            {
                i++;
                swap(i,j);
            }
        }

        int tmp = arr[i+1];
        arr[i+1] = arr[rightIndex];
        arr[rightIndex] = tmp;

        return i+1;
    }

    private void pivotSort(int leftIndex, int rightIndex)
    {
        if (leftIndex < rightIndex)
        {
            int pivotIndex = partition(leftIndex, rightIndex);

            pivotSort(leftIndex, pivotIndex-1);
            pivotSort(pivotIndex+1, rightIndex);
        }
    }

    public int[] sort(int[] arr) {
        this.arr = arr;
        pivotSort(0, arr.length-1);
        return arr;
    }

}
