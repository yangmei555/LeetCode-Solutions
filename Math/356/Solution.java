class Solution {
    public boolean isReflected(int[][] points) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int[] p : points) {
            map.putIfAbsent(p[1], new ArrayList<>());
            if (!map.get(p[1]).contains(p[0]))
                map.get(p[1]).add(p[0]);
        }
        Double x = null;
        for (int y : map.keySet()) {
            List<Integer> list = map.get(y);
            // if (y == 8)
            //     System.out.println(list);
            Collections.sort(list);
            Double value = null;
            int left = 0, right = list.size()-1;
            while (left <= right) {
                if (value == null) {
                    value = (list.get(left)+list.get(right)) / 2.0;
                } else {
                    if (Math.abs((list.get(left)+list.get(right)) / 2.0 - value) > 1e-6) 
                        return false;
                }
                left++;
                right--;
            }
            if (x == null) {
                x = value;
            } else {
                if (Math.abs(x - value) > 1e-6) 
                    return false;
            }
        }
        return true;
    }
}


class Solution {
    public boolean isReflected(int[][] points) {
        Map<Integer, Set<Integer>> map = new HashMap<>();
        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        for (int[] p : points) {
            map.putIfAbsent(p[1], new HashSet<>());
            map.get(p[1]).add(p[0]);
            min = min < p[0] ? min : p[0];
            max = max > p[0] ? max : p[0];
        }
        int x = min + max;
        for (int y : map.keySet()) {
            Set<Integer> list = map.get(y);
            for (int l : list)
                if (!list.contains(x - l))
                    return false;
        }
        return true;
    }
}


class Solution {
    public boolean isReflected(int[][] points) {
        Set<Integer> set = new HashSet<>();
        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        for (int[] p : points) {
            set.add(Arrays.hashCode(p));
            min = min < p[0] ? min : p[0];
            max = max > p[0] ? max : p[0];
        }
        int x = min + max;
        for (int[] p : points) {
            int[] point = new int[]{x - p[0], p[1]};
            if (!set.contains(Arrays.hashCode(point)))
                return false;
        }
        return true;
    }
}
