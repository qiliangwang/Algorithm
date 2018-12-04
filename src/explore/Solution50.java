package explore;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Solution50 {
    public double myPow(double x, int n) {
        return pow(x, n);
    }

    private double pow(double x, int n) {
        if (n < 0) {
            n = -n;
            x = 1 / x;
        }
        if (n == 0) {
            return 1;
        }
        if (n % 2 == 0) {
            return pow(x*x, n/2);
        }else {
            return pow(x*x, n/2) * x;
        }
    }

    public double myPow2(double x, int n) {
        long N = n;
        if(N == 0)
            return 1;
        if(N < 0){
            N = -N;
            x = 1/x;
        }
        return (N%2 == 0) ? myPow2(x*x, (int)(N/2)) : x*myPow2(x*x, (int)(N/2));
    }



    public static void main(String[] args) {
        Solution50 solution = new Solution50();
        double result = solution.myPow2(0.00001, 2147483647);
        System.out.println(result);
    }
}