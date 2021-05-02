package sorting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PancakesSorting {

    public List<Integer> pancakeSort(int[] arr) {
        List<Integer> result = new ArrayList<>();

        for (int i = arr.length - 1; i > 0; i--) {
            if (arr[i] != i + 1) {
                for (int j = i - 1; j > 0; j--) {
                    if (arr[j] == i + 1) {
                        flip(arr, j);
                        result.add(j + 1);
                        break;
                    }
                }
                flip(arr, i);
                result.add(i + 1);
            }
        }
        return result;
    }

    protected void flip(int[] sublist, int k) {
        int i = 0;
        while (i < k) {
            int temp = sublist[i];
            sublist[i] = sublist[k];
            sublist[k] = temp;
            i += 1;
            k -= 1;
        }
    }
}
