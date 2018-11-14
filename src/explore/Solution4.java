package explore;

import java.util.HashSet;

public class Solution4 {

    public int removeDuplicatesOld(int[] nums) {
        HashSet<Integer> numberSet = new HashSet<>();
        int j = 0;
        for (int i = 0; i < nums.length; i++) {
            if (! numberSet.contains(nums[i])) {
                numberSet.add(nums[i]);
                if (i != j){
                    int temp = nums[i];
                    nums[i] = nums[j];
                    nums[j] = temp;
                }
                j++;
            }
        }
        return j;
    }

    public int removeDuplicates(int[] nums) {
        if (nums.length == 0) return 0;
        int pre = 0, cur = 0, n = nums.length;
        while (cur < n) {
            if (nums[pre] == nums[cur]) {
                cur++;
            } else {
                nums[pre + 1] = nums[cur];
                pre++;
                cur++;
            }
        }
        return pre + 1;
    }

    public static void main(String[] args) {
        //build data
        int[] data = {0, 0, 1, 1, 1, 2, 2, 3, 3, 4};

//        int[] data = {0, 0, 0};


        int result = new Solution4().removeDuplicates(data);
        System.out.println(result);
        for (int number: data) {
            System.out.print(number + " ");
        }
//        System.out.println(data.toString());
    }
}
