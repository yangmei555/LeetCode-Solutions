class Solution {
    public void rotate(int[] nums, int k) {
        int i = 0, j = nums.length - 1, temp = 0;
        k = k % nums.length;
        while (i < j) {
            temp = nums[i];
            nums[i++] = nums[j];
            nums[j--] = temp;
        }
        i = 0;
        j = k-1;
        while (i < j) {
            temp = nums[i];
            nums[i++] = nums[j];
            nums[j--] = temp;
        }
        i = k;
        j = nums.length-1;
        while (i < j) {
            temp = nums[i];
            nums[i++] = nums[j];
            nums[j--] = temp;
        }
    }
}


class Solution {
    public void rotate(int[] nums, int k) {
        int count = 0, next = 0, temp1 = 0, temp2 = 0;
        k = k % nums.length;
        for (int i = 0; count < nums.length; i++) {
            next = (i + k) % nums.length;
            temp1 = nums[i];
            while (next != i) {
                temp2 = nums[next];
                nums[next] = temp1;
                temp1 = temp2;
                next = (next + k) % nums.length;
                count++;
            }
            nums[next] = temp1;
            count++;
        }
    }
}
