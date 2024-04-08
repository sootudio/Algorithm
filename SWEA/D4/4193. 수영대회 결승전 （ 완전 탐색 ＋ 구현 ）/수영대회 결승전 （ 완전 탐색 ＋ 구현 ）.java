

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Solution {
	static int T; // 테스트 케이스의 수
	static int N; // 바다의 가로, 세로
	static int[][] sea;// 바다
	static int[] start; // 출발점
	static int[] end; //도착점
	static boolean[][] visited;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		
		for(int tc = 1; tc <= T ; tc++) {
			N = Integer.parseInt(br.readLine());
			sea = new int[N][N];
			start = new int[2];
			end = new int[2];
			visited = new boolean[N][N];
			
			// 바다 정보 입력 받기
			for(int i = 0 ; i < N ; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for(int j = 0 ; j < N ; j++) {
					sea[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			start[0] = Integer.parseInt(st.nextToken());
			start[1] = Integer.parseInt(st.nextToken());
			
			st = new StringTokenizer(br.readLine());
			end[0] = Integer.parseInt(st.nextToken());
			end[1] = Integer.parseInt(st.nextToken());
			
			int result = bfs();
			
			System.out.println("#"+tc+" "+result);
		}

	}

	// 레벨별 bfs
	private static int bfs() {
		int[][] deltas = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}}; //상 우 하 좌
		ArrayDeque<int[]> q = new ArrayDeque<>();
		q.offer(new int[] {start[0], start[1], 0}); //마지막은 이동하기까지 남은 시간
		int time = 0;
		
		// 큐가 빌 떄까지 반복
		while(!q.isEmpty()) {
			int size = q.size();
			
			while(size-- > 0) {
				int[] cur = q.poll();
				int r = cur[0];
				int c = cur[1];
				int lt = cur[2]; // 남은 시간
//				System.out.println("현재 좌표: "+r+", "+c);
//				System.out.println("현재 시간: "+time);
//				System.out.println();
				if(lt > 0){
					q.offer(new int[] {r, c, lt-1});
				}
				else {
					// 도착지점이면, 반환
					if(r == end[0] && c == end[1]) return time;
					for(int d = 0 ; d < 4 ; d++) {
						int nr = r + deltas[d][0];
						int nc = c + deltas[d][1];
						if(!isvalid(nr, nc)) continue; // 경계 체크
						if(visited[nr][nc]) continue; //방문 체크
						if(sea[nr][nc] == 1) continue; // 섬일 경우
						visited[nr][nc] = true;
						if(sea[nr][nc] == 2 ) {
							q.offer(new int[] {nr, nc, 3-time%3-1});
						}
						else {
							q.offer(new int[] {nr, nc, 0});
						}
						
					}
				}
			}
			time++;
		}
		
		return -1;
	}

	private static boolean isvalid(int nr, int nc) {
		if(nr < 0 || nr >= N || nc <0 || nc >= N) return false;
		return true;
	}

}
