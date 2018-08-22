// DFS version 
class Solution {
    public boolean isBipartite(int[][] graph) {
        Boolean[] color = new Boolean[graph.length];
        for (int i = 0; i < graph.length; i++) {
            if (color[i] == null) {
                color[i] = true;
                if (!helper(i, graph, color))
                    return false;
            }
        }
        return true;
    }
    
    public boolean helper(int vertex, int[][] graph, Boolean[] color) {
        for (int v : graph[vertex]) {
            if (color[v] == null) {
                color[v] = !color[vertex];
                if (!helper(v, graph, color))
                    return false;
            } else if (color[v] == color[vertex]) {
                return false;
            }
        }
        return true;
    }
}


// BFS version 
class Solution {
    public boolean isBipartite(int[][] graph) {
        Boolean[] color = new Boolean[graph.length];
        for (int i = 0; i < graph.length; i++) {
            if (color[i] == null) {
                Queue<Integer> queue = new LinkedList<>();
                color[i] = true;
                queue.offer(i);
                while (!queue.isEmpty()) {
                    int u = queue.poll();
                    for (int v : graph[u]) {
                        if (color[v] == null) {
                            color[v] = !color[u];
                            queue.offer(v);
                        } else if (color[v] == color[u]) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }
}


// just change last solution's Queue to Stack 
class Solution {
    public boolean isBipartite(int[][] graph) {
        Boolean[] color = new Boolean[graph.length];
        for (int i = 0; i < graph.length; i++) {
            if (color[i] == null) {
                Stack<Integer> stack = new Stack<>();
                color[i] = true;
                stack.push(i);
                while (!stack.isEmpty()) {
                    int u = stack.pop();
                    for (int v : graph[u]) {
                        if (color[v] == null) {
                            color[v] = !color[u];
                            stack.push(v);
                        } else if (color[v] == color[u]) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }
}
