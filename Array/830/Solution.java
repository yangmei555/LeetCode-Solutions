class Solution {
    public List<List<Integer>> largeGroupPositions(String S) {
        char[] ch = S.toCharArray();
        List<List<Integer>> res = new LinkedList<>();
        int index = 0, start = 0;
        while (index < ch.length) {
            start = index++;
            while (index < ch.length && ch[index-1] == ch[index])
                index++;
            if (index - start >= 3) 
                res.add(Arrays.asList(start, index-1));
        }
        return res;
    }
}
