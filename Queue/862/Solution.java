class Solution {
    public int shortestSubarray(int[] A, int K) {
        int[] prefix = new int[A.length+1];
        for (int i = 0; i < A.length; i++)
            prefix[i+1] = prefix[i] + A[i];
        Deque<Integer> queue = new LinkedList<>();
        int res = Integer.MAX_VALUE;
        for (int j = 0; j < prefix.length; j++) {
            while (!queue.isEmpty() && prefix[queue.getLast()] >= prefix[j])
                queue.pollLast();
            while (!queue.isEmpty() && prefix[j] - prefix[queue.getFirst()] >= K)
                res = Math.min(res, j - queue.pollFirst());
            queue.offerLast(j);
        }
        return res == Integer.MAX_VALUE ? -1 : res;
    }
}


// use array to implement Deque, gain high performance 
// if K is allowed to be negative, the two while loops in the for loop should exchange 
class Solution {
    public int shortestSubarray(int[] A, int K) {
        int[] prefix = new int[A.length+1];
        for (int i = 0; i < A.length; i++)
            prefix[i+1] = prefix[i] + A[i];
        int[] queue = new int[prefix.length];
        int p1 = 0, p2 = 0;
        int res = Integer.MAX_VALUE;
        for (int j = 0; j < prefix.length; j++) {
            while (p1 != p2 && prefix[queue[p2-1]] >= prefix[j])
                p2--;
            while (p1 != p2 && prefix[j] - prefix[queue[p1]] >= K)
                res = Math.min(res, j - queue[p1++]);
            queue[p2++] = j;
        }
        return res == Integer.MAX_VALUE ? -1 : res;
    }
}


// use priority queue 
class Solution {
    public int shortestSubarray(int[] A, int K) {
        Queue<int[]> queue = new PriorityQueue<>(new Comparator<int[]>() {
            public int compare(int[] i1, int[] i2) {
                return i1[0] - i2[0];
            }
        });
        queue.offer(new int[]{0, -1});
        int res = Integer.MAX_VALUE, sum = 0;
        for (int i = 0; i < A.length; i++) {
            sum += A[i];
            while (!queue.isEmpty() && sum - queue.peek()[0] >= K)
                res = Math.min(res, i - queue.poll()[1]);
            queue.offer(new int[]{sum, i});
        }
        return res == Integer.MAX_VALUE ? -1 : res;
    }
}


// use tree map 
class Solution {
    public int shortestSubarray(int[] A, int K) {
        TreeMap<Integer, Integer> map = new TreeMap<>();
        int sum = 0, res = Integer.MAX_VALUE;
        map.put(0, -1);
        for (int i = 0; i < A.length; i++) {
            sum += A[i];
            Integer key = map.floorKey(sum - K);
            while (key != null) {
                res = Integer.min(res, i - map.get(key));
                map.remove(key);
                key = map.floorKey(key);
            }
            map.put(sum, i);
        }
        return res == Integer.MAX_VALUE ? -1 : res;
    }
}


// binary search 
class Solution {
    public int shortestSubarray(int[] A, int K) {
        int[] prefix = new int[A.length+1];
        for (int i = 0; i < A.length; i++)
            prefix[i+1] = prefix[i] + A[i];
        int left = 1, right = A.length+1;
        while (left < right) {
            int mid = (left + right) / 2;
            if (helper(prefix, K, mid))
                right = mid;
            else
                left = mid + 1;
        }
        return left == A.length + 1 ? -1 : left;
    }
    
    public boolean helper(int[] prefix, int K, int len) {
        int[] deque = new int[prefix.length];
        int p1 = 0, p2 = 0, max = Integer.MIN_VALUE;
        deque[p2++] = 0;
        for (int i = 1; i < prefix.length; i++) {
            max = Math.max(max, prefix[i] - prefix[deque[p1]]);
            if (max >= K)
                return true;
            while (p1 != p2 && prefix[deque[p2-1]] >= prefix[i])
                p2--;
            deque[p2++] = i;
            if (deque[p1] == i - len)
                p1++;
        }
        return max >= K;
    }
}
