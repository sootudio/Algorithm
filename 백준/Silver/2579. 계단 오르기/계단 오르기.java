import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int N; // 계단의 수
    static int[] stairs; // 계단 담는 배열
    static int[] dp; // 각 계단까지의 최대 점수 저장

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        stairs = new int[N + 1];
        dp = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            stairs[i] = Integer.parseInt(br.readLine());
        }

        // 다이내믹 프로그래밍을 이용하여 최대 점수 구하기
        dp[1] = stairs[1]; // 첫 번째 계단의 최대 점수는 첫 번째 계단 값과 같음
        if (N >= 2) dp[2] = stairs[1] + stairs[2]; // 두 번째 계단까지의 최대 점수는 두 번째 계단 값과 같음

        for (int i = 3; i <= N; i++) {
            // 현재 계단을 밟았을 때와 그 이전 계단을 밟았을 때 중 큰 값을 선택하여 현재 계단의 값과 더해줌
            dp[i] = Math.max(dp[i - 2], dp[i - 3] + stairs[i - 1]) + stairs[i];
        }

        // N번째 계단까지의 최대 점수 출력
        System.out.println(dp[N]);
    }
}