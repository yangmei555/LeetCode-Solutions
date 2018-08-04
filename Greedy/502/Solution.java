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
