import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Solution {
	
	static class Point{
		int r, c, cnt;

		public Point(int r, int c, int cnt) {
			super();
			this.r = r;
			this.c = c;
			this.cnt = cnt;
		}
		
	}
	
	static int dr[] = { -1, 1, 0, 0};
	static int dc[] = {0,0, -1, 1};
	static int N, H, W, min;
	

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC  = Integer.parseInt(br.readLine());
		
		for(int tc=1 ; tc<=TC ; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()); // 벽돌 개수
			W = Integer.parseInt(st.nextToken()); // map 넓이
			H = Integer.parseInt(st.nextToken()); // map 높이
			
			int[][] map =  new int[H][W];
			for(int i = 0 ; i < H ; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0 ; j < W; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			min = Integer.MAX_VALUE;
			go(0, map); // 구슬 던진 개수 0, 초기 입력 상태의 map
			System.out.println("#"+tc+" "+min);
		}
	}
	
	/**
	 * 구슬 던지기
	 * @param count 던져진 벽돌의 수
	 * @param map 벽돌의 상태들이 있어있음
	 * @return 던져서 벽돌이 모두 깨졌으면 true, 아니면 false 리턴
	 */
	private static boolean go(int count, int[][] map) {
		
		int remainCnt = getRemain(map);
		if(remainCnt == 0) {
			min = 0;
			return true; // 다 깨진 상황의 true 리턴
		}
		
		if(count == N) {
			min = Math.min(min, remainCnt);
			return false; // 다 깨졌는지 여부 리턴
		}
		
		// 모든 열에 던지기 시도
		int[][] newMap = new int[H][W]; // 벽돌 깨는 값 계속 넣을 새로운 map
		for(int c = 0 ; c < W; c++) { // 열 고정
			// 구슬에 맞는 가장 윗벽돌 찾기
			int r = 0;
			while(r<H && map[r][c] == 0) ++r; // 빈 칸이면 맵 밖으로 나가기 전까지 구슬 내리기
			
			if(r==H) continue; // 맞는 벽돌이 없으므로 다음 열에 던지기 시도
			
			// 벽돌 깨뜨리기 전에 벽돌 복사
			copy(map, newMap);
			// 해당 벽돌 깨뜨리기
			int brick = newMap[r][c];
			// 연쇄 벽돌 처리
			boom(r, c, newMap);
			if(brick > 1) { // 1이면 자기 혼자만 터지므로 연쇄나 중력 처리 필요 없음
				// 중력 작용 처리
				down(newMap);
			}
			// 다음 구슬 던지러 가기
			if(go(count+1, newMap)) return true;
		}
		return false;
	}
	
	private static int getRemain(int[][] map) {
		int cnt = 0;
		for(int r = 0 ; r < H ; r++) {
			for(int c= 0 ; c <  W ; c++) {
				if(map[r][c] > 0 ) ++cnt;
			}
		}
		return cnt;
	}

	private static Deque<Integer> stack = new ArrayDeque<>();
	private static void down(int[][] map) {
		for(int c = 0 ; c < W ; c++) {
			for(int r  = 0 ; r <H ; r++) { // 윗행부터 깨지지 않은 벽돌 스택에 담기
				if(map[r][c] == 0) continue;
				stack.push(map[r][c]);
				map[r][c] = 0;
			}
			
			int r = H-1;
			while(!stack.isEmpty()) {
				map[r--][c] = stack.pop();
			}
		}
	}

	private static void boom(int r, int c, int[][] map) {
		Deque<Point> q = new ArrayDeque<>();
		// 방문처리하고 큐에 놓기
		if(map[r][c] > 1) q.offer(new Point(r,c, map[r][c]));
		map[r][c] = 0; //방문처리(벽돌 깨뜨리기)
		
		while(!q.isEmpty()) {
			Point cur = q.poll();
			
			// 현재 벽돌에 쓰인 숫자 -1 만큰 사방탐색
			for(int d = 0 ; d < 4 ; d++) {
				int nr = cur.r;
				int nc = cur.c;
				for(int k = 1; k < cur.cnt ; k++) {
					nr += dr[d];
					nc += dc[d];
					if(nr >= 0 && nr < H && nc >= 0 && nc < W && map[nr][nc] > 0) {
						if(map[nr][nc] > 1) q.offer(new Point(nr, nc, map[nr][nc]));
						map[nr][nc] = 0;
					}
				}
			}
		}
	}

	private static void copy(int[][] map, int[][] newMap) {
		for(int r = 0 ; r < H; r++) {
			for(int c = 0 ; c < W ; c++) {
				newMap[r][c] = map[r][c];
			}
		}
	}

}