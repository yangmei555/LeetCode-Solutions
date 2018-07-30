class Solution {
    public int[] advantageCount(int[] A, int[] B) {
        Node[] nodes = new Node[B.length];
        for (int i = 0; i < B.length; i++)
            nodes[i] = new Node(B[i], i);
        Arrays.sort(nodes, new Comparator<Node>() {
            public int compare(Node n1, Node n2) {
                return n1.value - n2.value;
            } 
        });
        Arrays.sort(A);
        int[] record = new int[A.length], res = new int[A.length];
        int left = 0, right = nodes.length-1;
        for (int i = 0; i < A.length; i++) {            // loop according to A
            if (A[i] > nodes[left].value)
                res[nodes[left++].index] = A[i];
            else
                res[nodes[right--].index] = A[i];
        }
        return res;
    }
    
    class Node {
        int value, index;
        public Node(int value, int index) {
            this.value = value;
            this.index = index;
        }
    }
}


class Solution {
    public int[] advantageCount(int[] A, int[] B) {
        Node[] nodes = new Node[B.length];
        for (int i = 0; i < B.length; i++)
            nodes[i] = new Node(B[i], i);
        Arrays.sort(nodes, new Comparator<Node>() {
            public int compare(Node n1, Node n2) {
                return n1.value - n2.value;
            } 
        });
        Arrays.sort(A);
        int[] res = new int[A.length];
        int left = 0, right = A.length-1;
        for (int i = nodes.length-1; i >= 0; i--) {           // loop according to B 
            if (A[right] > nodes[i].value)
                res[nodes[i].index] = A[right--];
            else
                res[nodes[i].index] = A[left++];
        }
        return res;
    }
    
    class Node {
        int value, index;
        public Node(int value, int index) {
            this.value = value;
            this.index = index;
        }
    }
}


class Solution {
    public int[] advantageCount(int[] A, int[] B) {
        // using array to record index rather than wrapper class
        int[][] nodes = new int[B.length][2]; 
        for (int i = 0; i < B.length; i++) {
            nodes[i][0] = B[i];
            nodes[i][1] = i;
        }
        Arrays.sort(nodes, new Comparator<int[]>() {
            public int compare(int[] i1, int[] i2) {
                return i1[0] - i2[0];
            } 
        });
        Arrays.sort(A);
        int[] res = new int[A.length];
        int left = 0, right = A.length-1;
        for (int i = nodes.length-1; i >= 0; i--) {
            if (A[right] > nodes[i][0])
                res[nodes[i][1]] = A[right--];
            else
                res[nodes[i][1]] = A[left++];
        }
        return res;
    }
}
