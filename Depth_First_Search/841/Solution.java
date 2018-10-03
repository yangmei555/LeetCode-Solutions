class Solution {
    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        boolean[] visited = new boolean[rooms.size()];
        helper(rooms, 0, visited);
        for (boolean v : visited) {
            if (!v)
                return false;
        }
        return true;
    }
    
    public void helper(List<List<Integer>> rooms, int r, boolean[] visited) {
        visited[r] = true;
        for (int v : rooms.get(r)) {
            if (!visited[v])
                helper(rooms, v, visited);
        }
    }
}


// iterative version using a stack 
class Solution {
    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        boolean[] visited = new boolean[rooms.size()];
        int[] stack = new int[rooms.size()];
        int index = 0;
        visited[0] = true;
        stack[index++] = 0;
        while (index != 0) {
            int u = stack[--index];
            for (int v : rooms.get(u)) {
                if (!visited[v]) {
                    visited[v] = true;
                    stack[index++] = v;
                }
            }
        }
        for (boolean b : visited) {
            if (!b)
                return false;
        }
        return true;
    }
}
