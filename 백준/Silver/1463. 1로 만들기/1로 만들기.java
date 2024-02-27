import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int[] makeOne;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		
		makeOne = new int[N+1];
		
		System.out.println(dp());
		
	}

	private static int dp() {
		
		if(N == 1) return 0;
		else if(N == 2 || N == 3) return 1;
		
		makeOne[1] = 0;
		makeOne[2] = 1;
		makeOne[3] = 1;

		for(int i = 4 ; i <= N ; i++) {
			if(i % 6 == 0) {
				makeOne[i] = Math.min(Math.min(makeOne[i/3] + 1, makeOne[i/2] + 1), makeOne[i-1]+1) ;
			}
			else {
				switch(i % 3) {
				case 0:
					makeOne[i] = Math.min(makeOne[i/3] + 1,  makeOne[i-1]+1); 
					break;
				case 1:
					if(i % 2 == 0) {
						makeOne[i] = Math.min(makeOne[i/2] + 1,  makeOne[i-1]+1); 
					}
					else if(i % 2 == 1) {
						makeOne[i] = makeOne[i-1] + 1;
					}
					break;
				case 2: 
					if(i % 2 == 0) {
						makeOne[i] = Math.min(makeOne[i/2] + 1,  makeOne[i-1]+1); 
					}
					else if(i % 2 == 1) {
						makeOne[i] = makeOne[i-1] + 1;
					}
					break;
				}
			}
		}
		
		return makeOne[N];
	}

}