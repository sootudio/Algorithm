import java.util.Scanner;

public class Main {
	static int N;
	static int[] dp;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		
		dp = new int[N+1];
		
		System.out.println(fib(N));
	}
	
	private static int fib(int N) {
		if(N == 1) return 1;
		if(N == 2) return 2;
		if(dp[N] > 0) return dp[N];
		return dp[N] = (fib(N-1) + fib(N-2))%15746;
	}

}