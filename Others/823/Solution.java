class Solution {
    public int numFactoredBinaryTrees(int[] A) {
        int mod = 1000000007;
        Arrays.sort(A);
        long[] root = new long[A.length];
        Arrays.fill(root, 1);
        for (int i = 1; i < A.length; i++) {
            int left = 0, right = i - 1;
            while (left <= right) {
                if (A[left] * A[right] == A[i]) {
                    root[i] = (root[i] + ((root[left] * root[right] % mod) * 
                                            (left == right ? 1 : 2)) % mod) % mod;
                    left++;
                    right--;
                } else if (A[left] * A[right] < A[i]) {
                    left++;
                } else {
                    right--;
                }
            }
        }
        long res = 0;
        for (long r : root)
            res = (res + r) % mod;
        return (int)res;
    }
}
