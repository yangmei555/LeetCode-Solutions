class Solution {
    public String crackSafe(int n, int k) {
        int count = 1;
        for (int i = 1; i < n; i++)
            count *= k;
        boolean[][] map = new boolean[count][k];
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < n; i++)
            sb.append('0');
        helper(sb, map, 0, 0, count * k);
        return sb.toString();
    }
    
    public boolean helper(StringBuilder sb, boolean[][] map, int prefix, int index, int remain) {
        if (remain == 0)
            return true;
        int scale = map.length, k = map[0].length;
        int nextpre = index < sb.length() ? prefix * k - scale * (sb.charAt(index) - '0') : -1;
        for (int i = 0; i < k; i++) {
            if (!map[prefix][i]) {
                sb.append(i);
                map[prefix][i] = true;
                if (helper(sb, map, nextpre == -1 ? 0 : nextpre + i, index + 1, remain - 1))
                    return true;
                sb.deleteCharAt(sb.length()-1);
                map[prefix][i] = false;
            }
        }
        return false;
    }
}


// the same algorithm as Problem 332 , finding Euler path , no duplicate visit 
// walk ahead and remove an edge for each step, when going back, append that edge to result 
// the result is lexicographically largest 
class Solution {
    public String crackSafe(int n, int k) {
        int nums = 1;
        for (int i = 1; i < n; i++)
            nums *= k;
        LinkedList<Integer>[] adj = new LinkedList[nums];
        for (int i = 0; i < nums; i++) {
            adj[i] = new LinkedList<>();
            for (int j = 0; j < k; j++)
                adj[i].add(j);
        }
        StringBuilder sb = new StringBuilder(), snode = new StringBuilder();
        for (int i = 1; i < n; i++)
            snode.append(0);
        helper(adj, sb, snode.toString(), 0, k);
        sb.append(snode);
        return sb.toString();
    }
    
    public void helper(LinkedList<Integer>[] adj, StringBuilder sb, String snode, int nnode, int k) {
        int size = adj[nnode].size(), scale = adj.length;
        int newnnode = snode.length() == 0 ? -1 : nnode * k - (snode.charAt(0) - '0') * scale;
        while (adj[nnode].size() != 0) {
            int next = adj[nnode].poll();
            helper(adj, sb, (snode + next).substring(1), newnnode == -1 ? 0 : newnnode + next, k);
            sb.append(next);
        }
    }
}


// loop from back to front .   
// it works, but I don't know why it works... inspired by others 
class Solution {
    public String crackSafe(int n, int k) {
        int count = 1;
        for (int i = 1; i < n; i++)
            count *= k;
        int[] map = new int[count];
        for (int i = 0; i < map.length; i++)
            map[i] = k-1;
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < n; i++)
            sb.append('0');
        int prefix = 0, index = 0, remain = count * k;
        while (remain-- > 0) {
            int digit = map[prefix]--;
            if (n != 1)
                prefix = (prefix * k - count * (sb.charAt(index++) - '0')) + digit;
            sb.append(digit);
        }
        return sb.toString();
    }
}


// according to https://en.wikipedia.org/wiki/De_Bruijn_sequence
class Solution {
    public String crackSafe(int n, int k) {
        int count = 1;
        for (int i = 1; i < n; i++)
            count *= k;
        int[] map = new int[count * k];
        for (int i = 0; i < k; i++) {
            for (int j = 0; j < count; j++)
                map[i * count + j] = j * k + i;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < map.length; i++) {
            int j = i;
            while (map[j] >= 0) {
                sb.append(j / count);
                int next = map[j];
                map[j] = -1;
                j = next;
            }
        }
        for (int i = 1; i < n; i++)
            sb.append('0');
        return sb.toString();
    }
}


// no need to pre calculate the indices 
// more efficient than the above solution 
class Solution {
    public String crackSafe(int n, int k) {
        int count = 1;
        for (int i = 1; i < n; i++)
            count *= k;
        boolean[] visited = new boolean[count * k];
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < visited.length; i++) {
            int j = i;
            while (!visited[j]) {
                sb.append(j / count);
                visited[j] = true;
                j = j % count * k + j / count ;
            }
        }
        for (int i = 1; i < n; i++)
            sb.append('0');
        return sb.toString();
    }
}
