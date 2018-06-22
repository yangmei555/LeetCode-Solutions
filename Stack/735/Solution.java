class Solution {
    public int[] asteroidCollision(int[] asteroids) {
        int[] res = new int[asteroids.length];
        int index = 0;
        for (int a : asteroids) {
            if (index == 0 || a > 0) {
                res[index++] = a;
            } else {
                while (index != 0 && res[index-1] > 0 && res[index-1] < -a)
                    index--;
                if (index != 0 && res[index-1] == -a) {
                    index--;
                } else if (index == 0 || res[index-1] < 0) {
                    res[index++] = a;
                }
            }
        
        }
        return Arrays.copyOf(res, index);
    }
}
