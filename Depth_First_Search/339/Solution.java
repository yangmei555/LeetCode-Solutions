/**
 * // This is the interface that allows for creating nested lists.
 * // You should not implement it, or speculate about its implementation
 * public interface NestedInteger {
 *     // Constructor initializes an empty nested list.
 *     public NestedInteger();
 *
 *     // Constructor initializes a single integer.
 *     public NestedInteger(int value);
 *
 *     // @return true if this NestedInteger holds a single integer, rather than a nested list.
 *     public boolean isInteger();
 *
 *     // @return the single integer that this NestedInteger holds, if it holds a single integer
 *     // Return null if this NestedInteger holds a nested list
 *     public Integer getInteger();
 *
 *     // Set this NestedInteger to hold a single integer.
 *     public void setInteger(int value);
 *
 *     // Set this NestedInteger to hold a nested list and adds a nested integer to it.
 *     public void add(NestedInteger ni);
 *
 *     // @return the nested list that this NestedInteger holds, if it holds a nested list
 *     // Return null if this NestedInteger holds a single integer
 *     public List<NestedInteger> getList();
 * }
 */
class Solution {
    public int depthSum(List<NestedInteger> nestedList) {
        int res = 0;
        for (NestedInteger nest : nestedList)
            res += helper(nest, 1);
        return res;
    }
    
    public int helper(NestedInteger ni, int depth) {
        if (ni.isInteger())
            return ni.getInteger() * depth;
        int res = 0;
        for (NestedInteger nest: ni.getList()) {
            res += helper(nest, depth + 1);
        }
        return res;
    }
}


class Solution {
    public int depthSum(List<NestedInteger> nestedList) {
        return helper(nestedList, 1);
    }
    
    public int helper(List<NestedInteger> list, int depth) {
        int res = 0;
        for (NestedInteger nest: list) {
            if (nest.isInteger())
                res += nest.getInteger() * depth;
            else 
                res += helper(nest.getList(), depth + 1);
        }
        return res;
    }
}
