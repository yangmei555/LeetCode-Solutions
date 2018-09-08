class Solution {
    public List<Integer> lexicalOrder(int n) {
        List<Integer> res = new LinkedList<>();
        helper(1, n, res);
        return res;
    }
    
    public void helper(int x, int bound, List<Integer> res) {
        for (int i = 0; i < (x == 1 ? 9 : 10); i++) {
            if (x + i > bound)
                break;
            res.add(x + i);
            helper((x + i) * 10, bound, res);
        }
    }
}


// standard pre order traversal style 
class Solution {
    public List<Integer> lexicalOrder(int n) {
        List<Integer> res = new LinkedList<>();
        for (int i = 1; i < 10; i++)
            helper(i, n, res);
        return res;
    }
    
    public void helper(int root, int n, List<Integer> res) {
        if (root > n)
            return;
        res.add(root);
        if (root * 10 <= n) {
            for (int i = 0; i < 10; i++)
                helper(root * 10 + i, n, res);
        }
    }
}


class Solution {
    public List<Integer> lexicalOrder(int n) {
        List<Integer> res = new LinkedList<>();
        int[] stack = new int[n];
        int index = 0;
        stack[index++] = 1;
        while (index != 0) {
            int x = stack[--index];
            res.add(x);
            if (x + 1 <= n  && (x + 1) % 10 != 0)
                stack[index++] = x + 1;
            if (x * 10 <= n)
                stack[index++] = x * 10;
        }
        return res;
    }
}


// O(1) space solution 
class Solution {
    public List<Integer> lexicalOrder(int n) {
        List<Integer> res = new LinkedList<>();
        int cur = 1;
        while (res.size() != n) {
            res.add(cur);
            if (cur * 10 <= n) {
                cur *= 10;
            } else if (cur + 1 <= n && cur % 10 != 9) {
                cur += 1;
            } else {
                cur /= 10;
                while (cur % 10 == 9)
                    cur /= 10;
                cur++;
            }
        }
        return res;
    }
}
