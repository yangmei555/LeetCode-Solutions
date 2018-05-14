class Solution {
    public int numRabbits(int[] answers) {
        if (answers.length == 0)
            return 0;
        Map<Integer, Integer> map = new HashMap<>();
        int res = 0;
        for (int a : answers) {
            int count = map.getOrDefault(a, 0);
            if (count == a) {
                res += a + 1;
                count = -1;
            }
            map.put(a, count+1);
        }
        for (int k : map.keySet())
            if (map.get(k) != 0)
                res += k+1;
        return res;
    }
}


class Solution {
    public int numRabbits(int[] answers) {
        if (answers.length == 0)
            return 0;
        int[] map = new int[1000];
        int res = 0;
        for (int a : answers) {
            if (map[a] == a) {
                res += a + 1;
                map[a] = -1;
            }
            map[a]++;
        }
        for (int i = 0; i < map.length; i++)
            if (map[i] != 0)
                res += i + 1;
        return res;
    }
}


class Solution {
    public int numRabbits(int[] answers) {
        if (answers.length == 0)
            return 0;
        int[] map = new int[1000];
        int res = 0;
        for (int a : answers) {
            if (map[a] == 0) {
                res += a + 1;
                map[a]++;
            } else if (map[a] == a + 1) {
                res += a + 1;
                map[a] = 1;
            } else {
                map[a]++;
            }
        }
        return res;
    }
}
