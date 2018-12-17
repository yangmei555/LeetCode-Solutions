class Solution {
    public int maxEnvelopes(int[][] envelopes) {
        Arrays.sort(envelopes, new Comparator<int[]>() {
            public int compare(int[] a, int[] b) {
                if (a[0] == b[0]) 
                    return b[1] - a[1];
                else
                    return a[0] - b[0];
            } 
        });
        int[] inc = new int[envelopes.length];
        int index = 0;
        for (int i = 0; i < envelopes.length; i++) {
            int pos = search(inc, envelopes[i][1], index);
            if (pos == index)
                inc[index++] = envelopes[i][1];
            else
                inc[pos] = envelopes[i][1];
        }
        return index;
    }
    
    public int search(int[] inc, int elem, int index) {
        int left = 0, right = index;
        while (left < right) {
            int mid = (left + right) / 2;
            if (elem > inc[mid])
                left = mid + 1;
            else
                right = mid;
        }
        return left;
    }
}


class Solution {
    public int maxEnvelopes(int[][] envelopes) {
        Arrays.sort(envelopes, new Comparator<int[]>() {
            public int compare(int[] a, int[] b) {
                if (a[0] == b[0]) 
                    return b[1] - a[1];
                else
                    return a[0] - b[0];
            } 
        });
        int[] inc = new int[envelopes.length];
        int index = 0;
        for (int i = 0; i < envelopes.length; i++) {
            int pos = Arrays.binarySearch(inc, 0, index, envelopes[i][1]);
            if (pos < 0) {
                pos = - pos - 1;
                inc[pos] = envelopes[i][1];
                if (pos == index)
                    index++;
            }
        }
        return index;
    }
}


class Solution {
    public int maxEnvelopes(int[][] envelopes) {
        if (envelopes.length == 0)
            return 0;
        Arrays.sort(envelopes, new Comparator<int[]>() {
            public int compare(int[] a, int[] b) {
                return a[0] - b[0];
            } 
        });
        int[] dp = new int[envelopes.length];
        int res = 0;
        for (int i = 0; i < envelopes.length; i++) {
            dp[i] = 1;
            for (int j = 0; j < i && envelopes[j][0] != envelopes[i][0]; j++) {
                if (envelopes[j][1] < envelopes[i][1])
                    dp[i] = dp[i] > 1 + dp[j] ? dp[i] : 1 + dp[j];
            }
            res = res > dp[i] ? res : dp[i];
        }
        return res;
    }
}


// find longest path in a DAG ... 
class Solution {
    public int maxEnvelopes(int[][] envelopes) {
        int[] dist = new int[envelopes.length];
        for (int i = 0; i < envelopes.length; i++) {
            if (dist[i] == 0)
                helper(envelopes, i, dist);
        }
        int res = 0;
        for (int d : dist)
            res = Math.max(res, d);
        return res;
    }
    
    public int helper(int[][] envelopes, int node, int[] dist) {
        if (dist[node] != 0)
            return dist[node];
        int res = 1;
        for (int i = 0; i < envelopes.length; i++) {
            if (envelopes[node][0] < envelopes[i][0] && envelopes[node][1] < envelopes[i][1])
                res = Math.max(res, helper(envelopes, i, dist) + 1);
        }
        dist[node] = res;
        return res;
    }
}
