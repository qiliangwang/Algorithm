package leetcode.learn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

@SuppressWarnings("all")
public class Solution {


    /**
     * 20. 有效的括号
     * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是否有效。
     *
     * 有效字符串需满足：
     *
     * 左括号必须用相同类型的右括号闭合。
     * 左括号必须以正确的顺序闭合。
     *
     *
     * 示例 1：
     *
     * 输入：s = "()"
     * 输出：true
     * 示例 2：
     *
     * 输入：s = "()[]{}"
     * 输出：true
     * 示例 3：
     *
     * 输入：s = "(]"
     * 输出：false
     * 示例 4：
     *
     * 输入：s = "([)]"
     * 输出：false
     * 示例 5：
     *
     * 输入：s = "{[]}"
     * 输出：true
     *
     *
     * 提示：
     *
     * 1 <= s.length <= 104
     * s 仅由括号 '()[]{}' 组成
     * @param s
     * @return
     */
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i ++) {
            char c = s.charAt(i);
            if (c == '(' || c == '[' || c == '{') {
                stack.push(c);
            } else if (c == ')' || c == ']' || c == '}') {
                if (isPair( stack.peek(), c)) {
                    stack.pop();
                }
            }
        }
        return stack.isEmpty();
    }

    private boolean isPair(char c1, char c2) {
        if (c1 == '{') return c2 == '}';
        if (c1 == '[') return c2 == ']';
        if (c1 == '(') return c2 == ')';
        return false;
    }

    /**
     * 因为里面有重复的数字，所以这个为了优化一下很多时候需要跳过重复的数字
     * 这个跳过的逻辑就是套路 sort之后判断 num[i] 和 num[i + 1] 如果相等就跳过
     * @param nums
     * @return
     */
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 2; i ++) {
            //skip dup element
            if (i > 0 && nums[i - 1] == nums[i]) {continue;}

            int sum = 0 - nums[i];
            int left = i + 1, right = nums.length - 1;
            while (left < right) {
                if (nums[left] + nums[right] == sum) {
                    ans.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    //skip dup element
                    while (left < right && nums[left] == nums[left + 1]) {left ++;}
                    while (left < right && nums[right] == nums[right - 1]) {right --;}
                    left ++;
                    right --;
                } else if (nums[left] + nums[right] < sum) {
                    left ++;
                } else {
                    right --;
                }
            }
        }
        return ans;
    }

    /**
     * 33
     * 由于只经历了一次旋转所以可以更具 l mid r 的大小关系判断落在的是啥区间
     * l < mid 表明是在left 区间
     * @param nums
     * @param target
     * @return
     */
    public int search(int[] nums, int target) {
        int l = 0, r = nums.length - 1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (nums[mid] == target) {return mid;}
            if (nums[l] <= nums[mid]) {
                if (target >= nums[l] && target < nums[mid]) {
                    r = mid - 1;
                } else {
                    l = mid + 1;
                }
            } else {
                if (target > nums[mid] && target <= nums[r]) {
                    l = mid + 1;
                } else {
                    r = mid - 1;
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
//        boolean valid = solution.isValid("()[]{}");
//        System.out.println(valid);
//        List<List<Integer>> lists = solution.threeSum(new int[]{-1, 0, 1, 2, -1, -4});
//        System.out.println(lists);

        int search = solution.search(new int[]{3, 1}, 1);
        System.out.println(search);
    }
}
