class Solution {
    public List<Integer> killProcess(List<Integer> pid, List<Integer> ppid, int kill) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < pid.size(); i++) {
            map.putIfAbsent(ppid.get(i), new LinkedList<>());
            map.get(ppid.get(i)).add(pid.get(i));
        }
        List<Integer> res = new LinkedList<>();
        helper(res, map, kill);
        return res;
    }
    
    public void helper(List<Integer> res, Map<Integer, List<Integer>> map, int kill) {
        res.add(kill);
        if (map.containsKey(kill))
            for (int i : map.get(kill))
                helper(res, map, i);
    }
}


class Solution {
    public List<Integer> killProcess(List<Integer> pid, List<Integer> ppid, int kill) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < pid.size(); i++) {
            map.putIfAbsent(ppid.get(i), new LinkedList<>());
            map.get(ppid.get(i)).add(pid.get(i));
        }
        List<Integer> res = new LinkedList<>();
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(kill);
        while (!queue.isEmpty()) {
            int temp = queue.poll();
            res.add(temp);
            if (map.containsKey(temp)) {
                for (int i : map.get(temp))
                    queue.offer(i);
            }
        }
        return res;
    }
}
