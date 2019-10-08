package leetcode.solutions;

public class Solution605 {

    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        int res = 0;
        for (int i = 0; i < flowerbed.length; i ++) {
            if (flowerbed[i] == 0 && (i == 0 || flowerbed[i - 1] == 0) && (i == flowerbed.length - 1 || flowerbed[i + 1] == 0)) {
                flowerbed[i] = 1;
                res ++;
            }
        }
        return res >= n;
    }

    public static void main(String[] args) {
        int[] flowerbed = new int[] {1,0,0,0,1};
        boolean result = new Solution605().canPlaceFlowers(flowerbed, 1);
        System.out.println(result);
    }
}
