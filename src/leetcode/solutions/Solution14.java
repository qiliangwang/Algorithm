package leetcode.solutions;


class Solution14 {

    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) return "";
        String res = strs[0];
        for (int i = 1; i < strs.length; i++) {
            while (strs[i].indexOf(res) != 0) {
                res = res.substring(0, res.length() - 1);
            }
        }
        return res;
    }


    public static void main(String[] args) {
        String[] dataOne = {"flower", "flow", "flight"};

        String[] dataTwo = {"dog", "racecar", "car"};

        String result = new Solution14().longestCommonPrefix(dataTwo);
        System.out.println(result);
    }
}
