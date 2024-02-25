import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static StringBuilder sb = new StringBuilder();
	static StringBuilder sb2 = new StringBuilder();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		dupliPermu(0);
		System.out.println(sb2); // 중복순열 출력
	}

	private static void dupliPermu( int cnt) {
		if(cnt == M) {
			sb2.append(sb).append("\n");
			return;
		}
		for(int i = 1 ; i <= N; i++) {
			sb.append(i).append(" "); // 중복순열 골라서 값 넣기
			dupliPermu(cnt+1);
			sb.setLength(sb.length() - 2); // 공백문자와 이전에 들어간 문자 두개를 삭제해야 하므로
		}
	}

}