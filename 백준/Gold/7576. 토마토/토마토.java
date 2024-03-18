import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int M; // 상자의 행, 열
	static int[][] box; // 토마토가 들어가는 상자
	static boolean[][] isRipe; // 안 익었다가 익은 토마토 표시하기 위해
	static int numOfUnripe = 0;
	static Deque<int[]> q = new ArrayDeque<>();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		
		box = new int[N][M];
		isRipe = new boolean[N][M];
		
		// 박스에 토마토의 값 담기
		for(int i = 0 ; i < N ; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0 ; j < M ; j++) {
				int input = Integer.parseInt(st.nextToken());
				if(input == 0) numOfUnripe +=1; // 익지 않은 토마토 체크
				if(input == 1) {
					isRipe[i][j] = true;
					q.offer(new int[] {i, j});
				}
				box[i][j] = input; 
			}
		}
		
		int days = 0;
		if(numOfUnripe != 0) {
			 days = bfs();
		}
		
		System.out.println(days);
	}

	private static int bfs() {
		int[][] deltas = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}}; // 상 우 하 좌
		int days = 0;
		
		while(!q.isEmpty()) {
			int size = q.size();
			for(int i = 0 ; i < size ; i++) {
				int[] cur = q.poll();
				int cx = cur[0];
				int cy = cur[1];
				for(int d = 0 ; d < 4; d++) { // 사방 탐색
					int nr = cx + deltas[d][0];
					int nc = cy + deltas[d][1];
					if(nr < 0 || nr >= N || nc < 0 || nc >= M) continue; // 경계체크
					if(box[nr][nc] != 0 || isRipe[nr][nc]) continue; // 0이 아니거나, 이미 익은 상태인지 체크
					isRipe[nr][nc] = true;
					numOfUnripe -= 1;
					q.offer(new int[]{nr,nc});
				}
			}
			days++;
			if(numOfUnripe == 0) return days;
		}
		return -1;
	}
}