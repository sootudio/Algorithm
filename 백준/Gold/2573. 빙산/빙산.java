import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N; // 바다의 높이
	static int M; // 바다의 넓이
	static int numOfIceBerg; // 빙산의 개수
	static int[][] sea; // 빙산이 있는 바다의 배열
	static boolean[][] iceBerg; // 녹을 빙산들이 있는 배열
	static int[][] deltas = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}}; // 상 우 하 좌
	static boolean[][] isVisited;
	static int lump = 0;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		sea = new int[N][M];
		iceBerg = new boolean[N][M];
		int year = 0;
		
		for(int i = 0 ; i < N ; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M ; j++) {
				int input =  Integer.parseInt(st.nextToken());
				if(input != 0) {
					iceBerg[i][j] = true;
					numOfIceBerg++;
				}
				sea[i][j] = input;
			}
		}
		
		while(true) {
			isVisited = new boolean[N][M];
			lump = 0;
			year ++;
			melt();
			replace();
			
			
			for(int i = 0 ; i < N ; i++) {
				for(int j = 0 ; j < M ; j++) {
					if(sea[i][j] == 0) continue;
					if(isVisited[i][j]) continue;
					bfs(i, j);
				}
			}
			if(lump > 1) break;
			if(numOfIceBerg < 1) {
				year = 0;
				break;
			}
		}
		System.out.println(year);
	}

	private static void bfs(int i, int j) {
		Queue<int[]> q = new ArrayDeque<>();
		q.offer(new int[] {i,j});
		isVisited[i][j] = true;
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			int cx = cur[0];
			int cy = cur[1];
			if(sea[cx][cy]==0) continue;
			for(int d = 0 ; d < 4 ; d++) {
				int nx = cx + deltas[d][0];
				int ny = cy + deltas[d][1];
				if(nx < 0 || nx >= N || ny < 0 || ny >= M) continue; // 경계체크
				if(sea[cx][cy]==0) continue;
				if(isVisited[nx][ny]) continue;
				q.offer(new int[] {nx, ny});
				isVisited[nx][ny] = true;
			}
		}
		lump++;
	}

	private static void replace() {
		for(int i = 0 ; i < N ; i++) {
			for(int j = 0 ; j < M ; j++) {
				if(sea[i][j] <= 0) {
					if(iceBerg[i][j]) numOfIceBerg -- ;
					iceBerg[i][j] = false;
					sea[i][j] = 0;
				}
			}
		}
	}

	private static void melt() {
		for(int i = 0 ; i < N ; i++) {
			for(int j = 0 ; j < M ; j++) {
				if(!iceBerg[i][j]) continue;
				for(int d = 0 ; d < 4 ; d++) {
					int nx = i + deltas[d][0];
					int ny = j + deltas[d][1];
					if(nx < 0 || nx >= N || ny < 0 || ny >= M) continue; // 경계체크
					if(iceBerg[nx][ny]) continue; // 빙산 체크
					sea[i][j]--;
				}
			}
		}
	}
	
	

}