class Solution {
    public List<String> findItinerary(String[][] tickets) {
        Map<String, List<String>> map = new HashMap<>();
        for (String[] str : tickets) {
            map.putIfAbsent(str[0], new LinkedList<>());
            map.get(str[0]).add(str[1]);
        }
        for (String s : map.keySet())
            Collections.sort(map.get(s));
        List<String> res = new LinkedList<>();
        String start = "JFK";
        res.add(start);
        helper(map, start, res, tickets.length+1);
        return res;
    }
    
    public boolean helper(Map<String, List<String>> map, String start, List<String> res, int len) {
        if (!map.containsKey(start) || map.get(start).size() == 0) {
            // System.out.println(res);
            return res.size() == len ? true : false;
        } else {
            int size = map.get(start).size();
            for (int i = 0; i < size; i++) {
                String arrive = map.get(start).get(i);
                map.get(start).remove(i);
                res.add(arrive);
                if (helper(map, arrive, res, len))
                    return true;
                map.get(start).add(i, arrive);
                res.remove(res.size()-1);
            }
            return false;
        }
    }
}


// the correct way to get the Euler path 
// after visiting a node, removing that edge 
class Solution {
    public List<String> findItinerary(String[][] tickets) {
        Map<String, Queue<String>> map = new HashMap<>();
        for (String[] str : tickets) {
            map.putIfAbsent(str[0], new PriorityQueue<>());
            map.get(str[0]).offer(str[1]);
        }
        List<String> res = new LinkedList<>();
        helper(map, "JFK", res);
        return res;
    }
    
    public void helper(Map<String, Queue<String>> map, String start, List<String> res) {
        while (map.containsKey(start) && !map.get(start).isEmpty()) {
            String arrive = map.get(start).poll();
            helper(map, arrive, res);
        }
        res.add(0, start);
    }
}


// iterative version to find the Euler path 
class Solution {
    public List<String> findItinerary(String[][] tickets) {
        Map<String, Queue<String>> map = new HashMap<>();
        for (String[] str : tickets) {
            map.putIfAbsent(str[0], new PriorityQueue<>());
            map.get(str[0]).offer(str[1]);
        }
        List<String> res = new LinkedList<>();
        Stack<String> stack = new Stack<>();
        stack.push("JFK");
        while (!stack.isEmpty()) {
            if (map.containsKey(stack.peek()) && map.get(stack.peek()).size() != 0) {
                stack.push(map.get(stack.peek()).poll());
            } else {
                res.add(stack.pop());
            }
        }
        Collections.reverse(res);
        return res;
    }
}
