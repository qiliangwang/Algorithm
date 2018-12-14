package leetcode;


import java.util.*;

class Solution350 {

    public int[] intersect(int[] nums1, int[] nums2) {

        List<Integer> res = new ArrayList<>();

        HashMap<Integer, Integer> numFreqHashMap = new HashMap<>();
        for (int num: nums1) {
            numFreqHashMap.put(num, numFreqHashMap.getOrDefault(num, 0) + 1);
        }

        for (int num: nums2) {
            if (numFreqHashMap.containsKey(num) && numFreqHashMap.get(num) > 0) {
                res.add(num);
                numFreqHashMap.put(num, numFreqHashMap.get(num) - 1);
            }
        }

        //convert result
        int[] arr = new int[res.size()];
        int idx = 0;
        for (int ele : res) {
            arr[idx++] = ele;
        }
        return arr;
//        return res.stream().mapToInt(Integer::intValue).toArray();
    }


    public static void main(String[] args) {
        int[] nums1 = {1, 2, 2, 1};

        int[] nums2 = {2, 2};

        int[] intersect = new Solution350().intersect(nums1, nums2);
        for (int i : intersect) {
            System.out.print(i + " ");
        }
    }
}
