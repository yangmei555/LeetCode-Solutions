class Solution {
    public String simplifyPath(String path) {
        char[] ch = path.toCharArray();
        int i = 0, start = 0;
        Stack<String> stack = new Stack<>();
        while (i <= ch.length) {
            while (i < ch.length && ch[i] != '/')
                i++;
            if (start != i) {
                String str = path.substring(start, i);
                if (!str.equals("..") && !str.equals(".")) {
                    stack.push(str);
                } else if (str.equals("..") && !stack.isEmpty()) {
                    stack.pop();
                }
            } 
            start = i + 1;
            i++;
        }
        if (stack.isEmpty())
            return "/";
        StringBuilder sb = new StringBuilder();
        for (String str : stack)
            sb.append("/" + str);
        return sb.toString();
    }
}


class Solution {
    public String simplifyPath(String path) {
        char[] ch = path.toCharArray();
        int i = 0, start = 0;
        Deque<String> deque = new ArrayDeque<>();
        while (i <= ch.length) {
            while (i < ch.length && ch[i] != '/')
                i++;
            if (start != i) {
                String str = path.substring(start, i);
                if (!str.equals("..") && !str.equals(".")) {
                    deque.offer(str);
                } else if (str.equals("..") && !deque.isEmpty()) {
                    deque.pollLast();
                }
            } 
            start = i + 1;
            i++;
        }
        if (deque.isEmpty())
            return "/";
        StringBuilder sb = new StringBuilder();
        while (!deque.isEmpty())
            sb.append("/" + deque.poll());
        return sb.toString();
    }
}


class Solution {
    public String simplifyPath(String path) {
        String[] str = path.split("/");
        int[] stack = new int[str.length];
        int index = 0;
        for (int i = 0; i < str.length; i++) {
            String s = str[i];
            if (s.equals(""))
                continue;
            if (!s.equals(".") && !s.equals("..")) {
                stack[index] = i;
                index++;
            } else if (s.equals("..") && index != 0) {
                index--;
            }
        }
        if (index == 0)
            return "/";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < index; i++)
            sb.append("/" + str[stack[i]]);
        return sb.toString();
    }
}
