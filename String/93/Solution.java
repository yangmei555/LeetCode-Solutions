class Solution {
    public List<String> restoreIpAddresses(String s) {
        char[] ch = s.toCharArray();
        char[] ip = new char[ch.length+3];
        List<String> res = new ArrayList<>();
        helper(res, ch, ip, 0, 0, -1, 0);
        return res;
    }
    
    public void helper(List<String> res, char[] ch, char[] ip, int p1, int p2, int n, int dots) {
        if (dots == 4)
            return;
        if (p1 == ch.length || p2 == ip.length) {
            if (p1 == ch.length && p2 == ip.length && ip[p2-1] != '.')
                res.add(new String(ip));
        } else {
            if (n == -1) {
                n = ch[p1] - '0';
                ip[p2] = ch[p1];
                if (n == 0 && p2+1 < ip.length) {
                    ip[p2+1] = '.';
                    helper(res, ch, ip, p1+1, p2+2, -1, dots+1);
                } else {
                    helper(res, ch, ip, p1+1, p2+1, n, dots);;
                }
            } else {
                ip[p2] = '.';
                helper(res, ch, ip, p1, p2+1, -1, dots+1);
                if (n*10+ch[p1]-'0' <= 255) {
                    ip[p2] = ch[p1];
                    helper(res, ch, ip, p1+1, p2+1, n*10+ch[p1]-'0', dots);
                }
            }
        }
    }
}


class Solution {
    public List<String> restoreIpAddresses(String s) {
        List<String> list = new ArrayList<>();
        int len = s.length();
        for (int i = 1; i <= 3; i++) {
            if (i >= len)
                break;
            for (int j = i+1; j <= i+3; j++) {
                if (j >= len)
                    break;
                for (int k = j+1; k <= j+3; k++) {
                    if (k >= len)
                        break;
                    if (len-k > 3)
                        continue;
                    String s1 = s.substring(0, i), s2 = s.substring(i, j), 
                            s3 = s.substring(j, k), s4 = s.substring(k, len);
                    if (Integer.valueOf(s1)<=255 && Integer.valueOf(s2)<=255 && 
                        Integer.valueOf(s3)<=255 && Integer.valueOf(s4)<=255) {
                        if ((s1.charAt(0)!='0' || s1.length()==1) && 
                            (s2.charAt(0)!='0' || s2.length()==1) && 
                            (s3.charAt(0)!='0' || s3.length()==1) && 
                            (s4.charAt(0)!='0' || s4.length()==1))
                            list.add(s1+'.'+s2+'.'+s3+'.'+s4);
                    }
                }
            }
        }
        return list;
    }
}
