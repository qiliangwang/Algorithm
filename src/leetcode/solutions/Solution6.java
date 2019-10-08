package leetcode.solutions;

public class Solution6 {

    public String convert(String s, int numRows) {
        if (numRows <= 1)
            return s;

        StringBuilder[] stringBuilders = new StringBuilder[numRows];
        for (int i = 0; i < stringBuilders.length; i ++) {
            stringBuilders[i] = new StringBuilder();
        }
        for (int i = 0; i < s.length(); i ++) {
            int index = i % (2 * numRows - 2);
            index = index < numRows ? index : (2 * numRows - 2) - index;
            stringBuilders[index].append(s.charAt(i));
//            if (index < numRows) {
//                stringBuilders[index].append(s.charAt(i));
//            }else {
//                index = (2 * numRows - 2) - index;
//                for (int j = 0; j < numRows; j ++) {
//                    if (j == index) {
//                        stringBuilders[j].append(s.charAt(i));
//                    }else {
//                        stringBuilders[j].append(" ");
//                    }
//                }
//            }
        }
        for (int i = 1; i < stringBuilders.length; i ++) {
            stringBuilders[0].append(stringBuilders[i]);
//            System.out.print(stringBuilders[i].toString() + "\n");
        }
        return stringBuilders[0].toString();
    }

    public static void main(String[] args) {
        String result = new Solution6().convert("PAYPALISHIRING", 4);
        System.out.println(result);
    }
}
