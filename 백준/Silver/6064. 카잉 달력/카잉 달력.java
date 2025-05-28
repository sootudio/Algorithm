import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		int tc = 0;
		
		while(tc < T) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int M = Integer.parseInt(st.nextToken());
			int N = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			
			int gcd = getGCD(M, N);
			int lcm = (M * N) / gcd;
			int result = getCalendar(lcm, M, N, x, y);
			sb.append(result).append("\n");
			tc++;
		}
		
		sb.setLength(sb.length() - 1);
		System.out.println(sb);
	}
	
	public static int getGCD(int M, int N) {
		if(M % N == 0) return N;
		return getGCD(N, M % N);
	}
	
	public static int getCalendar(int lcm, int M, int N, int x, int y) {
		Set<Integer> set = new HashSet<>();
		int[] arr2 = new int[lcm/N];
		
		for(int i = 0 ; i < lcm/M ; i++) {
			set.add(x + (i * M));
		}
		for(int i = 0 ; i < lcm/N ; i++) {
			int num = y + (i * N);
			if(set.contains(num)) return num;
		}
		
		return -1;
	}
}
