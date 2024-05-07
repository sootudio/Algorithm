import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
	
	static int N, W, H;// 구슬 개수, 넓이, 높이, 테스트 케이스
	static int[][] map; // 처음 모양 담을 map
	static int min, total; // 남은 벽돌의 최소 개수, 처음 벽돌의 개수
	static int[][] deltas =  {{-1, 0},{0,1},{1,0},{0,-1}}; // 상 우 하 좌

	public static void main(String[] args) throws IOException {
		//System.setIn(new FileInputStream("res/sample_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		
		//테스트 케이스의 수만큼 반복
		for(int tc = 1 ; tc <= T ; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());
			
			map = new int[H][W];
			total = 0;
			
			for(int i=0 ; i < H ; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0 ; j < W ; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if(map[i][j]>0) total++; // 벽돌의 총 개수 세기
				}
			}
			
			// for(int[] a:map) System.out.println(Arrays.toString(a)); System.out.println();
			
			min = total;
			// 쏠 구슬을 골라서 벽돌의 최솟값을 구하는 permu 메서드 호출
			permu(map, 0, 0);
			// 구해진 최솟값 sb에 넣기
			sb.append("#").append(tc).append(" ").append(min==total? 0:min).append("\n");
		}
		// 결과 출력
		System.out.println(sb);
	}

	/**
	 * 
	 * @param map 지우는 2차원 배열 map
	 * @param cnt 고른 구슬 개수
	 * @param sum 부순 벽돌 수
	 */
	private static void permu(int[][] map, int cnt, int sum) {
		// 기저조건: N개를 뽑았을 때
		if(cnt == N) {
			min = Math.min(min, total - sum);
			return;
		}
		//부술 임시 배열 tap 선언
		int[][] tap = new int[H][W];
		// 유도파트: W개의 위치 중 중복해서 하나를 고름
		for(int c = 0 ; c < W ; c++) {
			int r = 0; // 시작 행
			while(r<H &&map[r][c] == 0) r++; // 시작위치 찾기
			if(r == H) continue; // 모든 행이 0이라면, 다른 행 찾음
			
			// 임시 배열 복사
			for(int i = 0 ; i < H ; i++) {
				for(int j = 0 ; j < W ; j++) {
					tap[i][j] = map[i][j];
				}
			}
			
			// dfs의 결괏값은 깬 벽돌수.
			int len = dfs(tap, r, c, tap[r][c]);
			
			if(len > 0) {
				down(tap);
				permu(tap, cnt+1, sum+len);
			}
			
		}
		
	}

	
	/**
	 * 벽돌 깨는 함수
	 * @param tap 벽돌을 깨는 배열
	 * @param r 깰 벽돌 행
	 * @param c 꺨 벽돌 열
	 * @param size 깰 벽돌에 있는 숫자(깰 범위)
	 * @return 깬 벽돌 수
	 */
	private static int dfs(int[][] tap, int r, int c, int size) {
		int broken = 1; // 일단 한개는 깰거이므로
		tap[r][c] = 0 ; // 깨기
		for(int d = 0 ; d < 4 ; d++) {
			for(int s = 1 ; s < size ; s++) {
				int nr = r + deltas[d][0]*s;
				int nc = c + deltas[d][1]*s;
				if(nr<0 || nr >= H || nc <0 || nc>= W ) continue; // 경계 체크
				if(tap[nr][nc] == 0) continue; // 빈칸 체크
				broken += dfs(tap,nr, nc, tap[nr][nc]);
			}
		}
		
		return broken;
	}
	
	/**
	 * 남은 벽돌들 내리는 함수
	 * @param tap 벽돌 내릴 배열 
	 */
	private static void down(int[][] tap) {
		ArrayDeque<Integer> stack = new ArrayDeque<Integer>();
		// 행 -> 열 순으로 탐색
		for(int j = 0 ; j < W; j++) {
			for(int i = 0 ; i < H ; i++) {
				if(tap[i][j] > 0) {
					stack.push(tap[i][j]);
					tap[i][j] = 0;
				}
				
			}
			int r = H-1;
			while(!stack.isEmpty()) {
				tap[r--][j] = stack.pop();
			}
		}
		
		
		
	}

}