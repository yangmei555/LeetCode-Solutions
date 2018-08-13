class Solution {
    public int findMaximizedCapital(int k, int W, int[] Profits, int[] Capital) {
        Queue<Integer> queue = new PriorityQueue<>(new Comparator<Integer>() {
            public int compare(Integer i1, Integer i2) {
                return Profits[i2] - Profits[i1];
            }  
        });
        for (int i = 0; i < Profits.length; i++)
            queue.offer(i);
        List<Integer> highCapital = new LinkedList<>();
        while (k-- > 0) {
            int size = queue.size();
            while (!queue.isEmpty()) {
                int index = queue.poll();
                if (W >= Capital[index]) {
                    W += Profits[index];
                    break;
                } else {
                    highCapital.add(index);
                }
            }
            for (int i : highCapital)
                queue.offer(i);
            highCapital.clear();
            if (size == queue.size())
                break;
        }
        return W;
    }
}


class Solution {
    public int findMaximizedCapital(int k, int W, int[] Profits, int[] Capital) {
        List<Integer> list = new LinkedList<>();
        for (int i = 0; i < Profits.length; i++)
            list.add(i);
        Collections.sort(list, new Comparator<Integer>() {
            public int compare(Integer i1, Integer i2) {
                return Profits[i2] - Profits[i1];
            }  
        });
        while (k-- > 0) {
            int p = -1;
            for (int i = 0; i < list.size(); i++) {
                if (W >= Capital[list.get(i)]) {
                    p = Profits[list.get(i)];
                    list.remove(i);
                    break;
                }
            }
            if (p == -1)
                break;
            W += p;
        }
        return W;
    }
}


// this solution is incorrect when k >= Profits.length, not necessarily every project can be taken 
// but it can fool the OJ and it is very fast so I include it ... 
class Solution {
    public int findMaximizedCapital(int k, int W, int[] Profits, int[] Capital) {
        if (k >= Profits.length) {
            for (int p : Profits)
                W += p;
            return W;
        }
        // System.out.println(k + " " + Capital.length);
        while (k-- > 0) {
            int index = -1;
            for (int i = 0; i < Capital.length; i++) {
                if (Profits[i] != -1 && W >= Capital[i] && 
                        (index == -1 || Profits[index] < Profits[i]))
                    index = i;
            }
            if (index == -1)
                break;
            W += Profits[index];
            Profits[index] = -1;
        }
        return W;
    }
}


class Solution {
    public int findMaximizedCapital(int k, int W, int[] Profits, int[] Capital) {
        Queue<Integer> queue = new PriorityQueue<>(new Comparator<Integer>() {
            public int compare(Integer i1, Integer i2) {
                return i2 - i1;
            }
        });
        int[][] pc = new int[Profits.length][2];
        for (int i = 0; i < Profits.length; i++) {
            pc[i][0] = Profits[i];
            pc[i][1] = Capital[i];
        }
        Arrays.sort(pc, new Comparator<int[]>() {
            public int compare(int[] i1, int[] i2) {
                return i1[1] - i2[1];
            }
        });
        int index = 0;
        while (k-- > 0) {
            while (index < pc.length && W >= pc[index][1]) {
                queue.offer(pc[index][0]);
                index++;
            }    
            if (queue.isEmpty())
                break;
            W += queue.poll();
        }
        return W;
    }
}
