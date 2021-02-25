package string;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ControlSetProb {

    /**
     * Given a string what is length of shortest substring controlled by a set
     * e.g {l,r,w}
     * HelloWorld -->worl length--4
     */

    public static int countSubStringCS(String s, List<Character> CS) {
        int minLength = Integer.MAX_VALUE;
        for (int i = 0; i < s.length(); i++) {
            for (int j = i + 1; j < s.length(); j++) {
                // subStr = s.substring(i,j);
                List<Character> controlSet = new ArrayList<>(CS);
                for (int k = i; k < j; k++) {
                    if (controlSet.contains(s.charAt(k)))
                        controlSet.remove(s.charAt(k));
                    if (controlSet.size() == 0)
                        minLength = Math.min(minLength, j - 1 + 1);
                }
            }
        }
        return minLength;
    }

    /**
     * Given a string what is length of shortest substring controlled by a set
     * Window Slider Approach
     * 1. Increment i,j unitl you hit a char from CS
     * 2. Once you hit increment J unit all chars in CS are cpatured. put count of all chars of CS in hashMap
     * 3. Capture the length j-1
     * 4. increment i and update hMap to decrement count based on char at i.
     * 5. As soon as substring is not in CS, then get the min  =j-i+1-> Extra one is because increment of i caused the substring not controlled
     * 6. Got to Step 2 , continue till j reaches to end of the string and increment of i makes any counter of char in hashmap to 0
     */

    public static int countSubStringCSImproved(String s, List<Character> CS) {
        int minLength = Integer.MAX_VALUE;
        Map<Character, Integer> characterIntegerMap = new HashMap<>();
        for (char c : CS) {
            characterIntegerMap.put(c, 0);
        }
        int forwardPtr = 0, endPtr = 0;
        for (int i = 0; i < s.length(); i++) {
            if (characterIntegerMap.containsKey(s.charAt(i))) {
                int currentCount = characterIntegerMap.get(s.charAt(i)).intValue() + 1;
                characterIntegerMap.put(s.charAt(i), currentCount);
                endPtr++;
            }
            forwardPtr++;
            endPtr++;

        }
        return minLength;
    }
}
