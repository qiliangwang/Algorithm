package explore;


import java.util.HashMap;

class Solution11 {

    public int maxArea(int[] height) {
        int max = 0;
        for (int i = 0; i < height.length; i ++) {
            for (int j = i + 1 ; j < height.length; j ++) {
                int tempMax = Math.min(height[i], height[j]) * (j - i);
                if (tempMax > max)
                    max = tempMax;
            }
        }
        return max;
    }

    public int maxAreaPlus(int[] height) {
        int left = 0;
        int right = height.length-1;
        int maxArea = 0;
        while(left<=right){
            int l = height[left];
            int r = height[right];
            maxArea = Math.max(maxArea, (right-left)*Math.min(l,r));
            if(l<r)
                left++;
            else
                right--;
        }
        return maxArea;
    }


    public static void main(String[] args) {
        int[] data = {1, 8, 6, 2, 5, 4, 8, 3, 7};
        int result = new Solution11().maxAreaPlus(data);
        System.out.println(result);
    }
}
