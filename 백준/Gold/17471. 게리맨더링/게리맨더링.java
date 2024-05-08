import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {
	
	static int N;
	static int[] person;
	static int[][] linked;
	static boolean[] isSelected;
	static int min, total;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		person = new int[N+1];
		linked = new int[N+1][N+1];
		isSelected = new boolean[N+1];
		total = 0;
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 1 ; i <= N ; i++) {
			person[i] = Integer.parseInt(st.nextToken());
			total += person[i];
		}
		
		for(int i= 1 ; i <= N ; i++) {
			st = new StringTokenizer(br.readLine());
			int cnt = Integer.parseInt(st.nextToken());
			for(int j = 0 ; j < cnt ; j++) {
				int linkNum = Integer.parseInt(st.nextToken());
				linked[i][linkNum] = 1;
			}
		}
		
		min = total;
		
		for(int m = 1; m < N ; m++) {
			combi(1, 0, m);
		}
		
		System.out.println(min==total? -1:min);
		
	}

	private static void combi(int idx, int cnt, int m) {
		if(cnt == m) {
			int sidx = 0;
			int usidx = 0;
			int[] sVill = new int[m];
			int[] usVill = new int[N-m];
			for(int i = 1 ; i <= N ; i++) {
				if(isSelected[i]) {
					sVill[sidx] = i;
					sidx ++;
				}
				else {
					usVill[usidx] = i;
					usidx++;
				}
			}
			
			if(bfs(sVill, m) && bfs(usVill, N-m)) {
				int diff = personDiff(sVill, usVill, m);
				min = Math.min(min, diff);
			}
			return;
		}
		for(int i = idx; i < N ; i++) {
			isSelected[i] = true;
			combi(i+1, cnt+1, m);
			isSelected[i] = false;
		}
		
	}

	private static boolean bfs(int[] vill, int m) {
		ArrayDeque<Integer> q = new ArrayDeque<>();
		boolean[] linkedNum = new boolean[N+1];
		q.offer(vill[0]);
		
		while(!q.isEmpty()) {
			int cur = q.poll();
			linkedNum[cur] = true;
			for(int i = 0 ; i < m ; i++) {
				int num = vill[i];
				if(linked[cur][num] == 0) continue;
				if(linkedNum[num]) continue;
				q.offer(num);
			}
		}
		
		for(int i = 0 ; i < m ; i++) {
			if(!linkedNum[vill[i]]) return false;
		}
		
		return true;
	}

	private static int personDiff(int[] sVill, int[] usVill, int m) {
		int sPerson = 0;
		int usPerson = 0;
		
		for(int i = 0 ; i < m ; i++) {
			sPerson += person[sVill[i]];
		}
		int nm = N-m;
		for(int i = 0 ; i < nm ; i++ ) {
			usPerson += person[usVill[i]];
		}
		int diff = sPerson - usPerson;
		return Math.abs(diff);
	}
	
	

}