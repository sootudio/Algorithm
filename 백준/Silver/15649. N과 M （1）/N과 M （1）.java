import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N,M; // 입력받는 숫자
	static int permu[]; // 수열 담을 배열
	static boolean isVisited[]; // 방문 체크하는 배열
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		permu = new int[M];
		isVisited = new boolean[N+1];
		
		Permu(1,0);
	}

	private static void Permu(int idx, int cnt) {
		// 기저조건 파트
		if(cnt == M) {
			for(int num : permu) { // 순열 수열 출력
				System.out.print(num + " ");
			}
			System.out.println();
			return;
		}
		for(int i = 1 ; i <= N ; i++) {
			if(isVisited[i]) continue; // 방문 체크
			permu[cnt] = i; // 순열 수열에 담기
			isVisited[i] = true; // 방문 처리
			Permu(i, cnt+1); // 재귀 호출
			isVisited[i] = false; // 방문 처리 쥐소
		}
	}

}