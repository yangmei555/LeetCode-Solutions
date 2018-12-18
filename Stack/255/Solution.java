// a verbose version 
class Solution {
    public boolean verifyPreorder(int[] preorder) {
        if (preorder.length == 0)
            return true;
        Stack<Integer> stack = new Stack<>();
        int pre = Integer.MIN_VALUE, index = 0;
        stack.push(preorder[index++]);
        while (index < preorder.length) {
            if (stack.peek() > preorder[index]) {
                if (preorder[index] < pre)
                    return false;
            } else {
                while (!stack.isEmpty() && stack.peek() < preorder[index])
                    pre = stack.pop();
            }
            stack.push(preorder[index++]);
        }
        return true;
    }
}


class Solution {
    public boolean verifyPreorder(int[] preorder) {
        int[] stack = new int[preorder.length];
        int index = 0, min = Integer.MIN_VALUE;
        for (int p : preorder) {
            if (p < min)
                return false;
            while (index > 0 && p > stack[index-1]) 
                min = stack[--index];
            stack[index++] = p;
        }
        return true;
    }
}


// so-called contant space, need to modify the original array
class Solution {
    public boolean verifyPreorder(int[] preorder) {
        int index = 0, min = Integer.MIN_VALUE;
        for (int p : preorder) {
            if (p < min)
                return false;
            while (index > 0 && p > preorder[index-1]) 
                min = preorder[--index];
            preorder[index++] = p;
        }
        return true;
    }
}


class Solution {
    public boolean verifyPreorder(int[] preorder) {
        return helper(preorder, 0, preorder.length-1);
    }
    
    public boolean helper(int[] preorder, int left, int right) {
        if (left >= right)
            return true;
        int root = preorder[left], index = left + 1;
        while (index <= right && root > preorder[index])
            index++;
        for (int i = index; i <= right; i++) {
            if (root > preorder[i])
                return false;
        }
        return helper(preorder, left+1, index-1) && helper(preorder, index, right);
    }
}
