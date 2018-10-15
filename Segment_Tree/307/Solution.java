class NumArray {
    
    int[] sum;
    public NumArray(int[] nums) {
        sum = new int[nums.length+1];
        for (int i = 1; i < sum.length; i++)
            sum[i] = sum[i-1] + nums[i-1];
    }
    
    public void update(int i, int val) {
        int num = sum[i+1] - sum[i];
        for (int j = i+1; j < sum.length; j++)
            sum[j] += val - num;
    }
    
    public int sumRange(int i, int j) {
        return sum[j+1] - sum[i];
    }
}

/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray obj = new NumArray(nums);
 * obj.update(i,val);
 * int param_2 = obj.sumRange(i,j);
 */


// segment tree ! 
class NumArray {
    
    Node root;
    public NumArray(int[] nums) {
        root = build(nums, 0, nums.length-1);
    }
    
    public Node build(int[] nums, int start, int end) {
        if (start > end)
            return null;
        Node root = new Node(start, end, nums[start]);
        if (start != end) {
            int mid = (start + end) / 2;
            root.left = build(nums, start, mid);
            root.right = build(nums, mid+1, end);
            root.sum = root.left.sum + root.right.sum;
        }
        return root;
    }
    
    public void update(int i, int val) {
        update(root, i, val);
    }
    
    public void update(Node node, int index, int val) {
        if (node.start == node.end) {
            node.sum = val;
        } else {
            if (node.left.end >= index)
                update(node.left, index, val);
            else
                update(node.right, index, val);
            node.sum = node.left.sum + node.right.sum;
        }
    }
    
    public int sumRange(int i, int j) {
        return getSum(root, i, j);
    }
    
    public int getSum(Node node, int i, int j) {
        if (node.start == i && node.end == j) {
            return node.sum;
        } else {
            if (node.left.end >= j)
                return getSum(node.left, i, j);
            else if (node.right.start <= i)
                return getSum(node.right, i, j);
            else
                return getSum(node.left, i, node.left.end) + 
                        getSum(node.right, node.right.start, j);
        }
    }
    
    class Node {
        int sum, start, end;
        Node left, right;
        public Node(int start, int end, int sum) {
            this.start = start;
            this.end = end;
            this.sum = sum;
        }
    }
}

/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray obj = new NumArray(nums);
 * obj.update(i,val);
 * int param_2 = obj.sumRange(i,j);
 */


// use array to implement segment tree 
class NumArray {
    
    int[] tree;
    public NumArray(int[] nums) {
        tree = new int[nums.length*2];
        for (int i = 0; i < nums.length; i++)
            tree[nums.length+i] = nums[i];
        for (int i = nums.length-1; i > 0; i--)
            tree[i] = tree[2*i] + tree[2*i+1];
    }
    
    public void update(int i, int val) {
        int index = tree.length/2 + i;
        int origin = tree[index];
        while (index != 0) {
            tree[index] += val - origin;
            index /= 2;
        }
    }
    
    public int sumRange(int i, int j) {
        int res = 0;
        i += tree.length/2;
        j += tree.length/2;
        while (i <= j) {
            if (i % 2 == 1) {
                res += tree[i];
                i++;
            }
            if (j % 2 == 0) {
                res += tree[j];
                j--;
            }
            i /= 2;
            j /= 2;
        }
        return res;
    }
}

/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray obj = new NumArray(nums);
 * obj.update(i,val);
 * int param_2 = obj.sumRange(i,j);
 */


// binay indexed tree ! 
class NumArray {
    
    int[] nums, tree;
    public NumArray(int[] nums) {
        this.nums = new int[nums.length];
        tree = new int[nums.length+1];
        for (int i = 0; i < nums.length; i++)
            update(i, nums[i]);
    }
    
    public void update(int i, int val) {
        int diff = val - nums[i];
        nums[i] = val;
        i++;
        while (i < tree.length) {
            tree[i] += diff;
            i += (i & -i);
        }
    }
    
    public int sumRange(int i, int j) {
        return getSum(j) - getSum(i-1);
    }
    
    public int getSum(int i) {
        i++;
        int res = 0;
        while (i > 0) {
            res += tree[i];
            i &= i-1;
        }
        return res;
    }
}

/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray obj = new NumArray(nums);
 * obj.update(i,val);
 * int param_2 = obj.sumRange(i,j);
 */


// binary indexed tree with only 1 additional array, add a "getSingle" method  
class NumArray {
    
    int[] tree;
    public NumArray(int[] nums) {
        tree = new int[nums.length+1];

        // // initialize in O(n*lgn)
        // for (int i = 0; i < nums.length; i++)
        //     update(i, nums[i]);

        // initialize in O(n) 
        for (int i = 0; i < nums.length; i++) {
            tree[i+1] += nums[i];
            int index = i+1 + (i+1 & (-i-1));
            if (index < tree.length)
                tree[index] += tree[i+1];
        }
    }
    
    public void update(int i, int val) {
        int origin = getSingle(i);
        int diff = val - origin;
        i++;
        while (i < tree.length) {
            tree[i] += diff;
            i += (i & -i);
        }
    }
    
    public int getSingle(int i) {
        int res = tree[++i], index = (i & i-1);
        i--;
        while (i != index) {
            res -= tree[i];
            i &= i-1;
        }
        return res;
    }
    
    public int sumRange(int i, int j) {
        return getSum(j) - getSum(i-1);
    }
    
    public int getSum(int i) {
        i++;
        int res = 0;
        while (i > 0) {
            res += tree[i];
            i &= i-1;
        }
        return res;
    }
}

/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray obj = new NumArray(nums);
 * obj.update(i,val);
 * int param_2 = obj.sumRange(i,j);
 */
