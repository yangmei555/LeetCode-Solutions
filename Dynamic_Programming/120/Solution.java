class Solution {
    public int minimumTotal(List<List<Integer>> triangle) {
        int[] paths = new int[triangle.size()];
        for (List<Integer> list : triangle) {
            int len = list.size(), temp1 = Integer.MAX_VALUE, temp2 = 0;
            for (int i = 0; i < len; i++) {
                if (i == len - 1) {
                    paths[i] = (temp1 == Integer.MAX_VALUE ? 0 : temp1) + list.get(i);
                } else {
                    temp2 = paths[i];
                    paths[i] = list.get(i) + (paths[i] < temp1 ? paths[i] : temp1);
                    temp1 = temp2;
                }
            }
        }
        int res = Integer.MAX_VALUE;
        for (int i : paths)
            if (i < res)
                res = i;
        return res;
    }
}


class Solution {
    public int minimumTotal(List<List<Integer>> triangle) {
        int[] paths = new int[triangle.size() + 1];
        for (int j = triangle.size() - 1; j >= 0; j--) {
            List<Integer> list = triangle.get(j);
            int len = list.size();
            for (int i = 0; i < len; i++) {
                paths[i] = list.get(i) + (paths[i] < paths[i+1] ? paths[i] : paths[i+1]); 
            }
        }
        return paths[0];
    }
}
