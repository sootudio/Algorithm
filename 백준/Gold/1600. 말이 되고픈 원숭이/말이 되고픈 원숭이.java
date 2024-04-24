import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {

	// 원숭이의 움직임 deltas
	static int[][] deltas = {{-1, 0},{0, 1}, {1, 0},{0, -1}}; // 상 우 하 좌
	
	// 말의 움직임 hdeltas
	static int[][] hdeltas = {{-2, -1}, {-1, -2}, {-2, 1}, {-1, 2}, {2, 1}, {1, 2}, {2, -1}, {1, -2}};
	
	// 원숭이 class
	public static class Monkey{
		int r; // 현재 원숭이의 행좌표
		int c; // 현재 원숭이의 열좌표
		int k; // 현재 원숭이가 사용한 말 이동 횟수
		public Monkey(int r, int c, int k) {
			super();
			this.r = r;
			this.c = c;
			this.k = k;
		}
	}
	
	static int K, W, H ; // 사용할 수 있는 말 움직임 횟수, 넓이, 높이
	static int[][] map; // map
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		K = Integer.parseInt(br.readLine());

		StringTokenizer st = new StringTokenizer(br.readLine());
		W = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		
		map = new int[H][W];
		
		for(int i = 0 ; i < H ; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < W ; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int result = bfs();
		
		System.out.println(result);
	}

	/**
	 * 레벨별 bfs 탐색하는 함수
	 * @return 최소 이동 횟수
	 */
	private static int bfs() {
		boolean[][][] isVisited = new boolean[H][W][K+1]; // K번까지 표시해야 하므로, K + 1 크기로 만들기
		Deque<Monkey> q = new ArrayDeque<>();
		int cnt = 0; // 이동 횟수
		
		isVisited[0][0][0] = true; // 시작 좌표 방문처리
		q.offer(new Monkey(0, 0, 0)); // 원숭이의 시작 위치 q에 넣어주기
		
		// 큐가 빌 때까지 반복
		while(!q.isEmpty()) {
			int size = q.size();
			while(size-- > 0) {
				Monkey m = q.poll();
				// 마지막 지점에 도착했을 때, 이동 횟수 반환
				if(m.r == H-1 && m.c == W-1) {
					return cnt;
				}
				// 가랏 원숭이!
				for(int d = 0 ; d < 4 ; d++) {
					int nr = m.r + deltas[d][0];
					int nc = m.c + deltas[d][1];
					int k = m.k; // 원숭이 움직임으로 갈 땐 k가 그대로임.
					
					if(!isInRange(nr, nc)) continue; // 경계 체크
					if(map[nr][nc] == 1) continue; // 벽 체크
					if(isVisited[nr][nc][k]) continue; // 방문 체크
					
					// 이동 가능한 위치라면, 큐에 넣기
					q.offer(new Monkey(nr, nc, k));
					isVisited[nr][nc][k] = true; // 방문 처리
				}
				
				// 말 이동 횟수를 다 사용했다면, 말 이동은 하지 못하게 함.
				if(m.k == K) continue;
				
				// 원숭이 [홀스무빙] 시작!
				for(int d = 0 ; d < 8 ; d++) {
					int nr = m.r + hdeltas[d][0];
					int nc = m.c + hdeltas[d][1];
					int k = m.k + 1; // 말 이동을 사용하는 경우이므로 1 더하기
					
					if(!isInRange(nr, nc)) continue; // 경계 체크
					if(map[nr][nc] == 1) continue; // 벽 체크
					if(isVisited[nr][nc][k]) continue; // 방문 체크
					
					// 이동 가능한 위치라면, 큐에 넣기
					q.offer(new Monkey(nr, nc, k));
					isVisited[nr][nc][k] = true; // 방문 처리
				}
			}
			cnt ++; // 이동횟수 + 1
		}
		
		// 큐가 끝났는데도 return 하지 못했다면, 못하는 것.
		return -1;
	}

	/**
	 * 경계체크 메소드
	 * @param r 행좌표
	 * @param c 열좌표
	 * @return 경계 벗어나지 않으면 true, 벗어나면 false
	 */
	private static boolean isInRange(int r, int c) {
		return r >= 0 && r < H && c >= 0 && c < W;
	}

}