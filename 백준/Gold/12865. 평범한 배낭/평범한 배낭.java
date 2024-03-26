import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N; //물품의 수
	static int K; //가방의 크기
	static int[][] objects; // 물건의 무게와 가치 담는 배열
	static int[][] knsk; // knapsack 계산 결과 담는 배열

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		objects = new int[N+1][2];
		knsk = new int[N+1][K+1];
		
		for(int i = 1 ; i <= N ; i++) {
			st = new StringTokenizer(br.readLine());
			objects[i][0] = Integer.parseInt(st.nextToken()); //0: 무게
			objects[i][1] = Integer.parseInt(st.nextToken()); //1: 가치
		}
		
		System.out.println(knapsack());
	}

	private static int knapsack() {
		for(int i = 0 ; i <= N ; i++) {
			knsk[i][0] = 0; // 0번 행을 0으로 초기화
		}
		for(int i = 0 ; i <= K ; i++) {
			knsk[0][i] = 0; // 0번 열을 0으로 초기화
		}
		for(int i = 1 ; i <= N ; i++) { //물건의 번호
			for(int j = 1 ; j <= K ; j++) { //가방의 (임시)무게
				if(objects[i][0] > j) // 물건이 안 들어가는 경우
					knsk[i][j] = knsk[i-1][j]; // 이전 행의 가치를 담는다.
				else // 물건이 들어가는 경우
					// 가치 비교해서 더 큰것으로 넣는다. (i번째 물건을 넣는것 vs 넣지 않는 것)
					knsk[i][j] = Math.max(objects[i][1] + knsk[i-1][j-objects[i][0]] , knsk[i-1][j]);
			}
		}
		return knsk[N][K];
	}

}