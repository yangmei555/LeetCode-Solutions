class Solution {
    public int minMutation(String start, String end, String[] bank) {
        char[][] ch = new char[bank.length][];
        for (int i = 0; i < ch.length; i++)
            ch[i] = bank[i].toCharArray();
        boolean[] visited = new boolean[bank.length];
        Queue<char[]> queue = new LinkedList<>();
        queue.offer(start.toCharArray());
        int res = 0;
        while (!queue.isEmpty()) {
            for (int size = queue.size(); size > 0; size--) {
                char[] node = queue.poll();
                if (end.equals(new String(node)))
                    return res;
                for (int i = 0; i < ch.length; i++) {
                    if (!visited[i]) {
                        int diff = 0;
                        for (int j = 0; j < ch[i].length; j++) {
                            if (node[j] != ch[i][j])
                                diff++;
                        }
                        if (diff == 1) {
                            queue.offer(ch[i]);
                            visited[i] = true;
                        }
                    }
                }
            }
            res++;
        }
        return -1;
    }
}
