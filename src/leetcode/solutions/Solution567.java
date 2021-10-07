package leetcode.solutions;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * 567. 字符串的排列
 * 给你两个字符串 s1 和 s2 ，写一个函数来判断 s2 是否包含 s1 的排列。如果是，返回 true ；否则，返回 false 。
 *
 * 换句话说，s1 的排列之一是 s2 的 子串 。
 *
 *
 *
 * 示例 1：
 *
 * 输入：s1 = "ab" s2 = "eidbaooo"
 * 输出：true
 * 解释：s2 包含 s1 的排列之一 ("ba").
 * 示例 2：
 *
 * 输入：s1= "ab" s2 = "eidboaoo"
 * 输出：false
 *
 *
 * 提示：
 *
 * 1 <= s1.length, s2.length <= 104
 * s1 和 s2 仅包含小写字母
 */
public class Solution567 {

    public boolean checkInclusion(String s1, String s2) {

        int windowSize = s1.length();
        Map<Character, Integer> count1 = getCount(s1);
        Map<Character, Integer> count2 = new HashMap<>();

        for (int i = 0; i < s2.length(); i ++) {
            //sliding window
            count2.put(s2.charAt(i), count2.getOrDefault(s2.charAt(i), 0) + 1);
            if (i - windowSize >= 0) {
                if (count2.get(s2.charAt(i - windowSize)) == 1) {
                    count2.remove(s2.charAt(i - windowSize));
                } else {
                    count2.put(s2.charAt(i - windowSize), count2.get(s2.charAt(i - windowSize)) - 1);
                }
            }
            //compare
            if (isEqualMap(count1, count2)) {
                return true;
            }
        }
        return false;
    }

    private Map<Character, Integer> getCount(String s) {
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i ++) {
            map.put(s.charAt(i), map.getOrDefault(s.charAt(i), 0) + 1);
        }
        return map;
    }

    private boolean isEqualMap(Map<Character, Integer> map1, Map<Character, Integer> map2) {
        if (map1.size() == map2.size()) {
            for (Map.Entry<Character, Integer> entry: map1.entrySet()) {
                if (!Objects.equals(map2.get(entry.getKey()), entry.getValue())) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }


    public static void main(String[] args) {
        Solution567 solution = new Solution567();
        boolean b = solution.checkInclusion("ab", "eidboaoo");
        System.out.println(b);
    }
}
