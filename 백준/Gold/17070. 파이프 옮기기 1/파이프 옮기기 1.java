import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
	static int N; // map의 높이, 넓이
	static int[][] map; // 파이프를 높을 행렬
	static int[][] map2; // 갈 수 있는 경로들을 더하는

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		map = new int[N][N];
		map2 = new int[N][N];

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		int cases = bfs();

		System.out.println(cases);
	}

	private static int bfs() {
		int[][] deltas = { { 0, 1 }, { 1, 0 }, { 1, 1 } }; // 현재 대각선일때 가로/세로/대각선 체크
		Deque<int[]> q = new ArrayDeque<>();
		q.offer(new int[] { 0, 1, 0 }); // 행좌표, 열좌표, 모양(처음은 가로) - 세로 :1, 대각선: 2

		while (!q.isEmpty()) {
			int[] cur = q.poll();
			int r = cur[0];
			int c = cur[1];
			int s = cur[2];

			map2[r][c] += 1;

			switch (s) {
			case 0:
				for (int d = 0; d < 3; d +=2) {
					int nr = r + deltas[d][0];
					int nc = c + deltas[d][1];

					if (!isValid(nr, nc)) continue; // 경계 체크
					if(map[nr][nc] == 1) continue; // 벽 체크 
					//System.out.println("현재 d값: "+d+" 좌표값: "+nr+", "+nc );
					//System.out.println(d == 2 && !isDiag(nr,nc));
					//System.out.println(isDiag(nr,nc));
					if(d == 2 && !isDiag(nr,nc)) continue; // 대각선은 추가로 가로, 세로 체크
					//System.out.println("q에 넣는 좌표: "+ nr +", "+nc);
					q.offer(new int[] {nr, nc, d});
				}
				break;
			case 1:
				for (int d = 1; d < 3; d++) {
					int nr = r + deltas[d][0];
					int nc = c + deltas[d][1];

					if (!isValid(nr, nc)) continue; // 경계 체크
					if(map[nr][nc] == 1) continue; // 벽 체크
					if(d == 2 && !isDiag(nr,nc)) continue; // 대각선은 추가로 가로, 세로 체크
					q.offer(new int[] {nr, nc, d});
				} 
				break;
			case 2:
				for (int d = 0; d < 3; d++) {
					int nr = r + deltas[d][0];
					int nc = c + deltas[d][1];

					if (!isValid(nr, nc)) continue; // 경계 체크
					if(map[nr][nc] == 1) continue; // 벽 체크
					if(d == 2 && !isDiag(nr,nc)) continue; // 대각선은 추가로 가로, 세로 체크
					q.offer(new int[] {nr, nc, d});
				}
				break;
			}
		}
		
//		for(int i = 0 ; i < N; i++) {
//			for(int j = 0 ; j < N ; j++) {
//				System.out.print(map2[i][j] +" ");
//			}
//			System.out.println();
//		}
		return map2[N-1][N-1];
	}

	private static boolean isDiag(int nr, int nc) {
		// 가로, 세로 추가로 체크
//		System.out.println("대각선 체크를 시작합니다.");
//		System.out.println("현재 좌표: "+nr+", "+nc);
//		int nr2 = nr -1;
//		System.out.println("체크할 가로 좌표: " +nr2+", "+ nc +" 값: "+map[nr-1][nc]);
//		System.out.println(map[nr][nc-1]);
//		System.out.println(map[nr-1][nc]);
//		System.out.println((map[nr][nc-1] == 1) || (map[nr-1][nc] == 1));
		if(map[nr][nc-1] == 1 || map[nr-1][nc] == 1) return false;
		return true;
	}


	private static boolean isValid(int nr, int nc) {
		if (nr < 0 || nr >= N || nc < 0 || nc >= N)
			return false;
		return true;
	}

}