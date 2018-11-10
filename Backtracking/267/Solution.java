class Solution {
    public List<String> generatePalindromes(String s) {
        int[] counts = new int[128];
        char[] ch = s.toCharArray();
        for (char c : ch)
            counts[c]++;
        int single = 0, index = 0;
        char singlechar = 0;
        char[] chars = new char[ch.length/2];
        boolean[] used = new boolean[chars.length];
        for (int i = 0; i < counts.length; i++) {
            if (counts[i] % 2 == 1) {
                single++;
                singlechar = (char)i;
                if (single == 2)
                    return new ArrayList<>();
            }
            for (int j = 0; j < counts[i]/2; j++)
                chars[index++] = (char)i;
        }
        StringBuilder sb = new StringBuilder();
        if (single == 1)
            sb.append(singlechar);
        List<String> res = new LinkedList<>();
        helper(res, chars, used, sb);
        return res;
    }
    
    public void helper(List<String> res, char[] ch, boolean[] used, StringBuilder sb) {
        if (sb.length()/2 == ch.length) {
            res.add(sb.toString());
        } else {
            for (int i = 0; i < ch.length; i++) {
                if (used[i] || i > 0 && ch[i-1] == ch[i] && !used[i-1])
                    continue;
                used[i] = true;
                sb.insert(0, ch[i]);
                sb.append(ch[i]);
                helper(res, ch, used, sb);
                used[i] = false;
                sb.deleteCharAt(0);
                sb.deleteCharAt(sb.length()-1);
            }
        }
    }
}


class Solution {
    public List<String> generatePalindromes(String s) {
        int[] counts = new int[128];
        char[] ch = s.toCharArray();
        for (char c : ch)
            counts[c]++;
        int single = 0, index = 0;
        String singlechar = "";
        char[] chars = new char[ch.length/2];
        for (int i = 0; i < counts.length; i++) {
            if (counts[i] % 2 == 1) {
                single++;
                singlechar += (char)i;
                if (single == 2)
                    return new ArrayList<>();
            }
            for (int j = 0; j < counts[i]/2; j++)
                chars[index++] = (char)i;
        }
        List<String> res = new LinkedList<>();
        helper(res, chars, 0, singlechar);
        return res;
    }
    
    public void helper(List<String> res, char[] ch, int index, String single) {
        if (index == ch.length) {
            StringBuilder sb = new StringBuilder(new String(ch));
            res.add(sb.toString() + single + sb.reverse().toString());
        } else {
            for (int i = index; i < ch.length; i++) {
                int j = index;
                while (j < i) {
                    if (ch[j] == ch[i])
                        break;
                    j++;
                }
                if (j == i) {
                    char temp = ch[index];
                    ch[index] = ch[i];
                    ch[i] = temp;
                    helper(res, ch, index+1, single);
                    temp = ch[index];
                    ch[index] = ch[i];
                    ch[i] = temp;
                }
            }
        }
    }
}


class Solution {
    public List<String> generatePalindromes(String s) {
        int[] counts = new int[128];
        char[] ch = s.toCharArray();
        for (char c : ch)
            counts[c]++;
        int single = 0, index = 0;
        char singlechar = 0;
        char[] chars = new char[ch.length/2];
        for (int i = 0; i < counts.length; i++) {
            if (counts[i] % 2 == 1) {
                single++;
                singlechar = (char)i;
                if (single == 2)
                    return new ArrayList<>();
            }
            for (int j = 0; j < counts[i]/2; j++)
                chars[index++] = (char)i;
            counts[i] /= 2;
        }
        StringBuilder sb = new StringBuilder();
        if (single == 1)
            sb.append(singlechar);
        List<String> res = new LinkedList<>();
        helper(res, counts, sb, ch.length/2);
        return res;
    }
    
    public void helper(List<String> res, int[] counts, StringBuilder sb, int limit) {
        if (sb.length()/2 == limit) {
            res.add(sb.toString());
        } else {
            for (int i = 0; i < counts.length; i++) {
                if (counts[i] > 0) {
                    counts[i]--;
                    sb.insert(0, (char)i);
                    sb.append((char)i);
                    helper(res, counts, sb, limit);
                    counts[i]++;
                    sb.deleteCharAt(0);
                    sb.deleteCharAt(sb.length()-1);
                }
            }
        }
    }
}


// refer to the last solution of Problem 47 
class Solution {
    public List<String> generatePalindromes(String s) {
        List<String> res = new LinkedList<>();
        int[] map = new int[128];
        for (char c : s.toCharArray())
            map[c]++;
        String odd = "";
        StringBuilder half = new StringBuilder();
        for (int i = 0; i < map.length; i++) {
            if (map[i] % 2 == 1) {
                if (odd.length() == 1)
                    return res;
                odd = (char)i + "";
            } 
            for (int j = 0; j < map[i] / 2; j++)
                half.append((char)i);
        }
        helper(half.toString().toCharArray(), 0, odd, res);
        return res;
    }
    
    public void helper(char[] ch, int index, String odd, List<String> res) {
        if (index == ch.length) {
            StringBuilder sb = new StringBuilder();
            for (char c : ch)
                sb.append(c);
            sb.append(odd);
            while (--index >= 0)
                sb.append(ch[index]);
            res.add(sb.toString());
        } else {
            for (int i = index; i >= 0; i--) {
                if (i != index && ch[i] == ch[index])
                    break;
                swap(ch, i, index);
                helper(ch, index+1, odd, res);
                swap(ch, i, index);
            }
        }
    }
    
    public void swap(char[] ch, int i, int j) {
        char temp = ch[i];
        ch[i] = ch[j];
        ch[j] = temp;
    }
}
