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


// 'ch' and 'stack' can point to the same area of memory , thus O(1) space 
class Solution {
    public String simplifyPath(String path) {
        char[] ch = path.toCharArray();
        char[] stack = new char[ch.length];
        int stackIndex = 0, index = 0;
        while (index < ch.length) {
            int start = index++;
            while (index < ch.length && ch[index] != '/')
                index++;
            if (index - start == 1)
                continue;
            if (index - start == 2 && ch[start+1] == '.')
                continue;
            if (index - start == 3 && ch[start+1] == '.' && ch[start+2] == '.') {
                while (stackIndex > 0 && stack[stackIndex-1] != '/')
                    stackIndex--;
                if (stackIndex != 0)
                    stackIndex--;
                continue;
            }
            while (start < index)
                stack[stackIndex++] = ch[start++];
        }
        return stackIndex == 0 ? "/" : new String(stack, 0, stackIndex);
    }
}
