class Solution {
    public boolean canCross(int[] stones) {
        for (int i = 1; i < stones.length; i++) {
            if (stones[i-1] + i < stones[i])
                return false;
        }
        boolean[][] memo = new boolean[stones.length][stones.length];
        return helper(stones, 0, 0, memo);
    }
    
    public boolean helper(int[] stones, int index, int step, boolean[][] memo) {
        if (index == stones.length-1)
            return true;
        if (memo[index][step])
            return false;
        for (int i = index+1; i < stones.length; i++) {
            if (stones[i] - stones[index] > step + 1)
                break;
            if (stones[i] - stones[index] == step-1 && helper(stones, i, step-1, memo)) 
                return true;
            if (stones[i] - stones[index] == step && helper(stones, i, step, memo)) 
                return true;
            if (stones[i] - stones[index] == step+1 && helper(stones, i, step+1, memo)) 
                return true;
        }
        memo[index][step] = true;
        return false;
    }
}


class Solution {
    public boolean canCross(int[] stones) {
        for (int i = 1; i < stones.length; i++) {
            if (stones[i-1] + i < stones[i])
                return false;
        }
        boolean[][] memo = new boolean[stones.length][stones.length];
        return helper(stones, 0, 0, memo);
    }
    
    public boolean helper(int[] stones, int index, int step, boolean[][] memo) {
        if (index == stones.length-1)
            return true;
        if (memo[index][step])
            return false;
        int i = Arrays.binarySearch(stones, index+1, stones.length, stones[index]+step-1);
        if (i >= 0 && helper(stones, i, step-1, memo)) 
            return true;
        i = Arrays.binarySearch(stones, index+1, stones.length, stones[index]+step);
        if (i >= 0 && helper(stones, i, step, memo)) 
            return true;
        i = Arrays.binarySearch(stones, index+1, stones.length, stones[index]+step+1);
        if (i >= 0 && helper(stones, i, step+1, memo)) 
            return true;
        memo[index][step] = true;
        return false;
    }
}


class Solution {
    public boolean canCross(int[] stones) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < stones.length; i++)
            map.put(stones[i], i);
        boolean[][] memo = new boolean[stones.length][stones.length];
        return helper(stones, 0, 0, memo, map);
    }
    
    public boolean helper(int[] stones, int index, int step, boolean[][] memo, 
                            Map<Integer, Integer> map) {
        if (index == stones.length-1)
            return true;
        if (memo[index][step])
            return false;
        if (step > 1 && map.containsKey(stones[index]+step-1) && 
                        helper(stones, map.get(stones[index]+step-1), step-1, memo, map)) 
            return true;
        if (step > 0 && map.containsKey(stones[index]+step) && 
                        helper(stones, map.get(stones[index]+step), step, memo, map)) 
            return true;
        if (map.containsKey(stones[index]+step+1) && 
                        helper(stones, map.get(stones[index]+step+1), step+1, memo, map)) 
            return true;
        memo[index][step] = true;
        return false;
    }
}


class Solution {
    public boolean canCross(int[] stones) {
        Map<Integer, HashSet<Integer>> map = new HashMap<>();
        map.put(0, new HashSet<>());
        map.get(0).add(0);
        for (int i = 1; i < stones.length; i++) {
            map.put(i, new HashSet<>());
            for (int j = 0; j < i; j++) {
                HashSet<Integer> set = map.get(j);
                int dist = stones[i] - stones[j];
                if (set.contains(dist-1) || set.contains(dist) || set.contains(dist+1))
                    map.get(i).add(dist);
            }
        }
        return !map.get(stones.length-1).isEmpty();
    }
}


class Solution {
    public boolean canCross(int[] stones) {
        Map<Integer, HashSet<Integer>> map = new HashMap<>();
        for (int s : stones)
            map.put(s, new HashSet<Integer>());
        map.get(stones[0]).add(0);
        for (int i = 0; i < stones.length; i++) {
            for (int step : map.get(stones[i])) {
                if (step > 1 && map.containsKey(stones[i]+step-1))
                    map.get(stones[i]+step-1).add(step-1);
                if (step > 0 && map.containsKey(stones[i]+step))
                    map.get(stones[i]+step).add(step);
                if (map.containsKey(stones[i]+step+1))
                    map.get(stones[i]+step+1).add(step+1);
            }
        }
        return !map.get(stones[stones.length-1]).isEmpty();
    }
}
