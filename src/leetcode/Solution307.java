package leetcode;

class NumArray {

    private int[] sum;

    public NumArray(int[] nums) {

    }

    public void update(int i, int val) {

    }

    public int sumRange(int i, int j) {
        return 0;
    }

    public static void main(String[] args) {
        int[] nums = {-2, 0, 3, -5, 2, -1};
        NumArray numArray = new NumArray(nums);
        int result = numArray.sumRange(0, 5);
        System.out.println(result);
    }
}
