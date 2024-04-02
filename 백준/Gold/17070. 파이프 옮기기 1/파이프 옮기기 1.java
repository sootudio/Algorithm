/**
 * 메모리: 401800 KB
 * 시간: 948ms
 */

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

	static int cases; // 갈 수 있는 개수
	static int[][] deltas = { { 0, 1 }, { 1, 0 }, { 1, 1 } }; // 현재 대각선일때 가로/세로/대각선 체크

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

		dfs(0, 1, 0);

		System.out.println(cases);
	}

	private static void dfs(int r, int c, int dir) {
		// 도착지에 도착했을때
		if (r == N - 1 && c == N - 1) {
			cases++; // 갈 수 있는 경우의 수 + 1
			return; // 돌아가서 다른 길도 탐색
		}
		switch (dir) {
		case 0:
			for(int d = 0 ; d < 3 ; d +=2) {
				int nr = r + deltas[d][0];
				int nc = c + deltas[d][1];
				if(!isValid(nr, nc)) continue;
				if(map[nr][nc] == 1) continue;
				if(d==2 && !isDiag(nr, nc)) continue;
				dfs(nr, nc, d);
			}
			break;
		case 1:
			for(int d = 1 ; d < 3 ; d++) {
				int nr = r + deltas[d][0];
				int nc = c + deltas[d][1];
				if(!isValid(nr, nc)) continue;
				if(map[nr][nc] == 1) continue;
				if(d==2 && !isDiag(nr, nc)) continue;
				dfs(nr, nc, d);
			}
			break;
		case 2:
			for(int d = 0 ; d < 3 ; d ++) {
				int nr = r + deltas[d][0];
				int nc = c + deltas[d][1];
				if(!isValid(nr, nc)) continue;
				if(map[nr][nc] == 1) continue;
				if(d==2 && !isDiag(nr, nc)) continue;
				dfs(nr, nc, d);
			}
			break;
		}

	}

	private static boolean isDiag(int nr, int nc) {
		// 가로, 세로 추가로 체크
		if (map[nr][nc - 1] == 1 || map[nr - 1][nc] == 1)
			return false;
		return true;
	}

	private static boolean isValid(int nr, int nc) {
		if (nr < 0 || nr >= N || nc < 0 || nc >= N)
			return false;
		return true;
	}

}