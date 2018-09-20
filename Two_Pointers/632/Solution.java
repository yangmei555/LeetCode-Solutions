class Solution {
    public int[] smallestRange(List<List<Integer>> nums) {
        int total = 0;
        for (List<Integer> list : nums)
            total += list.size();
        int[][] nodes = new int[total][];
        int index = 0;
        for (int i = 0; i < nums.size(); i++) {
            List<Integer> list = nums.get(i);
            for (int l : list)
                nodes[index++] = new int[]{l, i};
        }
        Arrays.sort(nodes, new Comparator<int[]>() {
            public int compare(int[] i1, int[] i2) {
                return i1[0] - i2[0];
            }
        });
        int[] map = new int[nums.size()];
        int count = map.length;
        int[] res = null;
        for (int i = 0, j = 0; j < nodes.length; j++) {
            if (map[nodes[j][1]]++ == 0)
                count--;
            if (count == 0) {
                while (--map[nodes[i][1]] != 0)
                    i++;
                if (res == null || nodes[j][0] - nodes[i][0] < res[1] - res[0])
                    res = new int[]{nodes[i][0], nodes[j][0]};
                i++;
                count++;
            }
        }
        return res;
    }
}
