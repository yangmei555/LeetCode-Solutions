// a verbose solution 
class Solution {
    public int flipgame(int[] fronts, int[] backs) {
        Integer[] indices = new Integer[fronts.length * 2];
        for (int i = 0; i < indices.length; i++)
            indices[i] = i;
        Arrays.sort(indices, new Comparator<Integer>() {
            public int compare(Integer i1, Integer i2) {
                int v1 = i1 < fronts.length ? fronts[i1] : backs[i1 - fronts.length];
                int v2 = i2 < fronts.length ? fronts[i2] : backs[i2 - fronts.length];
                return v1 - v2;
            }
        });
        int index = 0;
        while (index < indices.length) {
            int start = index;
            int val = indices[index] < fronts.length ? fronts[indices[index]] : 
                                                backs[indices[index] - fronts.length];
            boolean[] visited = new boolean[fronts.length];
            boolean flag = false;
            while (index < indices.length) {
                int v = indices[index] < fronts.length ? fronts[indices[index]] : 
                                                backs[indices[index] - fronts.length];
                if (val == v) {
                    if (!visited[indices[index] % fronts.length])
                        visited[indices[index] % fronts.length] = true;
                    else
                        flag = true;
                } else {
                    break;
                }
                index++;
            }
            if (!flag)
                return val;
        }
        return 0;
    }
}


class Solution {
    public int flipgame(int[] fronts, int[] backs) {
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < fronts.length; i++) {
            max = Math.max(max, fronts[i]);
            max = Math.max(max, backs[i]);
        }
        int[] states = new int[max+1];
        for (int i = 0; i < fronts.length; i++) {
            if (fronts[i] == backs[i]) {
                states[fronts[i]] = 2;
            } else {
                if (states[fronts[i]] == 0)
                    states[fronts[i]] = 1;
                if (states[backs[i]] == 0)
                    states[backs[i]] = 1;
            }
        }
        for (int i = 1; i < states.length; i++) {
            if (states[i] == 1)
                return i;
        }
        return 0;
    }
}


class Solution {
    public int flipgame(int[] fronts, int[] backs) {
        Set<Integer> banned = new HashSet<>();
        for (int i = 0; i < fronts.length; i++) {
            if (fronts[i] == backs[i])
                banned.add(fronts[i]);
        }
        int res = Integer.MAX_VALUE;
        for (int i = 0; i < fronts.length; i++) {
            if (!banned.contains(fronts[i]))
                res = Math.min(res, fronts[i]);
            if (!banned.contains(backs[i]))
                res = Math.min(res, backs[i]);
        }
        return res == Integer.MAX_VALUE ? 0 : res;
    } 
}
