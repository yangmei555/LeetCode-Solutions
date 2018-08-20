class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        // adjacency list
        List<Integer>[] adj = new List[numCourses];
        for (int i = 0; i < adj.length; i++)
            adj[i] = new LinkedList<>();
        for (int[] i : prerequisites)
            adj[i[0]].add(i[1]);
        int[] states = new int[numCourses];
        for (int i = 0; i < numCourses; i++) {
            if (states[i] == 0) {
                if (!helper(i, adj, states))
                    return false;
            }
        }
        return true;
    }
    
    public boolean helper(int vertex, List<Integer>[] adj, int[] states) {
        states[vertex] = 1;
        for (int v : adj[vertex]) {
            if (states[v] == 0) {
                if (!helper(v, adj, states))
                    return false;
            } else if (states[v] == 1) {
                return false;
            }
        }
        states[vertex] = 2;
        return true;
    }
}


class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        // adjacency list 
        boolean[][] adj = new boolean[numCourses][numCourses];
        for (int[] i : prerequisites)
            adj[i[0]][i[1]] = true;
        int[] states = new int[numCourses];
        for (int i = 0; i < numCourses; i++) {
            if (states[i] == 0) {
                if (!helper(i, adj, states))
                    return false;
            }
        }
        return true;
    }
    
    public boolean helper(int vertex, boolean[][] adj, int[] states) {
        states[vertex] = 1;
        for (int v = 0; v < adj[0].length; v++) {
            if (adj[vertex][v]) {
                if (states[v] == 0) {
                    if (!helper(v, adj, states))
                        return false;
                } else if (states[v] == 1) {
                    return false;
                }
            }
        }
        states[vertex] = 2;
        return true;
    }
}


// did not convert the input into a graph 
class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        int[] states = new int[numCourses];
        for (int i = 0; i < numCourses; i++) {
            if (states[i] == 0) {
                if (!helper(i, prerequisites, states))
                    return false;
            }
        }
        return true;
    }
    
    public boolean helper(int vertex, int[][] prereq, int[] states) {
        states[vertex] = 1;
        for (int i = 0; i < prereq.length; i++) {
            if (prereq[i][0] == vertex) {
                int v = prereq[i][1];
                if (states[v] == 0) {
                    if (!helper(v, prereq, states))
                        return false;
                } else if (states[v] == 1) {
                    return false;
                }
            }
        }
        states[vertex] = 2;
        return true;
    }
}
