import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int decimal = 0;
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0 ; i < N ; i++) {
			if(isDecimal(Integer.parseInt(st.nextToken())))
				decimal++;
		}
		
		System.out.println(decimal);
	}

	private static boolean isDecimal(int num) {
		if(num == 1) return false;
		for(int i = 2 ; i < num/2 + 1  ;i++) {
			if(num % i == 0) return false;
		}
		return true;
	}

}