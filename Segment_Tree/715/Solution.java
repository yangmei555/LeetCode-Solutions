class RangeModule {
    TreeSet<int[]> set;
    public RangeModule() {
        set = new TreeSet<>(new Comparator<int[]>() {
            public int compare(int[] i1, int[] i2) {
                return i1[0] - i2[0];
            }
        });
    }
    
    public void addRange(int left, int right) {
        int[] range = new int[]{left, right};
        int[] range1 = set.floor(range);
        if (range1 != null && range1[1] >= left) {
            set.remove(range1);
            left = range1[0];
            right = Math.max(right, range1[1]);
        }
        int[] range2 = set.ceiling(range);
        while (range2 != null && range2[0] <= right) {
            set.remove(range2);
            right = Math.max(right, range2[1]);
            range2 = set.ceiling(range);
        }
        set.add(new int[]{left, right});
    }
    
    public boolean queryRange(int left, int right) {
        int[] range = set.floor(new int[]{left, right});
        return range != null && right <= range[1];
    }
    
    public void removeRange(int left, int right) {
        int[] range = new int[]{left, right};
        int[] range1 = set.floor(range);
        if (range1 != null && range1[1] > left) {
            set.remove(range1);
            if (range1[0] != left)
                set.add(new int[]{range1[0], left});
            if (right < range1[1])
                set.add(new int[]{right, range1[1]});
        }
        int[] range2 = set.ceiling(range);
        while (range2 != null && range2[0] < right) {
            set.remove(range2);
            if (right < range2[1])
                set.add(new int[]{right, range2[1]});
            range2 = set.ceiling(range);
        }
    }
}

/**
 * Your RangeModule object will be instantiated and called as such:
 * RangeModule obj = new RangeModule();
 * obj.addRange(left,right);
 * boolean param_2 = obj.queryRange(left,right);
 * obj.removeRange(left,right);
 */


// segment tree with lazy propagation. sometimes pass and sometimes time limit exceed 
class RangeModule {

    Node root;
    public RangeModule() {
        root = new Node(0, 1000000000);
    }
    
    public void addRange(int left, int right) {
        update(root, left, right-1, 1);
    }
    
    public void update(Node root, int i, int j, int val) {
        int mid = root.start + (root.end - root.start) / 2;
        if (root.start != root.end) {
            if (root.left == null)
                root.left = new Node(root.start, mid);
            if (root.right == null)
                root.right = new Node(mid+1, root.end);
        }
        if (root.lazy != 0) {
            if (root.lazy == 1)
                root.count = root.end - root.start + 1;
            else
                root.count = 0;
            if (root.start != root.end) {
                root.left.lazy = root.lazy;
                root.right.lazy = root.lazy;
            }
            root.lazy = 0;
        }
        if (j < root.start || root.end < i)
            return;
        if (i <= root.start && root.end <= j) {
            if (val == 1)
                root.count = root.end - root.start + 1;
            else
                root.count = 0;
            if (root.start != root.end) {
                root.left.lazy = val;
                root.right.lazy = val;
            }
        } else {
            update(root.left, i, j, val);
            update(root.right, i, j, val);
            root.count = root.left.count + root.right.count;
        }
    }
    
    public boolean queryRange(int left, int right) {
        return query(root, left, right-1);
    }
    
    public boolean query(Node root, int i, int j) {
        if (root == null)
            return false;
        int mid = root.start + (root.end - root.start) / 2;
        if (root.lazy != 0) {
            if (root.lazy == 1)
                root.count = root.end - root.start + 1;
            else
                root.count = 0;
            if (root.start != root.end) {
                if (root.left == null)
                    root.left = new Node(root.start, mid);
                if (root.right == null)
                    root.right = new Node(mid+1, root.end);
                root.left.lazy = root.lazy;
                root.right.lazy = root.lazy;
            }
            root.lazy = 0;
        }
        if (i <= root.start && root.end <= j)
            return root.count == root.end - root.start + 1;
        else if (j <= mid)
            return query(root.left, i, j);
        else if (mid+1 <= i)
            return query(root.right, i, j);
        else
            return query(root.left, i, j) && query(root.right, i, j);
    }
    
    public void removeRange(int left, int right) {
        update(root, left, right-1, -1);
    }
    
    class Node {
        int start, end, lazy, count;
        Node left, right;
        public Node (int start, int end) {
            this.start = start;
            this.end = end;
            this.lazy = 0;
            this.count = 0;
        }
    }
}

/**
 * Your RangeModule object will be instantiated and called as such:
 * RangeModule obj = new RangeModule();
 * obj.addRange(left,right);
 * boolean param_2 = obj.queryRange(left,right);
 * obj.removeRange(left,right);
 */
