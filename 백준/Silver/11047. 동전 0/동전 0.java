import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		int[] coins = new int[n];
		
		for(int i = 0 ; i < n ; i++) {
			coins[i] = Integer.parseInt(br.readLine());
		}
		int result = 0;
		
		for(int i = n-1 ; i >= 0 ; i--) {
			if(k/coins[i] == 0) continue; // k보다 큰경우는 더 작은 동전으로 채워야 한다.
			else {
				result += k/coins[i];
				if(k%coins[i] == 0) break; // 나눠떨어지는 경우에는 계산을 그만한다.
				else {
					k = k%coins[i];
				}
			}
		}
		System.out.println(result);
	}

}
