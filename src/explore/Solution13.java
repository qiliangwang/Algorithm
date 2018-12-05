package explore;


class Solution13 {
    private int getIntegerFromRoman(char romanChar) {
        switch (romanChar) {
            case 'I': return 1;
            case 'V': return 5;
            case 'X': return 10;
            case 'L': return 50;
            case 'C': return 100;
            case 'D': return 500;
            case 'M': return 1000;
        }
        return 0;
    }

    public int romanToInt(String s) {
        int results = getIntegerFromRoman(s.charAt(0));
        for (int i = 1; i < s.length(); i++) {
            if (getIntegerFromRoman(s.charAt(i)) > getIntegerFromRoman(s.charAt(i - 1))) {
                results += getIntegerFromRoman(s.charAt(i)) - 2 * getIntegerFromRoman(s.charAt(i - 1));
            }else {
                results += getIntegerFromRoman(s.charAt(i));
            }
        }
        return results;
    }


    public static void main(String[] args) {

        int result = new Solution13().romanToInt("MCMXCIV");
        System.out.println(result);
    }
}
