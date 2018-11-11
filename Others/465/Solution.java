class Solution {
    public int minTransfers(int[][] transactions) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int[] t : transactions) {
            map.put(t[0], map.getOrDefault(t[0], 0) - t[2]);
            map.put(t[1], map.getOrDefault(t[1], 0) + t[2]);
            if (map.get(t[0]) == 0)
                map.remove(t[0]);
            if (map.get(t[1]) == 0)
                map.remove(t[1]);
        }
        int positive = 0;
        for (int k : map.keySet()) {
            if (map.get(k) > 0)
                positive++;
        }
        int[] people = new int[positive], bucket = new int[map.size()-positive];
        int index1 = 0, index2 = 0;
        for (int k : map.keySet()) {
            int v = map.get(k);
            if (v > 0)
                people[index1++] = v;
            else
                bucket[index2++] = -v;
        }
        return helper(people, bucket, 0);
    }
    
    public int helper(int[] people, int[] bucket, int index) {
        if (index == bucket.length) {
            return 0;
        } else {
            int res = Integer.MAX_VALUE;
            for (int i = 0; i < people.length; i++) {
                if (people[i] != 0) {
                    if (people[i] >= bucket[index]) {
                        people[i] -= bucket[index];
                        res = Math.min(res, 1 + helper(people, bucket, index+1));
                        people[i] += bucket[index];
                    } else {
                        int temp = people[i];
                        bucket[index] -= people[i];
                        people[i] = 0;
                        res = Math.min(res, 1 + helper(people, bucket, index));
                        people[i] = temp;
                        bucket[index] += people[i];
                    }
                }
            }
            return res;
        }
    }
}


// +k offsets -k, much faster than the above solution 
// combine the preprocess part of this solution and the usage of int[] in the above solution 
// will beat 100% 
class Solution {
    public int minTransfers(int[][] transactions) {
        Map<Integer, Integer> map = new HashMap<>(), count = new HashMap<>();
        for (int[] t : transactions) {
            map.put(t[0], map.getOrDefault(t[0], 0) - t[2]);
            map.put(t[1], map.getOrDefault(t[1], 0) + t[2]);
        }
        for (int k : map.keySet()) {
            int v = map.get(k);
            if (v != 0) 
                count.put(v, count.getOrDefault(v, 0) + 1);
        }
        int res = 0;
        for (int k : count.keySet()) {
            if (k > 0 && count.containsKey(-k)) {
                int min = Math.min(count.get(k), count.get(-k));
                count.put(k, count.get(k) - min);
                count.put(-k, count.get(-k) - min);
                res += min;
            }
        }
        List<Integer> people = new ArrayList<>(), bucket = new ArrayList<>();
        for (int k : count.keySet()) {
            int v = count.get(k);
            if (k > 0) {
                for (int i = 0; i < v; i++)
                    people.add(k);
            } else {
                for (int i = 0; i < v; i++)
                    bucket.add(-k);
            }
        }
        return res + helper(people, bucket, 0);
    }
    
    public int helper(List<Integer> people, List<Integer> bucket, int index) {
        if (index == bucket.size()) {
            return 0;
        } else {
            int res = Integer.MAX_VALUE;
            for (int i = 0; i < people.size(); i++) {
                if (people.get(i) != 0) {
                    if (people.get(i) >= bucket.get(index)) {
                        people.set(i, people.get(i) - bucket.get(index));
                        res = Math.min(res, 1 + helper(people, bucket, index+1));
                        people.set(i, people.get(i) + bucket.get(index));
                    } else {
                        int temp = people.get(i);
                        bucket.set(index, bucket.get(index) - people.get(i));
                        people.remove(i);
                        res = Math.min(res, 1 + helper(people, bucket, index));
                        people.add(i, temp);
                        bucket.set(index, bucket.get(index) + people.get(i));
                    }
                }
            }
            return res;
        }
    }
}


// another style of DFS searching, find each counter balance behind the current person and try 
class Solution {
    public int minTransfers(int[][] transactions) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int[] t : transactions) {
            map.put(t[0], map.getOrDefault(t[0], 0) - t[2]);
            map.put(t[1], map.getOrDefault(t[1], 0) + t[2]);
            if (map.get(t[0]) == 0)
                map.remove(t[0]);
            if (map.get(t[1]) == 0)
                map.remove(t[1]);
        }
        int[] balance = new int[map.size()];
        int i = 0;
        for (int k : map.keySet()) 
            balance[i++] = map.get(k);
        return helper(balance, 0);
    }
    
    public int helper(int[] balance, int index) {
        while (index < balance.length && balance[index] == 0)
            index++;
        if (index == balance.length)
            return 0;
        int res = Integer.MAX_VALUE;
        for (int i = index+1; i < balance.length; i++) {
            if (balance[index] * balance[i] < 0) {
                balance[i] += balance[index];
                res = Math.min(res, 1 + helper(balance, index+1));
                balance[i] -= balance[index];
            }
        }
        return res;
    }
}
