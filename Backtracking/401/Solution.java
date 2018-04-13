class Solution {
    public List<String> readBinaryWatch(int num) {
        if (num < 0 || num > 10)
            return new LinkedList<>();
        List<String> res = new LinkedList<>();
        int count = 0, start = 9;
        help(res, num, start, count);
        return res;
    }
    
    public void help(List<String> res, int num, int start, int count) {
        if (num == 0) {
            int min = count & 0x3f;
            int hour = count >> 6;
            if (min > 59 || hour > 11)
                return;
            res.add(hour + ":" + (min < 10 ? "0" + min : min));
            return;
        }
        if (start + 1 < num)
            return;
        help(res, num, start - 1, count);
        help(res, num - 1, start - 1, count | (1 << start));
    }
}


class Solution {
    public List<String> readBinaryWatch(int num) {
        if (num < 0 || num > 10)
            return new LinkedList<>();
        List<String> res = new LinkedList<>();
        for (int hour = 0; hour < 12; hour++) {
            for (int min = 0; min < 60; min++) {
                if (Integer.bitCount((hour << 6) + min) == num) {
                    res.add(hour + ":" + (min < 10 ? "0" + min : min));
                }
            }
        }
        return res;
    }
}
