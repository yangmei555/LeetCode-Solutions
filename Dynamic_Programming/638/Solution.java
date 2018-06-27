class Solution {
    public int shoppingOffers(List<Integer> price, List<List<Integer>> special, 
                                List<Integer> needs) {
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


class Solution {
    public int shoppingOffers(List<Integer> price, List<List<Integer>> special, 
                                List<Integer> needs) {
        HashMap<Integer, Integer> map = new HashMap<>();
        return helper(price, special, needs, map, 0);
    }
    
    public int helper(List<Integer> price, List<List<Integer>> special, List<Integer> needs, 
                        HashMap<Integer, Integer> map, int start) {
        int hash = needs.hashCode();
        // if (map.containsKey(hash))
        //     return map.get(hash);
        int res = Integer.MAX_VALUE;
        for (int index = start; index < special.size(); index++) {
            List<Integer> list = special.get(index);       
            int i = 0;
            List<Integer> nextNeed = new ArrayList<>(needs.size());
            for ( ; i < needs.size(); i++) {
                nextNeed.add(needs.get(i) - list.get(i));
                if (nextNeed.get(i) < 0)
                  break;  
            }
            if (i == needs.size()) {
                res = Math.min(res, list.get(i) + helper(price, special, nextNeed, map, index));
            }
        }
        int single = 0;
        for (int i = 0; i < price.size(); i++)
            single += price.get(i) * needs.get(i);
        res = res < single ? res : single;
        // map.put(hash, res);
        return res;
    }
}


class Solution {
    public int shoppingOffers(List<Integer> price, List<List<Integer>> special, 
                                List<Integer> needs) {
        HashMap<Integer, Integer> map = new HashMap<>();
        return helper(price, special, needs, map, 0);
    }
    
    public int helper(List<Integer> price, List<List<Integer>> special, List<Integer> needs, 
                        HashMap<Integer, Integer> map, int start) {
        int hash = needs.hashCode();
        if (map.containsKey(hash))
            return map.get(hash);
        int res = Integer.MAX_VALUE;
        for (int index = start; index < special.size(); index++) {
            List<Integer> list = special.get(index);       
            List<Integer> nextNeed = new ArrayList<>(needs.size());
            int count = Integer.MAX_VALUE;
            for (int i = 0; i < needs.size(); i++) {
                if (list.get(i) != 0)
                    count = count < needs.get(i)/list.get(i) ? count : needs.get(i)/list.get(i);
            }
            if (count != 0 && count != Integer.MAX_VALUE) {
                for (int i = 0; i < needs.size(); i++)
                    nextNeed.add(needs.get(i) - count * list.get(i));
                List<Integer> first = special.get(start), second = special.get(index);
                special.set(start, second);
                special.set(index, first);
                res = Math.min(res, list.get(list.size()-1) * count + 
                                    helper(price, special, nextNeed, map, start + 1));
                special.set(start, first);
                special.set(index, second);
            }
        }
        int single = 0;
        for (int i = 0; i < price.size(); i++)
            single += price.get(i) * needs.get(i);
        res = res < single ? res : single;
        map.put(hash, res);
        return res;
    }
}
