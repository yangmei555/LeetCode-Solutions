class Solution {
    public int leastInterval(char[] tasks, int n) {
        int[] map = new int[26];
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        for (int c : tasks) {
            queue.offer(map[c-'A']);
            map[c-'A'] += n + 1;
        }
        int res = 0;
        while (!queue.isEmpty()) {
            int t = queue.poll();
            res = res > t ? res : t;
            res++;
        }
        return res;
    }
}
