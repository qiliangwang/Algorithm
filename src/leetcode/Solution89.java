package leetcode;

import java.util.ArrayList;
import java.util.List;

public class Solution89 {

    /**
     * grey code i ^ (i / 2)
     * @param n
     * @return
     */
    public List<Integer> grayCode(int n) {
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < Math.pow(2, n); i ++) {
            res.add(i ^ i / 2);
        }
        return res;
    }

    public static void main(String[] args) {
        List<Integer> result = new Solution89().grayCode(2);
        System.out.println(result);
    }
}
