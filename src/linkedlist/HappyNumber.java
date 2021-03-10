package linkedlist;

import java.util.HashSet;
import java.util.Set;

public class HappyNumber {

    public static boolean isHappy(int n) {

        int nextNum = getNextNum(n);
        Set<Integer> setNum = new HashSet<>();
        while (nextNum != 1 && !setNum.contains(nextNum)) {
            setNum.add(nextNum);
            nextNum = getNextNum(nextNum);
        }
        return nextNum == 1;
    }

    private static int getNextNum(int n) {
        int sum = 0;
        while (n > 0) {
            sum = sum + (n % 10) * (n % 10);
            n = n / 10;
        }
        return sum;
    }

    //Number is happy when there is  a cycle in linked list of number and they converge to 1
    //to find the cycle length you could have another pointer in if block
    // when you have true in cycle scenario and go over cycle one more time to find the length
    public static boolean isHappy2(int n) {

        int nextNumSlow = n;
        int nextNumFast = n;
        while (true) {
            nextNumFast = getNextNum(getNextNum(nextNumFast));
            nextNumSlow = getNextNum(nextNumSlow);
            if (nextNumFast == nextNumSlow)
                return nextNumFast == 1;
        }
    }

    public static void main(String[] args) {
        System.out.println(isHappy2(4));
    }
}
