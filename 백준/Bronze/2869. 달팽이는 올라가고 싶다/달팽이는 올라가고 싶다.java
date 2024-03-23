import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int up = Integer.parseInt(st.nextToken());
		int down = Integer.parseInt(st.nextToken());
		int top = Integer.parseInt(st.nextToken());

		 int result = (top - down) / (up - down);
	        if ((top - down) % (up - down) != 0) {
	            result++;
	        }
	        System.out.println(result);
	}

}