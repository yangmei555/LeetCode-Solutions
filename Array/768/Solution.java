class Solution {
    public int maxChunksToSorted(int[] arr) {
        int[] copy = arr.clone();
        Arrays.sort(copy);
        Map<Integer, Integer> rank = new HashMap<>();
        for (int i = arr.length-1; i >= 0; i--)
            rank.put(copy[i], i);
        int res = 0, max = 0;
        int[] dup = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            int pos = rank.get(arr[i]);
            max = Math.max(max, pos + dup[pos]++);
            if (max == i)
                res++;
        }
        return res;
    }
}


class Solution {
    public int maxChunksToSorted(int[] arr) {
        int[] copy = arr.clone();
        Arrays.sort(copy);
        int res = 0, max = 0;
        int[] dup = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            int pos = helper(copy, arr[i]);
            max = Math.max(max, pos + dup[pos]++);
            if (max == i)
                res++;
        }
        return res;
    }
    
    public int helper(int[] copy, int val) {
        int left = 0, right = copy.length;
        while (left < right) {
            int mid = (left + right) / 2;
            if (copy[mid] >= val)
                right = mid;
            else
                left = mid + 1;
        }
        return left;
    }
}
