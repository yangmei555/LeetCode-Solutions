class ExamRoom {
    
    int N;
    TreeSet<int[]> set;
    Map<Integer, int[]> map;
    public ExamRoom(int N) {
        this.N = N;
        set = new TreeSet<>(new Comparator<int[]>() {
            // compare according to the suitable maximal distance the interval can bring 
            public int compare(int[] i1, int[] i2) {
                int dist1 = i1[0] == -1 || i1[1] == N ? i1[1] - i1[0] - 1 : (i1[1] - i1[0]) / 2;
                int dist2 = i2[0] == -1 || i2[1] == N ? i2[1] - i2[0] - 1 : (i2[1] - i2[0]) / 2;
                if (dist1 == dist2) {
                    return i2[0] - i1[0];
                } else {
                    return dist1 - dist2;
                }
            }
        });
        map = new HashMap<>();
        set.add(new int[]{-1, N});
    }
    
    public int seat() {
        int[] interval = set.last();
        int res = -1;
        if (interval[0] == -1) {
            res = 0;
        } else if (interval[1] == N) {
            res = N-1;
        } else {
            res = (interval[0] + interval[1]) / 2;
        }
        map.put(res, new int[]{interval[0], interval[1]});
        if (interval[0] != -1) {
            int[] pre = map.get(interval[0]);
            pre[1] = res;
        }
        if (interval[1] != N) {
            int[] next = map.get(interval[1]);
            next[0] = res;
        }
        set.remove(interval);
        set.add(new int[]{interval[0], res});
        set.add(new int[]{res, interval[1]});
        return res;
    }
    
    public void leave(int p) {
        int[] span = map.get(p);
        if (span[0] != -1) {
            int[] pre = map.get(span[0]);
            pre[1] = span[1];
        }
        if (span[1] != N) {
            int[] next = map.get(span[1]);
            next[0] = span[0];
        }
        map.remove(p);
        set.remove(new int[]{span[0], p});
        set.remove(new int[]{p, span[1]});
        set.add(new int[]{span[0], span[1]});
    }
}

/**
 * Your ExamRoom object will be instantiated and called as such:
 * ExamRoom obj = new ExamRoom(N);
 * int param_1 = obj.seat();
 * obj.leave(p);
 */
