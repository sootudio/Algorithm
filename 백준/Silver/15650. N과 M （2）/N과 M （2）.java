import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static int[] sequence;
	static boolean[] isVisited;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		sequence = new int[M];
		isVisited = new boolean[N+1];
		
		combi(1, 0);
	}

	private static void combi(int idx, int cnt) {
		if(cnt == M) {
			for(int num : sequence) {
				System.out.print(num + " ");
			}
			System.out.println();
			return;
		}
		for(int i = idx ; i<= N ; i++) {
			if(isVisited[i]) continue;
			sequence[cnt] = i;
			isVisited[i] = true;
			combi(i , cnt + 1);
			isVisited[i] = false;
		}
	}

}