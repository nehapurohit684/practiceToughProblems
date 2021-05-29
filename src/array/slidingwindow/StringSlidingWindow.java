package array.slidingwindow;

import java.util.HashMap;
import java.util.Map;

public class StringSlidingWindow {
    /**
     * Given two strings s1 and s2, return true if s2 contains the permutation of s1.
     *
     * @param s1
     * @param s2
     * @return
     */
    public boolean checkInclusion(String s1, String s2) {
        if (s1.length() > s2.length()) return false;

        char[] c1 = s1.toCharArray();
        char[] c2 = s2.toCharArray();
        Map<Character, Integer> freq1 = new HashMap<>();
        Map<Character, Integer> freq2 = new HashMap<>();
        int k = s1.length();
        for (int i = 0; i < k; i++) {
            freq1.put(c1[i], freq1.getOrDefault(c1[i], 0) + 1);
        }
        boolean result = true;
        for (int i = 0; i < k; i++) {
            freq2.put(c2[i], freq2.getOrDefault(c2[i], 0) + 1);
        }
        if (freq1.equals(freq2)) return true;
        for (int i = k; i < c2.length; i++) {
            freq2.put(c2[i], freq2.getOrDefault(c2[i], 0) + 1);
            if (freq2.containsKey(c2[i - k])) freq2.put(c2[i - k], freq2.get(c2[i - k]) - 1);
            if (freq2.get(c2[i - k]) == 0) freq2.remove(c2[i - k]);
            if (freq1.equals(freq2)) return true;
        }
        return false;
    }


    /**
     * 1456. Maximum Number of Vowels in a Substring of Given Length
     * For chars also follow same thing initialize update the
     * matrix by adding params from right and subtracting from left
     *
     * @param s
     * @param k
     * @return
     */
    public int maxVowels(String s, int k) {

        int numVowels = 0;
        char[] chars = s.toCharArray();

        for (int i = 0; i < k; i++) {
            if (chars[i] == 'a' | chars[i] == 'e' | chars[i] == 'i' | chars[i] == 'o' | chars[i] == 'u') {
                numVowels++;
            }
        }
        int maxVowel = numVowels;

        for (int i = k; i < chars.length; i++) {
            if (chars[i] == 'a' | chars[i] == 'e' | chars[i] == 'i' | chars[i] == 'o' | chars[i] == 'u') {
                numVowels++;
            }
            if (chars[i - k] == 'a' | chars[i - k] == 'e' | chars[i - k] == 'i' | chars[i - k] == 'o' | chars[i - k] == 'u') {
                numVowels--;
            }

            maxVowel = Math.max(numVowels, maxVowel);

        }
        return maxVowel;
    }

    /**
     * 1100. Find K-Length Substrings With No Repeated Characters
     * Hint: for each fixed length window of size K see if all chars are unique
     * to determine uniqness use hashtable
     *
     * @param S
     * @param K
     * @return
     */
    public int numKLenSubstrNoRepeats(String S, int K) {

        Map<Character, Integer> map = new HashMap<>();
        char[] chars = S.toCharArray();
        int count = 0;
        if (chars.length < K) return count;
        for (int i = 0; i < K; i++) {
            map.put(chars[i], map.getOrDefault(chars[i], 0) + 1);
            if (map.size() == K) {
                count++;
            }
        }
        for (int i = K; i < S.length(); i++) {
            map.put(chars[i], map.getOrDefault(chars[i], 0) + 1);
            if (map.containsKey(chars[i - K])) {
                map.put(chars[i - K], map.get(chars[i - K]) - 1);
                if (map.get(chars[i - K]) == 0) map.remove(chars[i - K]);
            }
            if (map.size() == K) {
                count++;
            }

        }
        return count;
    }
}
