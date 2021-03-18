package recursion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LetterCasePermutation {
    List<String> results = new ArrayList<>();

    /**
     * 784. Letter Case Permutation
     * You could create slate as string unlike below code that way you could use immutable slate but it increase space used
     *
     * @param S
     * @return
     */
    public List<String> letterCasePermutation(String S) {
        List<Character> slate = new ArrayList<>();
        helper(S.toCharArray(), 0, slate);
        return results;
    }

    private void helper(char[] toCharArray, int start, List<Character> slate) {
        if (start >= toCharArray.length) {
            StringBuilder sb = new StringBuilder();
            for (Character ch : slate) {
                sb.append(ch);
            }
            results.add(sb.toString());
            return;
        }
        if (Character.isLetter(toCharArray[start])) {
            slate.add(Character.toLowerCase(toCharArray[start]));
            helper(toCharArray, start + 1, slate);
            slate.remove(slate.size() - 1);
            slate.add(Character.toUpperCase(toCharArray[start]));
            helper(toCharArray, start + 1, slate);
            slate.remove(slate.size() - 1);
        }
        slate.add(toCharArray[start]);
        helper(toCharArray, start + 1, slate);
        slate.remove(slate.size() - 1);
    }

    public List<String> letterCombinations(String digits) {
        List<String> results = new ArrayList<>();
        if (digits.equalsIgnoreCase("")) return results;
        Map<Character, char[]> digitMap = createMap();
        helperLetter(digits.toCharArray(), 0, digitMap, new ArrayList<>(), results);
        return results;


    }

    private void helperLetter(char[] charArray, int start, Map<Character, char[]> digitMap, List<Character> slate, List<String> results) {
        if (start >= charArray.length) {
            StringBuilder sb = new StringBuilder();
            for (Character ch : slate) {
                sb.append(ch);
            }
            results.add(sb.toString());
            return;
        }
        for (char c : digitMap.get(charArray[start])) {
            slate.add(c);
            helperLetter(charArray, start + 1, digitMap, slate, results);
            slate.remove(slate.size() - 1);
        }

    }


    private Map<Character, char[]> createMap() {
        Map<Character, char[]> map = new HashMap<>();
        map.put('2', new char[]{'a', 'b', 'c'});
        map.put('3', new char[]{'d', 'e', 'f'});
        map.put('4', new char[]{'g', 'h', 'i'});
        map.put('5', new char[]{'j', 'k', 'l'});
        map.put('6', new char[]{'m', 'n', 'o'});
        map.put('7', new char[]{'p', 'q', 'r', 's'});
        map.put('8', new char[]{'t', 'u', 'v'});
        map.put('9', new char[]{'w', 'x', 'y', 'z'});

        return map;
    }
}
