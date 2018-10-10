class Solution {
    public double[] calcEquation(String[][] equations, double[] values, String[][] queries) {
        Map<String, Integer> map = new HashMap<>();
        int index = 0;
        for (String[] strs : equations) {
            if (!map.containsKey(strs[0]))
                map.put(strs[0], index++);
            if (!map.containsKey(strs[1]))
                map.put(strs[1], index++);
        }
        List<double[]>[] adj = new List[index];
        for (int i = 0; i < adj.length; i++)
            adj[i] = new LinkedList<>();
        for (int i = 0; i < equations.length; i++) {
            int index1 = map.get(equations[i][0]), index2 = map.get(equations[i][1]);
            adj[index1].add(new double[]{index2, values[i]});
            adj[index2].add(new double[]{index1, 1 / values[i]});
        }
        double[] res = new double[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int index1 = map.getOrDefault(queries[i][0], -1);
            int index2 = map.getOrDefault(queries[i][1], -1);
            if (index1 == -1 || index2 == -1) {
                res[i] = -1;
            } else {
                res[i] = helper(adj, index1, index2, new boolean[index]);
                if (res[i] == Integer.MAX_VALUE)
                    res[i] = -1;
            }
        }
        return res;
    }
    
    public double helper(List<double[]>[] adj, int start, int end, boolean[] visited) {
        visited[start] = true;
        if (start == end)
            return 1;
        for (double[] dou : adj[start]) {
            if (!visited[(int)dou[0]]) {
                double ret = helper(adj, (int)dou[0], end, visited);
                if (ret != Integer.MAX_VALUE)
                    return dou[1] * ret;
            }
        }
        return Integer.MAX_VALUE;
    }
}
