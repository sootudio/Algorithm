import java.util.*;

public class Solution {

    public int[] solution(int[] progresses, int[] speeds) {
        Stack<Integer> stack = new Stack<>();
        List<Integer> answerList = new ArrayList<>();

        // 각 작업의 배포일 계산
        for (int i = progresses.length - 1; i >= 0; i--) {
            int remainingProgress = 100 - progresses[i];
            int daysNeeded = remainingProgress / speeds[i];
            if (remainingProgress % speeds[i] != 0)
                daysNeeded++;

            stack.push(daysNeeded);
        }

        // 스택에서 배포되는 기능 개수 계산
        while (!stack.isEmpty()) {
            int count = 1;
            int top = stack.pop();
            // 현재 작업보다 앞에 있는 작업 중에서 배포 가능한 작업 계산
            while (!stack.isEmpty() && stack.peek() <= top) {
                stack.pop();
                count++;
            }
            answerList.add(count);
        }

        // List를 배열로 변환
        int[] answer = new int[answerList.size()];
        for (int i = 0; i < answerList.size(); i++) {
            answer[i] = answerList.get(i);
        }

        return answer;
    }

}
