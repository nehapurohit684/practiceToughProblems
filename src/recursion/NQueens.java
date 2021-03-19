package recursion;

import java.util.ArrayList;
import java.util.List;

public class NQueens {

    public static List<List<String>> solveNQueens(int n) {
        List<List<String>> results = new ArrayList<>();
        helper(n, 0, new ArrayList<>(), results);
        String[][] array = new String[results.size()][results.get(0).size()];
        for (int i = 0; i < results.size(); i++) {
            for (int j = 0; j < results.get(0).size(); j++) {
                array[i][j] = results.get(i).get(j);
            }

        }
        return results;
    }

    /**
     * Slate here is telling the column number in each row so slate[0] tells that in row 0 we put Queen in col slate[0]
     * Rather than keeping nyn slate we keep only n size slate
     * we select Queen's place in each row with each recursive call and
     * with each addition of new queen in each recursive call ,  we check if its not in conflict if its int in conflict we call until we reached at the end
     *
     * @param n
     * @param start
     * @param slate
     * @param results
     */
    private static void helper(int n, int start, List<Integer> slate, List<List<String>> results) {

        //backtracking- find conflict for 0 to i-1 and ith queen
        if (isConflict(slate)) return;
        if (start == n) {
            List<String> temp = new ArrayList<>();
            for (int j = 0; j < slate.size(); j++) {
                String s = createStr(slate.get(j), slate.size());
                temp.add(s);
            }
            results.add(temp);
            return;
        }

        for (int i = 0; i < n; i++) {
            slate.add(i);
            helper(n, start + 1, slate, results);
            slate.remove(slate.size() - 1);
        }

    }

    private static String createStr(int qPos, int size) {

        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < size; i++) {
            if (i == qPos) sb.append("Q");
            else sb.append(".");
        }
        return sb.toString();
    }

    /**
     * Queen conflict if new queen is in same column as any of previous queens
     * Or if new queen is in same diagonal as previous queens
     * to CHeck diagonal behaviour we check if row-col for any queen is same
     *
     * @param slate
     * @return
     */
    private static boolean isConflict(List<Integer> slate) {
        int size = slate.size() - 1;
        for (int earlier = 0; earlier < size; earlier++)
            if (slate.get(size) == slate.get(earlier) ||
                    Math.abs(slate.get(size) - slate.get(earlier)) == Math.abs(size - earlier)) {
                return true;
            }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(solveNQueens(4));
    }
}
