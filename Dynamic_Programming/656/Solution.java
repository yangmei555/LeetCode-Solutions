class Solution {
    public List<Integer> cheapestJump(int[] A, int B) {
        List<Integer> list = new LinkedList<>();
        if (A[A.length-1] == -1)
            return list;
        int[] dp = new int[A.length];
        dp[0] = A[0];
        for (int i = 1; i < A.length; i++) {
            if (A[i] == -1)
                continue;
            dp[i] = Integer.MAX_VALUE;
            for (int j = i-1; j >= 0 && j >= i - B; j--) {
                if (A[j] != -1)
                    dp[i] = dp[i] < dp[j] + A[i] ? dp[i] : dp[j] + A[i];
            }
            if (dp[i] == Integer.MAX_VALUE)
                return list;
        }
        boolean[] visited = new boolean[A.length];
        list.add(1);
        helper(A, B, dp, 0, list, visited);
        return list;
    }
    
    public boolean helper(int[] A, int B, int[] dp, int index, List<Integer> list, 
                                                                boolean[] visited) {
        if (visited[index])
            return false;
        if (index == A.length-1) 
            return true;
        for (int i = index+1; i < A.length && i <= index+B; i++) {
            if (A[i] != -1 && dp[index] + A[i] == dp[i]) {
                list.add(i+1);
                if (helper(A, B, dp, i, list, visited))
                    return true;
                list.remove(list.size()-1);
            }
        }
        visited[index] = true;
        return false;
    }
}


class Solution {
    public List<Integer> cheapestJump(int[] A, int B) {
        List<Integer> list = new LinkedList<>();
        if (A[A.length-1] == -1)
            return list;
        int[] dp = new int[A.length];
        int[] next = new int[A.length];
        dp[A.length-1] = A[A.length-1];
        next[A.length-1] = A.length;
        for (int i = A.length-2; i >= 0; i--) {
            if (A[i] == -1)
                continue;
            dp[i] = Integer.MAX_VALUE;
            for (int j = i+1; j < A.length && j <= i+B; j++) {
                if (A[j] != -1 && dp[i] > A[i] + dp[j]) {
                    dp[i] = A[i] + dp[j];
                    next[i] = j;
                }
            }
            if (dp[i] == Integer.MAX_VALUE)
                return list;
        }
        for (int i = 0; i < A.length; i = next[i])
            list.add(i+1);
        return list;
    }
}
