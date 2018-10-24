class MyCalendar {
    
    List<int[]> list;
    public MyCalendar() {
        list = new ArrayList<>();
    }
    
    public boolean book(int start, int end) {
        boolean res = true;
        for (int[] i : list) {
            if (start < i[1] && end > i[0]) {
                res = false;
                break;
            }
        }
        if (res == false)
            return false;
        list.add(new int[]{start, end});
        return true;
    }
}

/**
 * Your MyCalendar object will be instantiated and called as such:
 * MyCalendar obj = new MyCalendar();
 * boolean param_1 = obj.book(start,end);
 */


class MyCalendar {
    
    TreeSet<int[]> set;
    public MyCalendar() {
        set = new TreeSet<>(new Comparator<int[]>() {
            public int compare(int[] i1, int[] i2) {
                return i1[0] - i2[0];
            }
        });
    }
    
    public boolean book(int start, int end) {
        int[] range = new int[]{start, end};
        int[] range1 = set.floor(range);
        if (range1 != null && range[0] < range1[1])
            return false;
        int[] range2 = set.ceiling(range);
        if (range2 != null && range2[0] < range[1])
            return false;
        return set.add(range);
    }
}

/**
 * Your MyCalendar object will be instantiated and called as such:
 * MyCalendar obj = new MyCalendar();
 * boolean param_1 = obj.book(start,end);
 */


// unbalanced interval tree , but runs fast 
class MyCalendar {

    Node root;
    public MyCalendar() {
        root = new Node(Integer.MAX_VALUE, Integer.MIN_VALUE);
    }
    
    public boolean book(int start, int end) {
        if (insert(root, start, end) != null)
            return true;
        else
            return false;
    }
    
    public Node insert(Node root, int start, int end) {
        if (root == null)
            return new Node(start, end);
        if (root.start < end && start < root.end)
            return null;
        if (start < root.start) {
            Node node = insert(root.left, start, end);
            if (node == null)
                return null;
            else
                root.left = node;
        } else {
            Node node = insert(root.right, start, end);
            if (node == null)
                return null;
            else
                root.right = node;
        }
        return root;
    }
    
    class Node {
        int start, end;
        Node left, right;
        public Node (int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
}

/**
 * Your MyCalendar object will be instantiated and called as such:
 * MyCalendar obj = new MyCalendar();
 * boolean param_1 = obj.book(start,end);
 */
