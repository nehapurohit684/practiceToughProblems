package design;

import java.io.File;
import java.io.FileInputStream;
import java.util.*;

/**
 * Save tweets in Binary tree - Not necessarly balanced binary tree
 */
public class TweetCounts {

    private class TreeNode {

        private int val;
        private TreeNode left;
        private TreeNode right;

        private TreeNode(int data) {
            val = data;
            left = null;
            right = null;
        }

    }

    private Map<String, TreeNode> map;

    public TweetCounts() {
        map = new HashMap<>();
    }

    private TreeNode insert(TreeNode root, int val) {
        if (root == null) {
            root = new TreeNode(val);
        } else if (root.val <= val) {
            root.right = insert(root.right, val);
        } else {
            root.left = insert(root.left, val);
        }
        return root;
    }

    /**
     * void recordTweet(String tweetName, int time) Stores the tweetName at the recorded time (in seconds).
     * @param name
     * @param time
     */
    public void recordTweet(String name, int time) {
        TreeNode root = map.get(name);
        root = insert(root, time);
        map.put(name, root);
    }

    private int findCount(TreeNode root, int l, int r) {
        if (root == null || l >= r) {
            return 0;
        }
        if (root.val <= l) {
            int add = root.val == l ? 1 : 0;
            return add + findCount(root.right, l, r);
        }
        if (root.val >= r) {
            return findCount(root.left, l, r);
        }
        return 1 + findCount(root.left, l, r) + findCount(root.right, l, r);
    }

    /**
     * List<Integer> getTweetCountsPerFrequency(String freq, String tweetName, int startTime, int endTime) Returns a list of integers representing the number of tweets with tweetName in each time chunk for the given period of time [startTime, endTime] (in seconds) and frequency freq.
     * freq is one of "minute", "hour", or "day" representing a frequency of every minute, hour, or day respectively.
     * @param freq
     * @param name
     * @param start
     * @param end
     * @return
     */
    public List<Integer> getTweetCountsPerFrequency(String freq, String name, int start, int end) {
        int d = 0;
        TreeNode root = map.get(name);
        List<Integer> res = new ArrayList<>();
        if (freq.equals("minute")) {
            d = 60;
        } else if (freq.equals("hour")) {
            d = 3600;
        } else {
            d = 86400;
        }
        while (start + d <= end) {
            int count = findCount(root, start, start + d);
            start = start + d;
            res.add(count);
        }
        if (start <= end) {
            int count = findCount(root, start, end + 1);
            res.add(count);
            start = end + 1;
        }
        return res;
    }
    /* ... other methods and variables here ... */

    // Count the frequency of each character in a text file, for use in (human) language detection.
    public static int[] getCharacterCounts(String filename) throws Exception {
        File fileHandle = new File(filename);
        FileInputStream inputStream = new FileInputStream(fileHandle);
        byte[] byteArray = new byte[1];
        int readBytes = 0;
        int[] characterCounts = new int[256];
        while ((readBytes = inputStream.read(byteArray)) != -1) {
            int byteValue = byteArray[0] & 0xFF; // Convert signed byte to 0-255 value
            characterCounts[byteValue]++;
        }
        return characterCounts;
    }
}

