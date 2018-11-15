class SnakeGame {

    /** Initialize your data structure here.
        @param width - screen width
        @param height - screen height 
        @param food - A list of food positions
        E.g food = [[1,1], [1,0]] means the first food is positioned at [1,1], 
                                        the second is at [1,0]. */
    Set<Integer> set;
    int[][] food, dir;
    int[] head, map;
    int index, row, col;
    public SnakeGame(int width, int height, int[][] food) {
        row = height;
        col = width;
        index = 0;
        this.food = food;
        set = new LinkedHashSet<>();
        head = new int[]{0, 0};
        set.add(0);
        map = new int[26];
        map['U'-'A'] = 0;
        map['L'-'A'] = 1;
        map['R'-'A'] = 2;
        map['D'-'A'] = 3;
        dir = new int[][]{{-1, 0}, {0, -1}, {0, 1}, {1, 0}};
    }
    
    /** Moves the snake.
        @param direction - 'U' = Up, 'L' = Left, 'R' = Right, 'D' = Down 
        @return The game's score after the move. Return -1 if game over. 
        Game over when snake crosses the screen boundary or bites its body. */
    public int move(String direction) {
        int[] d = dir[map[direction.charAt(0)-'A']];
        head[0] += d[0];
        head[1] += d[1];
        if (head[0] < 0 || head[0] >= row || head[1] < 0 || head[1] >= col)
            return -1;
        int grid = head[0] * col + head[1];
        if (index < food.length && head[0] == food[index][0] && head[1] == food[index][1])
            index++;
        else
            set.remove(set.iterator().next());
        if (!set.add(grid))
            return -1;
        return set.size()-1;
    }
}

/**
 * Your SnakeGame object will be instantiated and called as such:
 * SnakeGame obj = new SnakeGame(width, height, food);
 * int param_1 = obj.move(direction);
 */
