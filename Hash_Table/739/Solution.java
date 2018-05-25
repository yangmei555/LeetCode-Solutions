class Solution {
    public int[] dailyTemperatures(int[] temperatures) {
        int[] temp = new int[temperatures.length];
        int[] index = new int[temperatures.length];
        int len = 0;
        int[] res = new int[temperatures.length];
        for (int i = 0; i < temperatures.length; i++) {
            while (len >= 1 && temp[len-1] < temperatures[i]) {
                res[index[len-1]] = i - index[len-1];
                len--;
            }
            temp[len] = temperatures[i];
            index[len++] = i;
        }
        return res;
    }
}


class Solution {
    public int[] dailyTemperatures(int[] temperatures) {
        int[] index = new int[temperatures.length];
        int len = 0;
        int[] res = new int[temperatures.length];
        for (int i = 0; i < temperatures.length; i++) {
            while (len >= 1 && temperatures[index[len-1]] < temperatures[i]) {
                res[index[len-1]] = i - index[len-1];
                len--;
            }
            index[len++] = i;
        }
        return res;
    }
}


class Solution {
    public int[] dailyTemperatures(int[] temperatures) {
        int[] map = new int[101];
        int[] res = new int[temperatures.length];
        for (int i = temperatures.length-1; i >= 0; i--) {
            res[i] = Integer.MAX_VALUE;
            for (int j = temperatures[i] + 1; j <= 100; j++) 
                if (map[j] > i && map[j] - i < res[i])
                    res[i] = map[j] - i;
            if (res[i] == Integer.MAX_VALUE)
                res[i] = 0;
            map[temperatures[i]] = i;
        }
        return res;
    }
}
