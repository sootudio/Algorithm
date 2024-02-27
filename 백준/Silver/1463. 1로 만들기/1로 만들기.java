import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int[] makeOne;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		
		makeOne = new int[N+1];
		
		System.out.println(dp());
		
	}

	private static int dp() {
		if(N == 1) return 0;
		else if(N == 2 || N ==3) return 1;
		makeOne[1] = 0;
		makeOne[2] = 1;
		makeOne[3] = 1;

		for(int i = 4 ; i <= N ; i++) {
			makeOne[i] = makeOne[i-1] + 1; // 현재값에서 1을 뺀 경우를 최소값으로 설정해 놓음
			// 2로 나눠 떨어질때, 2로 나눈 결과와 1을 뺀 결과를 비교하여 작은 것으로 설정한다.
			// 위에서 -1 을 뺀 경우를 이미 최솟값으로 해 놓았으므로, makeOne[i]로 비교해도 무방함.
			if(i%2 == 0) {
				makeOne[i] = Math.min(makeOne[i/2]+1, makeOne[i]);
			}
			// 3으로 나눠 떨어질 때, 3으로 나눈 결과와 1을 뺀 결과를 비교하여 작은 것으로 설정한다.
			if(i%3 == 0) {
				makeOne[i] = Math.min(makeOne[i/3]+1, makeOne[i]);
			}
		}
		
		return makeOne[N];
	}

}