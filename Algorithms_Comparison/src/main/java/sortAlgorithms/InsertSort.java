package sortAlgorithms;

public class InsertSort implements SortAlgorithm{

    private int[] arr;

    public InsertSort(int[] arr) {
        this.arr = arr;
    }

    public void setArr(int[] arr) {
        this.arr = arr;
    }

    @Override
    public void sort() {
        int len = arr.length;
        for (int i = 1; i <len; ++i) {
            int tmp = arr[i];
            int j = i - 1; 
            while (j >= 0 && arr[j] > tmp) {
                arr[j + 1] = arr[j];
                j = j - 1;
            }
            arr[j + 1] = tmp;
        }
    }
}
