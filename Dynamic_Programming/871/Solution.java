class Solution {
    public int minRefuelStops(int target, int startFuel, int[][] stations) {
        Queue<Node> queue = new PriorityQueue<>(new Comparator<Node>() {
            public int compare(Node n1, Node n2) {
                return n2.dist - n1.dist;
            }
        });
        queue.offer(new Node(startFuel, -1, 0));
        int[] dist = new int[stations.length];
        while (!queue.isEmpty()) {
            List<Node> temp = new LinkedList<>();
            while (!queue.isEmpty()) {
                Node node = queue.poll();
                if (node.dist >= target)
                    return node.stop;
                int index = helper(stations, node.dist);
                for (int i = index; i >= node.station + 1; i--) {
                    if (node.dist + stations[i][1] > dist[i]) {
                        dist[i] = node.dist + stations[i][1];
                        temp.add(new Node(node.dist + stations[i][1], i, node.stop + 1));
                    }
                }
            }
            for (Node node : temp)
                queue.offer(node);
        }
        return -1;
    }
    
    public int helper(int[][] stations, int dist) {
        int left = 0, right = stations.length;
        while (left < right) {
            int mid = (left + right) / 2;
            if (stations[mid][0] > dist)
                right = mid;
            else
                left = mid + 1;
        }
        return left - 1;
    }
    
    class Node {
        int dist, station, stop;
        public Node (int dist, int station, int stop) {
            this.dist = dist;
            this.station = station;
            this.stop = stop;
        }
    }
}
