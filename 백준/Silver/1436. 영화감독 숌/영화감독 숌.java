import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		Integer number = 665;
		int cnt = 0;
		
		while(true) {
			number ++ ;
			if(number.toString().contains("666")) {
				cnt ++;
			}
			if(cnt == n ) {
				System.out.println(number);
				break;
			}
		}

	}

}
