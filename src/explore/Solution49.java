package explore;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Solution49 {
    public List<List<String>> groupAnagrams(String[] strList) {
        List<List<String>> results = new ArrayList<>();
        HashMap<String, List<String>> strIndexMap = new HashMap<>();
        for (String str: strList) {
            char[] chars = str.toCharArray();
            Arrays.sort(chars);
            String key = new String(chars);
            if (strIndexMap.containsKey(key)) {
                strIndexMap.get(key).add(str);
            }else {
                ArrayList<String> strings = new ArrayList<>();
                strings.add(str);
                strIndexMap.put(key, strings);
            }
        }
        for (String str: strIndexMap.keySet()) {
            results.add(strIndexMap.get(str));
        }
        return results;
    }

    public static void main(String[] args) {
        String[] strList = {"eat", "tea", "tan", "ate", "nat", "bat"};
        Solution49 solution = new Solution49();
        List<List<String>> results = solution.groupAnagrams(strList);
        for (List<String> res: results) {
            System.out.println(res);
        }
    }
}