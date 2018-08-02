class Solution {
    public List<String> ambiguousCoordinates(String S) {
        String xy = S.substring(1, S.length()-1);
        List<String> res = new LinkedList<>();
        for (int i = 1; i < xy.length(); i++) {
            String x = xy.substring(0, i), y = xy.substring(i);
            for (int j = 0; j < x.length(); j++) {
                String x1 = j == 0 ? "" : x.substring(0, j), x2 = x.substring(j);
                if (j == 0 && x2.length() >= 2 && x2.charAt(0) == '0')
                    continue;
                if (x1.length() >= 2 && x1.charAt(0) == '0' || 
                        j != 0 && x2.charAt(x2.length()-1) == '0')
                    continue;
                String xx = j == 0 ? x2 : x1 + "." + x2;
                for (int k = 0; k < y.length(); k++) {
                    String y1 = k == 0 ? "" : y.substring(0, k), y2 = y.substring(k);
                    if (k == 0 && y2.length() >= 2 && y2.charAt(0) == '0')
                        continue;
                    if (y1.length() >= 2 && y1.charAt(0) == '0' || 
                            k != 0 && y2.charAt(y2.length()-1) == '0')
                        continue;
                    String yy = k == 0 ? y2 : y1 + "." + y2;
                    res.add("(" + xx + ", " + yy + ")");
                }
            }
        }
        return res;
    }
}


class Solution {
    public List<String> ambiguousCoordinates(String S) {
        String xy = S.substring(1, S.length()-1);
        List<String> res = new LinkedList<>();
        for (int i = 1; i < xy.length(); i++) {
            String x = xy.substring(0, i), y = xy.substring(i);
            for (int j = 1; j <= x.length(); j++) {
                // x1 : integral part,  x2 : fractional part 
                String x1 = x.substring(0, j), x2 = x.substring(j);
                if (x1.length() >= 2 && x1.charAt(0) == '0')
                    continue;
                if (x2.length() != 0 && x2.charAt(x2.length()-1) == '0')
                    continue;
                String xx = x1 + (x2.length() == 0 ? x2 : "." + x2);
                for (int k = 1; k <= y.length(); k++) {
                    // y1 : integral part, y2 : fractional part 
                    String y1 = y.substring(0, k), y2 = y.substring(k);
                    if (y1.length() >= 2 && y1.charAt(0) == '0')
                        continue;
                    if (y2.length() != 0 && y2.charAt(y2.length()-1) == '0')
                        continue;
                    String yy = y1 + (y2.length() == 0 ? y2 : "." + y2);
                    res.add("(" + xx + ", " + yy + ")");
                }
            }
        }
        return res;
    }
}
