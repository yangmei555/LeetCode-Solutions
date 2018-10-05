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


// total number of pairs, minus the number of connected components 
class Solution {
    public int minSwapsCouples(int[] row) {
        List<Integer>[] adj = new List[row.length/2];
        for (int i = 0; i < adj.length; i++)
            adj[i] = new LinkedList<>();
        int[] map = new int[row.length];
        for (int i = 0; i < row.length; i++)
            map[row[i]] = i;
        for (int i = 0; i < map.length; i += 2) {
            adj[map[i]/2].add(map[i+1]/2);
            adj[map[i+1]/2].add(map[i]/2);
        }
        boolean[] visited = new boolean[adj.length];
        int res = adj.length;
        for (int i = 0; i < visited.length; i++) {
            if (!visited[i]) {
                res--;
                helper(adj, i, visited);
            }
        }
        return res;
    }
    
    public void helper(List<Integer>[] adj, int node, boolean[] visited) {
        visited[node] = true;
        for (int v : adj[node]) {
            if (!visited[v]) 
                helper(adj, v, visited);
        }
    }
}


// greedy, swap one by one 
class Solution {
    public int minSwapsCouples(int[] row) {
        int count = 0;
        for (int i = 0; i < row.length; i += 2) {
            if (row[i] / 2 != row[i+1] / 2) {
                count++;
                for (int j = i+2; j < row.length; j++) {
                    if (row[i] / 2 == row[j] / 2) {
                        int temp = row[j];
                        row[j] = row[i+1];
                        row[i+1] = temp;
                        break;
                    }
                }
            }
        }
        return count;
    }
}


// repeat the above idea, but record the positions to achieve O(n) time complexity 
class Solution {
    public int minSwapsCouples(int[] row) {
        int[] map = new int[row.length];
        for (int i = 0; i < row.length; i++)
            map[row[i]] = i;
        int count = 0;
        for (int i = 0; i < row.length; i += 2) {
            if (row[i] / 2 != row[i+1] / 2) {
                count++;
                int partner = row[i] ^ 1;
                int seat = map[partner];
                int temp = row[seat];
                row[seat] = row[i+1];
                row[i+1] = temp;
                map[partner] = i+1;
                map[row[seat]] = seat;
            }
        }
        return count;
    }
}
