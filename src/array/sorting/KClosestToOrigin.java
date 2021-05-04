package array.sorting;

import java.util.Arrays;

public class KClosestToOrigin {

    public int[][] kClosest(int[][] points, int k) {

        int[] distance = new int[points.length];

        for (int i = 0; i < points.length; i++) {
            distance[i] = findDist(points[i]);
        }
        int[][] result = new int[k][2];

        Arrays.sort(distance);
        int thresholdDist = distance[k - 1];
        int r = 0;
        for (int i = 0; i < points.length; i++) {
            if (findDist(points[i]) <= thresholdDist)
                result[r++] = points[i];
        }
        return result;
    }

//    private void quickSelect(int[] distance, int start, int end, int[][] points, int[][] result, int k) {
//
//        if (start >= end) return;
//        Random rand = new Random();
//
//        int pivot = rand.nextInt((end - start) + 1) + start;        int pivotVal = distance[pivot];
//        swap(pivot,start,points);
//        swap1(pivot,start,distance);
//        int pivotIdx = start;
//        for (int i = start+1; i < end; i++) {
//           if(distance[i]<pivotVal){
//               swap1(start,i,distance);
//               swap(start,i,points);
//               pivotIdx++;
//           }
//        }
//        swap(pivotIdx,start,points);
//        if(pivotIdx==k) {
//            for (int i = 0; i < k; i++) {
//                result[i] =points[i];
//            }
//        } else if(pivotIdx>k) quickSelect(distance,start,pivotIdx,points,result,k);
//        else quickSelect(distance,start,k,points,result,k);
//    }
//
//    private void swap(int pivot, int start, int[][] nums) {
//        int[] temp = nums[pivot];
//        nums[pivot] = nums[start];
//        nums[start] = temp;
//    }
//    private void swap1(int pivot, int start, int[] nums) {
//        int temp = nums[pivot];
//        nums[pivot] = nums[start];
//        nums[start] = temp;
//    }

    private int findDist(int[] point) {
        int x = point[0];
        int y = point[1];
        return x * x + y * y;
    }
}
