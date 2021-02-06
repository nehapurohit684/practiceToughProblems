package general;

import java.util.ArrayList;
import java.util.List;

public class PalindromeRecursion {

    public static void main(String[] args) {

        generate_palindromic_decompositions("abcb");

    }

    static String[] generate_palindromic_decompositions(String s) {
        List<String> results = new ArrayList<>();
        char[] slate =new char[2*s.length()];
        char[] input = s.toCharArray();
        helper(input,slate,0,0,results);

        return results.toArray(new String[0]);

    }

    public List<List<String>> partition(String s) {
        List<List<String>> result = new ArrayList<>();
        dfs(0, result, new ArrayList<String>(), s);
        return result;
    }

    void dfs(int start, List<List<String>> result, ArrayList<String> currentList, String s) {
        if (start >= s.length()) result.add(new ArrayList<>(currentList));
        for (int end = start; end < s.length(); end++) {
            if (isPalindrome(s, start, end)) {
                // add current substring in the currentList
                currentList.add(s.substring(start, end + 1));
                dfs(end + 1, result, currentList, s);
                // backtrack and remove the current substring from currentList
                currentList.remove(currentList.size() - 1);
            }
        }
    }

    boolean isPalindrome(String s, int low, int high) {
        while (low < high) {
            if (s.charAt(low++) != s.charAt(high--)) return false;
        }
        return true;
    }

    static void helper(char[] input,char[] slate,int pos,int sartoSlate, List<String> results){

        if(pos==input.length){
            results.add(new String(slate,0,sartoSlate-1));
            return;
        }

        for (int i = pos; i < input.length; i++) {
            int length = i-pos;
            slate[sartoSlate+length] =input[i];
            if(isPalindrome(input,pos,i)) {
                slate[sartoSlate+length+1] = '|';
                helper(input, slate,pos+length+1,sartoSlate+length+2,results);

            }
        }
    }

    private static boolean isPalindrome(char[] input, int start, int i) {
        while (start<i) {
            if (input[start] != input[i]) {
                return false;
            }
        start++;i--;
        }
        return true;
    }

    static String[] generate_palindromic_decompositions1(String s) {
        List<String> results = new ArrayList<>();

        generate(s.toCharArray(), 0, new char[2 * s.length()], 0, results);

        return results.toArray(new String[results.size()]);
    }

    static void generate(char[] s, int pos, char[] out, int j, List<String> results) {
        if (pos == s.length) {
            results.add(new String(out, 0, j - 1));
            return;
        }

        for (int i = pos; i < s.length; i++) {
            int lengthOfCharAdded = i - pos;
            out[j + lengthOfCharAdded] = s[i];
            if (isPalindrome(s, pos, i)) {
                out[j + (i - pos) + 1] = '|';
                generate(s, pos + lengthOfCharAdded + 1, out, j + lengthOfCharAdded + 2 /* 1 for | and 1 for next char*/, results);
            }
        }
    }

}
