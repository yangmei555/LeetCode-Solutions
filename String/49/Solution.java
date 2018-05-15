class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> res = new ArrayList<>();
        Map<Integer, List<String>> map = new HashMap<>();
        for (String str : strs) {
            int[] index = new int[26];
            for (char c : str.toCharArray())
                index[c-'a']++;
            int s = Arrays.hashCode(index);
            map.putIfAbsent(s, new ArrayList<>());
            map.get(s).add(str);
        }
        for (int s : map.keySet())
            res.add(map.get(s));
        return res;
    }
}
