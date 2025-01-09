import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
	
	static int T, N, R, C, DR, DC;
	static boolean[][] cb;
	static int[][] deltas = {{-1,-2},{-2,-1},{-2,1},{-1,2},{1,2},{2,1},{2,-1},{1,-2}};
	static class Knight{
		int r;
		int c;
		int cnt;
		public Knight(int r, int c, int cnt) {
			this.r = r;
			this.c = c;
			this.cnt = cnt;
		}
	}

	public static void main(String[] args) throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		T = Integer.parseInt(br.readLine());
		
		while(T -- > 0) {
			N = Integer.parseInt(br.readLine());
			cb = new boolean[N][N];
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(br.readLine());
			DR = Integer.parseInt(st.nextToken());
			DC = Integer.parseInt(st.nextToken());
			
			int minMove = knightMove();
			sb.append(minMove).append("\n");
		}
		
		sb.setLength(sb.length()-1);
		System.out.println(sb);

	}

	private static int knightMove() {
		Deque<Knight> q = new ArrayDeque<>();
		q.offer(new Knight(R, C, 0));
		
		while(!q.isEmpty()) {
			Knight knight = q.poll();
			int kr = knight.r;
			int kc = knight.c;
			int kn = knight.cnt;
			
			if(kr == DR && kc == DC) return kn;
			
			for(int d = 0 ; d < 8 ; d++) {
				int nr = kr + deltas[d][0];
				int nc = kc + deltas[d][1];
				if(!isValid(nr, nc)) continue;
				if(cb[nr][nc]) continue;
				cb[nr][nc] = true;
				q.offer(new Knight(nr, nc, kn+1));
			}
		}
		return -1;
	}

	private static boolean isValid(int nr, int nc) {
		if(nr >= 0 && nr < N && nc >= 0 && nc < N) return true;
		return false;
	}

}
