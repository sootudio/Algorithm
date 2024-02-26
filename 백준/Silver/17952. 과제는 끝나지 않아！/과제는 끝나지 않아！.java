import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int[][] work; //업무의 만점과 처리 시간.
	static int totalScore = 0;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		work = new int[N+1][2];
		
		for(int i=1; i <= N ; i++) {
			st = new StringTokenizer(br.readLine());
			int w = Integer.parseInt(st.nextToken());
			if(w == 0) continue;
			else {
				work[i][0] =  Integer.parseInt(st.nextToken());
				work[i][1] =  Integer.parseInt(st.nextToken());
			}
		}
		
		stack();
		
		System.out.println(totalScore);
	}

	private static void stack() {
		Deque<int[]> stack = new ArrayDeque<>();
		
		for(int i = 1 ; i <= N ; i++) {
			if(work[i][0] == 0 && work[i][1] == 0) {
				if(stack.isEmpty()) continue;
				int[] curWork = stack.pop();
				curWork[1] --;
				if(curWork[1] == 0) {
					totalScore += curWork[0];
				}
				else {
					stack.push(curWork);
				}
			}
			else {
				work[i][1] --;
				if(work[i][1] == 0) {
					totalScore += work[i][0];
				}
				else {
					stack.push(new int[] {work[i][0], work[i][1]});
				}
				
			}
		}
	}
}