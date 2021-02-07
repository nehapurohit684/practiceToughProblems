package dp;

import java.util.ArrayList;
import java.util.List;

public class MinPathSumTriangle {

    public static void main(String[] args) {
        List<Integer> integerList = new ArrayList<>();
        integerList.add(2);
        List<Integer> integerList2 = new ArrayList<>();
        integerList2.add(3);
        integerList2.add(4);
        List<Integer> integerList3 = new ArrayList<>();
        integerList3.add(6);
        integerList3.add(5);
        integerList3.add(7);
        List<Integer> integerList4 = new ArrayList<>();
        integerList4.add(4);
        integerList4.add(1);
        integerList4.add(8);
        integerList4.add(3);

        List<List<Integer>> input = new ArrayList<>();
        input.add(integerList);
        input.add(integerList2);
        input.add(integerList3);
        input.add(integerList4);
        System.out.println(minimumTotalArray(input));
    }

    public static int minimumTotal(List<List<Integer>> triangle) {

        List<List<Integer>> minSum = new ArrayList<>();
        minSum.add(triangle.get(0));

        for(int i = 1; i<triangle.size(); i++){
            List<Integer> prev = minSum.get(i-1);
            List<Integer> current = new ArrayList<>();

            current.add(prev.get(0)+triangle.get(i).get(0));

            for (int j = 1; j < i; j++) {
                current.add(Math.min(prev.get(j-1),prev.get(j))+ triangle.get(i).get(j));
            }

            current.add(prev.get(prev.size()-1)+triangle.get(i).get(triangle.get(i).size()-1));
            minSum.add(current);
        }

        return findMin(minSum.get(triangle.size()-1));
    }

    private static int findMin(List<Integer> integers) {
        int min = integers.get(0);
        for (int i = 1 ;i < integers.size() ; i++) {
            if(min>integers.get(i))min=integers.get(i);
        }
        return min;
    }

    public static int minimumTotalArray(List<List<Integer>> triangle) {
        int size = triangle.size();
       int[][] minsSum = new int[size][triangle.get(size-1).size()];
        minsSum[0][0] = triangle.get(0).get(0);

        for(int i=1; i<triangle.size();i++){
            minsSum[i][0] = minsSum[i-1][0]+triangle.get(i).get(0);
            minsSum[i][triangle.get(i).size()-1]=minsSum[i-1][triangle.get(i-1).size()-1]+triangle.get(i).get(triangle.get(i).size()-1);
        }

        for(int i=2;i<triangle.size(); i++){
            for (int j = 1; j < i; j++) {
                minsSum[i][j]= Math.min(minsSum[i-1][j-1],minsSum[i-1][j]) + triangle.get(i).get(j);
            }
        }
        return findMin(minsSum[triangle.size()-1]);
    }

    private static int findMin(int[] inputArray) {

        int minValue = inputArray[0];
        for(int i=1;i<inputArray.length;i++){
            if(inputArray[i] < minValue){
                minValue = inputArray[i];
            }
        }
        return minValue;
    }

}
