class Solution {
    public String[] findRestaurant(String[] list1, String[] list2) {
        String[] res = new String[list1.length < list2.length? list1.length : list2.length];
        int index = 0;
        HashMap<String, Integer> map = new HashMap<>();
        for (int i = 0; i < list1.length; i++) {
            map.put(list1[i], i);
        }
        int least = Integer.MAX_VALUE;
        for (int i = 0; i < list2.length; i++) {
            if (i > least)
                break;
            if (map.containsKey(list2[i])) {
                if (least == i + map.get(list2[i])) {
                    res[index] = list2[i];
                    index++;
                } else if (least > i + map.get(list2[i])) {
                    least = i + map.get(list2[i]);
                    res[0] = list2[i];
                    index = 1;
                }
            }
        }
        return Arrays.copyOf(res, index);
    }
}
