import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
	static int N;// map의 크기
	static int[][] map;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int problemNum = 1;
		
		while(true) {
			N = Integer.parseInt(br.readLine());
			
			if(N == 0) System.exit(0); // 0일때 프로그램 끝내기
			
			map = new int[N][N];
			
			for(int i = 0 ; i < N ; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for(int j = 0 ; j < N ; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			int less = bfs();
			
			System.out.println("Problem "+problemNum+": " + less);
			problemNum++;
		}
		
		
		
	}

	private static int bfs() {
		int[][] deltas = {{-1,0},{0,1},{1,0},{0,-1}}; //상 우 하 좌
		int[][] map2 = new int[N][N]; // 잃는 최솟값 담을 배열
		// 배열 복사
		for(int i = 0 ; i < N ; i++) {
			for(int j = 0 ; j < N ; j++) {
				map2[i][j] = Integer.MAX_VALUE;
			}
		}
		
		map2[0][0] = map[0][0];
		
		Deque<int[]> q = new ArrayDeque<>();
		q.offer(new int[] {0, 0});
		
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			int r = cur[0];
			int c = cur[1];
			
			for(int d = 0 ; d < 4 ; d++) {
				int nr = r + deltas[d][0];
				int nc = c + deltas[d][1];
				
				if(!isValid(nr, nc)) continue;
				if(map2[r][c]+map[nr][nc] >= map2[nr][nc]) continue;
				map2[nr][nc] = map2[r][c]+map[nr][nc];
				q.offer(new int[] {nr, nc});
			}
		}
		return map2[N-1][N-1];
	}

	private static boolean isValid(int nr, int nc) {
		if(nr < 0 || nr >= N || nc < 0 || nc >=N) return false;
		return true;
	}

}