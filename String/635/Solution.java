class LogSystem {
    
    Map<String, Integer> map;
    Map<String, Integer> granularity;
    public LogSystem() {
        granularity = new HashMap<>();
        granularity.put("Year", 4);
        granularity.put("Month", 7);
        granularity.put("Day", 10);
        granularity.put("Hour", 13);
        granularity.put("Minute", 16);
        granularity.put("Second", 19);
        map = new HashMap<>();
    }
    
    public void put(int id, String timestamp) {
        map.put(timestamp, id);
    }
    
    public List<Integer> retrieve(String s, String e, String gra) {
        int len = granularity.get(gra);
        s = s.substring(0, len);
        e = e.substring(0, len);
        List<Integer> res = new LinkedList<>();
        for (String string : map.keySet()) {
            String str = string.substring(0, len);
            if (str.compareTo(s) >= 0 && str.compareTo(e) <= 0)
                res.add(map.get(string));
        }
        return res;
    }
}

/**
 * Your LogSystem object will be instantiated and called as such:
 * LogSystem obj = new LogSystem();
 * obj.put(id,timestamp);
 * List<Integer> param_2 = obj.retrieve(s,e,gra);
 */


class LogSystem {
    
    List<String> list1;
    List<Integer> list2;
    Map<String, Integer> granularity;
    public LogSystem() {
        granularity = new HashMap<>();
        granularity.put("Year", 4);
        granularity.put("Month", 7);
        granularity.put("Day", 10);
        granularity.put("Hour", 13);
        granularity.put("Minute", 16);
        granularity.put("Second", 19);
        list1 = new LinkedList<>();
        list2 = new LinkedList<>();
    }
    
    public void put(int id, String timestamp) {
        list1.add(timestamp);
        list2.add(id);
    }
    
    public List<Integer> retrieve(String s, String e, String gra) {
        int len = granularity.get(gra), i = 0;
        s = s.substring(0, len);
        e = e.substring(0, len);
        List<Integer> res = new LinkedList<>();
        for (String string : list1) {
            String str = string.substring(0, len);
            if (str.compareTo(s) >= 0 && str.compareTo(e) <= 0)
                res.add(list2.get(i));
            i++;
        }
        return res;
    }
}

/**
 * Your LogSystem object will be instantiated and called as such:
 * LogSystem obj = new LogSystem();
 * obj.put(id,timestamp);
 * List<Integer> param_2 = obj.retrieve(s,e,gra);
 */
