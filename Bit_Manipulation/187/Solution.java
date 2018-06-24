class Solution {
    public List<String> findRepeatedDnaSequences(String s) {
        List<String> res = new LinkedList<>();
        if (s.length() <= 10)
            return res;
        int[] map = new int[26];
        byte[] record = new byte[1 << 20];
        map[0] = 0;
        map['c'-'a'] = 1;
        map['g'-'a'] = 2;
        map['t'-'a'] = 3;
        char[] ch = s.toCharArray();
        int roll = 0;
        for (int i = 0; i < 10; i++)
            roll = (roll << 2) + map[ch[i]-'A'];
        record[roll]++;
        for (int i = 10; i < ch.length; i++) {
            roll = ((roll - (map[ch[i-10]-'A'] << 18)) << 2) + map[ch[i]-'A'];
            if (record[roll] < 3)
                record[roll]++;
            if (record[roll] == 2)
                res.add(s.substring(i-9, i+1));
        }
        return res;
    }
}
