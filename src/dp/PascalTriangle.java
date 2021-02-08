package dp;

import java.util.ArrayList;
import java.util.List;

public class PascalTriangle {

    public static List<List<Integer>> generate(int numRows) {
        List<List<Integer>> results = new ArrayList<>();
        if (numRows == 0) return results;
        List<Integer> integerList = new ArrayList<>();
        results.add(integerList);
        results.get(0).add(1);

        for (int i = 1; i < numRows; i++) {
            List<Integer> integers = new ArrayList<>();
            List<Integer> prev = results.get(i - 1);

            integers.add(1);
            for (int j = 1; j < i; j++) {
                integers.add(prev.get(j - 1) + prev.get(j));
            }
            integers.add(1);
            results.add(integers);
        }
        return results;
    }

    public static void main(String[] args) {

        generate(0);

    }


}
