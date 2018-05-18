class Solution {
    public List<List<String>> findDuplicate(String[] paths) {
        Map<String, List<String>> map = new HashMap<>();
        for (String path : paths) {
            String[] p = path.split(" ");
            for (int i = 1; i < p.length; i++) {
                int index = p[i].indexOf('(');
                String content = p[i].substring(index+1, p[i].length()-1);
                map.putIfAbsent(content, new ArrayList<>());
                map.get(content).add(p[0] + "/" + p[i].substring(0, index));
            }
        }
        List<List<String>> res = new ArrayList<>();
        for (String s : map.keySet()) {
            if (map.get(s).size() != 1)
                res.add(map.get(s));
        }
        return res;
    }
}


class Solution {
    public List<List<String>> findDuplicate(String[] paths) {
        Map<String, List<String>> map = new HashMap<>();
        for (String path : paths) {
            String[] p = path.split(" ");
            int len = p[0].length()+1;
            StringBuilder sb = new StringBuilder();
            sb.append(p[0]).append('/');
            for (int i = 1; i < p.length; i++) {
                int index = p[i].indexOf('(');
                String content = p[i].substring(index+1, p[i].length()-1);
                map.putIfAbsent(content, new ArrayList<>());
                map.get(content).add(sb.append(p[i].substring(0, index)).toString());
                sb.setLength(len);
            }
        }
        List<List<String>> res = new ArrayList<>();
        for (String s : map.keySet()) {
            if (map.get(s).size() != 1)
                res.add(map.get(s));
        }
        return res;
    }
}
