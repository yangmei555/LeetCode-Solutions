class Solution {
    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        char[] ch = s.toCharArray();
        int[] count = new int[128];
        int res = 0, left = 0, right = 0, dist = 0;
        while (right < ch.length) {
            if (count[ch[right]] == 0)
                dist++;
            count[ch[right]]++;
            if (dist > k) {
                while (--count[ch[left]] != 0)
                    left++;
                left++;
                dist--;
            }
            res = Math.max(res, right-left+1);
            right++;
        }
        return res;
    }
}


// use a treemap to record the left most valid char position 
class Solution {
    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        char[] ch = s.toCharArray();
        Map<Character, Integer> map1 = new HashMap<>();
        TreeMap<Integer, Character> map2 = new TreeMap<>();
        int res = 0, left = 0, right = 0;
        while (right < ch.length) {
            if (map1.containsKey(ch[right]))
                map2.remove(map1.get(ch[right]));
            map1.put(ch[right], right);
            map2.put(right, ch[right]);
            if (map1.size() > k) {
                int key = map2.firstKey();
                char c = map2.get(key);
                map2.remove(key);
                map1.remove(c);
                left = key+1;
            }
            res = Math.max(res, right-left+1);
            right++;
        }
        return res;
    }
}


// no need to use tree map, tree set is enough 
class Solution {
    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        char[] ch = s.toCharArray();
        Map<Character, Integer> map = new HashMap<>();
        TreeSet<Integer> set = new TreeSet<>();
        int res = 0, left = 0, right = 0;
        while (right < ch.length) {
            if (map.containsKey(ch[right]))
                set.remove(map.get(ch[right]));
            map.put(ch[right], right);
            set.add(right);
            if (map.size() > k) {
                int key = set.first();
                char c = ch[key];
                set.remove(key);
                map.remove(c);
                left = key+1;
            }
            res = Math.max(res, right-left+1);
            right++;
        }
        return res;
    }
}
