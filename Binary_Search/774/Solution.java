// notice the setting of the initial left and right boundary 
class Solution {
    public double minmaxGasDist(int[] stations, int K) {
        double left = (stations[stations.length-1] - stations[0] + .0) / (stations.length + K - 1);
        double right = (stations[stations.length-1] - stations[0] + .0) / (K + 1);
        while (Math.abs(left-right) >= 1e-6) {
            double mid = (left + right) / 2;
            int total = 0;
            for (int i = 0; i < stations.length-1; i++) {
                total += Math.ceil((stations[i+1] - stations[i]) / mid) - 1;
                if (total > K)
                    break;
            }
            if (total > K)
                left = mid;
            else
                right = mid;
        }
        return left;
    }
}


// use priority queue, but keep adding as long as the dist is above the upper bound 
class Solution {
    public double minmaxGasDist(int[] stations, int K) {
        Queue<int[]> queue = new PriorityQueue<>(new Comparator<int[]>() {
            public int compare(int[] i1, int[] i2) {
                return i2[0] * i1[1] - i1[0] * i2[1];
            }
        });
        double upper = (stations[stations.length-1] - stations[0] + .0) / (K + 1);
        for (int i = 0; i < stations.length-1; i++)
            queue.offer(new int[]{stations[i+1] - stations[i], 1});
        while (K > 0) {
            int[] node = queue.poll();
            node[1]++;
            K--;
            while (K > 0 && (node[0]+.0) / node[1] > upper) {
                node[1]++;
                K--;
            }
            queue.offer(node);
        }
        int[] node = queue.peek();
        return (node[0] + .0) / node[1];
    }
}


// another way of using priority queue , assign the K stations proportionally 
class Solution {
    public double minmaxGasDist(int[] stations, int K) {
        Queue<int[]> queue = new PriorityQueue<>(new Comparator<int[]>() {
            public int compare(int[] i1, int[] i2) {
                return i2[0] * i1[1] - i1[0] * i2[1];
            }
        });
        int span = stations[stations.length-1] - stations[0];
        int k = K;
        for (int i = 0; i < stations.length-1; i++) {
            int dist = stations[i+1] - stations[i];
            int quota = (int)(k * (dist + .0) / span);
            queue.offer(new int[]{dist, quota + 1});
            K -= quota;
        }
        while (K-- > 0) {
            int[] node = queue.poll();
            node[1]++;
            queue.offer(node);
        }
        int[] node = queue.peek();
        return (node[0] + .0) / node[1];
    }
}
