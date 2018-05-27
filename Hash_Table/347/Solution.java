class Solution {
    public List<Integer> topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int n : nums)
            map.put(n, map.getOrDefault(n, 0) + 1);
        List<Integer>[] lists = new List[nums.length+1];
        for (int i : map.keySet()) {
            int freq = map.get(i);
            if (lists[freq] == null)
                lists[freq] = new ArrayList<>();
            lists[freq].add(i);
        }
        List<Integer> res = new LinkedList<>();
        for (int i = lists.length-1; i >= 0; i--) {
            if (lists[i] != null) {
                if (k > lists[i].size()) {
                    res.addAll(lists[i]);
                    k -= lists[i].size();
                } else {
                    for (int j = 0; j < k; j++)
                        res.add(lists[i].get(j));
                    break;
                }
            }
        }
        return res;
    }
}


class Solution {
    public List<Integer> topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int n : nums)
            map.put(n, map.getOrDefault(n, 0) + 1);
        PriorityQueue<Map.Entry<Integer, Integer>> queue = new PriorityQueue<>(
            new Comparator<Map.Entry<Integer, Integer>>() {
            public int compare(Map.Entry<Integer, Integer> e1, Map.Entry<Integer, Integer> e2) {
                return e1.getValue() - e2.getValue();
            }
        });
        for (Map.Entry<Integer, Integer> e : map.entrySet()) {
            queue.offer(e);
            if (queue.size() > k)
                queue.poll();
        }
        List<Integer> res = new LinkedList<>();
        while (!queue.isEmpty()) {
            res.add(queue.poll().getKey());
        }
        return res;
    }
}


class Solution {
    public List<Integer> topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int n : nums)
            map.put(n, map.getOrDefault(n, 0) + 1);
        Map<Integer, List<Integer>> tmap = new TreeMap<>(new Comparator<Integer>() {
            public int compare(Integer i1, Integer i2) {
                return i2 - i1;
            }
        });
        for (int i : map.keySet()) {
            int freq = map.get(i);
            tmap.putIfAbsent(freq, new ArrayList<>());
            tmap.get(freq).add(i);
        }
        List<Integer> res = new LinkedList<>();
        for (int i : tmap.keySet()) {
            List<Integer> list = tmap.get(i);
            if (list.size() < k) {
                res.addAll(list);
                k -= list.size();
            } else {
                for (int j = 0; j < k; j++)
                    res.add(list.get(j));
                break;
            }
        }
        return res;
    }
}
