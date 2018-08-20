class Solution {
    public int numDistinctIslands2(int[][] grid) {
        Set<MyList> set = new HashSet<>();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    List<List<Integer>> list = new LinkedList<>();
                    helper(grid, i, j, i, j, list);
                    MyList mylist = new MyList(list);
                    if (set.contains(sort(list)) || 
                        set.contains(sort(rotate90(list))) || 
                        set.contains(sort(rotate180(list))) || 
                        set.contains(sort(rotate270(list))) || 
                        set.contains(sort(reflectX(list))) || 
                        set.contains(sort(reflectY(list))) || 
                        set.contains(sort(rotate90ReflectX(list))) || 
                        set.contains(sort(rotate90ReflectY(list))))
                        continue;

                    set.add(new MyList(list));
                }
            }
        }
        // for (MyList m : set)
        //     System.out.println(m.list);
        return set.size();
    }
    
    public MyList sort(List<List<Integer>> list) {
        Collections.sort(list, new Comparator<List<Integer>>() {
            public int compare(List<Integer> l1, List<Integer> l2) {
                if (l1.get(0) != l2.get(0)) 
                    return l1.get(0) - l2.get(0);
                else 
                    return l1.get(1) - l2.get(1);
            }
        });
        return new MyList(list);
    }
    
    public void helper(int[][] grid, int i, int j, int x, int y, List<List<Integer>> list) {
        int dx = i - x, dy = j - y;
        list.add(Arrays.asList(dx, dy));
        grid[i][j] = 0;
        if (i-1 >= 0 && grid[i-1][j] == 1) 
            helper(grid, i-1, j, x, y, list);
        if (i+1 < grid.length && grid[i+1][j] == 1) 
            helper(grid, i+1, j, x, y, list);
        if (j-1 >= 0 && grid[i][j-1] == 1) 
            helper(grid, i, j-1, x, y, list);
        if (j+1 < grid[0].length && grid[i][j+1] == 1) 
            helper(grid, i, j+1, x, y, list);
    }
    
    public List<List<Integer>> rotate90(List<List<Integer>> list) {
        List<List<Integer>> res = new LinkedList<>();
        for (List<Integer> l : list) 
            res.add(Arrays.asList(l.get(1), -l.get(0)));
        return res;
    }
    
    public List<List<Integer>> rotate180(List<List<Integer>> list) {
        List<List<Integer>> res = new LinkedList<>();
        for (List<Integer> l : list) 
            res.add(Arrays.asList(-l.get(0), -l.get(1)));
        return res;
    }
    
    public List<List<Integer>> rotate270(List<List<Integer>> list) {
        List<List<Integer>> res = new LinkedList<>();
        for (List<Integer> l : list) 
            res.add(Arrays.asList(-l.get(1), l.get(0)));
        return res;
    }
    
    public List<List<Integer>> reflectX(List<List<Integer>> list) {
        List<List<Integer>> res = new LinkedList<>();
        for (List<Integer> l : list) 
            res.add(Arrays.asList(l.get(0), -l.get(1)));
        return res;
    }
    
    public List<List<Integer>> reflectY(List<List<Integer>> list) {
        List<List<Integer>> res = new LinkedList<>();
        for (List<Integer> l : list) 
            res.add(Arrays.asList(-l.get(0), l.get(1)));
        return res;
    }
    
    public List<List<Integer>> rotate90ReflectX(List<List<Integer>> list) {
        List<List<Integer>> res = new LinkedList<>();
        for (List<Integer> l : list) 
            res.add(Arrays.asList(l.get(1), l.get(0)));
        return res;
    }
    
    public List<List<Integer>> rotate90ReflectY(List<List<Integer>> list) {
        List<List<Integer>> res = new LinkedList<>();
        for (List<Integer> l : list) 
            res.add(Arrays.asList(-l.get(1), -l.get(0)));
        return res;
    }
    
    class MyList {
        List<List<Integer>> list;
        public MyList(List<List<Integer>> list) {
            this.list = list;
        }
        public int hashCode() {
            return list.size();
        }
        public boolean equals(Object obj) {
            List<List<Integer>> list = ((MyList)obj).list;
            int x = this.list.get(0).get(0) - list.get(0).get(0);
            int y = this.list.get(0).get(1) - list.get(0).get(1);
            for (int i = 1; i < list.size(); i++) {
                if (x != this.list.get(i).get(0) - list.get(i).get(0))
                    return false;
                if (y != this.list.get(i).get(1) - list.get(i).get(1))
                    return false;
            }
            return true;
        }
    }
}


// a way of hashing which only works for not very large inputs 
class Solution {
    public int numDistinctIslands2(int[][] grid) {
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    List<List<Integer>> list = new ArrayList<>();
                    helper(grid, i, j, i, j, list);
                    set.add(hash(list));
                }
            }
        }
        return set.size();
    }
    
    public int hash(List<List<Integer>> list) {
        int hash = 0;
        for (int i = 0; i < list.size(); i++) {
            for (int j = i+1; j < list.size(); j++) {
                int dx = list.get(j).get(0) - list.get(i).get(0);
                int dy = list.get(j).get(1) - list.get(i).get(1);
                // here the 21 and 31 are two magic numbers 
                // can be replaces by other two co prime numbers as long as they can pass the OJ 
                hash += (dx == 0 || dy == 0) ? (dx * dx + dy * dy) * 21 : (dx * dx + dy * dy) * 31;
            }
        }
        return hash;
    }
    
    public void helper(int[][] grid, int i, int j, int x, int y, List<List<Integer>> list) {
        int dx = i - x, dy = j - y;
        list.add(Arrays.asList(dx, dy));
        grid[i][j] = 0;
        if (i-1 >= 0 && grid[i-1][j] == 1) 
            helper(grid, i-1, j, x, y, list);
        if (i+1 < grid.length && grid[i+1][j] == 1) 
            helper(grid, i+1, j, x, y, list);
        if (j-1 >= 0 && grid[i][j-1] == 1) 
            helper(grid, i, j-1, x, y, list);
        if (j+1 < grid[0].length && grid[i][j+1] == 1) 
            helper(grid, i, j+1, x, y, list);
    }
}
