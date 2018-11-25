class Solution {
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        int[] stack = new int[pushed.length];
        int index = 0, pIndex = 0;
        for (int i = 0; i < pushed.length; i++) {
            stack[index++] = pushed[i];
            while (index != 0 && stack[index-1] == popped[pIndex]) {
                index--;
                pIndex++;
            }
        }
        return pIndex == popped.length;
    }
}


// for allowing duplicate elements   
// can partition based on the first pushed element or the last popped element 
class Solution {
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        return helper(pushed, 0, pushed.length-1, popped, 0, popped.length-1);
    }
    
    // need a set to record [s1, e1, s2, e2] to avoid overlapping subproblem 
    public boolean helper(int[] pushed, int s1, int e1, int[] popped, int s2, int e2) {
        if (s1 > e1)
            return true;
        if (s1 == e1)
            return pushed[s1] == popped[s2];
        int first = pushed[s1];
        for (int i = s2; i <= e2; i++) {
            if (first == popped[i]) {
                if (helper(pushed, s1+1, i-s2+s1, popped, s2, i-1) && 
                    helper(pushed, i+1-e2+e1, e1, popped, i+1, e2))
                    return true;
            }
        }
        return false;
    }
}
