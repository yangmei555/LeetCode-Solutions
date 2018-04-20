class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        Set<String> set = new HashSet<>();
        return helper(s, wordDict, set);
    }
    
    public boolean helper(String s, List<String> list, Set<String> set) {
        if (s.equals(""))
            return true;
        set.add(s);
        int i = 0, len = list.size();
        for ( ; i < len; i++) {
            if (s.indexOf(list.get(i)) == 0) {
                if (set.contains(s.substring(list.get(i).length())))
                    continue;
                if (helper(s.substring(list.get(i).length()), list, set))
                    return true;
            }
        }
        return false;
    }
}


class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        boolean[] index = new boolean[s.length() + 1];
        index[s.length()] = true;
        for (int i = s.length() - 1; i >= 0; i--) {
            String sub = s.substring(i);
            for (String w : wordDict) {
                if (sub.indexOf(w) == 0 && index[i + w.length()]) {
                    index[i] = true;
                    break;
                }
            }
        }
        return index[0];
    }
}
