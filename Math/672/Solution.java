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
