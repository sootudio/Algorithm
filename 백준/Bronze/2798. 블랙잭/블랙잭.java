import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int N;
	static int M;
	static int maxNum = 0;
	static int[] nums;
	static int num;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		nums = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0 ; i < N ; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}
		
		SumOfNums(0, 0 ,0);
		
		System.out.println(maxNum);

	}
	
	public static void SumOfNums(int cnt, int idx, int sum) {
		if(sum > M) return;
		if(cnt == 3) {
			maxNum = Math.max(sum, maxNum);
			return;
		}
		for(int i = idx ; i < N ; i++) {
			num = nums[i];
			SumOfNums(cnt+1, i+1, sum+nums[i]);
		}
		
	}

}