class Solution {
    public String reorganizeString(String S) {
        char[] ch = S.toCharArray();
        int[] map = new int[26];
        char max = 'a';
        for (char c : ch) {
            map[c-'a']++;
            if (map[c-'a'] > map[max-'a'])
                max = c;
        }
        StringBuilder[] sb = new StringBuilder[map[max-'a']];
        for (int i = 0; i < sb.length; i++) {
            sb[i] = new StringBuilder();
            sb[i].append(max);
        }
        int index = 0;
        for (char c : ch) {
            if (c == max)
                continue;
            int origin = index;
            while (sb[index].charAt(sb[index].length()-1) == c) {
                index = (index + 1) % sb.length;
                if (index == origin)
                    return "";
            }
            sb[index].append(c);
            index = (index + 1) % sb.length;
        }
        for (int i = 1; i < sb.length; i++) {
            if (sb[0].charAt(sb[0].length()-1) == sb[i].charAt(0))
                return "";
            sb[0].append(sb[i]);
        }
        return sb[0].toString();
    }
}


class Solution {
    public String reorganizeString(String S) {
        char[] ch = S.toCharArray();
        int[] map = new int[26];
        int max = 0;
        Set<Character> set = new HashSet<>();
        for (char c : ch) {
            map[c-'a']++;
            if (map[c-'a'] > max) {
                max = map[c-'a'];
                set.clear();
                set.add(c);
            } else if (map[c-'a'] == max) {
                set.add(c);
            }
        }
        if (set.size() == 1 && max-1 > ch.length-max)
            return "";
        StringBuilder temp = new StringBuilder();
        for (char c : set)
            temp.append(c);
        StringBuilder[] sb = new StringBuilder[max];
        for (int i = 0; i < sb.length; i++) 
            sb[i] = new StringBuilder(temp.toString());
        int index = 0;
        for (char c : ch) {
            if (set.contains(c))
                continue;
            while (sb[index].charAt(sb[index].length()-1) == c)
                index = (index + 1) % sb.length;
            sb[index].append(c);
            index = (index + 1) % sb.length;
        }
        for (int i = 1; i < sb.length; i++) 
            sb[0].append(sb[i]);
        return sb[0].toString();
    }
}


class Solution {
    public String reorganizeString(String S) {
        char[] ch = S.toCharArray();
        int[] map = new int[26];
        char max = 'a';
        for (char c : ch) {
            map[c-'a']++;
            if (map[c-'a'] * 2 > ch.length + 1)
                return "";
            if (map[c-'a'] > map[max-'a'])
                max = c;
        }
        StringBuilder[] sb = new StringBuilder[map[max-'a']];
        for (int i = 0; i < sb.length; i++) {
            sb[i] = new StringBuilder();
            sb[i].append(max);
        }
        int index = 0;
        for (int i = 0; i < map.length; i++) {
            char c = (char)('a' + i);
            if (c == max)
                continue;
            while (map[i] > 0) {
                sb[index].append(c);
                index = (index + 1) % sb.length;
                map[i]--;
            }
        }
        for (int i = 1; i < sb.length; i++) 
            sb[0].append(sb[i]);
        return sb[0].toString();
    }
}


class Solution {
    public String reorganizeString(String S) {
        char[] ch = S.toCharArray();
        int[] map = new int[26];
        for (char c : ch) {
            map[c-'a']++;
            if (map[c-'a'] * 2 > ch.length + 1)
                return "";
        }
        Queue<Node> queue = new PriorityQueue<>(new Comparator<Node>() {
            public int compare(Node n1, Node n2) {
                if (n1.num == n2.num)
                    return n1.c - n2.c;
                else
                    return n2.num - n1.num;
            }
        });
        for (int i = 0; i < map.length; i++) {
            if (map[i] != 0)
                queue.offer(new Node((char)('a' + i), map[i]));
        }
        StringBuilder sb = new StringBuilder();
        while (!queue.isEmpty()) {
            Node n1 = queue.poll();
            // if (sb.length() != 0 && sb.charAt(sb.length()-1) == n1.c)
            //     return "";
            sb.append(n1.c);
            n1.num--;
            if (!queue.isEmpty()) {
                Node n2 = queue.poll();
                // if (sb.length() != 0 && sb.charAt(sb.length()-1) == n2.c)
                //     return "";
                sb.append(n2.c);
                n2.num--;
                if (n2.num != 0)
                    queue.offer(n2);
            }
            if (n1.num != 0)
                queue.offer(n1);
        }
        return sb.toString();
    }
    
    class Node {
        char c;
        int num;
        public Node(char c, int num) {
            this.c = c;
            this.num = num;
        }
    }
}


class Solution {
    public String reorganizeString(String S) {
        char[] ch = S.toCharArray();
        int[] map = new int[26];
        for (char c : ch) {
            map[c-'a']++;
            if (map[c-'a'] * 2 > ch.length + 1)
                return "";
        }
        for (int i = 0; i < map.length; i++)
            map[i] = map[i] * 26 + i;
        Arrays.sort(map);
        int index = 0;
        for (int i = map.length-1; i >= 0; i--) {
            int m = map[i];
            char c = (char)('a' + m % 26);
            int count = m / 26;
            for (int j = 0; j < count; j++) {
                ch[index] = c;
                index += 2;
                if (index >= ch.length)
                    index = 1;
            }
        }
        return new String(ch);
    }
}


class Solution {
    public String reorganizeString(String S) {
        char[] ch = S.toCharArray();
        int[] map = new int[26];
        char max = 'a';
        for (char c : ch) {
            map[c-'a']++;
            if (map[c-'a'] * 2 > ch.length + 1)
                return "";
            if (map[c-'a'] > map[max-'a'])
                max = c;
        }
        int index = 0;
        while (map[max-'a']-- != 0) {
            ch[index] = max;
            index += 2;
        }
        for (int i = 0; i < map.length; i++) {
            int m = map[i];
            char c = (char)('a' + i);
            for (int j = 0; j < m; j++) {
                if (index >= ch.length)
                    index = 1;
                ch[index] = c;
                index += 2;
            }
        }
        return new String(ch);
    }
}
