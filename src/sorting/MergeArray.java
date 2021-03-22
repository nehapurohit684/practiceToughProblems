package sorting;

import java.util.Arrays;

public class MergeArray {

    /**
     * Merge Nums2 to mums1 no aux space allowed
     *
     * @param nums1
     * @param m
     * @param nums2
     * @param n
     */
    public void merge(int[] nums1, int m, int[] nums2, int n) {

        int ptr1 = nums1.length - 1, ptr2 = nums2.length - 1;
        if (m == 0) {
            while (ptr1 >= 0 && ptr2 >= 0) {
                nums1[ptr1--] = nums2[ptr2--];
            }
        }


        while (ptr1 >= 0 && ptr2 >= 0) {
            if (nums1[m - 1] > nums2[n - 1]) {
                nums1[ptr1] = nums1[m - 1];
                ptr1--;
                m--;
            } else {
                nums1[ptr1] = nums2[n - 1];
                ptr1--;
                ptr2--;
                n--;
            }
        }


    }
}
