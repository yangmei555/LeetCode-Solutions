class Solution {
    public int openLock(String[] deadends, String target) {
        boolean[] dead = new boolean[10000];
        for (String str : deadends) {
            int n = 0;
            for (char c : str.toCharArray()) 
                n = n * 10 + c - '0';
            dead[n] = true;
        }
        int dest = 0;
        for (char c : target.toCharArray())
            dest = dest * 10 + c - '0';
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[10000];
        if (!dead[0]) {
            queue.offer(0);
            visited[0] = true;
        }
        int count = 0;
        while (!queue.isEmpty()) {
            for (int i = queue.size(); i > 0; i--) {
                int node = queue.poll();
                for (int b = 1; b < 10000; b *= 10) {
                    int digit = node / b % 10;
                    int up = node - digit * b + ((digit + 1) % 10) * b;
                    if (up == dest)
                        return count + 1;
                    if (!dead[up] && !visited[up]) {
                        visited[up] = true;
                        queue.offer(up);
                    }
                    int down = node - digit * b + ((digit + 9) % 10) * b;
                    if (down == dest)
                        return count + 1;
                    if (!dead[down] && !visited[down]) {
                        visited[down] = true;
                        queue.offer(down);
                    }
                }
            }
            count++;
        }
        return -1;
    }
}
