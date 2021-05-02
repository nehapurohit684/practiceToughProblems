package math;

import java.util.HashMap;
import java.util.Map;

public class BinaryToNumber {
    public class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    //leetcode 1290
    public int getDecimalValue(ListNode head) {

        int result = 0;

        while (head != null) {
            //rather than doing multiplication by 2 we move number to left by 1
            result = (result << 1) | head.val;
            head = head.next;
        }

        return result;
    }

    //leet code 476
    public int findComplement(int num) {
        int noBits = 0;
        int copy = num;
        while (num != 0) {
            noBits++;
            num = num >> 1;
        }
        //to toggle exor by 1
        copy = copy ^ ((1 << noBits) - 1);

        return copy;
    }

    //leetcode 191. Number of 1 Bits
    public int hammingWeight(int num) {
        int noBits = 0;
        int mask = 1;
        // if we keep while(n!=0) we reach to an infinite loop when all digits are 1,
        // so to restrict for loop we rn it only 32 times
        for (int i = 0; i < 32; i++) {
            if ((mask & num) != 0) {
                noBits++;
            }
            num = num >> 1;
        }
        return noBits;
    }


    public static String rotationalCipher(String input, int rotationFactor) {
        // Write your code here
        Map<Character, Integer> upperMap = new HashMap<>();
        Map<Character, Integer> lowerMap = new HashMap<>();
        String upper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lower = "abcdefghijklmnopqrstuvwxyz";
        for (int i = 0; i < upper.length(); i++) {
            upperMap.put(upper.charAt(i), i);
        }
        for (int i = 0; i < lower.length(); i++) {
            lowerMap.put(lower.charAt(i), i);
        }


        StringBuilder result = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {
            if (Character.isDigit(input.charAt(i))) {
                int k = Character.getNumericValue(input.charAt(i)) + rotationFactor;
                result.append(k % 10);
            } else if (upperMap.containsKey(input.charAt(i))) {
                result.append(upper.charAt((upperMap.get(input.charAt(i)) + rotationFactor) % upper.length()));

            } else if (lowerMap.containsKey(input.charAt(i))) {
                result.append(lower.charAt((lowerMap.get(input.charAt(i)) + rotationFactor) % lower.length()));
            } else {
                result.append(input.charAt(i));
            }
        }
        return result.toString();
    }

    public static void main(String[] args) {
        System.out.println(rotationalCipher("Zebra-493", 3));
    }
    //231
    //342
    //326
    //78
}
