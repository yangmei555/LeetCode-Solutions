/*
// Definition for a QuadTree node.
class Node {
    public boolean val;
    public boolean isLeaf;
    public Node topLeft;
    public Node topRight;
    public Node bottomLeft;
    public Node bottomRight;

    public Node() {}

    public Node(boolean _val,boolean _isLeaf,Node _topLeft,Node _topRight,
                                                Node _bottomLeft,Node _bottomRight) {
        val = _val;
        isLeaf = _isLeaf;
        topLeft = _topLeft;
        topRight = _topRight;
        bottomLeft = _bottomLeft;
        bottomRight = _bottomRight;
    }
};
*/
class Solution {
    public Node construct(int[][] grid) {
        return helper(grid, 0, grid.length-1, 0, grid.length-1);
    }
    
    public Node helper(int[][] grid, int rstart, int rend, int cstart, int cend) {
        if (rstart == rend && cstart == cend) {
            return new Node(grid[rstart][cstart] == 1 ? true : false, true, null, null, null, null);
        } else {
            Node n1 = helper(grid, rstart, (rstart + rend) / 2, cstart, (cstart + cend) / 2);
            Node n2 = helper(grid, rstart, (rstart + rend) / 2, (cstart + cend) / 2 + 1, cend);
            Node n3 = helper(grid, (rstart + rend) / 2 + 1, rend, cstart, (cstart + cend) / 2);
            Node n4 = helper(grid, (rstart + rend) / 2 + 1, rend, (cstart + cend) / 2 + 1, cend);
            if (n1.isLeaf && n2.isLeaf && n3.isLeaf && n4.isLeaf && 
                n1.val == n2.val && n2.val == n3.val && n3.val == n4.val)
                return new Node(n1.val, true, null, null, null, null);
            else
                return new Node(false, false, n1, n2, n3, n4);
        }
    }
}
