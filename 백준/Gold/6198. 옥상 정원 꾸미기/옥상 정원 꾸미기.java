import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class Main {
    
	static int N; // 빌딩의 수
    static int[] heights; // 각 빌딩의 높이를 저장할 배열

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        
        heights = new int[N];
        for(int i = 0 ; i < N ; i++) {
            heights[i] = Integer.parseInt(br.readLine());
        }
        
        long answer = countEyesight();
        System.out.println(answer);
    }

    private static long countEyesight() {
        Deque<Integer> stack = new ArrayDeque<>(); // 옥상을 확인하는데 사용할 스택
        long totalEyesight = 0; // 총 옥상 확인 가능한 빌딩 수
        
        for (int i = 0; i < N; i++) {
            // 스택이 비어있지 않고, 현재 빌딩의 높이가 스택의 맨 위 높이보다 크거나 같을 때까지 반복
            while (!stack.isEmpty() && heights[i] >= heights[stack.peek()]) {
                stack.pop(); // 스택의 맨 위 높이가 현재 높이보다 작다면 해당 빌딩은 옥상을 볼 수 없음
            }
            
            totalEyesight += stack.size(); // 현재 빌딩을 제외한 옥상을 볼 수 있는 빌딩 수를 더함
            stack.push(i); // 현재 빌딩을 스택에 추가
        }
        
        return totalEyesight;
    }

}