import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Solution {
	static int N, K;
	static int[][] map;
	static int maxH;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();

		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());

			map = new int[N][N];
			maxH = 0;

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					int input = Integer.parseInt(st.nextToken());
					maxH = Math.max(maxH, input);
					map[i][j] = input;
					
				}
			}

			int maxL = cut();
//			System.out.println("현재 최장길이: "+maxL);
			
			sb.append("#").append(tc).append(" ").append(maxL).append("\n");
		}
		System.out.println(sb);
	}

	private static int cut() {
		int maxL = 0;
		int[][] cpMap = new int[N][N];

		// 배열 하나 새로 복사하기
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				cpMap[i][j] = map[i][j];
			}
		}

		// 배열 중 한 픽셀의 높이를 0에서 k만큼 깎음
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				for (int k = 0; k <= K; k++) {
					cpMap[i][j] -= k;
					int curL = bfs(cpMap);
					maxL = Math.max(maxL, curL);
					cpMap[i][j] += k;
				}
			}
		}
		return maxL;
	}

	private static int bfs(int[][] cpMap) {
		//int maxH = 0; // 최대 높이
		int maxL = 0; // 최대 등산로의 길이\
		int[][] deltas = {{-1, 0},{0, 1},{1, 0},{0, -1}}; // 상 우 하 좌
		Deque<int[]> q = new ArrayDeque<>();

//		// 현재 배열에서 최댓값 구하기
//		for(int i = 0 ; i < N ; i++) {
//			for(int j = 0 ; j < N ; j++) {
//				if(cpMap[i][j] > maxH) maxH = cpMap[i][j];
//			}
//		}
		
		
//		for(int i = 0 ; i < N ; i++) {
//			for(int j = 0 ; j < N ; j++) {
//				System.out.print(cpMap[i][j]+" ");
//			}
//			System.out.println();
//		}
		
		// 최대 높이들 가진 좌표들 q에 넣기
		for(int i = 0 ; i < N ; i++) {
			for(int j = 0 ; j < N ; j++) {
				if(cpMap[i][j] == maxH) q.offer(new int[] {i, j});
				//System.out.println("최대 높이 q에 넣음.");
			}
		}
		
//		System.out.println("최대높이: "+maxH);
		
		// 큐가 모두 빌 때까지 진행
		while(!q.isEmpty()) {
			//현재 큐의 길이만큼 반복
			int size = q.size();
			for(int qs = 0 ; qs < size ; qs++) {
				int[] cur = q.poll();
				int r = cur[0];
				int c = cur[1];
				for(int d = 0 ; d < 4 ; d++) {
					int nr = r + deltas[d][0];
					int nc = c + deltas[d][1];
					if(!isValid(nr, nc)) continue; // 경계 체크
					if(cpMap[nr][nc] >= cpMap[r][c]) continue;// 여기서 온 길은 이미 걸러짐.
					q.offer(new int[] {nr, nc});
				}
			}
			maxL ++;
			//System.out.println("현재 최대 길이: "+maxL);
		}
		
		return maxL;

	}

	private static boolean isValid(int nr, int nc) {
		if(nr < 0 || nr >= N || nc < 0 || nc >= N) return false;
		return true;
	}

}