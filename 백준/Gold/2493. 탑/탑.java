import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	static int N; // 탑의 개수
	static int[] recep;
	static boolean isRecep;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		Stack<Integer> towers = new Stack<>();
		Stack<Integer> idxs = new Stack<>();
		recep = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		int nextNum = 0;
		for(int i = 0 ; i < N ; i++) {
			nextNum = Integer.parseInt(st.nextToken());
			for(int j = towers.size() ; j > 0 ; j--) {
				if(towers.peek() > nextNum) break;
				towers.pop();
				idxs.pop();
			}
			
			if(towers.isEmpty()) {
				recep[i] = 0;
			} 
			else {
				recep[i] = idxs.peek();
			}
			
			towers.add(nextNum);
			idxs.add(i+1);
		}
		
		for(int i = 0 ; i <N ; i++) {
			if(i == N-1)System.out.print(recep[i]);
			else System.out.print(recep[i]+" ");
		}
		
	}

}