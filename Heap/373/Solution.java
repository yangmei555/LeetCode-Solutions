class Solution {
    public List<int[]> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        PriorityQueue<int[]> queue = new PriorityQueue<>(new Comparator<int[]>() {
            public int compare(int[] i1, int[] i2) {
                return i1[0] + i1[1] - i2[0] - i2[1];
            }
        });
        for (int i = 0; i < nums1.length; i++) {
            for (int j = 0; j < nums2.length; j++) {
                queue.offer(new int[]{nums1[i], nums2[j]});
            }
        }
        List<int[]> res = new LinkedList<>();
        for (int i = 0; i < k && !queue.isEmpty(); i++) 
            res.add(queue.poll());
        return res;
    }
}


class Solution {
    public List<int[]> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        List<int[]> res = new LinkedList<>();
        for (int i = 0; i < nums1.length; i++) {
            for (int j = 0; j < nums2.length; j++) {
                res.add(new int[]{nums1[i], nums2[j]});
            }
        }
        Collections.sort(res, new Comparator<int[]>() {
            public int compare(int[] i1, int[] i2) {
                return i1[0] + i1[1] - i2[0] - i2[1];
            }
        });
        return res.subList(0, k < res.size() ? k : res.size());
    }
}


class Solution {
    public List<int[]> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        Map<Integer, List<int[]>> map = new TreeMap<>();
        for (int i = 0; i < nums1.length; i++) {
            for (int j = 0; j < nums2.length; j++) {
                int[] temp = new int[]{nums1[i], nums2[j]};
                map.putIfAbsent(temp[0] + temp[1], new ArrayList<>());
                map.get(temp[0] + temp[1]).add(temp);
            }
        }
        List<int[]> res = new LinkedList<>();
        for (int key : map.keySet()) {
            if (map.get(key).size() < k) {
                res.addAll(map.get(key));
                k -= map.get(key).size();
            } else {
                for (int[] i : map.get(key)) {
                    res.add(i);
                    k--;
                    if (k == 0)
                        return res;
                }
            }
        }
        return res;
    }
}


class Solution {
    public List<int[]> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        List<int[]> res = new LinkedList<>();
        if (nums1.length == 0 || nums2.length == 0)
            return res;
        PriorityQueue<int[]> queue = new PriorityQueue<>(new Comparator<int[]>() {
            public int compare(int[] i1, int[] i2) {
                return i1[0] + i1[1] - i2[0] - i2[1];
            }
        });
        for (int i = 0; i < nums1.length; i++) 
            queue.offer(new int[]{nums1[i], nums2[0], 1});
        while (res.size() < k && !queue.isEmpty()) {
            int[] temp = queue.poll();
            res.add(new int[]{temp[0], temp[1]});
            if (temp[2] < nums2.length) {
                queue.offer(new int[]{temp[0], nums2[temp[2]], temp[2] + 1});
            }
        }
        return res;
    }
}
