package array.sorting;

public class MedianArray {

    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
    int[] aux = new int[nums1.length+nums2.length];
    merge(nums1,nums2,aux);
    int length = aux.length;
    if(length%2==0){
        return (double)(aux[length/2]+aux[(length/2)-1])/2;
    }else
        return aux[length/2];
    }

    private static void merge(int[] left, int[] right, int[] aux) {
      int leftPointer =0;
      int rightPointer =0;
      int auxPointer =0;

      while(leftPointer<left.length && rightPointer<right.length){
          if(left[leftPointer]<=right[rightPointer]) aux[auxPointer++]=left[leftPointer++];
          else  aux[auxPointer++]=right[rightPointer++];
      }
        while(leftPointer<left.length){
            aux[auxPointer++]=left[leftPointer++];
        }
        while(rightPointer<right.length){
           aux[auxPointer++]=right[rightPointer++];
        }
    }

    public static void main(String[] args) throws Exception {
        int[] array = {3,4,7,9};
        int[] array1 = {1,2};
        System.out.println(findMedianSortedArrays(array,array1));
    }
}
