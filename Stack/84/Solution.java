class Solution {
    public int largestRectangleArea(int[] heights) {
        if (heights.length == 0)
            return 0;
        int[] stack = new int[heights.length+1];
        int index = 0, res = 0;
        stack[index++] = -1;
        for (int i = 0; i <= heights.length; i++) {
            while (index != 1 && (i == heights.length || heights[stack[index-1]] >= heights[i])) {
                int pre = heights[stack[--index]];
                int area = pre * (i - 1 - stack[index-1]);
                res = res > area ? res : area;
            }
            stack[index++] = i;
        }
        return res;
    }
}


class Solution {
    public int largestRectangleArea(int[] heights) {
        if (heights.length == 0)
            return 0;
        int[] stack = new int[heights.length], left = new int[heights.length], 
                right = new int[heights.length];
        int index = 0;
        Arrays.fill(right, heights.length);
        for (int i = 0; i < heights.length; i++) {
            while (index != 0 && heights[stack[index-1]] > heights[i]) 
                right[stack[--index]] = i;
            stack[index++] = i;
        }
        index = 0;
        Arrays.fill(left, -1);
        for (int i = heights.length-1; i >= 0; i--) {
            while (index != 0 && heights[stack[index-1]] > heights[i])
                left[stack[--index]] = i;
            stack[index++] = i;
        }
        int res = 0;
        for (int i = 0; i < heights.length; i++) {
            int area = heights[i] * (right[i] - left[i] - 1);
            res = res > area ? res : area;
        }
        return res;
    }
}
