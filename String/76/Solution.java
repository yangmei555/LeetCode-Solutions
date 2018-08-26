class Solution {
    public String minWindow(String s, String t) {
        char[] source = s.toCharArray(), target = t.toCharArray();
        int[] map = new int[128];
        for (char c : target)
            map[c]++;
        Queue<Integer>[] queues = new Queue[128];
        for (int i = 0; i < queues.length; i++)
            queues[i] = new LinkedList<>();
        boolean[] used = new boolean[source.length];
        int start = -1, len = 0;
        String res = "";
        for (int i = 0; i < source.length; i++) {
            char c = source[i];
            if (queues[c].size() < map[c]) {
                queues[c].offer(i);
                used[i] = true;
                len++;
                if (start == -1)
                    start = i;
            } else if (map[c] > 0 && queues[c].size() == map[c]) {
                int pos = queues[c].poll();
                used[pos] = false;
                queues[c].offer(i);
                used[i] = true;
                if (start == pos) {
                    while (start <= i && !used[start])
                        start++;
                    if (start > i)
                        start = -1;
                }
            }
            if (len == target.length) {
                if (res.length() == 0 || i - start + 1 < res.length()) 
                    res = s.substring(start, i+1);
                queues[source[start]].poll();
                used[start] = false;
                len--;
                start++;
                while (start <= i && !used[start])
                    start++;
                if (start > i)
                    start = -1;
            }
        }
        return res;
    }
}


class Solution {
    public String minWindow(String s, String t) {
        char[] source = s.toCharArray(), target = t.toCharArray();
        int[] map = new int[128], cur = new int[128];
        for (char c : target)
            map[c]++;
        int start = -1, len = 0;
        String res = "";
        for (int i = 0; i < source.length; i++) {
            char c = source[i];
            if (cur[c] < map[c]) {
                cur[c]++;
                len++;
                if (start == -1)
                    start = i;
            } else if (map[c] > 0) {
                if (c != source[start]) {
                    cur[c]++;
                } else {
                    start++;
                    while (start <= i && (map[source[start]] == 0 || 
                                            map[source[start]] < cur[source[start]])) {
                        if (map[source[start]] != 0)
                            cur[source[start]]--;
                        start++;
                    }
                }
            }
            if (len == target.length) {
                if (res.length() == 0 || i - start + 1 < res.length())
                    res = s.substring(start, i+1);
                cur[source[start++]]--;
                len--;
                while (start <= i && (map[source[start]] == 0 || 
                                        map[source[start]] < cur[source[start]])) {
                    if (map[source[start]] != 0)
                        cur[source[start]]--;
                    start++;
                }
                if (start > i)
                    start = -1;
            }
        }
        return res;
    }
}
