import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	static int N, M;
	static int[] nums;
	static int[] selected;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new  BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N  = Integer.parseInt(st.nextToken());
		M  = Integer.parseInt(st.nextToken());
		
		nums = new int[N];
		selected = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0 ; i < N ; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(nums);
		
		combi(0, 0);
	}

	private static void combi(int idx, int cnt) {
		if(cnt == M) {
			for(int i = 0 ; i < M ; i++) {
				System.out.print(selected[i]+" ");
			}
			System.out.println();
			return;
		}
		for(int i = idx; i < N ; i++) {
			selected[cnt] = nums[i];
			combi(i, cnt+1);
		}
		
	}
}