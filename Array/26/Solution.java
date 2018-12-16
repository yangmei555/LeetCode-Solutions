class Solution {
    public int removeDuplicates(int[] nums) {
		if (nums.length == 0)
			return 0;
		int index = 1, search = 1, pre = nums[0];
		while (search < nums.length) {
			if (nums[search] != pre) {
				nums[index] = nums[search];
				index++;
				pre = nums[search];
			}
			search++;
		}        
		return index;
    }
}


class Solution {
    public int removeDuplicates(int[] nums) {
        int index = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i == 0 || nums[i-1] != nums[i])
                nums[index++] = nums[i];
        }
        return index;
    }
}
