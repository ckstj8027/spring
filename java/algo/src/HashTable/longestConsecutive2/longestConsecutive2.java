package HashTable.longestConsecutive2;
import java.util.HashSet;
import java.util.Set;
class Solution {
    public int longestConsecutive(int[] nums) {
        int longest = 0;
        Set<Integer> numsHashSet = new HashSet<>();;
        for (int i = 0; i < nums.length; i++) {
            numsHashSet.add(nums[i]);;
        }
        for (int num : numsHashSet) {
            if (!numsHashSet.contains(num - 1)) {
                int cnt = 1;
                int target = num + 1;
                while (numsHashSet.contains(target)) {
                    target++;
                    cnt++;
                }
                longest = Math.max(longest, cnt);
            }
        }
        return longest;
    }
}



public class longestConsecutive2 {
    public static void main(String[] args) {
        Solution solution = new Solution();

        // 주어진 배열
        int[] nums = {0, 1, 101, 3, 105, 2, 6, 7, 8, 100, 101, 4, 102, 2, 5, 1};

        // 연속된 최대 수열의 길이를 계산하고 출력
        int longestConsecutiveLength = solution.longestConsecutive(nums);
        System.out.println("연속된 최대 수열의 길이: " + longestConsecutiveLength);
    }
}