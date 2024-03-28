import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	static int T, N; // 테스트 케이스의 개수 T, 정점의 개수 N
	static int[][] arr; // 인접 행렬을 넣을 배열
	static int MAXVAL = 1_000_000_000;
	static int minLength ; // 최소 거리
	static int curMinLength; // 현재 노드의 최소 거리
	static int minNode; // 최소 거리를 가진 노드
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		
		// 테스트 케이스의 수만큼 반복
		for(int tc = 1; tc<=T ; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			
			// 값 입력받기
			arr = new int[N+1][N+1];
			for(int i = 1 ; i <= N ; i++ ) {
				for(int  j =1 ; j<=N ;j++) {
					int input = Integer.parseInt(st.nextToken());
					if(input == 0) arr[i][j] = MAXVAL;
					else arr[i][j] = input;
				}
			}
			
			// 자기 자신과의 값 0으로 초기화
			for(int i = 1 ; i <= N ; i++ ) {
				for(int  j =1 ; j<=N ;j++) {
					if(i == j) arr[i][j] = 0;
				}
			}
			
			// 플로이드-워샬 알고리즘 실행
			for(int k = 1 ; k <= N ; k++) { // 모든 경유 노드 k에 대하여
				for(int i = 1 ; i <= N ; i++) { // 모든 시작점 i와
					for(int j = 1 ; j <= N ; j++) { // 모든 도착점 j에 대하여
						// i에서 j로 가는 방법과 i에서 k를 경유하여 j로 가는 방법 중 더 짧은 방법으로 초기화한다.
						arr[i][j] = Math.min(arr[i][j], arr[i][k]+arr[k][j]);
					}
				}
			}
			
			
			minLength = Integer.MAX_VALUE;
			minNode = 0;
			
			// 가장 최소 거리를 가진 사람을 구한다.
			for(int i = 1 ; i <= N ; i++ ) {
				curMinLength = 0;
				for(int  j =1 ; j<=N ;j++) {
					curMinLength += arr[i][j];
				}
				if(curMinLength < minLength) {
					minLength = curMinLength;
					minNode = i;
				}
			}
			
			// 최소값을 가진 노드 출력
			System.out.println("#"+tc+" "+minLength);
		
			
		}
		
		
	
	}

}