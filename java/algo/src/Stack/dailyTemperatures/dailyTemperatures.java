package Stack.dailyTemperatures;

import java.util.Stack;
class Solution {
    public int[] dailyTemperatures(int[] temperatures) {
        Stack<Integer> stack = new Stack<>();
        int[] ans = new int[temperatures.length];
        for (int day = 0; day < temperatures.length; day++){
            while(!stack.isEmpty() && temperatures[stack.peek()] < temperatures[day] ){
                int prevDay = stack.pop();
                ans[prevDay] = day - prevDay;
            }
            stack.push(day);
        }
        return ans;
    }
}

public class dailyTemperatures {
    public static void main(String[] args) {
        Solution solution = new Solution();

        // 주어진 일일 온도
        int[] temperatures = {73, 74, 75, 71, 69, 72, 76, 73};

        // 각 날짜에 대해 다음 따뜻한 날까지의 일 수 계산
        int[] result = solution.dailyTemperatures(temperatures);

        // 결과 출력
        System.out.println("각 날짜에 대해 다음 따뜻한 날까지의 일 수:");
        for (int i = 0; i < result.length; i++) {
            System.out.println("날짜 " + (i + 1) + ": " + result[i]);
        }
    }
}
