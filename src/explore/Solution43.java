package explore;


public class Solution43 {

    public String multiply(String num1, String num2) {
        int[] intResult = new int[num1.length() + num2.length()];
        for (int i = num2.length() - 1; i >=0 ; i --) {
            for (int j = num1.length() - 1; j >=0 ; j --) {
                int tempResult = Character.getNumericValue(num1.charAt(j)) * Character.getNumericValue(num2.charAt(i));
                int resultIndex = i + j + 1;
                int tempValue = intResult[resultIndex] + tempResult;
                intResult[resultIndex] = tempValue % 10;
                intResult[resultIndex - 1] += tempValue / 10;
            }
        }

        StringBuffer multiplyResult = new StringBuffer();
        for (int num: intResult) {
            if ((num == 0 && multiplyResult.length() == 0)) {
                continue;
            }
            multiplyResult.append(num);
        }
        return multiplyResult.length() == 0 ? "0" : multiplyResult.toString();
    }

    public static void main(String[] args) {
        String result = new Solution43().multiply("123", "456");
        System.out.println(result);
    }
}
