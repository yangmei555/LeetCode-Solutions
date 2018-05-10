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
