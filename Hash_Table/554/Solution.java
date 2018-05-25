class Solution {
    public int leastBricks(List<List<Integer>> wall) {
        Map<Integer, Integer> map = new HashMap<>();
        int max = 0;
        for (List<Integer> list : wall) {
            int len = 0;
            for (int i = 0; i < list.size() - 1; i++) {
                len += list.get(i);
                int freq = map.containsKey(len) ? map.get(len) : 0;
                map.put(len, freq + 1);
                max = max > freq + 1 ? max : freq + 1;
            }
        }
        return wall.size() - max;
    }
}
