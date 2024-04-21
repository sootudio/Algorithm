import java.util.Scanner;

public class Main {
	
	static int N;
	static int[] dp;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		
		dp = new int[N+1];
		
		cnt();
		
		System.out.println(dp[N]);

	}

	private static void cnt() {
		dp[1] = 1;
		
		if(N > 1) dp[2] = 3;
		
		if(N > 2) {
			for(int i = 3 ; i <= N ; i++) {
				dp[i] = (dp[i-1] + dp[i-2] + dp[i-2]) % 10007;
			}
		}
		
	}

}