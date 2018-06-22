class Solution {
    public int[] exclusiveTime(int n, List<String> logs) {
        int[] res = new int[n], stack = new int[logs.size()];
        int index = 0, pre = 0;
        for (String str : logs) {
            char[] ch = str.toCharArray();
            int id = 0, stamp = 0, i = 0;
            while (ch[i] != ':')
                id = id * 10 + ch[i++] - '0';
            boolean start = ch[i+4] == ':' ? false : true;
            i += start ? 7 : 5;
            while (i < ch.length)
                stamp = stamp * 10 + ch[i++] - '0';
            if (start) {
                if (index != 0)
                    res[stack[index-1]] += stamp - pre;
                stack[index++] = id;
            } else {
                stamp++;
                res[stack[index-1]] += stamp - pre;
                index--;
            }
            pre = stamp;
        }
        return res;
    }
}
