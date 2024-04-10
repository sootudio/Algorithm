import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static int[] nums;
	static int[] selected;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		nums = new int[N];
		selected = new int[M];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0 ; i < N ; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(nums);
		
		comb(0, 0);
		
		sb.setLength(sb.length()-1);
		System.out.println(sb);

	}

	private static void comb(int idx, int cnt) {
		// 기저조건: M개 골랐을 때
		if(cnt == M) {
			for(int i = 0 ; i < M ; i++) {
				sb.append(selected[i]).append(" ");
			}
			sb.setLength(sb.length() -1);// 공백 제거
			sb.append("\n");
			return;
		}
		for(int i = idx ; i < N ; i++) {
			selected[cnt] = nums[i];
			comb(i+1 , cnt+1);
		}
		
	}

}