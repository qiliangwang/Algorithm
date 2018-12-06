package explore;

public class Solution7 {

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
        int result = new Solution7().reverse(-0);
        System.out.println(result);
    }
}
