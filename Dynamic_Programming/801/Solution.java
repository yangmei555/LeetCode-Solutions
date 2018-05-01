class Solution {
    public int minSwap(int[] A, int[] B) {
        int swap = 1, notswap = 0, temp1 = 0, temp2 = 0;
        for (int i = 1; i < A.length; i++) {
            temp1 = swap;
            temp2 = notswap;
            swap = Integer.MAX_VALUE;
            notswap = Integer.MAX_VALUE;
            if (A[i-1] < A[i] && B[i-1] < B[i]) {
                swap = temp1 + 1;
                notswap = temp2;
            }
            if (A[i-1] < B[i] && B[i-1] < A[i]) {
                notswap = temp1 < notswap ? temp1 : notswap;
                swap = (temp2 + 1) < swap ? (temp2 + 1) : swap;
            }
        }
        return swap < notswap ? swap : notswap;
    }
}
