class Solution {
    Node root;
    public List<List<String>> wordSquares(String[] words) {
        root = new Node();
        for (String str : words) {
            Node cur = root;
            for (char c : str.toCharArray()) {
                if (cur.nodes[c-'a'] == null)
                    cur.nodes[c-'a'] = new Node();
                cur = cur.nodes[c-'a'];
            }
            cur.end = true;
        }
        char[][] square = new char[words[0].length()][words[0].length()];
        List<List<String>> res = new LinkedList<>();
        helper(root, square, 0, 0, res);
        return res;
    }
    
    public void helper(Node node, char[][] square, int row, int col, List<List<String>> res) {
        if (row == square.length) {
            List<String> list = new LinkedList<>();
            for (char[] ch : square)
                list.add(new String(ch));
            res.add(list);
        } else {
            if (row > col) {
                if (node.nodes[square[row][col]-'a'] == null)
                    return;
                helper(node.nodes[square[row][col]-'a'], square, row, col+1, res);
            } else {
                for (int i = 0; i < node.nodes.length; i++) {
                    if (node.nodes[i] != null) {
                        char c = (char)('a' + i);
                        square[row][col] = c;
                        square[col][row] = c;
                        if (col == square[0].length-1)
                            helper(root, square, row+1, 0, res);
                        else
                            helper(node.nodes[i], square, row, col+1, res);
                    }
                }
            }
        }
    }
    
    // since all words have exact the same length, "end" is not necessary here 
    class Node {
        Node[] nodes = new Node[26];
        boolean end;
    }
}
