package general;

import java.util.ArrayList;
import java.util.List;

public class RecursionPlaindrome2 {

    public static List<List<String>> partition(String s) {
        List<List<String>> results = new ArrayList<>();
        List<String> slate = new ArrayList<>();
        helper(0,s,slate,results);
        return results;
    }

    private static void helper(int pos, String s, List<String> slate, List<List<String>> results) {
        if(pos==s.length()){
            results.add(new ArrayList<String>(slate));
        }
        for (int i = pos; i < s.length(); i++) {
            if(isPalindrome(s,pos,i)){
            slate.add(s.substring(pos,i+1));
            helper(i+1,s,slate,results);
            slate.remove(slate.size()-1);
        }
    }
}

    private static boolean isPalindrome(String substring, int pos, int i) {
      char[] givenString = substring.toCharArray();
      while (pos<i) {
          if (givenString[pos++] != givenString[i--])
              return false;
      }
      return true;
    }

    static String[] generate_palindromic_decompositions(String s) {
        List<String> results = new ArrayList<>();
        helperArray(0,s,new ArrayList<>(),results);
        return results.toArray(new String[0]);
    }

    private static void helperArray(int pos, String s, List<String> slate, List<String> results) {
        if(pos==s.length()){
            String resulttobe = "";
            List<String> resultsStrings= new ArrayList<>(slate);
            for (String s1: resultsStrings) {
                resulttobe =resulttobe.concat(s1+"|");
            }
            results.add(resulttobe.substring(0,resulttobe.length()-1));
        }
        for (int i = pos; i < s.length(); i++) {
            if(isPalindrome(s,pos,i)){
                slate.add(s.substring(pos,i+1));
                helperArray(i+1,s,slate,results);
                slate.remove(slate.size()-1);
            }
        }
    }

    public static void main(String[] args) {

        generate_palindromic_decompositions("abcb");
        partition("abcb");

    }
}
