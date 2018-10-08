// rank is not used here, it is not necessary. once e[1] is not a root, it is recorded by 
// the secondParent variable 
// at least an edge caused cycle and/or an edge caused double parent will exist , 
// once find both, can get the result immediately  
class Solution {
    public int[] findRedundantDirectedConnection(int[][] edges) {
        int N = edges.length;
        int[] parent = new int[N+1], rank = new int[N+1];
        for (int i = 0; i < parent.length; i++)
            parent[i] = i;
        int[] secondParent = null, cycle = null;
        for (int[] e : edges) {
            if (parent[e[1]] != e[1]) {
                if (cycle != null)
                    return helper(edges, e);
                secondParent = e;
            } else if (find(parent, e[0]) == e[1]) {
                if (secondParent != null)
                    return helper(edges, secondParent);
                else
                    cycle = e;
            } else {
                parent[e[1]] = find(parent, e[0]);
            }
        }
        if (cycle == null)
            return secondParent;
        else
            return cycle;
    }
    
    public int[] helper(int[][] edges, int[] secondParent) {
        for (int[] e : edges) {
            if (e[0] != secondParent[0] && e[1] == secondParent[1])
                return e;
        }
        return null;
    }
    
    public int find(int[] parent, int i) {
        while (i != parent[i]) {
            parent[i] = parent[parent[i]];
            i = parent[i];
        }
        return i;
    }
}
