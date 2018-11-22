class Solution {
    public int romanToInt(String s) {
        char[] ch = s.toCharArray();
        int[] value = new int[26];
        value['I'-'A'] = 1;
        value['V'-'A'] = 5;
        value['X'-'A'] = 10;
        value['L'-'A'] = 50;
        value['C'-'A'] = 100;
        value['D'-'A'] = 500;
        value['M'-'A'] = 1000;
        int res = 0;
        for (int i = 0; i < ch.length; i++) {
            if (ch[i] == 'I' || ch[i] == 'X' || ch[i] == 'C') {
                if (i != ch.length-1 && (value[ch[i+1]-'A'] == value[ch[i]-'A']*5 || 
                                            value[ch[i+1]-'A'] == value[ch[i]-'A']*10)) {
                    res += value[ch[i+1]-'A'] - value[ch[i]-'A'];
                    i++;
                } else {
                    res += value[ch[i]-'A'];
                }
            } else {
                res += value[ch[i]-'A'];
            }
        }
        return res;
    }
}


class Solution {
    public int romanToInt(String s) {
        int[] map = new int[26];
        map['I'-'A'] = 1;
        map['V'-'A'] = 5;
        map['X'-'A'] = 10;
        map['L'-'A'] = 50;
        map['C'-'A'] = 100;
        map['D'-'A'] = 500;
        map['M'-'A'] = 1000;
        char[] ch = s.toCharArray();
        int res = 0;
        for (int i = 0; i < ch.length; i++) {
            res += map[ch[i]-'A'];
            if (i != 0) {
                if (ch[i-1] == 'I' && (ch[i] == 'V' || ch[i] == 'X') || 
                    ch[i-1] == 'X' && (ch[i] == 'L' || ch[i] == 'C') || 
                    ch[i-1] == 'C' && (ch[i] == 'D' || ch[i] == 'M'))
                    res -= 2 * map[ch[i-1]-'A'];
            }
        }
        return res;
    }
}
