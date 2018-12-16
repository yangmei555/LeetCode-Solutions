class Solution {
    public int[][] reconstructQueue(int[][] people) {
        Arrays.sort(people, new Comparator<int[]>() {
            public int compare(int[] p1, int[] p2) {
                if (p1[0] == p2[0]) 
                    return p1[1] - p2[1];
                else 
                    return - p1[0] + p2[0];
            }
        });
        List<int[]> list = new ArrayList<>();
        for (int[] p : people) 
            list.add(p[1], p);
        // for (int i = 0; i < people.length; i++)
        //     people[i] = list.get(i);
        // return people;
        return list.toArray(new int[people.length][]);
    }
}


// use order statistics tree to insert 
// but did not impelment red black tree so it runs pretty slow 
class Solution {
    public int[][] reconstructQueue(int[][] people) {
        if (people.length == 0)
            return new int[0][];
        Arrays.sort(people, new Comparator<int[]>() {
            public int compare(int[] p1, int[] p2) {
                if (p1[0] == p2[0]) 
                    return p1[1] - p2[1];
                else 
                    return - p1[0] + p2[0];
            }
        });
        Node root = new Node(people[0], 1);
        root.left = new Node(null, 0);
        root.right = new Node(null, 0);
        Node dummy = new Node(null, 0);
        dummy.right = root;
        for (int i = 1; i < people.length; i++) {
            Node parent = dummy, node = dummy.right;
            int rank = people[i][1] + 1;
            // System.out.println(people[i][0] + " " + people[i][1]);
            if (rank == node.size + 1) {
                while (node.right.elem != null) {
                    node.size++;
                    node = node.right;
                }
                Node newnode = new Node(people[i], 1);
                newnode.right = node.right;
                newnode.left = new Node(null, 0);
                node.right = newnode;
                node.size++;
            } else {
                while (node.left.size + 1 != rank) {
                    node.size++;
                    if (node.left.size + 1 < rank) {
                        rank -= node.left.size + 1;
                        parent = node;
                        node = node.right;
                    } else {
                        parent = node;
                        node = node.left;
                    }
                }
                Node newnode = new Node(people[i], node.size + 1);
                newnode.left = node.left;
                newnode.right = node;
                node.left = new Node(null, 0);
                node.size -= newnode.left.size;
                if (node == parent.left) 
                    parent.left = newnode;
                else
                    parent.right = newnode;
            }
        }
        int[][] res = new int[people.length][];
        helper(res, dummy.right, 0);
        return res;
    }
    
    public int helper(int[][] res, Node root, int index) {
        if (root.elem == null)
            return index;
        int pos = helper(res, root.left, index);
        res[pos] = root.elem;
        return helper(res, root.right, pos+1);
    }
    
    class Node {
        int[] elem;
        int size;
        Node left, right;
        public Node(int[] elem, int size) {
            this.elem = elem;
            this.size = size;
        }
    }
}


// using binary search + bit traversal to get the correct index, so O(N * logN * logN)
class Solution {
    public int[][] reconstructQueue(int[][] people) {
        Arrays.sort(people, new Comparator<int[]>() {
            public int compare(int[] i1, int[] i2) {
                if (i1[0] != i2[0])
                    return i1[0] - i2[0];
                else
                    return i2[1] - i1[1];
            }
        });
        int[] bit = new int[people.length+1];
        int[][] res = new int[people.length][];
        for (int[] p : people) {
            int order = p[1];
            int index = helper(bit, order);
            res[index] = p;
            for (int i = index+1; i < bit.length; i += i&-i)
                bit[i]++;
        }
        // System.out.println(Arrays.toString(bit));
        return res;
    }
    
    // O(logN * logN)
    public int helper(int[] bit, int order) {
        int left = 1, right = bit.length-1;
        while (left < right) {
            int mid = (left + right) / 2;
            int sum = 0;
            for (int i = mid; i > 0; i &= i-1) 
                sum += bit[i];
            if (mid - sum <= order)
                left = mid + 1;
            else
                right = mid;
        }
        return left - 1;
    }
}


// using binary indexed tree, indeed O(N * logN) 
class Solution {
    public int[][] reconstructQueue(int[][] people) {
        Arrays.sort(people, new Comparator<int[]>() {
            public int compare(int[] i1, int[] i2) {
                if (i1[0] != i2[0])
                    return i1[0] - i2[0];
                else
                    return i2[1] - i1[1];
            }
        });
        int[] bit = new int[people.length+1];
        int[][] res = new int[people.length][];
        for (int[] p : people) {
            int order = p[1];
            int index = helper(bit, order);
            res[index] = p;
            for (int i = index+1; i < bit.length; i += i&-i)
                bit[i]++;
        }
        // System.out.println(Arrays.toString(bit));
        return res;
    }
    
    // log(N), actually constant time 
    public int helper(int[] bit, int order) {
        int res = 0, sum = 0;
        for (int i = 30; i >= 0; i--) {
            res += 1 << i;
            if (res >= bit.length || res - (sum + bit[res]) > order)
                res -= 1 << i;
            else
                sum += bit[res];
        }
        return res;
    }
}
