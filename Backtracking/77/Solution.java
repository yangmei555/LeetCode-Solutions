class Solution {
    public List<List<Integer>> combine(int n, int k) {
        List<Integer> temp = new LinkedList<>();
        List<List<Integer>> res = new LinkedList<>();
        helper(res, temp, 1, n, k);
        return res;
    }
    
    public void helper(List<List<Integer>> res, List<Integer> temp, int index, int n, int k) {
        if (temp.size() == k) {
            res.add(new ArrayList<>(temp));
            return;
        }
        if (index <= n && k - temp.size() <= n + 1 - index) {
            helper(res, temp, index+1, n, k);
            temp.add(index);
            helper(res, temp, index+1, n, k);
            temp.remove(temp.size()-1);
        }
    }
}


class Solution {
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> res = new LinkedList<>();
        int[] temp = new int[k];
        helper(res, temp, 1, 0, n, k);
        return res;
    }
    
    public void helper(List<List<Integer>> res, int[] temp, int index1, int index2, int n, int k) {
        if (index2 == k) {
            List<Integer> list = new ArrayList<>(k);
            for (int t : temp)
                list.add(t);
            res.add(list);
            return;
        }
        if (index1 <= n) {
            if (k - index2 < n + 1 - index1)
                helper(res, temp, index1+1, index2, n, k);
            temp[index2] = index1;
            helper(res, temp, index1+1, index2+1, n, k);
        }
    }
}


class Solution {
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> res = new LinkedList<>();
        int[] temp = new int[k];
        helper(res, temp, 1, 0, n, k);
        return res;
    }
    
    public void helper(List<List<Integer>> res, int[] temp, int index1, int index2, int n, int k) {
        if (index2 == k) {
            List<Integer> list = new ArrayList<>(k);
            for (int t : temp)
                list.add(t);
            res.add(list);
            return;
        }
        for (int i = index1; i <= n+1-(k-index2); i++) {
            temp[index2] = i;
            helper(res, temp, i+1, index2+1, n, k);
        }
    }
}


class Solution {
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> res = new LinkedList<>();
        Integer[] temp = new Integer[k];
        helper(res, temp, 1, 0, n, k);
        return res;
    }
    
    public void helper(List<List<Integer>> res, Integer[] temp, int index1, int index2, 
                            int n, int k) {
        if (index2 == k) {
            Integer[] arr = new Integer[k];
            System.arraycopy(temp, 0, arr, 0, k);
            res.add(Arrays.asList(arr));
            return;
        }
        for (int i = index1; i <= n+1-(k-index2); i++) {
            temp[index2] = i;
            helper(res, temp, i+1, index2+1, n, k);
        }
    }
}


public class Solution {
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> res = new LinkedList<>();
        if (k == 0) {
            List<Integer> list = new ArrayList<>();
            res.add(list);
            return res;
        } else if (n == k) {
            List<Integer> list = new ArrayList<>();
            for (int i = 1; i <= k; i++)
                list.add(i);
            res.add(list);
            return res;
        }
        res = combine(n-1, k-1);
        for (List<Integer> list : res) 
            list.add(n);
        res.addAll(combine(n-1, k));
        return res;
    }
}
