// the famous "3 way partition" 
class Solution {
    public void sortColors(int[] nums) {
        int front = -1, back = nums.length;
        for (int i = 0; i < back; i++) {
            if (nums[i] == 0) {
                front++;
                nums[i] = nums[front];
                nums[front] = 0;
            } else if (nums[i] == 2) {
                back--;
                nums[i] = nums[back];
                nums[back] = 2;
                i--;
            }
        }
    }
}


class Solution {
    public void sortColors(int[] nums) {
        int n0 = -1, n1 = -1, n2 = -1;
        for (int n : nums) {
            if (n == 0) {
                nums[++n2] = 2;
                nums[++n1] = 1;
                nums[++n0] = 0;
            } else if (n == 1) {
                nums[++n2] = 2;
                nums[++n1] = 1;
            } else {
                nums[++n2] = 2;
            }
        }
    }
}
