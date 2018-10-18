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


// merge sort solution 
class Solution {
    public List<int[]> getSkyline(int[][] buildings) {
        return mergeSort(buildings, 0, buildings.length-1);
    }
    
    public List<int[]> mergeSort(int[][] buildings, int left, int right) {
        List<int[]> res = new LinkedList<>();
        if (left == right) {
            res.add(new int[]{buildings[left][0], buildings[left][2]});
            res.add(new int[]{buildings[left][1], 0});
        } else if (left < right) {
            int mid = (left + right) / 2;
            List<int[]> ret1 = mergeSort(buildings, left, mid);
            List<int[]> ret2 = mergeSort(buildings, mid+1, right);
            int h1 = 0, h2 = 0, preH = 0, ptr1 = 0, ptr2 = 0;
            while (ptr1 < ret1.size() || ptr2 < ret2.size()) {
                int[] next = new int[2];
                if (ptr2 == ret2.size() || ptr1 < ret1.size() && 
                            ret1.get(ptr1)[0] < ret2.get(ptr2)[0]) {
                    next[0] = ret1.get(ptr1)[0];
                    next[1] = Math.max(ret1.get(ptr1)[1], h2);
                    h1 = ret1.get(ptr1++)[1];
                } else if (ptr1 == ret1.size() || ptr2 < ret2.size() && 
                            ret1.get(ptr1)[0] > ret2.get(ptr2)[0]) {
                    next[0] = ret2.get(ptr2)[0];
                    next[1] = Math.max(h1, ret2.get(ptr2)[1]);
                    h2 = ret2.get(ptr2++)[1];
                } else {
                    // notice that when two x positions are equal it should be processed 
                    // differently 
                    next[0] = ret1.get(ptr1)[0];
                    next[1] = Math.max(ret1.get(ptr1)[1], ret2.get(ptr2)[1]);
                    h1 = ret1.get(ptr1++)[1];
                    h2 = ret2.get(ptr2++)[1];
                }
                if (next[1] != preH) {
                    res.add(next);
                    preH = next[1];
                }
            }
        }
        return res;
    }
}


// same merge sort as above, but use homemade linked list. much faster than above , beats 99% 
class Solution {
    public List<int[]> getSkyline(int[][] buildings) {
        Node node = mergeSort(buildings, 0, buildings.length-1);
        List<int[]> res = new LinkedList<>();
        while (node != null) {
            res.add(new int[]{node.x, node.y});
            node = node.next;
        }
        return res;
    }
    
    public Node mergeSort(int[][] buildings, int left, int right) {
        Node dummy = new Node(-1, -1), cur = dummy;
        if (left == right) {
            cur.next = new Node(buildings[left][0], buildings[left][2]);
            cur = cur.next;
            cur.next = new Node(buildings[left][1], 0);
        } else if (left < right) {
            int mid = (left + right) / 2;
            Node node1 = mergeSort(buildings, left, mid);
            Node node2 = mergeSort(buildings, mid+1, right);
            int h1 = 0, h2 = 0, preH = 0, ptr1 = 0, ptr2 = 0;
            while (node1 != null || node2 != null) {
                Node next;
                if (node2 == null || node1 != null && node1.x < node2.x) {
                    h1 = node1.y;
                    next = node1;
                    next.y = Math.max(node1.y, h2);
                    node1 = node1.next;
                } else if (node1 == null || node2 != null && node1.x > node2.x) {
                    h2 = node2.y;
                    next = node2;
                    next.y = Math.max(h1, node2.y);
                    node2 = node2.next;
                } else {
                    h1 = node1.y;
                    h2 = node2.y;
                    next = node1;
                    next.y = Math.max(node1.y, node2.y);
                    node1 = node1.next;
                    node2 = node2.next;
                }
                if (next.y != preH) {
                    preH = next.y;
                    cur.next = next;
                    cur = cur.next;
                }
            }
        }
        return dummy.next;
    }
    
    class Node {
        int x, y;
        Node next;
        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
