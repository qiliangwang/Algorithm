package leetcode;

public class Solution4 {

    public double findMedianSortedArrays(int[] numbers1, int[] numbers2) {
        // make sure numbers1 < numbers2
        if (numbers1.length > numbers2.length) {
            return findMedianSortedArrays(numbers2, numbers1);
        }
        int len = numbers1.length +  numbers2.length;
        int cut1 = 0;
        int

        return 0;
    }

    public static void main(String[] args) {
        int[] numbers1 = {1, 2};
        int[] numbers2 = {3, 4};
        double result = new Solution4().findMedianSortedArrays(numbers1, numbers2);
        System.out.println(result);
    }
}
