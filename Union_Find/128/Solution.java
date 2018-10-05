class Solution {
    int[] id, rank, size;
    public int longestConsecutive(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        id = new int[nums.length];
        rank = new int[nums.length];
        size = new int[nums.length];
        for (int i = 0; i < id.length; i++) {
            id[i] = i;
            size[i] = 1;
        }
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            if (!map.containsKey(nums[i])) {
                if (map.containsKey(nums[i]-1)) 
                    union(i, map.get(nums[i]-1));
                if (map.containsKey(nums[i]+1))
                    union(i, map.get(nums[i]+1));
                res = Math.max(res, size[find(i)]);
                map.put(nums[i], i);
            }
        }
        return res;
    }
    
    public void union(int i, int j) {
        int rep1 = find(i), rep2 = find(j);
        if (rep1 != rep2) {
            if (rank[rep1] > rank[rep2]) {
                id[rep2] = rep1;
                size[rep1] += size[rep2];
            } else if (rank[rep1] < rank[rep2]) {
                id[rep1] = rep2;
                size[rep2] += size[rep1];
            } else {
                id[rep1] = rep2;
                size[rep2] += size[rep1];
                rank[rep2]++;
            }
        }
    }
    
    public int find(int i) {
        while (i != id[i]) {
            id[i] = id[id[i]];
            i = id[i];
        }
        return i;
    }
}
