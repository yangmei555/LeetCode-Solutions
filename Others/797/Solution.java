// exponential time in worst case 
class Solution {
    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        return helper(graph, 0, graph.length-1);
    }
    
    public List<List<Integer>> helper(int[][] graph, int source, int target) {
        List<List<Integer>> res = new LinkedList<>();
        if (source == target) {
            List<Integer> list = new LinkedList<>();
            list.add(source);
            res.add(list);
        } else {
            for (int v : graph[source]) {
                List<List<Integer>> ret = helper(graph, v, target);
                for (List<Integer> list : ret) {
                    list.add(0, source);
                    res.add(list);
                }
            } 
        }
        return res;
    }
}


// memoization 
class Solution {
    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        List<List<Integer>>[] record = new List[graph.length];
        return helper(graph, record, 0, graph.length-1);
    }
    
    public List<List<Integer>> helper(int[][] graph, List<List<Integer>>[] record, 
                                                                int source, int target) {
        if (record[source] != null)
            return record[source];
        List<List<Integer>> res = new LinkedList<>();
        if (source == target) {
            List<Integer> list = new LinkedList<>();
            list.add(source);
            res.add(list);
        } else {
            for (int v : graph[source]) {
                List<List<Integer>> ret = helper(graph, record, v, target);
                for (List<Integer> list : ret) {
                    List<Integer> temp = new LinkedList<>();
                    temp.add(source);
                    temp.addAll(list);
                    res.add(temp);
                }
            } 
        }
        record[source] = res;
        return res;
    }
}


// a different way of using DFS 
class Solution {
    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        List<List<Integer>> res = new LinkedList<>();
        List<Integer> list = new LinkedList<>();
        helper(graph, res, list, 0, graph.length-1);
        return res;
    }
    
    public void helper(int[][] graph, List<List<Integer>> res, List<Integer> list, 
                                                                int source, int target) {
        list.add(source);
        if (source == target) {
            res.add(new LinkedList<>(list));
        } else {
            for (int v : graph[source])
                helper(graph, res, list, v, target);
        }
        list.remove(list.size()-1);
    }
}
