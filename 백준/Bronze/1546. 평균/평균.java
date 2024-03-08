import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		 BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	        int N = Integer.parseInt(br.readLine());
	        StringTokenizer st = new StringTokenizer(br.readLine());

	        int[] scores = new int[N];
	        int max = 0;
	        double totalScore = 0;

	        // 입력 받으면서 최댓값을 찾음
	        for (int i = 0; i < N; i++) {
	            int score = Integer.parseInt(st.nextToken());
	            scores[i] = score;
	            max = Math.max(max, score);
	        }

	        // 새로운 점수 계산
	        for (int i = 0; i < N; i++) {
	            totalScore += (double) scores[i] / max * 100;
	        }

	        // 평균 계산
	        double average = totalScore / N;

	        // 결과 출력
	        System.out.println(average);
	}
}