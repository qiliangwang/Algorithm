package leetcode.solutions;

import java.util.*;
import java.util.stream.Collectors;

class Solution349 {

    public int[] intersection2(int[] nums1, int[] nums2) {
        //boxed
        List<Integer> numbers1 = Arrays.stream(nums1).boxed().collect(Collectors.toList());
        List<Integer> numbers2 = Arrays.stream(nums2).boxed().collect(Collectors.toList());
        //convert
        HashSet<Integer> set1 = new HashSet<>(numbers1);
        HashSet<Integer> set2 = new HashSet<>(numbers2);
        //core
        set1.retainAll(set2);
        //convert
        return set1.stream().mapToInt(Integer::intValue).toArray();
    }

    public int[] intersection(int[] nums1, int[] nums2) {
        List<Integer> res = new ArrayList<>();
        HashSet<Integer> intersectSet = new HashSet<>();
        for (int i=0; i<nums1.length; i++) {
            intersectSet.add(nums1[i]);
        }

        HashSet<Integer> resultSet = new HashSet<>();
        for (int i=0; i<nums2.length; i++) {
            if (intersectSet.contains(nums2[i])) {
                resultSet.add(nums2[i]);
            }
        }
        return resultSet.stream().mapToInt(Integer::intValue).toArray();
    }


    public static void main(String[] args) {
        int[] nums1 = {4,9,5};

        int[] nums2 = {9,4,9,8,4};

        int[] intersection = new Solution349().intersection(nums1, nums2);
        for (int i : intersection) {
            System.out.print(i + " ");
        }
    }
}
