class Solution {
    public List<String> subdomainVisits(String[] cpdomains) {
        HashMap<String, Integer> map = new HashMap<>();
        for (String s : cpdomains) {
            String str = s.substring(s.indexOf(" ") + 1);
            int num = Integer.valueOf(s.substring(0, s.indexOf(" ")));
            map.put(str, map.getOrDefault(str, 0) + num);
            int start = 0, index = 0;
            while ((index = str.indexOf(".", start)) != -1) {
                map.put(str.substring(index + 1), map.getOrDefault(str.substring(index + 1), 0) + num);
                start = index + 1;
            }
        }
        List<String> res = new LinkedList<>();
        for (String s : map.keySet()) {
            res.add(map.get(s) + " " + s);
        }
        return res;
    }
}
