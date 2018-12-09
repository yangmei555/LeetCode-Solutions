class Solution {
    public int flipLights(int n, int m) {
        if (n >= 4) {
            if (m >= 3)
                return 8;
            else if (m == 2)
                return 7;
            else if (m == 1)
                return 4;
            else
                return 1;
        } else {
            if (n == 3) 
                if (m == 0)
                    return 1;
                else if (m == 1)
                    return 4;
                else if (m == 2)
                    return 7;
                else
                    return 8;
            else if (n == 2)
                if (m == 0)
                    return 1;
                else if (m == 1)
                    return 3;
                else
                    return 4;
            else if (n == 1)
                return m > 0 ? 2 : 1;
            else 
                return 0;
        }
        
    }
}


// a universe solution, notice that the number of times pressing each button can be denoted by 
// the parity of the number of times, so there are only 16 possibilities, 0000 ~ 1111. 
class Solution {
    public int flipLights(int n, int m) {
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < 16; i++) {
            int bits = 0, buttons = i;
            while (buttons != 0) {
                bits++;
                buttons &= buttons-1;
            }
            if (bits % 2 == m % 2 && bits <= m) {
                int state = 0;
                if ((i & (1 << 0)) != 0)
                    state ^= 0b111111 >> Math.max(0, 6-n);
                if ((i & (1 << 1)) != 0)
                    state ^= 0b010101 >> Math.max(0, 6-n);
                if ((i & (1 << 2)) != 0)
                    state ^= 0b101010 >> Math.max(0, 6-n);
                if ((i & (1 << 3)) != 0)
                    state ^= 0b100100 >> Math.max(0, 6-n);
                set.add(state);
            }
        }
        return set.size();
    }
}
