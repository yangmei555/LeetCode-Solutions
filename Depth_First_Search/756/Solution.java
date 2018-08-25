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


class Solution {
    public boolean pyramidTransition(String bottom, List<String> allowed) {
        boolean[][] map = new boolean[7 * 7][7];
        for (String str : allowed) {
            int index = (str.charAt(0) - 'A') * 7 + (str.charAt(1) - 'A');
            map[index][str.charAt(2)-'A'] = true;
        }
        return helper(new StringBuilder(bottom), new StringBuilder(), map);
    }
    
    public boolean helper(StringBuilder bottom, StringBuilder cur, boolean[][] map) {
        if (bottom.length() == 1) {
            return true;
        } else {
            int len = cur.length();
            int index = (bottom.charAt(len) - 'A') * 7 + (bottom.charAt(len+1) - 'A');
            for (int i = 0; i < map[index].length; i++) {
                if (map[index][i]) {
                    char c = (char)('A' + i);
                    cur.append(c);
                    if (cur.length()+1 == bottom.length()) {
                        if (helper(cur, new StringBuilder(), map))
                            return true;
                    } else {
                        if (helper(bottom, cur, map))
                            return true;
                    }
                    cur.deleteCharAt(cur.length()-1);
                }
            }
            return false;
        }
    }
}
