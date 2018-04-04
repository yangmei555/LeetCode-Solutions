class Solution {
    public int calPoints(String[] ops) {
        if (ops == null || ops.equals(""))
            return 0;
        Stack<Integer> stack = new Stack<>();
        int total = 0, last = 0, score = 0;
        for (String str : ops) {
            if (str.equals("C")) {
                total -= stack.pop();
            } else if (str.equals("D")) {
                stack.push(2 * stack.peek());
                total += stack.peek();
            } else if (str.equals("+")) {
                last = stack.pop();
                score = last + stack.peek();
                stack.push(last);
                stack.push(score);
                total += score;
            } else {
                score = Integer.valueOf(str);
                stack.push(score);
                total += score;
            }
        }
        return total;
    }
}


class Solution {
    public int calPoints(String[] ops) {
        if (ops == null || ops.equals(""))
            return 0;
        int[] scores = new int[ops.length];
        int i = 0, total = 0;
        for (String str : ops) {
            if (str.equals("D")) {
                scores[i] = scores[i-1] * 2;
                total += scores[i];
                i++;
            } else if (str.equals("C")) {
                i--;
                total -= scores[i];
            } else if (str.equals("+")) {
                scores[i] = scores[i-1] + scores[i-2];
                total += scores[i];
                i++;
            } else {
                scores[i] = Integer.valueOf(str);
                total += scores[i];
                i++;
            }
        }
        return total;
    }
}
