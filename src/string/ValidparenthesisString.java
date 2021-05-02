package string;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class ValidparenthesisString {
    public static String minRemoveToMakeValid(String s) {
        Set<Integer> wrongIndex = new HashSet<>();
        Stack<Integer> stack = new Stack<Integer>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.push(i);
            } else if (s.charAt(i) == ')') {
                if (stack.isEmpty()) {
                    wrongIndex.add(i);
                } else {
                    stack.pop();
                }
            }
        }
        while (!stack.isEmpty()) wrongIndex.add(stack.pop());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (!wrongIndex.contains(i))
                sb.append(s.charAt(i));
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(minRemoveToMakeValid(("(a(b(c)d)")));
    }
}
