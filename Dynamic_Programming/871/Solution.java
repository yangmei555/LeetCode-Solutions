// a verbose as well as strange as well as unclear method 
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


// maxDist[i] : the max reachable distance with i stops 
class Solution {
    public int minRefuelStops(int target, int startFuel, int[][] stations) {
        if (startFuel >= target)
            return 0;
        long[] maxDist = new long[stations.length+1];
        maxDist[0] = startFuel;
        int res = Integer.MAX_VALUE;
        for (int i = 0; i < stations.length; i++) {
            for (int j = i; j >= 0; j--) {
                if (maxDist[j] >= stations[i][0]) {
                    maxDist[j+1] = Math.max(maxDist[j+1], maxDist[j] + stations[i][1]);
                    if (maxDist[j+1] >= target)
                        res = Math.min(res, j+1);
                } else {
                    break;
                }
            }
        }
        return res == Integer.MAX_VALUE ? -1 : res;
    }
}


// priority queue, O(N * log N) 
class Solution {
    public int minRefuelStops(int target, int startFuel, int[][] stations) {
        Queue<Integer> queue = new PriorityQueue<>();
        int fuel = startFuel, last = 0, res = 0;
        for (int i = 0; i <= stations.length; i++) {
            fuel -= (i == stations.length ? target : stations[i][0]) - last;
            while (!queue.isEmpty() && fuel < 0) {
                fuel -= queue.poll();
                res++;
            }
            if (fuel < 0)
                return -1;
            if (i != stations.length) {
                queue.offer(-stations[i][1]);
                last = stations[i][0];
            }
        }
        return res;
    }
}
