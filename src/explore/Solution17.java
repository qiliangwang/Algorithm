package explore;

import java.util.List;

class Solution17 {
    private List<String> res;
    private String[] letterMap = new String[]{
                " ",    //0
                "",     //1
                "abc",  //2
                "def",  //3
                "ghi",  //4
                "jkl",  //5
                "mno",  //6
                "pqrs", //7
                "tuv",  //8
                "wxyz"};


    public List<String> letterCombinations(String digits) {



        return res;
    }


    public static void main(String[] args) {

        List<String> res = new Solution17().letterCombinations("12");
        for (String combination: res) {
            System.out.println(combination);
        }
    }
}
