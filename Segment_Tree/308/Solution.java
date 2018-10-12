class NumMatrix {
    
    Node root;
    public NumMatrix(int[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0)
            root = null;
        else
            root = build(matrix, 0, matrix.length-1, 0, matrix[0].length-1);
    }
    
    public Node build(int[][] matrix, int row1, int row2, int col1, int col2) {
        if (row1 > row2 || col1 > col2)
            return new Node(row1, row2, col1, col2, 0);
        Node root = new Node(row1, row2, col1, col2, matrix[row1][col1]);
        if (row1 < row2 || col1 < col2) {
            int mid1 = (row1 + row2) / 2;
            int mid2 = (col1 + col2) / 2;
            root.leftup = build(matrix, row1, mid1, col1, mid2);
            root.leftdown = build(matrix, mid1+1, row2, col1, mid2);
            root.rightup = build(matrix, row1, mid1, mid2+1, col2);
            root.rightdown = build(matrix, mid1+1, row2, mid2+1, col2);
            root.sum = root.leftup.sum + root.leftdown.sum + root.rightup.sum + root.rightdown.sum;
        }
        return root;
    }
    
    public void update(int row, int col, int val) {
        update(root, row, col, val);
    }
    
    public void update(Node root, int row, int col, int val) {
        if (root.row1 == root.row2 && root.col1 == root.col2) {
            root.sum = val;
        } else if (root.leftup.row2 >= row) {
            if (root.leftup.col2 >= col)
                update(root.leftup, row, col, val);
            else
                update(root.rightup, row, col, val);
            root.sum = root.leftup.sum + root.leftdown.sum + root.rightup.sum + root.rightdown.sum;
        } else {
            if (root.leftdown.col2 >= col)
                update(root.leftdown, row, col, val);
            else
                update(root.rightdown, row, col, val);
            root.sum = root.leftup.sum + root.leftdown.sum + root.rightup.sum + root.rightdown.sum;
        }
    }
    
    public int sumRegion(int row1, int col1, int row2, int col2) {
        return getSum(root, row1, row2, col1, col2);
    }
    
    public int getSum(Node root, int row1, int row2, int col1, int col2) {
        if (root.row1 == row1 && root.row2 == row2 && root.col1 == col1 && root.col2 == col2) {
            return root.sum;
        } else if (root.leftup.row2 >= row2) {
            if (root.leftup.col2 >= col2)
                return getSum(root.leftup, row1, row2, col1, col2);
            else if (root.rightup.col1 <= col1)
                return getSum(root.rightup, row1, row2, col1, col2);
            else
                return getSum(root.leftup, row1, row2, col1, root.leftup.col2) + 
                        getSum(root.rightup, row1, row2, root.rightup.col1, col2);
        } else if (root.leftdown.row1 <= row1) {
            if (root.leftdown.col2 >= col2)
                return getSum(root.leftdown, row1, row2, col1, col2);
            else if (root.rightdown.col1 <= col1)
                return getSum(root.rightdown, row1, row2, col1, col2);
            else
                return getSum(root.leftdown, row1, row2, col1, root.leftdown.col2) + 
                        getSum(root.rightdown, row1, row2, root.rightdown.col1, col2);
        } else if (root.leftup.col2 >= col2) {
            return getSum(root.leftup, row1, root.leftup.row2, col1, col2) + 
                    getSum(root.leftdown, root.leftdown.row1, row2, col1, col2);
        } else if (root.rightup.col1 <= col1) {
            return getSum(root.rightup, row1, root.rightup.row2, col1, col2) + 
                    getSum(root.rightdown, root.rightdown.row1, row2, col1, col2);
        } else {
            return getSum(root.leftup, row1, root.leftup.row2, col1, root.leftup.col2) + 
                    getSum(root.leftdown, root.leftdown.row1, row2, col1, root.leftdown.col2) + 
                    getSum(root.rightup, row1, root.rightup.row2, root.rightup.col1, col2) + 
                    getSum(root.rightdown, root.rightdown.row1, row2, root.rightdown.col1, col2);
        }
    }
    
    class Node {
        int sum, row1, row2, col1, col2;
        Node leftup, leftdown, rightup, rightdown;
        public Node(int row1, int row2, int col1, int col2, int sum) {
            this.row1 = row1;
            this.row2 = row2;
            this.col1 = col1;
            this.col2 = col2;
            this.sum = sum;
        }
    }
}

/**
 * Your NumMatrix object will be instantiated and called as such:
 * NumMatrix obj = new NumMatrix(matrix);
 * obj.update(row,col,val);
 * int param_2 = obj.sumRegion(row1,col1,row2,col2);
 */
