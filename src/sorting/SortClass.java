package sorting;

import java.util.*;

public class SortClass {

    public static ArrayList<Integer> selectionSort(ArrayList<Integer> array) {
        for (int i = 0; i < array.size() - 1; i++) {
            int min = i;
            for (int j = i + 1; j < array.size(); j++)
                if (array.get(j) < array.get(min))
                    min = j;

            int temp = array.get(min);
            array.set(min, array.get(i));
            array.set(i, temp);
        }
        return array;
    }

    public static ArrayList<Integer> bubbleSort(ArrayList<Integer> array) {
        for (int j = 0; j < array.size(); j++) {
            for (int i = 1; i < array.size(); i++) {
                if (array.get(i) < array.get(i - 1)) {
                    int temp = array.get(i - 1);
                    array.set(i - 1, array.get(i));
                    array.set(i, temp);
                }
            }
        }
        return array;
    }

    public static ArrayList<Integer> insertionSort(ArrayList<Integer> array, int size) {
        if (size <= 1) return array;
        insertionSort(array, size - 1);
        int temp = array.get(size - 1);
        int j = size - 2;
        while (j >= 0 && array.get(j) > temp) {
            array.set(j + 1, array.get(j));
            j--;
        }
        array.set(j + 1, temp);

        return array;
    }

    public static List<Integer> mergeSort(List<Integer> array) {
        if (array.size() <= 1) return array;
        int mid = array.size() / 2;
        List<Integer> left = new ArrayList<>();
        List<Integer> right = new ArrayList<>();
        for (int i = 0; i < mid; i++) {
            left.add(i, array.get(i));
        }
        for (int i = 0; i < array.size() - mid; i++) {
            right.add(i, array.get(mid + i));
        }
        left = mergeSort(left);
        right = mergeSort(right);
        return merge(left, right);
    }

    private static List<Integer> merge(List<Integer> left, List<Integer> right) {
        ArrayList<Integer> aux = new ArrayList<>();
        int leftPoiner, mergePointer, rightPointer;
        leftPoiner = rightPointer = mergePointer = 0;

        while (left.size() > leftPoiner && right.size() > rightPointer) {
            if (left.get(leftPoiner) <= right.get(rightPointer)) {
                aux.add(mergePointer, left.get(leftPoiner));
                leftPoiner++;
                mergePointer++;
            } else {
                aux.add(mergePointer, right.get(rightPointer));
                rightPointer++;
                mergePointer++;
            }
        }
        while (left.size() > leftPoiner) {
            aux.add(mergePointer, left.get(leftPoiner));
            mergePointer++;
            leftPoiner++;
        }
        while (right.size() > rightPointer) {
            aux.add(mergePointer, right.get(rightPointer));
            mergePointer++;
            rightPointer++;
        }

        return aux;
    }

    public static ArrayList<Integer> quickSort(ArrayList<Integer> array, int start, int end) {
        if (start >= end) return array;
        Random rand = new Random();

        int pivotInd = rand.nextInt((end - start) + 1) + start;
        int smallInd = start;
        int pivot = array.get(pivotInd);
        array.set(pivotInd, array.get(smallInd));
        array.set(smallInd, pivot);
        for (int i = start+1; i < end+1; i++) {
            if (array.get(i) < pivot) {
                smallInd++;
                int temp = array.get(smallInd);
                array.set(smallInd, array.get(i));
                array.set(i, temp);
            }
        }

        array.set(start, array.get(smallInd));
        array.set(smallInd, pivot);
        quickSort(array, start, smallInd-1);
        quickSort(array, smallInd + 1, end);
        return array;

    }

    static List resultHeap = new ArrayList();

