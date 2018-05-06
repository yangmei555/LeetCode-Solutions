class Solution {
    public int findBlackPixel(char[][] picture, int N) {
        if (picture.length == 0 || picture[0].length == 0)
            return 0;
        int[] row = new int[picture.length], col = new int[picture[0].length];
        Map<Integer, String> map = new HashMap<>();
        for (int i = 0; i < picture.length; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < picture[0].length; j++) {
                sb.append(picture[i][j]);
                if (picture[i][j] == 'B') {
                    row[i]++;
                    col[j]++;
                }
            }
            map.put(i, sb.toString());
        }
        int res = 0;
        for (int i = 0; i < picture.length; i++) {
            if (row[i] == N) {
                for (int j = 0; j < picture[0].length; j++) {
                    if (picture[i][j] == 'B' && col[j] == N) {
                        int k = 0;
                        for (; k < picture.length; k++) {
                            if (picture[k][j] == 'B' && !map.get(k).equals(map.get(i))) 
                                break;
                        }
                        if (k == picture.length) 
                            res += N;
                        col[j] = 0;
                    }
                }
            }
        }
        return res;
    }
}


class Solution {
    public int findBlackPixel(char[][] picture, int N) {
        if (picture.length == 0 || picture[0].length == 0)
            return 0;
        int[] row = new int[picture.length], col = new int[picture[0].length];
        for (int i = 0; i < picture.length; i++) {
            for (int j = 0; j < picture[0].length; j++) {
                if (picture[i][j] == 'B') {
                    row[i]++;
                    col[j]++;
                }
            }
        }
        int res = 0;
        for (int i = 0; i < picture.length; i++) {
            if (row[i] == N) {
                for (int j = 0; j < picture[0].length; j++) {
                    if (picture[i][j] == 'B' && col[j] == N) {
                        int k = 0;
                        for (; k < picture.length; k++) {
                            if (picture[k][j] == 'B' && !Arrays.equals(picture[i], picture[k])) 
                                break;
                        }
                        if (k == picture.length) 
                            res += N;
                        col[j] = 0;
                    }
                }
            }
        }
        return res;
    }
}


class Solution {
    public int findBlackPixel(char[][] picture, int N) {
        if (picture.length == 0 || picture[0].length == 0)
            return 0;
        int[] row = new int[picture.length], col = new int[picture[0].length];
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < picture.length; i++) {
            for (int j = 0; j < picture[0].length; j++) {
                if (picture[i][j] == 'B') {
                    row[i]++;
                    col[j]++;
                }
            }
            if (row[i] == N) {
                String str = new String(picture[i]);
                map.put(str, map.getOrDefault(str, 0) + 1);
            }
        }
        int res = 0;
        for (String str : map.keySet()) {
            if (map.get(str) == N) {
                char[] ch = str.toCharArray();
                for (int j = 0; j < ch.length; j++) {
                    if (ch[j] == 'B' && col[j] == N)
                        res += N;
                }
            }
        }
        return res;
    }
}


class Solution {
    public int findBlackPixel(char[][] picture, int N) {
        if (picture.length == 0 || picture[0].length == 0)
            return 0;
        int[] row = new int[picture.length], col = new int[picture[0].length];
        for (int i = 0; i < picture.length; i++) {
            for (int j = 0; j < picture[0].length; j++) {
                if (picture[i][j] == 'B') {
                    row[i]++;
                    col[j]++;
                }
            }
        }
        int res = 0;
        for (int i = 0; i < picture.length; i++) {
            if (row[i] == N) {
                Boolean isornot = null;
                for (int j = 0; j < picture[0].length; j++) {
                    if (picture[i][j] == 'B' && col[j] == N) {
                        if (isornot == null) {
                            int k = 0;
                            for (; k < picture.length; k++) {
                                if (picture[k][j] == 'B' && !Arrays.equals(picture[i], picture[k])) 
                                    break;
                            }
                            if (k == picture.length) {
                                res += N;
                                isornot = true;
                            } else {
                                isornot = false;
                            }
                        } else {
                            res += isornot ? N : 0;
                        }
                        col[j] = 0;
                    }
                }
            }
        }
        return res;
    }
}
