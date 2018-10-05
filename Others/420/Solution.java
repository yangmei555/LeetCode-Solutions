// it has ever driven me mad ... 
class Solution {
    public int strongPasswordChecker(String s) {
        char[] ch = s.toCharArray();
        int[] map = new int[128];
        boolean lower = false, upper = false, digit = false;
        int index = 0, dup = 0, remove = 0;
        while (index < ch.length) {
            if (ch[index] >= 'a' && ch[index] <= 'z')
                lower = true;
            else if (ch[index] >= 'A' && ch[index] <= 'Z')
                upper = true;
            else if (ch[index] >= '0' && ch[index] <= '9')
                digit = true;
            int start = index++;
            while (index < ch.length && ch[start] == ch[index])
                index++;
            dup += (index - start) / 3;
            remove += Math.max(0, index - start - 2);
        }
        int need = (lower ? 0 : 1) + (upper ? 0 : 1) + (digit ? 0 : 1);
        int res = 0;
        if (ch.length < 6) {
            res = Math.max(need, dup);
            int added = Math.max(0, need);
            if (ch.length + added < 6)
                res += 6 - ch.length - added;
        } else if (ch.length <= 20) {
            res = Math.max(need, dup);
        } else {
            if (ch.length - 20 > remove) {
                res = ch.length - 20 + need;
            } else {
                index = 0;
                int origin = ch.length - 20;
                remove = ch.length - 20;
                dup = 0;
                List<Integer> list = new LinkedList<>();
                while (index < ch.length) {
                    int start = index++;
                    while (index < ch.length && ch[start] == ch[index])
                        index++;
                    if (index - start >= 3) 
                        list.add(index - start);
                }
                // Collections.sort(list, new Comparator<Integer>() {
                //     public int compare(Integer i1, Integer i2) {
                //         return i1 % 3 - i2 % 3;
                //     }
                // });
                index = 0;
                while (remove != 0) {
                    if (list.get(index) >= 3) {
                        if (list.get(index) % 3 == 1 && remove >= 2) {
                            list.set(index, list.get(index)-2);
                            remove -= 2;
                        } else if (list.get(index) % 3 == 2 && remove >= 3) {
                            list.set(index, list.get(index)-3);
                            remove -= 3;
                        } else if (list.get(index) % 3 == 0) {
                            list.set(index, list.get(index)-1);
                            remove -= 1;
                        } else if (list.size() == 1) {
                            list.set(index, list.get(index)-1);
                            remove -= 1;
                        }
                    }
                    index = (index + 1) % list.size();
                }
                for (int l : list)
                    dup += l / 3;
                res = origin + Math.max(dup, need);
            }
        }
        return res;
    }
}
