package leetcode.solutions;

import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;
import leetcode.util.ArrayUtil;

import java.util.Arrays;

public class Solution31 {

    /**
     *
     * @param nums
     */
    public void nextPermutation(int[] nums) {
        int flag = 0;
        for (int i = nums.length - 1; i > 0; i--) {
            if (nums[i] > nums[i - 1]) {
                System.out.println(i);
                flag = i;
                break;
            }
        }
        Arrays.sort(nums, flag, nums.length);
        if (flag == 0) {
            return;
        }
        for (int j = flag; j < nums.length; j ++) {
            if (nums[j] > nums[flag - 1]) {
                int temp = nums[j];
                nums[j] = nums[flag - 1];
                nums[flag - 1] = temp;
                break;
            }
        }
    }


    /**
     * 31. 下一个排列
     * 实现获取 下一个排列 的函数，算法需要将给定数字序列重新排列成字典序中下一个更大的排列（即，组合出下一个更大的整数）。
     *
     * 如果不存在下一个更大的排列，则将数字重新排列成最小的排列（即升序排列）。
     *
     * 必须 原地 修改，只允许使用额外常数空间。
     *
     *
     *
     * 示例 1：
     *
     * 输入：nums = [1,2,3]
     * 输出：[1,3,2]
     * 示例 2：
     *
     * 输入：nums = [3,2,1]
     * 输出：[1,2,3]
     * 示例 3：
     *
     * 输入：nums = [1,1,5]
     * 输出：[1,5,1]
     * 示例 4：
     *
     * 输入：nums = [1]
     * 输出：[1]
     *
     *
     * 提示：
     *
     * 1 <= nums.length <= 100
     * 0 <= nums[i] <= 100
     * 通过次数212,407提交次数569,210
     *
     * 从length - 2位置开始 从后向前遍历找到 修改的位置 然后把修改位置后面的都进行排序操作
     * 排序完成后把数字插入到一个合适的位置 使得插入后的还是有序的，（从小到大，这样是最小的也就是next）
     * @param nums
     */
    public void nextPermutation2(int[] nums) {
        int flag = -1;
        for (int i = nums.length - 2; i >= 0; i --) {
            if (nums[i] < nums[i + 1]) {
                flag = i;
                break;
            }
        }
        Arrays.sort(nums,flag + 1, nums.length);
        if (flag == -1) {
            return;
        }
        for (int i = flag + 1; i < nums.length; i ++) {
            if (nums[flag] < nums[i]) {
                swap(nums, flag, i);
                break;
            }
        }
    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    public static void main(String[] args) {
        int[] nums= {1, 3, 2};
//        //[)
//        Arrays.sort(nums, 0, 3);
//        ArrayUtil.printArray(nums);

        new Solution31().nextPermutation2(nums);

        ArrayUtil.printArray(nums);
//
//        for (int num : nums) {
//            System.out.print(num + " ");
//        }
    }
}
