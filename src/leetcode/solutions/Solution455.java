package leetcode.solutions;

import java.util.Arrays;

public class Solution455 {

    public int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);
        int res = 0;
        int gi = g.length - 1;
        int si = s.length - 1;
        while (gi >= 0 && si >= 0) {
            if (s[si] >=g[gi]) {
                si--;
                gi--;
                res ++;
            }else {
                gi--;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] g = {1, 2, 3}, s = {1, 1};
        int[] g2 = {1, 2}, s2 = {1, 2, 3};
        int[] g3 = {1, 2}, s3 = {};
        int result = new Solution455().findContentChildren(g3, s3);
        System.out.println(result);
    }
}
