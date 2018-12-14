package leetcode;

class Solution122 {

    public int maxProfit(int[] prices) {
        if (prices == null || prices.length < 2)
            return 0;
        int profit = 0;

        for (int i = 1; i < prices.length; i ++) {
            if (prices[i] > prices[i - 1]) {
                profit += prices[i] - prices[i - 1];
            }
        }

        return profit;
    }

    public static void main(String[] args) {
        int[] prices = {1,2,3,4,5};
        Solution122 numArray = new Solution122();
        int result = numArray.maxProfit(prices);
        System.out.println(result);
    }
}