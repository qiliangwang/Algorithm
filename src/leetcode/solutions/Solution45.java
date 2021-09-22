package leetcode.solutions;

/**
 * 45. 跳跃游戏 II
 * 给你一个非负整数数组 nums ，你最初位于数组的第一个位置。
 *
 * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
 *
 * 你的目标是使用最少的跳跃次数到达数组的最后一个位置。
 *
 * 假设你总是可以到达数组的最后一个位置。
 *
 *
 *
 * 示例 1:
 *
 * 输入: nums = [2,3,1,1,4]
 * 输出: 2
 * 解释: 跳到最后一个位置的最小跳跃数是 2。
 *      从下标为 0 跳到下标为 1 的位置，跳 1 步，然后跳 3 步到达数组的最后一个位置。
 * 示例 2:
 *
 * 输入: nums = [2,3,0,1,4]
 * 输出: 2
 *
 *
 * 提示:
 *
 * 1 <= nums.length <= 104
 * 0 <= nums[i] <= 1000
 * 通过次数196,324提交次数458,800
 * 请问您在哪类招聘中遇到此题？
 */
public class Solution45 {

    /**
     * 使用贪心来解决这个问题
     * 用一个变量表示当前可以到达的最远maxIdx 如果maxIdx > lenght其实结果就出来了
     * 当idx = maxIdx 的时候说明已经到了跳不到的地方了，需要继续选择台阶这时候ans++；
     * @param nums
     * @return
     */
    public int jump(int[] nums) {
        int ans = 0, maxIdx = 0, end = 0;
        for (int i = 0; i < nums.length - 1; i ++) {
            maxIdx = Math.max(maxIdx, i + nums[i]);
            if (i == end) {
                ans ++;
                end = maxIdx;
                if (end > nums.length -1) {return ans;}
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int jump = new Solution45().jump(new int[]{2, 3, 1, 1, 4});
        System.out.println(jump);
    }
}
