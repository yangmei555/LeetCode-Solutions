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
    public NestedInteger deserialize(String s) {
        Stack<NestedInteger> stack = new Stack<>();
        char[] ch = s.toCharArray();
        NestedInteger nest = null, next = null;
        int num = 0, sign = 1;
        for (int i = 0; i < ch.length; i++) {
            if (ch[i] == '[') {
                next = new NestedInteger();
                if (nest != null) {
                    nest.add(next);
                    stack.push(nest);
                }
                nest = next;
            } else if (ch[i] == ']' || ch[i] == ',') {
                if (ch[i-1] != ']' && ch[i-1] != '[')
                    nest.add(new NestedInteger(sign*num));
                num = 0;
                sign = 1;
                if (ch[i] == ']' && !stack.isEmpty())
                    nest = stack.pop();
            } else if (ch[i] == '-') {
                sign = -1;
            } else {
                num = num * 10 + ch[i] - '0';
            }
        }
        return nest == null ? new NestedInteger(sign*num) : nest;
    }
}


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
    public NestedInteger deserialize(String s) {
        char[] ch = s.toCharArray();
        return helper(ch, 0).res;
    }
    
    public Res helper(char[] ch, int index) {
        if (ch[index] != '[')
            return new Res(new NestedInteger(Integer.valueOf(new String(ch))), index);
        int num = 0, sign = 1;
        NestedInteger nest = new NestedInteger();
        index++;
        while (index < ch.length) {
            if (ch[index] == '[') {
                Res ret = helper(ch, index);
                nest.add(ret.res);
                index = ret.index;
            } else if (ch[index] == ',' || ch[index] == ']') {
                if (ch[index-1] != '[' && ch[index-1] != ']')
                    nest.add(new NestedInteger(sign*num));
                num = 0;
                sign = 1;
                if (ch[index] == ']') {
                    return new Res(nest, index);
                }
            } else if (ch[index] == '-') {
                sign = -1;
            } else {
                num = num * 10 + ch[index] - '0';
            }
            index++;
        }
        return new Res(nest, index);
    }
    
    class Res {
        NestedInteger res;
        int index;
        public Res(NestedInteger nest, int i) {
            res = nest;
            index = i;
        }
    }
}
