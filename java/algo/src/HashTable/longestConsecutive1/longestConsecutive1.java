package HashTable.longestConsecutive1;
import java.util.HashMap;
import java.util.Map;
class Solution {
    public int longestConsecutive(int[] nums) {
        int longest = 0;
        Map<Integer, Boolean> numsHashMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            numsHashMap.put(nums[i], true);
        }
        for (int num : numsHashMap.keySet()) {
            if (!numsHashMap.containsKey(num - 1)) {
                int cnt = 1;
                int target = num + 1;
                while (numsHashMap.containsKey(target)) {
                    target++;
                    cnt++;
                }
                longest = Math.max(longest, cnt);
            }
        }
        return longest;
    }
}
public class longestConsecutive1 {
    public static void main(String[] args) {
        Solution solution = new Solution();

        // 주어진 배열
        int[] nums = {0, 1, 101, 3, 105, 2, 6, 7, 8, 100, 101, 4, 102, 2, 5, 1};

        // 연속된 최대 수열의 길이를 계산하고 출력
        int longestConsecutiveLength = solution.longestConsecutive(nums);
        System.out.println("연속된 최대 수열의 길이: " + longestConsecutiveLength);
    }
}