package twosum.twoSum2;

class Solution {
    public boolean twoSum(int[] nums, int target) {
        int n = nums.length;
        for(int i = 0; i < n; i++){
            for(int j = i + 1; j < n; j++){
                if (nums[i] + nums[j] == target){
                    return true;
                }
            }
        }
        return false;
    }
}


public class twoSum2 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = {2, 7, 11, 15};
        int target = 9;
        boolean result = solution.twoSum(nums, target);
        if (result) {
            System.out.println("두 수의 합이 " + target + "이 되는 경우가 있습니다.");
        } else {
            System.out.println("두 수의 합이 " + target + "이 되는 경우가 없습니다.");
        }
    }
}