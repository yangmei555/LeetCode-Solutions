public class Solution {
    public int maxArea(int[] height) {
        int length = height.length;
        int max = 0;
        int i = 0, j = length - 1;
        while (i < j){
            max = Math.max(max, Math.min(height[i], height[j]) * (j - i));
            if (height[i] < height[j])
                i++;
            else 
                j--;
        }
        return max;
    }
}
