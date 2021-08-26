package leetcode.solutions;

/**
 * 这个的复杂度是log(min(m,n)) 因为中位数其实是中间位置的数 所以这个中间其实是来源
 * 与2个数组，第一个数组贡献一部分，第二个数组贡献一部分 一起贡献的位置是中间的位置
 * 所以其实是对小的数组进行binerySearch 确认是否符合要求（l1 l2 < r1 r2)排列组合4种关系
 * 但是由于之前有约束l1 < r1 l2 < r2 所以只需要保证l1 < r2 l2 < r1即可
 */
public class Solution4 {

    public double findMedianSortedArrays(int[] numbers1, int[] numbers2) {
        // make sure numbers1 < numbers2
        if (numbers1.length > numbers2.length) {
            return findMedianSortedArrays(numbers2, numbers1);
        }
        int length = numbers1.length +  numbers2.length;
        int cut1 = 0;
        int cut2 = 0;

        int left = 0;
        int right = numbers1.length;
        while (cut1 <= numbers1.length) {
            //binary search
            cut1 = left +  (right - left) / 2;
            cut2 = length / 2 - cut1;
            double L1 = (cut1 == 0) ? Integer.MIN_VALUE : numbers1[cut1 - 1];
            double L2 = (cut2 == 0) ? Integer.MIN_VALUE : numbers2[cut2 - 1];
            double R1 = (cut1 == numbers1.length) ? Integer.MAX_VALUE : numbers1[cut1];
            double R2 = (cut2 == numbers2.length) ? Integer.MAX_VALUE : numbers2[cut2];
            if (L1 > R2) {
                //move left
                right = cut1 - 1;
            }else if (L2 > R1) {
                //move right
                left = cut1 + 1;
            }else {
                if (length % 2 == 0) {
                    L1 = (L1 > L2) ? L1 : L2;
                    R1 = (R1 < R2) ? R1 : R2;
                    return (L1 + R1) / 2;
                }else {
                    return (R1 < R2) ? R1 : R2;
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
//        int[] numbers1 = {1, 2};
//        int[] numbers2 = {3, 4};
        int[] numbers1 = {1, 3, 5, 7, 9};
        int[] numbers2 = {2, 3, 4, 5};
        double result = new Solution4().findMedianSortedArrays(numbers1, numbers2);
        System.out.println(result);
    }
}
