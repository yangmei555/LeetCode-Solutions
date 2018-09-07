// this solution is not totally correct , it does not search wide enough 
// eg. "RRWWRRBBRR", "WB" , "RRWWRRB[B]BR[W]R" can succeed , so the return should be 2 
// but the OJ and this solution returns -1 
class Solution {
    public int findMinStep(String board, String hand) {
        int[] map = new int[26];
        map['R'-'A'] = 0;
        map['Y'-'A'] = 1;
        map['B'-'A'] = 2;
        map['G'-'A'] = 3;
        map['W'-'A'] = 4;
        int[] count = new int[5];
        for (char c : hand.toCharArray())
            count[map[c-'A']]++;
        return helper(board, count, map);
    }
    
    public int helper(String board, int[] count, int[] map) {
        int index = 0;
        while (index < board.length()) {
            int start = index;
            index++;
            while (index < board.length() && board.charAt(index-1) == board.charAt(index))
                index++;
            if (index - start >= 3) {
                board = board.substring(0, start) + board.substring(index);
                index = 0;
            }
        }
        if (board.length() == 0) 
            return 0;
        int res = Integer.MAX_VALUE;
        for (int i = 0; i < board.length(); i++) {
            char c = board.charAt(i);
            boolean flag = i+1 < board.length() && board.charAt(i) == board.charAt(i+1);
            int require = flag ? 1 : 2;
            if (count[map[c-'A']] >= require) {
                count[map[c-'A']] -= require;
                String str = board.substring(0, i) + 
                                (flag ? board.substring(i+2) : board.substring(i+1));
                int ret = helper(str, count, map);
                if (ret != -1)
                    res = res < require + ret ? res : require + ret;
                count[map[c-'A']] += require;
            }
            if (flag)
                i++;
        }
        return res == Integer.MAX_VALUE ? -1 : res;
    }
}


// this solution searches every possibility , it can handle the case mentinoed above 
// but it causes TLE when running on the OJ 
class Solution {
    public int findMinStep(String board, String hand) {
        int[] map1 = new int[128];
        map1['R'] = 0;
        map1['Y'] = 1;
        map1['B'] = 2;
        map1['G'] = 3;
        map1['W'] = 4;
        int[] count = new int[5];
        for (char c : hand.toCharArray())
            count[map1[c]]++;
        char[] map2 = new char[]{'R', 'Y', 'B', 'G', 'W'};
        return helper(board, count, map2);
    }
    
    public int helper(String board, int[] count, char[] map) {
        int index = 0;
        while (index < board.length()) {
            int start = index;
            index++;
            while (index < board.length() && board.charAt(index-1) == board.charAt(index))
                index++;
            if (index - start >= 3) {
                board = board.substring(0, start) + board.substring(index);
                index = 0;
            }
        }
        if (board.length() == 0) 
            return 0;
        int res = Integer.MAX_VALUE;
        for (int i = 0; i < count.length; i++) {
            if (count[i] > 0) {
                count[i]--;
                for (int j = 0; j <= board.length(); j++) {
                    String str = board.substring(0, j) + map[i] + board.substring(j);
                    int ret = helper(str, count, map);
                    if (ret != -1) 
                        res = res < 1 + ret ? res : 1 + ret;
                    if (j+1 < board.length() && map[i] == board.charAt(j) && 
                                            board.charAt(j) == board.charAt(j+1))
                        j++;
                }
                count[i]++;
            }
        }
        return res == Integer.MAX_VALUE ? -1 : res;
    }
}