    private static List heapSort(ArrayList<Integer> list) {
        list = heappifyDown(list);
        list = heappifyUp(list);
        int currentHeight=0;

        int max = list.get(0);
        resultHeap.add(max);
        while (list.size() > 1) {
            list.remove(0);
            heapSort(list);
        }
        return resultHeap;
    }
    public static int findKthLargest(int[] nums, int k) {
        int res = 0;
        try {
            res=findKthLargest(nums,0,nums.length,nums.length-k);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return res;
    }

    private static int findKthLargest(int[] nums, int start, int end, int k) throws Exception {
        if(start>=end) throw new Exception("Not valid");
        int pivot = partition(nums, start, end);
        if (pivot == k) return nums[k];
        else if (pivot > k)
            return findKthLargest(nums, start, pivot, k);
        return findKthLargest(nums, pivot+1,end, k);

    }

    private static ArrayList<Integer> heappifyDown(ArrayList<Integer> list) {
        for (int i = 0; i < (list.size()) / 2; i++) {
            int max = finMaxOfThree(list.get(2 * i), list.get(i), list.get(2 * i + 1));
            if (list.get(i) == max) continue;
            else {
                int temp = list.get(i);
                if (list.get(2 * i) == max) {
                    list.set(i, list.get(2 * i));
                    list.set((2 * i), temp);
                } else {
                    list.set(i, list.get(2 * i + 1));
                    list.set((2 * i + 1), temp);
                }
            }
        }
        return list;
    }

    private static ArrayList<Integer> heappifyUp(ArrayList<Integer> list) {
        for (int i = (list.size()) - 1; i > 0; i--) {
            if (list.get(i) <= list.get(i / 2)) continue;
            else {
                int temp = list.get(i);
                list.set(i, list.get(i / 2));
                list.set((i / 2), temp);
            }
        }
        return list;
    }

    private static int finMaxOfThree(int num1, int num2, int num3) {
        if (num1 >= num2 && num1 >= num3) return num1;
        else if (num2 >= num1 && num2 >= num3) return num2;
        else return num3;
    }


    public static void main(String[] args) throws Exception {
        ArrayList<Integer> list = new ArrayList();
        list.add(2);
        list.add(4);
        list.add(3);
        list.add(7);
        list.add(9);
        list.add(12);
        int[] array = {10,12,3,4,7,9,2};

        //  System.out.println(selectionSort(list));
        //   System.out.println(bubbleSort(list));
//          System.out.println(insertionSort(list,list.size()));
         System.out.println(quickSort(list,0,list.size()-1));
       // System.out.println(heapSort(list));
//        try {
//            System.out.println(findMedian(list));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        System.out.println(findKthLargest(array,2));
//        System.out.println(kLargest(list,0,list.size(),list.size()-3));
//        System.out.println(kLargestQueue(list, 3));

    }
    private static int kLargest(ArrayList<Integer> list, int start, int end, int i) throws Exception {
        if(start>=end) throw new Exception("Not valid");
        int pivot = partition(list, start, end);
        if (pivot == i) return list.get(i);
        else if (pivot > i)
            return kLargest(list, start, pivot, i);
        return kLargest(list, pivot+1,end, i);
    }

    private static int partition(ArrayList<Integer> list, int start, int end) {
        int smallIndex = 0;
        if (start >= end) return smallIndex;
        Random rand = new Random();
        int pivotInd = rand.nextInt((end - start) + 1) + start;
        for (int i = 1; i < list.size(); i++) {
        if(list.get(i)<list.get(pivotInd)){
            smallIndex++;
        }
    }
        return smallIndex;
    }
    private static int partition(int[] list, int start, int end) {
        int smallIndex = start;
        if (start >= end) return smallIndex;
        Random rand = new Random();
        int pivotInd = rand.nextInt((end - start) + 1) + start;
        int pivot = list[pivotInd];
        list[pivotInd] =list[start];
        list[start] = pivot;
        for (int i = start+1; i < end; i++) {
            if(list[i]<list[pivotInd]){
                smallIndex++;
                int temp1 = list[i];
                list[i] =list[smallIndex];
                list[smallIndex] = temp1;
            }
        }
        int temp2 = list[smallIndex];
        list[smallIndex] = pivot;
        list[pivotInd] = temp2;
        return smallIndex;
    }

    private static int kLargestQueue(ArrayList<Integer> list, int i)  {
    PriorityQueue<Integer> queue = new PriorityQueue<>();
        for (Integer j:list) {
            if (queue.size() < i) {
                queue.add(j);
            } else {
                queue.add(j);
                queue.remove();
            }
        }
    return queue.poll();
    }

    private PriorityQueue<Integer> makeMaxHeap(ArrayList<Integer> list, double median)  {
        PriorityQueue<Integer> queue = new PriorityQueue<Integer>(list.size(), new Comparator<Integer>() {
            @Override
            public int compare(Integer t2, Integer t1) {
                return t1-t2;
            }
        });
        for (Integer j:list) {
            if (j< median) {
                queue.add(j);
            }
        }
        return queue;
    }
    private PriorityQueue<Integer> makeMinHeap(ArrayList<Integer> list, double median)  {
        PriorityQueue<Integer> queue = new PriorityQueue<Integer>();
        for (Integer j:list) {
            if (j> median) {
                queue.add(j);
            }
        }
        return queue;
    }
    PriorityQueue<Integer> maxHeap;
    PriorityQueue<Integer> minHeap;

    private static double findMedian(ArrayList<Integer> list) throws Exception {
        if(list.size()%2 ==0) {
            return (kLargest(list, 0, list.size(), list.size() / 2)
                    + kLargest(list, 0, list.size(), list.size() / 2 + 1)) / 2;
        }
        else return kLargest(list,0,list.size(),list.size()/2);
    }
 }
