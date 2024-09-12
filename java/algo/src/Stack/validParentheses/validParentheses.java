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


