class Solution {
    public boolean pyramidTransition(String bottom, List<String> allowed) {
        Map<String, List<Character>> map = new HashMap<>();
        for (String str : allowed) {
            map.putIfAbsent(str.substring(0, 2), new LinkedList<>());
            map.get(str.substring(0, 2)).add(str.charAt(2));
        }
        return helper(bottom, new String(), map);
    }
    
    public boolean helper(String bottom, String cur, Map<String, List<Character>> map) {
        if (bottom.length() == 1) {
            return true;
        } else {
            int len = cur.length();
            if (!map.containsKey(bottom.substring(len, len+2)))
                return false;
            for (char c : map.get(bottom.substring(len, len+2))) {
                if (cur.length() == bottom.length()-2) {
                    if (helper(cur+c, new String(), map))
                        return true;
                } else {
                    if (helper(bottom, cur+c, map))
                        return true;
                }
            }
            return false;
        }
    }
}
