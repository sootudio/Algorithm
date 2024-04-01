import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int A = Integer.parseInt(st.nextToken());
		int B = Integer.parseInt(st.nextToken());
		
		int gcd = Euclidean(A, B); // 최대공약수
		
		int lcm = Math.abs(A * B)/gcd;
		
		System.out.println(gcd +"\n"+lcm);
	}

	private static int Euclidean(int a, int b) {
		// 기저조건: 나누어 떨어지면 최대공약수
		if(b == 0)
			return a;
		return Euclidean(b, a%b);
	}

}