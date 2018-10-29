class Solution {
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < wordList.size(); i++)
            map.put(wordList.get(i), i);
        boolean[] visited = new boolean[wordList.size()];
        Node start = new Node(beginWord);
        Queue<Node> queue = new LinkedList<>();
        queue.offer(start);
        boolean flag = false;
        while (!queue.isEmpty() && !flag) {
            Node[] thisLevel = new Node[wordList.size()];
            for (int s = queue.size(); s > 0; s--) {
                Node node = queue.poll();
                if (endWord.equals(node.str)) {
                    flag = true;
                    break;
                }
                char[] ch = node.str.toCharArray();
                for (int p = 0; p < ch.length; p++) {
                    int offset = ch[p] - 'a';
                    for (int j = 0; j < 25; j++) {
                        offset = (offset + 1) % 26;
                        ch[p] = (char)('a' + offset);
                        Integer key = map.get(new String(ch));
                        if (key != null) {
                            if (!visited[key]) {
                                thisLevel[key] = new Node(wordList.get(key));
                                node.list.add(thisLevel[key]);
                                queue.offer(thisLevel[key]);
                                visited[key] = true;
                            } else if (thisLevel[key] != null) {
                                node.list.add(thisLevel[key]);
                            }
                        }
                    }
                    offset = (offset + 1) % 26;
                    ch[p] = (char)('a' + offset);
                }
            }
        }
        if (!flag)
            return new LinkedList<>();
        else
            return helper(start, endWord, new HashMap<String, List<List<String>>>());
    }
    
    public List<List<String>> helper(Node root, String endWord, 
                                                    Map<String, List<List<String>>> memo) {
        if (memo.containsKey(root.str))
            return memo.get(root.str);
        List<List<String>> res = new LinkedList<>();
        if (root.str.equals(endWord)) {
            List<String> list = new LinkedList<>();
            list.add(endWord);
            res.add(list);
        } else {
            for (Node node : root.list) {
                List<List<String>> ret = helper(node, endWord, memo);
                for (List<String> temp : ret) {
                    List<String> list = new LinkedList<>();
                    list.add(root.str);
                    list.addAll(temp);
                    res.add(list);
                }
            }
        }
        memo.put(root.str, res);
        return res;
    }
    
    class Node {
        String str;
        List<Node> list;
        public Node(String str) {
            this.str = str;
            list = new LinkedList<>();
        }
    }
}
