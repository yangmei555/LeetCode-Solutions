class Solution {
    public List<Integer> partitionLabels(String S) {
        char[] ch = S.toCharArray();
        List<Integer> res = new LinkedList<>();
        int[] map = new int[26];
        for (int i = 0; i < ch.length; i++) 
            map[ch[i]-'a'] = i;
        int index = 0, start = 0, end = 0;
        while (index < ch.length) {
            end = end > map[ch[index]-'a'] ? end : map[ch[index]-'a'];
            if (end == index) {
                res.add(end - start + 1);
                start = end + 1;
            }
            index++;
        }
        return res;
    }
}
