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
