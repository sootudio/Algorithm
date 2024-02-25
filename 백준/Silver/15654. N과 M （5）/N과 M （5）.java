import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int N,M;
	static int[] arr;
	static boolean[] isVisited;
	static StringBuilder sb = new StringBuilder();
	static StringBuilder sb2 = new StringBuilder();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		arr = new int[N];
		isVisited = new boolean[N];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0 ; i < N ; i++) {
			String str = st.nextToken();
			arr[i] = Integer.parseInt(str); 
		}
		
		Arrays.sort(arr); // 수열들을 오름차순으로 출력해야 하므로 arr 오름차순 정렬
		
		permu(0);
		System.out.println(sb2); // 전체 순열 수열들 출력
	}

	private static void permu(int cnt) {
		if(cnt == M) {
			sb2.append(sb).append("\n"); // sb와 개행문자 삽입
			return;
		}
		for(int i = 0 ; i < N ; i++) {
			if(isVisited[i]) continue; // 방문 체크
			int tempLength = sb.length();
			sb.append(arr[i]).append(" "); // i번째 숫자와 공백문자 삽입 
			isVisited[i] = true; // 방문 처리
			permu(cnt + 1); // 재귀 호출
			sb.setLength(tempLength); // 숫자와 공백문자 삭제
			isVisited[i] = false; // 미방문 처리
		}
	}

}