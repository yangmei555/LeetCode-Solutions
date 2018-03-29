class Solution {
    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        if (flowerbed == null || flowerbed.length == 0)
            return false;
        if (n == 0)
            return true;
        boolean disable = false;
        int num = 0;
        for (int i = 0; i < flowerbed.length; i++) {
            if (flowerbed[i] == 1) {
                if (disable == true)
                    num--;
                else 
                    disable = true;
            } else {
                if (disable == false) {
                    num++;
                    if (i + 1 > flowerbed.length - 1 || flowerbed[i+1] == 0) {
                        if (num >= n)
                            return true;
                    }
                    disable = true;
                } else 
                    disable = false;
            }
        }
        
        return false;
    }
}
