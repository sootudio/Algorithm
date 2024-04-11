import java.io.*;
import java.util.*;

public class Main {
	static int H, R, C; // 토마토 상자 가로,세로, 높이
	static int[][][] box ; // 토마토 담는 상자
	static List<int[]> redTomatos = new ArrayList<>(); // 익은 토마토 담는 리스트
	static int greenTomato = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine().trim());
		
		C = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());

		box = new int[H][R][C];
		int result = 0;
		
		// 값 받기
		for(int h = 0 ; h < H ; h++) {
			for(int i = 0 ; i < R; i++) {
				st = new StringTokenizer(br.readLine().trim());
				for(int j = 0 ; j < C ; j++) {
					int input = Integer.parseInt(st.nextToken());
					if(input == 1) {
						redTomatos.add(new int[] {h,i,j});
					}
					else if(input == 0) greenTomato++;
					box[h][i][j] = input;
				}
			}
		}
		
		
		// 익지 않은 토마토의 개수가 0이면 바로 0 출력
		if(greenTomato == 0) {
			result = 0;
		}
		else {
			result = bfs();
		}
		System.out.println(result);
		
	}

	private static int bfs() {
		int[][] deltas = {{0, -1, 0}, {0, 0, 1}, {0, 1, 0}, {0, 0, -1},{-1, 0, 0}, {1, 0, 0}}; // 상 우 하 좌 앞 뒤
		Deque<int[]> q = new ArrayDeque<>();
		int time = 0;
		
		//익은 토마토들의 좌표 넣기
		for(int i = 0 ; i < redTomatos.size() ; i++) {
			q.offer(redTomatos.get(i));
		}
		
		//큐가 빌 때까지 반복
		while(!q.isEmpty()) {
			// 익지 않은 토마토 개수가 0이면 
			if(greenTomato == 0) return time;
			time++;
			int size = q.size();
			for(int i = 0 ; i < size ; i++) {
				int[] cur = q.poll();
				int h = cur[0];
				int r = cur[1];
				int c = cur[2];
				for(int d = 0; d < 6 ; d++) {
					int nh = h + deltas[d][0];
					int nr = r + deltas[d][1];
					int nc = c + deltas[d][2];
					if(!isVaild(nh, nr, nc)) continue; // 경계체크
					if(box[nh][nr][nc] == -1 || box[nh][nr][nc] == 1) continue; // 이미 익거나 빈칸이라면
					greenTomato --;
					box[nh][nr][nc] = 1;
					
					q.offer(new int[] {nh, nr, nc});
				}
			}
			
		}
		return -1;
	}

	private static boolean isVaild(int nh, int nr, int nc) {
		return nh >= 0 && nh < H && nr >= 0 && nr < R && nc >=0 && nc < C;
	}

}