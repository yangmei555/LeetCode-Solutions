class Solution {
    public int[] pourWater(int[] heights, int V, int K) {
        if (heights.length == 0)
            return heights;
        for (int i = 0; i < V; i++) {
            int left = K-1, right = K+1;
            while (left >= 0 && heights[left] <= heights[left+1])
                left--;
            left++;
            while (left < K-1 && heights[left] == heights[left+1])
                left++;
            if (heights[left] < heights[K]) {
                heights[left]++;
                continue;
            }
            while (right < heights.length && heights[right] <= heights[right-1])
                right++;
            right--;
            while (right > K+1 && heights[right] == heights[right-1])
                right--;
            if (heights[right] < heights[K]) {
                heights[right]++;
            } else {
                heights[K]++;
            }
        }
        return heights;
    }
}


class Solution {
    public int[] pourWater(int[] heights, int V, int K) {
        if (heights.length == 0)
            return heights;
        for (int i = 0; i < V; i++) {
            int left = K, right = K;
            for (int j = left; j >= 0; j--) {
                if (heights[j] < heights[left])
                    left = j;
                else if (heights[j] > heights[left])
                    break;
            }
            if (left != K) {
                heights[left]++;
                continue;
            }
            for (int j = right; j < heights.length; j++) {
                if (heights[j] < heights[right])
                    right = j;
                else if (heights[j] > heights[right])
                    break;
            }
            heights[right]++;
        }
        return heights;
    }
}
