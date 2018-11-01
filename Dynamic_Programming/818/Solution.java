// BFS , use position and speed to represent each state(node) 
class Solution {
    public int racecar(int target) {
        Set<String> set = new HashSet<>();
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{0, 1});
        set.add(0 + " " + 1);
        int step = 0;
        while (!queue.isEmpty()) {
            for (int s = queue.size(); s > 0; s--) {
                int[] node = queue.poll();
                if (node[0] == target)
                    return step;
                int[] next1 = new int[]{node[0] + node[1], node[1] * 2};
                String key1 = next1[0] + " " + next1[1];
                if (!set.contains(key1) && next1[0] > 0 && next1[0] < target * 2) {
                    set.add(key1);
                    queue.offer(next1);
                }
                int[] next2 = new int[]{node[0], node[1] > 0 ? -1 : 1};
                String key2 = next2[0] + " " + next2[1];
                if (!set.contains(key2) && next2[0] > 0 && next2[0] < target * 2) {
                    set.add(key2);
                    queue.offer(next2);
                }
            }
            step++;
        }
        return -1;
    }
}


// temp is the greatest power of 2 which is smaller or equal to i 
class Solution {
    public int racecar(int target) {
        int[] dp = new int[target + 1];
        for (int i = 1; i <= target; i++) {
            int temp = i;
            while ((temp & (temp-1)) != 0) {
                temp &= temp-1;
            }
            int step = dp[temp-1];
            if (temp * 2 - 1 == i) {
                dp[i] = step + 1;
                continue;
            }
            dp[i] = Integer.MAX_VALUE;
            // goes to temp-1, then goes back 
            for (int back = 0; (1 << back) - 1 < temp - 1; back++) {
                dp[i] = Math.min(dp[i], step + 1 + back + 1 + dp[i - temp + (1 << back)]);
            }
            // goes to temp*2-1, which pasts i, then goes back to i
            dp[i] = Math.min(dp[i], step + 1 + 1 + dp[2 * temp - 1 - i]);
        }
        return dp[target];
    }
}
