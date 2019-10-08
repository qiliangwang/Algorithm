package leetcode.solutions;

public class Solution914 {

    public boolean hasGroupsSizeX(int[] deck) {
        int[] count = new int[10000];
        for (int c: deck)
            count[c]++;

        int g = -1;
        for (int i = 0; i < 10000; i++)
            if (count[i] > 0) {
                if (g == -1)
                    g = count[i];
                else
                    g = gcd(g, count[i]);
            }

        return g >= 2;
    }

    public int gcd(int x, int y) {
        return x == 0 ? y : gcd(y % x, x);
    }

    public static void main(String[] args) {
        int[] deck = new int[]{1,1,1,1,2,2,2,2,2,2};
        boolean result = new Solution914().hasGroupsSizeX(deck);
        System.out.println(result);
    }
}
