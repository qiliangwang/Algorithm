package leetcode.solutions;

public class Solution8 {

    public int myAtoi(String str) {
        if (str == null) return 0;
        str = str.trim();
        if (str.length() == 0) return 0;
        int sign = 1;
        int start = 0;
        long res = 0;
        if (str.charAt(0) == '-') {
            sign = -1;
            start ++;
        }else if (str.charAt(0) == '+') {
            start ++;
        }
        for (int i = start; i < str.length(); i ++) {
            if (! Character.isDigit(str.charAt(i))) {
                return (int) res * sign;
            }
            res = res * 10 + str.charAt(i) - '0';
            if (sign == -1 && res > Integer.MAX_VALUE) return Integer.MIN_VALUE;
            if (sign == 1 && res > Integer.MAX_VALUE) return Integer.MAX_VALUE;
        }
        return (int) res * sign;
    }

    public static void main(String[] args) {
        int result = new Solution8().myAtoi("9223372036854775808");
        System.out.println(result);
    }
}
