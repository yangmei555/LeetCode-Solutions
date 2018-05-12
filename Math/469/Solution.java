class Solution {
    public boolean isConvex(List<List<Integer>> points) {
        Boolean pos = null;
        int x1, y1, x2, y2;
        int size = points.size();
        for (int i = 0; i < size; i++) {
            List<Integer> l1 = points.get(i);
            List<Integer> l2 = i < size - 1 ? points.get(i+1) : points.get(0);
            List<Integer> l3 = i < size - 2 ? points.get(i+2) : points.get(i - size + 2);
            x1 = l2.get(0) - l1.get(0);
            y1 = l2.get(1) - l1.get(1);
            x2 = l3.get(0) - l2.get(0);
            y2 = l3.get(1) - l2.get(1);
            int z = x1 * y2 - x2 * y1;
            if (z == 0)
                continue;
            else if (z > 0) {
                if (pos == null)
                    pos = true;
                else if (!pos)
                    return false;
            } else {
                if (pos == null)
                    pos = false;
                if (pos)
                    return false;
            }
        }
        return true;
    }
}


class Solution {
    public boolean isConvex(List<List<Integer>> points) {
        int flag = 0;
        int size = points.size();
        for (int i = 0; i < size; i++) {
            int z = helper(points, i);
            if (z == 0)
                continue;
            if (flag == 0) {
                flag = z > 0 ? 1 : -1;
            } else if (flag * z < 0)
                return false;
        }
        return true;
    }
    
    public int helper(List<List<Integer>> points, int i) {
        int size = points.size();
        List<Integer> l1 = points.get(i%size);
        List<Integer> l2 = points.get((i+1)%size);
        List<Integer> l3 = points.get((i+2)%size);
        int x1 = l2.get(0) - l1.get(0);
        int y1 = l2.get(1) - l1.get(1);
        int x2 = l3.get(0) - l2.get(0);
        int y2 = l3.get(1) - l2.get(1);
        return x1 * y2 - x2 * y1;
    }
}
