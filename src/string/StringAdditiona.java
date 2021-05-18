package string;

public class StringAdditiona {
    /**
     * 415. Add Strings
     * Hint : Start at right most digit and add two chars there
     * value = remainder of sum/10 and carry is sum/10
     *
     * @param num1
     * @param num2
     * @return
     */
    public String addStrings(String num1, String num2) {

        StringBuilder sb = new StringBuilder();

        int numPtr1 = num1.length() - 1;
        int numPtr2 = num2.length() - 1;
        int carry = 0;

        while (numPtr1 >= 0 || numPtr2 >= 0) {
            int i = numPtr1 >= 0 ? num1.charAt(numPtr1) - '0' : 0;
            int j = numPtr2 >= 0 ? num2.charAt(numPtr2) - '0' : 0;

            int value = (i + j + carry) % 10;
            carry = (i + j + carry) / 10;
            numPtr1--;
            numPtr2--;
            sb.append(value);
        }

        if (carry != 0) sb.append(carry);

        return sb.reverse().toString();

    }
}
