package binarysearch;

public class BinarySearchNonArray {
    /**
     * 69. Sqrt(x)
     * Binary search with checking square of mid is smaller or larger than x
     * @param x
     * @return
     */
    public int mySqrt(int x) {

        int start =1;
        int end = x;

        while(start<=end){
            int mid = start +(end-start)/2;
            long midVal = (long)mid*mid;
            if(midVal>x) end =mid-1;
            else if(midVal<x) start =mid+1;
            else return mid;
        }
        return end;
    }

    public boolean isPerfectSquare(int x) {
        int start =1;
        int end = x;

        while(start<=end){
            int mid = start +(end-start)/2;
            long midVal = (long)mid*mid;
            if(midVal>x) end =mid-1;
            else if(midVal<x) start =mid+1;
            else return true;
        }
        return false;
    }

    /**
     * 441: Given the integer n, return the number of complete rows of the staircase you will build.
     * Hint: each mid find total sum you could reach to and compare that sum with n to go in right ot left
     * @param n
     * @return
     */
    public int arrangeCoins(int n) {

        int start =0;
        int end = n;
        if(n==1) return 1;
        while(start<=end){
            int mid = start +(end-start)/2;
            long sumTillMid = (long)mid * (mid+1)/2;
            if(sumTillMid<=n) start = mid+1;
            else end = mid-1;
        }
        return end;
    }

    /**
     * 29. Divide Two Integers
     * Simple Binary search but rememeber to convert all int to long in case any of the value is Int.min
     * @param dividend
     * @param divisor
     * @return
     */
    public int divide(int dividend, int divisor) {

        long start = 0;
        long end = Integer.MAX_VALUE;

        if (dividend == 0) {
            return 0;
        }

        if (dividend == Integer.MIN_VALUE && divisor == -1) {
            return Integer.MAX_VALUE;
        }

        if (dividend == Integer.MIN_VALUE && divisor == 1){
            return Integer.MIN_VALUE;
        }

        boolean negative = (divisor <0 && dividend>0 )|(divisor>0 && dividend<0);

        long dividend1,divisor1;

        if (dividend == Integer.MIN_VALUE){
            dividend1=(long)(Integer.MIN_VALUE) *-1;
        } else dividend1= Math.abs(dividend);
        if (divisor == Integer.MIN_VALUE){
            divisor1=(long)(Integer.MIN_VALUE) *-1;
        } else divisor1= Math.abs(divisor);

        while(start<=end){
            long mid =(long)(start+(end-start)/2);
            long midval = (long) (dividend1-(divisor1* mid ));
            if(midval==0){
                if(negative) return (int) -mid;
                else return (int)mid;
            }
            else if (midval>0) start = mid+1;
            else end = mid-1;

        }
        if(dividend1-divisor1*start < 0) start -=1;
        if(negative) return (int)-start;
        return (int)start;
    }



}
