import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int R, C; // 행, 열
	static boolean[][] map; // 빵집이 있는 맵
	// static char[][] cMap; // 디버깅용...
	static int[][] deltas = {{-1, 1}, {0, 1},{1, 1}}; // 우상단, 오른쪽, 우하단
	static int numOfPipe = 0; //설치할 수 있는 파이프 개수

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		map = new boolean[R][C];
		// cMap = new char[R][C];
		
		for(int i = 0 ; i < R ; i++) {
			String str = br.readLine();
			for(int j = 0 ; j < C ; j++) {
				char input = str.charAt(j);
				if(input == 'x') map[i][j] = true;
				// cMap[i][j] = input;
			}
		}
		
		// 0행 0열부터 R-1행 0열까지 탐색
		for(int row = 0 ; row < R ; row++) {
			if(buildPipe(row, 0)) numOfPipe++; // 설치 가능한 파이프 개수 증가
		}
		
		// 설치 가능한 파이프 개수 출력
		System.out.println(numOfPipe);
	}

	/**
	 * 0열부터 C-1열까지 파이프를 놓는 함수
	 * @param r 행 좌표
	 * @param c 열 좌표
	 */
	private static boolean buildPipe(int r, int c) {
		// 기저조건: C-1열에 도착하면
		if(c == C-1) {
			return true;
		}
		// 유도파트: 우상단, 오른쪽, 우하단 순으로 탐색
		for(int d = 0 ; d < 3; d++) {
			int nr = r + deltas[d][0];
			int nc = c + deltas[d][1];
			if(!isValid(nr, nc)) continue; // 경계 체크
			if(map[nr][nc]) continue; // true라면 이미 간 길 이거나, 건물이기 때문에 못감
			map[nr][nc] = true; // 방문 체크. 성공하거나 실패하거나 여기는 이제 갈 일 없음
			// cMap[nr][nc] = 'o';
			if(buildPipe(nr, nc)) return true;
		}
		return false;
	}

	/**
	 * 경계 체크하는 함수
	 * @param nr 행 좌표
	 * @param nc 열 좌표
	 * @return 해당 좌표가 경계 안에 있으면 true, 경계를 벗어나면 false
	 */
	private static boolean isValid(int nr, int nc) {
		if(nr < 0 || nr >= R || nc <0 || nc >= C) return false;
		return true;
	}

}