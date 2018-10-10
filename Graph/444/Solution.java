class Solution {
    public boolean sequenceReconstruction(int[] org, List<List<Integer>> seqs) {
        List<Integer>[] adj = new List[org.length+1];
        for (List<Integer> list : seqs) {
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i) < 1 || list.get(i) > org.length)
                    return false;
                if (adj[list.get(i)] == null)
                    adj[list.get(i)] = new LinkedList<>();
                if (i != list.size()-1) {
                    if (list.get(i+1) < 1 || list.get(i+1) > org.length)
                        return false;
                    if (adj[list.get(i+1)] == null)
                        adj[list.get(i+1)] = new LinkedList<>();
                    adj[list.get(i)].add(list.get(i+1));
                }
            }
        }
        int[] state = new int[org.length+1];
        List<Integer> topo = new LinkedList<>();
        for (int i = 1; i <= org.length; i++) {
            if (adj[i] == null || state[i] == 0 && !helper(adj, i, state, topo))
                return false;
        }
        if (topo.size() != org.length)
            return false;
        for (int i = 0; i < org.length; i++) {
            if (org[i] != topo.get(i))
                return false;
        }
        for (int i = 0; i < org.length-1; i++) {
            if (!adj[org[i]].contains(org[i+1]))
                return false;
        }
        return true;
    }
    
    public boolean helper(List<Integer>[] adj, int node, int[] state, List<Integer> topo) {
        state[node] = 1;
        for (int v : adj[node]) {
            if (state[v] == 1 || state[v] == 0 && !helper(adj, v, state, topo))
                return false;
        }
        state[node] = 2;
        topo.add(0, node);
        return true;
    }
}


// topological sort BFS version 
class Solution {
    public boolean sequenceReconstruction(int[] org, List<List<Integer>> seqs) {
        List<Integer>[] adj = new List[org.length+1];
        int[] indegree = new int[org.length+1];
        for (List<Integer> list : seqs) {
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i) < 1 || list.get(i) > org.length)
                    return false;
                if (adj[list.get(i)] == null)
                    adj[list.get(i)] = new LinkedList<>();
                if (i != list.size()-1) {
                    if (list.get(i+1) < 1 || list.get(i+1) > org.length)
                        return false;
                    if (adj[list.get(i+1)] == null)
                        adj[list.get(i+1)] = new LinkedList<>();
                    adj[list.get(i)].add(list.get(i+1));
                    indegree[list.get(i+1)]++;
                }
            }
        }
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 1; i <= org.length; i++) {
            if (adj[i] == null)
                return false;
            if (indegree[i] == 0)
                queue.offer(i);
        }
        if (queue.isEmpty())
            return false;
        int count = 0;
        while (!queue.isEmpty()) {
            if (queue.size() != 1)
                return false;
            int node = queue.poll();
            if (node != org[count])
                return false;
            count++;
            for (int v : adj[node]) {
                if (--indegree[v] == 0)
                    queue.offer(v);
            }
        }
        return count == org.length;
    }
}


// no graph theory, just check whether two consecutive elements in org all appear consecutive 
// in seqs . also, if the last index element appear, mark the last index of found to be true 
// because the OJ has bored test cases like "[1] []"  "[1] [[], []]" 
class Solution {
    public boolean sequenceReconstruction(int[] org, List<List<Integer>> seqs) {
        int[] map = new int[org.length+1];
        boolean[] found = new boolean[org.length];
        for (int i = 0; i < org.length; i++)
            map[org[i]] = i;
        for (List<Integer> list : seqs) {
            if (list.size() == 0)
                continue;
            if (list.get(0) <= 0 || list.get(0) > org.length)
                return false;
            int pre = map[list.get(0)];
            if (pre == org.length-1)
                found[org.length-1] = true;
            for (int i = 1; i < list.size(); i++) {
                if (list.get(i) <= 0 || list.get(i) > org.length)
                    return false;
                int cur = map[list.get(i)];
                if (pre >= cur)
                    return false;
                if (pre + 1 == cur)
                    found[pre] = true;
                pre = cur;
                if (pre == org.length-1)
                found[org.length-1] = true;
            }
        }
        for (boolean b : found) {
            if (!b)
                return false;
        }
        return true;
    }
}
