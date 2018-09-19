class Solution {
    public int preimageSizeFZF(int K) {
        return (int)(helper(K+1) - helper(K));
    }
    
    public long helper(int order) {
        long left = 0, right = Long.MAX_VALUE;
        while (left < right) {
            long mid = left + (right - left) / 2;
            long count = 0, temp = mid;
            while (temp != 0) 
                count += (temp /= 5);
            if (count < order)
                left = mid + 1;
            else
                right = mid;
        }
        return left;
    }
}
