package dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WordBreakDP {

    public static boolean wordBreak(String s, List<String> wordDict) {

        char[] chars = s.toCharArray();
        boolean[] table = new boolean[chars.length+1];

        Arrays.fill(table,false);
        //base case
        table[0] = true;
        if (wordDict.contains(s))table[chars.length]=true;
        //recursive case
        for (int i=1;i<chars.length+1;i++){
            //j iterate over substring of all length from 0 to i
            for (int j = 0; j < i; j++) {
                //if you check first table[j] then it will make sure you are not doing repetitive work.
                if (table[j] && wordDict.contains(s.substring(j, i))) {
                    table[i] = true;
                }
            }
        }
        return table[chars.length];
    }

    public static void main(String[] args) {
        List<String> dictionary = new ArrayList<>();
        dictionary.add("apple");
        dictionary.add("pen");
        System.out.println(wordBreak("applepenapple",dictionary));
    }
}
