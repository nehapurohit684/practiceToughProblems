package string;

public class SubStringMatch {

    static boolean substring(String parent, String sub) {
        char[] parentArray = parent.toCharArray();
        char[] subArray = sub.toCharArray();

        for (int i = 0; i < parentArray.length; i++) {
            for (int j = 0; j < subArray.length; j++) {
                if (i >= parentArray.length || parentArray[i] != subArray[j]) break;
                else i++;
                if (j == subArray.length - 1) return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(substring("nehamitanika", "ani"));
    }
}
