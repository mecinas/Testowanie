package sortAlgorithms;

public class BubbleSort implements SortAlgorithm {
    private int[] arr;

    public BubbleSort(int[] arr) {
        this.arr = arr;
    }

    public BubbleSort() {
    }

    public void setArr(int[] arr) {
        this.arr = arr;
    }

    private void swap(int index) {
        int temp = arr[index];
        arr[index] = arr[index + 1];
        arr[index + 1] = temp;
    }

    @Override
    public int[] sort(int[] arr) {
        this.arr = arr;
        int len = arr.length;
        for (int i = 0; i < len - 1; i++)
            for (int j = 0; j < len - i - 1; j++)
                if (arr[j] > arr[j + 1]) {
                    swap(j);
                }
        return arr;
    }
}
