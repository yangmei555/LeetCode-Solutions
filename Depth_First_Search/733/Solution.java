class Solution {
    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        int[][] visited = new int[image.length][image[0].length];
        
        helper(image, visited, sr, sc, image[sr][sc], newColor);
        image[sr][sc] = newColor;
        return image;
    }
    
    public void helper(int[][] image, int[][] visited, int r, int c, int oldColor, int newColor) {
        int row = image.length;
        int col = image[0].length;
        if (r < 0 || r >= row || c < 0 || c >= col || visited[r][c] == 1 || image[r][c] != oldColor)
            return;
        visited[r][c] = 1;
        image[r][c] = newColor;
        helper(image, visited, r - 1, c, oldColor, newColor);
        helper(image, visited, r + 1, c, oldColor, newColor);
        helper(image, visited, r, c + 1, oldColor, newColor);
        helper(image, visited, r, c - 1, oldColor, newColor);
    }
}


class Solution {
    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        if (image[sr][sc] == newColor)
            return image;
        helper(image, sr, sc, image[sr][sc], newColor);
        return image;
    }
    
    public void helper(int[][] image, int r, int c, int oldColor, int newColor) {
        int row = image.length;
        int col = image[0].length;
        if (r < 0 || r >= row || c < 0 || c >= col || image[r][c] != oldColor)
            return;
        image[r][c] = newColor;
        helper(image, r - 1, c, oldColor, newColor);
        helper(image, r + 1, c, oldColor, newColor);
        helper(image, r, c + 1, oldColor, newColor);
        helper(image, r, c - 1, oldColor, newColor);
    }
}
