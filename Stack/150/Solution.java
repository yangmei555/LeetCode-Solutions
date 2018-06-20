class Solution {
    public int evalRPN(String[] tokens) {
        int[] stack = new int[tokens.length];
        int index = 0;
        for (int i = 0; i < tokens.length; i++) {
            if (tokens[i].equals("+")) {
                stack[index-2] = stack[index-2] + stack[index-1];
                index--;
            } else if (tokens[i].equals("-")) {
                stack[index-2] = stack[index-2] - stack[index-1];
                index--;
            } else if (tokens[i].equals("*")) {
                stack[index-2] = stack[index-2] * stack[index-1];
                index--;
            } else if (tokens[i].equals("/")) {
                stack[index-2] = stack[index-2] / stack[index-1];
                index--;
            } else {
                stack[index++] = Integer.parseInt(tokens[i]);
            }
        }
        return stack[index-1];
    }
}
