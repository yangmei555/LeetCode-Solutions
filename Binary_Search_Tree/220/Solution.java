class Solution {
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        TreeMap<Long, Integer> map = new TreeMap<>();
        for (int i = 0; i < nums.length; i++) {
            Long key1 = map.floorKey(0L + nums[i] + t);
            Long key2 = map.ceilingKey(0L + nums[i] - t);
            if (key1 != null && key2 != null && key2 <= key1)
                return true;
            map.put(0L + nums[i], i);
            if (i >= k && map.get(0L + nums[i-k]) == i-k)
                map.remove(0L + nums[i-k]);
        }
        return false;
    }
}


// a more concise way to use red black tree 
class Solution {
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        TreeSet<Integer> set = new TreeSet<>();
        for (int i = 0; i < nums.length; i++) {
            Integer key1 = set.ceiling(nums[i]);
            if (key1 != null && key1 <= nums[i] + t)
                return true;
            Integer key2 = set.floor(nums[i]);
            // if use "key2 >= nums[i] - t" will cause integer overflow 
            if (key2 != null && key2 + t >= nums[i]) 
                return true;
            set.add(nums[i]);
            if (set.size() > k)    // this line can be written as "if (i >= k)"
                set.remove(nums[i-k]);
        }
        return false;
    }
}


// binary indexed tree solution 
class Solution {
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        int[] record = nums.clone();
        Arrays.sort(record);
        int[] tree = new int[nums.length+1];
        int[] rank = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            int index1 = binarySearch(record, 0L + nums[i] + t + 1);
            int index2 = binarySearch(record, 0L + nums[i] - t);
            int sum1 = 0, sum2 = 0;
            for (int j = index1; j > 0; j &= j-1)
                sum1 += tree[j];
            for (int j = index2; j > 0; j &= j-1)
                sum2 += tree[j];
            if (sum1 - sum2 > 0)
                return true;
            rank[i] = binarySearch(record, nums[i]);
            for (int j = rank[i]+1; j < tree.length; j += j&-j)
                tree[j]++;
            if (i >= k) {
                for (int j = rank[i-k]+1; j < tree.length; j += j&-j)
                    tree[j]--;
            }
        }
        return false;
    }
    
    public int binarySearch(int[] record, long val) {
        int left = 0, right = record.length;
        while (left < right) {
            int mid = (left + right) / 2;
            if (record[mid] < val)
                left = mid + 1;
            else
                right = mid;
        }
        return left;
    }
}


// use bucket to classify the values. O(1) solution 
class Solution {
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        if (t < 0)
            return false;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int id = nums[i] >= 0 ? nums[i] / (t + 1) : (nums[i] + 1) / (t + 1) - 1;
            if (map.containsKey(id))
                return true;
            if (map.containsKey(id-1) && map.get(id-1) + t >= nums[i])
                return true;
            if (map.containsKey(id+1) && map.get(id+1) <= nums[i] + t)
                return true;
            map.put(id, nums[i]);
            if (i >= k) {
                id = nums[i-k] >= 0 ? nums[i-k] / (t + 1) : (nums[i-k] + 1) / (t + 1) - 1;
                map.remove(id);
            }
        }
        return false;
    }
}
