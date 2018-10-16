class Solution {
    public List<int[]> getSkyline(int[][] buildings) {
        Node[] nodes = new Node[buildings.length*2];
        for (int i = 0; i < buildings.length; i++) {
            nodes[i*2] = new Node(buildings[i][0], buildings[i][2], true);
            nodes[i*2+1] = new Node(buildings[i][1], buildings[i][2], false);
        }
        Arrays.sort(nodes, new Comparator<Node>() {
            public int compare(Node n1, Node n2) {
                return n1.x - n2.x;
            }
        });
        TreeMap<Integer, Integer> map = new TreeMap<>();
        map.put(0, 1);
        List<int[]> res = new LinkedList<>();
        int index = 0, preMax = 0;
        while (index < nodes.length) {
            int start = index;
            while (index < nodes.length && nodes[start].x == nodes[index].x) {
                int count = map.getOrDefault(nodes[index].y, 0);
                if (nodes[index].left) 
                    map.put(nodes[index].y, count+1);
                else if (count != 1)
                    map.put(nodes[index].y, count-1);
                else
                    map.remove(nodes[index].y);
                index++;
            }
            int max = map.lastKey();
            if (preMax != max) {
                res.add(new int[]{nodes[start].x, max});
                preMax = max;
            }
        }
        return res;
    }
    
    class Node {
        int x, y;
        boolean left;
        public Node(int x, int y, boolean left) {
            this.x = x;
            this.y = y;
            this.left = left;
        }
    }
}
