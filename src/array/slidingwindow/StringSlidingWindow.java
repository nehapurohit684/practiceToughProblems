package array.slidingwindow;

import java.util.*;

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
     * if Number of unique keys == k when we considered any sub array of length k
     * means its a substring with all diff chars
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

    /**
     * 239. Sliding Window Maximum
     * You are given an array of integers nums,
     * there is a sliding window of size k which is moving from the very left of the array to the very right.
     * You can only see the k numbers in the window. Each time the sliding window moves right by one position.
     *
     * Hint: PriorityQueue cof size k could be used to maintain max for window size K but tit will be logK operation
     * Instead we want data strcuture where we can delete easily from back and front and miantain order
     * Dequeue: When we see a number at ith position greater than last element in queue we delete all numbers smaller than this
     * because if you encounter bigger number then all numbers smaller than it can never be max of any size array
     * how do you maintain you find max in k window, you keep only max number until its part of window
     * how do you make sure if the number is part of max window keep comparing the biggest number to i-k if
     * i-k number goes from window in each iteration, if this number is biggest in dequeue then delete it from queue as well
     * @param nums
     * @param k
     * @return
     */
    public int[] maxSlidingWindow(int[] nums, int k) {

        Deque<Integer> queue = new ArrayDeque<>();

        List<Integer> list = new ArrayList<>();

        for(int i=0;i<k;i++){
            while (!queue.isEmpty() && queue.getLast()<nums[i]){
                queue.removeLast();
            }
            queue.addLast(nums[i]);
        }
        list.add(queue.peek());

        for(int i=k ;i<nums.length;i++){

            if(nums[i-k] == queue.getFirst()){
                queue.removeFirst();
            }
            while (!queue.isEmpty() && queue.getLast()<nums[i]){
                queue.removeLast();
            }
            queue.addLast(nums[i]);
            list.add(queue.peek());

        }

        return list.parallelStream().mapToInt(Integer::intValue).toArray();
    }
}
