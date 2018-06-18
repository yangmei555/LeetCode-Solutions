class Solution {
    public boolean canIWin(int maxChoosableInteger, int desiredTotal) {
        if (desiredTotal == 0)
            return true;
        if (maxChoosableInteger*(maxChoosableInteger+1)/2 < desiredTotal)
            return false;
        boolean[] used = new boolean[maxChoosableInteger+1];
        Map<Integer, Boolean> map = new HashMap<>();
        return helper(used, desiredTotal, map);
    }
    
    public boolean helper(boolean[] used, int total, Map<Integer, Boolean> map) {
        if (total <= 0)
            return false;
        int hash = Arrays.hashCode(used);
        if (map.containsKey(hash))
            return map.get(hash);
        boolean res = false;
        for (int i = 1; i < used.length; i++) {
            if (!used[i]) {
                used[i] = true;
                res = helper(used, total - i, map);
                used[i] = false;
                if (!res) {
                    map.put(hash, true);
                    return true;
                }
            }
        }
        map.put(hash, false);
        return false;
    }
}


class Solution {
    public boolean canIWin(int maxChoosableInteger, int desiredTotal) {
        if (desiredTotal == 0)
            return true;
        if (maxChoosableInteger*(maxChoosableInteger+1)/2 < desiredTotal)
            return false;
        else if (maxChoosableInteger*(maxChoosableInteger+1)/2 == desiredTotal)
            return maxChoosableInteger % 2 == 1;
        int used = 0;
        Boolean[] map = new Boolean[1 << maxChoosableInteger];
        return helper(used, maxChoosableInteger, desiredTotal, map);
    }
    
    public boolean helper(int used, int max, int total, Boolean[] map) {
        if (total <= 0)
            return false;
        if (map[used] != null)
            return map[used];
        int index = 1;
        for (int i = 1; i <= max; i++) {
            if ((used & index) == 0) {
                if (!helper(used | index, max, total - i, map)) {
                    map[used] = true;
                    return true;
                }
            }
            index <<= 1;
        }
        map[used] = false;
        return false;
    }
}
