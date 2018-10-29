class Solution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        char[][] bridge = new char[wordList.size()][];
        for (int i = 0; i < wordList.size(); i++)
            bridge[i] = wordList.get(i).toCharArray();
        boolean[] visited = new boolean[bridge.length];
        Queue<char[]> queue = new LinkedList<>();
        queue.offer(beginWord.toCharArray());
        int step = 1;
        while (!queue.isEmpty()) {
            for (int i = queue.size(); i > 0; i--) {
                char[] node = queue.poll();
                for (int j = 0; j < bridge.length; j++) {
                    if (!visited[j]) {
                        int diff = 0;
                        for (int p = 0; p < node.length; p++) {
                            if (node[p] != bridge[j][p]) {
                                diff++;
                                if (diff == 2)
                                    break;
                            }
                        }
                        if (diff == 1) {
                            if (wordList.get(j).equals(endWord))
                                return step + 1;
                            visited[j] = true;
                            queue.offer(bridge[j]);
                        }
                    }
                }
            }
            step++;
        }
        return 0;
    }
}


class Solution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        char[][] bridge = new char[wordList.size()][];
        for (int i = 0; i < wordList.size(); i++)
            bridge[i] = wordList.get(i).toCharArray();
        char[] begin = beginWord.toCharArray();
        boolean[] visited = new boolean[bridge.length];
        Queue<Integer> queue = new LinkedList<>();
        List<Integer>[] adj = new List[bridge.length];
        for (int i = 0; i < adj.length; i++)
            adj[i] = new LinkedList<>();
        for (int i = 0; i < bridge.length; i++) {
            int diff = 0;
            for (int p = 0; p < begin.length; p++) {
                if (begin[p] != bridge[i][p]) {
                    diff++;
                    if (diff == 2)
                        break;
                }
            }
            if (diff == 1) {
                if (endWord.equals(wordList.get(i)))
                    return 2;
                queue.offer(i);
                visited[i] = true;
            }
            for (int j = i+1; j < bridge.length; j++) {
                diff = 0;
                for (int p = 0; p < begin.length; p++) {
                    if (bridge[i][p] != bridge[j][p]) {
                        diff++;
                        if (diff == 2)
                            break;
                    }
                }
                if (diff == 1) {
                    adj[i].add(j);
                    adj[j].add(i);
                }
            }
        }
        int step = 2;
        while (!queue.isEmpty()) {
            for (int size = queue.size(); size > 0; size--) {
                int node = queue.poll();
                if (endWord.equals(wordList.get(node)))
                    return step;
                for (int v : adj[node]) {
                    if (!visited[v]) {
                        visited[v] = true;
                        queue.offer(v);
                    }
                }
            }
            step++;
        }
        return 0;
    }
}


class Solution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < wordList.size(); i++)
            map.put(wordList.get(i), i);
        boolean[] visited = new boolean[wordList.size()];
        Queue<String> queue = new LinkedList<>();
        queue.offer(beginWord);
        int step = 1;
        while (!queue.isEmpty()) {
            for (int s = queue.size(); s > 0; s--) {
                String node = queue.poll();
                if (endWord.equals(node))
                    return step;
                char[] ch = node.toCharArray();
                for (int p = 0; p < ch.length; p++) {
                    int offset = ch[p] - 'a';
                    for (int j = 0; j < 25; j++) {
                        offset = (offset + 1) % 26;
                        ch[p] = (char)('a' + offset);
                        Integer key = map.get(new String(ch));
                        if (key != null && !visited[key]) {
                            queue.offer(wordList.get(key));
                            visited[key] = true;
                        }
                    }
                    offset = (offset + 1) % 26;
                    ch[p] = (char)('a' + offset);
                }
            }
            step++;
        }
        return 0;
    }
}
