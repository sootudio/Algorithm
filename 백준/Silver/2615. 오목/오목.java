import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int[][] board = new int[20][20];
	static int winner;
	static int winnerR;
	static int winnerC;
	static int[][] deltas = {{-1,1},{0,1},{1,1},{1,0},{1,-1},{0,-1},{-1,-1},{-1,0}};
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for(int i = 1 ; i <= 19 ; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j = 1 ; j <= 19 ; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		for(int i = 1 ; i <= 19 ; i++) {
			for(int j = 1; j <= 19 ; j++) {
				if(board[i][j] != 0) check(i, j);
				if(winner != 0) break;
			}
			if(winner != 0) break;
		}
		System.out.println(winner);
		if(winner != 0) System.out.println(winnerR + " " + winnerC);
	}

	private static void check(int r, int c) {
		int num = board[r][c];
		for(int d = 0 ; d < 4 ; d++) {
			// 육목 체크
			int pr = r + deltas[d+4][0];
			int pc = c + deltas[d+4][1];
			if(!isValid(pr,pc)) continue;
			if(num == board[pr][pc]) continue;
			// 오목 체크
			int cnt = 1;
			int cr = r;
			int cc = c;
			while(true) {
				int nr = cr + deltas[d][0];
				int nc = cc + deltas[d][1];
				if(!isValid(nr, nc)) break;
				if(num != board[nr][nc]) break;
				cnt ++;
				cr = nr;
				cc = nc;
			}
			if(cnt == 5) {
				winner = num;
				winnerR = r;
				winnerC = c;
				break;
			}
		}
		
	}

	private static boolean isValid(int nr, int nc) {
		if(nr >= 0 && nr <= 19 && nc >= 0 && nc <= 19) return true;
		return false;
	}

}
