import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Subset {


    static List<List<Integer>> findSubset(int[] A, int target){

        List<List<Integer>> results = new ArrayList<>();
        List<Integer> slate= new ArrayList<>();

        helper(A,0,slate,0,target,results);

        return results;
    }

    private static void helper(int[] a, int i, List<Integer> slate, int sumSofar, int target, List<List<Integer>> results) {

        if(sumSofar>target) return;

        if(a.length==i){
            if(sumSofar==target) results.add(new ArrayList<>(slate));
            return;
        }
        slate.add(a[i]);
        helper(a,i+1,slate,sumSofar+a[i],target,results);
        slate.remove(slate.size()-1);
        helper(a,i+1,slate,sumSofar,target,results);
    }

    public static void main(String[] args) {
//        ArrayList<Integer> list = new ArrayList();
//        long[] array1 = { 2,-10,10,0};
//        int[] array2 = {4,5,8,9,0,0,0,0};
//        list.add(2);
//        list.add(4);
//        list.add(0);
//        list.add(7);
//        list.add(9);
//        list.add(12);
//        // System.out.println(merge_sort(list));
//        System.out.println(generate_all_expressions("1234",10));
        System.out.println( how_many_BSTs(6));


    }

    static boolean check_if_sum_possible(long[] arr, long k) {
        List<List<Long>> result = new ArrayList<>();
        List<Long> slate = new ArrayList<>();
        helper1(arr,0,0,Math.abs(k),slate,result);
        return ! result.isEmpty();
    }

    static void helper1(long[] A, int i, long sumSoFar, long target,List<Long> slate,  List<List<Long>> result){
        if(sumSoFar>target) return;
        if(A.length==i){
            if(sumSoFar==target && !slate.isEmpty()) result.add(new ArrayList<>(slate));
            return;
        }
        slate.add(A[i]);
        helper1(A,i+1,sumSoFar+Math.abs(A[i]),target,slate,result);
        slate.remove(slate.size()-1);
        helper1(A,i+1,sumSoFar,target,slate,result);

    }

    static String[] find_all_well_formed_brackets(int n) {

        List<String> results = new ArrayList<>();

        helper(n,0,0,"",results);
        String [] resultArray = new String[results.size()];

        for (int i = 0; i < results.size(); i++) {
            resultArray[i] =results.get(i);
        }
        return resultArray;
    }

    static void helper(int n, int left, int right, String slate,List<String> results){
        if(right>left || left>n || right>n) return;

        if(left==n && right==n)
            results.add(slate);

        helper(n,left+1,right,slate+"(",results);
        helper(n,left,right+1,slate+")",results);


    }



    /*
     * Complete the function below.
     */
    static String[] generate_all_expressions(String s, long target) {

        List<String> results = new ArrayList<>();
        List<Character> slate = new ArrayList<>();
        //helpera(s,0,slate,0,target,results);
        return results.toArray(new String[0]);

    }

    static void helpera(String s, int i , String slate, long expValue,long target,List<String> results) {

        if(expValue == target){results.add(slate.toString()); return;};

        if(s.length()==i || expValue>target) return;

        helpera(s,i+1,slate+s.charAt(i),Long.valueOf(slate+s.charAt(i)),target,results);
        helpera(s,i+1,slate,eval(expValue,s.charAt(i),"*"),target,results);
        helpera(s,i+1,slate,eval(expValue,s.charAt(i),"+"),target,results);
    }

    private static long eval(long eval, char c, String operator) {

//        switch (operator){
//            case "join":
//                return ;
//            case "*":
//                return Long.valueOf(eval+"*"+String.valueOf(c));
//            default:
//                return eval+ "+" Long.valueOf(c);
//        }
        return 0;
    }

    static String[] generate_all_subsets(String s) {
        List<String> results =new ArrayList();
        List<Character> slate= new ArrayList();
        helperSubset(s.toCharArray(),0,slate,results);
        return results.toArray(new String[results.size()]);
    }

    static void helperSubset(char[] array,int pos,List<Character> slate, List<String> results){
        if(pos>=array.length){
            results.add(slate.stream().map(String::valueOf).collect(Collectors.joining()));
            return;
        }
        slate.add(array[pos]);
        helperSubset(array,pos+1,slate,results);
        slate.remove(slate.size()-1);
        helperSubset(array,pos+1,slate,results);
    }

    /*
     * Complete the function below.
     */
    static long how_many_BSTs(int n) {
        long[] result={0};
        return helperBST(n,result);
    }


    static long helperBST(int n,long[] result){
        if(n<=1) return 1;
        result[0] = 2*helperBST(n-1,result);

        for(int i=1; i<n-1; i++){
            result[0] =result[0] + helperBST(i,result)*helperBST(n-1-i,result);
        }
        return result[0];
    }

}
