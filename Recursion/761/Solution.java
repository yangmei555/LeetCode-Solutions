// failed lots of times before passing . still I cannot fully figure out the run time details 
// I get this approach by chance 
class Solution {
    
    Map<String, String> map = new HashMap<>();
    
    public String makeLargestSpecial(String S) {
        String origin = S;
        if (map.containsKey(origin))
            return map.get(origin);
        for (int i = 1; i < S.length(); i++) {
            if (S.charAt(i) == '1') {
                int count = 1, start = i, j = i+1;
                List<String> list = new LinkedList<>();
                while (j < S.length()) {
                    if (S.charAt(j) == '1')
                        count++;
                    else
                        count--;
                    j++;
                    if (count == 0) {
                        String str = makeLargestSpecial(S.substring(start, j));
                        list.add(str);
                        start = j;
                    } else if (count < 0) {
                        break;
                    }
                }
                if (count <= 0) {
                    Collections.sort(list, new Comparator<String>() {
                        public int compare(String s1, String s2) {
                            return (s2 + s1).compareTo(s1 + s2);
                        }
                    });
                    StringBuilder sb = new StringBuilder();
                    for (String l : list)
                        sb.append(l);
                    S = S.substring(0, i) + sb.toString() + S.substring(count == 0 ? j : j - 1);
                    i = j - 1;
                }
            }
        }
        // reorganize the whole string 
        int count = 0, start = 0;
        List<String> list = new LinkedList<>();
        for (int i = 0; i < S.length(); i++) {
            if (S.charAt(i) == '1') 
                count++;
            else
                count--;
            if (count == 0) {
                list.add(S.substring(start, i+1));
                start = i+1;
            }
        }
        Collections.sort(list, new Comparator<String>() {
            public int compare(String s1, String s2) {
                return (s2 + s1).compareTo(s1 + s2);
            }
        });
        StringBuilder sb = new StringBuilder();
        for (String l : list)
            sb.append(l);
        S = S.compareTo(sb.toString()) > 0 ? S : sb.toString();
        map.put(origin, S);
        return S;
    }   
}
