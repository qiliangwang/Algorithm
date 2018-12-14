package company;

public class Solution9 {
    public int removeDuplicates(int[] nums) {
        int j = 0;
        for (int i = 0; i < nums.length; i ++) {
            if (nums[i] != nums[j]) {
                j++;
                nums[j] = nums[i];
            }
        }
        return j + 1;
    }


    public static void main(String[] args) {
        int[] data = {0,0,1,1,1,2,2,3,3,4};
        int length = new Solution9().removeDuplicates(data);
        System.out.println(length);
        for (int number: data) {
            System.out.print(number + " ");
        }
    }
}
