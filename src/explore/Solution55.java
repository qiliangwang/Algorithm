package explore;

public class Solution55 {

    public boolean canJump(int[] numbers) {
        int maxValue = 0;
        for (int i = 0; i < numbers.length; i ++) {
            if (maxValue < i) {
                return false;
            }
            maxValue = Math.max(maxValue, i + numbers[i]);
            if (maxValue > numbers.length - 1) {
                return true;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int[] correctData = {2, 3, 1, 1, 4};
        int[] wrongData = {3, 2, 1, 0, 4};
        boolean result = new Solution55().canJump(correctData);
        System.out.println(result);
    }
}
