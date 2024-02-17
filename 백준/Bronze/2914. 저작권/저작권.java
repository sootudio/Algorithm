import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int A = Integer.parseInt(st.nextToken());
		int I = Integer.parseInt(st.nextToken());
		int maxSong = A * I;
		
		while(true) {
			if((maxSong / A) < (I-1)) {
				System.out.println(maxSong+1);
				break;
			}
			else if ((maxSong / A == I-1 && maxSong % A == 0)) {
				System.out.println(maxSong + 1);
				break;
			}
			maxSong --;
		}
	}

}