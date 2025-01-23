import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
	
	static int R, C, max; 
	static char[][] board;
	static int[][] deltas = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}}; // 상 우 하 좌
	static boolean[] alphabet;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		board = new char[R][C];
		alphabet = new boolean[26];
		max = Integer.MIN_VALUE;
		
		for(int i = 0 ; i < R ; i++) {
			String s = br.readLine();
			for(int j = 0 ; j < C; j++) {
				board[i][j] = s.charAt(j);
			}
		}
		
		dfs(0, 0, 1);
		
		System.out.println(max);
	}
	
	private static void dfs(int r, int c, int cnt) {
		if(!isValid(r, c)) return;
		if(alphabet[board[r][c] - 'A']) return;
		
		alphabet[board[r][c] - 'A'] = true;
		
		max = Math.max(max, cnt);
		
		for(int d = 0 ; d < 4 ; d++) {
			int nr = r + deltas[d][0];
			int nc = c + deltas[d][1];
			dfs(nr, nc, cnt+1);
		}
		
		alphabet[board[r][c] - 'A'] = false;	
	}
	
	private static boolean isValid(int r, int c) {
		if(r >= 0 && r < R && c >= 0 && c < C) return true;
		return false;
	}

}
