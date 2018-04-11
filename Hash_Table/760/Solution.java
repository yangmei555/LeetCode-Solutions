class Solution {
    public int[] anagramMappings(int[] A, int[] B) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < B.length; i++) {
            map.putIfAbsent(B[i], i);
        }
        int[] res = new int[A.length];
        for (int i = 0; i < A.length; i++)
            res[i] = map.get(A[i]);
        return res;
    }
}


class Solution {
    public int[] anagramMappings(int[] A, int[] B) {
        for (int i = 0; i < A.length; i++) {
            A[i] = (A[i] << 8) + i;
            B[i] = (B[i] << 8) + i;
        }
        Arrays.sort(A);
        Arrays.sort(B);
        int[] res = new int[A.length];
        for (int i = 0; i < res.length; i++) {
            res[A[i] & 0xff] = B[i] & 0xff;
        }
        return res;
    }
}
