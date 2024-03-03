/**
 * 조합 공식을 알아야 풀 수 있다. (난 몰랐다)
 * nCr = n-1Cr-1 + n-1Cr
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int T; // 테스트케이스 수
	static int[][] dp ; // 조합의 수 담을 dp. 행C열 의 값이 담김.

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		T = Integer.parseInt(br.readLine());
		dp = new int[30][30]; // M과 N의 최댓값은 30
		for(int tc = 0 ; tc < T ; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			// 서쪽, 동쪽 사이트의 개수
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());

			sb.append(combi(M, N)).append("\n");
		}
		System.out.println(sb);
	}

	private static int combi(int M, int N) {
		// 이미 계산된 값이면 그냥 반환. 메모이제이션 위해서
		if(dp[M][N] != 0) {
			return dp[M][N];
		}
		
		// ex: 10C10 = 1, 100C100 = 1
		if(M == N) {
			return dp[M][N] = 1;
		}
		
		// ex: 10C1 = 10, 100C1 = 100
		if(N == 1) {
			return dp[M][N] = M;
		}
		
		//조합 공식 써먹기
		return dp[M][N] = combi(M-1, N) + combi(M-1, N-1);
	}

}