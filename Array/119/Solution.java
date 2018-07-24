public class Solution {
    public List<Integer> getRow(int rowIndex) {
        List<Integer> list = new ArrayList<Integer>();
		if (rowIndex < 0)
			return list;

		for (int i = 0; i < rowIndex + 1; i++) {
			list.add(0, 1);
			for (int j = 1; j < list.size() - 1; j++) {
				list.set(j, list.get(j) + list.get(j + 1));
			}
		}
		return list;
    }
}


class Solution {
    public List<Integer> getRow(int rowIndex) {
        List<Integer> res = new ArrayList<>(rowIndex+1);
        for (int i = 0; i <= rowIndex; i++) {
            int temp1 = 1, temp2 = 0;
            for (int j = 1; j < res.size(); j++) {
                temp2 = res.get(j);
                res.set(j, temp2 + temp1);
                temp1 = temp2;
            }
            res.add(1);
        }
        return res;
    }
}
