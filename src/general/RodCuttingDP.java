package general;

import java.util.Arrays;

public class RodCuttingDP {

    public static void main(String[] args) {
        max_product_from_cut_pieces(11);
    }

    static long max_product_from_cut_pieces(int n) {
        /*
         * Write your code here.
         */
        long maxProd = 1;
        long[] table = new long[n+1];
        Arrays.fill(table,0);
        table[0]=1;
        table[1]=1;

        for(int i=2; i<=n; i++){
            for(int j=1; j<i; j++){
                long temp  = table[j]*(i-j) < (j * (i-j))? j*(i-j) :table[j]*(i-j) ;
                table[i] = temp>table[i] ? temp : table[i];
            }
        }
        return table[n];
    }
}
