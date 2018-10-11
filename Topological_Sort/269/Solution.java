// a typical topological sort problem 
class Solution {
    public String alienOrder(String[] words) {
        if (words.length == 0)
            return "";
        boolean[][] adj = new boolean[26][26];
        boolean[] appear = new boolean[26];
        for (char c : words[0].toCharArray())
            appear[c-'a'] = true;
        for (int i = 1; i < words.length; i++) {
            int diff = -1;
            for (int j = 0; j < words[i].length(); j++) {
                if (j < words[i-1].length() && diff == -1 && 
                    words[i-1].charAt(j) != words[i].charAt(j))
                    diff = j;
                appear[words[i].charAt(j)-'a'] = true;
            }
            if (diff != -1)
                adj[words[i-1].charAt(diff)-'a'][words[i].charAt(diff)-'a'] = true;
        }
        int[] state = new int[26];
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < appear.length; i++) {
            if (appear[i] && state[i] == 0) {
                if (!helper(adj, i, state, sb))
                    return "";
            }
        }
        return sb.reverse().toString();
    }
    
    public boolean helper(boolean[][] adj, int node, int[] state, StringBuilder sb) {
        state[node] = 1;
        for (int i = 0; i < adj[node].length; i++) {
            if (adj[node][i]) {
                if (state[i] == 1)
                    return false;
                else if (state[i] == 0 && !helper(adj, i, state, sb))
                    return false;
            }
        }
        state[node] = 2;
        sb.append((char)('a' + node));
        return true;
    }
}
