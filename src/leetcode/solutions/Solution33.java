package leetcode.solutions;

/**
 *33. 搜索旋转排序数组
 * 整数数组 nums 按升序排列，数组中的值 互不相同 。
 *
 * 在传递给函数之前，nums 在预先未知的某个下标 k（0 <= k < nums.length）上进行了 旋转，使数组变为 [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]]（下标 从 0 开始 计数）。例如， [0,1,2,4,5,6,7] 在下标 3 处经旋转后可能变为 [4,5,6,7,0,1,2] 。
 *
 * 给你 旋转后 的数组 nums 和一个整数 target ，如果 nums 中存在这个目标值 target ，则返回它的下标，否则返回 -1 。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [4,5,6,7,0,1,2], target = 0
 * 输出：4
 * 示例 2：
 *
 * 输入：nums = [4,5,6,7,0,1,2], target = 3
 * 输出：-1
 * 示例 3：
 *
 * 输入：nums = [1], target = 0
 * 输出：-1
 *
 *
 * 提示：
 *
 * 1 <= nums.length <= 5000
 * -10^4 <= nums[i] <= 10^4
 * nums 中的每个值都 独一无二
 * 题目数据保证 nums 在预先未知的某个下标上进行了旋转
 * -10^4 <= target <= 10^4
 *
 *
 * 进阶：你可以设计一个时间复杂度为 O(log n) 的解决方案吗？
 */
public class Solution33 {

    /**
     * Input: nums = [4,5,6,7,0,1,2], target = 0
     * Output: 4
     *
     * Input: nums = [4,5,6,7,0,1,2], target = 3
     * Output: -1
     * @param nums
     * @param target
     * @return
     */
    public int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) return -1;

        int start = 0, end = nums.length - 1;

        while (start + 1 < end) {
            int mid = start + (end - start) / 2;

            if (nums[mid] == target) return mid;
            //left part is ordered
            if (nums[start] < nums[mid]) {
                // start < target < mid
                if (nums[start] <= target && target <= nums[mid]) {
                    end = mid;
                } else start = mid;
            } else {
                // mid < target < end
                if (nums[mid] <= target && target <= nums[end]) {
                    start = mid;
                } else end = mid;
            }
        }
        if (nums[start] == target) return start;
        if (nums[end] == target) return end;
        return -1;
    }


    /**
     * 这个也是用二分的思路来弄的，只不过这个二分延续了之前的套路
     * while l <= r
     * l = mid + 1
     * r = mid + 1
     * @param nums
     * @param target
     * @return
     */
    public int search2(int[] nums, int target) {
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
        int[] nums = {4};
        int result = new Solution33().search(nums, 4);
        System.out.println(result);
    }
}
