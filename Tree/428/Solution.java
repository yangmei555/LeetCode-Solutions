/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> children;

    public Node() {}

    public Node(int _val,List<Node> _children) {
        val = _val;
        children = _children;
    }
};
*/
class Codec {

    // Encodes a tree to a single string.
    public String serialize(Node root) {
        StringBuilder sb = new StringBuilder();
        helper1(root, sb);
        return sb.toString();
    }
    
    // use '#' as the terminator of the children list 
    public void helper1(Node root, StringBuilder sb) {
        if (root != null) {
            sb.append(root.val);
            for (Node node : root.children) {
                sb.append(',');
                helper1(node, sb);
            }
            sb.append(",");
        }
        sb.append("#");
    }

    // Decodes your encoded data to tree.
    int index;
    public Node deserialize(String data) {
        String[] strs = data.split(",");
        index = 0;
        return helper2(strs);
    }
    
    public Node helper2(String[] strs) {
        if (strs[index].equals("#")) {
            index++;
            return null;
        }
        List<Node> children = new LinkedList<>();
        Node root = new Node(Integer.parseInt(strs[index++]), children);
        Node node = null;
        while ((node = helper2(strs)) != null)
            root.children.add(node);
        return root;
    }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));


// BFS version using queue 
class Codec {

    // Encodes a tree to a single string.
    public String serialize(Node root) {
        StringBuilder sb = new StringBuilder();
        Queue<Node> queue = new LinkedList<>();
        if (root != null) {
            sb.append(root.val);
            queue.offer(root);
        } 
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            for (Node child : node.children) {
                sb.append(',').append(child.val);
                queue.offer(child);
            }
            sb.append(",#");
        }
        return sb.toString();
    }

    // Decodes your encoded data to tree.
    public Node deserialize(String data) {
        if (data.length() == 0)
            return null;
        String[] strs = data.split(",");
        int index = 0;
        Node root = new Node(Integer.valueOf(strs[index++]), new LinkedList<>());
        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);
        while (index < strs.length) {
            Node node = queue.poll();
            while (!strs[index].equals("#")) {
                Node child = new Node(Integer.valueOf(strs[index++]), new LinkedList<>());
                node.children.add(child);
                queue.offer(child);
            }
            index++;
        }
        return root;
    }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));


// use the size of the children list to iterate 
class Codec {

    // Encodes a tree to a single string.
    public String serialize(Node root) {
        StringBuilder sb = new StringBuilder();
        if (root != null) {
            sb.append(root.val).append(',').append(root.children.size());
            for (Node node : root.children)
                sb.append(',').append(serialize(node));
        }
        return sb.toString();
    }

    // Decodes your encoded data to tree.
    int index;
    public Node deserialize(String data) {
        if (data.length() == 0)
            return null;
        String[] strs = data.split(",");
        index = 0;
        return helper2(strs);
    }
    
    public Node helper2(String[] strs) {
        Node root = new Node(Integer.parseInt(strs[index++]), new LinkedList<>());
        int size = Integer.parseInt(strs[index++]);
        while (size-- != 0)
            root.children.add(helper2(strs));
        return root;
    }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));
