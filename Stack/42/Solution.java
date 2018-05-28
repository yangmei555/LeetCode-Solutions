class Solution {
    public int trap(int[] height) {
        int[] stack = new int[height.length];
        int index = 0, res = 0;
        for (int i = 0; i < height.length; i++) {
            if (index == 0 || height[stack[index-1]] > height[i]) {
                stack[index++] = i;
            } else {
                int pre = stack[--index];
                while (index != 0 && height[stack[index-1]] <= height[i]) {
                    res += (height[stack[index-1]] - height[pre]) * (i-stack[index-1]-1);
                    pre = stack[--index];
                }
                if (index != 0)
                    res += (height[i] - height[pre]) * (i-stack[index-1]-1);
                stack[index++] = i;
            }
        }
        return res;
    }
}


class Solution {
    public int trap(int[] height) {
        int left = 0, right = height.length-1;
        int res = 0, lmax = 0, rmax = 0;
        while (left < right) {
            if (height[left] < height[right]) {
                if (lmax < height[left])
                    lmax = height[left];
                else
                    res += lmax - height[left];
                left++;
            } else {
                if (rmax < height[right])
                    rmax = height[right];
                res += rmax - height[right];
                right--;
            }
        }
        return res;
    }
}
