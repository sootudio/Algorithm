import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int[] switches;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(br.readLine());
		switches = new int[N+1];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 1 ; i <= N ; i++) {
			switches[i] = Integer.parseInt(st.nextToken());
		}
		
		int student = Integer.parseInt(br.readLine());
		int gender = 0;
		int index = 0;
		for(int i = 0 ; i < student ; i++) {
			st = new StringTokenizer(br.readLine());
			gender = Integer.parseInt(st.nextToken());
			index = Integer.parseInt(st.nextToken());
			if(gender == 1) {
				boys(index);
			}
			else {
				girls(index);
			}
		}
		
		for(int i = 1 ; i <= N ; i++) {
			if(i % 20 == 0) sb.append(switches[i]).append("\n");
			else sb.append(switches[i]).append(" ");
		}
		if(N % 20 == 0) sb.setLength(sb.length()-1);
		System.out.println(sb);
	}

	private static void boys(int index) {
		int mul = 1;
		while(true) {
			convert(index * mul);
			mul++;
			if(index * mul > N) break;
			
		}
	}

	private static void girls(int index) {
		convert(index);
		int lx = index - 1;
		int rx = index + 1;
		while(true) {
			if(lx <= 0 || rx > N) break;
			if(switches[lx] != switches[rx])break;
			convert(lx);
			convert(rx);
			lx -= 1;
			rx += 1;
		}
	}
	
	private static void convert(int index) {
		if(switches[index] == 0) switches[index] = 1;
		else switches[index] = 0;
	}
}
