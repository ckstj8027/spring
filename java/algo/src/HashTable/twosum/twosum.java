package HashTable.twosum;

import java.util.HashMap;
import java.util.Map;
class Solution {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> memo = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int needed = target - nums[i];
            if (memo.containsKey(needed)) {
                return new int[] {memo.get(needed), i};
            }
            memo.put(nums[i], i);
        }
        return new int[] {-1, -1};
    }
}



public class twosum {
    public static void main(String[] args) {
        Solution solution = new Solution();

        // 주어진 배열과 목표 합계
        int[] nums = {2, 7, 11, 15};
        int target = 9;

        // 두 수의 인덱스를 찾아서 출력
        int[] result = solution.twoSum(nums, target);
        System.out.println("두 수의 합이 " + target + "이 되는 인덱스: " + result[0] + ", " + result[1]);
    }
}