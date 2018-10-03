class Solution {
    int[] id, rank;
    public int[] findRedundantConnection(int[][] edges) {
        id = new int[edges.length+1];
        rank = new int[edges.length+1];
        for (int i = 0; i < id.length; i++) 
            id[i] = i;
        for (int[] e : edges) {
            int rep1 = find(e[0]), rep2 = find(e[1]);
            if (rep1 == rep2)
                return e;
            if (rank[rep1] < rank[rep2]) {
                id[rep1] = rep2;
            } else if (rank[rep1] > rank[rep2]) {
                id[rep2] = rep1;
            } else {
                id[rep2] = rep1;
                rank[rep1]++;
            }
        }
        return null;
    }
    
    public int find(int i) {
        while (i != id[i]) {
            id[i] = id[id[i]];
            i = id[i];
        }
        return i;
    }
}
