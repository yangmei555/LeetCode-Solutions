// notice that the building and searching can be processed parallelly 
// but building needs to precede searching, because for the first number the trie is empty  
class Solution {
    public int findMaximumXOR(int[] nums) {
        Node root = new Node();
        int res = 0;
        for (int n : nums) {
            Node cur = root, search = root;
            int offset = 31, cand = 0;
            while (offset >= 0) {
                int bit = ((1 << offset) & n) == 0 ? 0 : 1;
                if (cur.digits[bit] == null)
                    cur.digits[bit] = new Node();
                cur = cur.digits[bit];
                if (search.digits[bit ^ 1] == null) {
                    search = search.digits[bit];
                } else {
                    search = search.digits[bit ^ 1];
                    cand |= (1 << offset);
                }
                offset--;
            }
            res = Math.max(res, cand);
        }
        return res;
    }
    
    class Node {
        Node[] digits = new Node[2];
    }
}
