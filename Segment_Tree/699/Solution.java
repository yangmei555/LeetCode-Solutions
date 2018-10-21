// segment tree with lazy propagation 
class Solution {
    public List<Integer> fallingSquares(int[][] positions) {
        Set<Integer> set = new TreeSet<>();
        for (int[] pos : positions) {
            set.add(pos[0]);
            set.add(pos[0] + pos[1]);
        }
        Map<Integer, Integer> map = new HashMap<>();
        int id = 0;
        for (int s : set) 
            map.put(s, id++);
        Node root = build(0, id-1);
        List<Integer> res = new LinkedList<>();
        int max = 0;
        for (int[] pos : positions) {
            int i = map.get(pos[0]), j = map.get(pos[0] + pos[1]);
            int ret = query(root, i, j);
            max = Math.max(max, ret + pos[1]);
            res.add(max);
            update(root, i, j, ret + pos[1]);
        }
        return res;
    }
    
    public void update(Node root, int i, int j, int val) {
        int mid = (root.start + root.end) / 2;
        if (root.lazy != 0) {
            root.max = root.lazy;
            if (root.start + 1 != root.end) {
                root.left.lazy = root.lazy;
                root.right.lazy = root.lazy;
            }
            root.lazy = 0;
        }
        if (j <= root.start || i >= root.end)
            return;
        if (i <= root.start && j >= root.end) {
            root.max = val;
            if (root.start + 1 != root.end) {
                root.left.lazy = val;
                root.right.lazy = val;
            }
        } else {
            // generally speaking, need to update two sides even if one side is out of [i, j], 
            // because that side may have lazy values to process 
            update(root.left, i, j, val);
            update(root.right, i, j, val);
            root.max = Math.max(root.left.max, root.right.max);
        }
    }
    
    public int query(Node root, int i, int j) {
        int mid = (root.start + root.end) / 2;
        if (root.lazy != 0) {
            root.max = root.lazy;
            if (root.start + 1 != root.end) {
                root.left.lazy = root.lazy;
                root.right.lazy = root.lazy;
            }
            root.lazy = 0;
        }
        if (root.start >= i && root.end <= j)
            return root.max;
        if (j <= mid)
            return query(root.left, i, j);
        else if (i >= mid)
            return query(root.right, i, j);
        else
            return Math.max(query(root.left, i, j), query(root.right, i, j));
    }
    
    public Node build(int start, int end) {
        Node root = new Node(start, end);
        if (start + 1 != end) {
            int mid = (start + end) / 2;
            root.left = build(start, mid);
            root.right = build(mid, end);
        }
        return root;
    }
    
    class Node {
        int start, end, max, lazy;
        Node left, right;
        public Node (int start, int end) {
            this.start = start;
            this.end = end;
            max = 0;
            lazy = 0;
        }
    }
}


// O(n^2) plain solution 
class Solution {
    public List<Integer> fallingSquares(int[][] positions) {
        List<int[]> intervals = new LinkedList<>();
        List<Integer> res = new LinkedList<>();
        int max = 0;
        for (int[] pos : positions) {
            int local = 0;
            for (int[] node : intervals) {
                if (pos[0] < node[1] && node[0] < pos[0] + pos[1])
                    local = Math.max(local, node[2]);
            }
            max = Math.max(max, local + pos[1]);
            res.add(max);
            intervals.add(new int[]{pos[0], pos[0] + pos[1], local + pos[1]});
        }
        return res;
    }
}
