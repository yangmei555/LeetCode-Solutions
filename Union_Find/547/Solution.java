class Solution {
    int[] rank, parent;
    public int findCircleNum(int[][] M) {
        int res = M.length;
        parent = new int[res];
        rank = new int[res];
        for (int i = 0; i < parent.length; i++)
            parent[i] = i;
        for (int i = 0; i < M.length; i++) {
            for (int j = i+1; j < M.length; j++) {
                if (M[i][j] == 1 && findSet(i) != findSet(j)) {
                    union(i, j);
                    res--;
                }
            }
        }
        return res;
    }
    
    public int findSet(int i) {
        while (i != parent[i]) {
            parent[i] = parent[parent[i]];
            i = parent[i];
        }
        return i;
    }
    
    public void union(int i, int j) {
        int x = findSet(i), y = findSet(j);
        if (rank[x] < rank[y]) {
            parent[x] = y;
        } else if (rank[x] > rank[y]) {
            parent[y] = x;
        } else {
            parent[y] = x;
            rank[x]++;
        }
    }
}
