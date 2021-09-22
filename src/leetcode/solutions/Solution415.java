package leetcode.solutions;

/**
 * 415. 字符串相加
 * 给定两个字符串形式的非负整数 num1 和num2 ，计算它们的和并同样以字符串形式返回。
 *
 * 你不能使用任何內建的用于处理大整数的库（比如 BigInteger）， 也不能直接将输入的字符串转换为整数形式。
 *
 *
 *
 * 示例 1：
 *
 * 输入：num1 = "11", num2 = "123"
 * 输出："134"
 * 示例 2：
 *
 * 输入：num1 = "456", num2 = "77"
 * 输出："533"
 * 示例 3：
 *
 * 输入：num1 = "0", num2 = "0"
 * 输出："0"
 *
 *
 *
 *
 * 提示：
 *
 * 1 <= num1.length, num2.length <= 104
 * num1 和num2 都只包含数字 0-9
 * num1 和num2 都不包含任何前导零
 */
public class Solution415 {

    public String addStrings(String num1, String num2) {
        int idx1 = num1.length() -1,idx2 = num2.length() -1, carry = 0;
        StringBuilder sb = new StringBuilder();
        while (idx1 >= 0 || idx2 >= 0 || carry > 0) {
            int one = idx1 < 0 ? 0 : num1.charAt(idx1) - '0';
            int two = idx2 < 0 ? 0 : num2.charAt(idx2) - '0';
            int added = one + two + carry;
            sb.append(added % 10);
            carry = added / 10;
            idx1--;
            idx2--;
        }
        return sb.reverse().toString();
    }

    public static void main(String[] args) {

        String s = new Solution415().addStrings("11", "123");
        System.out.println(s);
    }

}
