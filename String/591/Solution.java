// process base on '<'
class Solution {
    public boolean isValid(String code) {
        Stack<String> stack = new Stack<>();
        StringBuilder sb = new StringBuilder();
        char[] ch = code.toCharArray();
        for (int i = 0; i < ch.length; i++) {
            if (ch[i] == '<') {
                if (stack.isEmpty() && i != 0)
                    return false;
                if (sb.length() != 0 && sb.charAt(0) == '<')
                    return false;
                sb.setLength(0);
                i++;
                boolean end = false;
                if (i < ch.length) {
                    if (ch[i] == '/') {
                        end = true;
                        i++;
                    } else if (ch[i] == '!') {
                        sb.append('<').append('!');
                        continue;
                    }
                }
                while (i < ch.length && ch[i] != '>') {
                    if (ch[i] == '<' || ch[i] < 'A' || ch[i] > 'Z')
                        return false;
                    sb.append(ch[i++]);
                }
                if (i == ch.length || sb.length() == 0 || sb.length() > 9)
                    return false;
                if (!end) {
                    stack.push(sb.toString());
                } else if (stack.isEmpty() || !stack.pop().equals(sb.toString())) {
                    return false;
                }
                sb.setLength(0);
            } else {
                if (i == 0)
                    return false;
                sb.append(ch[i]);
                if (sb.indexOf("<![CDATA[") == 0) {
                    if (stack.isEmpty())
                        return false;
                    int index = i + code.substring(i).indexOf("]]>");
                    if (index < i)
                        return false;
                    i = index + 2;
                    sb.setLength(0);
                }
            }
        }
        return stack.isEmpty() && sb.length() == 0;
    }
}


// process base on '>'
class Solution {
    public boolean isValid(String code) {
        char[] ch = code.toCharArray();
        StringBuilder sb = new StringBuilder();
        String[] stack = new String[ch.length/3];
        int stackIndex = 0;
        for (int i = 0; i < ch.length; i++) {
            if (ch[i] == '>') {
                if (sb.length() == 0 || sb.indexOf("<") == -1) {
                    sb.append('>');
                } else {
                    if (sb.indexOf("<![CDATA[") != -1) {
                        if (stackIndex == 0)
                            return false;
                        if (sb.charAt(sb.length()-2) == ']' && sb.charAt(sb.length()-1) == ']') {
                            sb.setLength(0);
                        } else {
                            sb.append('>');
                        }
                    } else {
                        String str = sb.substring(sb.indexOf("<")+1);
                        sb.setLength(0);
                        boolean end = false;
                        if (str.length() != 0 && str.charAt(0) == '/') {
                            end = true;
                            str = str.substring(1);
                        }
                        if (str.length() == 0 || str.length() > 9)
                            return false;
                        for (char c : str.toCharArray()) {
                            if (c < 'A' || c > 'Z')
                                return false;
                        }
                        if (!end) {
                            stack[stackIndex++] = str;
                        } else {
                            if (stackIndex == 0 || !stack[--stackIndex].equals(str))
                                return false;
                            if (stackIndex == 0 && i != ch.length-1)
                                return false;
                        }
                    }
                }
            } else {
                if (i == 0 && ch[i] != '<')
                    return false;
                sb.append(ch[i]);
            }
        }
        return stackIndex == 0 && sb.length() == 0;
    }
}


class Solution {
    public boolean isValid(String code) {
        if (code.length() < 2 || code.charAt(0) != '<' || code.charAt(code.length()-1) != '>')
            return false;
        Stack<String> stack = new Stack<>();
        for (int i = 0; i < code.length(); i++) {
            if (code.charAt(i) == '<') {
                if (code.charAt(i+1) == '!') {
                    if (code.indexOf("<![CDATA[", i) != i || stack.isEmpty())
                        return false;
                    int index = code.indexOf("]]>", i);
                    if (index == -1)
                        return false;
                    i = index + 2;
                } else {
                    boolean end = code.charAt(i+1) == '/';
                    int index = code.indexOf('>', i);
                    if (index == -1)
                        return false;
                    String str = code.substring(end ? i+2 : i+1, index);
                    if (!helper(str))
                        return false;
                    if (end) {
                        if (stack.isEmpty() || !stack.pop().equals(str))
                            return false;
                        if (stack.isEmpty())
                            return index+1 == code.length();
                    } else {
                        stack.push(str);
                    }
                    i = index;
                }
            }
        }
        return false;
    }
    
    public boolean helper(String str) {
        if (str.length() == 0 || str.length() > 9)
            return false;
        for (char c : str.toCharArray()) {
            if (c < 'A' || c > 'Z')
                return false;
        }
        return true;
    }
}
