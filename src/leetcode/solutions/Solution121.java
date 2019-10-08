package leetcode.solutions;

class Solution121 {

    public int maxProfit(int[] prices) {
        if (prices == null || prices.length < 2)
            return 0;

        int min = prices[0];
        int profit = 0;

        for (int price: prices) {
            min = Math.min(min, price);
            profit = Math.max(price - min, profit);
        }
        return profit;
    }

    public static void main(String[] args) {
        int[] prices = {7, 6, 4, 3, 1};
        Solution121 numArray = new Solution121();
        int result = numArray.maxProfit(prices);
        System.out.println(result);
    }
}