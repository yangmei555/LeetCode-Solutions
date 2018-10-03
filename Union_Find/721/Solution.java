class Solution {
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        Map<String, List<String>> adj = new HashMap<>();
        Map<String, String> emailToName = new HashMap<>();
        for (List<String> account : accounts) {
            for (int i = 1; i < account.size(); i++) {
                emailToName.put(account.get(i), account.get(0));
                adj.putIfAbsent(account.get(i), new LinkedList<>());
                for (int j = i+1; j < account.size(); j++) {
                    adj.putIfAbsent(account.get(j), new LinkedList<>());
                    adj.get(account.get(i)).add(account.get(j));
                    adj.get(account.get(j)).add(account.get(i));
                }
            }
        }
        Set<String> visited = new HashSet<>();
        List<List<String>> res = new LinkedList<>();
        for (String str : adj.keySet()) {
            if (!visited.contains(str)) {
                List<String> list = new LinkedList<>();
                helper(adj, str, list, visited);
                Collections.sort(list);
                list.add(0, emailToName.get(str));
                res.add(list);
            }
        }
        return res;
    }
    
    public void helper(Map<String, List<String>> adj, String str, List<String> list, 
                                                                    Set<String> visited) {
        visited.add(str);
        list.add(str);
        for (String s : adj.get(str)) {
            if (!visited.contains(s))
                helper(adj, s, list, visited);
        }
    }
}


// Union Find, but much slower than the DFS connected component method  
class Solution {
    Map<String, String> id = new HashMap<>();
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        Map<String, Integer> emailToName = new HashMap<>();
        for (int n = 0; n < accounts.size(); n++) {
            List<String> account = accounts.get(n);
            for (int i = 1; i < account.size(); i++) {
                emailToName.put(account.get(i), n);
                String rep1 = find(account.get(1)), rep2 = find(account.get(i));
                if (!rep1.equals(rep2)) {
                    id.put(rep1, rep2);
                }
            }
        }
        List<List<String>> res = new LinkedList<>();
        Map<String, List<String>> map = new HashMap<>();
        for (String str : id.keySet()) {
            String rep = find(str);
            map.putIfAbsent(rep, new LinkedList<>());
            map.get(rep).add(str);
        }
        for (String str : map.keySet()) {
            List<String> list = map.get(str);
            Collections.sort(list);
            list.add(0, accounts.get(emailToName.get(str)).get(0));
            res.add(list);
        }
        return res;
    }
    
    public String find(String str) {
        if (id.get(str) == null) {
            id.put(str, str);
            return str;
        } else if (id.get(str).equals(str)) {
            return str;
        } else {
            return find(id.get(str));
        }
    }
}
