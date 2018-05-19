class Solution {
    public List<String> removeComments(String[] source) {
        List<String> res = new LinkedList<>();
        int index = 0;
        StringBuilder cand = new StringBuilder();
        for (int i = 0; i < source.length; i++) {
            if ((index = source[i].indexOf("//")) != -1 && 
                (source[i].indexOf("/*") == -1 || source[i].indexOf("/*") > index)) {
                if (cand.length() != 0) {
                    res.add(cand.append(source[i].substring(0, index)).toString());
                    cand.setLength(0);
                } else {
                    if (index == 0)
                        continue;
                    else 
                        res.add(source[i].substring(0, index));
                }
            } else if ((index = source[i].indexOf("/*")) != -1) {
                int index2 = 0, start = i;
                if ((index2 = source[i].indexOf("*/", index+2)) == -1) {
                    i++;
                    while (i < source.length && (index2 = source[i].indexOf("*/")) == -1)
                        i++;
                }
                if (index == 0 && index2 == source[i].length()-2)
                    continue;
                else {
                    cand.append(source[start].substring(0, index));
                    source[i] = source[i].substring(index2+2);
                    i--;
                }
            } else {
                if (cand.length() == 0)
                    res.add(source[i]); 
                else {
                    res.add(cand.append(source[i]).toString());
                    cand.setLength(0);
                }
            }
        }
        return res;
    }
}
