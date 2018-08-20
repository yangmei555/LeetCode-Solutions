class Solution {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        List<Integer>[] adj = new List[numCourses];
        for (int i = 0; i < adj.length; i++)
            adj[i] = new LinkedList<>();
        for (int[] i : prerequisites)
            adj[i[1]].add(i[0]);
        int[] states = new int[numCourses];
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            if (states[i] == 0) {
                if (!helper(i, adj, states, list))
                    return new int[0];
            }
        }
        int[] res = new int[numCourses];
        for (int i = 0; i < res.length; i++)
            res[i] = list.get(res.length-1-i);
        return res;
    }
    
    public boolean helper(int vertex, List<Integer>[] adj, int[] states, List<Integer> list) {
        states[vertex] = 1;
        for (int v : adj[vertex]) {
            if (states[v] == 0) {
                if (!helper(v, adj, states, list))
                    return false;
            } else if (states[v] == 1) {
                return false;
            }
        }
        states[vertex] = 2;
        list.add(vertex);
        return true;
    }
}
