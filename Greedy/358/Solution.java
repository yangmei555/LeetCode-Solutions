// this solution is wrong but it can pass the OJ . 
// this solution does not sort the map array. the chars except the max frequency ones 
// should be processed in descending frequency order .
// eg. s = "aaaaabbbbccddddeeeeff" , k = 5 , 
// this solution produces "abcdeabcdeabdefabdefa", but 'd' and 'e' fail to meet the requirement 
class Solution {
    public String rearrangeString(String s, int k) {
        char[] ch = s.toCharArray();
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
        int availableSlots = (max - 1) * (k - set.size());
        int totalNeed = ch.length - max * set.size();
        if (availableSlots > totalNeed)
            return "";
        StringBuilder temp = new StringBuilder();
        for (char c : set)
            temp.append(c);
        StringBuilder[] sb = new StringBuilder[max];
        for (int i = 0; i < sb.length; i++) 
            sb[i] = new StringBuilder(temp.toString());
        int index = 0;
        for (int i = 0; i < map.length; i++) {
            char c = (char)('a' + i);
            if (set.contains(c))
                continue;
            while (map[i]-- > 0) {
                sb[index].append(c);
                index = (index + 1) % (sb.length-1);
            }
        }
        for (int i = 1; i < sb.length; i++) 
            sb[0].append(sb[i]);
        return sb[0].toString();
    }
}


// this is the bug free solution . process the alphabet according to sorded order 
class Solution {
    public String rearrangeString(String s, int k) {
        char[] ch = s.toCharArray();
        int[] freq = new int[26];
        for (char c : ch) 
            freq[c-'a']++;
        for (int i = 0; i < freq.length; i++)
            freq[i] = freq[i] * freq.length + i;
        Arrays.sort(freq);
        StringBuilder[] sbs = new StringBuilder[freq[freq.length-1]/freq.length];
        sbs[0] = new StringBuilder();
        int start = -1, len = freq.length;
        for (int i = len-1; i >= 0; i--) {
            if (freq[i] / len == freq[len-1] / len)
                sbs[0].append((char)(freq[i] % len + 'a'));
            else {
                start = i;
                break;
            }
        }
        for (int i = 1; i < sbs.length; i++)
            sbs[i] = new StringBuilder(sbs[0]);
        int index = 0;
        for (int i = start; i >= 0; i--) {
            for (int j = 0; j < freq[i] / len; j++) {
                sbs[index++].append((char)(freq[i] % len + 'a'));
                if (index == sbs.length-1)
                    index = 0;
            }
        }
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < sbs.length; i++) {
            if (i != sbs.length-1 && sbs[i].length() < k)
                return "";
            res.append(sbs[i].toString());
        }
        return res.toString();
    }
}


// record the earliest moment each char can be used 
// in each round, choose the char with max frequency and available to use 
class Solution {
    public String rearrangeString(String s, int k) {
        char[] ch = s.toCharArray();
        int[] freq = new int[26], firstExe = new int[26];
        for (char c : ch)
            freq[c-'a']++;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < ch.length; i++) {
            int max = 0, index = -1;
            for (int j = 0; j < freq.length; j++) {
                if (freq[j] > max && firstExe[j] <= i) {
                    max = freq[j];
                    index = j;
                }
            }
            if (index == -1)
                return "";
            sb.append((char)('a'+index));
            freq[index]--;
            firstExe[index] = i+k;
        }
        return sb.toString();
    }
}


// use 1 priority queue and 1 ordinary wait queue 
class Solution {
    public String rearrangeString(String s, int k) {
        if (k <= 1)
            return s;
        Node[] nodes = new Node[26];
        for (int i = 0; i < nodes.length; i++)
            nodes[i] = new Node((char)('a'+i), 0);
        char[] ch = s.toCharArray();
        for (char c : ch)
            nodes[c-'a'].freq++;
        Queue<Node> queue1 = new PriorityQueue<>(new Comparator<Node>() {
            public int compare(Node n1, Node n2) {
                return n2.freq - n1.freq;
            }    
        });
        Queue<Node> queue2 = new LinkedList<>();
        for (Node n : nodes) {
            if (n.freq != 0)
                queue1.offer(n);
        }
        StringBuilder sb = new StringBuilder();
        while (sb.length() < ch.length) {
            if (queue1.isEmpty())
                return "";
            Node node = queue1.poll();
            sb.append(node.c);
            node.freq--;
            queue2.offer(node);
            if (queue2.size() == k) {
                Node n = queue2.poll();
                if (n.freq != 0)
                    queue1.offer(n);
            }
        }
        return sb.toString();
    }
    
    class Node {
        char c;
        int freq;
        public Node(char c, int freq) {
            this.c = c;
            this.freq = freq;
        }
    }
}


// use 1 temp list 
class Solution {
    public String rearrangeString(String s, int k) {
        if (k <= 1)
            return s;
        Node[] nodes = new Node[26];
        for (int i = 0; i < nodes.length; i++)
            nodes[i] = new Node((char)('a'+i), 0);
        char[] ch = s.toCharArray();
        for (char c : ch)
            nodes[c-'a'].freq++;
        Queue<Node> queue = new PriorityQueue<>(new Comparator<Node>() {
            public int compare(Node n1, Node n2) {
                if (n1.freq == n2.freq)
                    return n1.c - n2.c;
                else
                    return n2.freq - n1.freq;
            }    
        });
        for (Node n : nodes) {
            if (n.freq != 0)
                queue.offer(n);
        }
        StringBuilder sb = new StringBuilder();
        while (sb.length() < ch.length) {
            List<Node> temp = new LinkedList<>();
            int turn = Math.min(k, ch.length-sb.length());
            for (int i = 0; i < turn; i++) {
                if (queue.isEmpty())
                    return "";
                Node node = queue.poll();
                sb.append(node.c);
                node.freq--;
                temp.add(node);
            }
            for (Node n : temp) {
                if (n.freq != 0)
                    queue.offer(n);
            }
        }
        return sb.toString();
    }
    
    class Node {
        char c;
        int freq;
        public Node(char c, int freq) {
            this.c = c;
            this.freq = freq;
        }
    }
}
