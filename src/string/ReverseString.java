package string;

public class ReverseString {


    static String reverseString(String s) {
        char[] chars = s.toCharArray();
        int i = 0;
        int j = chars.length - 1;
        while (i < j) {
            swapChar(chars, i, j);
            i++;
            j--;
        }
        return new String(chars);
    }

    private static void swapChar(char[] chars, int i, int j) {
        char temp = chars[i];
        chars[i] = chars[j];
        chars[j] = temp;
    }

    public static void main(String[] args) {
        System.out.println(reverseString("Neha"));
    }
}
