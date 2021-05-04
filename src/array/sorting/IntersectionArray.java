package array.sorting;

import java.util.*;

public class IntersectionArray {

    public int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> set1 = new HashSet<>();
        Set<Integer> set2 = new HashSet<>();
        for (int i : nums1) set1.add(i);
        for (int i : nums2) set2.add(i);
        if (nums1.length > nums2.length)
            return intersectionSet(set1, set2);
        else return intersectionSet(set2, set1);
    }

    private int[] intersectionSet(Set<Integer> set1, Set<Integer> set2) {
        int[] result = new int[set2.size()];
        int ptr = 0;

        for (Integer i : set2) {
            if (set1.contains(i)) result[ptr++] = i;
        }
        return Arrays.copyOf(result, ptr);
    }

    public int[] intersectionWithSort(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        return intersectUniqueArray(nums1, nums2);

    }

    private int[] intersectUniqueArray(int[] nums1, int[] nums2) {
        int[] result = new int[nums1.length + nums2.length];


        int ptr1 = 0, ptr2 = 0, ptr = 0;

        while (ptr1 < nums1.length && ptr2 < nums2.length) {
            if (nums1[ptr1] == nums2[ptr2]) {
                result[ptr] = nums1[ptr1];
                ptr1++;
                ptr2++;
                ptr++;
            } else if (nums1[ptr1] < nums2[ptr2]) {
                //For union here you will add nums1[ptr1] to results
                ptr1++;
            } else {
                //For union here you will add nums2[ptr2] to results
                ptr2++;
            }
        }
        return Arrays.copyOf(result, ptr);
    }

    /**
     * With Aux space hastable
     *
     * @param arr1
     * @param arr2
     * @param arr3
     * @return
     */
    public List<Integer> arraysIntersection(int[] arr1, int[] arr2, int[] arr3) {
        List<Integer> results = new ArrayList<>();
        Map<Integer, Integer> counter = new HashMap<>();
        for (int i : arr1) counter.put(i, counter.getOrDefault(i, 0) + 1);
        for (int i : arr2) counter.put(i, counter.getOrDefault(i, 0) + 1);
        for (int i : arr3) counter.put(i, counter.getOrDefault(i, 0) + 1);

        for (Integer j : counter.keySet()) {
            if (counter.get(j) == 3) results.add(j);
        }

        return results;
    }

    /**
     * Three Pointer approach
     *
     * @param arr1
     * @param arr2
     * @param arr3
     * @return
     */
    public List<Integer> arraysIntersectionThree(int[] arr1, int[] arr2, int[] arr3) {
        return intersectThree(arr1, arr2, arr3);
    }

    private List<Integer> intersectThree(int[] nums1, int[] nums2, int[] nums3) {
        List<Integer> results = new ArrayList<>();


        int ptr1 = 0, ptr2 = 0, ptr3 = 0;

        while (ptr1 < nums1.length && ptr2 < nums2.length && ptr3 < nums3.length) {
            if (nums1[ptr1] == nums2[ptr2] && nums1[ptr1] == nums3[ptr3]) {
                results.add(nums1[ptr1]);
                ptr1++;
                ptr2++;
                ptr3++;
            } else if (nums1[ptr1] < nums2[ptr2]) {
                ptr1++;
            } else if (nums2[ptr2] < nums3[ptr3]) {
                ptr2++;
            } else {
                ptr3++;
            }
        }
        return results;
    }


}
