package explore;


class Solution12 {

    //        StringBuilder roman = new StringBuilder();
//        int[] intValue = {1000, 500, 100, 50, 10, 5, 1};
//        char[] romanValue = {'M', 'D', 'C', 'L', 'X', 'V', 'I'};
//        for (int i=0; i < intValue.length; i ++) {
//            if (num > intValue[i]) {
//                num = num - intValue[i];
//                roman.append(romanValue[i]);
//            }
//        }
//        return roman.toString();

    public String intToRoman(int num) {
        StringBuilder roman = new StringBuilder();
        String[] one = {"I", "X", "C", "M", ""};
        String[] five = {"V", "L", "D", ""};
        for (int k = 3; k >= 0; k --) {
            int b = (int)Math.pow(10, k);
            if (num >= b) {
                int x = num / b;
                roman.append(rome(x, one[k], five[k], one[k+1]));
                num %= b;
            }

        }

        return roman.toString();
    }

    private String rome(int x, String one, String five, String ten) {
        if (x <= 3)
            return repeat(one, x);
        else if (x == 4)
            return one + five;
        else if (x <= 8)
            return five + repeat(one, x - 5);
        else
            return one + ten;
    }

    private String repeat(String c, int n) {
        StringBuilder result = new StringBuilder(n);
        for (int i = 0; i < n; ++i) {
            result.append(c);
        }
        return result.toString();
    }


    public static void main(String[] args) {

        String result = new Solution12().intToRoman(5);
        System.out.println(result);
    }
}
