package string;

public class PalindromeString {

    static boolean checkPalindrome(String s) {
        char[] chars = s.toCharArray();

        int i = 0;
        int j = chars.length - 1;

        while (i < j) {
            if (chars[i] != chars[j]) return false;
            else {
                i++;
                j--;
            }
        }
        return true;
    }

    /**
     * leet code 680: Given a non-empty string s, you may delete at most one character to make in palindrome
     * Hint : If any chars at i and j not match then try to see if i+1 to j is palindrome or i to j-1 is palindrome
     *
     * @param s
     * @return
     */
    public boolean validPalindrome(String s) {
        char[] chars = s.toCharArray();

        int i = 0;
        int j = chars.length - 1;

        while (i <= j) {
            if (chars[i] != chars[j]) {
                return isPalindrome(s, i + 1, j) | isPalindrome(s, i, j - 1);
            }
            ;
            i++;
            j--;
        }
        return true;
    }

    public boolean isPalindrome(String s, int i, int j) {
        char[] chars = s.toCharArray();
        while (i <= j) {
            if (chars[i] != chars[j]) {
                return false;
            }
            ;
            i++;
            j--;
        }
        return true;
    }

    /**
     * Given a string s, determine if it is a palindrome, considering only alphanumeric characters and ignoring cases.
     * Trick: When char is not letter or digit then pass on
     *
     * @param s
     * @return
     */
    public boolean isPalindrome(String s) {

        int i = 0;
        int j = s.length() - 1;

        while (i <= j) {
            if (!Character.isLetterOrDigit(s.charAt(i))) {
                i++;
                continue;
            }
            if (!Character.isLetterOrDigit(s.charAt(j))) {
                j--;
                continue;
            }
            if (Character.toLowerCase(s.charAt(i)) != Character.toLowerCase(s.charAt(j)))
                return false;
            i++;
            j--;
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(checkPalindrome("mom"));
    }
}
