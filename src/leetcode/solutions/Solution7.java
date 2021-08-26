package leetcode.solutions;

/**
 * brute frce && two pointers later
 * @author wangql
 * @since 2021-08-23 09:56
 */
class Solution7 {
  public int reverse(int x) {
    Long result = 0L;
    while (x != 0) {
      int lastNumber = x % 10;
      x = x /10;
      result = result * 10 + lastNumber;
      if (result > Integer.MAX_VALUE || result < Integer.MIN_VALUE)
        return 0;
    }
    return result.intValue();
  }

  public static void main(String[] args) {
    int reverse = new Solution7().reverse(-123);
  }
}
