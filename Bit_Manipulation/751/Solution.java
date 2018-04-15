class Solution {
    public List<String> ipToCIDR(String ip, int n) {
        String[] s = ip.split("\\.");
        int add = 0;
        for (int i = 0; i < s.length; i++)
            add |= Integer.valueOf(s[i]) << (24 - 8 * i);
        List<String> list = new LinkedList<>();
        while (true) {
            if (n == 0)
                break;
            int num = 1, count = 0;
            while (num <= n && (add & num) == 0) {
                num <<= 1;
                count++;
            }
            if (num > n) {
                num >>= 1;
                count--;
            }
            n -= num;
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 4; i++)
                sb.append("." + (add >>> (24 - 8 * i) & 255));
            sb.append("/" + (32 - count));
            list.add(sb.toString().substring(1));
            add += num;
        }
        return list;
    }
}
