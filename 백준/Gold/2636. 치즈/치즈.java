import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int M;
	static int[][] board; // 사각형 판
	static boolean[][] notMelted; // 치즈가 녹았을때 바꾸는 행렬
	static boolean[][] visited; // bfs 할 때 방문 처리
	static int[][] deltas = {{-1, 0} , {0, 1}, {1, 0}, {0, -1}}; // 상 우 하 좌
	static int leftCheeze = 0; // 남은 치즈 개수
	static int curCheeze = 0; // bfs돌며 남은 치즈 개수
	static int hour = 0; // 치즈가 녹는 시간

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		board = new int[N][M];
		notMelted = new boolean[N][M];


		for(int i = 0 ; i < N ; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0 ; j < M ; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
				if(board[i][j] == 1) { // 치즈 입력 받았을 때 
					notMelted[i][j] = true;
					curCheeze +=1; 
				}
			}
		}

		do {
			hour += 1;
			leftCheeze = curCheeze;
			visited = new boolean[N][M];
			melting();
			for(int i = 0 ; i < N ; i++) {
				for(int j = 0 ; j < M ; j++) {
					if(!notMelted[i][j]) board[i][j] = 0;
				}
			}
		} while(curCheeze > 0);

		System.out.println(hour);
		System.out.println(leftCheeze);
	}

	/**
	 * 0, 0 에서 한번 bfs로 탐색하며 겉 부분의 치즈 녹이는 함수.
	 */
	private static void melting() {
		Queue<int[]> queue = new ArrayDeque<>();

		// 첫 탐색 위치 넣기 -> 항상 0,0부터 돌림
		queue.offer(new int[] {0,0});

		// queue가 빌 때까지 반복
		while(!queue.isEmpty()) {
			// 탐색할 위치의 좌표 받아오기
			int currentIndex[] = queue.poll();
			int r = currentIndex[0];
			int c = currentIndex[1];

			// 경계 체크
			if(r < 0 || r >= N || c < 0 || c >= M) continue;
			// 방문 체크
			if(visited[r][c]) continue;

			visited[r][c] = true;

			if(board[r][c] == 1) {
				notMelted[r][c] = false;
				curCheeze --;
			}
			else {
				// 4방향으로 탐색
				for(int i = 0 ; i < 4 ; i++) {
					int nr = r + deltas[i][0];
					int nc = c + deltas[i][1];
					queue.offer(new int[] {nr,nc});
				}
			}

			
		}
	}
}