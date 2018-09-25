class Solution {
    public boolean isSelfCrossing(int[] x) {
        if (x.length < 4)
            return false;
        int index = 2;
        while (index < x.length && x[index] > x[index-2])
            index++;
        if (index == x.length)
            return false;
        int bound = 0;
        if (index >= 4 && x[index] >= x[index-2] - x[index-4] || index == 3 && x[index] == x[index-2])
            bound = x[index-1] - x[index-3];
        else
            bound = x[index-1];
        index++;
        while (index < x.length && x[index] < bound) {
            bound = x[index-1];
            index++;
        }
        return index != x.length;
    }
}
