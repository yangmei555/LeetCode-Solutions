class Solution {
    
    Random random;
    int[] blacklist;
    int N;
    public Solution(int N, int[] blacklist) {
        random = new Random();
        this.blacklist = blacklist;
        Arrays.sort(blacklist);
        this.N = N;
    }
    
    public int pick() {
        int get = random.nextInt(N - blacklist.length);
        int left = 0, right = blacklist.length;
        while (left < right) {
            int mid = (left + right) / 2;
            if (blacklist[mid] - mid >= get + 1)
                right = mid;
            else
                left = mid + 1;
        } 
        // int diff = (left == blacklist.length ? N : blacklist[left]) - left - get - 1;
        // return (left == blacklist.length ? N : blacklist[left]) - diff - 1;
        return get + left;
    }
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(N, blacklist);
 * int param_1 = obj.pick();
 */


class Solution {
    
    Random random;
    int len;
    Map<Integer, Integer> map = new HashMap<>();
    public Solution(int N, int[] blacklist) {
        random = new Random();
        len = N - blacklist.length;
        Set<Integer> backblack = new HashSet<>();
        for (int i = len; i < N; i++)
            backblack.add(i);
        for (int b : blacklist) {
            if (b >= len)
                backblack.remove(b);
        }
        Iterator<Integer> itr = backblack.iterator();
        for (int b : blacklist) {
            if (b < len)
                map.put(b, itr.next());
        }
    }
    
    public int pick() {
        int key = random.nextInt(len);
        return map.getOrDefault(key, key);
    }
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(N, blacklist);
 * int param_1 = obj.pick();
 */
