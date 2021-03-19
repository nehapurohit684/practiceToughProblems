package recursion;

import java.util.ArrayList;
import java.util.List;

public class GenerateParenthesis {
    /**
     * Leetcode 22
     * First create all possible bracket combination , it would be twice the number one of opening bracket and one for closing
     * then see which ones are valid using valid funciton
     *
     * @param n
     * @return
     */
    public List<String> generateParenthesis(int n) {

        List<String> result = new ArrayList<>();
        helper(n, 0, "", result);
        return result;

    }

    private void helper(int n, int start, String slate, List<String> result) {
        if (start == n * 2) {
            if (valid(slate.toCharArray())) result.add(new String(slate));
            return;
        }
        helper(n, start + 1, slate + "(", result);
        helper(n, start + 1, slate + ")", result);

    }

    /**
     * this is one is simpler solution where we start with left and right counter and
     * Any time we see if right is more that means ) bracket is more in string than ( we will end up with bad combination so we back track.
     *
     * @param n
     * @param left
     * @param right
     * @param slate
     * @param result
     */
    private void helper(int n, int left, int right, String slate, List<String> result) {
        //back track whenever you have extra ) bracket
        if (right > left) return;
        if (left == n && left == right) {
            result.add(new String(slate));
            return;
        }
        if (left < n)
            helper(n, left + 1, right, slate + "(", result);
        if (right < n)
            helper(n, left, right + 1, slate + ")", result);

    }

    public boolean valid(char[] current) {
        int balance = 0;
        for (char c : current) {
            if (c == '(') balance++;
            else balance--;
            if (balance < 0) return false;
        }
        return (balance == 0);
    }
}
