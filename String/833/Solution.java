class Solution {
    public String findReplaceString(String S, int[] indexes, String[] sources, String[] targets) {
        char[] ch = S.toCharArray();
        int[] record = new int[ch.length];
        for (int i = 0; i < indexes.length; i++) {
            if (S.substring(indexes[i]).indexOf(sources[i]) == 0) {
                ch[indexes[i]] = '@';
                record[indexes[i]] = i;
            }
        }
        StringBuilder sb = new StringBuilder();
        int start = 0, end = 0;
        while (end < ch.length) {
            if (ch[end] == '@') {
                sb.append(S.substring(start, end)).append(targets[record[end]]);
                start = end = end + sources[record[end]].length();
            } else {
                end++;
            }
        }
        if (start != ch.length)
            sb.append(S.substring(start, end));
        return sb.toString();
    }
}


class Solution {
    public String findReplaceString(String S, int[] indexes, String[] sources, String[] targets) {
        char[] ch = S.toCharArray();
        int[] record = new int[ch.length];
        for (int i = 0; i < indexes.length; i++) {
            if (S.substring(indexes[i]).indexOf(sources[i]) == 0) {
                ch[indexes[i]] = '@';
                record[indexes[i]] = i;
            }
        }
        StringBuilder sb = new StringBuilder();
        int start = 0, end = 0;
        while (end <= ch.length) {
            if (end == ch.length || ch[end] == '@') {
                sb.append(S.substring(start, end));
                if (end != ch.length) {
                    sb.append(targets[record[end]]);
                    start = end + sources[record[end]].length();
                    end = start - 1;
                }
            }
            end++;
        }
        return sb.toString();
    }
}


class Solution {
    public String findReplaceString(String S, int[] indexes, String[] sources, String[] targets) {
        char[] ch = S.toCharArray();
        int[] record = new int[ch.length];
        for (int i = 0; i < indexes.length; i++) {
            if (S.substring(indexes[i]).indexOf(sources[i]) == 0) {
                ch[indexes[i]] = '@';
                record[indexes[i]] = i;
            }
        }
        StringBuilder sb = new StringBuilder();
        int index = 0;
        while (index < ch.length) {
            if (ch[index] == '@') {
                sb.append(targets[record[index]]);
                index += sources[record[index]].length();
            } else {
                sb.append(ch[index++]);
            }
        }
        return sb.toString();
    }
}


class Solution {
    public String findReplaceString(String S, int[] indexes, String[] sources, String[] targets) {
        int[][] pair = new int[indexes.length][2];
        for (int i = 0; i < indexes.length; i++) {
            pair[i][0] = indexes[i];
            pair[i][1] = i;
        }
        // proceed from back to front, so indices won't be interfered 
        Arrays.sort(pair, new Comparator<int[]>() {
            public int compare(int[] i1, int[] i2) {
                return i1[0] - i2[0];
            }    
        });
        for (int i = pair.length-1; i >= 0; i--) {
            if (S.substring(pair[i][0]).indexOf(sources[pair[i][1]]) == 0)
                S = S.substring(0, pair[i][0]) + targets[pair[i][1]] + 
                        S.substring(pair[i][0] + sources[pair[i][1]].length());
        }
        return S;
    }
}


class Solution {
    public String findReplaceString(String S, int[] indexes, String[] sources, String[] targets) {
        int[] pair = new int[indexes.length];
        // use the index of indexes to record its original position 
        for (int i = 0; i < indexes.length; i++) 
            pair[i] = indexes[i] * indexes.length + i;
        Arrays.sort(pair);
        for (int i = pair.length-1; i >= 0; i--) {
            int index = pair[i] / indexes.length, origin = pair[i] % indexes.length;
            if (S.substring(index).indexOf(sources[origin]) == 0)
                S = S.substring(0, index) + targets[origin] + 
                        S.substring(index + sources[origin].length());
        }
        return S;
    }
}
