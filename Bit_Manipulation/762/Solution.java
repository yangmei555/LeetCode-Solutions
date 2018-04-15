class Solution {
    public int countPrimeSetBits(int L, int R) {
        int n = Integer.toString(R, 2).length();
        int[] index = new int[n + 1];
        index[0] = index[1] = 1;
        for (int i = 2; i * i <= n; i++) {
            if (index[i] == 0) {
                for (int j = i * i; j < index.length; j += i)
                    index[j] = 1;
            }
        }
        int count = 0, bits = 0, loop = L;
        while (loop != 0) {
            loop = (loop & loop - 1);
            bits++;
        }
        count += 1 - index[bits];
        for (int i = L; i <= R - 1; i++) {
            if ((i & 1) == 0) {
                bits++;
            } else {
                loop = i;
                while ((loop & 1) != 0) {
                    bits--;
                    loop >>>= 1;
                }
                bits++;
            }
            count += 1 - index[bits];
        }
        return count;
    }
}
