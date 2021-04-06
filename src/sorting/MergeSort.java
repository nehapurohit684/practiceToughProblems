package sorting;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MergeSort {

    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList();
        int[] array1 = { 5,7,9, 10};
        int[] array2 = {4,5,8,9,0,0,0,0};
        list.add(2);
        list.add(4);
        list.add(0);
        list.add(7);
        list.add(9);
        list.add(12);
       // System.out.println(merge_sort(list));
        merger_first_into_second(array1,array2);

    }

    private static List<Integer> merge_sort(List<Integer> list) {
        int[] array =new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            array[i] =list.get(i);
        }
        merge_sortArr(array,array.length);
        List<Integer> returnList = new ArrayList<>();
        for (int i = 0; i < array.length; i++) {
            returnList.add(array[i]);

        }
        return returnList;
    }

    private static void merge_sortArr(int[] array,int size) {
        if (size < 2) return;
        int mid = size / 2;
        int[] left = new int[mid];
        int[] right = new int[size - mid];
        for (int i=0; i < mid; i++) {
            left[i] = array[i];
        }

        for (int i=mid; i < size; i++) {
            right[i - mid] = array[i];
        }
        merge_sortArr(left,mid);
        merge_sortArr(right,size-mid);
        merge(left,right,mid,size-mid,array);
    }

    private static void merge(int[] left, int[] right, int size1, int size2, int[] array) {
        int leftPointer = 0, rightPointer = 0;
        int arrayPoint = 0;

        while (leftPointer < left.length && rightPointer < right.length) {
            if (left[leftPointer] <= right[rightPointer]) {
                array[arrayPoint++] = left[leftPointer++];
            } else {
                array[arrayPoint++] = right[rightPointer++];
            }
        }
            while (leftPointer < size1) {
                array[arrayPoint++] = left[leftPointer++];
            }

            while (rightPointer < size2) {
                array[arrayPoint++] = right[rightPointer++];
            }

    }
    private static void mergeEqArray(int[] left, int[] right, int[] array) {
        int leftPointer = 0, rightPointer = 0;
        int arrayPoint = 0;

        while (leftPointer < left.length & rightPointer < right.length) {
            if (left[leftPointer] <= right[rightPointer]) {
                array[arrayPoint++] = left[leftPointer++];
            } else {
                array[arrayPoint++] = right[rightPointer++];
            }
        }

        while (leftPointer < left.length) {
            array[arrayPoint++] = left[leftPointer++];
        }

        while (rightPointer < right.length) {
            array[arrayPoint++] = right[rightPointer++];
        }
    }

    static void merger_first_into_second(int[] arr1, int[] arr2) {
        int mid = arr2.length / 2;
        int[] newArr = new int[arr1.length];
         for (int i=0; i < arr1.length; i++) {
            newArr[i] = arr2[i];
         }
        //quickSort(arr2,0,arr2.length-1);
       // mergeEqArray(arr1,newArr,arr2);
        mergeBackwards(arr1,arr2);
    }

    private static void mergeBackwards(int[] arr1, int[] arr2) {
        int ptr =arr1.length-1;
        int ptr2 =(arr2.length/2)-1;
        int ptr3 =arr2.length-1;

        while(ptr>=0 && ptr2>=0){
            if(arr1[ptr]>arr2[ptr2]) {
                arr2[ptr3--] = arr1[ptr--];
            }else {
                arr2[ptr3--] = arr2[ptr2--];
            }
        }
        while (ptr > 0) {
            arr2[ptr3--] = arr1[ptr--];
        }
        while (ptr2 > 0) {
            arr2[ptr3--] = arr2[ptr2--];
        }

    }

    private static void quickSort(int[] arr2, int start, int end) {
        if (start >= end) return;
        Random rand = new Random();
        int pivotInd = rand.nextInt((end - start) + 1) + start;
        int smallInd = start;
        int pivot = arr2[pivotInd];
        arr2[pivotInd]= arr2[smallInd];
        arr2[smallInd]=pivot;
        for (int i = start+1; i < end+1; i++) {
            if (arr2[i] < pivot) {
                smallInd++;
                int temp = arr2[smallInd];
                arr2[smallInd]=arr2[i];
                arr2[i]=temp;
            }

        }
        arr2[start]= arr2[smallInd];
        arr2[smallInd] =pivot;
        quickSort(arr2, start, smallInd-1);
        quickSort(arr2, smallInd + 1, end);
    }
}
