/**
 * // This is the robot's control interface.
 * // You should not implement it, or speculate about its implementation
 * interface Robot {
 *     // Returns true if the cell in front is open and robot moves into the cell.
 *     // Returns false if the cell in front is blocked and robot stays in the current cell.
 *     public boolean move();
 *
 *     // Robot will stay in the same cell after calling turnLeft/turnRight.
 *     // Each turn will be 90 degrees.
 *     public void turnLeft();
 *     public void turnRight();
 *
 *     // Clean the current cell.
 *     public void clean();
 * }
 */
class Solution {
    public void cleanRoom(Robot robot) {
        Set<Point> set = new HashSet<>();
        Point point = new Point(0, 0);
        helper(robot, 0, 0, "", set);
    }
    
    public void helper(Robot robot, int x, int y, String in, Set<Point> set) {
        if (!set.add(new Point(x, y))) {
            robot.turnLeft();
            robot.turnLeft();
            robot.move();
            return;
        }
        robot.clean();
        if (in.equals("left")) {
            robot.turnRight();
        } else if (in.equals("down")) {
            robot.turnRight();
            robot.turnRight();
        } else if (in.equals("right")) {
            robot.turnLeft();
        }
        if (robot.move()) {
            helper(robot, x, y+1, "up", set);
            robot.turnLeft();
            robot.turnLeft();
        }
        robot.turnLeft();
        if (robot.move()) {
            helper(robot, x-1, y, "left", set);
            robot.turnLeft();
            robot.turnLeft();
        }
        robot.turnLeft();
        if (robot.move()) {
            helper(robot, x, y-1, "down", set);
            robot.turnLeft();
            robot.turnLeft();
        }
        robot.turnLeft();
        if (robot.move()) {
            helper(robot, x+1, y, "right", set);
            robot.turnLeft();
            robot.turnLeft();
        }
        robot.turnLeft();
        if (in.equals("up")) {
            robot.turnLeft();
            robot.turnLeft();
        } else if (in.equals("left")) {
            robot.turnRight();
        } else if (in.equals("right")) {
            robot.turnLeft();
        }
        robot.move();
    }
    
    class Point {
        int x, y;
        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
        public int hashCode() {
            return x * 31 + y;
        }
        public boolean equals(Object obj) {
            Point p = (Point)obj;
            return x == p.x && y == p.y;
        }
    }
}
