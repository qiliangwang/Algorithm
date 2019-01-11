package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Vader Wang
 */
public class Solution241 {

    /**
     * Given a string of numbers and operators,
     * return all possible results from computing all the different possible ways to group numbers and operators.
     * The valid operators are +, - and *.
     *
     * Example 1:
     *
     * Input: "2-1-1"
     * Output: [0, 2]
     * Explanation:
     * ((2-1)-1) = 0
     * (2-(1-1)) = 2
     * Example 2:
     *
     * Input: "2*3-4*5"
     * Output: [-34, -14, -10, -10, 10]
     * Explanation:
     * (2*(3-(4*5))) = -34
     * ((2*3)-(4*5)) = -14
     * ((2*(3-4))*5) = -10
     * (2*((3-4)*5)) = -10
     * (((2*3)-4)*5) = 10
     * @param input
     * @return
     */

    public List<Integer> diffWaysToCompute(String input) {
        if (input.length() == 0) return new ArrayList<>();

        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < input.length(); i ++) {
            char c = input.charAt(i);
            if (c == '-' || c == '+' || c == '*') {
                // substring range is [ )
                String left = input.substring(0, i);
                String right = input.substring(i + 1);
                List<Integer> leftList = diffWaysToCompute(left);
                List<Integer> rightList = diffWaysToCompute(right);
                for (int x : leftList) {
                    for (int y : rightList) {
                        if (c == '-') {
                            res.add(x - y);
                        }else if (c == '+') {
                            res.add(x + y);
                        } else {
                            res.add(x * y);
                        }
                    }
                }
            }
        }
        if (res.size() == 0) {
            res.add(Integer.valueOf(input));
        }
        return res;
    }

    public static void main(String[] args) {
        List<Integer> result = new Solution241().diffWaysToCompute("2*3-4*5");
//        List<Integer> result = new Solution241().diffWaysToCompute("");

        System.out.println(result);
    }
}
