class Solution {
    int[] id, rank;
    public int minSwapsCouples(int[] row) {
        id = new int[row.length/2];
        rank = new int[row.length/2];
        for (int i = 0; i < id.length; i++)
            id[i] = i;
        int[] map = new int[row.length];
        for (int i = 0; i < row.length; i++)
            map[row[i]] = i;
        int res = 0;
        for (int i = 0; i < row.length; i += 2) {
            int pos1 = map[i] / 2, pos2 = map[i+1] / 2;
            if (find(pos1) != find(pos2)) {
                res++;
                union(pos1, pos2);
            }
        }
        return res;
    }
    
    public void union(int i, int j) {
        int x = find(i), y = find(j);
        if (rank[x] < rank[y]) {
            id[x] = y;
        } else if (rank[x] > rank[y]) {
            id[y] = x;
        } else {
            id[y] = x;
            rank[x]++;
        }
    }
    
    public int find(int i) {
        while (i != id[i]) {
            id[i] = id[id[i]];
            i = id[i];
        }
        return i;
    }
}
