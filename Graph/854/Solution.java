// the brainless brute force method 
// use memoization will significantly improve the performance 
class Solution {
    // Map<String, Integer> memo = new HashMap<>();
    public int kSimilarity(String A, String B) {
        char[] ch1 = A.toCharArray(), ch2 = B.toCharArray();
        return helper(ch1, ch2, 0);
    }
    
    public int helper(char[] ch1, char[] ch2, int index) {
        if (index == ch1.length)
            return 0;
        if (ch1[index] == ch2[index])
            return helper(ch1, ch2, index+1);
        // String key = new String(ch1);
        // if (memo.containsKey(key))
        //     return memo.get(key);
        int res = Integer.MAX_VALUE;
        for (int i = index+1; i < ch1.length; i++) {
            if (ch1[i] == ch2[index]) {
                swap(ch1, i, index);
                res = Math.min(res, 1 + helper(ch1, ch2, index+1));
                swap(ch1, i, index);
            }
        }
        // memo.put(key, res);
        return res;
    }
    
    public void swap(char[] ch, int i, int j) {
        char temp = ch[i];
        ch[i] = ch[j];
        ch[j] = temp;
    }
}


// do some preprocess, will significantly improve performance. but no idea why 
// also, change line 70 to "if (ch1[i] == ch2[index] && ch1[i] != ch2[i]) {"  will beats 100% 
class Solution {
    public int kSimilarity(String A, String B) {
        char[] ch1 = A.toCharArray(), ch2 = B.toCharArray();
        int res = preprocess(ch1, ch2);
        return res + helper(ch1, ch2, 0);
    }
    
    public int preprocess(char[] ch1, char[] ch2) {
        int res = 0;
        for (int i = 0; i < ch1.length; i++) {
            if (ch1[i] != ch2[i]) {
                for (int j = i+1; j < ch1.length; j++) {
                    if (ch1[j] == ch2[i] && ch1[i] == ch2[j]) {
                        swap(ch1, i, j);
                        res++;
                        break;
                    }
                }
            }
        }
        return res;
    }
    
    public int helper(char[] ch1, char[] ch2, int index) {
        if (index == ch1.length)
            return 0;
        if (ch1[index] == ch2[index])
            return helper(ch1, ch2, index+1);
        int res = Integer.MAX_VALUE;
        for (int i = index+1; i < ch1.length; i++) {
            if (ch1[i] == ch2[index]) {
                swap(ch1, i, index);
                res = Math.min(res, 1 + helper(ch1, ch2, index+1));
                swap(ch1, i, index);
            }
        }
        return res;
    }
    
    public void swap(char[] ch, int i, int j) {
        char temp = ch[i];
        ch[i] = ch[j];
        ch[j] = temp;
    }
}


// generate all permutations then get the shortest steps to remove circles 
class Solution {
    public int kSimilarity(String A, String B) {
        int[][] graph = new int[6][6];
        char[] ch1 = A.toCharArray(), ch2 = B.toCharArray();
        for (int i = 0; i < ch1.length; i++) {
            if (ch1[i] != ch2[i])
                graph[ch1[i]-'a'][ch2[i]-'a']++;
        }
        int[] permu = new int[6];
        for (int i = 0; i < permu.length; i++)
            permu[i] = i;
        return generate(permu, 0, graph);
    }
    
    public int generate(int[] permu, int index, int[][] graph) {
        if (index == permu.length) {
            int res = 0;
            int[][] map = new int[6][6];
            for (int i = 0; i < map.length; i++)
                map[i] = graph[i].clone();
            for (int i = 0; i < map.length; i++) {
                for (int j = 0; j < map[0].length; j++) {
                    while (map[permu[i]][permu[j]] != 0) {
                        map[permu[i]][permu[j]]--;
                        res += helper(map, permu[j], permu[i], permu);
                    }
                }
            }
            return res;
        } else {
            int res = Integer.MAX_VALUE;
            for (int i = index; i < permu.length; i++) {
                swap(permu, index, i);
                res = Math.min(res, generate(permu, index+1, graph));
                swap(permu, index, i);
            }
            return res;
        }
    }
    
    public void swap(int[] permu, int i, int j) {
        int temp = permu[i];
        permu[i] = permu[j];
        permu[j] = temp;
    }
    
    public int helper(int[][] map, int node, int target, int[] permu) {
        if (node == target)
            return 0;
        for (int j = 0; j < map[node].length; j++) {
            if (map[node][permu[j]] != 0) {
                map[node][permu[j]]--;
                return 1 + helper(map, permu[j], target, permu);
            }
        }
        return -1;
    }
}


// BFS, notice the condition of doing swap, it will opetimize a lot compared with other conditions 
class Solution {
    public int kSimilarity(String A, String B) {
        Queue<String> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        int res = 0;
        queue.offer(A);
        visited.add(A);
        char[] ch2 = B.toCharArray();
        while (!queue.isEmpty()) {
            for (int i = queue.size(); i > 0; i--) {
                char[] ch1 = queue.poll().toCharArray();
                int index = 0;
                while (index < ch1.length) {
                    if (ch1[index] != ch2[index])
                        break;
                    index++;
                }
                if (index == ch1.length)
                    return res;
                for (int j = index+1; j < ch1.length; j++) {
                    if (ch1[index] == ch2[j] && ch1[j] != ch2[j]) {
                        swap(ch1, j, index);
                        String s = new String(ch1);
                        if (!visited.contains(s)) {
                            queue.offer(s);
                            visited.add(s);
                        }
                        swap(ch1, j, index);
                    }
                }
            }
            res++;
        }
        return res;
    }
    
    public void swap(char[] ch, int i, int j) {
        char temp = ch[i];
        ch[i] = ch[j];
        ch[j] = temp;
    }
}
