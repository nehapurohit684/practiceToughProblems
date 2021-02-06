package general;

public class GroupNumbers {

    public static void main(String[] args) {
        int[] array = {8,4,9,5,2, 9, 5, 7, 10};
        System.out.println(groupNumberEvenOdd(array,0,array.length-1));
    }

    private static int[] groupNumberEvenOdd(int[] array,int start,int end) {
        int even=start;
        for (int i = start; i <= end; i++) {
                if (array[i] % 2 == 0) {
                    int temp = array[even];
                    array[even] = array[i];
                    array[i] = temp;
                    even++;
                }
            }
        return array;
    }


}
