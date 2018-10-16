// nearly brute force 
class Solution {
    public int rectangleArea(int[][] rectangles) {
        int mod = 1000000007;
        Line[] lines = new Line[rectangles.length*2];
        for (int i = 0; i < rectangles.length; i++) {
            lines[i*2] = new Line(rectangles[i][0], rectangles[i][1], rectangles[i][3], true);
            lines[i*2+1] = new Line(rectangles[i][2], rectangles[i][1], rectangles[i][3], false);
        }
        Arrays.sort(lines, new Comparator<Line>() {
            public int compare(Line l1, Line l2) {
                return l1.x - l2.x;
            }
        });
        TreeMap<Line, Integer> map = new TreeMap<>(new Comparator<Line>() {
            public int compare(Line l1, Line l2) {
                if (l1.y1 == l2.y1)
                    return l1.y2 - l2.y2;
                else 
                    return l1.y1 - l2.y1;
            }
        });
        long res = 0;
        int index = 0, preX = 0;
        while (index < lines.length) {
            if (!map.isEmpty()) {
                int start = -1, end = -1, span = 0;
                for (Line line : map.keySet()) {
                    if (start != -1) {
                        if (end < line.y1) {
                            span += end - start;
                            start = line.y1;
                            end = line.y2;
                        } else {
                            end = Math.max(end, line.y2);
                        }
                    } else {
                        start = line.y1;
                        end = line.y2;
                    }
                }
                span += end - start;
                res = (res + (lines[index].x - preX + 0L) * span) % mod;
            }
            int start = index;
            while (index < lines.length && lines[start].x == lines[index].x) {
                int count = map.getOrDefault(lines[index], 0);
                if (lines[index].left) 
                    map.put(lines[index], count+1);
                else if (count == 1) 
                    map.remove(lines[index]);
                else 
                    map.put(lines[index], count-1);
                index++;
            }
            preX = lines[start].x;
        }
        return (int)res;
    }
    
    class Line {
        int x, y1, y2;
        boolean left;
        public Line(int x, int y1, int y2, boolean left) {
            this.x = x;
            this.y1 = y1;
            this.y2 = y2;
            this.left = left;
        }
    }
}
