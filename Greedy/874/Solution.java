class Solution {
    public int robotSim(int[] commands, int[][] obstacles) {
        Map<Integer, List<Integer>> obsx = new HashMap<>();
        Map<Integer, List<Integer>> obsy = new HashMap<>();
        for (int[] o : obstacles) {
            obsx.putIfAbsent(o[0], new LinkedList<>());
            obsy.putIfAbsent(o[1], new LinkedList<>());
            obsx.get(o[0]).add(o[1]);
            obsy.get(o[1]).add(o[0]);
        }
        for (int k : obsx.keySet()) 
            Collections.sort(obsx.get(k));
        for (int k : obsy.keySet()) 
            Collections.sort(obsy.get(k));
        int dir = 0, posx = 0, posy = 0, res = 0;
        for (int c : commands) {
            if (c != -1 && c != -2) {
                if (dir == 0) {
                    if (obsx.containsKey(posx)) {
                        int index = Collections.binarySearch(obsx.get(posx), posy);
                        if (index < 0) {
                            index = -index - 1;
                            if (index != obsx.get(posx).size())
                                c = Math.min(c, obsx.get(posx).get(index) - 1 - posy);
                        }
                    }
                    posy = posy + c;
                } else if (dir == 1) {
                    if (obsy.containsKey(posy)) {
                        int index = Collections.binarySearch(obsy.get(posy), posx);
                        if (index < 0) {
                            index = -index - 1;
                            if (index != obsy.get(posy).size())
                                c = Math.min(c, obsy.get(posy).get(index) - 1 - posx);
                        }
                    }
                    posx = posx + c;
                } else if (dir == 2) {
                    if (obsx.containsKey(posx)) {
                        int temp = c;
                        int index = Collections.binarySearch(obsx.get(posx), posy - c);
                        if (index < 0)
                            index = -index - 1;
                        if (index != obsx.get(posx).size())
                            c = Math.min(c, posy - obsx.get(posx).get(index) - 1);
                        if (c < 0)
                            c = temp;
                    }
                    posy = posy - c;
                } else {
                    if (obsy.containsKey(posy)) {
                        int temp = c;
                        int index = Collections.binarySearch(obsy.get(posy), posx - c);
                        if (index < 0)
                            index = -index - 1;
                        if (index != obsy.get(posy).size())
                            c = Math.min(c, posx - obsy.get(posy).get(index) - 1);
                        if (c < 0)
                            c = temp;
                    }
                    posx = posx - c;
                }
                res = Math.max(res, posx * posx + posy * posy);
            } else {
                if (dir == 0) 
                    dir = c == -1 ? 1 : 3;
                else if (dir == 1)
                    dir = c == -1 ? 2 : 0;
                else if (dir == 2)
                    dir = c == -1 ? 3 : 1;
                else
                    dir = c == -1 ? 0 : 2;
            }
        }
        return res;
    }
}


class Solution {
    public int robotSim(int[] commands, int[][] obstacles) {
        int dir = 0, posx = 0, posy = 0, res = 0;
        for (int c : commands) {
            if (c != -1 && c != -2) {
                if (dir == 0) {
                    int nexty = posy + c;
                    for (int[] o : obstacles) {
                        if (o[0] == posx && o[1] >= posy + 1 && o[1] <= nexty)
                            nexty = o[1] - 1;
                    }
                    posy = nexty;
                } else if (dir == 1) {
                    int nextx = posx + c;
                    for (int[] o : obstacles) {
                        if (o[1] == posy && o[0] >= posx + 1 && o[0] <= nextx)
                            nextx = o[0] - 1;
                    }
                    posx = nextx;
                } else if (dir == 2) {
                    int nexty = posy - c;
                    for (int[] o : obstacles) {
                        if (o[0] == posx && o[1] >= nexty && o[1] <= posy - 1)
                            nexty = o[1] + 1;
                    }
                    posy = nexty;
                } else {
                    int nextx = posx - c;
                    for (int[] o : obstacles) {
                        if (o[1] == posy && o[0] >= nextx && o[0] <= posx - 1)
                            nextx = o[0] + 1;
                    }
                    posx = nextx;
                }
                res = Math.max(res, posx * posx + posy * posy);
            } else {
                if (dir == 0) 
                    dir = c == -1 ? 1 : 3;
                else if (dir == 1)
                    dir = c == -1 ? 2 : 0;
                else if (dir == 2)
                    dir = c == -1 ? 3 : 1;
                else
                    dir = c == -1 ? 0 : 2;
            }
        }
        return res;
    }
}
