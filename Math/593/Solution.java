class Solution {
    public boolean validSquare(int[] p1, int[] p2, int[] p3, int[] p4) {
        if (p1[0]+p2[0]==p3[0]+p4[0] && p1[1]+p2[1]==p3[1]+p4[1]) {
            int x1 = p2[0]-p1[0], y1 = p2[1]-p1[1];
            int x2 = p4[0]-p3[0], y2 = p4[1]-p3[1];
            if (x1*x2 + y1*y2 != 0)
                return false;
            if (x1*x1+y1*y1 != x2*x2+y2*y2 || x1*x1+y1*y1 == 0)
                return false;
            return true;
        } else if (p1[0]+p3[0]==p2[0]+p4[0] && p1[1]+p3[1]==p2[1]+p4[1]) {
            int x1 = p3[0]-p1[0], y1 = p3[1]-p1[1];
            int x2 = p4[0]-p2[0], y2 = p4[1]-p2[1];
            if (x1*x2 + y1*y2 != 0)
                return false;
            if (x1*x1+y1*y1 != x2*x2+y2*y2 || x1*x1+y1*y1 == 0)
                return false;
            return true;
        } else if (p1[0]+p4[0]==p2[0]+p3[0] && p1[1]+p4[1]==p2[1]+p3[1]) {
            int x1 = p4[0]-p1[0], y1 = p4[1]-p1[1];
            int x2 = p3[0]-p2[0], y2 = p3[1]-p2[1];
            if (x1*x2 + y1*y2 != 0)
                return false;
            if (x1*x1+y1*y1 != x2*x2+y2*y2 || x1*x1+y1*y1 == 0)
                return false;
            return true;
        } else {
            return false;
        }
    }
}
