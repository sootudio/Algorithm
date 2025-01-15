import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static String result;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int num = Integer.parseInt(st.nextToken());
		
		if(num == 1) {
			for(int i = 2 ; i <= 8 ; i ++) {
				if(Integer.parseInt(st.nextToken()) != i) {
					result = "mixed";
					break;
				}
				if(i == 8) 
					result = "ascending";
			}
		}
		else if (num == 8) {
			for(int i = 7 ; i >= 1 ; i --) {
				if(Integer.parseInt(st.nextToken()) != i) {
					result = "mixed";
					break;
				}
				if(i == 1) 
					result = "descending";
			}
		}
		else
			result = "mixed";
		System.out.println(result);
	}

}
