// very slow solution 
class Solution {
    public int minStickers(String[] stickers, String target) {
        int[][] counts = new int[stickers.length][26];
        boolean[] exists = new boolean[26];
        for (int i = 0; i < stickers.length; i++) {
            for (char c : stickers[i].toCharArray()) {
                counts[i][c-'a']++;
                exists[c-'a'] = true;
            }
        }
        int[] tar = new int[26];
        for (char c : target.toCharArray())
            tar[c-'a']++;
        for (int i = 0; i < 26; i++) {
            if (tar[i] != 0 && !exists[i])
                return -1;
        }
        Map<String, Integer> memo = new HashMap<>();
        int ret = helper(tar, counts, memo);
        return ret == Integer.MAX_VALUE ? -1 : ret;
    }
    
    public int helper(int[] tar, int[][] counts, Map<String, Integer> memo) {
        boolean flag = true;
        for (int t : tar) {
            if (t != 0) {
                flag = false;
                break;
            }
        }
        if (flag)
            return 0;
        String key = Arrays.toString(tar);
        if (memo.containsKey(key))
            return memo.get(key);
        int res = Integer.MAX_VALUE;
        for (int[] count : counts) {
            flag = false;
            int[] copy = tar.clone();
            for (int i = 0; i < 26; i++) {
                if (copy[i] != 0 && count[i] != 0) {
                    flag = true;
                    copy[i] = Math.max(0, copy[i] - count[i]);
                }
            }
            if (flag) {
                res = Math.min(res, helper(copy, counts, memo));
            }
        }
        if (res != Integer.MAX_VALUE)
            res++;
        memo.put(key, res);
        return res;
    }
}
