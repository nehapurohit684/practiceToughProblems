package general;

import java.util.ArrayList;
import java.util.List;

public class PascalTriDP {

    public List<List<Integer>> generate(int numRows) {

        List<List<Integer>> results = new ArrayList<>();

            List<Integer> initial = new ArrayList<>();
            initial.add(0,1);
            results.add(0,initial);

        for (int i = 1; i < numRows; i++) {
            List<Integer> row = new ArrayList<>();
            List<Integer> prevRow = results.get(i-1);

            row.add(1);

            for (int j = 1; j < i; j++) {
                row.add(prevRow.get(j-1)+prevRow.get(j));
            }
            row.add(1);
            results.add(row);

        }



        return results;
    }
}
