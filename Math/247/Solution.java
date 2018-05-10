class Solution {
    char[] ch = new char[]{'0', '1', '8', '6', '9', '6', '9', '8', '1', '0'};
    public List<String> findStrobogrammatic(int n) {
        List<String> list = new ArrayList<>();
        if (n == 0)
            return list;
        StringBuilder sb = new StringBuilder();
        helper(list, sb, 0, n-1);
        return list;
    }
    
    public void helper(List<String> list, StringBuilder sb, int left, int right) {
        if (left > right) {
            list.add(sb.toString());
            return;
        } else if (left == right) {
            for (int i = 0; i < ch.length / 3; i++) {
                sb.insert(left, ch[i]);
                list.add(sb.toString());
                sb.deleteCharAt(left);
            }
        } else {
            for (int i = 0; i < ch.length / 2; i++) {
                if (i == 0 && left == 0)
                    continue;
                sb.insert(left, ch[i]);
                sb.insert(sb.length()-left, ch[ch.length-1-i]);
                helper(list, sb, left+1, right-1);
                sb.deleteCharAt(left);
                sb.deleteCharAt(sb.length()-1-left);
            }
        }
    }
}


class Solution {
    public List<String> findStrobogrammatic(int n) {
        return helper(n, n);
    }
    
    public List<String> helper(int n, int total) {
        if (n == 0) {
            return Arrays.asList("");
        } else if (n == 1) {
            return Arrays.asList("0", "1", "8");
        }
        List<String> ret = helper(n-2, total);
        List<String> res = new ArrayList<>();
        for (String s : ret) {
            if (n != total)
                res.add("0" + s + "0");
            res.add("1" + s + "1");
            res.add("8" + s + "8");
            res.add("6" + s + "9");
            res.add("9" + s + "6");
        }
        return res;
    }
}
