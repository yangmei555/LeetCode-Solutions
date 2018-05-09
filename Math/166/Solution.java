class Solution {
    public String fractionToDecimal(int numerator, int denominator) {
        if (numerator == 0)
            return "0";
        long n = numerator, d = denominator;
        n = n > 0 ? n : -n;
        d = d > 0 ? d : -d;
        StringBuilder sb = new StringBuilder();
        if (numerator > 0 ^ denominator > 0)
            sb.append('-');
        sb.append(n / d);
        long remain = n % d;
        if (remain == 0)
            return sb.toString();
        sb.append(".");
        int index = sb.length();
        Map<Long, Integer> map = new HashMap<>();
        while (!map.containsKey(remain)) {
            map.put(remain, index);
            remain *= 10;
            sb.append(remain / d);
            remain %= d;
            index++;
            if (remain == 0)
                break;
        }
        if (remain != 0) {
            int pos = map.get(remain);
            sb.insert(pos, '(');
            sb.append(')');
        }
        return sb.toString();
    }
}
