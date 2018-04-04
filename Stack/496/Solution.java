class Solution {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        if (nums2 == null || nums1 == null || nums2.length == 0 || nums1.length == 0)
            return new int[0];
        int[] res = new int[nums1.length];
        HashMap<Integer, Integer> map = new HashMap<>();
        Stack<Integer> stack = new Stack<>();
        for (int i : nums2) {
            while (!stack.isEmpty() && i > stack.peek())
                map.put(stack.pop(), i);
            stack.push(i);
        }
        for (int i = 0; i < nums1.length; i++)
            res[i] = map.getOrDefault(nums1[i], -1);
        return res;
    }
}
