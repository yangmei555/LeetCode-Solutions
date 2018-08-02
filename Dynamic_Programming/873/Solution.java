class Solution {
    public int lenLongestFibSubseq(int[] A) {
        Set<Integer> set = new HashSet<>();
        for (int a : A)
            set.add(a);
        int res = 0;
        for (int i = 0; i < A.length; i++) {
            for (int j = i + 1; j < A.length; j++) {
                int cand = 2, cur = A[i] + A[j], pre = A[j];
                while (set.contains(cur)) {
                    cand++;
                    int temp = cur;
                    cur = cur + pre;
                    pre = temp;
                }
                res = res > cand ? res : cand;
            }
        }
        return res >= 3 ? res : 0;
    }
}


class Solution {
    public int lenLongestFibSubseq(int[] A) {
        Map<Integer, Integer> pos = new HashMap<>();
        for (int i = 0; i < A.length; i++)
            pos.put(A[i], i);
        int res = 0;
        // map[i * A.length + j] : the length of the sequence in the form ...... , A[j], A[i]  
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < A.length; i++) {
            for (int j = i-1; j >= 0; j--) {
                if (A[i] - A[j] < A[j] && pos.containsKey(A[i] - A[j])) {
                    int index = pos.get(A[i] - A[j]);
                    int cand = map.getOrDefault(j * A.length + index, 2) + 1;
                    res = res > cand ? res : cand;
                    map.put(i * A.length + j, cand);
                } 
            }
        }
        return res;
    }
}


class Solution {
    public int lenLongestFibSubseq(int[] A) {
        // dp[i][j] : longest sequence ended with A[i], A[j] 
        int[][] dp = new int[A.length][A.length];
        int res = 0;
        for (int i = 2; i < A.length; i++) {
            int left = 0, right = i - 1;
            while (left < right) {
                if (A[left] + A[right] < A[i]) {
                    left++;
                } else if (A[left] + A[right] > A[i]) {
                    right--;
                } else {
                    dp[right][i] = dp[left][right] == 0 ? 3 : dp[left][right] + 1;
                    res = res > dp[right][i] ? res : dp[right][i];
                    left++;
                    right--;
                }
            }
        }
        return res;
    }
}
