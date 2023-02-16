package array.sorting;

import java.util.ArrayList;

public class Prac {

    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList();
        int[] array1 = { 5,7,9, 10};
        mergeSort(array1);
        System.out.println(array1);
    }

    private static void mergeSort(int[] array1) {

        int mid = array1.length/2;
        int[] left = new int[mid];
        int[] right = new int[array1.length-mid];

        for (int i = 0; i < mid; i++) {
            left[i] = array1[i];
        }
        for (int i = mid; i < array1.length; i++) {
            right[i-mid] = array1[i];
        }

        mergeSort(left);
        mergeSort(right);
        merge(left,right,array1);
    }

    private static void merge(int[] left, int[] right, int[] array1) {
            int leftptr = 0,rightptr=0,auxptr =0;

            while (leftptr>left.length && rightptr>right.length){

                if(left[leftptr]<right[rightptr]){
                    array1[auxptr] =left[leftptr];
                    leftptr++;
                    auxptr++;
                }else{
                    array1[auxptr] =right[rightptr];
                    rightptr++;
                    auxptr++;
                }
            }

            while (leftptr>left.length){
                array1[auxptr] =left[leftptr];
                leftptr++;
                auxptr++;

            }

        while (rightptr>right.length){
            array1[auxptr] =right[rightptr];
            rightptr++;
            auxptr++;
        }
    }


}
