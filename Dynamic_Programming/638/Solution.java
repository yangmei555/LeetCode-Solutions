class Solution {
    public int shoppingOffers(List<Integer> price, List<List<Integer>> special, List<Integer> needs) {
        HashMap<String, Integer> map = new HashMap<>();
        return helper(price, special, needs, map);
    }
    
    public int helper(List<Integer> price, List<List<Integer>> special, List<Integer> needs, 
                        HashMap<String, Integer> map) {
        String str = needs.toString();
        if (map.containsKey(str))
            return map.get(str);
        int res = Integer.MAX_VALUE;
        for (List<Integer> list : special) {
            int i = 0;
            List<Integer> nextNeed = new ArrayList<>(needs.size());
            for ( ; i < needs.size(); i++) {
                nextNeed.add(needs.get(i) - list.get(i));
                if (nextNeed.get(i) < 0)
                  break;  
            }
            if (i == needs.size()) {
                res = Math.min(res, list.get(i) + helper(price, special, nextNeed, map));
            }
        }
        int single = 0;
        for (int i = 0; i < price.size(); i++)
            single += price.get(i) * needs.get(i);
        res = res < single ? res : single;
        map.put(str, res);
        return res;
    }
}
