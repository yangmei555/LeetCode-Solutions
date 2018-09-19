class Solution {
    int row1, col1, row2, col2;
    public int minArea(char[][] image, int x, int y) {
        row1 = image.length;
        row2 = -1;
        col1 = image[0].length;
        col2 = -1;
        helper(image, x, y);
        return (row2 - row1 + 1) * (col2 - col1 + 1);
    }
    
    public void helper(char[][] image, int x, int y) {
        if (x < 0 || x >= image.length || y < 0 || y >= image[0].length || image[x][y] != '1')
            return;
        row1 = row1 < x ? row1 : x;
        row2 = row2 > x ? row2 : x;
        col1 = col1 < y ? col1 : y;
        col2 = col2 > y ? col2 : y;
        image[x][y] = '2';
        helper(image, x+1, y);
        helper(image, x-1, y);
        helper(image, x, y+1);
        helper(image, x, y-1);
    }
}


// binary search for the left, right, top, bottom boundary. the boolean value means 
// whether we can find '1'  
class Solution {
    public int minArea(char[][] image, int x, int y) {
        int left = helper1(image, 0, y, true);
        int right = helper1(image, y+1, image[0].length, false);
        int up = helper2(image, 0, x, left, right, true);
        int down = helper2(image, x+1, image.length, left, right, false);
        return (right - left) * (down - up);
    }
    
    public int helper1(char[][] image, int left, int right, boolean flag) {
        while (left < right) {
            int mid = (left + right) / 2, row = 0;
            while (row < image.length && image[row][mid] != '1')
                row++;
            if ((row != image.length) == flag)
                right = mid;
            else
                left = mid + 1;
        }
        return left;
    }
    
    public int helper2(char[][] image, int left, int right, int col1, int col2, boolean flag) {
        while (left < right) {
            int mid = (left + right) / 2, col = col1;
            while (col < col2 && image[mid][col] != '1')
                col++;
            if ((col < col2) == flag)
                right = mid;
            else
                left = mid + 1;
        }
        return left;
    }
}
