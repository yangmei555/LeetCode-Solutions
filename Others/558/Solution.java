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
    public Node intersect(Node quadTree1, Node quadTree2) {
        if (quadTree1.isLeaf || quadTree2.isLeaf) {
            if (quadTree1.isLeaf && quadTree1.val || quadTree2.isLeaf && !quadTree2.val)
                return quadTree1;
            else
                return quadTree2;
        } else {
            Node node1 = intersect(quadTree1.topLeft, quadTree2.topLeft);
            Node node2 = intersect(quadTree1.topRight, quadTree2.topRight);
            Node node3 = intersect(quadTree1.bottomLeft, quadTree2.bottomLeft);
            Node node4 = intersect(quadTree1.bottomRight, quadTree2.bottomRight);
            if (node1.isLeaf && node2.isLeaf && node3.isLeaf && node4.isLeaf && 
                node1.val == node2.val && node2.val == node3.val && node3.val == node4.val)
                return new Node(node1.val, true, null, null, null, null);
            else
                return new Node(false, false, node1, node2, node3, node4);
        }
    }
}
