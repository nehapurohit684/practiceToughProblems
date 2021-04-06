package sorting;

import java.util.stream.IntStream;

public class DutchFlag {


    public static void dutch_flag_sort(char[] balls) {
        int startPointer =0;
        int endPointer =balls.length-1;
        int midPointer = 0;
        while (midPointer<=endPointer) {
            if(balls[midPointer]=='R') {
                swap(balls,midPointer,startPointer);
                startPointer++;
                midPointer++;
            }else if(balls[midPointer]=='G') {
                midPointer++;
            }else {
                swap(balls, midPointer,endPointer);
                endPointer--;
            }
        }
    }

    private static int swap(char[] balls, int pointer, int i) {
        char temp = balls[pointer];
        balls[pointer] = balls[i];
        balls[i]=temp;
        return pointer;
    }

    public static void main(String[] args) {
        char[] array = {'G','G','B','B','R', 'R'};

        dutch_flag_sort(array);
    }
}
