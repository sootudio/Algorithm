import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int T;
	static int[] nums;
	static int[] lottos;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		while(true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			T = Integer.parseInt(st.nextToken());
			
			if(T == 0) break;
			
			nums = new int[T];
			lottos = new int[6];
			for(int i = 0 ; i < T ; i++) {
				nums[i] = Integer.parseInt(st.nextToken());
			}
			
			combi(0 , 0);
			
			sb.append("\n");
		}
		sb.setLength(sb.length());
		System.out.println(sb);
	}

	private static void combi(int depth, int idx) {
		if(depth == 6) {
			for(int i = 0 ; i < 6 ; i++) {
				sb.append(lottos[i]).append(" ");
			}
			sb.append("\n");
			return;
		}
		for(int i = idx ; i < T ; i++) {
			lottos[depth] = nums[i];
			combi(depth+1, i+1);
		}
		
	}
}
