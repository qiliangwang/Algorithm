package leetcode;

class Solution123 {

    public int maxProfit(int[] prices) {
        if (prices == null || prices.length <2)
            return 0;

        int min = prices[0];
        int profit = 0;


        return profit;
    }

    public static void main(String[] args) {
        int[] prices = {7, 6, 4, 3, 1};
        Solution123 numArray = new Solution123();
        int result = numArray.maxProfit(prices);
        System.out.println(result);
    }
}