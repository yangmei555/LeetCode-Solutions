class Solution {
    public int[] maxSumOfThreeSubarrays(int[] nums, int k) {
        int[] sums = new int[nums.length];
        int[][][] record = new int[nums.length][3][3];
        int sum = 0;
        for (int i = 0; i < k; i++)
            sum += nums[i];
        for (int i = 0; i <= nums.length-k; i++) {
            sums[i] = sum;
            if (i != nums.length-k)
                sum += nums[i+k] - nums[i];
        }
        for (int i = k-1; i < nums.length; i++) {
            if (i == k-1)
                record[i][0][0] = 0;
            else
                record[i][0][0] = sums[i-k+1] > sums[record[i-1][0][0]] ? i-k+1 : record[i-1][0][0];
        
            if (i == k*2-1) {
                record[i][1][0] = 0;
                record[i][1][1] = k;
            } else if (i > k*2-1) {
                if (sums[i-k+1] + sums[record[i-k][0][0]] > 
                    sums[record[i-1][1][0]] + sums[record[i-1][1][1]]) {
                    record[i][1][0] = record[i-k][0][0];
                    record[i][1][1] = i-k+1;
                } else {
                    record[i][1][0] = record[i-1][1][0];
                    record[i][1][1] = record[i-1][1][1];
                }
            }
            
            if (i == k*3-1) {
                record[i][2][0] = 0;
                record[i][2][1] = k;
                record[i][2][2] = k*2;
            } else if (i > k*3-1) {
                if (sums[i-k+1] + sums[record[i-k][1][0]] + sums[record[i-k][1][1]] > 
                    sums[record[i-1][2][0]] + sums[record[i-1][2][1]] + sums[record[i-1][2][2]]) {
                    record[i][2][0] = record[i-k][1][0];
                    record[i][2][1] = record[i-k][1][1];
                    record[i][2][2] = i-k+1;
                } else {
                    record[i][2][0] = record[i-1][2][0];
                    record[i][2][1] = record[i-1][2][1];
                    record[i][2][2] = record[i-1][2][2];
                }
            }
            
        }
        return record[nums.length-1][2];
    }
}


class Solution {
    public int[] maxSumOfThreeSubarrays(int[] nums, int k) {
        int[] sums = new int[nums.length-k+1];
        int sum = 0;
        for (int i = 0; i < k; i++)
            sum += nums[i];
        for (int i = 0; i < sums.length; i++) {
            sums[i] = sum;
            if (i != sums.length-1)
                sum += nums[i+k] - nums[i];
        }
        int[] left = new int[nums.length], right = new int[nums.length];
        for (int i = 0; i <= nums.length-k; i++) {
            if (i == 0 || sums[i] > sums[left[i-1]])
                left[i] = i;
            else
                left[i] = left[i-1];
        }
        for (int i = nums.length-k; i >= 0; i--) {
            if (i != nums.length-k && sums[i] < sums[right[i+1]])
                right[i] = right[i+1];
            else
                right[i] = i;
        }
        int[] res = new int[]{0, k, k*2};
        for (int i = k; i+k <= nums.length-k; i++) {
            int pos1 = i-k, pos2 = i+k;
            if (sums[left[pos1]] + sums[i] + sums[right[pos2]] > 
                sums[res[0]] + sums[res[1]] + sums[res[2]]) {
                res[0] = left[pos1];
                res[1] = i;
                res[2] = right[pos2];
            }
        }
        return res;
    }
}


class Solution {
    public int[] maxSumOfThreeSubarrays(int[] nums, int k) {
        int sum1 = 0, sum2 = 0, sum3 = 0;
        for (int i = 0; i < k; i++)
            sum1 += nums[i];
        for (int i = k; i < k*2; i++)
            sum2 += nums[i];
        for (int i = k*2; i < k*3; i++)
            sum3 += nums[i];
        int seq1 = sum1, seq2 = sum1 + sum2, seq3 = sum1 + sum2 + sum3;
        int index1 = 0;
        int[] index2 = new int[]{0, k}, index3 = new int[]{0, k, k*2};
        for (int i = 1; i <= nums.length-k*3; i++) {
            sum1 += nums[i+k-1] - nums[i-1];
            sum2 += nums[i+k*2-1] - nums[i+k-1];
            sum3 += nums[i+k*3-1] - nums[i+k*2-1];
            if (sum1 > seq1) {
                seq1 = sum1;
                index1 = i;
            }
            if (sum2 + seq1 > seq2) {
                seq2 = sum2 + seq1;
                index2[0] = index1;
                index2[1] = i+k;
            }
            if (sum3 + seq2 > seq3) {
                seq3 = sum3 + seq2;
                index3[0] = index2[0];
                index3[1] = index2[1];
                index3[2] = i+k*2;
            }
        }
        return index3;
    }
}


class Solution {
    public int[] maxSumOfThreeSubarrays(int[] nums, int k) {
        int[] sums = new int[nums.length-k+1];
        int sum = 0;
        for (int i = 0; i < k; i++)
            sum += nums[i];
        for (int i = 0; i < sums.length; i++) {
            sums[i] = sum;
            if (i != sums.length-1)
                sum += nums[i+k] - nums[i];
        }
        int count = 3;
        int[][] record = new int[count+1][sums.length+1];
        int[][] indices = new int[count+1][sums.length+1];
        for (int i = 1; i <= count; i++) {
            for (int j = (i-1)*k; j < sums.length-(count-i)*k; j++) {
                int cur = sums[j] + (j >= k ? record[i-1][j-k+1] : 0);
                if (cur > record[i][j]) {
                    record[i][j+1] = cur;
                    indices[i][j+1] = j;
                } else {
                    record[i][j+1] = record[i][j];
                    indices[i][j+1] = indices[i][j];
                }
            }    
            // System.out.println(Arrays.toString(record[i]));
            // System.out.println(Arrays.toString(indices[i]));
        }
        int res[] = new int[count];
        res[count-1] = indices[count][sums.length];
        for (int i = count-2; i >= 0; i--)
            res[i] = indices[i+1][res[i+1]-k+1];
        return res;
    }
}
